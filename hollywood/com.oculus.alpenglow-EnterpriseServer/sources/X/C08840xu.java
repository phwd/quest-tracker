package X;

import com.facebook.rti.mqtt.protocol.messages.MqttPublishRequestBody;
import java.util.List;
import javax.annotation.Nullable;

/* renamed from: X.0xu  reason: invalid class name and case insensitive filesystem */
public final class C08840xu {
    public String A00;
    public byte[] A01;
    public final C08650xb A02;
    public final String A03;
    public final String A04;
    public final String A05;
    public final String A06;
    @Nullable
    public final List<MqttPublishRequestBody> A07;

    public final String toString() {
        return "{clientIdentifier='" + this.A03 + "', willTopic='" + this.A06 + "', willMessage='" + this.A05 + "', userName='" + this.A02 + "', phpOverride='" + this.A00 + "'" + '}';
    }

    public C08840xu(String str, C08650xb r3, String str2, @Nullable List<MqttPublishRequestBody> list) {
        this.A03 = str;
        this.A07 = list;
        this.A06 = null;
        this.A05 = null;
        this.A02 = r3;
        this.A04 = str2;
    }

    public C08840xu(String str, String str2, String str3, C08650xb r4, String str4, List<MqttPublishRequestBody> list) {
        this.A03 = str;
        this.A06 = str2;
        this.A05 = str3;
        this.A02 = r4;
        this.A04 = str4;
        this.A07 = list;
    }
}
