package X;

import com.facebook.msys.mcd.MqttNetworkSessionPlugin;

/* renamed from: X.223  reason: invalid class name */
public class AnonymousClass223 extends AnonymousClass1Kd {
    public static final String __redex_internal_original_name = "com.facebook.msys.mcd.MqttNetworkSessionPlugin$4";
    public final /* synthetic */ MqttNetworkSessionPlugin A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass223(MqttNetworkSessionPlugin mqttNetworkSessionPlugin) {
        super("onMqttDisconnected");
        this.A00 = mqttNetworkSessionPlugin;
    }

    public final void run() {
        MqttNetworkSessionPlugin.onMqttDisconnected();
    }
}
