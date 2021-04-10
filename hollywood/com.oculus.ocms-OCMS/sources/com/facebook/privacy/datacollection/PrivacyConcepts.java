package com.facebook.privacy.datacollection;

import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class PrivacyConcepts {
    private final boolean mApproved;
    private final boolean mBackground;
    private final SemanticType mSemanticType;

    public PrivacyConcepts(boolean z, SemanticType semanticType, boolean z2) {
        this.mApproved = z;
        this.mSemanticType = semanticType;
        this.mBackground = z2;
    }

    public boolean isCollectionApproved() {
        return this.mApproved;
    }

    public SemanticType getSemanticType() {
        return this.mSemanticType;
    }

    public boolean isBackgroundAllowed() {
        return this.mBackground;
    }

    public PrivacyDecisionMatchResult matches(PrivacyConcepts privacyConcepts) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        if (this.mApproved != privacyConcepts.isCollectionApproved()) {
            arrayList.add("Error: Data collection approvement doesn't match!");
            z = false;
        } else {
            z = true;
        }
        if (this.mSemanticType != privacyConcepts.getSemanticType()) {
            arrayList.add("Error: Semantic type doesn't match!");
            z = false;
        }
        if (this.mBackground != privacyConcepts.isBackgroundAllowed()) {
            arrayList.add("Error: Background allowance doesn't match!");
            z = false;
        }
        return new PrivacyDecisionMatchResult(z, String.join("\n", arrayList));
    }
}
