package X;

import android.os.Bundle;
import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.vV  reason: case insensitive filesystem */
public final class C1215vV implements AbstractC0388Vb {
    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        String str = OacrConstants.AUTO_SPEECH_DOMAIN;
        if (bundle != null && bundle.containsKey("inputText")) {
            str = bundle.getString("inputText", str);
        }
        return new C0773gz(str);
    }
}
