package X;

public final class Uz extends Enum<Uz> {
    public static String A00(Integer num) {
        if (num == null) {
            return "null";
        }
        switch (num.intValue()) {
            case 1:
                return "BINARY";
            case 2:
                return "BOOLEAN";
            case 3:
                return "MISSING";
            case 4:
                return "NULL";
            case 5:
                return "NUMBER";
            case 6:
                return "OBJECT";
            case 7:
                return "POJO";
            case 8:
                return "STRING";
            default:
                return "ARRAY";
        }
    }
}
