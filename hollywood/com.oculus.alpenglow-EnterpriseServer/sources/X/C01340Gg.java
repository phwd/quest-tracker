package X;

import android.content.Context;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;

@InjectorModule
/* renamed from: X.0Gg  reason: invalid class name and case insensitive filesystem */
public final class C01340Gg extends AbstractC01750Lk {
    @AutoGeneratedAccessMethod
    public static final Context A00(AbstractC02990bJ r1) {
        return (Context) AnonymousClass13m.A00(4, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Context A02(AbstractC02990bJ r1) {
        return (Context) AnonymousClass13m.A00(76, r1);
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
    public static final Context A01(AbstractC02990bJ r0) {
        Context A3m = r0.getScopeAwareInjector().A3m();
        if (A3m != null) {
            return A3m.getApplicationContext();
        }
        throw new RuntimeException();
    }

    @AutoGeneratedFactoryMethod
    public static final Context A03(AbstractC02990bJ r2) {
        Context A3m = r2.getScopeAwareInjector().A3m();
        if (A3m == null) {
            throw new RuntimeException();
        } else if (A3m == A3m.getApplicationContext() || (1 & AnonymousClass0RZ.A01.get().A00) == 0) {
            return A3m;
        } else {
            throw new AnonymousClass0RU("Should not call getContext in singleton creation. Can lead to memory leaks.");
        }
    }
}
