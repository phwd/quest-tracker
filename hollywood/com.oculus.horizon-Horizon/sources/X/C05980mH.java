package X;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/* renamed from: X.0mH  reason: invalid class name and case insensitive filesystem */
public class C05980mH implements AnonymousClass0b8 {
    public final /* synthetic */ AbstractC05970mG A00;

    public C05980mH(AbstractC05970mG r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0b8
    public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r14) {
        String A01;
        Object[] objArr;
        String str;
        String stringExtra = intent.getStringExtra("extra_mqtt_endpoint");
        String stringExtra2 = intent.getStringExtra("extra_analytics_endpoint");
        String stringExtra3 = intent.getStringExtra("extra_fbns_endpoint");
        String stringExtra4 = intent.getStringExtra("extra_fbns_analytics_endpoint");
        C02890bh r4 = new C02890bh();
        r4.A00 |= 1;
        if (!TextUtils.isEmpty("MQTT_CONFIG_CHANGE_DOMAIN")) {
            r4.A02.add("MQTT_CONFIG_CHANGE_DOMAIN");
            boolean A002 = r4.A00().A00(context, intent, null);
            AbstractC05970mG r42 = this.A00;
            if (!A002) {
                A01 = r42.A01();
                objArr = new Object[]{stringExtra, stringExtra2, stringExtra3, stringExtra4};
                str = "ignore unauthorized sender %s, %s, %s, %s";
            } else if (!r42.A06(stringExtra) || !r42.A06(stringExtra3)) {
                A01 = r42.A01();
                objArr = new Object[]{stringExtra, stringExtra2, stringExtra3, stringExtra4};
                str = "ignore illegal target endpoint switch %s, %s, %s, %s";
            } else {
                Integer num = r42.A02;
                if (num == AnonymousClass007.A01 || num == AnonymousClass007.A0C) {
                    if (!TextUtils.isEmpty(stringExtra3)) {
                        stringExtra = stringExtra3;
                    }
                    if (!TextUtils.isEmpty(stringExtra4)) {
                        stringExtra2 = stringExtra4;
                    }
                }
                String str2 = r42.A06;
                if (str2 != null ? str2.equals(stringExtra) : stringExtra == null) {
                    String str3 = r42.A05;
                    if (str3 == null) {
                        if (stringExtra2 == null) {
                            return;
                        }
                    } else if (str3.equals(stringExtra2)) {
                        return;
                    }
                }
                r42.A05(stringExtra, stringExtra2);
                r42.A06 = stringExtra;
                r42.A05 = stringExtra2;
                r42.A01.A02();
                return;
            }
            AnonymousClass0NO.A0F(A01, str, objArr);
            return;
        }
        throw new IllegalArgumentException();
    }
}
