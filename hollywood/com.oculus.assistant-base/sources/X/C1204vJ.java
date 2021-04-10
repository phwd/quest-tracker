package X;

import android.os.Bundle;
import com.facebook.acra.CrashTimeDataCollector;
import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.vJ  reason: case insensitive filesystem */
public final class C1204vJ implements AbstractC0106Ak, AbstractC0388Vb {
    public final String A00;
    public final String A01;

    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        String str = OacrConstants.AUTO_SPEECH_DOMAIN;
        String str2 = CrashTimeDataCollector.ANDROID_RUNTIME_UNKNOWN;
        if (bundle != null) {
            str2 = bundle.getString("errorType", str2);
            str = bundle.getString("errorMessage", str);
        }
        return new C1204vJ(str2, str);
    }

    public C1204vJ(String str, String str2) {
        this.A01 = str;
        this.A00 = str2;
    }

    @Override // X.AbstractC0106Ak
    public final Object A2L() {
        return this;
    }
}
