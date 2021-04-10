package defpackage;

import android.content.Context;
import com.google.android.gms.cast.framework.CastOptions;
import java.util.List;
import java.util.Objects;

/* renamed from: GG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class GG1 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8080a;
    public final String b;
    public final US0 c;
    public final CastOptions d;
    public final TI1 e;

    public GG1(Context context, CastOptions castOptions, TI1 ti1) {
        String str;
        if (castOptions.x().isEmpty()) {
            str = AbstractC1717an.a(castOptions.F);
        } else {
            String str2 = castOptions.F;
            List x = castOptions.x();
            if (str2 == null) {
                throw new IllegalArgumentException("applicationId cannot be null");
            } else if (x != null) {
                str = AbstractC1717an.b("com.google.android.gms.cast.CATEGORY_CAST", str2, x);
            } else {
                throw new IllegalArgumentException("namespaces cannot be null");
            }
        }
        this.c = new US0(this, null);
        Objects.requireNonNull(context, "null reference");
        this.f8080a = context.getApplicationContext();
        SE0.f(str);
        this.b = str;
        this.d = castOptions;
        this.e = ti1;
    }
}
