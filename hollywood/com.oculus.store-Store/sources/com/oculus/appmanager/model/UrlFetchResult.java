package com.oculus.appmanager.model;

import java.util.List;

public class UrlFetchResult {
    public final UpdateConfig apkConfig;
    public final List<UpdateConfig> assetConfigs;
    public final String installIdentifier;
    public final UpdateConfig obbConfig;
    public final String oculusAppStoreId;
    public final long versionCode;

    public UrlFetchResult(UpdateConfig apkConfig2, UpdateConfig obbConfig2, List<UpdateConfig> assetConfigs2, String installIdentifier2, String appStoreId, long versionCode2) {
        this.apkConfig = apkConfig2;
        this.obbConfig = obbConfig2;
        this.assetConfigs = assetConfigs2;
        this.installIdentifier = installIdentifier2;
        this.oculusAppStoreId = appStoreId;
        this.versionCode = versionCode2;
    }
}
