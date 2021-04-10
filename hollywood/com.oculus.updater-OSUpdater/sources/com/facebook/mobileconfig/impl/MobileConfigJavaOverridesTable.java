package com.facebook.mobileconfig.impl;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.facebook.common.preconditions.Preconditions;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.mobileconfig.factory.MobileConfigOverridesTable;
import com.facebook.mobileconfig.metadata.ParamsMapEntry;
import com.facebook.mobileconfig.metadata.ParamsMapList;
import com.facebook.mobileconfig.specifier.MobileConfigKeyUtils;
import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigJavaOverridesTable implements MobileConfigOverridesTable {
    private final SparseArray<Boolean> mBoolOverrides = new SparseArray<>();
    private final SparseArray<Double> mDoubleOverrides = new SparseArray<>();
    private final SparseArray<Long> mLongOverrides = new SparseArray<>();
    private final SparseBooleanArray mNullOverrides = new SparseBooleanArray();
    private final SparseArray<String> mStringOverrides = new SparseArray<>();
    @Nullable
    private final long[][] mTranslationTable;

    private static int getNullOverrideKey(int i, int i2) {
        return (i << 20) + i2;
    }

    public MobileConfigJavaOverridesTable(String str, @Nullable long[][] jArr, ParamsMapList paramsMapList) {
        int i;
        String str2;
        int i2;
        ParamsMapEntry next;
        this.mTranslationTable = jArr;
        try {
            JSONObject jSONObject = new JSONObject(str);
            Map<String, Integer> configIndexes = paramsMapList.getConfigIndexes();
            Map<Integer, Integer> configIndexesFromKey = paramsMapList.getConfigIndexesFromKey();
            JSONArray jSONArray = (JSONArray) Preconditions.checkNotNull(jSONObject.names());
            char c = 0;
            int i3 = 0;
            while (i3 < jSONArray.length()) {
                String[] split = jSONArray.getString(i3).split(":", -1);
                char c2 = 1;
                if (split.length > 1) {
                    i = split[c].trim().isEmpty() ? 0 : Integer.parseInt(split[c].trim());
                    str2 = split[1].trim();
                } else {
                    str2 = split[c].trim();
                    i = 0;
                }
                if (MobileConfigKeyUtils.validConfigKey(i) && configIndexesFromKey.containsKey(Integer.valueOf(i))) {
                    i2 = configIndexesFromKey.get(Integer.valueOf(i)).intValue();
                } else if (!MobileConfigKeyUtils.validConfigName(str2) || !configIndexes.containsKey(str2)) {
                    i3++;
                    c = 0;
                } else {
                    i2 = configIndexes.get(str2).intValue();
                }
                JSONArray jSONArray2 = jSONObject.getJSONArray(jSONArray.getString(i3));
                List<ParamsMapEntry> entriesOfConfig = paramsMapList.entriesOfConfig(i2);
                int i4 = 0;
                while (i4 < jSONArray2.length()) {
                    String[] split2 = jSONArray2.getString(i4).split(": ", 3);
                    Integer valueOf = Integer.valueOf(Integer.parseInt(split2[c]));
                    String str3 = split2[c2];
                    Iterator<ParamsMapEntry> it = entriesOfConfig.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        next = it.next();
                        if ((MobileConfigKeyUtils.isFakeParamKey(valueOf.intValue()) || next.key != valueOf.intValue()) && (!MobileConfigKeyUtils.validParamName(str3) || !next.paramName.equals(str3))) {
                        }
                    }
                    String str4 = split2[2];
                    if (str4.equals("__NULL_VALUE__")) {
                        this.mNullOverrides.put(getNullOverrideKey(next.paramType, next.slotId), true);
                        i4++;
                        c = 0;
                        c2 = 1;
                    } else {
                        int i5 = next.paramType;
                        if (i5 == 1) {
                            this.mBoolOverrides.put(next.slotId, Boolean.valueOf(str4.equals("true")));
                        } else if (i5 == 2) {
                            this.mLongOverrides.put(next.slotId, Long.valueOf(Long.parseLong(str4)));
                        } else if (i5 == 3) {
                            this.mStringOverrides.put(next.slotId, str4);
                        } else if (i5 == 4) {
                            this.mDoubleOverrides.put(next.slotId, Double.valueOf(Double.parseDouble(str4)));
                        }
                        i4++;
                        c = 0;
                        c2 = 1;
                    }
                }
                i3++;
                c = 0;
            }
        } catch (NumberFormatException | JSONException unused) {
        }
    }

    private static int getNullOverrideKey(long j) {
        return getNullOverrideKey(MobileConfigSpecifierUtil.getParamType(j), MobileConfigSpecifierUtil.getSlotId(j));
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigOverridesTable
    public boolean boolOverrideForParam(long j, boolean z) {
        Boolean bool = this.mBoolOverrides.get(MobileConfigSpecifierUtil.getSlotId(translateSpecifierIfNeeded(j)));
        return bool == null ? z : bool.booleanValue();
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigOverridesTable
    public double doubleOverrideForParam(long j, double d) {
        Double d2 = this.mDoubleOverrides.get(MobileConfigSpecifierUtil.getSlotId(translateSpecifierIfNeeded(j)));
        return d2 == null ? d : d2.doubleValue();
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigOverridesTable
    public long intOverrideForParam(long j, long j2) {
        Long l = this.mLongOverrides.get(MobileConfigSpecifierUtil.getSlotId(translateSpecifierIfNeeded(j)));
        return l == null ? j2 : l.longValue();
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigOverridesTable
    public String stringOverrideForParam(long j, String str) {
        String str2 = this.mStringOverrides.get(MobileConfigSpecifierUtil.getSlotId(translateSpecifierIfNeeded(j)));
        return str2 == null ? str : str2;
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigOverridesTable
    public boolean hasBoolOverrideForParam(long j) {
        long translateSpecifierIfNeeded = translateSpecifierIfNeeded(j);
        return this.mBoolOverrides.get(MobileConfigSpecifierUtil.getSlotId(translateSpecifierIfNeeded)) != null || this.mNullOverrides.get(getNullOverrideKey(translateSpecifierIfNeeded));
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigOverridesTable
    public boolean hasDoubleOverrideForParam(long j) {
        long translateSpecifierIfNeeded = translateSpecifierIfNeeded(j);
        return this.mDoubleOverrides.get(MobileConfigSpecifierUtil.getSlotId(translateSpecifierIfNeeded)) != null || this.mNullOverrides.get(getNullOverrideKey(translateSpecifierIfNeeded));
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigOverridesTable
    public boolean hasIntOverrideForParam(long j) {
        long translateSpecifierIfNeeded = translateSpecifierIfNeeded(j);
        return this.mLongOverrides.get(MobileConfigSpecifierUtil.getSlotId(translateSpecifierIfNeeded)) != null || this.mNullOverrides.get(getNullOverrideKey(translateSpecifierIfNeeded));
    }

    @Override // com.facebook.mobileconfig.factory.MobileConfigOverridesTable
    public boolean hasStringOverrideForParam(long j) {
        long translateSpecifierIfNeeded = translateSpecifierIfNeeded(j);
        return this.mStringOverrides.get(MobileConfigSpecifierUtil.getSlotId(translateSpecifierIfNeeded)) != null || this.mNullOverrides.get(getNullOverrideKey(translateSpecifierIfNeeded));
    }

    private long translateSpecifierIfNeeded(long j) {
        long[][] jArr = this.mTranslationTable;
        return jArr != null ? MobileConfigContextV2WithTranslationTable.getUpdatedSpecifier(j, jArr) : j;
    }
}
