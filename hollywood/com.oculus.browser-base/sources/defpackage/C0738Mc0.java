package defpackage;

import android.graphics.Canvas;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Calendar;

/* renamed from: Mc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0738Mc0 extends EK0 {

    /* renamed from: a  reason: collision with root package name */
    public final Calendar f8487a = AbstractC2255ds1.e();
    public final Calendar b = AbstractC2255ds1.e();
    public final /* synthetic */ C1104Sc0 c;

    public C0738Mc0(C1104Sc0 sc0) {
        this.c = sc0;
    }

    @Override // defpackage.EK0
    public void h(Canvas canvas, RecyclerView recyclerView, VK0 vk0) {
        int i;
        AbstractC5750yK0 yk0 = recyclerView.T;
        if (yk0 instanceof Gz1) {
            IK0 ik0 = recyclerView.U;
            if (ik0 instanceof GridLayoutManager) {
                Gz1 gz1 = (Gz1) yk0;
                GridLayoutManager gridLayoutManager = (GridLayoutManager) ik0;
                for (C1754aw0 aw0 : this.c.A0.n()) {
                    Object obj = aw0.f9500a;
                    if (!(obj == null || aw0.b == null)) {
                        this.f8487a.setTimeInMillis(((Long) obj).longValue());
                        this.b.setTimeInMillis(((Long) aw0.b).longValue());
                        int s = gz1.s(this.f8487a.get(1));
                        int s2 = gz1.s(this.b.get(1));
                        View u = gridLayoutManager.u(s);
                        View u2 = gridLayoutManager.u(s2);
                        int i2 = gridLayoutManager.H;
                        int i3 = s / i2;
                        int i4 = s2 / i2;
                        for (int i5 = i3; i5 <= i4; i5++) {
                            View u3 = gridLayoutManager.u(gridLayoutManager.H * i5);
                            if (u3 != null) {
                                int top = u3.getTop() + this.c.E0.d.f8384a.top;
                                int bottom = u3.getBottom() - this.c.E0.d.f8384a.bottom;
                                int width = i5 == i3 ? (u.getWidth() / 2) + u.getLeft() : 0;
                                if (i5 == i4) {
                                    i = (u2.getWidth() / 2) + u2.getLeft();
                                } else {
                                    i = recyclerView.getWidth();
                                }
                                canvas.drawRect((float) width, (float) top, (float) i, (float) bottom, this.c.E0.h);
                            }
                        }
                    }
                }
            }
        }
    }
}
