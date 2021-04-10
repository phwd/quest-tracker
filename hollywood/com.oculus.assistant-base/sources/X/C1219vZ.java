package X;

import android.os.Bundle;
import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.vZ  reason: case insensitive filesystem */
public final class C1219vZ implements AbstractC0388Vb {
    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        String[] split;
        String string = bundle.getString("suggestedWord", OacrConstants.AUTO_SPEECH_DOMAIN);
        if (bundle.containsKey("otherSuggestionsList")) {
            split = bundle.getStringArray("otherSuggestionsList");
        } else {
            split = bundle.getString("otherSuggestions", OacrConstants.AUTO_SPEECH_DOMAIN).split(";");
        }
        if (split == null) {
            split = new String[0];
        }
        int length = split.length + (!string.isEmpty());
        boolean[] booleanArray = bundle.getBooleanArray("fromSupplementalDict");
        if (booleanArray == null) {
            booleanArray = new boolean[length];
        }
        return new C0776h5(string, split, booleanArray);
    }
}
