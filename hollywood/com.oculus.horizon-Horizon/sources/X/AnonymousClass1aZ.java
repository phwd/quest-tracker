package X;

import android.text.TextUtils;
import com.oculus.horizon.linkedaccounts.dumper.LinkedAccountsDumperPlugin;
import java.util.ArrayList;
import java.util.Collections;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.1aZ  reason: invalid class name */
public final class AnonymousClass1aZ {
    public static final AnonymousClass1XQ A04 = new AnonymousClass1XQ(Collections.emptyList(), new ArrayList());
    @Nullable
    public String A00;
    @Nullable
    public String A01;
    @Nullable
    public String A02;
    @Nullable
    public String A03;

    public final JSONObject A00() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.A03)) {
                jSONObject.put("scheme", this.A03);
            }
            if (!TextUtils.isEmpty(this.A00)) {
                jSONObject.put("authority", this.A00);
            }
            if (!TextUtils.isEmpty(this.A01)) {
                jSONObject.put("path", this.A01);
            }
            if (!TextUtils.isEmpty(this.A02)) {
                jSONObject.put(LinkedAccountsDumperPlugin.COMMAND_QUERY, this.A02);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public AnonymousClass1aZ(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        this.A03 = str;
        this.A00 = str2;
        this.A01 = str3;
        this.A02 = str4;
    }
}
