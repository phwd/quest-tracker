package X;

import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

/* renamed from: X.0cc  reason: invalid class name and case insensitive filesystem */
public final class C03540cc extends AnonymousClass0DY {
    public C04000dn<AnonymousClass0DZ, C01050Dc> A00 = new C04000dn<>();
    public AnonymousClass0DX A01;
    public ArrayList<AnonymousClass0DX> A02 = new ArrayList<>();
    public boolean A03 = false;
    public int A04 = 0;
    public boolean A05 = false;
    public final WeakReference<AbstractC01030Da> A06;

    private AnonymousClass0DX A02(AnonymousClass0DZ r5) {
        AnonymousClass05r<K, V> r0;
        AnonymousClass0DX r3;
        C04000dn<AnonymousClass0DZ, C01050Dc> r1 = this.A00;
        if (r1.A00.containsKey(r5)) {
            r0 = r1.A00.get(r5).A01;
        } else {
            r0 = null;
        }
        AnonymousClass0DX r2 = null;
        if (r0 != null) {
            r3 = r0.getValue().A01;
        } else {
            r3 = null;
        }
        ArrayList<AnonymousClass0DX> arrayList = this.A02;
        if (!arrayList.isEmpty()) {
            r2 = arrayList.get(arrayList.size() - 1);
        }
        AnonymousClass0DX r12 = this.A01;
        if (r3 != null && r3.compareTo((Enum) r12) < 0) {
            r12 = r3;
        }
        if (r2 == null || r2.compareTo((Enum) r12) >= 0) {
            return r12;
        }
        return r2;
    }

    private void A03() {
        AnonymousClass0DW r2;
        AnonymousClass0DX r1;
        AbstractC01030Da r3 = this.A06.get();
        if (r3 == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (true) {
            C04000dn<AnonymousClass0DZ, C01050Dc> r12 = this.A00;
            boolean z = true;
            if (!(((C005905t) r12).A00 == 0 || (r12.A02.getValue().A01 == (r1 = this.A00.A01.getValue().A01) && this.A01 == r1))) {
                z = false;
            }
            this.A03 = false;
            if (!z) {
                if (this.A01.compareTo((Enum) this.A00.A02.getValue().A01) < 0) {
                    C04000dn<AnonymousClass0DZ, C01050Dc> r22 = this.A00;
                    AnonymousClass0MZ r6 = new AnonymousClass0MZ(r22.A01, r22.A02);
                    r22.A03.put(r6, false);
                    while (r6.hasNext() && !this.A03) {
                        Map.Entry entry = (Map.Entry) r6.next();
                        C01050Dc r4 = (C01050Dc) entry.getValue();
                        while (r4.A01.compareTo((Enum) this.A01) > 0 && !this.A03) {
                            if (this.A00.A00.containsKey(entry.getKey())) {
                                AnonymousClass0DX r23 = r4.A01;
                                switch (r23.ordinal()) {
                                    case 0:
                                    case 1:
                                        throw new IllegalArgumentException();
                                    case 2:
                                        r2 = AnonymousClass0DW.ON_DESTROY;
                                        break;
                                    case 3:
                                        r2 = AnonymousClass0DW.ON_STOP;
                                        break;
                                    case 4:
                                        r2 = AnonymousClass0DW.ON_PAUSE;
                                        break;
                                    default:
                                        throw new IllegalArgumentException("Unexpected state value " + r23);
                                }
                                this.A02.add(A01(r2));
                                r4.A00(r3, r2);
                                ArrayList<AnonymousClass0DX> arrayList = this.A02;
                                arrayList.remove(arrayList.size() - 1);
                            } else {
                                continue;
                            }
                        }
                    }
                }
                AnonymousClass05r<K, V> r24 = this.A00.A01;
                if (!this.A03 && r24 != null && this.A01.compareTo((Enum) r24.getValue().A01) > 0) {
                    C04000dn<AnonymousClass0DZ, C01050Dc> r0 = this.A00;
                    C03990dm r5 = new C03990dm(r0);
                    r0.A03.put(r5, false);
                    while (r5.hasNext() && !this.A03) {
                        Map.Entry entry2 = (Map.Entry) r5.next();
                        C01050Dc r25 = (C01050Dc) entry2.getValue();
                        while (r25.A01.compareTo((Enum) this.A01) < 0 && !this.A03) {
                            if (this.A00.A00.containsKey(entry2.getKey())) {
                                AnonymousClass0DX r13 = r25.A01;
                                this.A02.add(r13);
                                r25.A00(r3, A00(r13));
                                ArrayList<AnonymousClass0DX> arrayList2 = this.A02;
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

    public static void A04(C03540cc r2, AnonymousClass0DX r3) {
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

    @Override // X.AnonymousClass0DY
    @NonNull
    public final AnonymousClass0DX A05() {
        return this.A01;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0027, code lost:
        if (r6.A05 != false) goto L_0x0029;
     */
    @Override // X.AnonymousClass0DY
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A06(@androidx.annotation.NonNull X.AnonymousClass0DZ r7) {
        /*
        // Method dump skipped, instructions count: 110
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C03540cc.A06(X.0DZ):void");
    }

    @Override // X.AnonymousClass0DY
    public final void A07(@NonNull AnonymousClass0DZ r2) {
        this.A00.A01(r2);
    }

    public C03540cc(@NonNull AbstractC01030Da r2) {
        this.A06 = new WeakReference<>(r2);
        this.A01 = AnonymousClass0DX.INITIALIZED;
    }

    public static AnonymousClass0DW A00(AnonymousClass0DX r2) {
        switch (r2.ordinal()) {
            case 0:
            case 1:
                return AnonymousClass0DW.ON_CREATE;
            case 2:
                return AnonymousClass0DW.ON_START;
            case 3:
                return AnonymousClass0DW.ON_RESUME;
            case 4:
                throw new IllegalArgumentException();
            default:
                throw new IllegalArgumentException("Unexpected state value " + r2);
        }
    }

    public static AnonymousClass0DX A01(AnonymousClass0DW r2) {
        switch (r2.ordinal()) {
            case 0:
            case 4:
                return AnonymousClass0DX.CREATED;
            case 1:
            case 3:
                return AnonymousClass0DX.STARTED;
            case 2:
                return AnonymousClass0DX.RESUMED;
            case 5:
                return AnonymousClass0DX.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + r2);
        }
    }

    public final void A08(@NonNull AnonymousClass0DW r2) {
        A04(this, A01(r2));
    }
}
