package X;

import android.graphics.Rect;
import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@RequiresApi(api = 20)
/* renamed from: X.0sQ  reason: invalid class name */
public class AnonymousClass0sQ extends C001707p {
    public static Constructor<WindowInsets> A01;
    public static Field A02;
    public static boolean A03;
    public static boolean A04;
    public WindowInsets A00;

    @Override // X.C001707p
    @NonNull
    public final AnonymousClass07r A00() {
        WindowInsets windowInsets = this.A00;
        if (windowInsets != null) {
            return new AnonymousClass07r(windowInsets);
        }
        throw null;
    }

    @Override // X.C001707p
    public final void A01(@NonNull AnonymousClass053 r6) {
        WindowInsets windowInsets = this.A00;
        if (windowInsets != null) {
            this.A00 = windowInsets.replaceSystemWindowInsets(r6.A01, r6.A03, r6.A02, r6.A00);
        }
    }

    public AnonymousClass0sQ() {
        WindowInsets windowInsets;
        if (!A04) {
            try {
                A02 = WindowInsets.class.getDeclaredField("CONSUMED");
            } catch (ReflectiveOperationException unused) {
            }
            A04 = true;
        }
        Field field = A02;
        if (field != null) {
            try {
                WindowInsets windowInsets2 = (WindowInsets) field.get(null);
                if (windowInsets2 != null) {
                    windowInsets = new WindowInsets(windowInsets2);
                    this.A00 = windowInsets;
                }
            } catch (ReflectiveOperationException unused2) {
            }
        }
        if (!A03) {
            try {
                A01 = WindowInsets.class.getConstructor(Rect.class);
            } catch (ReflectiveOperationException unused3) {
            }
            A03 = true;
        }
        Constructor<WindowInsets> constructor = A01;
        if (constructor != null) {
            try {
                windowInsets = constructor.newInstance(new Rect());
            } catch (ReflectiveOperationException unused4) {
            }
            this.A00 = windowInsets;
        }
        windowInsets = null;
        this.A00 = windowInsets;
    }

    public AnonymousClass0sQ(@NonNull AnonymousClass07r r2) {
        this.A00 = r2.A00();
    }
}
