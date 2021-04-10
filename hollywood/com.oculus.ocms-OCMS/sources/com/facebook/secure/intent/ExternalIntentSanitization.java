package com.facebook.secure.intent;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import com.facebook.secure.logger.Reporter;
import java.util.ArrayList;
import java.util.Set;

/* access modifiers changed from: package-private */
public class ExternalIntentSanitization {
    private static final String TAG = "ExternalIntentSanitization";
    @VisibleForTesting
    static InternalExtraDetector sInternalExtraDetector = new InternalExtraDetector() {
        /* class com.facebook.secure.intent.ExternalIntentSanitization.AnonymousClass1 */

        @Override // com.facebook.secure.intent.ExternalIntentSanitization.InternalExtraDetector
        public boolean isInternalExtra(Object obj) {
            return ExternalIntentScope.class.getClassLoader().equals(obj.getClass().getClassLoader());
        }
    };

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public interface InternalExtraDetector {
        boolean isInternalExtra(Object obj);
    }

    ExternalIntentSanitization() {
    }

    public static Intent sanitize(Intent intent, Reporter reporter, boolean z) {
        if (intent.getExtras() == null) {
            return intent;
        }
        return enforceExternalExtra(intent, reporter, z);
    }

    private static Intent enforceExternalExtra(Intent intent, Reporter reporter, boolean z) {
        Bundle extras = intent.getExtras();
        Set<String> keySet = extras.keySet();
        ArrayList<String> arrayList = new ArrayList();
        for (String str : keySet) {
            Object obj = extras.get(str);
            if (obj != null && sInternalExtraDetector.isInternalExtra(obj)) {
                arrayList.add(str);
            }
        }
        for (String str2 : arrayList) {
            if (!z) {
                intent.removeExtra(str2);
                reporter.report(TAG, String.format("Removed an internal class in a different-key intent: %s => %s", str2, extras.get(str2)), null);
            } else {
                reporter.report(TAG, String.format("Found an internal class in a different-key intent but not removing: %s => %s", str2, extras.get(str2)), null);
            }
        }
        return intent;
    }
}
