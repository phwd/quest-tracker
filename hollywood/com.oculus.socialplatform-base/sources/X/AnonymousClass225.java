package X;

import com.facebook.msys.mcd.MqttNetworkSessionPlugin;

/* renamed from: X.225  reason: invalid class name */
public class AnonymousClass225 extends AnonymousClass1Kd {
    public static final String __redex_internal_original_name = "com.facebook.msys.mcd.MqttNetworkSessionPlugin$6";
    public final /* synthetic */ int A00;
    public final /* synthetic */ MqttNetworkSessionPlugin A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass225(MqttNetworkSessionPlugin mqttNetworkSessionPlugin, int i) {
        super("onMqttPubAckTimeout");
        this.A01 = mqttNetworkSessionPlugin;
        this.A00 = i;
    }

    public final void run() {
        MqttNetworkSessionPlugin.onMqttPubAckTimeout(this.A00);
    }
}
