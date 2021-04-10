package defpackage;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountTrackerService;

/* renamed from: Q1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Q1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final AccountTrackerService f8732a;
    public final AccountManagerFacade b;

    public Q1(AccountTrackerService accountTrackerService, AccountManagerFacade accountManagerFacade) {
        this.f8732a = accountTrackerService;
        this.b = accountManagerFacade;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        AccountTrackerService accountTrackerService = this.f8732a;
        AccountManagerFacade accountManagerFacade = this.b;
        Objects.requireNonNull(accountTrackerService);
        S1 s1 = new S1(accountTrackerService, (List) obj, accountManagerFacade);
        Executor executor = AbstractC2032cb.f9616a;
        s1.f();
        ((ExecutorC1463Ya) executor).execute(s1.e);
    }
}
