package com.oculus.base.app;

import com.facebook.inject.RequiresBinding;
import com.oculus.util.constants.OculusConstants;

@RequiresBinding
public class AppInfo {
    public final String appId = OculusConstants.UNIFIED_TELEMETRY_FB_APP_ID;
    public final String appName = OculusConstants.UNIFIED_TELEMETRY_APP_NAME;
    public final String appSecret = OculusConstants.UNIFIED_TELEMETRY_FB_APP_TOKEN;
    public final String flytrapCategoryId = OculusConstants.HORIZON_APP_FLYTRAP_CATEGORY_ID;
    public final String oculusAppId = "";
    public final String oculusLoginToken = "";
}
