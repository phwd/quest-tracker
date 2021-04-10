package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: X0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class X0 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1769b1 f9184a;

    public X0(C1769b1 b1Var) {
        this.f9184a = b1Var;
    }

    public void onReceive(Context context, Intent intent) {
        C1769b1 b1Var = this.f9184a;
        Objects.requireNonNull(b1Var);
        Object obj = ThreadUtils.f10596a;
        new Z0(b1Var, null).d(AbstractC2032cb.b);
    }
}
