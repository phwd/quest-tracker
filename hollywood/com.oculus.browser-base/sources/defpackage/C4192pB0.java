package defpackage;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.payments.ui.PaymentRequestHeader;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback;

/* renamed from: pB0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4192pB0 implements FaviconHelper$FaviconImageCallback {

    /* renamed from: a  reason: collision with root package name */
    public final AB0 f11055a;
    public final C3542lO b;

    public C4192pB0(AB0 ab0, C3542lO lOVar) {
        this.f11055a = ab0;
        this.b = lOVar;
    }

    @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback
    public void onFaviconAvailable(Bitmap bitmap, String str) {
        EA0 ea0;
        AbstractC1797bA0 ba0;
        AB0 ab0 = this.f11055a;
        C3542lO lOVar = this.b;
        if (!(bitmap != null || (ea0 = ((C0289Es) ab0.m).f7982a) == null || (ba0 = ea0.A) == null)) {
            ((C4018oA0) ba0).p0();
        }
        TA0 ta0 = ab0.w;
        if (!(ta0 == null || bitmap == null)) {
            ((ImageView) ((PaymentRequestHeader) ta0.P.findViewById(R.id.header)).findViewById(R.id.icon_view)).setImageBitmap(bitmap);
        }
        lOVar.b();
    }
}
