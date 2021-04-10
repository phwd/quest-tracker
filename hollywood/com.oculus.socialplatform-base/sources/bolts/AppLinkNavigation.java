package bolts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import bolts.AppLink;
import com.facebook.acra.constants.ErrorReportingConstants;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppLinkNavigation {
    public static final String KEY_NAME_REFERER_APP_LINK = "referer_app_link";
    public static final String KEY_NAME_REFERER_APP_LINK_APP_NAME = "app_name";
    public static final String KEY_NAME_REFERER_APP_LINK_PACKAGE = "package";
    public static final String KEY_NAME_USER_AGENT = "user_agent";
    public static final String KEY_NAME_VERSION = "version";
    public static final String VERSION = "1.0";
    public static AppLinkResolver defaultResolver;
    public final AppLink appLink;
    public final Bundle appLinkData;
    public final Bundle extras;

    public enum NavigationResult {
        FAILED("failed", false),
        WEB(WebViewAppLinkResolver.KEY_WEB, true),
        APP(ErrorReportingConstants.APP_NAME_KEY, true);
        
        public String code;
        public boolean succeeded;

        public String getCode() {
            return this.code;
        }

        public boolean isSucceeded() {
            return this.succeeded;
        }

        /* access modifiers changed from: public */
        NavigationResult(String str, boolean z) {
            this.code = str;
            this.succeeded = z;
        }
    }

    private Bundle buildAppLinkDataForNavigation(Context context) {
        String string;
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        if (context != null) {
            String packageName = context.getPackageName();
            if (packageName != null) {
                bundle2.putString("package", packageName);
            }
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (!(applicationInfo == null || (string = context.getString(applicationInfo.labelRes)) == null)) {
                bundle2.putString("app_name", string);
            }
        }
        bundle.putAll(this.appLinkData);
        bundle.putString(AppLinks.KEY_NAME_TARGET, this.appLink.sourceUrl.toString());
        bundle.putString("version", "1.0");
        bundle.putString(KEY_NAME_USER_AGENT, "Bolts Android 1.4.0");
        bundle.putBundle(KEY_NAME_REFERER_APP_LINK, bundle2);
        bundle.putBundle(AppLinks.KEY_NAME_EXTRAS, this.extras);
        return bundle;
    }

    public static AppLinkResolver getDefaultResolver() {
        return defaultResolver;
    }

    private JSONObject getJSONForBundle(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            jSONObject.put(str, getJSONValue(bundle.get(str)));
        }
        return jSONObject;
    }

    private Object getJSONValue(Object obj) throws JSONException {
        JSONArray jSONArray;
        if (obj instanceof Bundle) {
            return getJSONForBundle((Bundle) obj);
        }
        if (!(obj instanceof CharSequence)) {
            if (obj instanceof List) {
                jSONArray = new JSONArray();
                for (Object obj2 : (List) obj) {
                    jSONArray.put(getJSONValue(obj2));
                }
            } else {
                int i = 0;
                if (obj instanceof SparseArray) {
                    jSONArray = new JSONArray();
                    SparseArray sparseArray = (SparseArray) obj;
                    while (i < sparseArray.size()) {
                        jSONArray.put(sparseArray.keyAt(i), getJSONValue(sparseArray.valueAt(i)));
                        i++;
                    }
                } else if (!(obj instanceof Character)) {
                    if (obj instanceof Boolean) {
                        return obj;
                    }
                    if (obj instanceof Number) {
                        if ((obj instanceof Double) || (obj instanceof Float)) {
                            return Double.valueOf(((Number) obj).doubleValue());
                        }
                        return Long.valueOf(((Number) obj).longValue());
                    } else if (obj instanceof boolean[]) {
                        jSONArray = new JSONArray();
                        boolean[] zArr = (boolean[]) obj;
                        int length = zArr.length;
                        while (i < length) {
                            jSONArray.put(getJSONValue(Boolean.valueOf(zArr[i])));
                            i++;
                        }
                    } else if (obj instanceof char[]) {
                        jSONArray = new JSONArray();
                        char[] cArr = (char[]) obj;
                        int length2 = cArr.length;
                        while (i < length2) {
                            jSONArray.put(getJSONValue(Character.valueOf(cArr[i])));
                            i++;
                        }
                    } else if (obj instanceof CharSequence[]) {
                        jSONArray = new JSONArray();
                        CharSequence[] charSequenceArr = (CharSequence[]) obj;
                        int length3 = charSequenceArr.length;
                        while (i < length3) {
                            jSONArray.put(getJSONValue(charSequenceArr[i]));
                            i++;
                        }
                    } else if (obj instanceof double[]) {
                        jSONArray = new JSONArray();
                        double[] dArr = (double[]) obj;
                        int length4 = dArr.length;
                        while (i < length4) {
                            jSONArray.put(getJSONValue(Double.valueOf(dArr[i])));
                            i++;
                        }
                    } else if (obj instanceof float[]) {
                        jSONArray = new JSONArray();
                        float[] fArr = (float[]) obj;
                        int length5 = fArr.length;
                        while (i < length5) {
                            jSONArray.put(getJSONValue(Float.valueOf(fArr[i])));
                            i++;
                        }
                    } else if (obj instanceof int[]) {
                        jSONArray = new JSONArray();
                        int[] iArr = (int[]) obj;
                        int length6 = iArr.length;
                        while (i < length6) {
                            jSONArray.put(getJSONValue(Integer.valueOf(iArr[i])));
                            i++;
                        }
                    } else if (obj instanceof long[]) {
                        jSONArray = new JSONArray();
                        long[] jArr = (long[]) obj;
                        int length7 = jArr.length;
                        while (i < length7) {
                            jSONArray.put(getJSONValue(Long.valueOf(jArr[i])));
                            i++;
                        }
                    } else if (obj instanceof short[]) {
                        jSONArray = new JSONArray();
                        short[] sArr = (short[]) obj;
                        int length8 = sArr.length;
                        while (i < length8) {
                            jSONArray.put(getJSONValue(Short.valueOf(sArr[i])));
                            i++;
                        }
                    } else if (!(obj instanceof String[])) {
                        return null;
                    } else {
                        jSONArray = new JSONArray();
                        String[] strArr = (String[]) obj;
                        int length9 = strArr.length;
                        while (i < length9) {
                            jSONArray.put(getJSONValue(strArr[i]));
                            i++;
                        }
                    }
                }
            }
            return jSONArray;
        }
        return obj.toString();
    }

    public static AppLinkResolver getResolver(Context context) {
        AppLinkResolver appLinkResolver = defaultResolver;
        if (appLinkResolver == null) {
            return new WebViewAppLinkResolver(context);
        }
        return appLinkResolver;
    }

    private void sendAppLinkNavigateEventBroadcast(Context context, Intent intent, NavigationResult navigationResult, JSONException jSONException) {
        String str;
        HashMap hashMap = new HashMap();
        if (jSONException != null) {
            hashMap.put("error", jSONException.getLocalizedMessage());
        }
        if (navigationResult.isSucceeded()) {
            str = "1";
        } else {
            str = "0";
        }
        hashMap.put("success", str);
        hashMap.put("type", navigationResult.getCode());
        MeasurementEvent.sendBroadcastEvent(context, MeasurementEvent.APP_LINK_NAVIGATE_OUT_EVENT_NAME, intent, hashMap);
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

    public AppLinkNavigation(AppLink appLink2, Bundle bundle, Bundle bundle2) {
        if (appLink2 != null) {
            bundle = bundle == null ? new Bundle() : bundle;
            bundle2 = bundle2 == null ? new Bundle() : bundle2;
            this.appLink = appLink2;
            this.extras = bundle;
            this.appLinkData = bundle2;
            return;
        }
        throw new IllegalArgumentException("appLink must not be null.");
    }

    public static void setDefaultResolver(AppLinkResolver appLinkResolver) {
        defaultResolver = appLinkResolver;
    }

    public static NavigationResult navigate(Context context, AppLink appLink2) {
        return new AppLinkNavigation(appLink2, null, null).navigate(context);
    }

    public static Task<NavigationResult> navigateInBackground(Context context, Uri uri) {
        return navigateInBackground(context, uri, getResolver(context));
    }

    public static Task<NavigationResult> navigateInBackground(final Context context, Uri uri, AppLinkResolver appLinkResolver) {
        return appLinkResolver.getAppLinkFromUrlInBackground(uri).onSuccess(new Continuation<AppLink, NavigationResult>() {
            /* class bolts.AppLinkNavigation.AnonymousClass1 */

            @Override // bolts.Continuation
            public NavigationResult then(Task<AppLink> task) throws Exception {
                return AppLinkNavigation.navigate(context, task.getResult());
            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    public static Task<NavigationResult> navigateInBackground(Context context, String str) {
        return navigateInBackground(context, str, getResolver(context));
    }

    public static Task<NavigationResult> navigateInBackground(Context context, String str, AppLinkResolver appLinkResolver) {
        return navigateInBackground(context, Uri.parse(str), appLinkResolver);
    }

    public static Task<NavigationResult> navigateInBackground(Context context, URL url) {
        return navigateInBackground(context, url, getResolver(context));
    }

    public static Task<NavigationResult> navigateInBackground(Context context, URL url, AppLinkResolver appLinkResolver) {
        return navigateInBackground(context, Uri.parse(url.toString()), appLinkResolver);
    }

    public NavigationResult navigate(Context context) {
        Intent intent;
        PackageManager packageManager = context.getPackageManager();
        Bundle buildAppLinkDataForNavigation = buildAppLinkDataForNavigation(context);
        Iterator it = Collections.unmodifiableList(this.appLink.targets).iterator();
        while (true) {
            if (!it.hasNext()) {
                intent = null;
                break;
            }
            AppLink.Target target = (AppLink.Target) it.next();
            intent = new Intent("android.intent.action.VIEW");
            Uri uri = target.url;
            if (uri == null) {
                uri = this.appLink.sourceUrl;
            }
            intent.setData(uri);
            intent.setPackage(target.packageName);
            String str = target.className;
            if (str != null) {
                intent.setClassName(target.packageName, str);
            }
            intent.putExtra(AppLinks.KEY_NAME_APPLINK_DATA, buildAppLinkDataForNavigation);
            if (packageManager.resolveActivity(intent, 65536) != null) {
                break;
            }
        }
        NavigationResult navigationResult = NavigationResult.FAILED;
        if (intent != null) {
            navigationResult = NavigationResult.APP;
        } else {
            Uri uri2 = this.appLink.webUrl;
            if (uri2 != null) {
                try {
                    intent = new Intent("android.intent.action.VIEW", uri2.buildUpon().appendQueryParameter(AppLinks.KEY_NAME_APPLINK_DATA, getJSONForBundle(buildAppLinkDataForNavigation).toString()).build());
                    navigationResult = NavigationResult.WEB;
                } catch (JSONException e) {
                    sendAppLinkNavigateEventBroadcast(context, intent, navigationResult, e);
                    throw new RuntimeException(e);
                }
            } else {
                intent = null;
            }
        }
        sendAppLinkNavigateEventBroadcast(context, intent, navigationResult, null);
        if (intent != null) {
            context.startActivity(intent);
        }
        return navigationResult;
    }
}
