package X;

/* renamed from: X.0mI  reason: invalid class name */
public final class AnonymousClass0mI {
    public final char[][] A00 = new char[AnonymousClass007.A00(4).length][];

    public AnonymousClass0mI() {
        AnonymousClass007.A00(4);
    }

    public final char[] A00(Integer num, int i) {
        int i2;
        int intValue = num.intValue();
        switch (intValue) {
            case 2:
            case 3:
                i2 = 200;
                break;
            default:
                i2 = 2000;
                break;
        }
        if (i2 > i) {
            i = i2;
        }
        char[][] cArr = this.A00;
        char[] cArr2 = cArr[intValue];
        if (cArr2 == null || cArr2.length < i) {
            return new char[i];
        }
        cArr[intValue] = null;
        return cArr2;
    }
}
