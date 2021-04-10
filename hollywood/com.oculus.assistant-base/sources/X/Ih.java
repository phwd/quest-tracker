package X;

public final class Ih {
    public static final String A00 = System.getProperty("scenario", null);
    public static final String[] A01 = new String[0];

    public static String[] A00(String str) {
        if (str == null || str.isEmpty()) {
            return A01;
        }
        return str.split(",,,");
    }
}
