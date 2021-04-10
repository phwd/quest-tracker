package X;

import android.os.Bundle;
import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.vM  reason: case insensitive filesystem */
public final class C1207vM implements AbstractC0388Vb {
    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        return new C0806hu(bundle.getString("transcriptionResponse", OacrConstants.AUTO_SPEECH_DOMAIN), bundle.getString("interactionId", OacrConstants.AUTO_SPEECH_DOMAIN), null);
    }
}
