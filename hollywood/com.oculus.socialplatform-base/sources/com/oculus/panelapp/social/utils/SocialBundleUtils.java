package com.oculus.panelapp.social.utils;

import android.net.Uri;
import com.oculus.horizoncontent.user.LinkedAccountsInfo;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.vrshell.SystemUXRoute;

public class SocialBundleUtils {
    public static void ShowUpsell(SocialPanelApp socialPanelApp, UpsellLoggingParameters upsellLoggingParameters) {
        socialPanelApp.actionNavigate(SystemUXRoute.SOCIAL, getUpsellRoute(upsellLoggingParameters));
    }

    public static String getUpsellRoute(UpsellLoggingParameters upsellLoggingParameters) {
        return new Uri.Builder().encodedPath("/fb-connect/").appendPath(upsellLoggingParameters.mSource).appendQueryParameter("action", upsellLoggingParameters.mAction).appendQueryParameter("container", upsellLoggingParameters.mContainer).appendQueryParameter("entrypoint", upsellLoggingParameters.mEntryPoint).appendQueryParameter("mustInteract", upsellLoggingParameters.mMustInteract).appendQueryParameter("product", upsellLoggingParameters.mProduct).build().toString();
    }

    public static void PerformActionIfLinked(SocialPanelApp socialPanelApp, LinkedAccountsInfo linkedAccountsInfo, Runnable runnable, UpsellLoggingParameters upsellLoggingParameters) {
        if (linkedAccountsInfo.isFbLinked()) {
            runnable.run();
        } else if (linkedAccountsInfo.isInsufficientTerms()) {
            socialPanelApp.actionNavigate(SystemUXRoute.SOCIAL, "");
        } else {
            ShowUpsell(socialPanelApp, upsellLoggingParameters);
        }
    }
}
