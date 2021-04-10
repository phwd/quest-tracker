package org.chromium.chrome.browser.tab;

import J.N;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.view.KeyEvent;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.BuildInfo;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.components.find_in_page.FindMatchRectsDetails;
import org.chromium.components.find_in_page.FindNotificationDetails;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class TabWebContentsDelegateAndroidImpl extends AbstractC0133Cd1 {

    /* renamed from: a  reason: collision with root package name */
    public final TabImpl f10774a;
    public final AbstractC0133Cd1 b;
    public final Handler c = new Handler();
    public final Runnable d = new RunnableC0194Dd1(this);

    public TabWebContentsDelegateAndroidImpl(TabImpl tabImpl, AbstractC0133Cd1 cd1) {
        this.f10774a = tabImpl;
        this.b = cd1;
    }

    public static FindMatchRectsDetails createFindMatchRectsDetails(int i, int i2, RectF rectF) {
        return new FindMatchRectsDetails(i, i2, rectF);
    }

    public static FindNotificationDetails createFindNotificationDetails(int i, Rect rect, int i2, boolean z) {
        return new FindNotificationDetails(i, rect, i2, z);
    }

    public static Rect createRect(int i, int i2, int i3, int i4) {
        return new Rect(i, i2, i3, i4);
    }

    public static RectF createRectF(float f, float f2, float f3, float f4) {
        return new RectF(f, f2, f3, f4);
    }

    public static void setMatchRectByIndex(FindMatchRectsDetails findMatchRectsDetails, int i, RectF rectF) {
        findMatchRectsDetails.b[i] = rectF;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public int a() {
        return this.b.a();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void activateContents() {
        this.b.activateContents();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public boolean addMessageToConsole(int i, String str, int i2, String str2) {
        return !BuildInfo.a();
    }

    @Override // defpackage.AbstractC0133Cd1
    public boolean addNewContents(WebContents webContents, WebContents webContents2, int i, Rect rect, boolean z) {
        return this.b.addNewContents(webContents, webContents2, i, rect, z);
    }

    @Override // defpackage.AbstractC0133Cd1
    public boolean canShowAppBanners() {
        return this.b.canShowAppBanners();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void closeContents() {
        this.c.removeCallbacks(this.d);
        this.c.post(this.d);
        this.b.closeContents();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void enterFullscreenModeForTab(boolean z) {
        this.b.enterFullscreenModeForTab(z);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void exitFullscreenModeForTab() {
        this.b.exitFullscreenModeForTab();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void fullscreenStateChangedForTab(boolean z) {
        this.b.enterFullscreenModeForTab(z);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public int getBottomControlsHeight() {
        return this.b.getBottomControlsHeight();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public int getBottomControlsMinHeight() {
        return this.b.getBottomControlsMinHeight();
    }

    @Override // defpackage.AbstractC0133Cd1
    public String getManifestScope() {
        return this.b.getManifestScope();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public int getTopControlsHeight() {
        return this.b.getTopControlsHeight();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public int getTopControlsMinHeight() {
        return this.b.getTopControlsMinHeight();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void handleKeyboardEvent(KeyEvent keyEvent) {
        this.b.handleKeyboardEvent(keyEvent);
    }

    @Override // defpackage.AbstractC0133Cd1
    public boolean isCustomTab() {
        return this.b.isCustomTab();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public boolean isFullscreenForTabOrPending() {
        return this.b.isFullscreenForTabOrPending();
    }

    @Override // defpackage.AbstractC0133Cd1
    public boolean isInstalledWebappDelegateGeolocation() {
        return this.b.isInstalledWebappDelegateGeolocation();
    }

    @Override // defpackage.AbstractC0133Cd1
    public boolean isNightModeEnabled() {
        return this.b.isNightModeEnabled();
    }

    @Override // defpackage.AbstractC0133Cd1
    public boolean isPictureInPictureEnabled() {
        return this.b.isPictureInPictureEnabled();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void loadingStateChanged(boolean z) {
        WebContents webContents = this.f10774a.L;
        if (!(webContents != null && webContents.d())) {
            TabImpl tabImpl = this.f10774a;
            boolean z2 = tabImpl.Y;
            tabImpl.Y = false;
            Iterator it = tabImpl.P.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (!uq0.hasNext()) {
                    break;
                }
                ((AbstractC1404Xa1) uq0.next()).F(tabImpl, z2);
            }
        } else {
            TabImpl tabImpl2 = this.f10774a;
            if (z) {
                tabImpl2.Y = true;
            }
            Iterator it2 = tabImpl2.P.iterator();
            while (true) {
                C1261Uq0 uq02 = (C1261Uq0) it2;
                if (!uq02.hasNext()) {
                    break;
                }
                ((AbstractC1404Xa1) uq02.next()).E(tabImpl2, z);
            }
        }
        this.b.loadingStateChanged(z);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void navigationStateChanged(int i) {
        if ((i & 2) != 0) {
            Context applicationContext = ContextUtils.getApplicationContext();
            int id = this.f10774a.getId();
            TabImpl tabImpl = this.f10774a;
            C4941td0.i(applicationContext, id, tabImpl.L, tabImpl.getUrl());
        }
        if ((i & 8) != 0) {
            this.f10774a.n0();
        }
        if ((i & 1) != 0) {
            C1261Uq0 Y = this.f10774a.Y();
            while (Y.hasNext()) {
                ((AbstractC1404Xa1) Y.next()).S(this.f10774a);
            }
        }
        this.b.navigationStateChanged(i);
    }

    public final void onFindMatchRectsAvailable(FindMatchRectsDetails findMatchRectsDetails) {
        C1261Uq0 Y = this.f10774a.Y();
        while (Y.hasNext()) {
            ((AbstractC1404Xa1) Y.next()).y(findMatchRectsDetails);
        }
    }

    public final void onFindResultAvailable(FindNotificationDetails findNotificationDetails) {
        C1261Uq0 Y = this.f10774a.Y();
        while (Y.hasNext()) {
            ((AbstractC1404Xa1) Y.next()).z(findNotificationDetails);
        }
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void onUpdateUrl(GURL gurl) {
        C1261Uq0 Y = this.f10774a.Y();
        while (Y.hasNext()) {
            ((AbstractC1404Xa1) Y.next()).R(this.f10774a, gurl);
        }
        this.b.onUpdateUrl(gurl);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void openNewTab(GURL gurl, String str, ResourceRequestBody resourceRequestBody, int i, boolean z) {
        this.b.openNewTab(gurl, str, resourceRequestBody, i, z);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void rendererResponsive() {
        WebContents webContents = this.f10774a.L;
        if (webContents != null) {
            N.M8ARKEz4(webContents);
        }
        this.f10774a.Z(true);
        this.b.rendererResponsive();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void rendererUnresponsive() {
        WebContents webContents = this.f10774a.L;
        if (webContents != null) {
            N.MsGvyS6g(webContents);
        }
        this.f10774a.Z(false);
        this.b.rendererUnresponsive();
    }

    @Override // defpackage.AbstractC0133Cd1
    public void setOverlayMode(boolean z) {
        this.b.setOverlayMode(z);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public boolean shouldAnimateBrowserControlsHeightChanges() {
        return this.b.shouldAnimateBrowserControlsHeightChanges();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public boolean shouldBlockMediaRequest(GURL gurl) {
        return this.b.shouldBlockMediaRequest(gurl);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public boolean shouldCreateWebContents(GURL gurl) {
        return this.b.shouldCreateWebContents(gurl);
    }

    @Override // defpackage.AbstractC0133Cd1
    public boolean shouldEnableEmbeddedMediaExperience() {
        return this.b.shouldEnableEmbeddedMediaExperience();
    }

    @Override // defpackage.AbstractC0133Cd1
    public boolean shouldResumeRequestsForCreatedWindow() {
        return this.b.shouldResumeRequestsForCreatedWindow();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void showRepostFormWarningDialog() {
        this.b.showRepostFormWarningDialog();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public boolean takeFocus(boolean z) {
        return this.b.takeFocus(z);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void visibleSSLStateChanged() {
        Objects.requireNonNull(AppHooks.get());
        N.MX4bLIGx(this.f10774a.L);
        ContextUtils.getApplicationContext();
        C1261Uq0 Y = this.f10774a.Y();
        while (Y.hasNext()) {
            ((AbstractC1404Xa1) Y.next()).O(this.f10774a);
        }
        this.b.visibleSSLStateChanged();
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void webContentsCreated(WebContents webContents, long j, long j2, String str, GURL gurl, WebContents webContents2) {
        this.b.webContentsCreated(webContents, j, j2, str, gurl, webContents2);
    }
}
