package defpackage;

import J.N;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.SigninFragment;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.chrome.browser.sync.settings.ManageSyncSettings;
import org.chromium.chrome.browser.sync.settings.SyncAndServicesSettings;

/* renamed from: CV0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CV0 implements AbstractC2363eW0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ boolean f7815a;
    public final /* synthetic */ Runnable b;
    public final /* synthetic */ SigninFragment c;

    public CV0(SigninFragment signinFragment, boolean z, Runnable runnable) {
        this.c = signinFragment;
        this.f7815a = z;
        this.b = runnable;
    }

    @Override // defpackage.AbstractC2363eW0
    public void a() {
        String str;
        N.MnEYaN9w(Profile.b(), true);
        if (!this.f7815a) {
            ProfileSyncService b2 = ProfileSyncService.b();
            N.MlP9oGhJ(b2.e, b2, 0);
        } else if (N.M09VlOh_("MobileIdentityConsistency")) {
            Activity u = this.c.u();
            Bundle m1 = ManageSyncSettings.m1(true);
            String name = ManageSyncSettings.class.getName();
            Intent intent = new Intent();
            intent.setClass(u, XS0.class);
            if (!(u instanceof Activity)) {
                intent.addFlags(268435456);
                intent.addFlags(67108864);
            }
            intent.putExtra("show_fragment", name);
            intent.putExtra("show_fragment_args", m1);
            U20.q(u, intent);
        } else {
            Activity u2 = this.c.u();
            Bundle l1 = SyncAndServicesSettings.l1(true);
            String name2 = SyncAndServicesSettings.class.getName();
            Intent intent2 = new Intent();
            intent2.setClass(u2, XS0.class);
            if (!(u2 instanceof Activity)) {
                intent2.addFlags(268435456);
                intent2.addFlags(67108864);
            }
            intent2.putExtra("show_fragment", name2);
            intent2.putExtra("show_fragment_args", l1);
            U20.q(u2, intent2);
        }
        SigninFragment signinFragment = this.c;
        int i = signinFragment.W0;
        if (i != 0) {
            if (i == 1) {
                str = "Signin.SigninCompletedAccessPoint.WithDefault";
            } else if (i == 2) {
                str = "Signin.SigninCompletedAccessPoint.NotDefault";
            } else if (i == 3) {
                str = "Signin.SigninCompletedAccessPoint.NewAccountNoExistingAccount";
            }
            AbstractC3364kK0.g(str, signinFragment.V0, 34);
        }
        Activity u3 = this.c.u();
        if (u3 != null) {
            u3.finish();
        }
        this.b.run();
    }

    @Override // defpackage.AbstractC2363eW0
    public void b() {
        this.b.run();
    }
}
