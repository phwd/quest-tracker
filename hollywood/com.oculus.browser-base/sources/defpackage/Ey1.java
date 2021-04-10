package defpackage;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.util.Objects;

/* renamed from: Ey1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Ey1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Fy1 f7990a;

    public Ey1(Fy1 fy1) {
        this.f7990a = fy1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        BitmapDrawable bitmapDrawable;
        Fy1 fy1 = this.f7990a;
        Bitmap bitmap = (Bitmap) obj;
        Objects.requireNonNull(fy1);
        if (bitmap != null && fy1.P != (bitmapDrawable = new BitmapDrawable(fy1.F.getResources(), bitmap))) {
            fy1.P = bitmapDrawable;
            fy1.O = 0;
            fy1.s();
        }
    }
}
