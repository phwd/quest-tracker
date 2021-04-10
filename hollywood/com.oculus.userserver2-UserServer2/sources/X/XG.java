package X;

public abstract class XG {
    public static XG A00;

    public final void A00(XX xx, String str) {
        String str2;
        String substring;
        int indexOf = str.indexOf(":", 1);
        if (indexOf != -1) {
            str2 = str.substring(0, indexOf);
            substring = str.substring(indexOf + 1);
        } else {
            str2 = "";
            if (str.startsWith(":")) {
                substring = str.substring(1);
            } else {
                xx.A02(str2, str);
                return;
            }
        }
        xx.A02(str2, substring);
    }
}
