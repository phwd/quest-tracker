package defpackage;

import J.N;
import android.accounts.Account;
import android.os.SystemClock;
import java.lang.reflect.Array;
import java.util.List;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountTrackerService;

/* renamed from: S1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class S1 extends AbstractC2032cb {
    public final /* synthetic */ List i;
    public final /* synthetic */ AccountManagerFacade j;
    public final /* synthetic */ AccountTrackerService k;

    public S1(AccountTrackerService accountTrackerService, List list, AccountManagerFacade accountManagerFacade) {
        this.k = accountTrackerService;
        this.i = list;
        this.j = accountManagerFacade;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        int[] iArr = new int[2];
        iArr[1] = this.i.size();
        iArr[0] = 2;
        String[][] strArr = (String[][]) Array.newInstance(String.class, iArr);
        for (int i2 = 0; i2 < this.i.size(); i2++) {
            strArr[0][i2] = this.j.f(((Account) this.i.get(i2)).name);
            strArr[1][i2] = ((Account) this.i.get(i2)).name;
        }
        AbstractC3364kK0.k("Signin.AndroidGetAccountIdsTime", SystemClock.elapsedRealtime() - elapsedRealtime);
        return strArr;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        boolean z;
        String[][] strArr = (String[][]) obj;
        AccountTrackerService accountTrackerService = this.k;
        if (accountTrackerService.c) {
            accountTrackerService.e();
            return;
        }
        String[] strArr2 = strArr[0];
        int length = strArr2.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                z = true;
                break;
            } else if (strArr2[i2] == null) {
                z = false;
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            N.Mu2KU$HY(this.k.f10889a, strArr[0], strArr[1]);
            AccountTrackerService accountTrackerService2 = this.k;
            accountTrackerService2.b = 2;
            accountTrackerService2.d();
            return;
        }
        AbstractC1220Ua0.f("AccountService", "Invalid mapping of id/email", new Object[0]);
        this.k.e();
    }
}
