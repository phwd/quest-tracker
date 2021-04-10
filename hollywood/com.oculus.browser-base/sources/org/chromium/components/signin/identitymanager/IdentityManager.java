package org.chromium.components.signin.identitymanager;

import J.N;
import java.util.Iterator;
import org.chromium.components.signin.base.AccountInfo;
import org.chromium.components.signin.base.CoreAccountInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IdentityManager {

    /* renamed from: a  reason: collision with root package name */
    public long f10894a;
    public final C1322Vq0 b = new C1322Vq0();

    public IdentityManager(long j, ProfileOAuth2TokenServiceDelegate profileOAuth2TokenServiceDelegate) {
        this.f10894a = j;
    }

    public static IdentityManager create(long j, ProfileOAuth2TokenServiceDelegate profileOAuth2TokenServiceDelegate) {
        return new IdentityManager(j, profileOAuth2TokenServiceDelegate);
    }

    public AccountInfo a(String str) {
        return (AccountInfo) N.MRQQkZGI(this.f10894a, str);
    }

    public CoreAccountInfo b(int i) {
        return (CoreAccountInfo) N.MwJ3GEOr(this.f10894a, i);
    }

    public boolean c() {
        return b(1) != null;
    }

    public final void destroy() {
        this.f10894a = 0;
    }

    public void onAccountsCookieDeletedByUserAction() {
        Iterator it = this.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC5779yZ) uq0.next()).R();
            } else {
                return;
            }
        }
    }

    public void onExtendedAccountInfoUpdated(AccountInfo accountInfo) {
        Iterator it = this.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC5779yZ) uq0.next()).S(accountInfo);
            } else {
                return;
            }
        }
    }

    public void onPrimaryAccountChanged(PrimaryAccountChangeEvent primaryAccountChangeEvent) {
        Iterator it = this.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC5779yZ) uq0.next()).T(primaryAccountChangeEvent);
            } else {
                return;
            }
        }
    }
}
