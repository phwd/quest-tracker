package X;

/* renamed from: X.23L  reason: invalid class name */
public class AnonymousClass23L implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.mqtt.client.internal.MqttClientImpl$15";
    public final /* synthetic */ AnonymousClass22G A00;
    public final /* synthetic */ String A01;

    public AnonymousClass23L(AnonymousClass22G r1, String str) {
        this.A00 = r1;
        this.A01 = str;
    }

    public final void run() {
        if (this.A00.A0D != null) {
            String str = this.A01;
            if (str.startsWith("PUBLISH_")) {
                str = str.substring(8);
            }
            AnonymousClass1N0.A00(str);
        }
    }
}
