package X;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;

/* renamed from: X.0Mz  reason: invalid class name */
public final class AnonymousClass0Mz implements Cloneable {
    public boolean A00;
    public final AnonymousClass0N1 A01;
    public final C08330wN A02;
    public final C01200Md A03;
    public final boolean A04;

    public final void A03(AbstractC08570wn r7) {
        synchronized (this) {
            if (!this.A00) {
                this.A00 = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        this.A03.A00 = C07790vM.A00.A01("response.body().close()");
        C08450wb r5 = this.A01.A0J;
        AnonymousClass0N0 r4 = new AnonymousClass0N0(this, r7);
        synchronized (r5) {
            Deque<AnonymousClass0N0> deque = r5.A02;
            if (deque.size() >= 64 || C08450wb.A00(r5, r4) >= 5) {
                r5.A01.add(r4);
            } else {
                deque.add(r4);
                r5.A02().execute(r4);
            }
        }
    }

    public final String A00() {
        C08400wU r1 = this.A02.A03;
        C08410wV r2 = new C08410wV();
        if (r2.A02(r1, "/...") != AnonymousClass007.A00) {
            r2 = null;
        }
        r2.A03 = C08400wU.A04("", " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
        r2.A02 = C08400wU.A04("", " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
        return r2.A03().toString();
    }

    public final C08220wC A01() throws IOException {
        ArrayList arrayList = new ArrayList();
        AnonymousClass0N1 r2 = this.A01;
        arrayList.addAll(r2.A08);
        arrayList.add(this.A03);
        arrayList.add(new C01240Mh(r2.A0I));
        arrayList.add(new C01270Mp());
        arrayList.add(new C01260Mo(r2));
        boolean z = this.A04;
        if (!z) {
            arrayList.addAll(r2.A09);
        }
        arrayList.add(new C01230Mg(z));
        C08330wN r8 = this.A02;
        return new C01220Mf(arrayList, null, null, null, 0, r8).A7Z(r8);
    }

    public final void A02() {
        AbstractC08080vr r1;
        C01250Mm r0;
        C01200Md r12 = this.A03;
        r12.A04 = true;
        C08090vs r3 = r12.A01;
        if (r3 != null) {
            synchronized (r3.A08) {
                r3.A04 = true;
                r1 = r3.A03;
                r0 = r3.A02;
            }
            if (r1 != null) {
                r1.cancel();
            } else if (r0 != null) {
                C08160w5.A07(r0.A03);
            }
        }
    }

    @Override // java.lang.Object
    public final Object clone() throws CloneNotSupportedException {
        return new AnonymousClass0Mz(this.A01, this.A02, this.A04);
    }

    public AnonymousClass0Mz(AnonymousClass0N1 r2, C08330wN r3, boolean z) {
        this.A01 = r2;
        this.A02 = r3;
        this.A04 = z;
        this.A03 = new C01200Md(r2, z);
    }
}
