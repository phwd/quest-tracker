package defpackage;

import android.accounts.Account;
import android.accounts.AuthenticatorDescription;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.UserManager;
import com.google.android.gms.common.ConnectionResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* renamed from: b1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1769b1 extends R0 {

    /* renamed from: a  reason: collision with root package name */
    public final P0 f9507a;
    public final C1322Vq0 b = new C1322Vq0();
    public C0789My0[] c;
    public C2111d1 d;
    public final AtomicReference e = new AtomicReference();
    public final CountDownLatch f = new CountDownLatch(1);
    public final ArrayList g = new ArrayList();
    public int h;
    public final ArrayList i = new ArrayList();
    public C3774mm0 j = new C3774mm0(Boolean.TRUE);

    public C1769b1(P0 p0) {
        Object obj = ThreadUtils.f10596a;
        this.f9507a = p0;
        C5537x51 x51 = (C5537x51) p0;
        Context applicationContext = ContextUtils.getApplicationContext();
        C5367w51 w51 = new C5367w51(x51);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.accounts.LOGIN_ACCOUNTS_CHANGED");
        applicationContext.registerReceiver(w51, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter2.addDataScheme("package");
        intentFilter2.addDataPath("com.google.android.gms", 1);
        applicationContext.registerReceiver(w51, intentFilter2);
        x51.c = true;
        x51.b.b(new U0(this));
        IntentFilter intentFilter3 = new IntentFilter("android.intent.action.APPLICATION_RESTRICTIONS_CHANGED");
        ContextUtils.getApplicationContext().registerReceiver(new X0(this), intentFilter3);
        new Y0(this, null).d(AbstractC2032cb.b);
    }

    public static boolean r(C1769b1 b1Var, Account account, String str) {
        String[] strArr = {str};
        C5537x51 x51 = (C5537x51) b1Var.f9507a;
        if (!x51.a()) {
            return false;
        }
        try {
            return x51.f11586a.hasFeatures(account, strArr, null, null).getResult().booleanValue();
        } catch (AuthenticatorException | IOException e2) {
            AbstractC1220Ua0.a("Auth", "Error while checking features: ", e2);
            return false;
        } catch (OperationCanceledException unused) {
            AbstractC1220Ua0.a("Auth", "Checking features was cancelled. This should not happen.", new Object[0]);
            return false;
        }
    }

    public static void s(C1769b1 b1Var) {
        int i2 = b1Var.h - 1;
        b1Var.h = i2;
        if (i2 <= 0) {
            Iterator it = b1Var.i.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
            b1Var.i.clear();
            b1Var.j.a(Boolean.FALSE);
        }
    }

    public static void t(C1769b1 b1Var) {
        int i2 = b1Var.h;
        b1Var.h = i2 + 1;
        if (i2 <= 0) {
            b1Var.j.a(Boolean.TRUE);
        }
    }

    public static C0789My0[] u() {
        try {
            Context applicationContext = ContextUtils.getApplicationContext();
            String[] stringArray = ((UserManager) applicationContext.getSystemService("user")).getApplicationRestrictions(applicationContext.getPackageName()).getStringArray("RestrictAccountsToPatterns");
            if (stringArray == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (String str : stringArray) {
                arrayList.add(new C0789My0(str));
            }
            return (C0789My0[]) arrayList.toArray(new C0789My0[0]);
        } catch (C0728Ly0 e2) {
            AbstractC1220Ua0.a("Sync_Signin", "Can't get account restriction patterns", e2);
            return null;
        }
    }

    public static C2111d1 v(C1769b1 b1Var) {
        Objects.requireNonNull(b1Var);
        try {
            Objects.requireNonNull((C5537x51) b1Var.f9507a);
            Object obj = SV.c;
            SV sv = SV.d;
            AtomicBoolean atomicBoolean = AbstractC3729mW.f10427a;
            throw new JV(String.format("Can't use Google Play Services: %s", ConnectionResult.B(1)), 1);
        } catch (Q0 e2) {
            return new C2111d1(e2);
        }
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public void a(W1 w1) {
        Object obj = ThreadUtils.f10596a;
        this.b.b(w1);
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public void b(Account account, Activity activity, Callback callback) {
        C5537x51 x51 = (C5537x51) this.f9507a;
        Objects.requireNonNull(x51);
        Object obj = ThreadUtils.f10596a;
        C5197v51 v51 = new C5197v51(callback);
        x51.f11586a.updateCredentials(account, "android", new Bundle(), activity, v51, null);
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public void c(String str) {
        Objects.requireNonNull((C5537x51) this.f9507a);
        try {
            AbstractC2362eW.f(ContextUtils.getApplicationContext(), str);
        } catch (C3046iW e2) {
            throw new C4424qb(false, e2);
        } catch (C2192dW e3) {
            throw new C4424qb(false, e3);
        } catch (IOException e4) {
            throw new C4424qb(true, e4);
        }
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public boolean d() {
        return this.e.get() != null;
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public void e(Runnable runnable) {
        Object obj = ThreadUtils.f10596a;
        if (d()) {
            ThreadUtils.d(runnable);
        } else {
            this.g.add(runnable);
        }
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public String f(String str) {
        Objects.requireNonNull((C5537x51) this.f9507a);
        try {
            return AbstractC2362eW.h(ContextUtils.getApplicationContext(), str);
        } catch (C2192dW | IOException e2) {
            AbstractC1220Ua0.a("Auth", "SystemAccountManagerDelegate.getAccountGaiaId", e2);
            return null;
        }
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public boolean h() {
        Objects.requireNonNull(this.f9507a);
        return false;
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public void i(Account account, T0 t0) {
        Object obj = ThreadUtils.f10596a;
        W0 w0 = new W0(this, account, t0);
        Executor executor = AbstractC2032cb.f9616a;
        w0.f();
        ((ExecutorC1463Ya) executor).execute(w0.e);
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public void j(Runnable runnable) {
        Object obj = ThreadUtils.f10596a;
        C3774mm0 mm0 = this.j;
        Objects.requireNonNull(mm0);
        if (!((Boolean) mm0.f10446a).booleanValue()) {
            ((FV0) runnable).run();
        } else {
            this.i.add(runnable);
        }
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public ZG0 k() {
        Objects.requireNonNull(this.f9507a);
        return null;
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public C4839t l(Account account, String str) {
        Objects.requireNonNull((C5537x51) this.f9507a);
        try {
            return new C4839t(AbstractC2362eW.i(ContextUtils.getApplicationContext(), account, str, null));
        } catch (C2192dW e2) {
            throw new C4424qb(false, AbstractC2531fV.g("Error while getting token for scope '", str, "'"), e2);
        } catch (IOException e3) {
            throw new C4424qb(true, e3);
        }
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public void m(W1 w1) {
        Object obj = ThreadUtils.f10596a;
        this.b.c(w1);
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public void o(Callback callback) {
        e(new V0(this, callback));
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public boolean p() {
        for (AuthenticatorDescription authenticatorDescription : ((C5537x51) this.f9507a).f11586a.getAuthenticatorTypes()) {
            if ("com.google".equals(authenticatorDescription.type)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public void q(Callback callback) {
        ((C5537x51) this.f9507a).f11586a.addAccount("com.google", null, null, null, null, new C5027u51(callback), null);
    }

    public final void w() {
        Iterator it = this.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((W1) uq0.next()).e();
            } else {
                return;
            }
        }
    }

    public final C2111d1 x() {
        boolean z;
        C2111d1 d1Var = this.d;
        if ((d1Var.b != null) || this.c == null) {
            return d1Var;
        }
        ArrayList arrayList = new ArrayList();
        for (Account account : (List) this.d.f9739a) {
            C0789My0[] my0Arr = this.c;
            int length = my0Arr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                C0789My0 my0 = my0Arr[i2];
                String str = account.name;
                if (my0.f8515a.size() == 1) {
                    z = str.equals(my0.f8515a.get(0));
                } else {
                    String str2 = (String) my0.f8515a.get(0);
                    if (str.startsWith(str2)) {
                        List list = my0.f8515a;
                        String str3 = (String) list.get(list.size() - 1);
                        if (str.endsWith(str3)) {
                            int length2 = str2.length();
                            List list2 = my0.f8515a;
                            Iterator it = list2.subList(1, list2.size() - 1).iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    String str4 = (String) it.next();
                                    int indexOf = str.indexOf(str4, length2);
                                    if (indexOf == -1) {
                                        break;
                                    }
                                    length2 = indexOf + str4.length();
                                } else if (str3.length() + length2 <= str.length()) {
                                    z = true;
                                }
                            }
                        }
                    }
                    z = false;
                }
                if (z) {
                    arrayList.add(account);
                    break;
                }
                i2++;
            }
        }
        return new C2111d1(Collections.unmodifiableList(arrayList));
    }

    public List y() {
        C2111d1 d1Var = (C2111d1) this.e.get();
        if (d1Var == null) {
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                this.f.await();
                C2111d1 d1Var2 = (C2111d1) this.e.get();
                if (ThreadUtils.i()) {
                    AbstractC3364kK0.k("Signin.AndroidPopulateAccountCacheWaitingTime", SystemClock.elapsedRealtime() - elapsedRealtime);
                }
                d1Var = d1Var2;
            } catch (InterruptedException e2) {
                throw new RuntimeException("Interrupted waiting for accounts", e2);
            }
        }
        Q0 q0 = d1Var.b;
        if (q0 == null) {
            return (List) d1Var.f9739a;
        }
        throw q0;
    }
}
