package defpackage;

import android.graphics.Rect;
import org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Cd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0133Cd1 extends WebContentsDelegateAndroid {
    public abstract boolean addNewContents(WebContents webContents, WebContents webContents2, int i, Rect rect, boolean z);

    public boolean canShowAppBanners() {
        return !(this instanceof C2181dQ0);
    }

    public String getManifestScope() {
        return null;
    }

    public boolean isCustomTab() {
        return false;
    }

    public boolean isInstalledWebappDelegateGeolocation() {
        return false;
    }

    public boolean isNightModeEnabled() {
        return false;
    }

    public boolean isPictureInPictureEnabled() {
        return false;
    }

    public abstract void setOverlayMode(boolean z);

    public boolean shouldEnableEmbeddedMediaExperience() {
        return false;
    }

    public abstract boolean shouldResumeRequestsForCreatedWindow();
}
