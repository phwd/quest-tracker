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

/* renamed from: jY0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3221jY0 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final SmsProviderGms f10212a;
    public boolean b = false;
    public C5688xz1 c;

    public C3221jY0(SmsProviderGms smsProviderGms, C5688xz1 xz1) {
        this.f10212a = smsProviderGms;
        this.c = xz1;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.gms.auth.api.phone.SMS_RETRIEVED");
        this.c.registerReceiver(this, intentFilter);
    }

    public void a() {
        C5388wC1 wc1 = (C5388wC1) this.f10212a.a().f10934a;
        Objects.requireNonNull(wc1);
        C0441He1 a2 = AbstractC0502Ie1.a();
        a2.f8170a = new GC1(wc1, null);
        a2.b = new Feature[]{MC1.d};
        OI1 e = wc1.e(1, a2.a());
        C3051iY0 iy0 = new C3051iY0(this);
        Objects.requireNonNull(e);
        e.b.a(new C4723sH1(AbstractC0928Pe1.f8702a, iy0));
        e.e();
    }

    public void onReceive(Context context, Intent intent) {
        if (!this.b && "com.google.android.gms.auth.api.phone.SMS_RETRIEVED".equals(intent.getAction()) && intent.getExtras() != null) {
            try {
                int i = ((Status) intent.getParcelableExtra("com.google.android.gms.auth.api.phone.EXTRA_STATUS")).K;
                if (i == 0) {
                    this.f10212a.f.H0((Intent) intent.getExtras().getParcelable("com.google.android.gms.auth.api.phone.EXTRA_CONSENT_INTENT"), new C2880hY0(this), null);
                } else if (i == 15) {
                    N.Mz9c1Rem(this.f10212a.f10933a);
                }
            } catch (Throwable unused) {
            }
        }
    }
}
