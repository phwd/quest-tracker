package defpackage;

import org.chromium.chrome.browser.crash.PureJavaExceptionReporter;

/* renamed from: kN0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3370kN0 extends AbstractC0823Nl {
    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        new PureJavaExceptionReporter().c((Throwable) obj);
    }
}
