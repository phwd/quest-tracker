package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/* renamed from: zI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5915zI0 implements FI0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DI0 f11739a;

    public C5915zI0(DI0 di0) {
        this.f11739a = di0;
    }

    @Override // defpackage.FI0
    public void a(HI0 hi0, float f) {
        hi0.invalidateSelf();
    }

    @Override // defpackage.FI0
    public void b(HI0 hi0, Paint paint, Canvas canvas, float f) {
        Rect bounds = hi0.getBounds();
        canvas.drawCircle(bounds.exactCenterX(), bounds.exactCenterY(), AbstractC4089od0.e(this.f11739a.a(bounds), this.f11739a.b(bounds), 1.0f - f), paint);
    }
}
