package defpackage;

import org.chromium.chrome.browser.compositor.CompositorViewHolder;

/* renamed from: dk1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2232dk1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final N30 f9802a;

    public C2232dk1(N30 n30) {
        this.f9802a = n30;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        N30 n30 = this.f9802a;
        Runnable runnable = (Runnable) obj;
        if (n30 != null) {
            M30 m30 = n30.f8524a;
            if (m30 != null) {
                CompositorViewHolder compositorViewHolder = (CompositorViewHolder) m30;
                if (compositorViewHolder.P <= 0) {
                    runnable.run();
                } else if (!compositorViewHolder.Q.contains(runnable)) {
                    compositorViewHolder.Q.add(runnable);
                }
            } else {
                runnable.run();
            }
        } else {
            runnable.run();
        }
    }
}
