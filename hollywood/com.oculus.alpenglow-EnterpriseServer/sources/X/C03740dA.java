package X;

import android.graphics.Rect;
import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.squareup.okhttp.internal.http.HttpEngine;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@RequiresApi(api = HttpEngine.MAX_FOLLOW_UPS)
/* renamed from: X.0dA  reason: invalid class name and case insensitive filesystem */
public class C03740dA extends AnonymousClass0B5 {
    public static Constructor<WindowInsets> A01;
    public static Field A02;
    public static boolean A03;
    public static boolean A04;
    public WindowInsets A00;

    @Override // X.AnonymousClass0B5
    @NonNull
    public final AnonymousClass0B7 A00() {
        return AnonymousClass0B7.A01(this.A00);
    }

    @Override // X.AnonymousClass0B5
    public final void A02(@NonNull AnonymousClass08P r6) {
        WindowInsets windowInsets = this.A00;
        if (windowInsets != null) {
            this.A00 = windowInsets.replaceSystemWindowInsets(r6.A01, r6.A03, r6.A02, r6.A00);
        }
    }

    public C03740dA() {
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

    public C03740dA(@NonNull AnonymousClass0B7 r2) {
        this.A00 = r2.A02();
    }
}
