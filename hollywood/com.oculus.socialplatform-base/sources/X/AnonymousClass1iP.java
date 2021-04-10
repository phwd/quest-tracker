package X;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* renamed from: X.1iP  reason: invalid class name */
public final class AnonymousClass1iP {
    @Nullable
    public AnonymousClass0VT A00;
    @Nullable
    public AbstractC09461i1 A01;
    @Nullable
    public AnonymousClass0JW A02;
    @Nullable
    public AnonymousClass0JZ A03;
    @Nullable
    public AnonymousClass0VL A04;
    public final AnonymousClass1iQ A05;

    @Nullable
    private AbstractC09461i1 A00() {
        AbstractC09461i1 r0 = this.A01;
        if (r0 != null) {
            return r0;
        }
        try {
            Constructor<?> constructor = Class.forName("com.facebook.imagepipeline.memory.NativeMemoryChunkPool").getConstructor(AnonymousClass0JS.class, AnonymousClass1i0.class, AnonymousClass1i3.class);
            AnonymousClass1iQ r02 = this.A05;
            AbstractC09461i1 r03 = (AbstractC09461i1) constructor.newInstance(r02.A00, r02.A03, r02.A06);
            this.A01 = r03;
            return r03;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            AnonymousClass0J5.A04("PoolFactory", "", e);
            this.A01 = null;
            return null;
        }
    }

    public final AnonymousClass0JW A01() {
        AnonymousClass0JW r2 = this.A02;
        if (r2 != null) {
            return r2;
        }
        C00740Ii.A02(A00(), AnonymousClass006.A03("failed to get pool for chunk type: ", 0));
        AnonymousClass1iR r22 = new AnonymousClass1iR(A00(), A02());
        this.A02 = r22;
        return r22;
    }

    public final AnonymousClass0JZ A02() {
        AnonymousClass0JZ r0 = this.A03;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass0VT r3 = this.A00;
        if (r3 == null) {
            AnonymousClass1iQ r02 = this.A05;
            r3 = new C09451hz(r02.A00, r02.A04, r02.A07);
            this.A00 = r3;
        }
        AnonymousClass0JZ r03 = new AnonymousClass0JZ(r3);
        this.A03 = r03;
        return r03;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public final AnonymousClass0VL A03() {
        AnonymousClass0JS r2;
        AnonymousClass1i0 A002;
        AnonymousClass0VL r3 = this.A04;
        if (r3 != null) {
            return r3;
        }
        AnonymousClass1iQ r32 = this.A05;
        switch ("legacy".hashCode()) {
            case -1868884870:
                if ("legacy".equals("legacy_default_params")) {
                    r2 = r32.A00;
                    A002 = AnonymousClass1iO.A00();
                    break;
                }
                r2 = r32.A00;
                A002 = r32.A01;
                break;
            case -404562712:
                if ("legacy".equals("experimental")) {
                    AnonymousClass1iT r33 = new AnonymousClass1iT(C09531il.A00());
                    this.A04 = r33;
                    return r33;
                }
                r2 = r32.A00;
                A002 = r32.A01;
                break;
            case -402149703:
                if ("legacy".equals("dummy_with_tracking")) {
                    C09421hu r34 = new C09421hu();
                    this.A04 = r34;
                    return r34;
                }
                r2 = r32.A00;
                A002 = r32.A01;
                break;
            case 95945896:
                if ("legacy".equals("dummy")) {
                    C09431hx r35 = new C09431hx();
                    this.A04 = r35;
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
        C09411ht r36 = new C09411ht(r2, A002, r32.A05);
        this.A04 = r36;
        return r36;
    }

    public AnonymousClass1iP(AnonymousClass1iQ r1) {
        this.A05 = r1;
    }
}
