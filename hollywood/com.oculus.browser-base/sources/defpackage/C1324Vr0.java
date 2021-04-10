package defpackage;

import android.app.Activity;
import org.chromium.base.ApplicationStatus;

/* renamed from: Vr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1324Vr0 implements Z9 {
    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        if (i == 6) {
            ((C1568Zr0) AbstractC2254ds0.b.remove(activity)).destroy();
            ApplicationStatus.h(this);
        }
    }
}
