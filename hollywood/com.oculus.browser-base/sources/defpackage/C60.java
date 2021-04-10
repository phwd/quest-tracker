package defpackage;

import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import java.util.Objects;

/* renamed from: C60  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C60 implements View.OnTouchListener {
    public final H60 F;
    public final G60 G;

    public C60(H60 h60, G60 g60) {
        this.F = h60;
        this.G = g60;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        H60 h60 = this.F;
        G60 g60 = this.G;
        Objects.requireNonNull(h60);
        if (motionEvent.getActionMasked() == 0) {
            C5533x40 x40 = h60.f9020J;
            if (!((x40.m.e(x40.r, g60) & 16711680) != 0)) {
                Log.e("ItemTouchHelper", "Start drag has been called but dragging is not enabled");
            } else if (g60.G.getParent() != x40.r) {
                Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
            } else {
                VelocityTracker velocityTracker = x40.t;
                if (velocityTracker != null) {
                    velocityTracker.recycle();
                }
                x40.t = VelocityTracker.obtain();
                x40.i = 0.0f;
                x40.h = 0.0f;
                x40.t(g60, 2);
            }
        }
        return false;
    }
}
