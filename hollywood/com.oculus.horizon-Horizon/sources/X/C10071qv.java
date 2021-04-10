package X;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* renamed from: X.1qv  reason: invalid class name and case insensitive filesystem */
public final class C10071qv {
    @Nullable
    public AnonymousClass0Km A00;
    @Nullable
    public AbstractC10131rK A01;
    @Nullable
    public C09891pv A02;
    @Nullable
    public AnonymousClass0Ox A03;
    @Nullable
    public AnonymousClass1pV A04;
    public final AnonymousClass1r6 A05;

    @Nullable
    private AbstractC10131rK A00() {
        AbstractC10131rK r6 = this.A01;
        if (r6 != null) {
            return r6;
        }
        try {
            Constructor<?> constructor = Class.forName("com.facebook.imagepipeline.memory.NativeMemoryChunkPool").getConstructor(AbstractC10671uj.class, C10471su.class, AbstractC10691uo.class);
            AnonymousClass1r6 r1 = this.A05;
            AbstractC10131rK r0 = (AbstractC10131rK) constructor.newInstance(r1.A00, r1.A03, r1.A06);
            this.A01 = r0;
            return r0;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            C01080Kb.A04("PoolFactory", "", e);
            this.A01 = null;
            return null;
        }
    }

    public final C09891pv A01() {
        C09891pv r0 = this.A02;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass0Km r3 = this.A00;
        if (r3 == null) {
            AnonymousClass1r6 r02 = this.A05;
            r3 = new C10141rL(r02.A00, r02.A04, r02.A07);
            this.A00 = r3;
        }
        C09891pv r03 = new C09891pv(r3);
        this.A02 = r03;
        return r03;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public final AnonymousClass0Ox A02() {
        AbstractC10671uj r2;
        C10471su A002;
        AnonymousClass0Ox r3 = this.A03;
        if (r3 != null) {
            return r3;
        }
        AnonymousClass1r6 r32 = this.A05;
        switch ("legacy".hashCode()) {
            case -1868884870:
                if ("legacy".equals("legacy_default_params")) {
                    r2 = r32.A00;
                    A002 = AnonymousClass1s7.A00();
                    break;
                }
                r2 = r32.A00;
                A002 = r32.A01;
                break;
            case -404562712:
                if ("legacy".equals("experimental")) {
                    AnonymousClass1sK r33 = new AnonymousClass1sK(AnonymousClass1uQ.A00());
                    this.A03 = r33;
                    return r33;
                }
                r2 = r32.A00;
                A002 = r32.A01;
                break;
            case -402149703:
                if ("legacy".equals("dummy_with_tracking")) {
                    C10121rJ r34 = new C10121rJ();
                    this.A03 = r34;
                    return r34;
                }
                r2 = r32.A00;
                A002 = r32.A01;
                break;
            case 95945896:
                if ("legacy".equals("dummy")) {
                    AnonymousClass1s9 r35 = new AnonymousClass1s9();
                    this.A03 = r35;
                    return r35;
                }
                r2 = r32.A00;
                A002 = r32.A01;
                break;
            default:
                r2 = r32.A00;
                A002 = r32.A01;
                break;
        }
        AnonymousClass1rH r36 = new AnonymousClass1rH(r2, A002, r32.A05);
        this.A03 = r36;
        return r36;
    }

    public final AnonymousClass1pV A03() {
        AnonymousClass1pV r2 = this.A04;
        if (r2 != null) {
            return r2;
        }
        AnonymousClass0KU.A02(A00(), AnonymousClass006.A01("failed to get pool for chunk type: ", 0));
        AnonymousClass1pV r22 = new AnonymousClass1pV(A00(), A01());
        this.A04 = r22;
        return r22;
    }

    public C10071qv(AnonymousClass1r6 r1) {
        this.A05 = r1;
    }
}
