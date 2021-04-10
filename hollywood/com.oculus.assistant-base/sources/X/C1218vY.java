package X;

import android.os.Bundle;
import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.vY  reason: case insensitive filesystem */
public final class C1218vY implements AbstractC0388Vb {
    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        bundle.getString("inputContext", OacrConstants.AUTO_SPEECH_DOMAIN);
        return new h4(bundle.getString("inputText", OacrConstants.AUTO_SPEECH_DOMAIN), bundle.getString("requestingPanel", OacrConstants.AUTO_SPEECH_DOMAIN));
    }
}
