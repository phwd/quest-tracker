package X;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.lang.reflect.InvocationTargetException;

/* renamed from: X.0vF  reason: invalid class name and case insensitive filesystem */
public class C05310vF extends AnonymousClass09S {
    public final /* synthetic */ AnonymousClass09b A00;

    public C05310vF(AnonymousClass09b r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass09S
    @NonNull
    public final Fragment A01(@NonNull ClassLoader classLoader, @NonNull String str) {
        try {
            return (Fragment) AnonymousClass09S.A00(this.A00.A05.A01.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (InstantiationException e) {
            throw new AnonymousClass09F(AnonymousClass006.A09("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e);
        } catch (IllegalAccessException e2) {
            throw new AnonymousClass09F(AnonymousClass006.A09("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e2);
        } catch (NoSuchMethodException e3) {
            throw new AnonymousClass09F(AnonymousClass006.A09("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e3);
        } catch (InvocationTargetException e4) {
            throw new AnonymousClass09F(AnonymousClass006.A09("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e4);
        }
    }
}
