//package FromControle;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class Util {
//
//    
//    public static void clearScreen() {
//        for (int i = 0; i < 1000; i++) {
//            System.out.println("");
//        }
//    }    
//    
//    public static boolean isFlagged(String text) {
//        return text.startsWith("#!");
//
//    }
//
//    public static Long fixedValue(String text) {
//        return new Long(text.substring(2));
//    }
//
//    public static Date parseDate(String str) {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        sdf.setLenient(false);
//        try {
//            return (str == null || "".equals(str)) ?  null : sdf.parse(str);
//        } catch (java.text.ParseException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//}
