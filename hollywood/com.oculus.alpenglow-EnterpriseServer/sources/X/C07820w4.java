package X;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/* renamed from: X.0w4  reason: invalid class name and case insensitive filesystem */
public class C07820w4 implements AbstractC04990iH {
    public final /* synthetic */ AbstractC07830w5 A00;

    public C07820w4(AbstractC07830w5 r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC04990iH
    public final void onReceive(Context context, Intent intent, AbstractC05000iI r12) {
        String str;
        Object[] objArr;
        String str2;
        String stringExtra = intent.getStringExtra("extra_mqtt_endpoint");
        String stringExtra2 = intent.getStringExtra("extra_analytics_endpoint");
        String stringExtra3 = intent.getStringExtra("extra_fbns_endpoint");
        String stringExtra4 = intent.getStringExtra("extra_fbns_analytics_endpoint");
        C05300iz r4 = new C05300iz();
        r4.A00 |= 1;
        if (!TextUtils.isEmpty("MQTT_CONFIG_CHANGE_DOMAIN")) {
            r4.A02.add("MQTT_CONFIG_CHANGE_DOMAIN");
            boolean A002 = r4.A00().A00(context, intent, null);
            AbstractC07830w5 r7 = this.A00;
            if (!A002) {
                if (!(r7 instanceof C07740vv)) {
                    str = "WorkConnectionConfigOverrides";
                } else {
                    str = "ZeroRatingConnectionConfigOverrides";
                }
                objArr = new Object[]{stringExtra, stringExtra2, stringExtra3, stringExtra4};
                str2 = "ignore unauthorized sender %s, %s, %s, %s";
            } else if (!r7.A01(stringExtra) || !r7.A01(stringExtra3)) {
                if (!(r7 instanceof C07740vv)) {
                    str = "WorkConnectionConfigOverrides";
                } else {
                    str = "ZeroRatingConnectionConfigOverrides";
                }
                objArr = new Object[]{stringExtra, stringExtra2, stringExtra3, stringExtra4};
                str2 = "ignore illegal target endpoint switch %s, %s, %s, %s";
            } else {
                Integer num = r7.A04;
                if (num == AnonymousClass007.A01 || num == AnonymousClass007.A0C) {
                    if (!TextUtils.isEmpty(stringExtra3)) {
                        stringExtra = stringExtra3;
                    }
                    if (!TextUtils.isEmpty(stringExtra4)) {
                        stringExtra2 = stringExtra4;
                    }
                }
                String str3 = r7.A06;
                if (str3 != null ? str3.equals(stringExtra) : stringExtra == null) {
                    String str4 = r7.A05;
                    if (str4 == null) {
                        if (stringExtra2 == null) {
                            return;
                        }
                    } else if (str4.equals(stringExtra2)) {
                        return;
                    }
                }
                if (!(r7 instanceof C07740vv)) {
                    C07720vq A2E = r7.A02.A00(EnumC07690vn.LAST_HOST).A2E();
                    A2E.A00.putString("work_last_host", stringExtra);
                    A2E.A00.putString("work_last_analytics_endpoint", stringExtra2);
                    A2E.A00();
                } else {
                    C07720vq A2E2 = r7.A02.A00(EnumC07690vn.LAST_HOST).A2E();
                    A2E2.A00.putString("zero_rating_last_host", stringExtra);
                    A2E2.A00.putLong("zero_rating_last_host_timestamp", System.currentTimeMillis());
                    A2E2.A00();
                }
                r7.A06 = stringExtra;
                r7.A05 = stringExtra2;
                Context context2 = ((AnonymousClass0v5) r7.A03).A00;
                context2.sendBroadcast(new Intent("com.facebook.rti.mqtt.ACTION_MQTT_CONFIG_CHANGED").setPackage(context2.getPackageName()));
                return;
            }
            AnonymousClass0NK.A07(str, str2, objArr);
            return;
        }
        throw new IllegalArgumentException();
    }
}
