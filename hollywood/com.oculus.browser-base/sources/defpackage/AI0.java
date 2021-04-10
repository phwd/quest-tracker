package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/* renamed from: AI0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AI0 implements FI0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7665a;

    public AI0(int i) {
        this.f7665a = i;
    }

    @Override // defpackage.FI0
    public void a(HI0 hi0, float f) {
        hi0.setAlpha((int) AbstractC4089od0.e(12.0f, 75.0f, f));
    }

    @Override // defpackage.FI0
    public void b(HI0 hi0, Paint paint, Canvas canvas, float f) {
        RectF rectF = new RectF(hi0.getBounds());
        float f2 = (float) this.f7665a;
        canvas.drawRoundRect(rectF, f2, f2, paint);
    }
}
