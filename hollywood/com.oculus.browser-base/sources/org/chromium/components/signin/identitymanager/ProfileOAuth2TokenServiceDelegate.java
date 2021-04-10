package org.chromium.components.signin.identitymanager;

import J.N;
import android.accounts.Account;
import android.text.TextUtils;
import java.util.ArrayList;
import org.chromium.base.ThreadUtils;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountTrackerService;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ProfileOAuth2TokenServiceDelegate extends T1 {
    public final long F;
    public final AccountTrackerService G;
    public final AccountManagerFacade H;
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public String f10897J;

    public ProfileOAuth2TokenServiceDelegate(long j, AccountTrackerService accountTrackerService, AccountManagerFacade accountManagerFacade) {
        this.F = j;
        this.G = accountTrackerService;
        this.H = accountManagerFacade;
        if (accountTrackerService != null) {
            accountTrackerService.a(this);
        }
    }

    public static ProfileOAuth2TokenServiceDelegate create(long j, AccountTrackerService accountTrackerService, AccountManagerFacade accountManagerFacade) {
        return new ProfileOAuth2TokenServiceDelegate(j, accountTrackerService, accountManagerFacade);
    }

    public final void getAccessTokenFromNative(String str, String str2, long j) {
        Account account = null;
        if (str == null) {
            AbstractC1220Ua0.a("OAuth2TokenService", "Username is null", new Object[0]);
        } else {
            Account c = V1.c(this.H.n(), str);
            if (c == null) {
                AbstractC1220Ua0.a("OAuth2TokenService", "Account not found for provided username.", new Object[0]);
            } else {
                account = c;
            }
        }
        if (account == null) {
            ThreadUtils.d(new RunnableC1989cH0(j));
            return;
        }
        new C3014iH0(new C2330eH0(this.H, account, AbstractC2531fV.f("oauth2:", str2), new C2160dH0(this, j))).b();
    }

    public String[] getSystemAccountNames() {
        P21 f0 = P21.f0();
        try {
            ArrayList arrayList = (ArrayList) V1.d(this.H.n());
            String[] strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
            f0.close();
            return strArr;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public final boolean hasOAuth2RefreshToken(String str) {
        boolean z = false;
        if (!this.H.d()) {
            return false;
        }
        P21 f0 = P21.f0();
        try {
            if (V1.c(this.H.n(), str) != null) {
                z = true;
            }
            f0.close();
            return z;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public void invalidateAccessToken(String str) {
        if (!TextUtils.isEmpty(str)) {
            new C3014iH0(new C2501fH0(this, str)).b();
        }
    }

    public final void seedAndReloadAccountsWithPrimaryAccount(String str) {
        Object obj = ThreadUtils.f10596a;
        if (!this.G.b()) {
            this.I = true;
            this.f10897J = str;
            return;
        }
        N.M0SOBbHG(this.F, str);
    }

    @Override // defpackage.U1
    public void v() {
        if (this.I) {
            N.M0SOBbHG(this.F, this.f10897J);
            this.I = false;
            this.f10897J = null;
        }
    }
}
