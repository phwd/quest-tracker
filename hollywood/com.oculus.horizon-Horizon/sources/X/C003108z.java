package X;

import android.content.Context;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;

@InjectorModule
/* renamed from: X.08z  reason: invalid class name and case insensitive filesystem */
public final class C003108z extends AnonymousClass0J5 {
    @AutoGeneratedAccessMethod
    public static final Context A00(AbstractC06640p5 r1) {
        return (Context) AnonymousClass117.A00(294, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Context A02(AbstractC06640p5 r1) {
        return (Context) AnonymousClass117.A00(80, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final void A04() {
        throw new AssertionError();
    }

    @AutoGeneratedFactoryMethod
    public static final void A05() {
        throw new AssertionError();
    }

    @AutoGeneratedFactoryMethod
    public static final void A06() {
        throw new AssertionError();
    }

    @AutoGeneratedFactoryMethod
    public static final Context A01(AbstractC06640p5 r0) {
        Context A3Z = r0.getScopeAwareInjector().A3Z();
        if (A3Z != null) {
            return A3Z.getApplicationContext();
        }
        throw new RuntimeException();
    }

    @AutoGeneratedFactoryMethod
    public static final Context A03(AbstractC06640p5 r2) {
        Context A3Z = r2.getScopeAwareInjector().A3Z();
        if (A3Z == null) {
            throw new RuntimeException();
        } else if (A3Z == A3Z.getApplicationContext() || (1 & AnonymousClass0Qe.A01.get().A00) == 0) {
            return A3Z;
        } else {
            throw new AnonymousClass0QZ("Should not call getContext in singleton creation. Can lead to memory leaks.");
        }
    }
}
