package defpackage;

import J.N;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.chrome.browser.sync.settings.ManageSyncSettings;
import org.chromium.chrome.browser.sync.settings.SyncAndServicesSettings;

/* renamed from: WQ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WQ implements AbstractC2363eW0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ boolean f9146a;
    public final /* synthetic */ Activity b;

    public WQ(boolean z, Activity activity) {
        this.f9146a = z;
        this.b = activity;
    }

    @Override // defpackage.AbstractC2363eW0
    public void a() {
        N.MnEYaN9w(Profile.b(), true);
        if (this.f9146a) {
            Activity activity = this.b;
            if (N.M09VlOh_("MobileIdentityConsistency")) {
                Bundle m1 = ManageSyncSettings.m1(true);
                String name = ManageSyncSettings.class.getName();
                Intent intent = new Intent();
                intent.setClass(activity, XS0.class);
                if (!(activity instanceof Activity)) {
                    intent.addFlags(268435456);
                    intent.addFlags(67108864);
                }
                intent.putExtra("show_fragment", name);
                intent.putExtra("show_fragment_args", m1);
                U20.q(activity, intent);
            } else {
                Bundle l1 = SyncAndServicesSettings.l1(true);
                String name2 = SyncAndServicesSettings.class.getName();
                Intent intent2 = new Intent();
                intent2.setClass(activity, XS0.class);
                if (!(activity instanceof Activity)) {
                    intent2.addFlags(268435456);
                    intent2.addFlags(67108864);
                }
                intent2.putExtra("show_fragment", name2);
                intent2.putExtra("show_fragment_args", l1);
                U20.q(activity, intent2);
            }
        } else {
            ProfileSyncService b2 = ProfileSyncService.b();
            N.MlP9oGhJ(b2.e, b2, 0);
        }
        XQ.a(true);
    }

    @Override // defpackage.AbstractC2363eW0
    public void b() {
        XQ.a(true);
    }
}
