package defpackage;

import android.view.View;
import org.chromium.chrome.browser.ntp.IncognitoDescriptionView;

/* renamed from: e00  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2279e00 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final IncognitoDescriptionView f9824a;

    public C2279e00(IncognitoDescriptionView incognitoDescriptionView) {
        this.f9824a = incognitoDescriptionView;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        View view = (View) obj;
        this.f9824a.c();
    }
}
