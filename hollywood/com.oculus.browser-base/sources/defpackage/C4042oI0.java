package defpackage;

import J.N;
import android.content.Context;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileKey;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* renamed from: oI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4042oI0 extends AbstractC4798sm0 {
    public long f;

    @Override // defpackage.AbstractC0865Oe
    public void c(Context context) {
    }

    @Override // defpackage.AbstractC4798sm0
    public int e(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        return 0;
    }

    @Override // defpackage.AbstractC4798sm0
    public void f(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        this.f = N.MFWoHdRt(this, cf1.f9623a, cf1.b.getString("serialized_task_extras"), new C3700mI0(this, ne));
        if (((BrowserStartupControllerImpl) AbstractC4280pk.a()).f()) {
            N.Mh0pzgZH(this.f, this, Profile.b());
            return;
        }
        N.MgNysWkl(this.f, this, ProfileKey.a());
        ((BrowserStartupControllerImpl) AbstractC4280pk.a()).a(new C3871nI0(this));
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean g(Context context, C2046cf1 cf1) {
        return true;
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean h(Context context, C2046cf1 cf1) {
        long j = this.f;
        if (j == 0) {
            return false;
        }
        boolean MQTaGJay = N.MQTaGJay(j, this);
        long j2 = this.f;
        if (j2 != 0) {
            N.MJHnuE5A(j2, this);
            this.f = 0;
        }
        return MQTaGJay;
    }
}
