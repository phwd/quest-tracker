package X;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.SSLException;

/* renamed from: X.24p  reason: invalid class name and case insensitive filesystem */
public final class C147224p {
    public final AnonymousClass252<AnonymousClass24X> A00;

    public final synchronized void A00(AnonymousClass25V r7) throws AnonymousClass25A {
        AnonymousClass258<E> r1;
        AnonymousClass258<E> r12;
        try {
            AnonymousClass252<AnonymousClass24X> r5 = this.A00;
            AnonymousClass253<E> r13 = r5.A02;
            AnonymousClass254<E> r2 = r5.A00;
            E e = r5.A01;
            if (r13.A02) {
                Iterator<AnonymousClass250<E>> it = r13.A01.get(r2).A00.iterator();
                while (it.hasNext()) {
                    AnonymousClass250<E> next = it.next();
                    if (next.A03.isInstance(r7)) {
                        AnonymousClass254<E> r22 = next.A01;
                        AnonymousClass254<E> r0 = r5.A00;
                        if (!(r22 == r0 || (r12 = r0.A01) == null)) {
                            r12.A00(r7, e, next, 1);
                        }
                        AnonymousClass258<E> r14 = next.A00;
                        if (r14 != null) {
                            r14.A00(r7, e, next, 2);
                        }
                        if (!(r22 == r5.A00 || (r1 = r22.A00) == null)) {
                            r1.A00(r7, e, next, 3);
                        }
                        r5.A00 = r22;
                    }
                }
                throw new AnonymousClass25D(AnonymousClass006.A07("No valid transition from state: ", r2.A03));
            }
            throw new AnonymousClass25D("State machine map is not initialized - call build()");
        } catch (AnonymousClass25D e2) {
            e = e2;
            if (!(e.getCause() instanceof AnonymousClass25A)) {
                if (e.getCause() != null) {
                    e = e.getCause();
                }
                throw new AnonymousClass25A((byte) 80, new SSLException("Internal Error", e));
            }
            throw ((AnonymousClass25A) e.getCause());
        }
    }

    public C147224p(AnonymousClass24X r10) throws AnonymousClass25A {
        AnonymousClass253 r3 = new AnonymousClass253();
        try {
            AnonymousClass254<E>[] r8 = C147424r.A0C;
            for (AnonymousClass254<E> r4 : r8) {
                if (r3.A01.get(r4) == null) {
                    boolean z = false;
                    if (r4.A02 == AnonymousClass007.A00) {
                        z = true;
                        if (r3.A00 != null) {
                            throw new AnonymousClass25D(AnonymousClass006.A07("Start state already exists, new state invalid: ", r4.A03));
                        }
                    }
                    r3.A01.put(r4, new AnonymousClass25B<>());
                    if (z) {
                        r3.A00 = r4;
                    }
                } else {
                    throw new AnonymousClass25D(AnonymousClass006.A07("State already added: ", r4.A03));
                }
            }
            AnonymousClass250[] r6 = C147124o.A00;
            for (AnonymousClass250 r42 : r6) {
                AnonymousClass25B<E> r2 = r3.A01.get(r42.A02);
                if (r2 == null) {
                    throw new AnonymousClass25D(AnonymousClass006.A07("Cannot find input state for transition ", r42.A04));
                } else if (r3.A01.get(r42.A01) != null) {
                    r2.A00.add(r42);
                } else {
                    throw new AnonymousClass25D(AnonymousClass006.A07("Cannot find output state for transition ", r42.A04));
                }
            }
            if (r3.A00 != null) {
                for (AnonymousClass254<E> r0 : r3.A01.keySet()) {
                    Integer num = r0.A02;
                    Integer num2 = AnonymousClass007.A03;
                    if (num == num2) {
                        HashSet hashSet = new HashSet();
                        for (Map.Entry<AnonymousClass254<E>, AnonymousClass25B<E>> entry : r3.A01.entrySet()) {
                            if (entry.getValue().A00.size() != 0 || entry.getKey().A02 == num2) {
                                HashSet hashSet2 = new HashSet();
                                Iterator<AnonymousClass250<E>> it = entry.getValue().A00.iterator();
                                while (it.hasNext()) {
                                    hashSet2.add(it.next().A01);
                                }
                                hashSet.addAll(hashSet2);
                            } else {
                                throw new AnonymousClass25D(AnonymousClass006.A07("Non-end state with no outbound transitions: ", entry.getKey().A03));
                            }
                        }
                        if (r3.A01.size() - hashSet.size() > 1) {
                            throw new AnonymousClass25D("Non-start state(s) with no incoming transitions exist(s)");
                        } else if (r3.A01.size() - hashSet.size() != 1 || !hashSet.contains(r3.A00)) {
                            r3.A02 = true;
                            this.A00 = new AnonymousClass252<>(r3, r10);
                            return;
                        } else {
                            throw new AnonymousClass25D("Non-start state(s) with no incoming transitions exist(s)");
                        }
                    }
                }
                throw new AnonymousClass25D("State machine must have an end state");
            }
            throw new AnonymousClass25D("State machine must have a start state");
        } catch (AnonymousClass25D e) {
            e = e;
            throw new AnonymousClass25A((byte) 80, new SSLException("Failed to init finite state machine.", e.getCause() != null ? e.getCause() : e));
        }
    }
}
