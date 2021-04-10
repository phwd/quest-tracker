package defpackage;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.browser.customtabs.CustomTabsSessionToken;
import androidx.browser.customtabs.PostMessageService;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* renamed from: WY0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class WY0 extends Service {
    public final BW0 F = new BW0();
    public AY G = new BinderC5046uC(this);
    public String H;
    public C4535rC I;

    public WY0(String str) {
        this.H = str;
    }

    public boolean a(CustomTabsSessionToken customTabsSessionToken) {
        boolean z;
        CustomTabsConnection customTabsConnection = this.I.f11189a;
        Objects.requireNonNull(customTabsConnection);
        C4194pC pCVar = new C4194pC(customTabsConnection);
        C4365qC qCVar = new C4365qC(customTabsConnection, customTabsSessionToken);
        IE0 ie0 = new IE0(qCVar);
        C3287jv jvVar = customTabsConnection.f;
        int callingUid = Binder.getCallingUid();
        synchronized (jvVar) {
            if (customTabsSessionToken.c == null) {
                z = false;
            } else {
                if (jvVar.b.containsKey(customTabsSessionToken)) {
                    ((C3116iv) jvVar.b.get(customTabsSessionToken)).b = customTabsSessionToken.c;
                } else {
                    jvVar.b.put(customTabsSessionToken, new C3116iv(ContextUtils.getApplicationContext(), callingUid, customTabsSessionToken.c, pCVar, ie0, qCVar));
                }
                z = true;
            }
        }
        customTabsConnection.j("newSession()", Boolean.valueOf(z));
        return z;
    }

    public void attachBaseContext(Context context) {
        Context a2 = AbstractC2369eZ0.a(context);
        C4535rC rCVar = (C4535rC) AbstractC2369eZ0.b(a2, this.H);
        this.I = rCVar;
        Objects.requireNonNull(rCVar);
        super.attachBaseContext(a2);
    }

    public boolean b(CustomTabsSessionToken customTabsSessionToken, Uri uri) {
        boolean z;
        C4535rC rCVar = this.I;
        Objects.requireNonNull(rCVar);
        C4649rt0 a2 = C4649rt0.a(uri);
        boolean z2 = false;
        if (a2 != null) {
            CustomTabsConnection customTabsConnection = rCVar.f11189a;
            if (customTabsConnection.h.get()) {
                if (!customTabsConnection.h()) {
                    Objects.requireNonNull(customTabsConnection.e);
                } else {
                    C3287jv jvVar = customTabsConnection.f;
                    synchronized (jvVar) {
                        C3116iv ivVar = (C3116iv) jvVar.b.get(customTabsSessionToken);
                        if (ivVar == null) {
                            z = false;
                        } else {
                            KE0 ke0 = ivVar.e;
                            Context applicationContext = ContextUtils.getApplicationContext();
                            String str = ke0.d;
                            if (str != null) {
                                Intent intent = new Intent();
                                intent.setClassName(str, PostMessageService.class.getName());
                                z = applicationContext.bindService(intent, ke0, 1);
                                if (!z) {
                                    Log.w("PostMessageServConn", "Could not bind to PostMessageService in client.");
                                }
                            } else {
                                throw new IllegalStateException("setPackageName must be called before bindSessionToPostMessageService.");
                            }
                        }
                    }
                    if (z) {
                        PostTask.b(Zo1.f9374a, new RunnableC1973cC(customTabsConnection, customTabsSessionToken, Binder.getCallingUid(), a2), 0);
                        z2 = true;
                    }
                }
            }
            StringBuilder i = AbstractC2531fV.i("requestPostMessageChannel() with origin ");
            i.append(a2.toString());
            customTabsConnection.j(i.toString(), Boolean.valueOf(z2));
        }
        return z2;
    }

    public IBinder onBind(Intent intent) {
        C4535rC rCVar = this.I;
        rCVar.b = intent;
        CustomTabsConnection f = CustomTabsConnection.f();
        rCVar.f11189a = f;
        f.j("Service#onBind()", Boolean.TRUE);
        return this.G;
    }

    public void onCreate() {
        super.onCreate();
        Objects.requireNonNull(this.I);
        OG0.a().d();
        C2173dM0.b();
    }

    public boolean onUnbind(Intent intent) {
        CustomTabsConnection customTabsConnection = this.I.f11189a;
        if (customTabsConnection == null) {
            return false;
        }
        customTabsConnection.j("Service#onUnbind()", Boolean.TRUE);
        return false;
    }
}
