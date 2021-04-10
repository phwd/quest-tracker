package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import com.oculus.browser.R;

/* renamed from: Ki1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0636Ki1 extends BitmapDrawable {

    /* renamed from: a  reason: collision with root package name */
    public ColorStateList f8382a;

    public C0636Ki1(Context context, Bitmap bitmap) {
        super(context.getResources(), bitmap);
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        this.f8382a = context.getColorStateList(R.color.f11390_resource_name_obfuscated_RES_2131099829);
    }

    public static C0636Ki1 a(Context context, int i) {
        return new C0636Ki1(context, BitmapFactory.decodeResource(context.getResources(), i));
    }

    public static C0636Ki1 b(Context context, int i, int i2) {
        C0636Ki1 ki1 = new C0636Ki1(context, BitmapFactory.decodeResource(context.getResources(), i));
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        ki1.c(context.getColorStateList(i2));
        return ki1;
    }

    public void c(ColorStateList colorStateList) {
        if (this.f8382a != colorStateList) {
            this.f8382a = colorStateList;
            d();
        }
    }

    public final boolean d() {
        ColorStateList colorStateList = this.f8382a;
        if (colorStateList == null) {
            return false;
        }
        setColorFilter(colorStateList.getColorForState(getState(), 0), PorterDuff.Mode.SRC_IN);
        return true;
    }

    public boolean isStateful() {
        return true;
    }

    public boolean onStateChange(int[] iArr) {
        boolean d = d();
        super.onStateChange(iArr);
        return d;
    }
}
