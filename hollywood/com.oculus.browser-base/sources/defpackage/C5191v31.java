package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/* renamed from: v31  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5191v31 {

    /* renamed from: a  reason: collision with root package name */
    public Drawable f11457a;
    public boolean b;
    public boolean c;
    public boolean d;
    public int e;

    public C5191v31(Drawable drawable) {
        this.f11457a = drawable;
    }

    public static C5191v31 b(Context context, Bitmap bitmap) {
        return new C5191v31(new BitmapDrawable(context.getResources(), bitmap));
    }

    public static C5191v31 c(Context context, int i) {
        C5191v31 v31 = new C5191v31(AbstractC5544x8.a(context, i));
        v31.e = i;
        return v31;
    }

    public C5361w31 a() {
        return new C5361w31(this.f11457a, this.c, this.d, this.b, this.e, null);
    }
}
