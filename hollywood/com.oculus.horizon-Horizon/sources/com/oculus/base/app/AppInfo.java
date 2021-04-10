package com.oculus.base.app;

import com.facebook.inject.RequiresBinding;
import com.oculus.util.constants.OculusConstants;

@RequiresBinding
public class AppInfo {
    public final String appId = OculusConstants.HORIZON_APP_ID;
    public final String appName = OculusConstants.HORIZON_APP_NAME;
    public final String appSecret = OculusConstants.HORIZON_FB_APP_CLIENT_TOKEN;
    public final String flytrapCategoryId = OculusConstants.HORIZON_APP_FLYTRAP_CATEGORY_ID;
    public final String oculusAppId = "826037204154824";
    public final String oculusLoginToken = "";
}
