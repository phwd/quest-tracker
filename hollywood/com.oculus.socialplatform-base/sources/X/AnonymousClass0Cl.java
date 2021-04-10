package X;

/* renamed from: X.0Cl  reason: invalid class name */
public final class AnonymousClass0Cl extends AnonymousClass0On {
    public final String A00;
    public final String A01;

    @Override // X.AnonymousClass0On, X.AbstractC04530qb
    public final AbstractC02190iF AAn(String str) {
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
        return super.AAn(str);
    }

    public AnonymousClass0Cl(AbstractC02190iF r5, AnonymousClass0r9 r6) {
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

    @Override // X.AnonymousClass0On, X.AbstractC04530qb
    public final String A5X(Object obj) {
        String name = obj.getClass().getName();
        String str = this.A01;
        if (name.startsWith(str)) {
            return name.substring(str.length() - 1);
        }
        return name;
    }
}
