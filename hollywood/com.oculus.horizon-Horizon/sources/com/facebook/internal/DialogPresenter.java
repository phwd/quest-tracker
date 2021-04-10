package com.facebook.internal;

import X.AnonymousClass006;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.facebook.FacebookActivity;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.Utility;

public class DialogPresenter {

    public interface ParameterProvider {
        Bundle getLegacyParameters();

        Bundle getParameters();
    }

    public static void logDialogActivity(Context context, String str, String str2) {
        AppEventsLogger appEventsLogger = new AppEventsLogger(context, null);
        Bundle bundle = new Bundle();
        bundle.putString(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME, str2);
        AppEventsLogger.A02(appEventsLogger, str, bundle, true);
    }

    public static void setupAppCallForCannotShowError(AppCall appCall) {
        setupAppCallForErrorResult(appCall, new FacebookException("Unable to show the provided content via the web or the installed version of the Facebook app. Some dialogs are only supported starting API 14."));
    }

    public static void setupAppCallForErrorResult(AppCall appCall, FacebookException facebookException) {
        if (facebookException != null) {
            Validate.sdkInitialized();
            Validate.hasFacebookActivity(FacebookSdk.applicationContext, true);
            Intent intent = new Intent();
            Validate.sdkInitialized();
            intent.setClass(FacebookSdk.applicationContext, FacebookActivity.class);
            intent.setAction(FacebookActivity.PASS_THROUGH_CANCEL_ACTION);
            NativeProtocol.setupProtocolRequestIntent(intent, appCall.callId.toString(), null, NativeProtocol.getLatestKnownVersion(), NativeProtocol.createBundleForException(facebookException));
            appCall.requestIntent = intent;
        }
    }

    public static boolean canPresentNativeDialogWithFeature(DialogFeature dialogFeature) {
        if (getProtocolVersionForNativeDialog(dialogFeature) != -1) {
            return true;
        }
        return false;
    }

    public static boolean canPresentWebFallbackDialogWithFeature(DialogFeature dialogFeature) {
        if (getDialogWebFallbackUri(dialogFeature) != null) {
            return true;
        }
        return false;
    }

    public static Uri getDialogWebFallbackUri(DialogFeature dialogFeature) {
        String name = dialogFeature.name();
        String action = dialogFeature.getAction();
        Validate.sdkInitialized();
        Utility.DialogFeatureConfig dialogFeatureConfig = Utility.getDialogFeatureConfig(FacebookSdk.applicationId, action, name);
        if (dialogFeatureConfig != null) {
            return dialogFeatureConfig.fallbackUrl;
        }
        return null;
    }

    public static int getProtocolVersionForNativeDialog(DialogFeature dialogFeature) {
        Validate.sdkInitialized();
        String str = FacebookSdk.applicationId;
        String action = dialogFeature.getAction();
        return NativeProtocol.getLatestAvailableProtocolVersionForAction(action, getVersionSpecForFeature(str, action, dialogFeature));
    }

    public static int[] getVersionSpecForFeature(String str, String str2, DialogFeature dialogFeature) {
        Utility.DialogFeatureConfig dialogFeatureConfig = Utility.getDialogFeatureConfig(str, str2, dialogFeature.name());
        if (dialogFeatureConfig != null) {
            return dialogFeatureConfig.featureVersionSpec;
        }
        return new int[]{dialogFeature.getMinVersion()};
    }

    public static void setupAppCallForNativeDialog(AppCall appCall, ParameterProvider parameterProvider, DialogFeature dialogFeature) {
        String str;
        Bundle legacyParameters;
        Validate.sdkInitialized();
        Context context = FacebookSdk.applicationContext;
        String action = dialogFeature.getAction();
        int protocolVersionForNativeDialog = getProtocolVersionForNativeDialog(dialogFeature);
        if (protocolVersionForNativeDialog != -1) {
            if (NativeProtocol.isVersionCompatibleWithBucketedIntent(protocolVersionForNativeDialog)) {
                legacyParameters = parameterProvider.getParameters();
            } else {
                legacyParameters = parameterProvider.getLegacyParameters();
            }
            if (legacyParameters == null) {
                legacyParameters = new Bundle();
            }
            Intent createPlatformActivityIntent = NativeProtocol.createPlatformActivityIntent(context, appCall.callId.toString(), action, protocolVersionForNativeDialog, legacyParameters);
            if (createPlatformActivityIntent != null) {
                appCall.requestIntent = createPlatformActivityIntent;
                return;
            }
            str = "Unable to create Intent; this likely means theFacebook app is not installed.";
        } else {
            str = "Cannot present this dialog. This likely means that the Facebook app is not installed.";
        }
        throw new FacebookException(str);
    }

