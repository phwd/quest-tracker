package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: NP0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NP0 extends View {
    public final ViewGroup F;
    public final int G;
    public FP0 H;

    public NP0(Context context, ViewGroup viewGroup, int i, FP0 fp0) {
        super(context);
        this.F = viewGroup;
        setFocusable(false);
        setImportantForAccessibility(2);
        this.G = i;
        this.H = fp0;
        setAlpha(0.0f);
        setVisibility(8);
        super.setBackgroundColor(i);
        setLayoutParams(new ViewGroup.MarginLayoutParams(-1, -1));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0038, code lost:
        if (((android.view.GestureDetector) r0.f.g(r3)).onTouchEvent(r1) == false) goto L_0x0049;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            FP0 r0 = r5.H
            LP0 r0 = (defpackage.LP0) r0
            boolean r1 = r0.i
            r2 = 0
            if (r1 == 0) goto L_0x000a
            goto L_0x0049
        L_0x000a:
            UH0 r1 = r0.f
            java.util.Collection r1 = r1.a()
            TH0 r3 = defpackage.MP0.j
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            boolean r1 = r1.contains(r3)
            if (r1 != 0) goto L_0x001b
            goto L_0x0049
        L_0x001b:
            boolean r1 = r0.h
            if (r1 == 0) goto L_0x003b
            int r1 = r6.getActionMasked()
            if (r1 == 0) goto L_0x003b
            android.view.MotionEvent r1 = android.view.MotionEvent.obtain(r6)
            r1.setAction(r2)
            UH0 r4 = r0.f
            java.lang.Object r4 = r4.g(r3)
            android.view.GestureDetector r4 = (android.view.GestureDetector) r4
            boolean r1 = r4.onTouchEvent(r1)
            if (r1 != 0) goto L_0x003b
            goto L_0x0049
        L_0x003b:
            r0.h = r2
            UH0 r0 = r0.f
            java.lang.Object r0 = r0.g(r3)
            android.view.GestureDetector r0 = (android.view.GestureDetector) r0
            boolean r2 = r0.onTouchEvent(r6)
        L_0x0049:
            if (r2 == 0) goto L_0x004d
            r6 = 1
            return r6
        L_0x004d:
            boolean r6 = super.onTouchEvent(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.NP0.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setBackgroundColor(int i) {
        if (i == 0) {
            i = this.G;
        }
        super.setBackgroundColor(i);
    }
}
