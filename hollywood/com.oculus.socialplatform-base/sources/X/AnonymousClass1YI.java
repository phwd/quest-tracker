package X;

import android.content.IntentFilter;
import com.facebook.rti.mqtt.common.hardware.MqttNetworkChangeListener;

/* renamed from: X.1YI  reason: invalid class name */
public class AnonymousClass1YI implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.mqtt.client.internal.MqttClientImpl$1";
    public final /* synthetic */ AnonymousClass22G A00;

    public AnonymousClass1YI(AnonymousClass22G r1) {
        this.A00 = r1;
    }

    public final void run() {
        AnonymousClass22G r7 = this.A00;
        Integer num = AnonymousClass007.A00;
        if (!r7.A0F) {
            r7.A0F = true;
            AnonymousClass22H r4 = r7.A0A;
            AnonymousClass1XN r3 = new AnonymousClass1XN(r4);
            r4.A04 = r3;
            r4.A05.registerReceiver(r3, new IntentFilter("android.os.action.POWER_SAVE_MODE_CHANGED"), null, r4.A06);
            AnonymousClass1XO r32 = new AnonymousClass1XO(r4);
            r4.A03 = r32;
            r4.A05.registerReceiver(r32, new IntentFilter("com.facebook.rti.mqtt.ACTION_MQTT_CONFIG_CHANGED"), null, r4.A06);
            AnonymousClass1QS r2 = r4.A0H;
            MqttNetworkChangeListener mqttNetworkChangeListener = r4.A0d;
            synchronized (r2) {
                r2.A04.add(mqttNetworkChangeListener);
            }
            AnonymousClass1PI r1 = r4.A0O;
            if (((AnonymousClass1Q1) r1).A00 == null) {
                AnonymousClass0Uh r33 = new AnonymousClass0Uh("com.facebook.rti.mqtt.ACTION_ZR_SWITCH", new AnonymousClass1Q2(r1));
                ((AnonymousClass1Q1) r1).A00 = r33;
                r1.A01.registerReceiver(r33, new IntentFilter("com.facebook.rti.mqtt.ACTION_ZR_SWITCH"), "com.facebook.permission.prod.FB_APP_COMMUNICATION", null);
            }
        }
        r7.A0A.A0A(num);
    }
}
