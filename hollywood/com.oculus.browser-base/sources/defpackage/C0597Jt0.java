package defpackage;

import android.os.Handler;
import java.util.Objects;
import org.chromium.chrome.browser.compositor.bottombar.OverlayPanelContent;
import org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* renamed from: Jt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0597Jt0 extends WebContentsDelegateAndroid {

    /* renamed from: a  reason: collision with root package name */
    public boolean f8321a;
    public final /* synthetic */ OverlayPanelContent b;

    public C0597Jt0(OverlayPanelContent overlayPanelContent) {
        this.b = overlayPanelContent;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void enterFullscreenModeForTab(boolean z) {
        this.f8321a = true;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void exitFullscreenModeForTab() {
        this.f8321a = false;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public int getBottomControlsHeight() {
        return 0;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public int getTopControlsHeight() {
        OverlayPanelContent overlayPanelContent = this.b;
        return (int) (((float) overlayPanelContent.t) / overlayPanelContent.e.b0.I.e);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public boolean isFullscreenForTabOrPending() {
        return this.f8321a;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void loadingStateChanged(boolean z) {
        WebContents webContents = this.b.f10635a;
        if (webContents != null && webContents.d()) {
            AbstractC0292Et0 et0 = this.b.m.f7920a;
            et0.j0 = 0.0f;
            et0.h0 = true;
            et0.R();
            return;
        }
        C0231Dt0 dt0 = this.b.m;
        Objects.requireNonNull(dt0);
        new Handler().postDelayed(new RunnableC0170Ct0(dt0), 64);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public boolean shouldCreateWebContents(GURL gurl) {
        return false;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void visibleSSLStateChanged() {
        Objects.requireNonNull(this.b.l);
    }
}
