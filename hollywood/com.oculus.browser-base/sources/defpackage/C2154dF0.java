package defpackage;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: dF0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2154dF0 extends EK0 {

    /* renamed from: a  reason: collision with root package name */
    public Drawable f9763a;
    public int b;
    public boolean c = true;
    public final /* synthetic */ AbstractC2324eF0 d;

    public C2154dF0(AbstractC2324eF0 ef0) {
        this.d = ef0;
    }

    @Override // defpackage.EK0
    public void g(Rect rect, View view, RecyclerView recyclerView, VK0 vk0) {
        if (j(view, recyclerView)) {
            rect.bottom = this.b;
        }
    }

    @Override // defpackage.EK0
    public void i(Canvas canvas, RecyclerView recyclerView, VK0 vk0) {
        if (this.f9763a != null) {
            int childCount = recyclerView.getChildCount();
            int width = recyclerView.getWidth();
            for (int i = 0; i < childCount; i++) {
                View childAt = recyclerView.getChildAt(i);
                if (j(childAt, recyclerView)) {
                    int height = childAt.getHeight() + ((int) childAt.getY());
                    this.f9763a.setBounds(0, height, width, this.b + height);
                    this.f9763a.draw(canvas);
                }
            }
        }
    }

    public final boolean j(View view, RecyclerView recyclerView) {
        XK0 L = recyclerView.L(view);
        boolean z = false;
        if (!((L instanceof C4886tF0) && ((C4886tF0) L).b0)) {
            return false;
        }
        boolean z2 = this.c;
        int indexOfChild = recyclerView.indexOfChild(view);
        if (indexOfChild >= recyclerView.getChildCount() - 1) {
            return z2;
        }
        XK0 L2 = recyclerView.L(recyclerView.getChildAt(indexOfChild + 1));
        if ((L2 instanceof C4886tF0) && ((C4886tF0) L2).a0) {
            z = true;
        }
        return z;
    }
}
