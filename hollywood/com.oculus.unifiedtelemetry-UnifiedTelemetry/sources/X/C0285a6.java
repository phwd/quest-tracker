package X;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.lang.reflect.InvocationTargetException;

/* renamed from: X.a6  reason: case insensitive filesystem */
public class C0285a6 extends AnonymousClass9R {
    public final /* synthetic */ AbstractC00279a A00;

    public C0285a6(AbstractC00279a r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass9R
    @NonNull
    public final Fragment A01(@NonNull ClassLoader classLoader, @NonNull String str) {
        try {
            return (Fragment) AnonymousClass9R.A00(this.A00.A05.A01.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (InstantiationException e) {
            throw new AnonymousClass9E(AnonymousClass06.A05("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e);
        } catch (IllegalAccessException e2) {
            throw new AnonymousClass9E(AnonymousClass06.A05("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e2);
        } catch (NoSuchMethodException e3) {
            throw new AnonymousClass9E(AnonymousClass06.A05("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e3);
        } catch (InvocationTargetException e4) {
            throw new AnonymousClass9E(AnonymousClass06.A05("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e4);
        }
    }
}
