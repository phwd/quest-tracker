package X;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: X.1ez  reason: invalid class name */
public final class AnonymousClass1ez<Transcode> {
    public int A00;
    public int A01;
    public C08731fB A02;
    public AnonymousClass1cY A03;
    public AbstractC06491aL A04;
    public AnonymousClass1cO A05;
    public AbstractC08841fc A06;
    public C08051dt A07;
    public Class<?> A08;
    public Class<Transcode> A09;
    public Object A0A;
    public Map<Class<?>, AnonymousClass1eU<?>> A0B;
    public boolean A0C;
    public boolean A0D;
    public boolean A0E;
    public boolean A0F;
    public final List<AbstractC06491aL> A0G = new ArrayList();
    public final List<C07091bb<?>> A0H = new ArrayList();

    public final <Z> AnonymousClass1eU<Z> A00(Class<Z> cls) {
        AnonymousClass1eU<Z> r0 = (AnonymousClass1eU<Z>) this.A0B.get(cls);
        if (r0 != null) {
            return r0;
        }
        Iterator<Map.Entry<Class<?>, AnonymousClass1eU<?>>> it = this.A0B.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<Class<?>, AnonymousClass1eU<?>> next = it.next();
            if (next.getKey().isAssignableFrom(cls)) {
                AnonymousClass1eU<Z> r02 = (AnonymousClass1eU<Z>) next.getValue();
                if (r02 != null) {
                    return r02;
                }
            }
        }
        if (!this.A0B.isEmpty() || !this.A0F) {
            return (AnonymousClass1eU<Z>) C08621ew.A00;
        }
        StringBuilder sb = new StringBuilder("Missing transformation for ");
        sb.append(cls);
        sb.append(". If you wish to ignore unknown resource types, use the optional transformation methods.");
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r27v0, resolved type: java.lang.Class<Data> */
    /* JADX WARN: Multi-variable type inference failed */
    public final <Data> C07471cG<Data, ?, Transcode> A01(Class<Data> cls) {
        C07471cG<Data, ?, Transcode> r12;
        ArrayList arrayList;
        AbstractC08801fP r14;
        C07641cm r11 = this.A02.A02;
        Class<?> cls2 = this.A08;
        Class cls3 = (Class<Transcode>) this.A09;
        AnonymousClass1dM r9 = r11.A06;
        C08771fG andSet = r9.A01.getAndSet(null);
        if (andSet == null) {
            andSet = new C08771fG();
        }
        andSet.A00 = cls;
        andSet.A01 = cls2;
        andSet.A02 = cls3;
        C05700wg<C08771fG, C07471cG<?, ?, ?>> r1 = r9.A00;
        synchronized (r1) {
            r12 = (C07471cG<Data, ?, Transcode>) r1.get(andSet);
        }
        r9.A01.set(andSet);
        C07471cG<?, ?, ?> r4 = AnonymousClass1dM.A02;
        if (r4.equals(r12)) {
            return null;
        }
        if (r12 != null) {
            return r12;
        }
        ArrayList arrayList2 = new ArrayList();
        C08721fA r122 = r11.A08;
        for (Class<?> cls4 : r122.A00(cls, cls2)) {
            C08641ey r3 = r11.A03;
            Iterator it = r3.A00(cls4, cls3).iterator();
            while (true) {
                if (it.hasNext()) {
                    Class cls5 = (Class) it.next();
                    synchronized (r122) {
                        arrayList = new ArrayList();
                        for (String str : r122.A00) {
                            List<C08791fJ<?, ?>> list = r122.A01.get(str);
                            if (list != null) {
                                for (C08791fJ<?, ?> r13 : list) {
                                    if (r13.A01.isAssignableFrom(cls) && cls4.isAssignableFrom(r13.A02)) {
                                        arrayList.add(r13.A00);
                                    }
                                }
                            }
                        }
                    }
                    synchronized (r3) {
                        if (cls5.isAssignableFrom(cls4)) {
                            r14 = C07801dQ.A00;
                        } else {
                            for (C08631ex<?, ?> r15 : r3.A00) {
                                if (r15.A01.isAssignableFrom(cls4) && cls5.isAssignableFrom(r15.A02)) {
                                    r14 = r15.A00;
                                }
                            }
                            StringBuilder sb = new StringBuilder();
                            sb.append("No transcoder registered to transcode from ");
                            sb.append(cls4);
                            sb.append(" to ");
                            sb.append(cls5);
                            throw new IllegalArgumentException(sb.toString());
                        }
                    }
                    arrayList2.add(new AnonymousClass1dL(cls, cls4, cls5, arrayList, r14, r11.A00));
                }
            }
        }
        C07471cG<Data, ?, Transcode> r123 = arrayList2.isEmpty() ? null : new C07471cG<>(cls, cls2, cls3, arrayList2, r11.A00);
        C07471cG<?, ?, ?> r2 = r123;
        C05700wg<C08771fG, C07471cG<?, ?, ?>> r16 = r9.A00;
        synchronized (r16) {
            C08771fG r0 = new C08771fG(cls, cls2, cls3);
            if (r123 == null) {
                r2 = r4;
            }
            r16.put(r0, r2);
        }
        return r123;
    }

    public final List<AbstractC06491aL> A02() {
        if (!this.A0C) {
            this.A0C = true;
            List<AbstractC06491aL> list = this.A0G;
            list.clear();
            List<C07091bb<?>> A032 = A03();
            int size = A032.size();
            for (int i = 0; i < size; i++) {
                C07091bb<?> r2 = A032.get(i);
                if (!list.contains(r2.A00)) {
                    list.add(r2.A00);
                }
                for (int i2 = 0; i2 < r2.A02.size(); i2++) {
                    if (!list.contains(r2.A02.get(i2))) {
                        list.add(r2.A02.get(i2));
                    }
                }
            }
        }
        return this.A0G;
    }

    public final List<C07091bb<?>> A03() {
        if (!this.A0D) {
            this.A0D = true;
            List<C07091bb<?>> list = this.A0H;
            list.clear();
            List A012 = this.A02.A02.A01(this.A0A);
            int size = A012.size();
            for (int i = 0; i < size; i++) {
                C07091bb<?> A1r = ((AbstractC07011bT) A012.get(i)).A1r(this.A0A, this.A01, this.A00, this.A05);
                if (A1r != null) {
                    list.add(A1r);
                }
            }
        }
        return this.A0H;
    }
}
