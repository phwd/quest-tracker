package com.oculus.appmanager.model;

import java.util.List;

public class UrlFetchResult {
    public final UpdateConfig apkConfig;
    public final List<UpdateConfig> assetConfigs;
    public final String installIdentifier;
    public final UpdateConfig obbConfig;
    public final String oculusAppStoreId;
    public final long versionCode;
}