    public static void setupAppCallForWebDialog(AppCall appCall, String str, Bundle bundle) {
        Validate.sdkInitialized();
        Validate.hasFacebookActivity(FacebookSdk.applicationContext, true);
        Validate.sdkInitialized();
        Validate.hasInternetPermissions(FacebookSdk.applicationContext, true);
        Bundle bundle2 = new Bundle();
        bundle2.putString("action", str);
        bundle2.putBundle("params", bundle);
        Intent intent = new Intent();
        NativeProtocol.setupProtocolRequestIntent(intent, appCall.callId.toString(), str, NativeProtocol.getLatestKnownVersion(), bundle2);
        Validate.sdkInitialized();
        intent.setClass(FacebookSdk.applicationContext, FacebookActivity.class);
        intent.setAction(FacebookDialogFragment.TAG);
        appCall.requestIntent = intent;
    }

    public static void setupAppCallForWebFallbackDialog(AppCall appCall, Bundle bundle, DialogFeature dialogFeature) {
        String str;
        String authority;
        String path;
        Validate.sdkInitialized();
        Validate.hasFacebookActivity(FacebookSdk.applicationContext, true);
        Validate.sdkInitialized();
        Validate.hasInternetPermissions(FacebookSdk.applicationContext, true);
        String name = dialogFeature.name();
        Uri dialogWebFallbackUri = getDialogWebFallbackUri(dialogFeature);
        if (dialogWebFallbackUri != null) {
            Bundle queryParamsForPlatformActivityIntentWebFallback = ServerProtocol.getQueryParamsForPlatformActivityIntentWebFallback(appCall.callId.toString(), NativeProtocol.getLatestKnownVersion(), bundle);
            if (queryParamsForPlatformActivityIntentWebFallback != null) {
                if (dialogWebFallbackUri.isRelative()) {
                    authority = ServerProtocol.getDialogAuthority();
                    path = dialogWebFallbackUri.toString();
                } else {
                    authority = dialogWebFallbackUri.getAuthority();
                    path = dialogWebFallbackUri.getPath();
                }
                Uri buildUri = Utility.buildUri(authority, path, queryParamsForPlatformActivityIntentWebFallback);
                Bundle bundle2 = new Bundle();
                bundle2.putString("url", buildUri.toString());
                bundle2.putBoolean(NativeProtocol.WEB_DIALOG_IS_FALLBACK, true);
                Intent intent = new Intent();
                NativeProtocol.setupProtocolRequestIntent(intent, appCall.callId.toString(), dialogFeature.getAction(), NativeProtocol.getLatestKnownVersion(), bundle2);
                Validate.sdkInitialized();
                intent.setClass(FacebookSdk.applicationContext, FacebookActivity.class);
                intent.setAction(FacebookDialogFragment.TAG);
                appCall.requestIntent = intent;
                return;
            }
            str = "Unable to fetch the app's key-hash";
        } else {
            str = AnonymousClass006.A07("Unable to fetch the Url for the DialogFeature : '", name, "'");
        }
        throw new FacebookException(str);
    }

    public static void setupAppCallForValidationError(AppCall appCall, FacebookException facebookException) {
        setupAppCallForErrorResult(appCall, facebookException);
    }

    public static void present(AppCall appCall, Activity activity) {
        activity.startActivityForResult(appCall.requestIntent, appCall.requestCode);
        AppCall.setCurrentPendingCall(appCall);
    }

    public static void present(AppCall appCall, FragmentWrapper fragmentWrapper) {
        fragmentWrapper.startActivityForResult(appCall.requestIntent, appCall.requestCode);
        AppCall.setCurrentPendingCall(appCall);
    }
}
