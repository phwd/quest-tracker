package defpackage;

import android.app.Activity;
import org.chromium.base.ApplicationStatus;

/* renamed from: T9  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class T9 implements AbstractC2200da {
    @Override // defpackage.AbstractC2200da
    public void e(Activity activity, boolean z) {
        int e;
        if (z && activity != ApplicationStatus.e && (e = ApplicationStatus.e(activity)) != 6 && e != 5) {
            ApplicationStatus.e = activity;
        }
    }
}
