package defpackage;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.sync.ProfileSyncService;

/* renamed from: d51  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2125d51 implements AbstractC3526lH0 {
    public final AbstractC0711Lp0 F = new C0771Mp0(ContextUtils.getApplicationContext());
    public final ProfileSyncService G = ProfileSyncService.b();
    public boolean H;

    public final void a() {
        ((C0771Mp0) this.F).b.cancel(1);
        this.H = false;
    }

    public final void b(int i, Intent intent) {
        CB0 cb0 = new CB0(PendingIntent.getActivity(ContextUtils.getApplicationContext(), 0, intent, 134217728), 134217728);
        Context applicationContext = ContextUtils.getApplicationContext();
        String string = applicationContext.getString(R.string.f61900_resource_name_obfuscated_RES_2131953507);
        String string2 = applicationContext.getString(i);
        C3444kq0 z = AbstractC3786mq0.b(true, "browser", null, new C0832Np0(8, null, 1)).x(true).B(cb0).H(string).F(string2).A(R.drawable.f29770_resource_name_obfuscated_RES_2131231017).C(string2).q(true).r("Sync").z(string2);
        ((C0771Mp0) this.F).a(z);
        AbstractC3102iq0.f10166a.b(8, z.f10306a);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0082  */
    @Override // defpackage.AbstractC3526lH0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m() {
        /*
        // Method dump skipped, instructions count: 312
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2125d51.m():void");
    }
}
