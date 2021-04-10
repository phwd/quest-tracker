package defpackage;

import org.chromium.chrome.browser.feed.v2.FeedStreamSurface;

/* renamed from: HO  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class HO extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final FeedStreamSurface f8155a;

    public HO(FeedStreamSurface feedStreamSurface) {
        this.f8155a = feedStreamSurface;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Boolean bool = (Boolean) obj;
        this.f8155a.a();
    }
}
