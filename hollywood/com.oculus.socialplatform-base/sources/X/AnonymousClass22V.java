package X;

import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONObject;

/* renamed from: X.22V  reason: invalid class name */
public final class AnonymousClass22V {
    public final int A00;
    @Nullable
    public final AnonymousClass230 A01;
    public final Boolean A02;
    public final Boolean A03;
    @Nullable
    public final Boolean A04;
    @Nullable
    public final Byte A05;
    @Nullable
    public final Integer A06;
    @Nullable
    public final Integer A07;
    @Nullable
    public final Long A08;
    @Nullable
    public final Long A09;
    @Nullable
    public final Long A0A;
    @Nullable
    public final Long A0B;
    @Nullable
    public final Long A0C;
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
    @Nullable
    public final String A0J;
    public final List<String> A0K;
    @Nullable
    public final Map<String, String> A0L;

    public final String toString() {
        StringBuilder sb = new StringBuilder("ConnectPayloadUserName {");
        sb.append("user_id = <redacted>, ");
        sb.append("user_agent = ");
        sb.append(this.A0J);
        sb.append(", ");
        sb.append("capabilities = ");
        sb.append(this.A08);
        sb.append(", ");
        sb.append("mqtt_session_id = ");
        sb.append(this.A0B);
        sb.append(", ");
        sb.append("network_type = ");
        Integer num = this.A07;
        sb.append(num);
        sb.append(", ");
        sb.append("network_subtype = ");
        sb.append(num);
        sb.append(", ");
        sb.append("chat_on = ");
        sb.append(this.A02);
        sb.append(", ");
        sb.append("no_auto_fg = ");
        sb.append(this.A04);
        sb.append(", ");
        sb.append("device_client_id = <redacted>");
        sb.append(", ");
        sb.append("device_client_secret = <redacted>");
        sb.append(", ");
        sb.append("fg_keepalive = ");
        sb.append(this.A03);
        sb.append(", ");
        sb.append("client_type = ");
        sb.append(this.A0E);
        sb.append(", ");
        sb.append("app_id = ");
        sb.append(this.A0D);
        sb.append(", ");
        sb.append("connect_payload_hash = ");
        sb.append(this.A0F);
        sb.append("}");
        return sb.toString();
    }

    public AnonymousClass22V(@Nullable Long l, @Nullable String str, @Nullable Long l2, @Nullable Long l3, @Nullable Integer num, @Nullable Integer num2, Boolean bool, @Nullable Boolean bool2, @Nullable String str2, @Nullable String str3, Boolean bool3, @Nullable Long l4, int i, @Nullable String str4, @Nullable String str5, List<String> list, @Nullable String str6, @Nullable String str7, @Nullable Byte b, @Nullable Map<String, String> map, @Nullable Long l5, @Nullable AnonymousClass230 r23) {
        this.A0C = l;
        this.A0J = str;
        this.A08 = l2;
        this.A0B = l3;
        this.A07 = num;
        this.A06 = num2;
        this.A02 = bool;
        this.A04 = bool2;
        this.A0H = str2;
        this.A0I = str3;
        this.A03 = bool3;
        this.A0A = l4;
        this.A00 = i;
        this.A0E = str4;
        this.A0D = str5;
        this.A0K = list;
        this.A0F = str6;
        this.A0G = str7;
        this.A05 = b;
        this.A0L = map;
        this.A09 = l5;
        this.A01 = r23;
    }

    @Nullable
    public static Boolean A00(JSONObject jSONObject, EnumC141722f r2) {
        if (!jSONObject.has(r2.getJsonKey())) {
            return null;
        }
        return Boolean.valueOf(jSONObject.optBoolean(r2.getJsonKey()));
    }

    @Nullable
    public static Long A01(JSONObject jSONObject, EnumC141722f r2) {
        if (!jSONObject.has(r2.getJsonKey())) {
            return null;
        }
        return Long.valueOf(jSONObject.optLong(r2.getJsonKey()));
    }

    @Nullable
    public static String A02(JSONObject jSONObject, EnumC141722f r2) {
        if (!jSONObject.has(r2.getJsonKey())) {
            return null;
        }
        return jSONObject.optString(r2.getJsonKey());
    }
}
