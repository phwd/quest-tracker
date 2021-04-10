package X;

import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.bv  reason: case insensitive filesystem */
public abstract class AbstractC0558bv {
    public static AbstractC0558bv A00;

    public final void A00(C0541be beVar, String str) {
        String str2;
        String substring;
        int indexOf = str.indexOf(":", 1);
        if (indexOf != -1) {
            str2 = str.substring(0, indexOf);
            substring = str.substring(indexOf + 1);
        } else {
            boolean startsWith = str.startsWith(":");
            str2 = OacrConstants.AUTO_SPEECH_DOMAIN;
            if (startsWith) {
                substring = str.substring(1);
            } else {
                beVar.A02(str2, str);
                return;
            }
        }
        beVar.A02(str2, substring);
    }
}
