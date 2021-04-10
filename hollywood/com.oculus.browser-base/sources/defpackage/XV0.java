package defpackage;

import android.accounts.Account;

/* renamed from: XV0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class XV0 extends AbstractC2705gW0 {

    /* renamed from: a  reason: collision with root package name */
    public final C1851bW0 f9210a;
    public final Account b;
    public final AbstractC2363eW0 c;

    public XV0(C1851bW0 bw0, Account account, AbstractC2363eW0 ew0) {
        this.f9210a = bw0;
        this.b = account;
        this.c = ew0;
    }

    @Override // defpackage.AbstractC2705gW0
    public void b() {
        C1851bW0 bw0 = this.f9210a;
        Account account = this.b;
        AbstractC2363eW0 ew0 = this.c;
        bw0.c.b.p("prefs_sync_account_renamed", null);
        bw0.f9545a.t(30, account, ew0);
    }
}
