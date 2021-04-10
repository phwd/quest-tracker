package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.oculus.browser.R;

/* renamed from: oi1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4105oi1 {

    /* renamed from: a  reason: collision with root package name */
    public final Resources f10570a;
    public final KZ b;
    public final KN0 c;
    public final Resources.Theme d;
    public final int e;
    public final int f;
    public final float g;
    public final int h;
    public final int i;

    public C4105oi1(Context context, int i2, int i3, KZ kz) {
        this.b = kz;
        Resources resources = context.getResources();
        this.f10570a = resources;
        this.d = context.getTheme();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.f26160_resource_name_obfuscated_RES_2131166235);
        this.e = dimensionPixelSize;
        this.g = resources.getDimension(R.dimen.f26130_resource_name_obfuscated_RES_2131166232);
        this.f = Math.min(dimensionPixelSize, resources.getDimensionPixelSize(R.dimen.f26150_resource_name_obfuscated_RES_2131166234));
        int i4 = 0;
        this.h = i2 != 1 ? i2 != 2 ? 0 : R.layout.f41630_resource_name_obfuscated_RES_2131624472 : R.layout.f41620_resource_name_obfuscated_RES_2131624471;
        if (i2 == 1) {
            i4 = R.layout.f42070_resource_name_obfuscated_RES_2131624516;
        } else if (i2 == 2) {
            i4 = R.layout.f42080_resource_name_obfuscated_RES_2131624517;
        }
        this.i = i4;
        this.c = new KN0(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize / 2, resources.getColor(R.color.f11180_resource_name_obfuscated_RES_2131099808), (float) resources.getDimensionPixelSize(R.dimen.f26180_resource_name_obfuscated_RES_2131166237));
    }

    public void a(C0815Nh1 nh1, Bitmap bitmap) {
        int round = Math.round((this.g * ((float) bitmap.getWidth())) / ((float) this.e));
        if (nh1.f8567a.e == 7) {
            round = this.e / 2;
        }
        HN0 hn0 = new HN0(this.f10570a, bitmap);
        hn0.b((float) round);
        hn0.d.setAntiAlias(true);
        hn0.invalidateSelf();
        hn0.setFilterBitmap(true);
        nh1.e = hn0;
        nh1.c = 1;
    }

    public void b(C0815Nh1 nh1, int i2, boolean z) {
        if (nh1.f8567a.e != 7) {
            this.c.e.setColor(i2);
            nh1.e = new BitmapDrawable(this.f10570a, this.c.b(nh1.f8567a.b.h()));
            nh1.c = z ? 3 : 2;
        }
    }
}
