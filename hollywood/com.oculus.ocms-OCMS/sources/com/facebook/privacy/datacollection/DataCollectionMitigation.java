package com.facebook.privacy.datacollection;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Locale;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class DataCollectionMitigation {
    private final String mNumber;
    private final PrivacyConcepts mPrivacyConcepts;
    private final String mPrivacyDecisionNumber;

    public DataCollectionMitigation(String str, String str2, PrivacyConcepts privacyConcepts) {
        this.mNumber = str.toUpperCase(Locale.US);
        this.mPrivacyDecisionNumber = str2;
        this.mPrivacyConcepts = privacyConcepts;
    }

    public String getNumber() {
        return this.mNumber;
    }

    public String getPrivacyDecisionNumber() {
        return this.mPrivacyDecisionNumber;
    }

    public PrivacyConcepts getPrivacyConcepts() {
        return this.mPrivacyConcepts;
    }
}
