package X;

import android.os.Bundle;
import android.util.Log;

/* renamed from: X.vQ  reason: case insensitive filesystem */
public final class C1211vQ implements AbstractC0388Vb {
    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        if (bundle.getString("feedback") != null && bundle.getString("interactionId") != null) {
            return new C0775h2(bundle.getString("feedback"), bundle.getString("interactionId"));
        }
        Log.e("SubmitTranscriptionFeedbackMessage", "Missing fields from bundle.");
        return null;
    }
}
