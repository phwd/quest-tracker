package X;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.SSLException;

/* renamed from: X.1vJ  reason: invalid class name */
public final class AnonymousClass1vJ {
    public final C10831vi<AnonymousClass1vF> A00;

    public final synchronized void A00(AnonymousClass1w6 r7) throws AnonymousClass1v5 {
        AbstractC10881vn<E> r1;
        AbstractC10881vn<E> r12;
        try {
            C10831vi<AnonymousClass1vF> r5 = this.A00;
            C10861vl<E> r13 = r5.A02;
            C10841vj<E> r2 = r5.A00;
            E e = r5.A01;
            if (r13.A02) {
                Iterator<C10821vh<E>> it = r13.A01.get(r2).A00.iterator();
                while (it.hasNext()) {
                    C10821vh<E> next = it.next();
                    if (next.A03.isInstance(r7)) {
                        C10841vj<E> r22 = next.A01;
                        C10841vj<E> r0 = r5.A00;
                        if (!(r22 == r0 || (r12 = r0.A01) == null)) {
                            r12.A00(r7, e, next, 1);
                        }
                        AbstractC10881vn<E> r14 = next.A00;
                        if (r14 != null) {
                            r14.A00(r7, e, next, 2);
                        }
                        if (!(r22 == r5.A00 || (r1 = r22.A00) == null)) {
                            r1.A00(r7, e, next, 3);
                        }
                        r5.A00 = r22;
                    }
                }
                throw new AnonymousClass1lG(AnonymousClass006.A05("No valid transition from state: ", r2.A03));
            }
            throw new AnonymousClass1lG("State machine map is not initialized - call build()");
        } catch (AnonymousClass1lG e2) {
            e = e2;
            if (!(e.getCause() instanceof AnonymousClass1v5)) {
                if (e.getCause() != null) {
                    e = e.getCause();
                }
                throw new AnonymousClass1v5((byte) 80, new SSLException("Internal Error", e));
            }
            throw ((AnonymousClass1v5) e.getCause());
        }
    }

    public AnonymousClass1vJ(AnonymousClass1vF r10) throws AnonymousClass1v5 {
        C10861vl r3 = new C10861vl();
        try {
            C10841vj<E>[] r8 = AnonymousClass1vY.A0C;
            for (C10841vj<E> r4 : r8) {
                if (r3.A01.get(r4) == null) {
                    boolean z = false;
                    if (r4.A02 == AnonymousClass007.A00) {
                        z = true;
                        if (r3.A00 != null) {
                            throw new AnonymousClass1lG(AnonymousClass006.A05("Start state already exists, new state invalid: ", r4.A03));
                        }
                    }
                    r3.A01.put(r4, new C10891vo<>());
                    if (z) {
                        r3.A00 = r4;
                    }
                } else {
                    throw new AnonymousClass1lG(AnonymousClass006.A05("State already added: ", r4.A03));
                }
            }
            C10821vh[] r6 = AnonymousClass1vU.A00;
            for (C10821vh r42 : r6) {
                C10891vo<E> r2 = r3.A01.get(r42.A02);
                if (r2 == null) {
                    throw new AnonymousClass1lG(AnonymousClass006.A05("Cannot find input state for transition ", r42.A04));
                } else if (r3.A01.get(r42.A01) != null) {
                    r2.A00.add(r42);
                } else {
                    throw new AnonymousClass1lG(AnonymousClass006.A05("Cannot find output state for transition ", r42.A04));
                }
            }
            if (r3.A00 != null) {
                for (C10841vj<E> r0 : r3.A01.keySet()) {
                    Integer num = r0.A02;
                    Integer num2 = AnonymousClass007.A0C;
                    if (num == num2) {
                        HashSet hashSet = new HashSet();
                        for (Map.Entry<C10841vj<E>, C10891vo<E>> entry : r3.A01.entrySet()) {
                            if (entry.getValue().A00.size() != 0 || entry.getKey().A02 == num2) {
                                HashSet hashSet2 = new HashSet();
                                Iterator<C10821vh<E>> it = entry.getValue().A00.iterator();
                                while (it.hasNext()) {
                                    hashSet2.add(it.next().A01);
                                }
                                hashSet.addAll(hashSet2);
                            } else {
                                throw new AnonymousClass1lG(AnonymousClass006.A05("Non-end state with no outbound transitions: ", entry.getKey().A03));
                            }
                        }
                        if (r3.A01.size() - hashSet.size() > 1) {
                            throw new AnonymousClass1lG("Non-start state(s) with no incoming transitions exist(s)");
                        } else if (r3.A01.size() - hashSet.size() != 1 || !hashSet.contains(r3.A00)) {
                            r3.A02 = true;
                            this.A00 = new C10831vi<>(r3, r10);
                            return;
                        } else {
                            throw new AnonymousClass1lG("Non-start state(s) with no incoming transitions exist(s)");
                        }
                    }
                }
                throw new AnonymousClass1lG("State machine must have an end state");
            }
            throw new AnonymousClass1lG("State machine must have a start state");
        } catch (AnonymousClass1lG e) {
            e = e;
            throw new AnonymousClass1v5((byte) 80, new SSLException("Failed to init finite state machine.", e.getCause() != null ? e.getCause() : e));
        }
    }
}
