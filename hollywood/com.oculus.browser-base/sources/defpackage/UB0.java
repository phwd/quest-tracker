package defpackage;

import J.N;
import android.app.Activity;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.infobar.PermissionUpdateInfoBarDelegate;

/* renamed from: UB0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UB0 implements Z9 {
    public final /* synthetic */ PermissionUpdateInfoBarDelegate F;

    public UB0(PermissionUpdateInfoBarDelegate permissionUpdateInfoBarDelegate) {
        this.F = permissionUpdateInfoBarDelegate;
    }

    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        if (i == 6) {
            ApplicationStatus.h(this);
            PermissionUpdateInfoBarDelegate permissionUpdateInfoBarDelegate = this.F;
            permissionUpdateInfoBarDelegate.d = null;
            N.M7uW1If6(permissionUpdateInfoBarDelegate.c, permissionUpdateInfoBarDelegate, false);
        } else if (i == 3) {
            ApplicationStatus.h(this);
            PermissionUpdateInfoBarDelegate permissionUpdateInfoBarDelegate2 = this.F;
            permissionUpdateInfoBarDelegate2.d = null;
            permissionUpdateInfoBarDelegate2.a();
        }
    }
}
