package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import com.oculus.browser.R;

/* renamed from: mO  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3713mO {

    /* renamed from: a  reason: collision with root package name */
    public Bitmap f10418a;
    public Bitmap b;
    public Bitmap c;
    public Bitmap d;

    public Bitmap a(Resources resources, String str, boolean z) {
        Bitmap bitmap;
        boolean a2 = C3542lO.a(str);
        if (a2) {
            bitmap = z ? this.f10418a : this.b;
        } else {
            bitmap = z ? this.c : this.d;
        }
        if (bitmap != null) {
            return bitmap;
        }
        Bitmap decodeResource = BitmapFactory.decodeResource(resources, C3542lO.a(str) ? R.drawable.f28750_resource_name_obfuscated_RES_2131230915 : R.drawable.f29030_resource_name_obfuscated_RES_2131230943);
        Bitmap createBitmap = Bitmap.createBitmap(decodeResource.getWidth(), decodeResource.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        int color = resources.getColor(z ? R.color.f11220_resource_name_obfuscated_RES_2131099812 : R.color.f11320_resource_name_obfuscated_RES_2131099822);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(decodeResource, 0.0f, 0.0f, paint);
        if (a2 && z) {
            this.f10418a = createBitmap;
        } else if (a2) {
            this.b = createBitmap;
        } else if (z) {
            this.c = createBitmap;
        } else {
            this.d = createBitmap;
        }
        return createBitmap;
    }
}
