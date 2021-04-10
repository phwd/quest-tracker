package com.oculus.base.app;

import com.facebook.inject.RequiresBinding;
import com.oculus.util.constants.OculusConstants;

@RequiresBinding
public class AppInfo {
    public final String appId = OculusConstants.USER_SERVER_FB_APP_ID;
    public final String appName = OculusConstants.USER_SERVER_APP_NAME;
    public final String appSecret = OculusConstants.USER_SERVER_FB_APP_TOKEN;
    public final String flytrapCategoryId = OculusConstants.HORIZON_APP_FLYTRAP_CATEGORY_ID;
    public final String oculusAppId = OculusConstants.USER_SERVER_OCULUS_APP_ID;
    public final String oculusLoginToken = "";
}
