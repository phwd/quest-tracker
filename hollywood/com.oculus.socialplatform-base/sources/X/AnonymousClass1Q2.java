package X;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* renamed from: X.1Q2  reason: invalid class name */
public class AnonymousClass1Q2 implements AbstractC02700jf {
    public final /* synthetic */ AnonymousClass1Q1 A00;

    public AnonymousClass1Q2(AnonymousClass1Q1 r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC02700jf
    public final void onReceive(Context context, Intent intent, AnonymousClass0jg r12) {
        String str;
        Object[] objArr;
        String str2;
        SharedPreferences.Editor editor;
        String stringExtra = intent.getStringExtra("extra_mqtt_endpoint");
        String stringExtra2 = intent.getStringExtra("extra_analytics_endpoint");
        String stringExtra3 = intent.getStringExtra("extra_fbns_endpoint");
        String stringExtra4 = intent.getStringExtra("extra_fbns_analytics_endpoint");
        AnonymousClass0kQ r4 = new AnonymousClass0kQ();
        r4.A00 |= 1;
        if (!TextUtils.isEmpty("MQTT_CONFIG_CHANGE_DOMAIN")) {
            r4.A02.add("MQTT_CONFIG_CHANGE_DOMAIN");
            boolean A01 = r4.A00().A01(context, intent, null);
            AnonymousClass1Q1 r7 = this.A00;
            if (!A01) {
                if (!(r7 instanceof AnonymousClass1PI)) {
                    str = "WorkConnectionConfigOverrides";
                } else {
                    str = "ZeroRatingConnectionConfigOverrides";
                }
                objArr = new Object[]{stringExtra, stringExtra2, stringExtra3, stringExtra4};
                str2 = "ignore unauthorized sender %s, %s, %s, %s";
            } else if (!r7.A01(stringExtra) || !r7.A01(stringExtra3)) {
                if (!(r7 instanceof AnonymousClass1PI)) {
                    str = "WorkConnectionConfigOverrides";
                } else {
                    str = "ZeroRatingConnectionConfigOverrides";
                }
                objArr = new Object[]{stringExtra, stringExtra2, stringExtra3, stringExtra4};
                str2 = "ignore illegal target endpoint switch %s, %s, %s, %s";
            } else {
                Integer num = r7.A04;
                if (num == AnonymousClass007.A01 || num == AnonymousClass007.A03) {
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
                if (!(r7 instanceof AnonymousClass1PI)) {
                    AnonymousClass1PB A002 = r7.A02.A00(AnonymousClass1PL.LAST_HOST).A00();
                    A002.A00.putString("work_last_host", stringExtra);
                    A002.A00.putString("work_last_analytics_endpoint", stringExtra2);
                    editor = A002.A00;
                } else {
                    AnonymousClass1PB A003 = r7.A02.A00(AnonymousClass1PL.LAST_HOST).A00();
                    A003.A00.putString("zero_rating_last_host", stringExtra);
                    A003.A00.putLong("zero_rating_last_host_timestamp", System.currentTimeMillis());
                    editor = A003.A00;
                }
                editor.apply();
                r7.A06 = stringExtra;
                r7.A05 = stringExtra2;
                AnonymousClass22G r2 = ((AnonymousClass1YH) r7.A03).A00;
                Intent intent2 = new Intent("com.facebook.rti.mqtt.ACTION_MQTT_CONFIG_CHANGED");
                intent2.setPackage(r2.A00.getPackageName());
                r2.A00.sendBroadcast(intent2);
                return;
            }
            AnonymousClass0MD.A0A(str, str2, objArr);
            return;
        }
        throw new IllegalArgumentException();
    }
}
