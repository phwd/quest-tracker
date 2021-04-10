package defpackage;

import android.graphics.Bitmap;
import java.util.Arrays;
import org.chromium.base.Callback;

/* renamed from: os0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4134os0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f11032a;

    public C4134os0(Callback callback) {
        this.f11032a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f11032a.onResult(Arrays.asList((Bitmap) obj));
    }
}
