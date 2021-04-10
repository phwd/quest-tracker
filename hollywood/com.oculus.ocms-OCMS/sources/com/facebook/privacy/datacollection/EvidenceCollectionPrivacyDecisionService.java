package com.facebook.privacy.datacollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class EvidenceCollectionPrivacyDecisionService {
    private static final ArrayList<DataCollectionMitigation> HARDCODED_MITIGATIONS = new ArrayList<>(Arrays.asList(new DataCollectionMitigation("M136519", "L328757PRV", new PrivacyConcepts(false, SemanticType.LOCATION_COUNTRY, false)), new DataCollectionMitigation("M147073", "L22184PRV5", new PrivacyConcepts(true, SemanticType.LOCATION_COUNTRY, true)), new DataCollectionMitigation("M148412", "L26395PRV", new PrivacyConcepts(true, SemanticType.LOCATION_PRECISE, false))));
    private static final Map<String, String> mMitigationToPrivacyDecisionNumbers = Collections.synchronizedMap(initMitigationToPrivacyDecisionNumberMap());
    private static final Map<String, DataCollectionPrivacyDecision> mPdNumbersToPrivacyDecisions = Collections.synchronizedMap(initPrivacyDecisionMap());

    public static Optional<DataCollectionPrivacyDecision> getPrivacyDecision(String str) {
        return Optional.ofNullable(mPdNumbersToPrivacyDecisions.get(str));
    }

    public static void addPrivacyDecision(String str, DataCollectionPrivacyDecision dataCollectionPrivacyDecision) {
        mPdNumbersToPrivacyDecisions.put(str, dataCollectionPrivacyDecision);
        for (DataCollectionMitigation dataCollectionMitigation : dataCollectionPrivacyDecision.getMitigations()) {
            mMitigationToPrivacyDecisionNumbers.put(dataCollectionMitigation.getNumber(), dataCollectionPrivacyDecision.getNumber());
        }
    }

    public static Optional<DataCollectionMitigation> getPrivacyMitigation(String str) {
        String str2 = mMitigationToPrivacyDecisionNumbers.get(str);
        if (str2 == null) {
            return Optional.empty();
        }
        DataCollectionPrivacyDecision dataCollectionPrivacyDecision = mPdNumbersToPrivacyDecisions.get(str2);
        if (dataCollectionPrivacyDecision == null) {
            return Optional.empty();
        }
        return dataCollectionPrivacyDecision.getMitigation(str);
    }

    private static Map<String, DataCollectionPrivacyDecision> initPrivacyDecisionMap() {
        HashMap hashMap = new HashMap();
        Iterator<DataCollectionMitigation> it = HARDCODED_MITIGATIONS.iterator();
        while (it.hasNext()) {
            DataCollectionMitigation next = it.next();
            DataCollectionPrivacyDecision dataCollectionPrivacyDecision = (DataCollectionPrivacyDecision) hashMap.get(next.getPrivacyDecisionNumber());
            if (dataCollectionPrivacyDecision == null) {
                DataCollectionPrivacyDecision dataCollectionPrivacyDecision2 = new DataCollectionPrivacyDecision(next.getPrivacyDecisionNumber(), Arrays.asList(next));
                hashMap.put(dataCollectionPrivacyDecision2.getNumber(), dataCollectionPrivacyDecision2);
            } else {
                dataCollectionPrivacyDecision.addMitigation(next);
            }
        }
        return hashMap;
    }

    private static Map<String, String> initMitigationToPrivacyDecisionNumberMap() {
        HashMap hashMap = new HashMap();
        Iterator<DataCollectionMitigation> it = HARDCODED_MITIGATIONS.iterator();
        while (it.hasNext()) {
            DataCollectionMitigation next = it.next();
            hashMap.put(next.getNumber(), next.getPrivacyDecisionNumber());
        }
        return hashMap;
    }
}
