package defpackage;

import android.content.Context;
import android.graphics.RectF;
import android.view.MotionEvent;

/* renamed from: ka  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3395ka extends C4411qV {
    public final RectF p;
    public boolean q;

    public C3395ka(Context context, AbstractC4581rV rVVar, RectF rectF, boolean z, boolean z2) {
        super(context, rVVar, z, z2);
        RectF rectF2 = new RectF();
        this.p = rectF2;
        rectF2.setEmpty();
    }

    @Override // defpackage.C4411qV, defpackage.VL
    public boolean b(MotionEvent motionEvent, boolean z) {
        if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 0) {
            this.q = false;
        }
        if (!this.p.contains(motionEvent.getX() * this.f9077a, motionEvent.getY() * this.f9077a)) {
            return false;
        }
        if (motionEvent.getActionMasked() == 0) {
            this.q = true;
        } else if (!this.q) {
            return false;
        }
        return true;
    }

    @Override // defpackage.C4411qV, defpackage.VL
    public boolean c(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 0) {
            this.q = false;
        }
        super.c(motionEvent);
        return true;
    }
}
