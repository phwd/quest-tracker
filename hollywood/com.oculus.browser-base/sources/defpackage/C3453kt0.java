package defpackage;

import android.app.Activity;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;

/* renamed from: kt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3453kt0 implements Z9 {
    public final /* synthetic */ C3795mt0 F;

    public C3453kt0(C3795mt0 mt0) {
        this.F = mt0;
    }

    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        if (i != 4) {
            C3795mt0 mt0 = this.F;
            Objects.requireNonNull(mt0);
            Object obj = ThreadUtils.f10596a;
            mt0.b.remove(activity);
            if (mt0.b.isEmpty()) {
                ApplicationStatus.h(mt0.c);
                mt0.f10454a.b(new RunnableC3282jt0(mt0, activity));
            }
        }
    }
}
