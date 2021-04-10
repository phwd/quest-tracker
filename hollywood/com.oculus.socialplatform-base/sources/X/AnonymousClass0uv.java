package X;

import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

/* renamed from: X.0uv  reason: invalid class name */
public final class AnonymousClass0uv extends AnonymousClass0AQ {
    public C05730wl<AnonymousClass0AR, AnonymousClass0AU> A00 = new C05730wl<>();
    public AnonymousClass0AP A01;
    public ArrayList<AnonymousClass0AP> A02 = new ArrayList<>();
    public boolean A03 = false;
    public int A04 = 0;
    public boolean A05 = false;
    public final WeakReference<AnonymousClass0AS> A06;

    private AnonymousClass0AP A02(AnonymousClass0AR r5) {
        AnonymousClass02Z<K, V> r0;
        AnonymousClass0AP r3;
        C05730wl<AnonymousClass0AR, AnonymousClass0AU> r1 = this.A00;
        if (r1.A00.containsKey(r5)) {
            r0 = r1.A00.get(r5).A01;
        } else {
            r0 = null;
        }
        AnonymousClass0AP r2 = null;
        if (r0 != null) {
            r3 = r0.getValue().A01;
        } else {
            r3 = null;
        }
        ArrayList<AnonymousClass0AP> arrayList = this.A02;
        if (!arrayList.isEmpty()) {
            r2 = arrayList.get(arrayList.size() - 1);
        }
        AnonymousClass0AP r12 = this.A01;
        if (r3 != null && r3.compareTo((Enum) r12) < 0) {
            r12 = r3;
        }
        if (r2 == null || r2.compareTo((Enum) r12) >= 0) {
            return r12;
        }
        return r2;
    }

    private void A03() {
        AnonymousClass0AO r2;
        AnonymousClass0AP r1;
        AnonymousClass0AS r3 = this.A06.get();
        if (r3 == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (true) {
            C05730wl<AnonymousClass0AR, AnonymousClass0AU> r12 = this.A00;
            boolean z = true;
            if (!(((AnonymousClass02b) r12).A00 == 0 || (r12.A02.getValue().A01 == (r1 = this.A00.A01.getValue().A01) && this.A01 == r1))) {
                z = false;
            }
            this.A03 = false;
            if (!z) {
                if (this.A01.compareTo((Enum) this.A00.A02.getValue().A01) < 0) {
                    C05730wl<AnonymousClass0AR, AnonymousClass0AU> r22 = this.A00;
                    AnonymousClass0WH r6 = new AnonymousClass0WH(r22.A01, r22.A02);
                    r22.A03.put(r6, false);
                    while (r6.hasNext() && !this.A03) {
                        Map.Entry entry = (Map.Entry) r6.next();
                        AnonymousClass0AU r4 = (AnonymousClass0AU) entry.getValue();
                        while (r4.A01.compareTo((Enum) this.A01) > 0 && !this.A03) {
                            if (this.A00.A00.containsKey(entry.getKey())) {
                                AnonymousClass0AP r23 = r4.A01;
                                switch (r23.ordinal()) {
                                    case 0:
                                        throw new IllegalArgumentException();
                                    case 1:
                                        throw new IllegalArgumentException();
                                    case 2:
                                        r2 = AnonymousClass0AO.ON_DESTROY;
                                        break;
                                    case 3:
                                        r2 = AnonymousClass0AO.ON_STOP;
                                        break;
                                    case 4:
                                        r2 = AnonymousClass0AO.ON_PAUSE;
                                        break;
                                    default:
                                        StringBuilder sb = new StringBuilder("Unexpected state value ");
                                        sb.append(r23);
                                        throw new IllegalArgumentException(sb.toString());
                                }
                                this.A02.add(A01(r2));
                                r4.A00(r3, r2);
                                ArrayList<AnonymousClass0AP> arrayList = this.A02;
                                arrayList.remove(arrayList.size() - 1);
                            } else {
                                continue;
                            }
                        }
                    }
                }
                AnonymousClass02Z<K, V> r24 = this.A00.A01;
                if (!this.A03 && r24 != null && this.A01.compareTo((Enum) r24.getValue().A01) > 0) {
                    C05730wl<AnonymousClass0AR, AnonymousClass0AU> r0 = this.A00;
                    AnonymousClass0wk r5 = new AnonymousClass0wk(r0);
                    r0.A03.put(r5, false);
                    while (r5.hasNext() && !this.A03) {
                        Map.Entry entry2 = (Map.Entry) r5.next();
                        AnonymousClass0AU r25 = (AnonymousClass0AU) entry2.getValue();
                        while (r25.A01.compareTo((Enum) this.A01) < 0 && !this.A03) {
                            if (this.A00.A00.containsKey(entry2.getKey())) {
                                AnonymousClass0AP r13 = r25.A01;
                                this.A02.add(r13);
                                r25.A00(r3, A00(r13));
                                ArrayList<AnonymousClass0AP> arrayList2 = this.A02;
                                arrayList2.remove(arrayList2.size() - 1);
                            }
                        }
                    }
                }
            } else {
                return;
            }
        }
    }

    public static void A04(AnonymousClass0uv r2, AnonymousClass0AP r3) {
        if (r2.A01 != r3) {
            r2.A01 = r3;
            if (r2.A05 || r2.A04 != 0) {
                r2.A03 = true;
                return;
            }
            r2.A05 = true;
            r2.A03();
            r2.A05 = false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0027, code lost:
        if (r6.A05 != false) goto L_0x0029;
     */
    @Override // X.AnonymousClass0AQ
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A06(@androidx.annotation.NonNull X.AnonymousClass0AR r7) {
        /*
        // Method dump skipped, instructions count: 110
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0uv.A06(X.0AR):void");
    }

    @Override // X.AnonymousClass0AQ
    public final void A07(@NonNull AnonymousClass0AR r2) {
        this.A00.A01(r2);
    }

    public AnonymousClass0uv(@NonNull AnonymousClass0AS r2) {
        this.A06 = new WeakReference<>(r2);
        this.A01 = AnonymousClass0AP.INITIALIZED;
    }

    public static AnonymousClass0AO A00(AnonymousClass0AP r2) {
        switch (r2.ordinal()) {
            case 0:
            case 1:
                return AnonymousClass0AO.ON_CREATE;
            case 2:
                return AnonymousClass0AO.ON_START;
            case 3:
                return AnonymousClass0AO.ON_RESUME;
            case 4:
                throw new IllegalArgumentException();
            default:
                StringBuilder sb = new StringBuilder("Unexpected state value ");
                sb.append(r2);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public static AnonymousClass0AP A01(AnonymousClass0AO r2) {
        switch (r2.ordinal()) {
            case 0:
            case 4:
                return AnonymousClass0AP.CREATED;
            case 1:
            case 3:
                return AnonymousClass0AP.STARTED;
            case 2:
                return AnonymousClass0AP.RESUMED;
            case 5:
                return AnonymousClass0AP.DESTROYED;
            default:
                StringBuilder sb = new StringBuilder("Unexpected event value ");
                sb.append(r2);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    @Override // X.AnonymousClass0AQ
    @NonNull
    public final AnonymousClass0AP A05() {
        return this.A01;
    }

    public final void A08(@NonNull AnonymousClass0AO r2) {
        A04(this, A01(r2));
    }
}
