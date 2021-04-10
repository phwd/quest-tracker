package X;

/* renamed from: X.0E4  reason: invalid class name */
public final class AnonymousClass0E4 extends AnonymousClass0K0 {
    public final String A00;
    public final String A01;

    @Override // X.AnonymousClass0K0, X.AnonymousClass0o4
    public final AnonymousClass0aI A8c(String str) {
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
        return super.A8c(str);
    }

    public AnonymousClass0E4(AnonymousClass0aI r5, C07040od r6) {
        super(r5, r6);
        String name = r5._class.getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf < 0) {
            this.A00 = "";
            this.A01 = ".";
            return;
        }
        this.A01 = name.substring(0, lastIndexOf + 1);
        this.A00 = name.substring(0, lastIndexOf);
    }

    @Override // X.AnonymousClass0K0, X.AnonymousClass0o4
    public final String A56(Object obj) {
        String name = obj.getClass().getName();
        String str = this.A01;
        if (name.startsWith(str)) {
            return name.substring(str.length() - 1);
        }
        return name;
    }
}
