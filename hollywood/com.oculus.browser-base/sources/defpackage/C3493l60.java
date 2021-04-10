package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;
import android.view.WindowInsets;
import android.view.inputmethod.InputMethodManager;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: l60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3493l60 {
    public static C3493l60 F = new C3493l60();
    public final C1322Vq0 G = new C1322Vq0();

    public void a(AbstractC3322k60 k60) {
        if (this.G.isEmpty()) {
            g();
        }
        this.G.b(k60);
    }

    public int b(View view) {
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int height = view.getHeight() - (rect.height() + rect.top);
        if (height <= 0) {
            return 0;
        }
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        return rootWindowInsets != null ? height - rootWindowInsets.getStableInsetBottom() : height;
    }

    public boolean c(View view) {
        return ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public boolean d(View view) {
        return c(view);
    }

    public boolean e(Context context, View view) {
        View rootView = view.getRootView();
        if (rootView != null) {
            int b = b(rootView);
            Rect rect = new Rect();
            rootView.getWindowVisibleDisplayFrame(rect);
            rect.width();
            rootView.getWidth();
            if (b > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean f(Context context, View view) {
        return e(context, view);
    }

    public void g() {
    }

    public void h(AbstractC3322k60 k60) {
        this.G.c(k60);
        if (this.G.isEmpty()) {
            j();
        }
    }

    public void i(View view) {
        new RunnableC3151j60(this, view, new AtomicInteger(), new Handler()).run();
    }

    public void j() {
    }
}
