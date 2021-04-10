package com.oculus.appmanager.model;

import java.util.List;

public class UrlFetchResult {
    public final UpdateConfig apkConfig;
    public final List<UpdateConfig> assetConfigs;
    public final String installIdentifier;
    public final UpdateConfig obbConfig;
    public final String oculusAppStoreId;
    public final long versionCode;

    public UrlFetchResult(UpdateConfig updateConfig, UpdateConfig updateConfig2, List<UpdateConfig> list, String str, String str2, long j) {
        this.apkConfig = updateConfig;
        this.obbConfig = updateConfig2;
        this.assetConfigs = list;
        this.installIdentifier = str;
        this.oculusAppStoreId = str2;
        this.versionCode = j;
    }
}
