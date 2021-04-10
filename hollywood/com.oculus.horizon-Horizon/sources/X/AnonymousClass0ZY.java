package X;

import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONObject;

/* renamed from: X.0ZY  reason: invalid class name */
public final class AnonymousClass0ZY {
    public final int A00;
    public final Boolean A01;
    public final Boolean A02;
    @Nullable
    public final Boolean A03;
    @Nullable
    public final Byte A04;
    @Nullable
    public final Integer A05;
    @Nullable
    public final Integer A06;
    @Nullable
    public final Long A07;
    @Nullable
    public final Long A08;
    @Nullable
    public final Long A09;
    @Nullable
    public final Long A0A;
    @Nullable
    public final Long A0B;
    @Nullable
    public final String A0C;
    @Nullable
    public final String A0D;
    @Nullable
    public final String A0E;
    @Nullable
    public final String A0F;
    @Nullable
    public final String A0G;
    @Nullable
    public final String A0H;
    @Nullable
    public final String A0I;
    public final List<String> A0J;
    @Nullable
    public final Map<String, String> A0K;

    public final String toString() {
        StringBuilder sb = new StringBuilder("ConnectPayloadUserName {");
        sb.append("user_id = <redacted>, ");
        sb.append("user_agent = ");
        sb.append(this.A0I);
        sb.append(", ");
        sb.append("capabilities = ");
        sb.append(this.A07);
        sb.append(", ");
        sb.append("mqtt_session_id = ");
        sb.append(this.A0A);
        sb.append(", ");
        sb.append("network_type = ");
        Integer num = this.A06;
        sb.append(num);
        sb.append(", ");
        sb.append("network_subtype = ");
        sb.append(num);
        sb.append(", ");
        sb.append("chat_on = ");
        sb.append(this.A01);
        sb.append(", ");
        sb.append("no_auto_fg = ");
        sb.append(this.A03);
        sb.append(", ");
        sb.append("device_client_id = <redacted>");
        sb.append(", ");
        sb.append("device_client_secret = <redacted>");
        sb.append(", ");
        sb.append("fg_keepalive = ");
        sb.append(this.A02);
        sb.append(", ");
        sb.append("client_type = ");
        sb.append(this.A0D);
        sb.append(", ");
        sb.append("app_id = ");
        sb.append(this.A0C);
        sb.append(", ");
        sb.append("connect_payload_hash = ");
        sb.append(this.A0E);
        sb.append("}");
        return sb.toString();
    }

    @Nullable
    public static Boolean A00(JSONObject jSONObject, AnonymousClass0ZU r2) {
        if (!jSONObject.has(r2.getJsonKey())) {
            return null;
        }
        return Boolean.valueOf(jSONObject.optBoolean(r2.getJsonKey()));
    }

    @Nullable
    public static Long A01(JSONObject jSONObject, AnonymousClass0ZU r2) {
        if (!jSONObject.has(r2.getJsonKey())) {
            return null;
        }
        return Long.valueOf(jSONObject.optLong(r2.getJsonKey()));
    }

    @Nullable
    public static String A02(JSONObject jSONObject, AnonymousClass0ZU r2) {
        if (!jSONObject.has(r2.getJsonKey())) {
            return null;
        }
        return jSONObject.optString(r2.getJsonKey());
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Long;ILjava/lang/String;Ljava/lang/String;Ljava/util/List<Ljava/lang/String;>;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Byte;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;Ljava/lang/Long;Lcom/facebook/mqtt/model/thrift/NetworkTypeInfo;)V */
    public AnonymousClass0ZY(@Nullable Long l, @Nullable String str, @Nullable Long l2, @Nullable Long l3, @Nullable Integer num, @Nullable Integer num2, Boolean bool, @Nullable Boolean bool2, @Nullable String str2, @Nullable String str3, Boolean bool3, @Nullable Long l4, int i, @Nullable String str4, @Nullable String str5, List list, @Nullable String str6, @Nullable String str7, @Nullable Byte b, @Nullable Map map, @Nullable Long l5) {
        this.A0B = l;
        this.A0I = str;
        this.A07 = l2;
        this.A0A = l3;
        this.A06 = num;
        this.A05 = num2;
        this.A01 = bool;
        this.A03 = bool2;
        this.A0G = str2;
        this.A0H = str3;
        this.A02 = bool3;
        this.A09 = l4;
        this.A00 = i;
        this.A0D = str4;
        this.A0C = str5;
        this.A0J = list;
        this.A0E = str6;
        this.A0F = str7;
        this.A04 = b;
        this.A0K = map;
        this.A08 = l5;
    }
}
