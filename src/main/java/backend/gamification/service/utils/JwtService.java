package backend.gamification.service.utils;

import backend.gamification.enumkey.JwtEnum;
import backend.gamification.exception.InvalidTokenException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.*;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    //ATTRIBUTE
    private final KeyPair keyPair;
    private final ObjectMapper objectMapper;

    public JwtService(KeyPair keyPair, ObjectMapper objectMapper) {
        this.keyPair = keyPair;
        this.objectMapper = objectMapper;
    }
    //------------------------------------------------------------------------------------------------------------------

    //AUTOWIRED
    @Autowired
    Utils utils;
    //------------------------------------------------------------------------------------------------------------------

    //EXTRACT METHOD FROM TOKEN
    public Claims extractAllClaims(String token) throws InvalidTokenException {
        if (utils.isNullOrEmpty(token)) {
            throw new InvalidTokenException("Token is missing");
        }
        return Jwts
                .parserBuilder()
                .setSigningKey(keyPair.getPublic())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Map<String, Object> extractClaimsMap(String token) throws InvalidTokenException {
        try {
            Claims claims = extractAllClaims(token);
            return new HashMap<>(claims);
        } catch (InvalidTokenException e) {
            throw new InvalidTokenException(e);
        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws InvalidTokenException {
        try {
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
        } catch (InvalidTokenException e) {
            throw new InvalidTokenException(e);
        }
    }

    public <T> T extractAndDeserializeClaim(String token, String claimKey, Class<T> valueType) throws InvalidTokenException {
        try {
            Claims claims = extractAllClaims(token);
            String json = claims.get(claimKey, String.class);
            return objectMapper.readValue(json, valueType);
        } catch (InvalidTokenException e) {
            throw new InvalidTokenException("Failed to deserialize claim: " + claimKey, e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String extractTokenOwner(String token) throws InvalidTokenException {
        try {
            return extractClaim(token, Claims::getSubject);
        } catch (InvalidTokenException e) {
            throw new InvalidTokenException(e);
        }
    }

    public Date extractExpiration(String token) throws InvalidTokenException {
        try {
            return extractClaim(token, Claims::getExpiration);
        } catch (InvalidTokenException e) {
            throw new InvalidTokenException(e);
        }
    }
    //------------------------------------------------------------------------------------------------------------------

    //CHECK TOKEN VALID
    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            final String ownerName = extractTokenOwner(token);
            final String userName = userDetails.getUsername();
            Map<String, Object> claimsMap = extractClaimsMap(token);
            boolean repeatable = (Boolean) claimsMap.get(String.valueOf(JwtEnum.Is_Repeatable_JWT));
            boolean isUsed = (Boolean) claimsMap.get(String.valueOf(JwtEnum.Is_Used_JWT));
            boolean authenticateRequire = (Boolean) claimsMap.get(String.valueOf(JwtEnum.Is_Authenticated_Required_JWT));
            boolean authorizeRequire = (Boolean) claimsMap.get(String.valueOf(JwtEnum.Is_Authorize_Required_JWT));

            Date tokenExpiration = extractExpiration(token);
            if (tokenExpiration != null) {
                if (tokenExpiration.before(Date.from(Instant.now()))) {
                    return false;
                }
            }
            if (!repeatable) {
                if (isUsed) {
                    return false;
                }
            }
            if (authenticateRequire || authorizeRequire) {
                if (!ownerName.equals(userName)) {
                    return false;
                }
            }
            return true;
        } catch (InvalidTokenException e) {
            return false;
        }
    }
    //------------------------------------------------------------------------------------------------------------------

    //GENERATE TOKEN
    public String generateToken(Map<String, Object> claimsMap,
                                UserDetails userDetails,
                                boolean liveTimeRequire,
                                long liveTime,
                                boolean repeatable,
                                boolean authenticateRequire,
                                boolean authorizeRequire) {
        claimsMap.put(String.valueOf(JwtEnum.Is_Repeatable_JWT), repeatable);
        claimsMap.put(String.valueOf(JwtEnum.Is_Used_JWT), false);
        claimsMap.put(String.valueOf(JwtEnum.Is_Authenticated_Required_JWT), authenticateRequire);
        claimsMap.put(String.valueOf(JwtEnum.Is_Authorize_Required_JWT), authorizeRequire);

        try {
            for (Map.Entry<String, Object> entry : claimsMap.entrySet()) {
                entry.setValue(objectMapper.writeValueAsString(entry.getValue()));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing objects to JSON", e);
        }

        if (liveTimeRequire) {
            if (authenticateRequire || authorizeRequire) {
                return Jwts
                        .builder()
                        .addClaims(claimsMap)
                        .setSubject(userDetails.getUsername())
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + liveTime))
                        .signWith(keyPair.getPrivate(), SignatureAlgorithm.ES256)
                        .compact();
            } else {
                return Jwts
                        .builder()
                        .addClaims(claimsMap)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + liveTime))
                        .signWith(keyPair.getPrivate(), SignatureAlgorithm.ES256)
                        .compact();
            }
        } else {
            if (authenticateRequire || authorizeRequire) {
                return Jwts
                        .builder()
                        .addClaims(claimsMap)
                        .setSubject(userDetails.getUsername())
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .signWith(keyPair.getPrivate(), SignatureAlgorithm.ES256)
                        .compact();
            } else {
                return Jwts
                        .builder()
                        .addClaims(claimsMap)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .signWith(keyPair.getPrivate(), SignatureAlgorithm.ES256)
                        .compact();
            }
        }
    }

    //REGISTER TOKEN
    public String generateUserRegisterToken(Map<String, Object> registerResultMap,
                                            long liveDuration) {
        return generateToken(registerResultMap,
                null,
                true,
                liveDuration,
                false,
                false,
                false);
    }

    //OAUTH TOKEN
    public Map<String, Object> extractOAuthInformation (String token) throws InvalidTokenException {
        try {
            Claims claims = extractAllClaims(token);
            Map<String, Object> informationMap = new HashMap<>();
            informationMap.put(String.valueOf(JwtEnum.User_Id_OAuth_JWT), claims.getSubject());
            informationMap.put(String.valueOf(JwtEnum.User_Full_Name_OAuth_JWT), claims.get("name"));

        } catch (InvalidTokenException e) {
            throw new InvalidTokenException(e);
        }
        return  null;
    }

}
