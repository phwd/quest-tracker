package org.chromium.components.signin;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ChildAccountInfoFetcher {

    /* renamed from: a  reason: collision with root package name */
    public final long f10890a;
    public final String b;
    public final Account c;
    public final BroadcastReceiver d;

    public ChildAccountInfoFetcher(long j, String str, String str2) {
        this.f10890a = j;
        this.b = str;
        Account b2 = V1.b(str2);
        this.c = b2;
        C1010Qn qn = new C1010Qn(this);
        this.d = qn;
        ContextUtils.getApplicationContext().registerReceiver(qn, new IntentFilter("com.google.android.gms.auth.ACCOUNT_SERVICES_CHANGED"), "com.google.android.gms.auth.permission.GOOGLE_ACCOUNT_CHANGE", null);
        AccountManagerFacadeProvider.getInstance().i(b2, new C0949Pn(this));
    }

    public static ChildAccountInfoFetcher create(long j, String str, String str2) {
        return new ChildAccountInfoFetcher(j, str, str2);
    }

    public static void initializeForTests() {
        C1769b1 b1Var = new C1769b1(new C5537x51());
        AtomicReference atomicReference = AccountManagerFacadeProvider.f10888a;
        ThreadUtils.h(new RunnableC1940c1(b1Var));
    }

    public final void destroy() {
        ContextUtils.getApplicationContext().unregisterReceiver(this.d);
    }
}
