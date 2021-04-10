package defpackage;

import J.N;
import android.accounts.Account;
import android.content.ContentResolver;
import android.os.Bundle;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.base.CoreAccountInfo;

/* renamed from: t6  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4858t6 {

    /* renamed from: a  reason: collision with root package name */
    public static C4858t6 f11322a;
    public final String b = ContextUtils.getApplicationContext().getPackageName();
    public final X41 c;
    public Account d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public Y41 i;

    @Deprecated
    public C4858t6(Account account) {
        Object obj = ThreadUtils.f10596a;
        if (X41.f9192a == null) {
            X41.f9192a = new X41();
        }
        X41 x41 = X41.f9192a;
        this.c = x41;
        this.d = account;
        f();
        g();
        ProfileSyncService b2 = ProfileSyncService.b();
        if (b2 != null && N.M09VlOh_("DecoupleSyncFromAndroidMasterSync")) {
            this.h = N.MrzU4hbb(b2.e, b2);
        }
        SyncStatusObserverC4688s6 s6Var = new SyncStatusObserverC4688s6(this);
        Objects.requireNonNull(x41);
        ContentResolver.addStatusChangeListener(1, s6Var);
    }

    public static C4858t6 c() {
        Object obj = ThreadUtils.f10596a;
        if (f11322a == null) {
            f11322a = new C4858t6(CoreAccountInfo.a(C5949zZ.a().c(Profile.b()).b(1)));
        }
        return f11322a;
    }

    public boolean a() {
        Object obj = ThreadUtils.f10596a;
        return this.g || this.h;
    }

    public void b() {
        Object obj = ThreadUtils.f10596a;
        e(true);
    }

    public final void d() {
        Y41 y41 = this.i;
        if (y41 != null) {
            y41.b();
        }
    }

    public final void e(boolean z) {
        Account account;
        g();
        if (z != this.f && (account = this.d) != null) {
            this.f = z;
            X41 x41 = this.c;
            String str = this.b;
            Objects.requireNonNull(x41);
            ContentResolver.setSyncAutomatically(account, str, z);
            d();
        }
    }

    public final boolean f() {
        boolean z = this.f;
        boolean z2 = this.g;
        Account account = this.d;
        if (account != null) {
            X41 x41 = this.c;
            String str = this.b;
            Objects.requireNonNull(x41);
            this.e = ContentResolver.getIsSyncable(account, str) > 0;
            X41 x412 = this.c;
            Account account2 = this.d;
            String str2 = this.b;
            Objects.requireNonNull(x412);
            this.f = ContentResolver.getSyncAutomatically(account2, str2);
        } else {
            this.e = false;
            this.f = false;
        }
        Objects.requireNonNull(this.c);
        this.g = ContentResolver.getMasterSyncAutomatically();
        if (this.d != null && ProfileSyncService.b() != null && N.M09VlOh_("DecoupleSyncFromAndroidMasterSync") && this.g && !this.h) {
            this.h = true;
            ProfileSyncService b2 = ProfileSyncService.b();
            N.MoUp3S2d(b2.e, b2);
        }
        return (z == this.f && z2 == this.g) ? false : true;
    }

    public final void g() {
        boolean z = this.d != null && !N.M09VlOh_("DecoupleSyncFromAndroidMasterSync");
        if (this.e != z) {
            this.e = z;
            if (z) {
                X41 x41 = this.c;
                Account account = this.d;
                String str = this.b;
                Objects.requireNonNull(x41);
                ContentResolver.setIsSyncable(account, str, 1);
                X41 x412 = this.c;
                Account account2 = this.d;
                String str2 = this.b;
                Bundle bundle = Bundle.EMPTY;
                Objects.requireNonNull(x412);
                ContentResolver.removePeriodicSync(account2, str2, bundle);
            } else {
                Account account3 = this.d;
                if (account3 != null) {
                    X41 x413 = this.c;
                    String str3 = this.b;
                    Objects.requireNonNull(x413);
                    ContentResolver.setIsSyncable(account3, str3, 0);
                }
            }
            AccountManagerFacadeProvider.getInstance().g(new C4347q6(this));
        }
    }
}
