package X;

import androidx.annotation.NonNull;
import java.io.File;
import java.util.List;

/* renamed from: X.1f6  reason: invalid class name and case insensitive filesystem */
public final class C08681f6 implements AbstractC08981fq, AnonymousClass1Ry<Object> {
    public int A00;
    public List<AbstractC07011bT<File, ?>> A01;
    public int A02 = -1;
    public AbstractC06491aL A03;
    public File A04;
    public final AnonymousClass1fL A05;
    public final AnonymousClass1ez<?> A06;
    public final List<AbstractC06491aL> A07;
    public volatile C07091bb<?> A08;

    @Override // X.AnonymousClass1Ry
    public final void A6x(Object obj) {
        this.A05.A6w(this.A03, obj, this.A08.A01, AnonymousClass1fM.DATA_DISK_CACHE, this.A03);
    }

    @Override // X.AnonymousClass1Ry
    public final void A7F(@NonNull Exception exc) {
        this.A05.A6v(this.A03, exc, this.A08.A01, AnonymousClass1fM.DATA_DISK_CACHE);
    }

    @Override // X.AbstractC08981fq
    public final boolean AAU() {
        while (true) {
            List<AbstractC07011bT<File, ?>> list = this.A01;
            if (list == null || this.A00 >= list.size()) {
                int i = this.A02 + 1;
                this.A02 = i;
                List<AbstractC06491aL> list2 = this.A07;
                if (i >= list2.size()) {
                    return false;
                }
                AbstractC06491aL r3 = list2.get(this.A02);
                AnonymousClass1ez<?> r2 = this.A06;
                File A3L = r2.A07.A00().A3L(new C06501aM(r3, r2.A04));
                this.A04 = A3L;
                if (A3L != null) {
                    this.A03 = r3;
                    this.A01 = r2.A02.A02.A01(A3L);
                    this.A00 = 0;
                }
            } else {
                this.A08 = null;
                while (this.A00 < this.A01.size()) {
                    List<AbstractC07011bT<File, ?>> list3 = this.A01;
                    int i2 = this.A00;
                    this.A00 = i2 + 1;
                    File file = this.A04;
                    AnonymousClass1ez<?> r32 = this.A06;
                    this.A08 = list3.get(i2).A1r(file, r32.A01, r32.A00, r32.A05);
                    if (this.A08 != null && r32.A01(this.A08.A01.A3h()) != null) {
                        this.A08.A01.A6H(r32.A03, this);
                        return true;
                    }
                }
                return false;
            }
        }
    }

    @Override // X.AbstractC08981fq
    public final void cancel() {
        C07091bb<?> r0 = this.A08;
        if (r0 != null) {
            r0.A01.cancel();
        }
    }

    public C08681f6(List<AbstractC06491aL> list, AnonymousClass1ez<?> r3, AnonymousClass1fL r4) {
        this.A07 = list;
        this.A06 = r3;
        this.A05 = r4;
    }
}
