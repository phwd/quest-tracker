package X;

import com.oculus.config.updater.ConfigUpdaterDumperPlugin;
import java.io.Serializable;
import java.util.Collections;

/* renamed from: X.0g6  reason: invalid class name and case insensitive filesystem */
public final class C03780g6 extends AbstractC05740lm implements Serializable {
    public static final C03780g6 A00 = new C03780g6();
    public static final C03790g7 A01;
    public static final C03790g7 A02;
    public static final C03790g7 A03;
    public static final C03790g7 A04 = new C03790g7(null, AnonymousClass071.A00(String.class), C03810gA.A01(String.class, null, null), Collections.emptyList());
    public static final long serialVersionUID = 1;

    static {
        Class cls = Boolean.TYPE;
        A01 = new C03790g7(null, AnonymousClass071.A00(cls), C03810gA.A01(cls, null, null), Collections.emptyList());
        Class cls2 = Integer.TYPE;
        A02 = new C03790g7(null, AnonymousClass071.A00(cls2), C03810gA.A01(cls2, null, null), Collections.emptyList());
        Class cls3 = Long.TYPE;
        A03 = new C03790g7(null, AnonymousClass071.A00(cls3), C03810gA.A01(cls3, null, null), Collections.emptyList());
    }

    public static final C03790g7 A00(AbstractC04000gb r1) {
        Class<?> cls = r1._class;
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

    @Override // X.AbstractC05740lm
    public final AbstractC05180kU A03(AnonymousClass08X r4, AbstractC04000gb r5, AbstractC05730ll r6) {
        String str;
        C05310km A06;
        AbstractC04040gi r1 = null;
        if (r4.A05(EnumC03960gV.USE_ANNOTATIONS)) {
            r1 = r4.A01();
        }
        C03810gA A002 = C03810gA.A00(r5._class, r1, r6);
        if (r1 == null || (A06 = r1.A06(A002)) == null) {
            str = "with";
        } else {
            str = A06.A01;
        }
        C05780lq r0 = new C05780lq(r4, r5, A002, str);
        r0.A05();
        return C03790g7.A00(r0);
    }

    @Override // X.AbstractC05740lm
    public final AbstractC05180kU A04(AbstractC03910gQ r4, AbstractC04000gb r5, AbstractC05730ll r6) {
        AbstractC04040gi r0;
        boolean A05 = r4.A05(EnumC03960gV.USE_ANNOTATIONS);
        Class<?> cls = r5._class;
        if (A05) {
            r0 = r4.A01();
        } else {
            r0 = null;
        }
        return new C03790g7(r4, r5, C03810gA.A00(cls, r0, r6), Collections.emptyList());
    }

    @Override // X.AbstractC05740lm
    public final AbstractC05180kU A01(AnonymousClass08X r4, AbstractC04000gb r5, AbstractC05730ll r6) {
        AbstractC04040gi r0;
        C03790g7 A002 = A00(r5);
        if (A002 != null) {
            return A002;
        }
        boolean A05 = r4.A05(EnumC03960gV.USE_ANNOTATIONS);
        Class<?> cls = r5._class;
        if (A05) {
            r0 = r4.A01();
        } else {
            r0 = null;
        }
        C05780lq r02 = new C05780lq(r4, r5, C03810gA.A00(cls, r0, r6), ConfigUpdaterDumperPlugin.COMMAND_SET);
        r02.A05();
        return C03790g7.A00(r02);
    }

    @Override // X.AbstractC05740lm
    public final AbstractC05180kU A02(AnonymousClass08X r4, AbstractC04000gb r5, AbstractC05730ll r6) {
        AbstractC04040gi r0;
        C03790g7 A002 = A00(r5);
        if (A002 != null) {
            return A002;
        }
        boolean A05 = r4.A05(EnumC03960gV.USE_ANNOTATIONS);
        Class<?> cls = r5._class;
        if (A05) {
            r0 = r4.A01();
        } else {
            r0 = null;
        }
        C05780lq r02 = new C05780lq(r4, r5, C03810gA.A00(cls, r0, r6), ConfigUpdaterDumperPlugin.COMMAND_SET);
        r02.A05();
        return C03790g7.A00(r02);
    }
}
