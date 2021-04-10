package defpackage;

import android.graphics.Bitmap;
import java.util.List;
import java.util.Objects;

/* renamed from: wi1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5467wi1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C5637xi1 f11564a;

    public C5467wi1(C5637xi1 xi1) {
        this.f11564a = xi1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C5637xi1 xi1 = this.f11564a;
        List list = (List) obj;
        Objects.requireNonNull(xi1);
        xi1.x((list == null || list.isEmpty()) ? null : (Bitmap) list.get(0));
    }
}
