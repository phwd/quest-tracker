package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import com.google.android.gms.cast.framework.media.ImageHints;

/* renamed from: fJ1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2508fJ1 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9914a;
    public final ImageHints b;
    public Uri c;
    public AbstractAsyncTaskC3362kJ1 d;
    public Bitmap e;
    public boolean f;
    public AbstractC3875nJ1 g;

    public C2508fJ1(Context context) {
        ImageHints imageHints = new ImageHints(-1, 0, 0);
        this.f9914a = context;
        this.b = imageHints;
        a();
    }

    public final void a() {
        AbstractAsyncTaskC3362kJ1 kj1 = this.d;
        if (kj1 != null) {
            kj1.cancel(true);
            this.d = null;
        }
        this.c = null;
        this.e = null;
        this.f = false;
    }
}
