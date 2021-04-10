package defpackage;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.chromium.base.ThreadUtils;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.ChildAccountInfoFetcher;

/* renamed from: Qn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1010Qn extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ChildAccountInfoFetcher f8784a;

    public C1010Qn(ChildAccountInfoFetcher childAccountInfoFetcher) {
        this.f8784a = childAccountInfoFetcher;
    }

    public void onReceive(Context context, Intent intent) {
        Object obj = ThreadUtils.f10596a;
        Account account = (Account) intent.getParcelableExtra("account");
        String str = account.name;
        if (this.f8784a.c.equals(account)) {
            ChildAccountInfoFetcher childAccountInfoFetcher = this.f8784a;
            String str2 = childAccountInfoFetcher.c.name;
            AccountManagerFacadeProvider.getInstance().i(childAccountInfoFetcher.c, new C0949Pn(childAccountInfoFetcher));
        }
    }
}
