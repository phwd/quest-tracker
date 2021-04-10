package defpackage;

import android.content.Intent;
import java.util.Objects;
import org.chromium.base.PackageManagerUtils;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: JD  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class JD implements Ky1 {

    /* renamed from: a  reason: collision with root package name */
    public final KD f8278a;

    public JD(KD kd) {
        this.f8278a = kd;
    }

    @Override // defpackage.Ky1
    public void a(WindowAndroid windowAndroid, int i, Intent intent) {
        int i2 = this.f8278a.b;
        if (ID.b == null) {
            ID.b = new ID();
        }
        ID id = ID.b;
        Objects.requireNonNull(id);
        AbstractC3364kK0.g(i2 == 0 ? "Android.DefaultBrowserPromo.Outcome.NoDefault" : "Android.DefaultBrowserPromo.Outcome.OtherDefault", id.a(PackageManagerUtils.e()), 3);
    }
}
