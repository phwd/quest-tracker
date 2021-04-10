package defpackage;

import org.chromium.base.Callback;
import org.chromium.content_public.browser.LoadUrlParams;

/* renamed from: Sr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1141Sr0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f8920a;

    public C1141Sr0(Callback callback) {
        this.f8920a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f8920a.onResult((LoadUrlParams) obj);
    }
}
