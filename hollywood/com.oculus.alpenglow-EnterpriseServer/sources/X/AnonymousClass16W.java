package X;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.SSLException;

/* renamed from: X.16W  reason: invalid class name */
public final class AnonymousClass16W {
    public final AnonymousClass175<AnonymousClass16P> A00;

    public final synchronized void A00(AnonymousClass10c r7) throws AnonymousClass11f {
        AnonymousClass17A<E> r1;
        AnonymousClass17A<E> r12;
        try {
            AnonymousClass175<AnonymousClass16P> r5 = this.A00;
            AnonymousClass177<E> r13 = r5.A02;
            AnonymousClass172<E> r2 = r5.A00;
            E e = r5.A01;
            if (r13.A02) {
                Iterator<AnonymousClass170<E>> it = r13.A01.get(r2).A00.iterator();
                while (it.hasNext()) {
                    AnonymousClass170<E> next = it.next();
                    if (next.A03.isInstance(r7)) {
                        AnonymousClass172<E> r22 = next.A01;
                        AnonymousClass172<E> r0 = r5.A00;
                        if (!(r22 == r0 || (r12 = r0.A01) == null)) {
                            r12.A00(r7, e, next, 1);
                        }
                        AnonymousClass17A<E> r14 = next.A00;
                        if (r14 != null) {
                            r14.A00(r7, e, next, 2);
                        }
                        if (!(r22 == r5.A00 || (r1 = r22.A00) == null)) {
                            r1.A00(r7, e, next, 3);
                        }
                        r5.A00 = r22;
                    }
                }
                throw new AnonymousClass10Z(AnonymousClass006.A05("No valid transition from state: ", r2.A03));
            }
            throw new AnonymousClass10Z("State machine map is not initialized - call build()");
        } catch (AnonymousClass10Z e2) {
            if (!(e2.getCause() instanceof AnonymousClass11f)) {
                Throwable cause = e2.getCause();
                Throwable th = e2;
                if (cause != null) {
                    th = e2.getCause();
                }
                throw new AnonymousClass11f((byte) 80, new SSLException("Internal Error", th));
            }
            throw ((AnonymousClass11f) e2.getCause());
        }
    }

    public AnonymousClass16W(AnonymousClass16P r10) throws AnonymousClass11f {
        AnonymousClass177 r3 = new AnonymousClass177();
        try {
            AnonymousClass172<E>[] r8 = C097616l.A0C;
            for (AnonymousClass172<E> r4 : r8) {
                if (r3.A01.get(r4) == null) {
                    boolean z = false;
                    if (r4.A02 == AnonymousClass007.A00) {
                        z = true;
                        if (r3.A00 != null) {
                            throw new AnonymousClass10Z(AnonymousClass006.A05("Start state already exists, new state invalid: ", r4.A03));
                        }
                    }
                    r3.A01.put(r4, new AnonymousClass17I<>());
                    if (z) {
                        r3.A00 = r4;
                    }
                } else {
                    throw new AnonymousClass10Z(AnonymousClass006.A05("State already added: ", r4.A03));
                }
            }
            AnonymousClass170[] r6 = C097216h.A00;
            for (AnonymousClass170 r42 : r6) {
                AnonymousClass17I<E> r2 = r3.A01.get(r42.A02);
                if (r2 == null) {
                    throw new AnonymousClass10Z(AnonymousClass006.A05("Cannot find input state for transition ", r42.A04));
                } else if (r3.A01.get(r42.A01) != null) {
                    r2.A00.add(r42);
                } else {
                    throw new AnonymousClass10Z(AnonymousClass006.A05("Cannot find output state for transition ", r42.A04));
                }
            }
            if (r3.A00 != null) {
                for (AnonymousClass172<E> r0 : r3.A01.keySet()) {
                    Integer num = r0.A02;
                    Integer num2 = AnonymousClass007.A0C;
                    if (num == num2) {
                        HashSet hashSet = new HashSet();
                        for (Map.Entry<AnonymousClass172<E>, AnonymousClass17I<E>> entry : r3.A01.entrySet()) {
                            if (entry.getValue().A00.size() != 0 || entry.getKey().A02 == num2) {
                                HashSet hashSet2 = new HashSet();
                                Iterator<AnonymousClass170<E>> it = entry.getValue().A00.iterator();
                                while (it.hasNext()) {
                                    hashSet2.add(it.next().A01);
                                }
                                hashSet.addAll(hashSet2);
                            } else {
                                throw new AnonymousClass10Z(AnonymousClass006.A05("Non-end state with no outbound transitions: ", entry.getKey().A03));
                            }
                        }
                        if (r3.A01.size() - hashSet.size() > 1) {
                            throw new AnonymousClass10Z("Non-start state(s) with no incoming transitions exist(s)");
                        } else if (r3.A01.size() - hashSet.size() != 1 || !hashSet.contains(r3.A00)) {
                            r3.A02 = true;
                            this.A00 = new AnonymousClass175<>(r3, r10);
                            return;
                        } else {
                            throw new AnonymousClass10Z("Non-start state(s) with no incoming transitions exist(s)");
                        }
                    }
                }
                throw new AnonymousClass10Z("State machine must have an end state");
            }
            throw new AnonymousClass10Z("State machine must have a start state");
        } catch (AnonymousClass10Z e) {
            throw new AnonymousClass11f((byte) 80, new SSLException("Failed to init finite state machine.", e.getCause() != null ? e.getCause() : e));
        }
    }
}
