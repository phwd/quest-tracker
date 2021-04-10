package defpackage;

import android.content.Intent;
import android.util.Log;

/* renamed from: NG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class NG1 implements Runnable {
    public final /* synthetic */ Intent F;
    public final /* synthetic */ EG1 G;

    public NG1(EG1 eg1, Intent intent) {
        this.G = eg1;
        this.F = intent;
    }

    public final void run() {
        String action = this.F.getAction();
        StringBuilder sb = new StringBuilder(String.valueOf(action).length() + 61);
        sb.append("Service took too long to process intent: ");
        sb.append(action);
        sb.append(" App may get closed.");
        Log.w("EnhancedIntentService", sb.toString());
        this.G.a();
    }
}
