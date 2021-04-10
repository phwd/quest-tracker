package defpackage;

import android.app.Activity;
import android.content.Context;
import android.os.ResultReceiver;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.lang.ref.WeakReference;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: S10  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class S10 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8868a;
    public WindowAndroid b;
    public Q10 c;
    public Runnable d;

    public S10(Context context, WindowAndroid windowAndroid, Q10 q10) {
        this.f8868a = context;
        this.b = windowAndroid;
        this.c = q10;
    }

    public static Activity a(WindowAndroid windowAndroid) {
        WeakReference s0;
        if (windowAndroid == null || (s0 = windowAndroid.s0()) == null) {
            return null;
        }
        return (Activity) s0.get();
    }

    public final InputMethodManager b() {
        Context a2 = a(this.b);
        if (a2 == null) {
            a2 = this.f8868a;
        }
        return (InputMethodManager) a2.getSystemService("input_method");
    }

    public boolean c(View view) {
        InputMethodManager b2 = b();
        return b2 != null && b2.isActive(view);
    }

    public final void d(View view, int i, ResultReceiver resultReceiver) {
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            InputMethodManager b2 = b();
            if (b2 != null) {
                b2.showSoftInput(view, i, resultReceiver);
            }
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
        }
    }
}
