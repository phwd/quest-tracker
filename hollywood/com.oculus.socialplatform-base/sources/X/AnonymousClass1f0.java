package X;

import androidx.annotation.NonNull;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1f0  reason: invalid class name */
public final class AnonymousClass1f0 implements AbstractC08981fq, AnonymousClass1Ry<Object> {
    public int A00;
    public List<AbstractC07011bT<File, ?>> A01;
    public int A02 = -1;
    public int A03;
    public AbstractC06491aL A04;
    public AnonymousClass1cK A05;
    public File A06;
    public final AnonymousClass1fL A07;
    public final AnonymousClass1ez<?> A08;
    public volatile C07091bb<?> A09;

    @Override // X.AnonymousClass1Ry
    public final void A6x(Object obj) {
        this.A07.A6w(this.A04, obj, this.A09.A01, AnonymousClass1fM.RESOURCE_DISK_CACHE, this.A05);
    }

    @Override // X.AnonymousClass1Ry
    public final void A7F(@NonNull Exception exc) {
        this.A07.A6v(this.A05, exc, this.A09.A01, AnonymousClass1fM.RESOURCE_DISK_CACHE);
    }

    @Override // X.AbstractC08981fq
    public final boolean AAU() {
        List<Class<?>> list;
        ArrayList<Class> arrayList;
        AnonymousClass1ez<?> r2 = this.A08;
        List<AbstractC06491aL> A022 = r2.A02();
        if (!A022.isEmpty()) {
            C07641cm r7 = r2.A02.A02;
            Class<?> cls = r2.A0A.getClass();
            Class<?> cls2 = r2.A08;
            Class cls3 = (Class<Transcode>) r2.A09;
            AnonymousClass1cX r11 = r7.A07;
            AtomicReference<C08771fG> atomicReference = r11.A01;
            C08771fG andSet = atomicReference.getAndSet(null);
            if (andSet == null) {
                andSet = new C08771fG(cls, cls2, cls3);
            } else {
                andSet.A00 = cls;
                andSet.A01 = cls2;
                andSet.A02 = cls3;
            }
            C05700wg<C08771fG, List<Class<?>>> r6 = r11.A00;
            synchronized (r6) {
                list = r6.get(andSet);
            }
            atomicReference.set(andSet);
            ArrayList arrayList2 = list;
            if (list == null) {
                ArrayList arrayList3 = new ArrayList();
                C07741cw r12 = r7.A02;
                synchronized (r12) {
                    C07381c7 r13 = r12.A01;
                    synchronized (r13) {
                        arrayList = new ArrayList();
                        for (C07391c8<?, ?> r15 : r13.A02) {
                            Class<Data> cls4 = r15.A01;
                            if (!arrayList.contains(cls4) && r15.A02.isAssignableFrom(cls)) {
                                arrayList.add(cls4);
                            }
                        }
                    }
                }
                for (Class cls5 : arrayList) {
                    for (Class cls6 : r7.A08.A00(cls5, cls2)) {
                        if (!r7.A03.A00(cls6, cls3).isEmpty() && !arrayList3.contains(cls6)) {
                            arrayList3.add(cls6);
                        }
                    }
                }
                List<Class<?>> unmodifiableList = Collections.unmodifiableList(arrayList3);
                C05700wg<C08771fG, List<Class<?>>> r62 = r11.A00;
                synchronized (r62) {
                    r62.put(new C08771fG(cls, cls2, cls3), unmodifiableList);
                }
                arrayList2 = arrayList3;
            }
            if (!arrayList2.isEmpty()) {
                while (true) {
                    List<AbstractC07011bT<File, ?>> list2 = this.A01;
                    if (list2 == null || this.A00 >= list2.size()) {
                        int i = this.A02 + 1;
                        this.A02 = i;
                        if (i >= arrayList2.size()) {
                            int i2 = this.A03 + 1;
                            this.A03 = i2;
                            if (i2 >= A022.size()) {
                                break;
                            }
                            this.A02 = 0;
                        }
                        AbstractC06491aL r8 = A022.get(this.A03);
                        Class cls7 = arrayList2.get(this.A02);
                        this.A05 = new AnonymousClass1cK(r2.A02.A04, r8, r2.A04, r2.A01, r2.A00, r2.A00(cls7), cls7, r2.A05);
                        File A3L = r2.A07.A00().A3L(this.A05);
                        this.A06 = A3L;
                        if (A3L != null) {
                            this.A04 = r8;
                            this.A01 = r2.A02.A02.A01(A3L);
                            this.A00 = 0;
                        }
                    } else {
                        this.A09 = null;
                        while (this.A00 < this.A01.size()) {
                            List<AbstractC07011bT<File, ?>> list3 = this.A01;
                            int i3 = this.A00;
                            this.A00 = i3 + 1;
                            this.A09 = list3.get(i3).A1r(this.A06, r2.A01, r2.A00, r2.A05);
                            if (!(this.A09 == null || r2.A01(this.A09.A01.A3h()) == null)) {
                                this.A09.A01.A6H(r2.A03, this);
                                return true;
                            }
                        }
                    }
                }
            } else if (!File.class.equals(r2.A09)) {
                StringBuilder sb = new StringBuilder("Failed to find any load path from ");
                sb.append(r2.A0A.getClass());
                sb.append(" to ");
                sb.append(r2.A09);
                throw new IllegalStateException(sb.toString());
            }
        }
        return false;
    }

    @Override // X.AbstractC08981fq
    public final void cancel() {
        C07091bb<?> r0 = this.A09;
        if (r0 != null) {
            r0.A01.cancel();
        }
    }

    public AnonymousClass1f0(AnonymousClass1ez<?> r2, AnonymousClass1fL r3) {
        this.A08 = r2;
        this.A07 = r3;
    }
}
