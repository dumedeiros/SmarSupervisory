package Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static void clearScreen() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("");
        }
    }

    public static boolean isFlagged(String text) {
        return text.startsWith("#!");

    }

    public static Long fixedValue(String text) {
        return new Long(text.substring(2));
    }

    public static Date parseDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            return (str == null || "".equals(str)) ? null : sdf.parse(str);
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String formatData(String s) {
        char[] x = s.toCharArray();
        String a = "", b = "";
        int c = 0;
        for (int i = x.length - 1; i >= 0; i--) {
            if (x[i] == '.') {
                c++;
                if (c >= 2) {
                    return b;
                }
            }
            if (x[0] == 'S' && x[i] == '_') {
                return b;
            }

            b = x[i] + b;
        }
        return b;

    }
//    public static String formatData(String s) {
//        char[] x = s.toCharArray();
//        String a = "", b = "";
//
//        for (int i = 0; i < s.length(); i++) {
//            b += x[i];
//            if (x[i] == '.') {
//                a = b;
//                b = "";
//            }
//        }
//        return a + b;
//    }
}
