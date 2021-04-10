package com.facebook.privacy.datacollection;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class EvidenceCollectionPrivacyDecisionHelper {
    public static Optional<Collection<DataCollectionMitigation>> getPrivacyMitigations(String str) {
        Optional<DataCollectionPrivacyDecision> privacyDecision = EvidenceCollectionPrivacyDecisionService.getPrivacyDecision(str);
        if (privacyDecision.isPresent()) {
            return Optional.of(privacyDecision.get().getMitigations());
        }
        return Optional.empty();
    }

    public static Optional<DataCollectionMitigation> getPrivacyMitigation(String str) {
        return EvidenceCollectionPrivacyDecisionService.getPrivacyMitigation(str);
    }

    public static void addPrivacyDecision(String str, List<DataCollectionMitigation> list) {
        DataCollectionPrivacyDecision dataCollectionPrivacyDecision = new DataCollectionPrivacyDecision(str, list);
        EvidenceCollectionPrivacyDecisionService.addPrivacyDecision(dataCollectionPrivacyDecision.getNumber(), dataCollectionPrivacyDecision);
    }

    public static PrivacyDecisionMatchResult isAccessAllowed(String str, PrivacyConcepts privacyConcepts) {
        Optional<Collection<DataCollectionMitigation>> privacyMitigations = getPrivacyMitigations(str);
        if (!privacyMitigations.isPresent()) {
            return new PrivacyDecisionMatchResult(false, "Error: Privacy Decision doesn't exist!");
        }
        for (DataCollectionMitigation dataCollectionMitigation : privacyMitigations.get()) {
            if (dataCollectionMitigation.getPrivacyConcepts().getSemanticType() == privacyConcepts.getSemanticType()) {
                return dataCollectionMitigation.getPrivacyConcepts().matches(privacyConcepts);
            }
        }
        return new PrivacyDecisionMatchResult(false, "Error: No mitigation has the same Semantic type as the request");
    }
}
