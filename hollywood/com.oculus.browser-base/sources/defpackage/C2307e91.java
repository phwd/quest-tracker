package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import com.oculus.browser.R;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: e91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2307e91 {

    /* renamed from: a  reason: collision with root package name */
    public static Drawable f9836a;
    public static Drawable b;
    public static Drawable c;
    public static Drawable d;
    public static Drawable e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;
    public final boolean j;
    public final Context k;
    public final int l;
    public final int m;
    public boolean n;
    public Profile o;
    public C3542lO p;

    public C2307e91(Context context, boolean z) {
        this.k = context;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.f18040_resource_name_obfuscated_RES_2131165423);
        this.g = dimensionPixelSize;
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen.f25720_resource_name_obfuscated_RES_2131166191);
        this.f = dimensionPixelSize2;
        this.h = z ? dimensionPixelSize2 : dimensionPixelSize;
        this.i = AbstractC4656rv1.b(context.getResources().getDisplayMetrics(), (float) context.getResources().getDimensionPixelSize(R.dimen.f25710_resource_name_obfuscated_RES_2131166190));
        this.j = z;
        if (f9836a == null) {
            f9836a = f(c(AbstractC5544x8.a(context, R.drawable.f30310_resource_name_obfuscated_RES_2131231071), dimensionPixelSize), false);
        }
        if (b == null) {
            b = f(c(AbstractC5544x8.a(context, R.drawable.f30310_resource_name_obfuscated_RES_2131231071), dimensionPixelSize2), true);
        }
        if (c == null) {
            c = f(BitmapFactory.decodeResource(context.getResources(), R.drawable.f28750_resource_name_obfuscated_RES_2131230915), false);
        }
        if (d == null) {
            d = f(c(AbstractC5544x8.a(context, R.drawable.f28750_resource_name_obfuscated_RES_2131230915), dimensionPixelSize2), true);
        }
        if (e == null) {
            e = AbstractC5544x8.a(context, R.drawable.f30660_resource_name_obfuscated_RES_2131231106);
        }
        this.l = context.getResources().getColor(R.color.f11220_resource_name_obfuscated_RES_2131099812);
        this.m = context.getResources().getColor(R.color.f11320_resource_name_obfuscated_RES_2131099822);
    }

    public Drawable a(boolean z) {
        if (this.j) {
            return b;
        }
        Drawable drawable = f9836a;
        d(drawable, z);
        return drawable;
    }

    public void b(String str, boolean z, Callback callback) {
        Drawable drawable;
        if (this.p == null || AbstractC5154ur1.g(str)) {
            if (this.j) {
                drawable = d;
            } else {
                drawable = c;
                d(drawable, z);
            }
            callback.onResult(drawable);
            return;
        }
        this.p.c(this.o, str, this.h, new C1966c91(this, callback, z));
    }

    public final Bitmap c(Drawable drawable, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, i2, i2);
        drawable.draw(canvas);
        return createBitmap;
    }

    public final Drawable d(Drawable drawable, boolean z) {
        drawable.setColorFilter(z ? this.m : this.l, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

    public void e(Profile profile) {
        if (!this.n) {
            this.n = true;
            this.o = profile;
            this.p = new C3542lO();
        }
    }

    public final Drawable f(Bitmap bitmap, boolean z) {
        int i2 = z ? this.f : this.g;
        IN0 b2 = AbstractC4055oO.b(this.k.getResources(), Bitmap.createScaledBitmap(bitmap, i2, i2, true));
        if (!z) {
            return b2;
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{AbstractC5544x8.a(this.k, R.drawable.f35170_resource_name_obfuscated_RES_2131231557), b2});
        int i3 = this.i;
        layerDrawable.setLayerInset(1, i3, i3, i3, i3);
        return layerDrawable;
    }
}
