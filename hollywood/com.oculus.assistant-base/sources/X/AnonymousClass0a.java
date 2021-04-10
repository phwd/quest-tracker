package X;

import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.0a  reason: invalid class name */
public final class AnonymousClass0a extends QX {
    public final String A00;
    public final String A01;

    @Override // X.PS, X.QX
    public final AbstractC1024qt A5H(String str) {
        if (str.startsWith(".")) {
            int length = str.length();
            String str2 = this.A01;
            int length2 = str2.length();
            StringBuilder sb = new StringBuilder(length + length2);
            if (length2 == 0) {
                str = str.substring(1);
            } else {
                sb.append(str2);
            }
            sb.append(str);
            str = sb.toString();
        }
        return super.A5H(str);
    }

    public AnonymousClass0a(AbstractC1024qt qtVar, C0300Pw pw) {
        super(qtVar, pw);
        String name = qtVar._class.getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf < 0) {
            this.A01 = OacrConstants.AUTO_SPEECH_DOMAIN;
            this.A00 = ".";
            return;
        }
        this.A00 = name.substring(0, lastIndexOf + 1);
        this.A01 = name.substring(0, lastIndexOf);
    }
}
