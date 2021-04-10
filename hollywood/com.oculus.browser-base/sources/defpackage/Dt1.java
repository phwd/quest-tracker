package defpackage;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.util.Objects;
import org.chromium.base.Callback;

/* renamed from: Dt1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Dt1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Ft1 f7921a;
    public final Callback b;

    public Dt1(Ft1 ft1, Callback callback) {
        this.f7921a = ft1;
        this.b = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Ft1 ft1 = this.f7921a;
        Callback callback = this.b;
        Objects.requireNonNull(ft1);
        callback.onResult(new BitmapDrawable(ft1.f8047a.getResources(), (Bitmap) obj));
    }
}
