package defpackage;

import J.N;
import android.accounts.Account;
import java.util.List;
import org.chromium.components.signin.AccountTrackerService;

/* renamed from: R1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class R1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final AccountTrackerService f8803a;

    public R1(AccountTrackerService accountTrackerService) {
        this.f8803a = accountTrackerService;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        AccountTrackerService accountTrackerService = this.f8803a;
        List list = (List) obj;
        if (!accountTrackerService.c && accountTrackerService.b == 3) {
            String[] strArr = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                strArr[i] = ((Account) list.get(i)).name;
            }
            if (N.MVRs2cAt(accountTrackerService.f10889a, strArr)) {
                accountTrackerService.b = 2;
                accountTrackerService.d();
            }
        }
    }
}
