package org.chromium.content.browser.webcontents;

import J.N;
import org.chromium.base.ThreadUtils;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WebContentsObserverProxy extends AbstractC6022zx1 {
    public long G;
    public final C1322Vq0 H;
    public final C1261Uq0 I;

    public WebContentsObserverProxy(WebContentsImpl webContentsImpl) {
        Object obj = ThreadUtils.f10596a;
        this.G = N.MTpUzW91(this, webContentsImpl);
        C1322Vq0 vq0 = new C1322Vq0();
        this.H = vq0;
        this.I = vq0.e();
    }

    @Override // defpackage.AbstractC6022zx1
    public void a(WindowAndroid windowAndroid) {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).a(windowAndroid);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void destroy() {
        Object obj = ThreadUtils.f10596a;
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).destroy();
        }
        this.H.clear();
        long j = this.G;
        if (j != 0) {
            N.M7giG0Ri(j, this);
            this.G = 0;
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didChangeThemeColor() {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).didChangeThemeColor();
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didChangeVisibleSecurityState() {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).didChangeVisibleSecurityState();
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFailLoad(boolean z, int i, GURL gurl) {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).didFailLoad(z, i, gurl);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFinishLoad(long j, GURL gurl, boolean z, boolean z2) {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).didFinishLoad(j, gurl, z, z2);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFinishNavigation(NavigationHandle navigationHandle) {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).didFinishNavigation(navigationHandle);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFirstVisuallyNonEmptyPaint() {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).didFirstVisuallyNonEmptyPaint();
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didRedirectNavigation(NavigationHandle navigationHandle) {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).didRedirectNavigation(navigationHandle);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didStartLoading(GURL gurl) {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).didStartLoading(gurl);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didStartNavigation(NavigationHandle navigationHandle) {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).didStartNavigation(navigationHandle);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void didStopLoading(GURL gurl, boolean z) {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).didStopLoading(gurl, z);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void documentAvailableInMainFrame() {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).documentAvailableInMainFrame();
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void documentLoadedInFrame(long j, boolean z) {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).documentLoadedInFrame(j, z);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void hasEffectivelyFullscreenVideoChange(boolean z) {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).hasEffectivelyFullscreenVideoChange(z);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void loadProgressChanged(float f) {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).loadProgressChanged(f);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void navigationEntriesChanged() {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).navigationEntriesChanged();
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void navigationEntriesDeleted() {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).navigationEntriesDeleted();
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void navigationEntryCommitted() {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).navigationEntryCommitted();
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void onWebContentsFocused() {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).onWebContentsFocused();
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void onWebContentsLostFocus() {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).onWebContentsLostFocus();
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void renderFrameCreated(int i, int i2) {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).renderFrameCreated(i, i2);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void renderFrameDeleted(int i, int i2) {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).renderFrameDeleted(i, i2);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void renderProcessGone(boolean z) {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).renderProcessGone(z);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void renderViewReady() {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).renderViewReady();
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void titleWasSet(String str) {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).titleWasSet(str);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void viewportFitChanged(int i) {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).viewportFitChanged(i);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void wasHidden() {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).wasHidden();
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void wasShown() {
        this.I.b();
        while (this.I.hasNext()) {
            ((AbstractC6022zx1) this.I.next()).wasShown();
        }
    }
}
