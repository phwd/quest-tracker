package X;

import android.graphics.Canvas;
import android.graphics.drawable.NinePatchDrawable;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1lo  reason: invalid class name and case insensitive filesystem */
public final class C10311lo extends AnonymousClass1lZ {
    @Override // X.AnonymousClass1lZ
    public final void draw(Canvas canvas) {
        C01060Pq.A00();
        if (A02()) {
            A01();
            A00();
            canvas.clipPath(this.A0S);
        }
        super.draw(canvas);
        C01060Pq.A00();
    }

    public C10311lo(NinePatchDrawable ninePatchDrawable) {
        super(ninePatchDrawable);
    }
}
