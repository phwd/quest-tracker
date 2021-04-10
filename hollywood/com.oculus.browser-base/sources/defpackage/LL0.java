package defpackage;

import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

/* renamed from: LL0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class LL0 implements HL0 {
    public final Status F;

    public LL0(Status status, JSONObject jSONObject) {
        this.F = status;
    }

    @Override // defpackage.AM0
    public final Status b() {
        return this.F;
    }
}
