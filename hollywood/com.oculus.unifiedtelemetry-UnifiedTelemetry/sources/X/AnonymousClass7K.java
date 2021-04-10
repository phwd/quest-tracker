package X;

/* renamed from: X.7K  reason: invalid class name */
public final class AnonymousClass7K extends C4 {
    public final String A00;

    @Override // X.V3, X.C4
    public final AbstractC0224Wl A5W(String str) {
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
        return super.A5W(str);
    }

    public AnonymousClass7K(AbstractC0224Wl wl, NT nt) {
        super(wl, nt);
        String substring;
        String name = wl._class.getName();
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
