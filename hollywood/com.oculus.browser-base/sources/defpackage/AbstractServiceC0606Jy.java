package defpackage;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.content.app.ContentChildProcessServiceDelegate;

/* renamed from: Jy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractServiceC0606Jy extends Service {
    public C1732ap F;

    public IBinder onBind(Intent intent) {
        C1732ap apVar = this.F;
        if (apVar.p) {
            return apVar.r;
        }
        apVar.e.stopSelf();
        apVar.i = intent.getBooleanExtra("org.chromium.base.process_launcher.extra.bind_to_caller", false);
        apVar.p = true;
        Objects.requireNonNull((ContentChildProcessServiceDelegate) apVar.d);
        C2303e80 e80 = C2474f80.f9900a.i;
        Bundle extras = intent.getExtras();
        synchronized (e80.c.j) {
            e80.f9835a = extras.getLong("org.chromium.base.android.linker.base_load_address", 0);
        }
        C2474f80.f9900a.l(intent.getExtras().getInt("org.chromium.content.common.child_service_params.library_process_type", 2));
        String stringExtra = intent.getStringExtra("org.chromium.base.process_launcher.extra.browser_package_name");
        if (stringExtra == null) {
            stringExtra = apVar.f.getApplicationInfo().packageName;
        }
        new Handler(Looper.getMainLooper()).post(new RunnableC1378Wo(apVar, stringExtra));
        return apVar.r;
    }

    public void onCreate() {
        super.onCreate();
        C1732ap apVar = new C1732ap(new ContentChildProcessServiceDelegate(), this, getApplicationContext());
        this.F = apVar;
        Objects.requireNonNull(apVar);
        AbstractC1220Ua0.d("ChildProcessService", "Creating new ChildProcessService pid=%d", Integer.valueOf(Process.myPid()));
        if (!C1732ap.f9491a) {
            C1732ap.f9491a = true;
            ContextUtils.f10585a = apVar.f;
            Objects.requireNonNull((ContentChildProcessServiceDelegate) apVar.d);
            Thread thread = new Thread(new RunnableC1561Zo(apVar), "ChildProcessMain");
            apVar.l = thread;
            thread.start();
            return;
        }
        throw new RuntimeException("Illegal child process reuse.");
    }

    public void onDestroy() {
        super.onDestroy();
        Objects.requireNonNull(this.F);
        AbstractC1220Ua0.d("ChildProcessService", "Destroying ChildProcessService pid=%d", Integer.valueOf(Process.myPid()));
        System.exit(0);
        this.F = null;
    }
}
