package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* renamed from: Lz1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Lz1 extends Kz1 {
    public Lz1(Context context) {
        super(new Rz1("AppUpdateListenerRegistry"), new IntentFilter("com.google.android.play.core.install.ACTION_INSTALL_STATUS"), context);
    }

    @Override // defpackage.Kz1
    public final void c(Context context, Intent intent) {
        if (!context.getPackageName().equals(intent.getStringExtra("package.name"))) {
            this.f8398a.a(3, "ListenerRegistryBroadcastReceiver received broadcast for third party app: %s", new Object[]{intent.getStringExtra("package.name")});
            return;
        }
        this.f8398a.a(3, "List of extras in received intent:", new Object[0]);
        for (String str : intent.getExtras().keySet()) {
            this.f8398a.a(3, "Key: %s; value: %s", new Object[]{str, intent.getExtras().get(str)});
        }
        Rz1 rz1 = this.f8398a;
        rz1.a(3, "List of extras in received intent needed by fromUpdateIntent:", new Object[0]);
        rz1.a(3, "Key: %s; value: %s", new Object[]{"install.status", Integer.valueOf(intent.getIntExtra("install.status", 0))});
        rz1.a(3, "Key: %s; value: %s", new Object[]{"error.code", Integer.valueOf(intent.getIntExtra("error.code", 0))});
        Iz1 iz1 = new Iz1(intent.getIntExtra("install.status", 0), intent.getIntExtra("error.code", 0), intent.getStringExtra("package.name"));
        this.f8398a.a(3, "ListenerRegistryBroadcastReceiver.onReceive: %s", new Object[]{iz1});
        d(iz1);
    }
}
