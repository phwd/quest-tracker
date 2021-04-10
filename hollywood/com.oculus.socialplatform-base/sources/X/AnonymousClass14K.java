package X;

import com.oculus.localmedia.database.LocalMediaContract;

/* renamed from: X.14K  reason: invalid class name */
public final class AnonymousClass14K extends Enum<AnonymousClass14K> {
    public static String A00(Integer num) {
        if (num == null) {
            return "null";
        }
        switch (num.intValue()) {
            case 1:
                return "END_ARRAY";
            case 2:
                return "BEGIN_OBJECT";
            case 3:
                return "END_OBJECT";
            case 4:
                return "NAME";
            case 5:
                return "STRING";
            case 6:
                return "NUMBER";
            case 7:
                return LocalMediaContract.ExtrasTable.ColumnsTypes.BOOLEAN;
            case 8:
                return "NULL";
            case 9:
                return "END_DOCUMENT";
            default:
                return "BEGIN_ARRAY";
        }
    }
}
