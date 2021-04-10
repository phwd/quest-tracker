package defpackage;

import android.accounts.Account;
import java.util.List;
import org.chromium.components.signin.AccountManagerFacade;

/* renamed from: Rn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1071Rn extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final AccountManagerFacade f8852a;
    public final T0 b;

    public C1071Rn(AccountManagerFacade accountManagerFacade, T0 t0) {
        this.f8852a = accountManagerFacade;
        this.b = t0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        AccountManagerFacade accountManagerFacade = this.f8852a;
        T0 t0 = this.b;
        List list = (List) obj;
        if (list.size() == 1) {
            accountManagerFacade.i((Account) list.get(0), t0);
        } else {
            t0.a(0);
        }
    }
}
