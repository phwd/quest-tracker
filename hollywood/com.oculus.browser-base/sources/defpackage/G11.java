package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/* renamed from: G11  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class G11 implements AbstractC0853Oa {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Resources f8056a;

    public G11(Resources resources) {
        this.f8056a = resources;
    }

    @Override // defpackage.AbstractC0853Oa
    public AbstractC3197jM0 a(int i) {
        Resources resources = this.f8056a;
        if (i <= 0) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        options.inPreferredConfig = config;
        options.inPreferredConfig = config;
        Bitmap decodeResource = BitmapFactory.decodeResource(resources, i, options);
        if (decodeResource == null) {
            decodeResource = null;
        } else if (decodeResource.getConfig() != options.inPreferredConfig) {
            Bitmap createBitmap = Bitmap.createBitmap(decodeResource.getWidth(), decodeResource.getHeight(), options.inPreferredConfig);
            new Canvas(createBitmap).drawBitmap(decodeResource, 0.0f, 0.0f, (Paint) null);
            decodeResource.recycle();
            decodeResource = createBitmap;
        }
        if (decodeResource == null) {
            try {
                Drawable c = AbstractC3153j7.c(resources, i);
                int max = Math.max(c.getMinimumWidth(), Math.max(0, 1));
                int max2 = Math.max(c.getMinimumHeight(), Math.max(0, 1));
                Bitmap createBitmap2 = Bitmap.createBitmap(max, max2, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap2);
                c.setBounds(0, 0, max, max2);
                c.draw(canvas);
                decodeResource = createBitmap2;
            } catch (Resources.NotFoundException unused) {
                decodeResource = null;
            }
        }
        if (decodeResource == null) {
            return null;
        }
        return new F11(decodeResource);
    }
}
