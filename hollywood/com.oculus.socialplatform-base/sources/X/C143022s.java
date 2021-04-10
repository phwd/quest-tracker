package X;

import com.facebook.rti.mqtt.protocol.messages.MqttPublishRequestBody;
import java.util.List;
import javax.annotation.Nullable;

/* renamed from: X.22s  reason: invalid class name and case insensitive filesystem */
public final class C143022s {
    public String A00;
    public final AnonymousClass22V A01;
    public final String A02;
    public final String A03;
    public final String A04;
    public final String A05;
    @Nullable
    public final List<MqttPublishRequestBody> A06;

    public final String toString() {
        StringBuilder sb = new StringBuilder("{clientIdentifier='");
        sb.append(this.A02);
        sb.append("', willTopic='");
        sb.append(this.A05);
        sb.append("', willMessage='");
        sb.append(this.A04);
        sb.append("', userName='");
        sb.append(this.A01);
        sb.append("', phpOverride='");
        sb.append(this.A00);
        sb.append("'");
        sb.append('}');
        return sb.toString();
    }

    public C143022s(String str, AnonymousClass22V r3, String str2, @Nullable List<MqttPublishRequestBody> list) {
        this.A02 = str;
        this.A06 = list;
        this.A05 = null;
        this.A04 = null;
        this.A01 = r3;
        this.A03 = str2;
    }

    public C143022s(String str, String str2, String str3, AnonymousClass22V r4, String str4, List<MqttPublishRequestBody> list) {
        this.A02 = str;
        this.A05 = str2;
        this.A04 = str3;
        this.A01 = r4;
        this.A03 = str4;
        this.A06 = list;
    }
}
