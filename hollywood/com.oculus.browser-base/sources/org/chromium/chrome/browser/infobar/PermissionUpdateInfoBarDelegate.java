package org.chromium.chrome.browser.infobar;

import J.N;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PermissionUpdateInfoBarDelegate implements HB0 {

    /* renamed from: a  reason: collision with root package name */
    public final WebContents f10684a;
    public final String[] b;
    public long c;
    public Z9 d;

    public PermissionUpdateInfoBarDelegate(long j, WebContents webContents, String[] strArr) {
        this.c = j;
        this.b = strArr;
        this.f10684a = webContents;
    }

    public static PermissionUpdateInfoBarDelegate create(long j, WebContents webContents, String[] strArr) {
        return new PermissionUpdateInfoBarDelegate(j, webContents, strArr);
    }

    public final void a() {
        WindowAndroid I = this.f10684a.I();
        boolean z = false;
        int i = 0;
        if (I != null) {
            boolean z2 = true;
            while (true) {
                String[] strArr = this.b;
                if (i >= strArr.length) {
                    break;
                }
                z2 &= I.hasPermission(strArr[i]);
                i++;
            }
            z = z2;
        }
        long j = this.c;
        if (j != 0) {
            N.M7uW1If6(j, this, z);
        }
    }

    @Override // defpackage.HB0
    public void b(String[] strArr, int[] iArr) {
        a();
    }

    public final void onNativeDestroyed() {
        this.c = 0;
        Z9 z9 = this.d;
        if (z9 != null) {
            ApplicationStatus.h(z9);
            this.d = null;
        }
    }

    public final void requestPermissions() {
        WindowAndroid I = this.f10684a.I();
        if (I == null) {
            N.M7uW1If6(this.c, this, false);
            return;
        }
        int i = 0;
        boolean z = true;
        while (true) {
            String[] strArr = this.b;
            if (i >= strArr.length) {
                break;
            }
            z &= I.hasPermission(strArr[i]) || I.canRequestPermission(this.b[i]);
            i++;
        }
        Activity activity = (Activity) I.s0().get();
        if (z) {
            I.i(this.b, this);
        } else if (activity == null) {
            N.M7uW1If6(this.c, this, false);
        } else {
            UB0 ub0 = new UB0(this);
            this.d = ub0;
            ApplicationStatus.g(ub0, activity);
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            StringBuilder i2 = AbstractC2531fV.i("package:");
            i2.append(ContextUtils.getApplicationContext().getPackageName());
            intent.setData(Uri.parse(i2.toString()));
            intent.setFlags(268435456);
            activity.startActivity(intent);
        }
    }
}
