package X;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;

@InjectorModule
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.093  reason: invalid class name */
public final class AnonymousClass093 extends AnonymousClass0J5 {
    public static final Object A00 = new Object();
    public static final Object A01 = new Object();

    @AutoGeneratedFactoryMethod
    public static final AnonymousClass0pV A00(AbstractC06640p5 r4) {
        synchronized (AnonymousClass0pV.class) {
            AnonymousClass0Pi A002 = AnonymousClass0Pi.A00(null, r4);
            if (A002 != null) {
                try {
                    r4.getApplicationInjector();
                    throw new IllegalStateException("Application did not provide its own FbAppType");
                } catch (Throwable th) {
                    A002.A01();
                    throw th;
                }
            }
        }
        return null;
    }
}