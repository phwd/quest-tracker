package defpackage;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: GC0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GC0 extends EK0 {

    /* renamed from: a  reason: collision with root package name */
    public int f8073a;
    public int b;
    public final /* synthetic */ EC0 c;

    public GC0(EC0 ec0, int i, int i2) {
        this.c = ec0;
        this.f8073a = i;
        this.b = i2;
    }

    @Override // defpackage.EK0
    public void g(Rect rect, View view, RecyclerView recyclerView, VK0 vk0) {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        if (this.c.b0) {
            rect.set(0, 0, 0, this.b);
            return;
        }
        int K = recyclerView.K(view);
        if (K >= 0) {
            int i5 = this.f8073a;
            int i6 = K % i5;
            i3 = this.b;
            int i7 = i3 - ((i6 * i3) / i5);
            i = ((i6 + 1) * i3) / i5;
            if (K < i5) {
                i4 = i3;
            }
            i2 = i4;
            i4 = i7;
        } else {
            i2 = 0;
            i = 0;
            i3 = 0;
        }
        rect.set(i4, i2, i, i3);
    }
}
