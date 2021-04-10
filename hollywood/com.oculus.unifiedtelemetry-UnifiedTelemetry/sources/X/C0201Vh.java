package X;

import java.io.IOException;

/* renamed from: X.Vh  reason: case insensitive filesystem */
public final class C0201Vh {
    public AbstractC0202Vi A00;
    public Object A01;
    public int A02;
    public final AbstractC0226Wn A03;
    public final C0204Vm A04;
    public final Object[] A05;
    public final AbstractC0232Ww A06;

    public final void A00(C0263Yn yn, String str, Object obj) {
        this.A00 = new WU(this.A00, obj, yn, str);
    }

    public final void A01(AbstractC0073Cr cr, Object obj) {
        this.A00 = new WS(this.A00, obj, cr);
    }

    public final boolean A02(int i, Object obj) {
        this.A05[i] = obj;
        int i2 = this.A02 - 1;
        this.A02 = i2;
        if (i2 > 0) {
            return false;
        }
        return true;
    }

    public final boolean A03(String str) throws IOException {
        C0204Vm vm = this.A04;
        if (vm == null || !str.equals(vm.propertyName)) {
            return false;
        }
        this.A01 = vm.deserializer.A09(this.A06, this.A03);
        return true;
    }

    public C0201Vh(AbstractC0232Ww ww, AbstractC0226Wn wn, int i, C0204Vm vm) {
        this.A06 = ww;
        this.A03 = wn;
        this.A02 = i;
        this.A04 = vm;
        this.A05 = new Object[i];
    }
}
