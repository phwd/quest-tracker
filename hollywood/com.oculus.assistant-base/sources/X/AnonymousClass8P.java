package X;

import android.media.AudioAttributes;
import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.assistant.oacr.config.OacrTrimSpec;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.Map;

@JsonAutoDetect(fieldVisibility = EnumC0242Mp.ANY, getterVisibility = EnumC0242Mp.NONE, setterVisibility = EnumC0242Mp.NONE)
/* renamed from: X.8P  reason: invalid class name */
public final class AnonymousClass8P {
    public static final AudioAttributes A0L = new AudioAttributes.Builder().setContentType(2).setUsage(1).build();
    public int A00 = 1024;
    public int A01;
    public AudioAttributes A02 = A0L;
    public BJ A03;
    @JsonIgnore
    public OacrTrimSpec A04 = BM.A00;
    @JsonIgnore
    public FQ A05 = new C0742gR();
    @JsonIgnore
    public C0881nS A06 = new C0881nS();
    @JsonIgnore
    public C0884nc A07 = new C0884nc();
    public String A08;
    public String A09;
    public String A0A = OacrConstants.DEVICE_TYPE;
    public String A0B = "facebook";
    public String A0C;
    public String A0D = "https://prod.facebookvirtualassistant.com";
    public String A0E = "dev.facebookvirtualassistant.com,staging.facebookvirtualassistant.com,prod.facebookvirtualassistant.com,internal.facebookvirtualassistant.com,assistant-internal.fbinfra.net,localhost";
    public String A0F;
    public String A0G;
    public String A0H = "wss://prod.facebookvirtualassistant.com/v2/vp/recognition";
    public Map A0I = new HashMap();
    public boolean A0J = true;
    public boolean A0K;

    public final String toString() {
        try {
            return new C1028qz(null).A00(this);
        } catch (NV e) {
            C0139Dd.A0L("AssistantClientPlatformConfig", "Error serializing", e);
            return "Error serializing";
        }
    }
}
