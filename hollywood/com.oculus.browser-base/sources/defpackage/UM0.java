package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.oculus.browser.R;
import java.util.Objects;

/* renamed from: UM0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class UM0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final WM0 f9025a;

    public UM0(WM0 wm0) {
        this.f9025a = wm0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        WM0 wm0 = this.f9025a;
        Bitmap bitmap = (Bitmap) obj;
        Objects.requireNonNull(wm0);
        if (bitmap != null) {
            Resources resources = wm0.G.getResources();
            Drawable c = AbstractC3153j7.c(resources, R.drawable.f28720_resource_name_obfuscated_RES_2131230912);
            Bitmap createBitmap = Bitmap.createBitmap(c.getIntrinsicWidth(), c.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            c.setBounds(0, 0, c.getIntrinsicWidth(), c.getIntrinsicHeight());
            c.draw(canvas);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, createBitmap);
            Shader.TileMode tileMode = Shader.TileMode.REPEAT;
            bitmapDrawable.setTileModeXY(tileMode, tileMode);
            bitmapDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
            Bitmap createBitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap2);
            bitmapDrawable.draw(canvas2);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            canvas2.drawBitmap(bitmap, new Matrix(), paint);
            wm0.a(createBitmap2, true);
        }
    }
}
