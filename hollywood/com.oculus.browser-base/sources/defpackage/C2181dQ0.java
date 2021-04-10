package defpackage;

import android.graphics.Rect;
import org.chromium.content_public.browser.WebContents;

/* renamed from: dQ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2181dQ0 extends AbstractC0133Cd1 {
    public C2181dQ0(C2351eQ0 eq0) {
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public int a() {
        return 1;
    }

    @Override // defpackage.AbstractC0133Cd1
    public boolean addNewContents(WebContents webContents, WebContents webContents2, int i, Rect rect, boolean z) {
        return false;
    }

    @Override // defpackage.AbstractC0133Cd1
    public void setOverlayMode(boolean z) {
    }

    @Override // defpackage.AbstractC0133Cd1
    public boolean shouldResumeRequestsForCreatedWindow() {
        return false;
    }
}
