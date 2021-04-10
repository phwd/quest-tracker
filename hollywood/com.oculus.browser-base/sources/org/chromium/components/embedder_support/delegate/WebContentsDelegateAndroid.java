package org.chromium.components.embedder_support.delegate;

import android.view.KeyEvent;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WebContentsDelegateAndroid {
    public int a() {
        return 0;
    }

    public void activateContents() {
    }

    public boolean addMessageToConsole(int i, String str, int i2, String str2) {
        return false;
    }

    public void closeContents() {
    }

    public void enterFullscreenModeForTab(boolean z) {
    }

    public void exitFullscreenModeForTab() {
    }

    public void fullscreenStateChangedForTab(boolean z) {
    }

    public int getBottomControlsHeight() {
        return 0;
    }

    public int getBottomControlsMinHeight() {
        return 0;
    }

    public final int getDisplayModeChecked() {
        return a();
    }

    public int getTopControlsHeight() {
        return 0;
    }

    public int getTopControlsMinHeight() {
        return 0;
    }

    public void handleKeyboardEvent(KeyEvent keyEvent) {
    }

    public boolean isFullscreenForTabOrPending() {
        return false;
    }

    public void loadingStateChanged(boolean z) {
    }

    public void navigationStateChanged(int i) {
    }

    public void onUpdateUrl(GURL gurl) {
    }

    public void openNewTab(GURL gurl, String str, ResourceRequestBody resourceRequestBody, int i, boolean z) {
    }

    public void rendererResponsive() {
    }

    public void rendererUnresponsive() {
    }

    public boolean shouldAnimateBrowserControlsHeightChanges() {
        return false;
    }

    public boolean shouldBlockMediaRequest(GURL gurl) {
        return false;
    }

    public boolean shouldCreateWebContents(GURL gurl) {
        return true;
    }

    public void showRepostFormWarningDialog() {
    }

    public boolean takeFocus(boolean z) {
        return false;
    }

    public void visibleSSLStateChanged() {
    }

    public void webContentsCreated(WebContents webContents, long j, long j2, String str, GURL gurl, WebContents webContents2) {
    }
}
