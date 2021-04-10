package X;

import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;

/* renamed from: X.0cp  reason: invalid class name and case insensitive filesystem */
public class C03620cp extends C00940Ca {
    public final /* synthetic */ AnonymousClass0Cj A00;

    public C03620cp(AnonymousClass0Cj r1) {
        this.A00 = r1;
    }

    @Override // X.C00940Ca
    @NonNull
    public final AnonymousClass0MN A01(@NonNull ClassLoader classLoader, @NonNull String str) {
        try {
            return (AnonymousClass0MN) C00940Ca.A00(this.A00.A05.A01.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (InstantiationException e) {
            throw new AnonymousClass0CN(AnonymousClass006.A07("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e);
        } catch (IllegalAccessException e2) {
            throw new AnonymousClass0CN(AnonymousClass006.A07("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e2);
        } catch (NoSuchMethodException e3) {
            throw new AnonymousClass0CN(AnonymousClass006.A07("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e3);
        } catch (InvocationTargetException e4) {
            throw new AnonymousClass0CN(AnonymousClass006.A07("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e4);
        }
    }
}
