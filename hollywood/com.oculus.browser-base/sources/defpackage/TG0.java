package defpackage;

import android.accounts.Account;
import java.util.List;
import java.util.Objects;

/* renamed from: TG0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class TG0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final WG0 f8948a;

    public TG0(WG0 wg0) {
        this.f8948a = wg0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        WG0 wg0 = this.f8948a;
        Objects.requireNonNull(wg0);
        for (Account account : (List) obj) {
            YG0 b = wg0.L.b(account.name);
            if (b != null) {
                String str = b.f9266a;
                wg0.a0(new C3522lG(str, wg0.X(b.b, str), b.c, b.d));
            }
        }
    }
}
