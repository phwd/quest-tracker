package X;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Al {
    public static final AnonymousClass3C<String, Class<?>> A00 = new AnonymousClass3C<>();

    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: X.3C<java.lang.String, java.lang.Class<?>> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v7. Raw type applied. Possible types: java.lang.Class<?>, java.lang.Class<? extends androidx.fragment.app.Fragment> */
    @NonNull
    public static Class<? extends Fragment> A00(@NonNull ClassLoader classLoader, @NonNull String str) {
        try {
            AnonymousClass3C<String, Class<?>> r1 = A00;
            Class<? extends Fragment> cls = (Class) r1.get(str);
            if (cls != null) {
                return cls;
            }
            Class cls2 = Class.forName(str, false, classLoader);
            r1.put(str, cls2);
            return cls2;
        } catch (ClassNotFoundException e) {
            throw new AY(AnonymousClass06.A04("Unable to instantiate fragment ", str, ": make sure class name exists"), e);
        } catch (ClassCastException e2) {
            throw new AY(AnonymousClass06.A04("Unable to instantiate fragment ", str, ": make sure class is a valid subclass of Fragment"), e2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0049, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004a, code lost:
        r1 = X.AnonymousClass06.A04(r3, r7, ": calling Fragment constructor caused an exception");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0051, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0052, code lost:
        r1 = X.AnonymousClass06.A04(r3, r7, ": could not find Fragment constructor");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0059, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005a, code lost:
        r1 = X.AnonymousClass06.A04(r3, r7, r4);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:3:0x0008, B:9:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0059 A[ExcHandler: IllegalAccessException | InstantiationException (e java.lang.Throwable), PHI: r3 r4 
      PHI: (r3v0 java.lang.String) = (r3v1 java.lang.String), (r3v2 java.lang.String) binds: [B:9:0x0031, B:3:0x0008] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r4v0 java.lang.String) = (r4v1 java.lang.String), (r4v2 java.lang.String) binds: [B:9:0x0031, B:3:0x0008] A[DONT_GENERATE, DONT_INLINE], Splitter:B:3:0x0008] */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final androidx.fragment.app.Fragment A01(@androidx.annotation.NonNull java.lang.ClassLoader r6, @androidx.annotation.NonNull java.lang.String r7) {
        /*
        // Method dump skipped, instructions count: 108
        */
        throw new UnsupportedOperationException("Method not decompiled: X.Al.A01(java.lang.ClassLoader, java.lang.String):androidx.fragment.app.Fragment");
    }
}
