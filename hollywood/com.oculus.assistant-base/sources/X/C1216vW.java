package X;

import android.os.Bundle;
import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.vW  reason: case insensitive filesystem */
public final class C1216vW implements AbstractC0388Vb {
    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        return new h1(bundle.getString("inputText", OacrConstants.AUTO_SPEECH_DOMAIN), bundle.getString("requestingPanel", OacrConstants.AUTO_SPEECH_DOMAIN));
    }
}
