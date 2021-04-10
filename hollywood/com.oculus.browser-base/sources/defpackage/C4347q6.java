package defpackage;

import android.accounts.Account;
import android.content.ContentResolver;
import java.util.List;
import java.util.Objects;

/* renamed from: q6  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4347q6 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4858t6 f11115a;

    public C4347q6(C4858t6 t6Var) {
        this.f11115a = t6Var;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4858t6 t6Var = this.f11115a;
        Objects.requireNonNull(t6Var);
        for (Account account : (List) obj) {
            if (!account.equals(t6Var.d)) {
                X41 x41 = t6Var.c;
                String str = t6Var.b;
                Objects.requireNonNull(x41);
                if (ContentResolver.getIsSyncable(account, str) > 0) {
                    X41 x412 = t6Var.c;
                    String str2 = t6Var.b;
                    Objects.requireNonNull(x412);
                    ContentResolver.setIsSyncable(account, str2, 0);
                }
            }
        }
    }
}
