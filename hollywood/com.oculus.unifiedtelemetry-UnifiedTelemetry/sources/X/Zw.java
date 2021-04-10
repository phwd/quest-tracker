package X;

import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

public final class Zw extends AP {
    public C0302aa<AQ, AT> A00 = new C0302aa<>();
    public AO A01;
    public ArrayList<AO> A02 = new ArrayList<>();
    public boolean A03 = false;
    public int A04 = 0;
    public boolean A05 = false;
    public final WeakReference<AR> A06;

    private AO A02(AQ aq) {
        AnonymousClass2S<K, V> r0;
        AO ao;
        C0302aa<AQ, AT> aaVar = this.A00;
        if (aaVar.A00.containsKey(aq)) {
            r0 = aaVar.A00.get(aq).A01;
        } else {
            r0 = null;
        }
        AO ao2 = null;
        if (r0 != null) {
            ao = r0.getValue().A01;
        } else {
            ao = null;
        }
        ArrayList<AO> arrayList = this.A02;
        if (!arrayList.isEmpty()) {
            ao2 = arrayList.get(arrayList.size() - 1);
        }
        AO ao3 = this.A01;
        if (ao != null && ao.compareTo((Enum) ao3) < 0) {
            ao3 = ao;
        }
        if (ao2 == null || ao2.compareTo((Enum) ao3) >= 0) {
            return ao3;
        }
        return ao2;
    }

    private void A03() {
        AN an;
        AO ao;
        AR ar = this.A06.get();
        if (ar == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (true) {
            C0302aa<AQ, AT> aaVar = this.A00;
            boolean z = true;
            if (!(((AnonymousClass2U) aaVar).A00 == 0 || (aaVar.A02.getValue().A01 == (ao = this.A00.A01.getValue().A01) && this.A01 == ao))) {
                z = false;
            }
            this.A03 = false;
            if (!z) {
                if (this.A01.compareTo((Enum) this.A00.A02.getValue().A01) < 0) {
                    C0302aa<AQ, AT> aaVar2 = this.A00;
                    J1 j1 = new J1(aaVar2.A01, aaVar2.A02);
                    aaVar2.A03.put(j1, false);
                    while (j1.hasNext() && !this.A03) {
                        Map.Entry entry = (Map.Entry) j1.next();
                        AT at = (AT) entry.getValue();
                        while (at.A01.compareTo((Enum) this.A01) > 0 && !this.A03) {
                            if (this.A00.A00.containsKey(entry.getKey())) {
                                AO ao2 = at.A01;
                                switch (ao2.ordinal()) {
                                    case 0:
                                    case 1:
                                        throw new IllegalArgumentException();
                                    case 2:
                                        an = AN.ON_DESTROY;
                                        break;
                                    case 3:
                                        an = AN.ON_STOP;
                                        break;
                                    case 4:
                                        an = AN.ON_PAUSE;
                                        break;
                                    default:
                                        StringBuilder sb = new StringBuilder("Unexpected state value ");
                                        sb.append(ao2);
                                        throw new IllegalArgumentException(sb.toString());
                                }
                                this.A02.add(A01(an));
                                at.A00(ar, an);
                                ArrayList<AO> arrayList = this.A02;
                                arrayList.remove(arrayList.size() - 1);
                            } else {
                                continue;
                            }
                        }
                    }
                }
                AnonymousClass2S<K, V> r2 = this.A00.A01;
                if (!this.A03 && r2 != null && this.A01.compareTo((Enum) r2.getValue().A01) > 0) {
                    C0302aa<AQ, AT> aaVar3 = this.A00;
                    C0301aZ aZVar = new C0301aZ(aaVar3);
                    aaVar3.A03.put(aZVar, false);
                    while (aZVar.hasNext() && !this.A03) {
                        Map.Entry entry2 = (Map.Entry) aZVar.next();
                        AT at2 = (AT) entry2.getValue();
                        while (at2.A01.compareTo((Enum) this.A01) < 0 && !this.A03) {
                            if (this.A00.A00.containsKey(entry2.getKey())) {
                                AO ao3 = at2.A01;
                                this.A02.add(ao3);
                                at2.A00(ar, A00(ao3));
                                ArrayList<AO> arrayList2 = this.A02;
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

    public static void A04(Zw zw, AO ao) {
        if (zw.A01 != ao) {
            zw.A01 = ao;
            if (zw.A05 || zw.A04 != 0) {
                zw.A03 = true;
                return;
            }
            zw.A05 = true;
            zw.A03();
            zw.A05 = false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0027, code lost:
        if (r6.A05 != false) goto L_0x0029;
     */
    @Override // X.AP
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A06(@androidx.annotation.NonNull X.AQ r7) {
        /*
        // Method dump skipped, instructions count: 110
        */
        throw new UnsupportedOperationException("Method not decompiled: X.Zw.A06(X.AQ):void");
    }

    @Override // X.AP
    public final void A07(@NonNull AQ aq) {
        this.A00.A01(aq);
    }

    public Zw(@NonNull AR ar) {
        this.A06 = new WeakReference<>(ar);
        this.A01 = AO.INITIALIZED;
    }

    public static AN A00(AO ao) {
        switch (ao.ordinal()) {
            case 0:
            case 1:
                return AN.ON_CREATE;
            case 2:
                return AN.ON_START;
            case 3:
                return AN.ON_RESUME;
            case 4:
                throw new IllegalArgumentException();
            default:
                StringBuilder sb = new StringBuilder("Unexpected state value ");
                sb.append(ao);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public static AO A01(AN an) {
        switch (an.ordinal()) {
            case 0:
            case 4:
                return AO.CREATED;
            case 1:
            case 3:
                return AO.STARTED;
            case 2:
                return AO.RESUMED;
            case 5:
                return AO.DESTROYED;
            default:
                StringBuilder sb = new StringBuilder("Unexpected event value ");
                sb.append(an);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public final void A08(@NonNull AN an) {
        A04(this, A01(an));
    }

    @Override // X.AP
    @NonNull
    public final AO A05() {
        return this.A01;
    }
}
