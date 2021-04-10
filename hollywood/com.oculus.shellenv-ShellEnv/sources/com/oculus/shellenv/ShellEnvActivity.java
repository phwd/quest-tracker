package com.oculus.shellenv;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Trace;
import android.util.Log;
import com.oculus.clay.app.ClayAppActivity;
import com.oculus.clay.app.IPCDebugCommandHandler;
import com.oculus.clay.app.g;

public class ShellEnvActivity extends ClayAppActivity {
    private static final boolean a;
    private static com.oculus.clay.app.a b = new com.oculus.clay.app.a("Clay", "ShellEnvActivity");
    private g c = null;
    private f d = null;
    private Handler e = new Handler(Looper.getMainLooper());
    private boolean f = a;
    private a g;

    class a extends BroadcastReceiver {
        private a() {
            ShellEnvActivity.this = r1;
        }

        /* synthetic */ a(ShellEnvActivity shellEnvActivity, byte b) {
            this();
        }

        public final void onReceive(Context context, Intent intent) {
            ShellEnvActivity.this.b((ShellEnvActivity) intent);
        }
    }

    static {
        ClayAppActivity.a(Boolean.valueOf(a), "ShellEnv");
        b.a("static");
        try {
            b.a("load libshellenv.so");
            try {
                System.loadLibrary("shellenv");
                Trace.endSection();
            } finally {
                Trace.endSection();
            }
        } finally {
            Trace.endSection();
        }
    }

    static /* synthetic */ Intent a(ShellEnvActivity shellEnvActivity) {
        Intent intent = new Intent(shellEnvActivity.getIntent());
        intent.putExtra("intent_pkg", "com.oculus.vrshell");
        intent.removeExtra("intent_data");
        intent.removeExtra("intent_atop_shellenv");
        intent.removeExtra("start_in_min_overlay");
        return intent;
    }

    private static void a(Intent intent) {
        setGuardianSetupIsActive((intent == null || !"systemux://guardian/update_state".equals(intent.getStringExtra("intent_data"))) ? a : true);
    }

    private void b(Intent intent) {
        g gVar;
        boolean a2 = a ? IPCDebugCommandHandler.a(intent) : a;
        boolean b2 = IPCDebugCommandHandler.b(intent);
        if (!b2 || !isVrShellIPCEnabled()) {
            if (!b2 && !isVrShellIPCEnabled()) {
                gVar = new g(this, c());
            }
            if (!a2 && isVrShellIPCEnabled()) {
                this.c.a(intent);
                return;
            }
        }
        this.c.e();
        gVar = null;
        this.c = gVar;
        if (!a2) {
        }
    }

    private IBinder c() {
        try {
            return (IBinder) Activity.class.getDeclaredMethod("getActivityToken", new Class[0]).invoke(this, new Object[0]);
        } catch (Throwable th) {
            Log.e("Clay", "failed to get windowToken", th);
            return null;
        }
    }

    private static native void setGuardianSetupIsActive(boolean z);

    public void clearLaunchIntent() {
        if (isVrShellIPCEnabled()) {
            this.e.post(new b(this));
        }
    }

    public void guardianSetupRequestedFromMinOverlay() {
        if (isVrShellIPCEnabled()) {
            setGuardianSetupIsActive(true);
            this.e.post(new c(this));
        }
    }

    public boolean isVrShellIPCEnabled() {
        if (this.c != null) {
            return true;
        }
        return a;
    }

    public void notifyEnterVrMode() {
        if (isVrShellIPCEnabled()) {
            this.e.postDelayed(new e(this), 100);
        }
    }

    @Override // com.oculus.clay.app.ClayAppActivity
    public void onCreate(Bundle bundle) {
        b.a("onCreate");
        super.onCreate(bundle);
        f.a(this);
        this.d = f.c();
        this.d.d();
        Intent intent = getIntent();
        if (intent != null) {
            if (a) {
                IPCDebugCommandHandler.a(intent);
            }
            if (!IPCDebugCommandHandler.b(intent)) {
                this.c = new g(this, c());
                this.c.a(this.d);
                this.c.a();
            }
        }
        this.g = new a(this, (byte) 0);
        Trace.endSection();
    }

    @Override // com.oculus.clay.app.ClayAppActivity
    public void onDestroy() {
        b.a("onDestroy");
        this.d.g();
        if (isVrShellIPCEnabled()) {
            this.c.e();
        }
        super.onDestroy();
        Trace.endSection();
        shutdownShellEnv();
    }

    public void onNewIntent(Intent intent) {
        setIntent(intent);
        b(intent);
    }

    @Override // com.oculus.clay.app.ClayAppActivity
    public void onPause() {
        b.a("onPause");
        super.onPause();
        this.d.f();
        if (isVrShellIPCEnabled()) {
            this.c.d();
        }
        unregisterReceiver(this.g);
        Trace.endSection();
    }

    @Override // com.oculus.clay.app.ClayAppActivity
    public void onResume() {
        b.a("onResume");
        super.onResume();
        this.d.e();
        a(getIntent());
        if (this.f) {
            b(getIntent());
            this.f = a;
        }
        if (isVrShellIPCEnabled()) {
            this.c.b();
        }
        registerReceiver(this.g, IPCDebugCommandHandler.a);
        Trace.endSection();
    }

    public void shutdownShellEnv() {
        finish();
        a();
    }

    public void signalGuardianSetupEnded() {
        if (isVrShellIPCEnabled()) {
            a(getIntent());
            this.e.post(new d(this));
        }
    }

    public void switchToFullOverlayService() {
        if (isVrShellIPCEnabled()) {
            this.e.post(new a(this));
        }
    }
}
