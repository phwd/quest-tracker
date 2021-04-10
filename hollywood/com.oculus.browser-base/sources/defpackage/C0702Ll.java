package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import com.oculus.browser.R;

/* renamed from: Ll  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0702Ll {

    /* renamed from: a  reason: collision with root package name */
    public final C0641Kl f8435a;
    public final C0641Kl b;
    public final C0641Kl c;
    public final C0641Kl d;
    public final C0641Kl e;
    public final C0641Kl f;
    public final C0641Kl g;
    public final Paint h;

    public C0702Ll(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(AbstractC0251Ec0.c(context, R.attr.f6210_resource_name_obfuscated_RES_2130969067, C1104Sc0.class.getCanonicalName()), FJ0.d0);
        this.f8435a = C0641Kl.a(context, obtainStyledAttributes.getResourceId(3, 0));
        this.g = C0641Kl.a(context, obtainStyledAttributes.getResourceId(1, 0));
        this.b = C0641Kl.a(context, obtainStyledAttributes.getResourceId(2, 0));
        this.c = C0641Kl.a(context, obtainStyledAttributes.getResourceId(4, 0));
        ColorStateList b2 = AbstractC2722gd0.b(context, obtainStyledAttributes, 5);
        this.d = C0641Kl.a(context, obtainStyledAttributes.getResourceId(7, 0));
        this.e = C0641Kl.a(context, obtainStyledAttributes.getResourceId(6, 0));
        this.f = C0641Kl.a(context, obtainStyledAttributes.getResourceId(8, 0));
        Paint paint = new Paint();
        this.h = paint;
        paint.setColor(b2.getDefaultColor());
        obtainStyledAttributes.recycle();
    }
}
