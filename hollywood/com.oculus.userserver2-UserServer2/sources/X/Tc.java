package X;

import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

public final class Tc extends AbstractC0041Bq {
    public int A00 = 0;
    public UF<AbstractC0042Br, Bu> A01 = new UF<>();
    public EnumC0040Bp A02;
    public ArrayList<EnumC0040Bp> A03 = new ArrayList<>();
    public boolean A04 = false;
    public boolean A05 = false;
    public final WeakReference<Bs> A06;

    public static EnumC0040Bp A02(Tc tc, AbstractC0042Br br) {
        AnonymousClass2f<K, V> r0;
        EnumC0040Bp bp;
        UF<AbstractC0042Br, Bu> uf = tc.A01;
        if (uf.A00.containsKey(br)) {
            r0 = uf.A00.get(br).A01;
        } else {
            r0 = null;
        }
        EnumC0040Bp bp2 = null;
        if (r0 != null) {
            bp = r0.getValue().A01;
        } else {
            bp = null;
        }
        ArrayList<EnumC0040Bp> arrayList = tc.A03;
        if (!arrayList.isEmpty()) {
            bp2 = arrayList.get(arrayList.size() - 1);
        }
        EnumC0040Bp bp3 = tc.A02;
        if (bp != null && bp.compareTo((Enum) bp3) < 0) {
            bp3 = bp;
        }
        if (bp2 == null || bp2.compareTo((Enum) bp3) >= 0) {
            return bp3;
        }
        return bp2;
    }

    public static void A03(Tc tc) {
        EnumC0039Bo bo;
        EnumC0040Bp bp;
        Bs bs = tc.A06.get();
        if (bs == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (true) {
            UF<AbstractC0042Br, Bu> uf = tc.A01;
            boolean z = true;
            if (!(((AnonymousClass2h) uf).A00 == 0 || (uf.A02.getValue().A01 == (bp = tc.A01.A01.getValue().A01) && tc.A02 == bp))) {
                z = false;
            }
            tc.A05 = false;
            if (!z) {
                if (tc.A02.compareTo((Enum) tc.A01.A02.getValue().A01) < 0) {
                    UF<AbstractC0042Br, Bu> uf2 = tc.A01;
                    C0048Cq cq = new C0048Cq(uf2.A01, uf2.A02);
                    uf2.A03.put(cq, false);
                    while (cq.hasNext() && !tc.A05) {
                        Map.Entry entry = (Map.Entry) cq.next();
                        Bu bu = (Bu) entry.getValue();
                        while (bu.A01.compareTo((Enum) tc.A02) > 0 && !tc.A05) {
                            if (tc.A01.A00.containsKey(entry.getKey())) {
                                EnumC0040Bp bp2 = bu.A01;
                                switch (bp2.ordinal()) {
                                    case 0:
                                    case 1:
                                        throw new IllegalArgumentException();
                                    case 2:
                                        bo = EnumC0039Bo.ON_DESTROY;
                                        break;
                                    case 3:
                                        bo = EnumC0039Bo.ON_STOP;
                                        break;
                                    case 4:
                                        bo = EnumC0039Bo.ON_PAUSE;
                                        break;
                                    default:
                                        StringBuilder sb = new StringBuilder("Unexpected state value ");
                                        sb.append(bp2);
                                        throw new IllegalArgumentException(sb.toString());
                                }
                                tc.A03.add(A01(bo));
                                bu.A00(bs, bo);
                                ArrayList<EnumC0040Bp> arrayList = tc.A03;
                                arrayList.remove(arrayList.size() - 1);
                            } else {
                                continue;
                            }
                        }
                    }
                }
                AnonymousClass2f<K, V> r2 = tc.A01.A01;
                if (!tc.A05 && r2 != null && tc.A02.compareTo((Enum) r2.getValue().A01) > 0) {
                    UF<AbstractC0042Br, Bu> uf3 = tc.A01;
                    UE ue = new UE(uf3);
                    uf3.A03.put(ue, false);
                    while (ue.hasNext() && !tc.A05) {
                        Map.Entry entry2 = (Map.Entry) ue.next();
                        Bu bu2 = (Bu) entry2.getValue();
                        while (bu2.A01.compareTo((Enum) tc.A02) < 0 && !tc.A05) {
                            if (tc.A01.A00.containsKey(entry2.getKey())) {
                                EnumC0040Bp bp3 = bu2.A01;
                                tc.A03.add(bp3);
                                bu2.A00(bs, A00(bp3));
                                ArrayList<EnumC0040Bp> arrayList2 = tc.A03;
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

    public static void A04(Tc tc, EnumC0040Bp bp) {
        if (tc.A02 != bp) {
            tc.A02 = bp;
            if (tc.A04 || tc.A00 != 0) {
                tc.A05 = true;
                return;
            }
            tc.A04 = true;
            A03(tc);
            tc.A04 = false;
        }
    }

    public Tc(@NonNull Bs bs) {
        this.A06 = new WeakReference<>(bs);
        this.A02 = EnumC0040Bp.INITIALIZED;
    }

    public static EnumC0039Bo A00(EnumC0040Bp bp) {
        switch (bp.ordinal()) {
            case 0:
            case 1:
                return EnumC0039Bo.ON_CREATE;
            case 2:
                return EnumC0039Bo.ON_START;
            case 3:
                return EnumC0039Bo.ON_RESUME;
            case 4:
                throw new IllegalArgumentException();
            default:
                StringBuilder sb = new StringBuilder("Unexpected state value ");
                sb.append(bp);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public static EnumC0040Bp A01(EnumC0039Bo bo) {
        switch (bo.ordinal()) {
            case 0:
            case 4:
                return EnumC0040Bp.CREATED;
            case 1:
            case 3:
                return EnumC0040Bp.STARTED;
            case 2:
                return EnumC0040Bp.RESUMED;
            case 5:
                return EnumC0040Bp.DESTROYED;
            default:
                StringBuilder sb = new StringBuilder("Unexpected event value ");
                sb.append(bo);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public final void A06(@NonNull EnumC0039Bo bo) {
        A04(this, A01(bo));
    }
}
