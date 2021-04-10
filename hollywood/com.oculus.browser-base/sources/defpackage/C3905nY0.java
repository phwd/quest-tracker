package defpackage;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import java.util.Objects;
import org.chromium.content.browser.sms.SmsProviderGms;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: nY0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3905nY0 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final SmsProviderGms f10496a;
    public boolean b = false;
    public C5688xz1 c;

    public C3905nY0(SmsProviderGms smsProviderGms, C5688xz1 xz1) {
        this.f10496a = smsProviderGms;
        this.c = xz1;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.gms.auth.api.phone.SMS_CODE_RETRIEVED");
        this.c.registerReceiver(this, intentFilter);
    }

    public void a(WindowAndroid windowAndroid) {
        GI1 gi1 = (GI1) this.f10496a.a().b;
        Objects.requireNonNull(gi1);
        C0441He1 a2 = AbstractC0502Ie1.a();
        a2.b = new Feature[]{MC1.b};
        a2.f8170a = new YI1(gi1);
        OI1 e = gi1.e(1, a2.a());
        e.a(new C3392kY0(this));
        C3563lY0 ly0 = new C3563lY0(this, windowAndroid);
        e.b.a(new C4723sH1(AbstractC0928Pe1.f8702a, ly0));
        e.e();
    }

    public void onReceive(Context context, Intent intent) {
        if (!this.b && "com.google.android.gms.auth.api.phone.SMS_CODE_RETRIEVED".equals(intent.getAction()) && intent.getExtras() != null) {
            try {
                int i = ((Status) intent.getParcelableExtra("com.google.android.gms.auth.api.phone.EXTRA_STATUS")).K;
                if (i == 0) {
                    N.MDAxNisW(this.f10496a.f10933a, intent.getExtras().getString("com.google.android.gms.auth.api.phone.EXTRA_SMS_CODE_LINE"), 2);
                } else if (i == 15) {
                    N.Mz9c1Rem(this.f10496a.f10933a);
                }
            } catch (Throwable unused) {
            }
        }
    }
}
