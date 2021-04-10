package defpackage;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: EK0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class EK0 {
    @Deprecated
    public void f(Rect rect, int i, RecyclerView recyclerView) {
        rect.set(0, 0, 0, 0);
    }

    public void g(Rect rect, View view, RecyclerView recyclerView, VK0 vk0) {
        f(rect, ((JK0) view.getLayoutParams()).a(), recyclerView);
    }

    public void h(Canvas canvas, RecyclerView recyclerView, VK0 vk0) {
    }

    public void i(Canvas canvas, RecyclerView recyclerView, VK0 vk0) {
    }
}
