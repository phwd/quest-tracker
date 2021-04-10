package defpackage;

import java.lang.ref.WeakReference;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* renamed from: zx1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC6022zx1 {
    public WeakReference F;

    public AbstractC6022zx1(WebContents webContents) {
        this.F = new WeakReference(webContents);
        webContents.c0(this);
    }

    public void a(WindowAndroid windowAndroid) {
    }

    public void destroy() {
        WeakReference weakReference = this.F;
        if (weakReference != null) {
            WebContents webContents = (WebContents) weakReference.get();
            this.F = null;
            if (webContents != null) {
                webContents.Q(this);
            }
        }
    }

    public void didChangeThemeColor() {
    }

    public void didChangeVisibleSecurityState() {
    }

    public void didFailLoad(boolean z, int i, GURL gurl) {
    }

    public void didFinishLoad(long j, GURL gurl, boolean z, boolean z2) {
    }

    public void didFinishNavigation(NavigationHandle navigationHandle) {
    }

    public void didFirstVisuallyNonEmptyPaint() {
    }

    public void didRedirectNavigation(NavigationHandle navigationHandle) {
    }

    public void didStartLoading(GURL gurl) {
    }

    public void didStartNavigation(NavigationHandle navigationHandle) {
    }

    public void didStopLoading(GURL gurl, boolean z) {
    }

    public void documentAvailableInMainFrame() {
    }

    public void documentLoadedInFrame(long j, boolean z) {
    }

    public void hasEffectivelyFullscreenVideoChange(boolean z) {
    }

    public void loadProgressChanged(float f) {
    }

    public void navigationEntriesChanged() {
    }

    public void navigationEntriesDeleted() {
    }

    public void navigationEntryCommitted() {
    }

    public void onWebContentsFocused() {
    }

    public void onWebContentsLostFocus() {
    }

    public void renderFrameCreated(int i, int i2) {
    }

    public void renderFrameDeleted(int i, int i2) {
    }

    public void renderProcessGone(boolean z) {
    }

    public void renderViewReady() {
    }

    public void titleWasSet(String str) {
    }

    public void viewportFitChanged(int i) {
    }

    public void wasHidden() {
    }

    public void wasShown() {
    }

    public AbstractC6022zx1() {
    }
}
