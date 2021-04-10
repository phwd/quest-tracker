package X;

import com.facebook.msys.mcd.MqttNetworkSessionPlugin;

/* renamed from: X.221  reason: invalid class name */
public class AnonymousClass221 extends AnonymousClass1Kd {
    public static final String __redex_internal_original_name = "com.facebook.msys.mcd.MqttNetworkSessionPlugin$2";
    public final /* synthetic */ MqttNetworkSessionPlugin A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass221(MqttNetworkSessionPlugin mqttNetworkSessionPlugin) {
        super("onMqttConnecting");
        this.A00 = mqttNetworkSessionPlugin;
    }

    public final void run() {
        MqttNetworkSessionPlugin.onMqttConnecting();
    }
}
