package com.facebook.mobileconfig.impl;

import com.facebook.crudolib.params.ParamsCollectionMap;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.mobileconfig.factory.MobileConfigContext;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigEPDecisionResponse {
    @Nullable
    public final MobileConfigContext cachedContext;
    public String cachedValues = "";
    public final int cachedVersion;
    public final int configIndex;
    public final int delayRestart;
    public final boolean existsInCache;
    public final boolean forceRefresh;
    public final MobileConfigContext latestContext;
    public String latestValues = "";
    public final int latestVersion;
    public final String name;
    public final boolean paramValuesChanged;
    public final boolean restart;

    /* access modifiers changed from: protected */
    public void serializeIntoParamsCollectionMap(ParamsCollectionMap paramsCollectionMap) {
        paramsCollectionMap.add("restart", Boolean.valueOf(this.restart));
        paramsCollectionMap.add("delay_restart", Integer.valueOf(this.delayRestart));
        paramsCollectionMap.add("force_refresh", Boolean.valueOf(this.forceRefresh));
        paramsCollectionMap.add("cached_version", Integer.valueOf(this.cachedVersion));
        paramsCollectionMap.add("latest_version", Integer.valueOf(this.latestVersion));
        paramsCollectionMap.add("param_values_changed", Boolean.valueOf(this.paramValuesChanged));
        paramsCollectionMap.add("exists_in_cache", Boolean.valueOf(this.existsInCache));
        paramsCollectionMap.add("latest_values", this.latestValues);
        paramsCollectionMap.add("cached_values", this.cachedValues);
    }

    public MobileConfigEPDecisionResponse(String str, boolean z, boolean z2, int i, boolean z3, int i2, int i3, boolean z4, int i4, @Nullable MobileConfigContext mobileConfigContext, MobileConfigContext mobileConfigContext2) {
        this.name = str;
        this.restart = z;
        this.existsInCache = z2;
        this.delayRestart = i;
        this.forceRefresh = z3;
        this.cachedVersion = i2;
        this.latestVersion = i3;
        this.paramValuesChanged = z4;
        this.configIndex = i4;
        this.cachedContext = mobileConfigContext;
        this.latestContext = mobileConfigContext2;
    }
}
