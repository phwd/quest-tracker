package X;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;

/* renamed from: X.05P  reason: invalid class name */
public class AnonymousClass05P extends TouchDelegate {
    public boolean A00;
    public final int A01;
    public final Rect A02 = new Rect();
    public final Rect A03 = new Rect();
    public final Rect A04 = new Rect();
    public final View A05;

    public AnonymousClass05P(Rect rect, Rect rect2, View view) {
        super(rect, view);
        this.A01 = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        this.A04.set(rect);
        Rect rect3 = this.A03;
        rect3.set(rect);
        int i = -this.A01;
        rect3.inset(i, i);
        this.A02.set(rect2);
        this.A05 = view;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004a, code lost:
        if (r7.A03.contains(r3, r2) == false) goto L_0x004c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            float r0 = r8.getX()
            int r3 = (int) r0
            float r0 = r8.getY()
            int r2 = (int) r0
            int r6 = r8.getAction()
            r4 = 2
            r5 = 1
            r1 = 0
            if (r6 == 0) goto L_0x0056
            if (r6 == r5) goto L_0x0040
            if (r6 == r4) goto L_0x0040
            r0 = 3
            if (r6 != r0) goto L_0x003f
            boolean r0 = r7.A00
            r7.A00 = r1
            if (r0 == 0) goto L_0x003f
        L_0x0020:
            android.graphics.Rect r0 = r7.A02
            boolean r0 = r0.contains(r3, r2)
            if (r0 != 0) goto L_0x004c
            android.view.View r1 = r7.A05
            int r0 = r1.getWidth()
            int r0 = r0 / r4
            float r3 = (float) r0
            int r2 = r1.getHeight()
            int r2 = r2 / r4
        L_0x0035:
            float r0 = (float) r2
            r8.setLocation(r3, r0)
            android.view.View r0 = r7.A05
            boolean r1 = r0.dispatchTouchEvent(r8)
        L_0x003f:
            return r1
        L_0x0040:
            boolean r0 = r7.A00
            if (r0 == 0) goto L_0x003f
            android.graphics.Rect r0 = r7.A03
            boolean r0 = r0.contains(r3, r2)
            if (r0 != 0) goto L_0x0020
        L_0x004c:
            android.graphics.Rect r1 = r7.A02
            int r0 = r1.left
            int r3 = r3 - r0
            float r3 = (float) r3
            int r0 = r1.top
            int r2 = r2 - r0
            goto L_0x0035
        L_0x0056:
            android.graphics.Rect r0 = r7.A04
            boolean r0 = r0.contains(r3, r2)
            if (r0 == 0) goto L_0x003f
            r7.A00 = r5
            goto L_0x0020
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass05P.onTouchEvent(android.view.MotionEvent):boolean");
    }
}
