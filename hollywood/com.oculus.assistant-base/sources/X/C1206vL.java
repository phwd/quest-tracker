package X;

import android.os.Bundle;
import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.vL  reason: case insensitive filesystem */
public final class C1206vL implements AbstractC0388Vb {
    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        return new C0811hz(bundle.getString("transcriptionResponse", OacrConstants.AUTO_SPEECH_DOMAIN));
    }
}
