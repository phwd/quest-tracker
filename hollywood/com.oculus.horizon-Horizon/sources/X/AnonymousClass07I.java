package X;

/* renamed from: X.07I  reason: invalid class name */
public final class AnonymousClass07I extends AnonymousClass0GO {
    public final String A00;

    @Override // X.AbstractC05940mA, X.AnonymousClass0GO
    public final AbstractC04000gb A9e(String str) {
        if (str.startsWith(".")) {
            int length = str.length();
            String str2 = this.A00;
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
        return super.A9e(str);
    }

    public AnonymousClass07I(AbstractC04000gb r5, C06240ml r6) {
        super(r5, r6);
        String substring;
        String name = r5._class.getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf < 0) {
            substring = "";
        } else {
            name.substring(0, lastIndexOf + 1);
            substring = name.substring(0, lastIndexOf);
        }
        this.A00 = substring;
    }
}
