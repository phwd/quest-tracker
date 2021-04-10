package defpackage;

import J.N;
import android.accounts.Account;
import java.util.Objects;
import org.chromium.components.signin.AccountManagerFacade;

/* renamed from: eH0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2330eH0 implements AbstractC2672gH0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AccountManagerFacade f9845a;
    public final /* synthetic */ Account b;
    public final /* synthetic */ String c;
    public final /* synthetic */ C2160dH0 d;

    public C2330eH0(AccountManagerFacade accountManagerFacade, Account account, String str, C2160dH0 dh0) {
        this.f9845a = accountManagerFacade;
        this.b = account;
        this.c = str;
        this.d = dh0;
    }

    @Override // defpackage.AbstractC2672gH0
    public void a(Object obj) {
        C2160dH0 dh0 = this.d;
        Objects.requireNonNull(dh0);
        N.MTN9MD0o(((C4839t) obj).f11314a, 0, false, dh0.f9767a);
    }

    @Override // defpackage.AbstractC2672gH0
    public void b(boolean z) {
        N.MTN9MD0o(null, 0, z, this.d.f9767a);
    }

    @Override // defpackage.AbstractC2672gH0
    public Object run() {
        return this.f9845a.l(this.b, this.c);
    }
}
