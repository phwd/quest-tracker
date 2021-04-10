package defpackage;

import android.graphics.Rect;
import java.util.Objects;

/* renamed from: zd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5962zd1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C0011Ad1 f11757a;

    public C5962zd1(C0011Ad1 ad1) {
        this.f11757a = ad1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C0011Ad1 ad1 = this.f11757a;
        Rect rect = (Rect) obj;
        Objects.requireNonNull(ad1);
        if (rect != null) {
            ad1.L.set(rect);
            ad1.e();
        }
    }
}
