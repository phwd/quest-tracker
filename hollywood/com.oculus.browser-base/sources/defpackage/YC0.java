package defpackage;

import org.chromium.chrome.browser.media.PictureInPictureActivity;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: YC0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YC0 extends AbstractC1180Th0 {
    public final /* synthetic */ PictureInPictureActivity b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public YC0(PictureInPictureActivity pictureInPictureActivity, AbstractC5123uh0 uh0) {
        super(uh0);
        this.b = pictureInPictureActivity;
    }

    @Override // defpackage.AbstractC1180Th0
    public void f(boolean z, boolean z2) {
        PictureInPictureActivity pictureInPictureActivity = this.b;
        Tab tab = PictureInPictureActivity.p0;
        pictureInPictureActivity.setPictureInPictureParams(pictureInPictureActivity.z0());
    }
}
