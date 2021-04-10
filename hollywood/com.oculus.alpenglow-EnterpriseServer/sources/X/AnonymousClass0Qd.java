package X;

import java.io.IOException;
import java.util.ArrayList;

/* renamed from: X.0Qd  reason: invalid class name */
public final class AnonymousClass0Qd implements Cloneable {
    public boolean A00;
    public final AnonymousClass0Qs A01;
    public final C05700ke A02;
    public final AnonymousClass0PV A03;
    public final boolean A04;

    public final String A00() {
        C05890la r1 = this.A02.A03;
        C06000lm r2 = new C06000lm();
        if (r2.A02(r1, "/...") != AnonymousClass007.A00) {
            r2 = null;
        }
        int length = "".length();
        r2.A03 = C05890la.A02("", 0, length, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
        r2.A02 = C05890la.A02("", 0, length, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
        return r2.A03().toString();
    }

    public final C05660kD A01() throws IOException {
        ArrayList arrayList = new ArrayList();
        AnonymousClass0Qs r2 = this.A01;
        arrayList.addAll(r2.A06);
        arrayList.add(this.A03);
        arrayList.add(new AnonymousClass0Pa(r2.A0G));
        arrayList.add(new C02010Py());
        arrayList.add(new C02000Px(r2));
        boolean z = this.A04;
        if (!z) {
            arrayList.addAll(r2.A07);
        }
        arrayList.add(new AnonymousClass0PY(z));
        C05700ke r8 = this.A02;
        return new AnonymousClass0PX(arrayList, null, null, null, 0, r8).A00(r8);
    }

    @Override // java.lang.Object
    public final Object clone() throws CloneNotSupportedException {
        return new AnonymousClass0Qd(this.A01, this.A02, this.A04);
    }

    public AnonymousClass0Qd(AnonymousClass0Qs r2, C05700ke r3, boolean z) {
        this.A01 = r2;
        this.A02 = r3;
        this.A04 = z;
        this.A03 = new AnonymousClass0PV(r2, z);
    }
}
