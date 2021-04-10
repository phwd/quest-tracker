package defpackage;

import android.app.Activity;
import android.app.Dialog;
import org.chromium.base.ApplicationStatus;

/* renamed from: K4  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class K4 extends Dialog implements Z9 {
    public K4(Activity activity, int i) {
        super(activity, i);
        ApplicationStatus.g(this, activity);
        setOwnerActivity(activity);
    }

    public void dismiss() {
        super.dismiss();
        ApplicationStatus.h(this);
    }

    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        if (i == 6) {
            dismiss();
        }
    }
}
