package defpackage;

import android.app.ActivityManager;
import java.util.Iterator;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.media.PictureInPictureActivity;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: ZC0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZC0 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public PictureInPictureActivity f9328a;
    public int b = 1;

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void q(Tab tab) {
        this.b = 2;
        PictureInPictureActivity pictureInPictureActivity = this.f9328a;
        if (pictureInPictureActivity != null) {
            pictureInPictureActivity.finish();
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        boolean z;
        if (!tab.x()) {
            Tab tab2 = PictureInPictureActivity.p0;
            Iterator<ActivityManager.AppTask> it = ((ActivityManager) ContextUtils.getApplicationContext().getSystemService("activity")).getAppTasks().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getTaskInfo().id == PictureInPictureActivity.q0) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                return;
            }
        }
        this.b = 2;
        PictureInPictureActivity pictureInPictureActivity = this.f9328a;
        if (pictureInPictureActivity != null) {
            pictureInPictureActivity.finish();
        }
    }
}
