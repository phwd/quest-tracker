package X;

import com.facebook.common.time.AwakeTimeSinceBootClock;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;

@InjectorModule
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.094  reason: invalid class name */
public final class AnonymousClass094 extends AnonymousClass0J5 {
    public static volatile AnonymousClass0pY A00;

    @AutoGeneratedFactoryMethod
    public static final AnonymousClass0pY A01(AbstractC06640p5 r3) {
        if (A00 == null) {
            synchronized (AnonymousClass0pY.class) {
                AnonymousClass0Pi A002 = AnonymousClass0Pi.A00(A00, r3);
                if (A002 != null) {
                    try {
                        r3.getApplicationInjector();
                        A00 = new AnonymousClass0pY();
                    } finally {
                        A002.A01();
                    }
                }
            }
        }
        return A00;
    }

    @AutoGeneratedFactoryMethod
    public static final AwakeTimeSinceBootClock A00() {
        return AwakeTimeSinceBootClock.INSTANCE;
    }

    @AutoGeneratedFactoryMethod
    public static final RealtimeSinceBootClock A02() {
        return RealtimeSinceBootClock.A00;
    }

    @AutoGeneratedFactoryMethod
    public static final AnonymousClass0pW A03() {
        return AnonymousClass0pW.A00;
    }
}
