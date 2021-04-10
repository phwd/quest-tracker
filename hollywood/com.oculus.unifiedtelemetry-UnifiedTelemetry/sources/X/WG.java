package X;

import java.io.Serializable;
import java.util.Collections;

public final class WG extends VP implements Serializable {
    public static final WG A00 = new WG();
    public static final WH A01;
    public static final WH A02;
    public static final WH A03;
    public static final WH A04 = new WH(null, AnonymousClass73.A00(String.class), WK.A01(String.class, null, null), Collections.emptyList());
    public static final long serialVersionUID = 1;

    static {
        Class cls = Boolean.TYPE;
        A01 = new WH(null, AnonymousClass73.A00(cls), WK.A01(cls, null, null), Collections.emptyList());
        Class cls2 = Integer.TYPE;
        A02 = new WH(null, AnonymousClass73.A00(cls2), WK.A01(cls2, null, null), Collections.emptyList());
        Class cls3 = Long.TYPE;
        A03 = new WH(null, AnonymousClass73.A00(cls3), WK.A01(cls3, null, null), Collections.emptyList());
    }

    public static final WH A00(AbstractC0224Wl wl) {
        Class<?> cls = wl._class;
        if (cls == String.class) {
            return A04;
        }
        if (cls == Boolean.TYPE) {
            return A01;
        }
        if (cls == Integer.TYPE) {
            return A02;
        }
        if (cls == Long.TYPE) {
            return A03;
        }
        return null;
    }

    @Override // X.VP
    public final jm A03(AnonymousClass8M r4, AbstractC0224Wl wl, VQ vq) {
        String str;
        C0410hE A07;
        Wp wp = null;
        if (r4.A05(EnumC0220Wf.USE_ANNOTATIONS)) {
            wp = r4.A01();
        }
        WK A002 = WK.A00(wl._class, wp, vq);
        if (wp == null || (A07 = wp.A07(A002)) == null) {
            str = "with";
        } else {
            str = A07.A01;
        }
        VM vm = new VM(r4, wl, A002, str);
        vm.A05();
        return WH.A00(vm);
    }

    @Override // X.VP
    public final jm A04(WZ wz, AbstractC0224Wl wl, VQ vq) {
        Wp wp;
        boolean A05 = wz.A05(EnumC0220Wf.USE_ANNOTATIONS);
        Class<?> cls = wl._class;
        if (A05) {
            wp = wz.A01();
        } else {
            wp = null;
        }
        return new WH(wz, wl, WK.A00(cls, wp, vq), Collections.emptyList());
    }

    @Override // X.VP
    public final jm A01(AnonymousClass8M r4, AbstractC0224Wl wl, VQ vq) {
        Wp wp;
        WH A002 = A00(wl);
        if (A002 != null) {
            return A002;
        }
        boolean A05 = r4.A05(EnumC0220Wf.USE_ANNOTATIONS);
        Class<?> cls = wl._class;
        if (A05) {
            wp = r4.A01();
        } else {
            wp = null;
        }
        VM vm = new VM(r4, wl, WK.A00(cls, wp, vq), "set");
        vm.A05();
        return WH.A00(vm);
    }

    @Override // X.VP
    public final jm A02(AnonymousClass8M r4, AbstractC0224Wl wl, VQ vq) {
        Wp wp;
        WH A002 = A00(wl);
        if (A002 != null) {
            return A002;
        }
        boolean A05 = r4.A05(EnumC0220Wf.USE_ANNOTATIONS);
        Class<?> cls = wl._class;
        if (A05) {
            wp = r4.A01();
        } else {
            wp = null;
        }
        VM vm = new VM(r4, wl, WK.A00(cls, wp, vq), "set");
        vm.A05();
        return WH.A00(vm);
    }
}
