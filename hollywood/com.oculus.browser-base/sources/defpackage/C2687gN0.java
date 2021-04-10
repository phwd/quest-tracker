package defpackage;

import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.Objects;

/* renamed from: gN0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2687gN0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final GN0 f9992a;

    public C2687gN0(GN0 gn0) {
        this.f9992a = gn0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        GN0 gn0 = this.f9992a;
        Objects.requireNonNull(gn0);
        gn0.b0 = new View$OnClickListenerC5098uY0(gn0.F, (ViewGroup) ((View) obj).findViewById(R.id.bottom_sheet_snackbar_container), gn0.F.b0);
    }
}
