package X;

import java.util.HashSet;
import java.util.Locale;

/* renamed from: X.dg  reason: case insensitive filesystem */
public final class C0640dg implements AnonymousClass1W {
    public static final Locale A01;
    public static final Locale A02 = new Locale("ar", "XB");
    public static final Locale A03 = new Locale("en", "XA");
    public static final Locale[] A04 = new Locale[0];
    public final Locale[] A00;

    static {
        Locale locale;
        String str = "-";
        if (!"en-Latn".contains(str)) {
            str = "_";
            if (!"en-Latn".contains(str)) {
                locale = new Locale("en-Latn");
                A01 = locale;
            }
        }
        String[] split = "en-Latn".split(str, -1);
        int length = split.length;
        if (length > 2) {
            locale = new Locale(split[0], split[1], split[2]);
        } else if (length > 1) {
            locale = new Locale(split[0], split[1]);
        } else if (length == 1) {
            locale = new Locale(split[0]);
        } else {
            throw new IllegalArgumentException(AnonymousClass08.A05("Can not parse language tag: [", "en-Latn", "]"));
        }
        A01 = locale;
    }

    @Override // X.AnonymousClass1W
    public final Object A2b() {
        return null;
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof C0640dg) {
                Locale[] localeArr = ((C0640dg) obj).A00;
                Locale[] localeArr2 = this.A00;
                int length = localeArr2.length;
                if (length == localeArr.length) {
                    for (int i = 0; i < length; i++) {
                        if (localeArr2[i].equals(localeArr[i])) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        int i2 = 0;
        while (true) {
            Locale[] localeArr = this.A00;
            if (i2 >= localeArr.length) {
                return i;
            }
            i = (i * 31) + localeArr[i2].hashCode();
            i2++;
        }
    }

    @Override // X.AnonymousClass1W
    public final Locale A2F(int i) {
        if (i < 0) {
            return null;
        }
        Locale[] localeArr = this.A00;
        if (i < localeArr.length) {
            return localeArr[i];
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        int i = 0;
        while (true) {
            Locale[] localeArr = this.A00;
            int length = localeArr.length;
            if (i < length) {
                sb.append(localeArr[i]);
                if (i < length - 1) {
                    sb.append(',');
                }
                i++;
            } else {
                sb.append("]");
                return sb.toString();
            }
        }
    }

    public C0640dg(Locale... localeArr) {
        int length = localeArr.length;
        if (length == 0) {
            this.A00 = A04;
            return;
        }
        Locale[] localeArr2 = new Locale[length];
        HashSet hashSet = new HashSet();
        for (int i = 0; i < length; i++) {
            Locale locale = localeArr[i];
            if (locale == null) {
                throw new NullPointerException(AnonymousClass08.A01("list[", i, "] is null"));
            } else if (!hashSet.contains(locale)) {
                Locale locale2 = (Locale) locale.clone();
                localeArr2[i] = locale2;
                locale2.getCountry();
                hashSet.add(locale2);
            } else {
                throw new IllegalArgumentException(AnonymousClass08.A01("list[", i, "] is a repetition"));
            }
        }
        this.A00 = localeArr2;
    }
}
