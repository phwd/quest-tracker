package com.oculus.clay.app;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.oculus.clay.app.g;

public final class f implements g.a {
    private final ClayAppActivity a;

    public f(ClayAppActivity clayAppActivity) {
        new Handler(Looper.getMainLooper());
        this.a = clayAppActivity;
    }

    private void b(Intent intent) {
        Intent intent2 = new Intent(intent);
        intent2.setAction("com.oculus.vrshell.intent.action.LAUNCH");
        intent2.setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.ShellOverlayService"));
        intent2.putExtra("intent_atop_shellenv", "true");
        intent2.putExtra("intent_action", "com.oculus.vrshell.intent.action.LAUNCH");
        this.a.startService(intent2);
    }

    @Override // com.oculus.clay.app.g.a
    public final void a() {
    }

    @Override // com.oculus.clay.app.g.a
    public final void a(Intent intent) {
    }

    public final void b() {
        Intent intent = new Intent();
        intent.setAction("com.oculus.vrshell.OVERLAY_COMMAND");
        intent.putExtra("command", "forceOverlayShellEnvReset");
        intent.setPackage("com.oculus.vrshell");
        this.a.sendBroadcast(intent);
    }

    public final void c() {
        b(this.a.getIntent());
    }

    @Override // com.oculus.clay.app.g.a
    public final boolean e() {
        return true;
    }

    @Override // com.oculus.clay.app.g.a
    public final void f() {
        b(this.a.getIntent());
    }

    @Override // com.oculus.clay.app.g.a
    public final void g() {
    }
}
