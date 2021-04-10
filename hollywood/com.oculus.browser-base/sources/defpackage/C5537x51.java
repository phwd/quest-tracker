package defpackage;

import android.accounts.AccountManager;
import android.os.Process;
import org.chromium.base.ContextUtils;

/* renamed from: x51  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5537x51 extends P0 {

    /* renamed from: a  reason: collision with root package name */
    public final AccountManager f11586a = AccountManager.get(ContextUtils.getApplicationContext());
    public final C1322Vq0 b = new C1322Vq0();
    public boolean c;

    public boolean a() {
        return AbstractC3153j7.a(ContextUtils.getApplicationContext(), "android.permission.GET_ACCOUNTS", Process.myPid(), Process.myUid()) == 0;
    }
}
