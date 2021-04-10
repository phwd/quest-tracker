package defpackage;

import org.chromium.chrome.browser.compositor.CompositorViewHolder;

/* renamed from: Pw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0967Pw extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final CompositorViewHolder f8722a;

    public C0967Pw(CompositorViewHolder compositorViewHolder) {
        this.f8722a = compositorViewHolder;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Integer num = (Integer) obj;
        this.f8722a.z();
    }
}
