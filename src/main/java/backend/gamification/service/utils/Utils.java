package backend.gamification.service.utils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
public class Utils {
    private static double epsilon = 0.000001;

    //SEPARATE STRING TO LIST
    public List<String> separateStringToListString(String s) {
        if (s.isEmpty()) {
            return null;
        }
        String[] list = s.split("");
        List<String> sl = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            sl.add(list[i].trim());
        }
        return sl;
    }

    public List<String> separateStringWithCommaToListString(String s) {
        if (s.isEmpty()) {
            return null;
        }
        String[] list = s.split(",");
        List<String> sl = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            sl.add(list[i].trim());
        }
        return sl;
    }

    //ALPHABET UPPERCASE
    public List<String> getAlphabetList() {
        List<String> list = separateStringToListString("QWERTYUIOPASDFGHJKLZXCVBNM1234567890");
        return list;
    }

    //STRING AND TIME CONVERSION
    public List<String> localDateTimeFormatterList() {
        List<String> fomartterList = new ArrayList<>();
        fomartterList.add("yyyy-MM-dd'T'HH:mm");
        fomartterList.add("yyyy/MM/dd'T'HH:mm");
        fomartterList.add("dd/MM/yyyy HH:mm");
        fomartterList.add("dd-MM-yyyy HH:mm");
        fomartterList.add("dd/MM/yyyy' At: 'HH:mm");
        return fomartterList;
    }

    public List<String> localDateFormatterList() {
        List<String> formaterList = new ArrayList<>();
        formaterList.add("yyyy-MM-dd");
        formaterList.add("yyyy/MM/dd");
        formaterList.add("dd/MM/yyyy");
        formaterList.add("dd-MM-yyyy");
        return formaterList;
    }

    public LocalDate convertLocalDateTimeToLocalDate(LocalDateTime localDateTime) {
        try {
            LocalDate localDate = localDateTime.toLocalDate();
            return localDate;
        } catch (Exception e) {
            return null;
        }
    }

    public LocalDateTime convertLocalDateToLocalDateTime(LocalDate localDate) {
        try {
            LocalDateTime localDateTime = localDate.atStartOfDay();
            return localDateTime;
        } catch (Exception e) {
            return null;
        }
    }

    public String convertLocalDateTimeToString(LocalDateTime inputLocalDateTime) {
        if (inputLocalDateTime == null) {
            return null;
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                return inputLocalDateTime.format(formatter);
            } catch (Exception e) {
                return null;
            }
        }
    }

    public LocalDateTime convertStringToLocalDateTime(String inputString) {
        if (inputString == null) {
            return null;
        } else if (inputString.isEmpty()) {
            return null;
        } else {
            for (String formatString : localDateTimeFormatterList()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
                try {
                    LocalDateTime result = LocalDateTime.parse(inputString, formatter);
                    return result;
                } catch (Exception e) {
                }
            }
            for (String formatString : localDateFormatterList()) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
                    LocalDate tempResult = LocalDate.parse(inputString, formatter);
                    return convertLocalDateToLocalDateTime(tempResult);
                } catch (Exception e) {
                }
            }
            return null;
        }
    }

    public String convertLocalDateToString(LocalDate inputLocalDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return inputLocalDate.format(formatter);
        } catch (Exception e) {
            return null;
        }
    }

    public LocalDate convertStringToLocalDate(String inputString) {
        if (inputString == null) {
            return null;
        } else if (inputString.isEmpty()) {
            return null;
        } else {
            for (String formatString : localDateFormatterList()) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
                    return LocalDate.parse(inputString, formatter);
                } catch (Exception e) {
                }
            }
            for (String formatString : localDateTimeFormatterList()) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
                    LocalDateTime tempResult = LocalDateTime.parse(inputString, formatter);
                    return convertLocalDateTimeToLocalDate(tempResult);
                } catch (Exception e) {
                }
            }
            return null;
        }
    }

    public String viewStringOfLocalDateTime(String inputLocalDateTimeString, String formatter) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(formatter);
        try {
            Date date = inputDateFormat.parse(inputLocalDateTimeString);
            String outputLocalDateTimeString = outputDateFormat.format(date);
            return outputLocalDateTimeString;
        } catch (DateTimeParseException e) {
            return null;
        } catch (ParseException e) {
            return null;
        }
    }

    public String viewStringOfLocalDate(String inputLocalDateString, String formatter) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(formatter);
        try {
            Date date = inputDateFormat.parse(inputLocalDateString);
            String outputLocalDateString = outputDateFormat.format(date);
            return outputLocalDateString;
        } catch (ParseException e) {
            return null;
        }
    }

    public String viewDayMonthYear(LocalDateTime input) {
        if (input == null) {
            return null;
        } else {
            int year = input.getYear();
            int month = input.getMonthValue();
            int day = input.getDayOfMonth();
            String MM = new String();
            String dd = new String();
            if (month < 10) {
                MM = "0" + month;
            } else {
                MM = String.valueOf(month);
            }
            if (day < 10) {
                dd = "0" + day;
            } else {
                dd = String.valueOf(day);
            }
            return dd + "/" + MM + "/" + year;
        }
    }

    public String viewHourMinute(LocalDateTime input) {
        if (input == null) {
            return null;
        } else {
            int hour = input.getHour();
            int minute = input.getMinute();
            String HH = new String();
            String mm = new String();
            if (hour < 10) {
                HH = "0" + hour;
            } else {
                HH = String.valueOf(hour);
            }
            if (minute < 10) {
                mm = "0" + minute;
            } else {
                mm = String.valueOf(minute);
            }
            return HH + ":" + mm;
        }
    }

    public String viewDayMonthYear(String input) {
        LocalDateTime localDateTime = convertStringToLocalDateTime(input);
        return viewDayMonthYear(localDateTime);
    }

    public String viewHourMinute(String input) {
        LocalDateTime localDateTime = convertStringToLocalDateTime(input);
        return viewHourMinute(localDateTime);
    }

    //GENERATE RANDOM UNIQUE KEY IN SET
    public String generateRandomKeyInSet(Set<String> list, int stringLength) {
        String s = "";
        List<String> alphabet = getAlphabetList();
        do {
            for (int j = 1; j <= stringLength; j++) {
                Collections.shuffle(alphabet);
                s = s + alphabet.get(0);
            }
        } while (list.add(s) == false);
        return s;
    }

    //FILE AND BASE64 CONVERSION
    public Blob convertBase64ToBlob(String base64String) {
        try {
            byte[] imgData = Base64.getDecoder().decode(base64String);
            return new SerialBlob(imgData);
        } catch (Exception e) {
            return null;
        }
    }

    public String convertBlobToBase64(Blob blob) {
        if (blob == null) {
            return "";
        }
        try {
            byte[] blobBytes = blob.getBytes(1L, (int) blob.length());
            return Base64.getEncoder().encodeToString(blobBytes);
        } catch (SQLException e) {
            return "";
        }
    }

    public Blob convertMultipartFileToBlob(MultipartFile file) {
        try {
            byte[] imgData = file.getBytes();
            return new SerialBlob(imgData);
        } catch (SerialException e) {
            return null;
        } catch (SQLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public String convertMultipartFileToBase64(MultipartFile file) {
        try {
            if (file == null) {
                return "";
            }
            byte[] imgData = file.getBytes();
            return Base64.getEncoder().encodeToString(imgData);
        } catch (IOException e) {
            return "";
        }
    }

    //AUTO GENERATE ALPHABET LIST FROM INPUT NUMBER (COMBINATION OF 3 FUNCTIONS)
    public List<String> autoGenerateAlphabetList(int inputNumber) {
        List<String> alphabet = separateStringToListString("ABCDEFGHIJKLMNOPQRSTRUVWXYZ");
        List<String> result = new ArrayList<>();
        int limit = alphabet.size() - 1;
        List<Integer> loop = loop(inputNumber, limit + 1);
        for (int i = 0; i < loop.size(); i++) {
            int[][] array = transfer(loop.get(i), i + 1, limit);
            for (int j = 0; j < array.length; j++) {
                String s = "";
                for (int k = 0; k < array[i].length; k++) {
                    s = s + alphabet.get(array[j][k]);
                }
                result.add(s);
            }
        }
        return result;
    }

    public int[][] transfer(int row, int col, int limit) {
        int[][] result = new int[row][col];
        for (int i = 0; i < result.length; i++) {
            if (i != 0) {
                int[] array = result[i - 1];
                int[] arrayNew = array.clone();
                arrayNew[arrayNew.length - 1] = array[array.length - 1] + 1;
                result[i] = resolveOnLimit(arrayNew, limit);
            }
        }
        return result;
    }

    public List<Integer> loop(int input, int size) {
        List<Integer> result = new ArrayList<>();
        double power = 1;
        do {
            if (input > Math.pow(size, power)) {
                input = input - Integer.valueOf((int) Math.pow(size, power));
                result.add((int) Math.pow(size, power));
                power++;
            } else {
                result.add(input);
                break;
            }
        } while (true);
        return result;
    }

    public int[] resolveOnLimit(int[] array, int limit) {
        int[] result = array.clone();
        for (int i = 0; i < result.length; i++) {
            int k = 0;
            if (result[i] > limit && i > 0) {
                for (int j = i; j < result.length; j++) {
                    result[j] = 0;
                }
                for (int j = i - 1; j >= 0; j--) {
                    if (result[j] + 1 <= limit) {
                        result[j] = result[j] + 1;
                        k = j;
                        break;
                    }
                }
                if (k < result.length - 1) {
                    for (int j = k + 1; j < result.length; j++) {
                        result[j] = 0;
                    }
                }
            }
        }
        return result;
    }

    //GUARANTEE TRANSFER STRING TO NUMBER
    public String ultimateNumberFormatter(String input) {
        input = input.replaceAll("[^0-9\\-]", "")
                .replaceAll("^0+(?!$)", "")
                .replaceAll("[^0-9\\-]|(?<!^)([\\-])", "")
                .replaceAll("^(-?)0+(\\d+)$", "$1$2");
        if (input.equals("-") | input.equals("")) {
            input = "0";
        }
        return input;
    }

    //PROVIDE INTEGER LIST FROM MIN TO MAX WITH STEP
    public List<Integer> integerListProvider(int min, int max, int step) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = min; i <= max; i = i + step) {
            if (i <= max) {
                integerList.add(i);
            }
        }
        integerList.add(max);
        return integerList;
    }

    public Set<Integer> randomIntegerSet(int min, int max, int step) {
        List<Integer> integerList = integerListProvider(min, max, step);
        Collections.shuffle(integerList);
        return new HashSet<>(integerList);
    }

    //INTEGER LIST FROM MIN TO MAX WITH STEP
    public List<Integer> createIntegerListMinToMaxWithStep(int min, int max, int step) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = min; i <= max; i = i + step) {
            if (i < max) {
                integerList.add(i);
            }
        }
        integerList.add(max);
        return integerList;
    }

    public List<Double> autoRescaleRating(List<Double> originalRewardRating) {
        double totalSum = 0;
        DecimalFormat df = new DecimalFormat("#.####");
        for (int i = 0; i < originalRewardRating.size(); i++) {
            totalSum = totalSum + originalRewardRating.get(i);
        }
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < originalRewardRating.size(); i++) {
            double rescaleNumber = Double.valueOf(df.format(originalRewardRating.get(i) / totalSum));
            result.add(rescaleNumber);
        }
        return result;
    }

    public boolean isEqual(double a, int b) {
        return Math.abs(a - b) < epsilon;
    }


    public int multi10OfPercentage(List<Double> percentageList) {
        int result = 1;

        for (int i = 0; i < percentageList.size(); i++) {
            double number = percentageList.get(i);
            if (number != 0 && !isEqual(number * result, (int) (number * result))) {
                do {
                    result *= 10;
                } while (!isEqual(number * result, (int) (number * result)));
            }
        }
        return result;
    }

    public void putLog(String string) {
        System.out.println("----------------------------------------------------------------");
        System.out.println(string);
    }

    //CHECK NULL OR EMPTY STRING
    public boolean isNullOrEmpty(String string) {
        if (string == null) {
            return true;
        }
        if (string.isEmpty()) {
            return true;
        }
        return false;
    }

    //BOOLEAN AND STRING CONVERSION
    public String convertBooleanToString(boolean trueFalseBoolean) {
        if (trueFalseBoolean) {
            return "true";
        } else {
            return "false";
        }
    }

    public boolean convertStringToBoolean(String trueFalseString) {
        if (trueFalseString == null) {
            return false;
        }
        if (trueFalseString.equalsIgnoreCase("true")) {
            return true;
        } else {
            return false;
        }
    }

    public String convertBufferedImageToBase64(BufferedImage image) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            String base64 = Base64.getEncoder().encodeToString(imageBytes);
            System.out.println(base64);
            return base64;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String convertImageUrlToBase64(String imageUrl, long onUrl) {
        if (onUrl == 0) {
            //From Project
            try (InputStream inputStream = ImageIO.class.getResourceAsStream(imageUrl)) {
                if (inputStream == null) {
                    System.out.println("InputStream is null");
                    return "";
                }
                BufferedImage image = ImageIO.read(inputStream);
                return convertBufferedImageToBase64(image);
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        } else {
            //From Url
            try (InputStream inputStream = new URL(imageUrl).openStream()) {
                byte[] imageData = inputStream.readAllBytes();
                return Base64.getEncoder().encodeToString(imageData);
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
    }
}
