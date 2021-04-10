package X;

import com.facebook.rti.mqtt.protocol.messages.MqttPublishRequestBody;
import java.util.List;
import javax.annotation.Nullable;

/* renamed from: X.0ZX  reason: invalid class name */
public final class AnonymousClass0ZX {
    public String A00;
    public byte[] A01;
    public final AnonymousClass0ZY A02;
    public final String A03;
    public final String A04;
    public final String A05;
    public final String A06;
    @Nullable
    public final List<MqttPublishRequestBody> A07;

    public final String toString() {
        StringBuilder sb = new StringBuilder("{clientIdentifier='");
        sb.append(this.A03);
        sb.append("', willTopic='");
        sb.append(this.A06);
        sb.append("', willMessage='");
        sb.append(this.A05);
        sb.append("', userName='");
        sb.append(this.A02);
        sb.append("', phpOverride='");
        sb.append(this.A00);
        sb.append("'");
        sb.append('}');
        return sb.toString();
    }

    public AnonymousClass0ZX(String str, AnonymousClass0ZY r3, String str2, @Nullable List<MqttPublishRequestBody> list) {
        this.A03 = str;
        this.A07 = list;
        this.A06 = null;
        this.A05 = null;
        this.A02 = r3;
        this.A04 = str2;
    }

    public AnonymousClass0ZX(String str, String str2, String str3, AnonymousClass0ZY r4, String str4, List<MqttPublishRequestBody> list) {
        this.A03 = str;
        this.A06 = str2;
        this.A05 = str3;
        this.A02 = r4;
        this.A04 = str4;
        this.A07 = list;
    }
}
