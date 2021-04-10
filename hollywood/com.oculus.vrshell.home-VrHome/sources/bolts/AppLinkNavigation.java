package bolts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import bolts.AppLink;
import com.oculus.util.constants.OculusConstants;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class AppLinkNavigation {
    private static final String KEY_NAME_REFERER_APP_LINK = "referer_app_link";
    private static final String KEY_NAME_REFERER_APP_LINK_APP_NAME = "app_name";
    private static final String KEY_NAME_REFERER_APP_LINK_PACKAGE = "package";
    private static final String KEY_NAME_USER_AGENT = "user_agent";
    private static final String KEY_NAME_VERSION = "version";
    private static final String VERSION = "1.0";
    private static AppLinkResolver defaultResolver;
    private final AppLink appLink;
    private final Bundle appLinkData;
    private final Bundle extras;

    public enum NavigationResult {
        FAILED("failed", false),
        WEB("web", true),
        APP("app", true);
        
        private String code;
        private boolean succeeded;

        public String getCode() {
            return this.code;
        }

        public boolean isSucceeded() {
            return this.succeeded;
        }

        private NavigationResult(String code2, boolean success) {
            this.code = code2;
            this.succeeded = success;
        }
    }

    public AppLinkNavigation(AppLink appLink2, Bundle extras2, Bundle appLinkData2) {
        if (appLink2 == null) {
            throw new IllegalArgumentException("appLink must not be null.");
        }
        extras2 = extras2 == null ? new Bundle() : extras2;
        appLinkData2 = appLinkData2 == null ? new Bundle() : appLinkData2;
        this.appLink = appLink2;
        this.extras = extras2;
        this.appLinkData = appLinkData2;
    }

    public AppLink getAppLink() {
        return this.appLink;
    }

    public Bundle getAppLinkData() {
        return this.appLinkData;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    private Bundle buildAppLinkDataForNavigation(Context context) {
        String refererAppName;
        Bundle data = new Bundle();
        Bundle refererAppLinkData = new Bundle();
        if (context != null) {
            String refererAppPackage = context.getPackageName();
            if (refererAppPackage != null) {
                refererAppLinkData.putString(KEY_NAME_REFERER_APP_LINK_PACKAGE, refererAppPackage);
            }
            ApplicationInfo appInfo = context.getApplicationInfo();
            if (!(appInfo == null || (refererAppName = context.getString(appInfo.labelRes)) == null)) {
                refererAppLinkData.putString(KEY_NAME_REFERER_APP_LINK_APP_NAME, refererAppName);
            }
        }
        data.putAll(getAppLinkData());
        data.putString("target_url", getAppLink().getSourceUrl().toString());
        data.putString(KEY_NAME_VERSION, "1.0");
        data.putString(KEY_NAME_USER_AGENT, "Bolts Android 1.4.0");
        data.putBundle(KEY_NAME_REFERER_APP_LINK, refererAppLinkData);
        data.putBundle("extras", getExtras());
        return data;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type boolean[] to java.lang.Object for r0v17 'arr$'  boolean[]
        	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:100)
        	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
        	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:358)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:144)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:93)
        */
    private java.lang.Object getJSONValue(java.lang.Object r13) throws org.json.JSONException {
        /*
        // Method dump skipped, instructions count: 440
        */
        throw new UnsupportedOperationException("Method not decompiled: bolts.AppLinkNavigation.getJSONValue(java.lang.Object):java.lang.Object");
    }

    private JSONObject getJSONForBundle(Bundle bundle) throws JSONException {
        JSONObject root = new JSONObject();
        for (String key : bundle.keySet()) {
            root.put(key, getJSONValue(bundle.get(key)));
        }
        return root;
    }

    public NavigationResult navigate(Context context) {
        PackageManager pm = context.getPackageManager();
        Bundle finalAppLinkData = buildAppLinkDataForNavigation(context);
        Intent eligibleTargetIntent = null;
        Iterator i$ = getAppLink().getTargets().iterator();
        while (true) {
            if (!i$.hasNext()) {
                break;
            }
            AppLink.Target target = i$.next();
            Intent targetIntent = new Intent("android.intent.action.VIEW");
            if (target.getUrl() != null) {
                targetIntent.setData(target.getUrl());
            } else {
                targetIntent.setData(this.appLink.getSourceUrl());
            }
            targetIntent.setPackage(target.getPackageName());
            if (target.getClassName() != null) {
                targetIntent.setClassName(target.getPackageName(), target.getClassName());
            }
            targetIntent.putExtra("al_applink_data", finalAppLinkData);
            if (pm.resolveActivity(targetIntent, 65536) != null) {
                eligibleTargetIntent = targetIntent;
                break;
            }
        }
        Intent outIntent = null;
        NavigationResult result = NavigationResult.FAILED;
        if (eligibleTargetIntent != null) {
            outIntent = eligibleTargetIntent;
            result = NavigationResult.APP;
        } else {
            Uri webUrl = getAppLink().getWebUrl();
            if (webUrl != null) {
                try {
                    outIntent = new Intent("android.intent.action.VIEW", webUrl.buildUpon().appendQueryParameter("al_applink_data", getJSONForBundle(finalAppLinkData).toString()).build());
                    result = NavigationResult.WEB;
                } catch (JSONException e) {
                    sendAppLinkNavigateEventBroadcast(context, eligibleTargetIntent, NavigationResult.FAILED, e);
                    throw new RuntimeException(e);
                }
            }
        }
        sendAppLinkNavigateEventBroadcast(context, outIntent, result, null);
        if (outIntent != null) {
            context.startActivity(outIntent);
        }
        return result;
    }

    private void sendAppLinkNavigateEventBroadcast(Context context, Intent intent, NavigationResult type, JSONException e) {
        Map<String, String> extraLoggingData = new HashMap<>();
        if (e != null) {
            extraLoggingData.put("error", e.getLocalizedMessage());
        }
        extraLoggingData.put("success", type.isSucceeded() ? "1" : OculusConstants.DEFAULT_ENTERPRISE_USER_ID);
        extraLoggingData.put("type", type.getCode());
        MeasurementEvent.sendBroadcastEvent(context, MeasurementEvent.APP_LINK_NAVIGATE_OUT_EVENT_NAME, intent, extraLoggingData);
    }

    public static void setDefaultResolver(AppLinkResolver resolver) {
        defaultResolver = resolver;
    }

    public static AppLinkResolver getDefaultResolver() {
        return defaultResolver;
    }

    private static AppLinkResolver getResolver(Context context) {
        if (getDefaultResolver() != null) {
            return getDefaultResolver();
        }
        return new WebViewAppLinkResolver(context);
    }

    public static NavigationResult navigate(Context context, AppLink appLink2) {
        return new AppLinkNavigation(appLink2, null, null).navigate(context);
    }

    public static Task<NavigationResult> navigateInBackground(final Context context, Uri destination, AppLinkResolver resolver) {
        return resolver.getAppLinkFromUrlInBackground(destination).onSuccess(new Continuation<AppLink, NavigationResult>() {
            /* class bolts.AppLinkNavigation.AnonymousClass1 */

            @Override // bolts.Continuation
            public NavigationResult then(Task<AppLink> task) throws Exception {
                return AppLinkNavigation.navigate(context, task.getResult());
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    public static Task<NavigationResult> navigateInBackground(Context context, URL destination, AppLinkResolver resolver) {
        return navigateInBackground(context, Uri.parse(destination.toString()), resolver);
    }

    public static Task<NavigationResult> navigateInBackground(Context context, String destinationUrl, AppLinkResolver resolver) {
        return navigateInBackground(context, Uri.parse(destinationUrl), resolver);
    }

    public static Task<NavigationResult> navigateInBackground(Context context, Uri destination) {
        return navigateInBackground(context, destination, getResolver(context));
    }

    public static Task<NavigationResult> navigateInBackground(Context context, URL destination) {
        return navigateInBackground(context, destination, getResolver(context));
    }

    public static Task<NavigationResult> navigateInBackground(Context context, String destinationUrl) {
        return navigateInBackground(context, destinationUrl, getResolver(context));
    }
}
