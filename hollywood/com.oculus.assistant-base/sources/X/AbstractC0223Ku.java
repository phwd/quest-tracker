package X;

/* renamed from: X.Ku  reason: case insensitive filesystem */
public abstract class AbstractC0223Ku {
    public final AbstractC0223Ku A00(String str, Object obj) {
        if (!(this instanceof C0982py)) {
            return this;
        }
        C0982py pyVar = (C0982py) this;
        C0222Kt kt = pyVar.A01;
        String valueOf = String.valueOf(obj);
        String[] strArr = kt.A01;
        int length = strArr.length;
        int i = kt.A00;
        if (length - i < 2) {
            String[] strArr2 = new String[(length << 1)];
            kt.A01 = strArr2;
            System.arraycopy(strArr, 0, strArr2, 0, i);
        }
        String[] strArr3 = kt.A01;
        int i2 = kt.A00;
        int i3 = i2 + 1;
        kt.A00 = i3;
        strArr3[i2] = str;
        kt.A00 = i3 + 1;
        strArr3[i3] = valueOf;
        return pyVar;
    }

    public final void A01() {
        if (this instanceof C0982py) {
            C0982py pyVar = (C0982py) this;
            pyVar.A02.A2A(pyVar.A00, pyVar.A03, pyVar.A01);
        }
    }
}
