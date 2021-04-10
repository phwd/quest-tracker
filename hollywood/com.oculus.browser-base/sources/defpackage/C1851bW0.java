package defpackage;

import android.content.Context;
import android.util.Pair;
import com.google.android.gms.auth.AccountChangeEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.AccountTrackerService;
import org.chromium.components.signin.base.CoreAccountInfo;

/* renamed from: bW0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1851bW0 implements AbstractC1678aa {

    /* renamed from: a  reason: collision with root package name */
    public final SigninManager f9545a;
    public final AccountTrackerService b;
    public final C4072oW0 c;

    public C1851bW0(SigninManager signinManager, AccountTrackerService accountTrackerService, C4072oW0 ow0) {
        this.f9545a = signinManager;
        this.b = accountTrackerService;
        this.c = ow0;
        ApplicationStatus.h.b(this);
    }

    public static Pair b(C1671aW0 aw0, int i, String str) {
        ArrayList<String> arrayList;
        List n = AccountManagerFacadeProvider.getInstance().n();
        Context applicationContext = ContextUtils.getApplicationContext();
        Objects.requireNonNull(aw0);
        try {
            List<AccountChangeEvent> g = AbstractC2362eW.g(applicationContext, i, str);
            arrayList = new ArrayList(g.size());
            for (AccountChangeEvent accountChangeEvent : g) {
                if (accountChangeEvent.I == 4) {
                    arrayList.add(accountChangeEvent.K);
                } else {
                    arrayList.add(null);
                }
            }
        } catch (C2192dW | IOException e) {
            AbstractC1220Ua0.f("SigninHelper", "Failed to get change events", e);
            arrayList = new ArrayList(0);
        }
        int size = arrayList.size();
        for (String str2 : arrayList) {
            if (str2 != null) {
                if (V1.c(n, str2) == null) {
                    return b(aw0, 0, str2);
                }
                return new Pair(Integer.valueOf(size), str2);
            }
        }
        return new Pair(Integer.valueOf(size), str);
    }

    @Override // defpackage.AbstractC1678aa
    public void a(int i) {
        if (i == 1) {
            c();
        }
    }

    public void c() {
        TraceEvent j0 = TraceEvent.j0("SigninHelper.onMainActivityStart");
        try {
            C4072oW0 ow0 = C4072oW0.f10556a;
            boolean z = false;
            if (ow0.b.d("prefs_sync_accounts_changed", false)) {
                ow0.b.m("prefs_sync_accounts_changed", false);
                z = true;
            }
            AccountManagerFacadeProvider.getInstance().e(new VV0(this, z));
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public final void d(boolean z) {
        this.b.b();
        if (!z) {
            AccountTrackerService accountTrackerService = this.b;
            Objects.requireNonNull(accountTrackerService);
            Object obj = ThreadUtils.f10596a;
            if (accountTrackerService.b()) {
                accountTrackerService.b = 3;
                AccountManagerFacadeProvider.getInstance().g(new R1(accountTrackerService));
            }
        }
        if (this.f9545a.o()) {
            this.f9545a.K(new WV0(this, z));
            return;
        }
        CoreAccountInfo b2 = this.f9545a.f().b(1);
        if (b2 != null) {
            String i = this.c.b.i("prefs_sync_account_renamed", null);
            if (z && i != null) {
                StringBuilder i2 = AbstractC2531fV.i("handleAccountRename from: ");
                i2.append(b2.getEmail());
                i2.append(" to ");
                i2.append(i);
                AbstractC1220Ua0.d("SigninHelper", i2.toString(), new Object[0]);
                this.f9545a.h(3, new XV0(this, V1.b(i), new ZV0(this)), false);
            } else if (V1.c(AccountManagerFacadeProvider.getInstance().n(), b2.getEmail()) == null) {
                new YV0(this, b2).d(AbstractC2032cb.b);
            } else if (z) {
                this.f9545a.l();
            }
        }
    }
}
