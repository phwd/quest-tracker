package defpackage;

import android.app.Activity;
import android.content.Context;
import android.security.KeyChainAliasCallback;
import org.chromium.base.task.PostTask;

/* renamed from: eO0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2347eO0 implements KeyChainAliasCallback {

    /* renamed from: a  reason: collision with root package name */
    public final long f9851a;
    public final Context b;
    public boolean c;
    public final Activity d;

    public C2347eO0(Context context, long j, Activity activity) {
        this.b = context;
        this.f9851a = j;
        this.d = activity;
    }

    public void alias(String str) {
        if (this.c) {
            AbstractC1220Ua0.f("SSLClientCertRequest", AbstractC2531fV.g("KeyChainCertSelectionCallback called more than once ('", str, "')"), new Object[0]);
            return;
        }
        this.c = true;
        PostTask.c(Zo1.f9374a, new RunnableC2006cO0(this, str));
    }
}
