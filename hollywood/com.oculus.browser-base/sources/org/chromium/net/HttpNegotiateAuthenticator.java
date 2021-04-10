package org.chromium.net;

import J.N;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HttpNegotiateAuthenticator {

    /* renamed from: a  reason: collision with root package name */
    public Bundle f11002a;
    public final String b;

    public HttpNegotiateAuthenticator(String str) {
        this.b = str;
    }

    public static HttpNegotiateAuthenticator create(String str) {
        return new HttpNegotiateAuthenticator(str);
    }

    public boolean a(Context context, String str, boolean z) {
        return !z && context.checkPermission(str, Process.myPid(), Process.myUid()) != 0;
    }

    public void getNextAuthToken(long j, String str, String str2, boolean z) {
        Context applicationContext = ContextUtils.getApplicationContext();
        C1674aY aYVar = new C1674aY();
        aYVar.d = AbstractC2531fV.f("SPNEGO:HOSTBASED:", str);
        aYVar.b = AccountManager.get(applicationContext);
        aYVar.f9435a = j;
        String[] strArr = {"SPNEGO"};
        Bundle bundle = new Bundle();
        aYVar.c = bundle;
        if (str2 != null) {
            bundle.putString("incomingAuthToken", str2);
        }
        Bundle bundle2 = this.f11002a;
        if (bundle2 != null) {
            aYVar.c.putBundle("spnegoContext", bundle2);
        }
        aYVar.c.putBoolean("canDelegate", z);
        Activity activity = ApplicationStatus.e;
        if (activity == null) {
            if (a(applicationContext, "android.permission.GET_ACCOUNTS", true)) {
                AbstractC1220Ua0.a("net_auth", "ERR_MISCONFIGURED_AUTH_ENVIRONMENT: GET_ACCOUNTS permission not granted. Aborting authentication.", new Object[0]);
                N.M0s8NeYn(aYVar.f9435a, this, -343, null);
                return;
            }
            aYVar.b.getAccountsByTypeAndFeatures(this.b, strArr, new XX(this, aYVar), new Handler(ThreadUtils.c()));
        } else if (a(applicationContext, "android.permission.GET_ACCOUNTS", false)) {
            AbstractC1220Ua0.a("net_auth", "ERR_MISCONFIGURED_AUTH_ENVIRONMENT: %s permission not granted. Aborting authentication", "android.permission.GET_ACCOUNTS");
            N.M0s8NeYn(aYVar.f9435a, this, -343, null);
        } else {
            aYVar.b.getAuthTokenByFeatures(this.b, aYVar.d, strArr, activity, null, aYVar.c, new ZX(this, aYVar), new Handler(ThreadUtils.c()));
        }
    }
}
