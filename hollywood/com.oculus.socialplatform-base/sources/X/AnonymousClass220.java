package X;

import com.facebook.msys.mcd.MqttNetworkSessionPlugin;
import com.oculus.messengervr.fb.VrMsysMqttClientCallbacks;

/* renamed from: X.220  reason: invalid class name */
public class AnonymousClass220 extends AnonymousClass1Kd {
    public static final String __redex_internal_original_name = "com.facebook.msys.mcd.MqttNetworkSessionPlugin$1";
    public final /* synthetic */ MqttNetworkSessionPlugin A00;
    public final /* synthetic */ String A01 = VrMsysMqttClientCallbacks.MQTT_TOPIC_LS_RESP;
    public final /* synthetic */ byte[] A02;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass220(MqttNetworkSessionPlugin mqttNetworkSessionPlugin, byte[] bArr) {
        super("onMqttPublishReceived");
        this.A00 = mqttNetworkSessionPlugin;
        this.A02 = bArr;
    }

    public final void run() {
        MqttNetworkSessionPlugin.onMqttPublishReceived(this.A01, this.A02);
    }
}
