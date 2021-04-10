package X;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;

public final class LB implements Cloneable {
    public boolean A00;
    public final LD A01;
    public final C0362dj A02;
    public final L1 A03;
    public final boolean A04;

    public final C0359dg A02() throws IOException {
        Deque<LB> deque;
        synchronized (this) {
            if (!this.A00) {
                this.A00 = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        this.A03.A00 = C0324cr.A00.A01("response.body().close()");
        try {
            C0373dw dwVar = this.A01.A0J;
            synchronized (dwVar) {
                deque = dwVar.A03;
                deque.add(this);
            }
            C0359dg A012 = A01();
            C0373dw.A02(dwVar, deque, this, false);
            return A012;
        } catch (Throwable th) {
            C0373dw dwVar2 = this.A01.A0J;
            C0373dw.A02(dwVar2, dwVar2.A03, this, false);
            throw th;
        }
    }

    public final String A00() {
        C0367dp dpVar = this.A02.A03;
        C0368dq dqVar = new C0368dq();
        if (dqVar.A02(dpVar, "/...") != AnonymousClass07.A00) {
            dqVar = null;
        }
        dqVar.A03 = C0367dp.A04("", " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
        dqVar.A02 = C0367dp.A04("", " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
        return dqVar.A03().toString();
    }

    public final C0359dg A01() throws IOException {
        ArrayList arrayList = new ArrayList();
        LD ld = this.A01;
        arrayList.addAll(ld.A08);
        arrayList.add(this.A03);
        arrayList.add(new L5(ld.A0I));
        arrayList.add(new L8());
        arrayList.add(new L7(ld));
        boolean z = this.A04;
        if (!z) {
            arrayList.addAll(ld.A09);
        }
        arrayList.add(new L4(z));
        C0362dj djVar = this.A02;
        return new L3(arrayList, null, null, null, 0, djVar).A00(djVar);
    }

    @Override // java.lang.Object
    public final Object clone() throws CloneNotSupportedException {
        return new LB(this.A01, this.A02, this.A04);
    }

    public LB(LD ld, C0362dj djVar, boolean z) {
        this.A01 = ld;
        this.A02 = djVar;
        this.A04 = z;
        this.A03 = new L1(ld, z);
    }
}
