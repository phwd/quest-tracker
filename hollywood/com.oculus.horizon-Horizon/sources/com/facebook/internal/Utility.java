package com.facebook.internal;

import X.AnonymousClass006;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.AccessToken;
import com.facebook.AccessTokenManager;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.oculus.horizon.logging.LoggingKeys;
import com.oculus.horizon.service_media.OVRMediaServiceManager;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class Utility {
    public static final String APPLICATION_FIELDS = "fields";
    public static final String APP_SETTINGS_PREFS_KEY_FORMAT = "com.facebook.internal.APP_SETTINGS.%s";
    public static final String APP_SETTINGS_PREFS_STORE = "com.facebook.internal.preferences.APP_SETTINGS";
    public static final String APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES = "android_sdk_error_categories";
    public static final String APP_SETTING_DIALOG_CONFIGS = "android_dialog_configs";
    public static final String[] APP_SETTING_FIELDS = {APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING, APP_SETTING_NUX_CONTENT, APP_SETTING_NUX_ENABLED, APP_SETTING_DIALOG_CONFIGS, APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES};
    public static final String APP_SETTING_NUX_CONTENT = "gdpv4_nux_content";
    public static final String APP_SETTING_NUX_ENABLED = "gdpv4_nux_enabled";
    public static final String APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING = "supports_implicit_sdk_logging";
    public static final int DEFAULT_STREAM_BUFFER_SIZE = 8192;
    public static final String DIALOG_CONFIG_DIALOG_NAME_FEATURE_NAME_SEPARATOR = "\\|";
    public static final String DIALOG_CONFIG_NAME_KEY = "name";
    public static final String DIALOG_CONFIG_URL_KEY = "url";
    public static final String DIALOG_CONFIG_VERSIONS_KEY = "versions";
    public static final String EXTRA_APP_EVENTS_INFO_FORMAT_VERSION = "a2";
    public static final int GINGERBREAD_MR1 = 10;
    public static final String HASH_ALGORITHM_MD5 = "MD5";
    public static final String HASH_ALGORITHM_SHA1 = "SHA-1";
    public static final String LOG_TAG = "FacebookSDK";
    public static final int REFRESH_TIME_FOR_EXTENDED_DEVICE_INFO_MILLIS = 1800000;
    public static final String URL_SCHEME = "https";
    public static final String UTF8 = "UTF-8";
    public static long availableExternalStorageGB = -1;
    public static String carrierName = "NoCarrier";
    public static String deviceTimezone = "";
    public static Map<String, FetchedAppSettings> fetchedAppSettings = new ConcurrentHashMap();
    public static AtomicBoolean loadingSettings = new AtomicBoolean(false);
    public static final String noCarrierConstant = "NoCarrier";
    public static int numCPUCores = 0;
    public static long timestampOfLastCheck = -1;
    public static long totalExternalStorageGB = -1;

    public static class DialogFeatureConfig {
        public String dialogName;
        public Uri fallbackUrl;
        public String featureName;
        public int[] featureVersionSpec;

        public static DialogFeatureConfig parseDialogConfig(JSONObject jSONObject) {
            String optString = jSONObject.optString("name");
            Uri uri = null;
            if (!Utility.isNullOrEmpty(optString)) {
                String[] split = optString.split(Utility.DIALOG_CONFIG_DIALOG_NAME_FEATURE_NAME_SEPARATOR);
                if (split.length == 2) {
                    String str = split[0];
                    String str2 = split[1];
                    if (!Utility.isNullOrEmpty(str) && !Utility.isNullOrEmpty(str2)) {
                        String optString2 = jSONObject.optString("url");
                        if (!Utility.isNullOrEmpty(optString2)) {
                            uri = Uri.parse(optString2);
                        }
                        return new DialogFeatureConfig(str, str2, uri, parseVersionSpec(jSONObject.optJSONArray(Utility.DIALOG_CONFIG_VERSIONS_KEY)));
                    }
                }
            }
            return null;
        }

        public static int[] parseVersionSpec(JSONArray jSONArray) {
            if (jSONArray == null) {
                return null;
            }
            int length = jSONArray.length();
            int[] iArr = new int[length];
            for (int i = 0; i < length; i++) {
                int i2 = -1;
                int optInt = jSONArray.optInt(i, -1);
                if (optInt == -1) {
                    String optString = jSONArray.optString(i);
                    if (!Utility.isNullOrEmpty(optString)) {
                        try {
                            i2 = Integer.parseInt(optString);
                        } catch (NumberFormatException e) {
                            Utility.logd(Utility.LOG_TAG, e);
                        }
                        iArr[i] = i2;
                    }
                }
                i2 = optInt;
                iArr[i] = i2;
            }
            return iArr;
        }

        public DialogFeatureConfig(String str, String str2, Uri uri, int[] iArr) {
            this.dialogName = str;
            this.featureName = str2;
            this.fallbackUrl = uri;
            this.featureVersionSpec = iArr;
        }

        public String getDialogName() {
            return this.dialogName;
        }

        public Uri getFallbackUrl() {
            return this.fallbackUrl;
        }

        public String getFeatureName() {
            return this.featureName;
        }

        public int[] getVersionSpec() {
            return this.featureVersionSpec;
        }
    }

    public interface GraphMeRequestWithCacheCallback {
        void onFailure(FacebookException facebookException);

        void onSuccess(JSONObject jSONObject);
    }

    public interface Mapper<T, K> {
        K apply(T t);
    }

    public interface Predicate<T> {
        boolean apply(T t);
    }

    public static <T> ArrayList<T> arrayList(T... tArr) {
        int length = tArr.length;
        ArrayList<T> arrayList = new ArrayList<>(length);
        for (T t : tArr) {
            arrayList.add(t);
        }
        return arrayList;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (T t : list) {
                if (predicate.apply(t)) {
                    arrayList.add(t);
                }
            }
            if (arrayList.size() != 0) {
                return arrayList;
            }
        }
        return null;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:12:0x0027 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.Date] */
    /* JADX WARN: Type inference failed for: r4v2, types: [long] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Date getBundleLongAsDate(android.os.Bundle r5, java.lang.String r6, java.util.Date r7) {
        /*
            r4 = 0
            if (r5 != 0) goto L_0x0004
            return r4
        L_0x0004:
            java.lang.Object r1 = r5.get(r6)
            boolean r0 = r1 instanceof java.lang.Long
            if (r0 == 0) goto L_0x0023
            java.lang.Number r1 = (java.lang.Number) r1
            long r4 = r1.longValue()
        L_0x0012:
            r1 = 0
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x002e
            r1 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.util.Date r0 = new java.util.Date
            r0.<init>(r1)
            return r0
        L_0x0023:
            boolean r0 = r1 instanceof java.lang.String
            if (r0 == 0) goto L_0x003b
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ NumberFormatException -> 0x003b }
            long r4 = java.lang.Long.parseLong(r1)     // Catch:{ NumberFormatException -> 0x003b }
            goto L_0x0012
        L_0x002e:
            long r2 = r7.getTime()
            r0 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 * r0
            long r2 = r2 + r4
            java.util.Date r4 = new java.util.Date
            r4.<init>(r2)
        L_0x003b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.Utility.getBundleLongAsDate(android.os.Bundle, java.lang.String, java.util.Date):java.util.Date");
    }

    public static long getContentSize(Uri uri) {
        Cursor cursor = null;
        try {
            Validate.sdkInitialized();
            cursor = FacebookSdk.applicationContext.getContentResolver().query(uri, null, null, null, null);
            int columnIndex = cursor.getColumnIndex("_size");
            cursor.moveToFirst();
            long j = cursor.getLong(columnIndex);
            cursor.close();
            return j;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static boolean hasSameId(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject != null && jSONObject2 != null && jSONObject.has("id") && jSONObject2.has("id")) {
            if (jSONObject.equals(jSONObject2)) {
                return true;
            }
            String optString = jSONObject.optString("id");
            String optString2 = jSONObject2.optString("id");
            if (!(optString == null || optString2 == null)) {
                return optString.equals(optString2);
            }
        }
        return false;
    }

    public static <T> HashSet<T> hashSet(T... tArr) {
        int length = tArr.length;
        HashSet<T> hashSet = new HashSet<>(length);
        for (T t : tArr) {
            hashSet.add(t);
        }
        return hashSet;
    }

    public static Object invokeMethodQuietly(Object obj, Method method, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    public static <T> boolean isSubset(Collection<T> collection, Collection<T> collection2) {
        if (collection2 != null && collection2.size() != 0) {
            HashSet hashSet = new HashSet(collection2);
            for (T t : collection) {
                if (!hashSet.contains(t)) {
                }
            }
            return true;
        } else if (collection == null || collection.size() == 0) {
            return true;
        }
        return false;
    }

    public static <T, K> List<K> map(List<T> list, Mapper<T, K> mapper) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (T t : list) {
                K apply = mapper.apply(t);
                if (apply != null) {
                    arrayList.add(apply);
                }
            }
            if (arrayList.size() != 0) {
                return arrayList;
            }
        }
        return null;
    }

    public static String readStreamToString(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream;
        InputStreamReader inputStreamReader;
        Throwable th;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                inputStreamReader = new InputStreamReader(bufferedInputStream);
                try {
                    StringBuilder sb = new StringBuilder();
                    char[] cArr = new char[2048];
                    while (true) {
                        int read = inputStreamReader.read(cArr);
                        if (read != -1) {
                            sb.append(cArr, 0, read);
                        } else {
                            String obj = sb.toString();
                            closeQuietly(bufferedInputStream);
                            closeQuietly(inputStreamReader);
                            return obj;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    closeQuietly(bufferedInputStream);
                    closeQuietly(inputStreamReader);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStreamReader = null;
                closeQuietly(bufferedInputStream);
                closeQuietly(inputStreamReader);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream = null;
            inputStreamReader = null;
            closeQuietly(bufferedInputStream);
            closeQuietly(inputStreamReader);
            throw th;
        }
    }

    public static class FetchedAppSettings {
        public Map<String, Map<String, DialogFeatureConfig>> dialogConfigMap;
        public FacebookRequestErrorClassification errorClassification;
        public String nuxContent;
        public boolean nuxEnabled;
        public boolean supportsImplicitLogging;

        public Map<String, Map<String, DialogFeatureConfig>> getDialogConfigurations() {
            return this.dialogConfigMap;
        }

        public FacebookRequestErrorClassification getErrorClassification() {
            return this.errorClassification;
        }

        public String getNuxContent() {
            return this.nuxContent;
        }

        public boolean getNuxEnabled() {
            return this.nuxEnabled;
        }

        public boolean supportsImplicitLogging() {
            return this.supportsImplicitLogging;
        }

        public FetchedAppSettings(boolean z, String str, boolean z2, Map<String, Map<String, DialogFeatureConfig>> map, FacebookRequestErrorClassification facebookRequestErrorClassification) {
            this.supportsImplicitLogging = z;
            this.nuxContent = str;
            this.nuxEnabled = z2;
            this.dialogConfigMap = map;
            this.errorClassification = facebookRequestErrorClassification;
        }
    }

    public static <T> boolean areObjectsEqual(T t, T t2) {
        if (t != null) {
            return t.equals(t2);
        }
        if (t2 == null) {
            return true;
        }
        return false;
    }

    public static <T> List<T> asListNoNulls(T... tArr) {
        ArrayList arrayList = new ArrayList();
        for (T t : tArr) {
            if (t != null) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public static Uri buildUri(String str, String str2, Bundle bundle) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority(str);
        builder.path(str2);
        if (bundle != null) {
            for (String str3 : bundle.keySet()) {
                Object obj = bundle.get(str3);
                if (obj instanceof String) {
                    builder.appendQueryParameter(str3, (String) obj);
                }
            }
        }
        return builder.build();
    }

    public static void clearFacebookCookies(Context context) {
        clearCookiesForDomain(context, "facebook.com");
        clearCookiesForDomain(context, ".facebook.com");
        clearCookiesForDomain(context, "https://facebook.com");
        clearCookiesForDomain(context, "https://.facebook.com");
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static long convertBytesToGB(double d) {
        return Math.round(d / 1.073741824E9d);
    }

    public static Map<String, Object> convertJSONObjectToHashMap(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        JSONArray names = jSONObject.names();
        for (int i = 0; i < names.length(); i++) {
            try {
                String string = names.getString(i);
                Object obj = jSONObject.get(string);
                if (obj instanceof JSONObject) {
                    obj = convertJSONObjectToHashMap((JSONObject) obj);
                }
                hashMap.put(string, obj);
            } catch (JSONException unused) {
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int copyAndCloseInputStream(java.io.InputStream r6, java.io.OutputStream r7) throws java.io.IOException {
        /*
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0020 }
            r5.<init>(r6)     // Catch:{ all -> 0x0020 }
            r0 = 8192(0x2000, float:1.14794E-41)
            byte[] r4 = new byte[r0]     // Catch:{ all -> 0x0022 }
            r3 = 0
            r2 = 0
        L_0x000b:
            int r1 = r5.read(r4)     // Catch:{ all -> 0x0022 }
            r0 = -1
            if (r1 == r0) goto L_0x0017
            r7.write(r4, r3, r1)     // Catch:{ all -> 0x0022 }
            int r2 = r2 + r1
            goto L_0x000b
        L_0x0017:
            r5.close()
            if (r6 == 0) goto L_0x001f
            r6.close()
        L_0x001f:
            return r2
        L_0x0020:
            r0 = move-exception
            goto L_0x0026
        L_0x0022:
            r0 = move-exception
            r5.close()
        L_0x0026:
            if (r6 == 0) goto L_0x002b
            r6.close()
        L_0x002b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.Utility.copyAndCloseInputStream(java.io.InputStream, java.io.OutputStream):int");
    }

    public static void disconnectQuietly(URLConnection uRLConnection) {
        if (uRLConnection != null && (uRLConnection instanceof HttpURLConnection)) {
            ((HttpURLConnection) uRLConnection).disconnect();
        }
    }

    public static String getActivityName(Context context) {
        if (context == null) {
            return "null";
        }
        if (context == context.getApplicationContext()) {
            return "unknown";
        }
        return context.getClass().getSimpleName();
    }

    public static JSONObject getAppSettingsQueryResponse(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", TextUtils.join(",", APP_SETTING_FIELDS));
        GraphRequest newGraphPathRequest = GraphRequest.newGraphPathRequest(null, str, null);
        newGraphPathRequest.skipClientToken = true;
        newGraphPathRequest.parameters = bundle;
        return GraphRequest.executeAndWait(newGraphPathRequest).graphObject;
    }

    public static FetchedAppSettings getAppSettingsWithoutQuery(String str) {
        if (str != null) {
            return fetchedAppSettings.get(str);
        }
        return null;
    }

    public static GraphRequest getGraphMeRequestWithCache(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id,name,first_name,middle_name,last_name,link");
        bundle.putString("access_token", str);
        return new GraphRequest(null, "me", bundle, HttpMethod.GET, null);
    }

    public static String getMetadataApplicationId(Context context) {
        Validate.notNull(context, "context");
        FacebookSdk.sdkInitialize(context);
        Validate.sdkInitialized();
        return FacebookSdk.applicationId;
    }

    public static String getUriString(Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x000e A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int[] intersectRanges(int[] r12, int[] r13) {
        /*
            if (r12 != 0) goto L_0x0003
            return r13
        L_0x0003:
            if (r13 == 0) goto L_0x0061
            int r10 = r12.length
            int r9 = r13.length
            int r0 = r10 + r9
            int[] r8 = new int[r0]
            r7 = 0
            r6 = 0
            r5 = 0
        L_0x000e:
            if (r7 >= r10) goto L_0x005d
            if (r6 >= r9) goto L_0x005d
            r4 = r12[r7]
            r11 = r13[r6]
            int r0 = r10 + -1
            r3 = 2147483647(0x7fffffff, float:NaN)
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r7 >= r0) goto L_0x0024
            int r0 = r7 + 1
            r2 = r12[r0]
        L_0x0024:
            int r0 = r9 + -1
            r1 = 2147483647(0x7fffffff, float:NaN)
            if (r6 >= r0) goto L_0x002f
            int r0 = r6 + 1
            r1 = r13[r0]
        L_0x002f:
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r4 >= r11) goto L_0x0050
            if (r2 <= r11) goto L_0x004d
            if (r2 <= r1) goto L_0x0049
            int r6 = r6 + 2
            r4 = r11
        L_0x003a:
            r2 = r1
        L_0x003b:
            if (r4 == r0) goto L_0x000e
            int r0 = r5 + 1
            r8[r5] = r4
            r5 = r0
            if (r2 == r3) goto L_0x005d
            int r5 = r0 + 1
            r8[r0] = r2
            goto L_0x000e
        L_0x0049:
            int r7 = r7 + 2
            r4 = r11
            goto L_0x003b
        L_0x004d:
            int r7 = r7 + 2
            goto L_0x000e
        L_0x0050:
            if (r1 <= r4) goto L_0x005a
            if (r1 <= r2) goto L_0x0057
            int r7 = r7 + 2
            goto L_0x003b
        L_0x0057:
            int r6 = r6 + 2
            goto L_0x003a
        L_0x005a:
            int r6 = r6 + 2
            goto L_0x000e
        L_0x005d:
            int[] r12 = java.util.Arrays.copyOf(r8, r5)
        L_0x0061:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.Utility.intersectRanges(int[], int[]):int[]");
    }

    public static boolean isContentUri(Uri uri) {
        if (uri == null || !LoggingKeys.REFERRER_CONTENT.equalsIgnoreCase(uri.getScheme())) {
            return false;
        }
        return true;
    }

    public static boolean isCurrentAccessToken(AccessToken accessToken) {
        if (accessToken != null) {
            return accessToken.equals(AccessTokenManager.getInstance().currentAccessToken);
        }
        return false;
    }

    public static boolean isFileUri(Uri uri) {
        if (uri == null || !GraphRequest.ATTACHMENT_FILENAME_PREFIX.equalsIgnoreCase(uri.getScheme())) {
            return false;
        }
        return true;
    }

    public static boolean isWebUri(Uri uri) {
        if (uri == null) {
            return false;
        }
        if ("http".equalsIgnoreCase(uri.getScheme()) || "https".equalsIgnoreCase(uri.getScheme())) {
            return true;
        }
        return false;
    }

    public static Set<String> jsonArrayToSet(JSONArray jSONArray) throws JSONException {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(jSONArray.getString(i));
        }
        return hashSet;
    }

    public static List<String> jsonArrayToStringList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }

    public static void loadAppSettingsAsync(final Context context, final String str) {
        boolean compareAndSet = loadingSettings.compareAndSet(false, true);
        if (!isNullOrEmpty(str) && !fetchedAppSettings.containsKey(str) && compareAndSet) {
            final String format = String.format(APP_SETTINGS_PREFS_KEY_FORMAT, str);
            FacebookSdk.getExecutor().execute(new Runnable() {
                /* class com.facebook.internal.Utility.AnonymousClass1 */

                public void run() {
                    SharedPreferences sharedPreferences = context.getSharedPreferences(Utility.APP_SETTINGS_PREFS_STORE, 0);
                    String string = sharedPreferences.getString(format, null);
                    if (!Utility.isNullOrEmpty(string)) {
                        try {
                            Utility.parseAppSettingsFromJSON(str, new JSONObject(string));
                        } catch (JSONException e) {
                            Utility.logd(Utility.LOG_TAG, e);
                        }
                    }
                    JSONObject appSettingsQueryResponse = Utility.getAppSettingsQueryResponse(str);
                    if (appSettingsQueryResponse != null) {
                        Utility.parseAppSettingsFromJSON(str, appSettingsQueryResponse);
                        sharedPreferences.edit().putString(format, appSettingsQueryResponse.toString()).apply();
                    }
                    Utility.loadingSettings.set(false);
                }
            });
        }
    }

    public static String md5hash(String str) {
        return hashWithAlgorithm(HASH_ALGORITHM_MD5, str);
    }

    public static FetchedAppSettings parseAppSettingsFromJSON(String str, JSONObject jSONObject) {
        FacebookRequestErrorClassification createFromJSON;
        JSONArray optJSONArray = jSONObject.optJSONArray(APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES);
        if (optJSONArray == null) {
            createFromJSON = FacebookRequestErrorClassification.getDefaultErrorClassification();
        } else {
            createFromJSON = FacebookRequestErrorClassification.createFromJSON(optJSONArray);
        }
        FetchedAppSettings fetchedAppSettings2 = new FetchedAppSettings(jSONObject.optBoolean(APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING, false), jSONObject.optString(APP_SETTING_NUX_CONTENT, ""), jSONObject.optBoolean(APP_SETTING_NUX_ENABLED, false), parseDialogConfigurations(jSONObject.optJSONObject(APP_SETTING_DIALOG_CONFIGS)), createFromJSON);
        fetchedAppSettings.put(str, fetchedAppSettings2);
        return fetchedAppSettings2;
    }

    public static Map<String, Map<String, DialogFeatureConfig>> parseDialogConfigurations(JSONObject jSONObject) {
        JSONArray optJSONArray;
        HashMap hashMap = new HashMap();
        if (!(jSONObject == null || (optJSONArray = jSONObject.optJSONArray("data")) == null)) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                DialogFeatureConfig parseDialogConfig = DialogFeatureConfig.parseDialogConfig(optJSONArray.optJSONObject(i));
                if (parseDialogConfig != null) {
                    String str = parseDialogConfig.dialogName;
                    Map map = (Map) hashMap.get(str);
                    if (map == null) {
                        map = new HashMap();
                        hashMap.put(str, map);
                    }
                    map.put(parseDialogConfig.featureName, parseDialogConfig);
                }
            }
        }
        return hashMap;
    }

    public static Bundle parseUrlQueryString(String str) {
        String str2;
        String str3;
        Bundle bundle = new Bundle();
        if (!isNullOrEmpty(str)) {
            for (String str4 : str.split("&")) {
                String[] split = str4.split("=");
                try {
                    if (split.length == 2) {
                        str2 = URLDecoder.decode(split[0], "UTF-8");
                        str3 = URLDecoder.decode(split[1], "UTF-8");
                    } else if (split.length == 1) {
                        str2 = URLDecoder.decode(split[0], "UTF-8");
                        str3 = "";
                    }
                    bundle.putString(str2, str3);
                } catch (UnsupportedEncodingException e) {
                    logd(LOG_TAG, e);
                }
            }
        }
        return bundle;
    }

    public static void putCommaSeparatedStringList(Bundle bundle, String str, List<String> list) {
        String str2;
        if (list != null) {
            StringBuilder sb = new StringBuilder();
            for (String str3 : list) {
                sb.append(str3);
                sb.append(",");
            }
            if (sb.length() > 0) {
                str2 = sb.substring(0, sb.length() - 1);
            } else {
                str2 = "";
            }
            bundle.putString(str, str2);
        }
    }

    public static boolean putJSONValueInBundle(Bundle bundle, String str, Object obj) {
        String obj2;
        if (obj == null) {
            bundle.remove(str);
            return true;
        } else if (obj instanceof Boolean) {
            bundle.putBoolean(str, ((Boolean) obj).booleanValue());
            return true;
        } else if (obj instanceof boolean[]) {
            bundle.putBooleanArray(str, (boolean[]) obj);
            return true;
        } else if (obj instanceof Double) {
            bundle.putDouble(str, ((Number) obj).doubleValue());
            return true;
        } else if (obj instanceof double[]) {
            bundle.putDoubleArray(str, (double[]) obj);
            return true;
        } else if (obj instanceof Integer) {
            bundle.putInt(str, ((Number) obj).intValue());
            return true;
        } else if (obj instanceof int[]) {
            bundle.putIntArray(str, (int[]) obj);
            return true;
        } else if (obj instanceof Long) {
            bundle.putLong(str, ((Number) obj).longValue());
            return true;
        } else if (obj instanceof long[]) {
            bundle.putLongArray(str, (long[]) obj);
            return true;
        } else {
            if (obj instanceof String) {
                obj2 = (String) obj;
            } else if (!(obj instanceof JSONArray) && !(obj instanceof JSONObject)) {
                return false;
            } else {
                obj2 = obj.toString();
            }
            bundle.putString(str, obj2);
            return true;
        }
    }

    public static void putUri(Bundle bundle, String str, Uri uri) {
        if (uri != null) {
            putNonEmptyString(bundle, str, uri.toString());
        }
    }

    public static FetchedAppSettings queryAppSettings(String str, boolean z) {
        if (!z && fetchedAppSettings.containsKey(str)) {
            return fetchedAppSettings.get(str);
        }
        JSONObject appSettingsQueryResponse = getAppSettingsQueryResponse(str);
        if (appSettingsQueryResponse == null) {
            return null;
        }
        return parseAppSettingsFromJSON(str, appSettingsQueryResponse);
    }

    public static int refreshBestGuessNumberOfCPUCores() {
        int i = numCPUCores;
        if (i > 0) {
            return i;
        }
        try {
            numCPUCores = new File("/sys/devices/system/cpu/").listFiles(new FilenameFilter() {
                /* class com.facebook.internal.Utility.AnonymousClass3 */

                public boolean accept(File file, String str) {
                    return Pattern.matches("cpu[0-9]+", str);
                }
            }).length;
        } catch (Exception unused) {
        }
        int i2 = numCPUCores;
        if (i2 > 0) {
            return i2;
        }
        int max = Math.max(Runtime.getRuntime().availableProcessors(), 1);
        numCPUCores = max;
        return max;
    }

    public static void refreshCarrierName(Context context) {
        if (carrierName.equals(noCarrierConstant)) {
            try {
                carrierName = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
            } catch (Exception unused) {
            }
        }
    }

    public static void refreshPeriodicExtendedDeviceInfo(Context context) {
        long j = timestampOfLastCheck;
        if (j == -1 || System.currentTimeMillis() - j >= 1800000) {
            timestampOfLastCheck = System.currentTimeMillis();
            refreshTimezone();
            refreshCarrierName(context);
            refreshTotalExternalStorage();
            refreshAvailableExternalStorage();
        }
    }

    public static String safeGetStringFromResponse(JSONObject jSONObject, String str) {
        return jSONObject != null ? jSONObject.optString(str, "") : "";
    }

    public static void setAppEventAttributionParameters(JSONObject jSONObject, AttributionIdentifiers attributionIdentifiers, String str, boolean z) throws JSONException {
        if (attributionIdentifiers != null) {
            String str2 = attributionIdentifiers.attributionId;
            if (str2 != null) {
                jSONObject.put("attribution", str2);
            }
            String str3 = attributionIdentifiers.androidAdvertiserId;
            if (str3 != null) {
                jSONObject.put("advertiser_id", str3);
                jSONObject.put("advertiser_tracking_enabled", !attributionIdentifiers.limitTracking);
            }
            String str4 = attributionIdentifiers.androidInstallerPackage;
            if (str4 != null) {
                jSONObject.put("installer_package", str4);
            }
        }
        jSONObject.put("anon_id", str);
        jSONObject.put("application_tracking_enabled", !z);
    }

    public static void setAppEventExtendedDeviceInfoParameters(JSONObject jSONObject, Context context) throws JSONException {
        String str;
        Locale locale;
        int i;
        int i2;
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(EXTRA_APP_EVENTS_INFO_FORMAT_VERSION);
        refreshPeriodicExtendedDeviceInfo(context);
        String packageName = context.getPackageName();
        int i3 = -1;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            i3 = packageInfo.versionCode;
            str = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            str = "";
        }
        jSONArray.put(packageName);
        jSONArray.put(i3);
        jSONArray.put(str);
        jSONArray.put(Build.VERSION.RELEASE);
        jSONArray.put(Build.MODEL);
        try {
            locale = context.getResources().getConfiguration().locale;
        } catch (Exception unused2) {
            locale = Locale.getDefault();
        }
        jSONArray.put(AnonymousClass006.A07(locale.getLanguage(), "_", locale.getCountry()));
        jSONArray.put(deviceTimezone);
        jSONArray.put(carrierName);
        double d = OVRMediaServiceManager.SCREENSHOT_SHORTCUT_DELAY;
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager != null) {
                Display defaultDisplay = windowManager.getDefaultDisplay();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                defaultDisplay.getMetrics(displayMetrics);
                i = displayMetrics.widthPixels;
                try {
                    i2 = displayMetrics.heightPixels;
                    try {
                        d = (double) displayMetrics.density;
                    } catch (Exception unused3) {
                    }
                } catch (Exception unused4) {
                }
                jSONArray.put(i);
                jSONArray.put(i2);
                jSONArray.put(String.format("%.2f", Double.valueOf(d)));
                jSONArray.put(refreshBestGuessNumberOfCPUCores());
                jSONArray.put(totalExternalStorageGB);
                jSONArray.put(availableExternalStorageGB);
                jSONObject.put("extinfo", jSONArray.toString());
            }
        } catch (Exception unused5) {
        }
        i = 0;
        i2 = 0;
        jSONArray.put(i);
        jSONArray.put(i2);
        jSONArray.put(String.format("%.2f", Double.valueOf(d)));
        jSONArray.put(refreshBestGuessNumberOfCPUCores());
        jSONArray.put(totalExternalStorageGB);
        jSONArray.put(availableExternalStorageGB);
        jSONObject.put("extinfo", jSONArray.toString());
    }

    public static JSONArray tryGetJSONArrayFromResponse(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            return jSONObject.optJSONArray(str);
        }
        return null;
    }

    public static JSONObject tryGetJSONObjectFromResponse(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject(str);
        }
        return null;
    }

    public static void writeStringMapToParcel(Parcel parcel, Map<String, String> map) {
        if (map == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            parcel.writeString(entry.getKey());
            parcel.writeString(entry.getValue());
        }
    }

    public static JSONObject awaitGetGraphMeRequestWithCache(String str) {
        JSONObject profileInformation = ProfileInformationCache.getProfileInformation(str);
        if (profileInformation != null) {
            return profileInformation;
        }
        GraphResponse executeAndWait = GraphRequest.executeAndWait(getGraphMeRequestWithCache(str));
        if (executeAndWait.error != null) {
            return null;
        }
        return executeAndWait.graphObject;
    }

    public static void clearCookiesForDomain(Context context, String str) {
        CookieSyncManager.createInstance(context).sync();
        CookieManager instance = CookieManager.getInstance();
        String cookie = instance.getCookie(str);
        if (cookie != null) {
            for (String str2 : cookie.split(";")) {
                String[] split = str2.split("=");
                if (split.length > 0) {
                    instance.setCookie(str, AnonymousClass006.A05(split[0].trim(), "=;expires=Sat, 1 Jan 2000 00:00:01 UTC;"));
                }
            }
            instance.removeExpiredCookie();
        }
    }

    public static String coerceValueIfNullOrEmpty(String str, String str2) {
        if (isNullOrEmpty(str)) {
            return str2;
        }
        return str;
    }

    public static void deleteDirectory(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    deleteDirectory(file2);
                }
            }
            file.delete();
        }
    }

    public static boolean externalStorageExists() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static DialogFeatureConfig getDialogFeatureConfig(String str, String str2, String str3) {
        FetchedAppSettings fetchedAppSettings2;
        Map<String, DialogFeatureConfig> map;
        if (isNullOrEmpty(str2) || isNullOrEmpty(str3) || (fetchedAppSettings2 = fetchedAppSettings.get(str)) == null || (map = fetchedAppSettings2.dialogConfigMap.get(str2)) == null) {
            return null;
        }
        return map.get(str3);
    }

    public static void getGraphMeRequestWithCacheAsync(final String str, final GraphMeRequestWithCacheCallback graphMeRequestWithCacheCallback) {
        JSONObject profileInformation = ProfileInformationCache.getProfileInformation(str);
        if (profileInformation != null) {
            graphMeRequestWithCacheCallback.onSuccess(profileInformation);
            return;
        }
        AnonymousClass2 r1 = new GraphRequest.Callback() {
            /* class com.facebook.internal.Utility.AnonymousClass2 */

            @Override // com.facebook.GraphRequest.Callback
            public void onCompleted(GraphResponse graphResponse) {
                FacebookRequestError facebookRequestError = graphResponse.error;
                if (facebookRequestError != null) {
                    graphMeRequestWithCacheCallback.onFailure(facebookRequestError.exception);
                    return;
                }
                ProfileInformationCache.putProfileInformation(str, graphResponse.graphObject);
                graphMeRequestWithCacheCallback.onSuccess(graphResponse.graphObject);
            }
        };
        GraphRequest graphMeRequestWithCache = getGraphMeRequestWithCache(str);
        graphMeRequestWithCache.setCallback(r1);
        graphMeRequestWithCache.executeAsync();
    }

    public static Object getStringPropertyAsJSON(JSONObject jSONObject, String str, String str2) throws JSONException {
        Object opt = jSONObject.opt(str);
        if (opt == null || (((opt instanceof String) && (opt = new JSONTokener((String) opt).nextValue()) == null) || (opt instanceof JSONObject) || (opt instanceof JSONArray))) {
            return opt;
        }
        if (str2 != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt(str2, opt);
            return jSONObject2;
        }
        throw new FacebookException("Got an unexpected non-JSON object.");
    }

    public static String hashBytes(MessageDigest messageDigest, byte[] bArr) {
        messageDigest.update(bArr);
        byte[] digest = messageDigest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(Integer.toHexString((b >> 4) & 15));
            sb.append(Integer.toHexString((b >> 0) & 15));
        }
        return sb.toString();
    }

    public static void putNonEmptyString(Bundle bundle, String str, String str2) {
        if (!isNullOrEmpty(str2)) {
            bundle.putString(str, str2);
        }
    }

    public static Map<String, String> readStringMapFromParcel(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < readInt; i++) {
            hashMap.put(parcel.readString(), parcel.readString());
        }
        return hashMap;
    }

    public static void refreshAvailableExternalStorage() {
        try {
            if (externalStorageExists()) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                availableExternalStorageGB = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
            }
            availableExternalStorageGB = convertBytesToGB((double) availableExternalStorageGB);
        } catch (Exception unused) {
        }
    }

    public static void refreshTimezone() {
        try {
            TimeZone timeZone = TimeZone.getDefault();
            deviceTimezone = timeZone.getDisplayName(timeZone.inDaylightTime(new Date()), 0);
        } catch (Exception unused) {
        }
    }

    public static void refreshTotalExternalStorage() {
        try {
            if (externalStorageExists()) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                totalExternalStorageGB = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
            }
            totalExternalStorageGB = convertBytesToGB((double) totalExternalStorageGB);
        } catch (Exception unused) {
        }
    }

    public static boolean stringsEqualOrEmpty(String str, String str2) {
        boolean isEmpty = TextUtils.isEmpty(str);
        boolean isEmpty2 = TextUtils.isEmpty(str2);
        if (isEmpty) {
            if (!isEmpty2) {
                return false;
            }
            return true;
        } else if (!isEmpty2) {
            return str.equals(str2);
        }
        return false;
    }

    public static <T> Collection<T> unmodifiableCollection(T... tArr) {
        return Collections.unmodifiableCollection(Arrays.asList(tArr));
    }

    public static void clearCaches(Context context) {
        ImageDownloader.clearCache(context);
    }

    public static Method getMethodQuietly(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static Method getMethodQuietly(String str, String str2, Class<?>... clsArr) {
        try {
            try {
                return Class.forName(str).getMethod(str2, clsArr);
            } catch (NoSuchMethodException unused) {
                return null;
            }
        } catch (ClassNotFoundException unused2) {
            return null;
        }
    }

    public static String hashWithAlgorithm(String str, String str2) {
        return hashWithAlgorithm(str, str2.getBytes());
    }

    public static String hashWithAlgorithm(String str, byte[] bArr) {
        try {
            return hashBytes(MessageDigest.getInstance(str), bArr);
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static <T> boolean isNullOrEmpty(Collection<T> collection) {
        return collection == null || collection.size() == 0;
    }

    public static void logd(String str, Exception exc) {
        if (FacebookSdk.isDebugEnabled && str != null && exc != null) {
            exc.getMessage();
        }
    }

    public static void logd(String str, String str2) {
    }

    public static void logd(String str, String str2, Throwable th) {
    }

    public static String sha1hash(String str) {
        return hashWithAlgorithm(HASH_ALGORITHM_SHA1, str);
    }

    public static String sha1hash(byte[] bArr) {
        return hashWithAlgorithm(HASH_ALGORITHM_SHA1, bArr);
    }
}
