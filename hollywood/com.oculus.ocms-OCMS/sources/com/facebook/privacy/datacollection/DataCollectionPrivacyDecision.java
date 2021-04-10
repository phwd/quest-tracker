package com.facebook.privacy.datacollection;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class DataCollectionPrivacyDecision {
    private final Map<String, DataCollectionMitigation> mMitigations = new LinkedHashMap();
    private final String mPdNumber;

    public DataCollectionPrivacyDecision(String str, List<DataCollectionMitigation> list) {
        this.mPdNumber = str.toUpperCase(Locale.US);
        for (DataCollectionMitigation dataCollectionMitigation : list) {
            this.mMitigations.put(dataCollectionMitigation.getNumber(), dataCollectionMitigation);
        }
    }

    public Optional<DataCollectionMitigation> getMitigation(String str) {
        return Optional.ofNullable(this.mMitigations.get(str));
    }

    public Collection<DataCollectionMitigation> getMitigations() {
        return this.mMitigations.values();
    }

    public void addMitigation(DataCollectionMitigation dataCollectionMitigation) {
        this.mMitigations.put(dataCollectionMitigation.getNumber(), dataCollectionMitigation);
    }

    public String getNumber() {
        return this.mPdNumber;
    }
}
