package com.oculus.common.quickpromotion;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.appmanager.assets.database.assetcontract.AssetContract;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.common.quickpromotion.tooltip.AUITooltipQuickPromotionModel;
import com.oculus.graphql.oc.GraphQLUtil;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.provider.OculusContent;
import com.oculus.systemdialog.DialogDefinitionCommon;
import com.oculus.vrshell.SystemUXRoute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuickPromotionController {
    public static final String EVENT_ACTION = "click";
    public static final String EVENT_EXPOSURE = "qp_holdout_exposure";
    public static final String EVENT_VIEW = "view";
    public static final String KEY_INSTANCE_LOG_DATA = "instance_log_data";
    public static final String KEY_OBJECT_ID = "object_id";
    public static final String KEY_PROMOTION_ID = "promotion_id";
    public static final String KEY_TRIGGER = "trigger";
    public static final String MODULE_NAME = "quick_promotion";
    private static final String SHARED_PREF_KEY_ROOT_PROMOTIONS_BY_SURFACE = "/promotions/";
    private static final String SHARED_PREF_KEY_ROOT_PROMOTIONS_BY_SURFACE_DIRTY_FLAG = "/promotions/dirty/";
    private static final String SHARED_PREF_NAME = "quick_promotion_preferences";
    private static final String TAG = "QuickPromotionController";
    private static QuickPromotionController mInstance;
    private static final ReentrantLock mInstanceLock = new ReentrantLock(true);
    private static Interceptor mInterceptor;
    private String mAccessToken;
    private boolean mEnabled = false;
    private HashMap<Integer, Integer> mEnabledSurfaces = null;
    private boolean mInitialized = false;
    private final String mLocale;
    private OkHttpClient mOkHttpClient;
    private final SharedPreferences mSharedPreferences;
    private List<Integer> mSurfacePriorityList = null;
    private final UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    public enum QPApplication {
        AUI
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String signalTrigger(String str) {
        return str;
    }

    public QuickPromotionController(Context context) {
        this.mSharedPreferences = getSharedPreferences(context);
        this.mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(context);
        Locale locale = context.getResources().getConfiguration().locale;
        this.mLocale = (locale == null ? Locale.getDefault() : locale).toString();
    }

    /* JADX INFO: finally extract failed */
    private OkHttpClient getHttpClient() {
        OkHttpClient okHttpClient = this.mOkHttpClient;
        if (okHttpClient != null) {
            return okHttpClient;
        }
        OkHttpClient.Builder connectTimeout = new OkHttpClient.Builder().connectTimeout(6, TimeUnit.SECONDS);
        mInstanceLock.lock();
        try {
            mInstance = this;
            if (mInterceptor != null) {
                connectTimeout.addNetworkInterceptor(mInterceptor);
            }
            mInstanceLock.unlock();
            this.mOkHttpClient = connectTimeout.build();
            return this.mOkHttpClient;
        } catch (Throwable th) {
            mInstanceLock.unlock();
            throw th;
        }
    }

    public void shutdownModule() {
        mInstanceLock.lock();
        try {
            mInstance = null;
        } finally {
            mInstanceLock.unlock();
        }
    }

    public static void setHttpClientInterceptor(Interceptor interceptor) {
        mInterceptor = interceptor;
        mInstanceLock.lock();
        try {
            if (!(mInstance == null || mInstance.mOkHttpClient == null)) {
                mInstance.mOkHttpClient = mInstance.mOkHttpClient.newBuilder().addNetworkInterceptor(interceptor).build();
            }
        } finally {
            mInstanceLock.unlock();
        }
    }

    public static void onLogout(Context context) {
        getSharedPreferences(context).edit().clear().apply();
        Log.d(TAG, "Cleared Quick Promotion cache");
    }

    public static abstract class SurfaceFetchCallback {
        final AtomicInteger mSurfaceCount = new AtomicInteger();

        public abstract void onFetchComplete();

        public void setSurfaceCount(int i) {
            this.mSurfaceCount.set(i);
        }

        public void markSurfaceComplete() {
            int decrementAndGet = this.mSurfaceCount.decrementAndGet();
            if (decrementAndGet < 0) {
                throw new RuntimeException("Surface fetch completed more times than the number of surfaces.");
            } else if (decrementAndGet == 0) {
                onFetchComplete();
            }
        }
    }

    public void fetchForAllSurfaces(Context context, OkHttpClient okHttpClient, String str, QPApplication qPApplication, SurfaceFetchCallback surfaceFetchCallback) {
        Log.d(TAG, "Fetching Quick Promotions for all surfaces.");
        QuickPromotionController quickPromotionController = new QuickPromotionController(context);
        String initialize = quickPromotionController.initialize(qPApplication, true);
        if (initialize == null) {
            quickPromotionController.setAccessToken(str);
            this.mAccessToken = str;
            if (surfaceFetchCallback != null) {
                surfaceFetchCallback.setSurfaceCount(quickPromotionController.mSurfacePriorityList.size());
            }
            for (Integer num : quickPromotionController.mSurfacePriorityList) {
                quickPromotionController.fetchForSurface(okHttpClient, num.intValue(), null, surfaceFetchCallback);
            }
            Log.d(TAG, "Fetched Quick Promotions for all surfaces.");
            return;
        }
        String str2 = TAG;
        Log.e(str2, "Initializing Quick Promotions failed: " + initialize);
        throw new RuntimeException("fetchForAllSurfaces(): " + initialize);
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREF_NAME, 0);
    }

    public String initialize(QPApplication qPApplication, boolean z) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Initialized and ");
        sb.append(z ? "" : "not ");
        sb.append("enabled.");
        Log.d(str, sb.toString());
        if (this.mInitialized) {
            return "Already initialized.";
        }
        this.mInitialized = true;
        this.mEnabled = z;
        this.mAccessToken = null;
        this.mEnabledSurfaces = new HashMap<>();
        this.mSurfacePriorityList = QuickPromotionSurfaceIDs.getSurfacePriorityList(qPApplication);
        if (this.mSurfacePriorityList != null) {
            return null;
        }
        return "Unable to set surface priority list for application: " + qPApplication.name();
    }

    public void setAccessToken(String str) {
        Log.d(TAG, "Set access token.");
        this.mAccessToken = str;
    }

    public void refetchAllSurfaces() {
        if (this.mInitialized) {
            for (Integer num : this.mSurfacePriorityList) {
                fetchForSurface(getHttpClient(), num.intValue(), null, null);
            }
        }
    }

    public void enableSurface(double d) {
        int i = (int) d;
        if (this.mInitialized) {
            String str = TAG;
            Log.d(str, "Enable surface #" + i + ".");
            Integer num = this.mEnabledSurfaces.get(Integer.valueOf(i));
            if (num == null) {
                num = 0;
            }
            this.mEnabledSurfaces.put(Integer.valueOf(i), Integer.valueOf(num.intValue() + 1));
        }
    }

    /* access modifiers changed from: protected */
    public void disableSurface(double d) {
        int i = (int) d;
        if (this.mInitialized) {
            String str = TAG;
            Log.d(str, "Disable surface #" + i + ".");
            Integer num = this.mEnabledSurfaces.get(Integer.valueOf(i));
            if (num != null) {
                if (num.intValue() <= 1) {
                    this.mEnabledSurfaces.remove(Integer.valueOf(i));
                } else {
                    this.mEnabledSurfaces.put(Integer.valueOf(i), Integer.valueOf(num.intValue() - 1));
                }
            }
        }
    }

    public void markPromotionExposed(QPPromotion qPPromotion, final String str, int i) {
        if (this.mInitialized) {
            final String iDForPromotion = getIDForPromotion(qPPromotion);
            final String loggingDataForPromotion = getLoggingDataForPromotion(qPPromotion);
            if (this.mAccessToken != null) {
                GraphQLUtil.post(getHttpClient(), QuickPromotionGraphQL.LOG_EVENT_MUTATION, this.mAccessToken, QuickPromotionGraphQL.getParamsForExposure(iDForPromotion, loggingDataForPromotion, i, str), new GraphQLUtil.Result() {
                    /* class com.oculus.common.quickpromotion.QuickPromotionController.AnonymousClass1 */

                    @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                    public void onFailure(String str) {
                        String str2 = QuickPromotionController.TAG;
                        Log.e(str2, "onFailure: " + str);
                        AnalyticsEvent analyticsEvent = new AnalyticsEvent(QuickPromotionController.MODULE_NAME, QuickPromotionController.EVENT_EXPOSURE);
                        analyticsEvent.setExtra(QuickPromotionController.KEY_PROMOTION_ID, iDForPromotion);
                        analyticsEvent.setExtra(QuickPromotionController.KEY_TRIGGER, str);
                        analyticsEvent.setExtra(QuickPromotionController.KEY_INSTANCE_LOG_DATA, loggingDataForPromotion);
                        QuickPromotionController.this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
                    }

                    @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                    public void onSuccess(JSONObject jSONObject) {
                        Log.i(QuickPromotionController.TAG, "markPromotionExposed() successful");
                    }
                });
            }
        }
    }

    public void markPromotionShown(QPPromotion qPPromotion, final String str, int i) {
        if (this.mInitialized) {
            final String iDForPromotion = getIDForPromotion(qPPromotion);
            final String loggingDataForPromotion = getLoggingDataForPromotion(qPPromotion);
            if (this.mAccessToken != null) {
                GraphQLUtil.post(getHttpClient(), QuickPromotionGraphQL.LOG_EVENT_MUTATION, this.mAccessToken, QuickPromotionGraphQL.getParamsForView(iDForPromotion, loggingDataForPromotion, i, str), new GraphQLUtil.Result() {
                    /* class com.oculus.common.quickpromotion.QuickPromotionController.AnonymousClass2 */

                    @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                    public void onFailure(String str) {
                        String str2 = QuickPromotionController.TAG;
                        Log.e(str2, "onFailure: " + str);
                        AnalyticsEvent analyticsEvent = new AnalyticsEvent(QuickPromotionController.MODULE_NAME, QuickPromotionController.EVENT_VIEW);
                        analyticsEvent.setExtra(QuickPromotionController.KEY_PROMOTION_ID, iDForPromotion);
                        analyticsEvent.setExtra(QuickPromotionController.KEY_TRIGGER, str);
                        analyticsEvent.setExtra(QuickPromotionController.KEY_INSTANCE_LOG_DATA, loggingDataForPromotion);
                        QuickPromotionController.this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
                    }

                    @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                    public void onSuccess(JSONObject jSONObject) {
                        Log.i(QuickPromotionController.TAG, "markPromotionShown() successful");
                    }
                });
            }
        }
    }

    public void markPromotionAction(QPPromotion qPPromotion, final String str, final String str2, int i) {
        if (this.mInitialized) {
            final String iDForPromotion = getIDForPromotion(qPPromotion);
            final String loggingDataForPromotion = getLoggingDataForPromotion(qPPromotion);
            if (this.mAccessToken != null) {
                GraphQLUtil.post(getHttpClient(), QuickPromotionGraphQL.LOG_EVENT_MUTATION, this.mAccessToken, QuickPromotionGraphQL.getParamsForAction(iDForPromotion, loggingDataForPromotion, i, str, str2), new GraphQLUtil.Result() {
                    /* class com.oculus.common.quickpromotion.QuickPromotionController.AnonymousClass3 */

                    @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                    public void onFailure(String str) {
                        String str2 = QuickPromotionController.TAG;
                        Log.e(str2, "onFailure: " + str);
                        AnalyticsEvent analyticsEvent = new AnalyticsEvent(QuickPromotionController.MODULE_NAME, QuickPromotionController.EVENT_ACTION);
                        analyticsEvent.setExtra(QuickPromotionController.KEY_PROMOTION_ID, iDForPromotion);
                        analyticsEvent.setExtra(QuickPromotionController.KEY_TRIGGER, str);
                        analyticsEvent.setExtra(QuickPromotionController.KEY_INSTANCE_LOG_DATA, loggingDataForPromotion);
                        analyticsEvent.setExtra(QuickPromotionController.KEY_OBJECT_ID, str2);
                        QuickPromotionController.this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
                    }

                    @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                    public void onSuccess(JSONObject jSONObject) {
                        Log.i(QuickPromotionController.TAG, "markPromotionAction() successful");
                    }
                });
            }
        }
    }

    private ArrayList<QPPromotion> getPromotionsForSurface(int i, String str) {
        if (!this.mEnabled) {
            return null;
        }
        if (!isCacheUpToDateForSurface(i)) {
            fetchForSurface(getHttpClient(), i, str, null);
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.mSharedPreferences.getString(getSurfaceListSharedPreferenceKey(i), null));
            String string = jSONObject.getString(OculusContent.Profile.LOCALE);
            if (string.equals(this.mLocale)) {
                return convertJSONArrayToQPPromotionParcelList(jSONObject.optJSONArray("promotions"));
            }
            String str2 = TAG;
            Log.i(str2, "Mismatched cache locale: `" + string + "` vs `" + this.mLocale + "`.");
            return null;
        } catch (JSONException unused) {
            return null;
        }
    }

    private ArrayList<QPPromotion> convertJSONArrayToQPPromotionParcelList(JSONArray jSONArray) {
        if (jSONArray == null) {
            Log.e(TAG, "Null array cannot be converted to promotion parcel list");
            return null;
        }
        ArrayList<QPPromotion> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(QPPromotion.makeFromJSONObject(jSONArray.getJSONObject(i).getJSONObject("node")));
            } catch (JSONException unused) {
                return null;
            }
        }
        return arrayList;
    }

    public AUITooltipQuickPromotionModel getAUITooltipQP() {
        ArrayList<QPPromotion> promotionsForSurface = getPromotionsForSurface(QuickPromotionSurfaceIDs.OCULUS_AUI_TOOLTIP_QP, null);
        if (promotionsForSurface == null || promotionsForSurface.isEmpty()) {
            return null;
        }
        return new AUITooltipQuickPromotionModel(promotionsForSurface.get(0));
    }

    public AUIGuideBarQuickPromotionModel getAUIGuideBarQP() {
        ArrayList<QPPromotion> promotionsForSurface = getPromotionsForSurface(QuickPromotionSurfaceIDs.OCULUS_AUI_GUIDE_BAR_QP, null);
        if (promotionsForSurface == null || promotionsForSurface.isEmpty()) {
            return null;
        }
        return new AUIGuideBarQuickPromotionModel(promotionsForSurface.get(0));
    }

    private void fetchForSurface(OkHttpClient okHttpClient, final int i, final String str, final SurfaceFetchCallback surfaceFetchCallback) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject();
            jSONObject.put(QuickPromotionGraphQL.ARGUMENT_SURFACE_ID, i);
        } catch (JSONException e) {
            String str2 = TAG;
            Log.e(str2, "Json error: " + e.getMessage());
            jSONObject = null;
        }
        String str3 = this.mAccessToken;
        if (str3 != null) {
            GraphQLUtil.query(okHttpClient, QuickPromotionGraphQL.QUICK_PROMOTION_QUERY, str3, jSONObject, new GraphQLUtil.Result() {
                /* class com.oculus.common.quickpromotion.QuickPromotionController.AnonymousClass4 */

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onFailure(String str) {
                    String str2 = QuickPromotionController.TAG;
                    Log.e(str2, "onFailure: " + str);
                    SurfaceFetchCallback surfaceFetchCallback = surfaceFetchCallback;
                    if (surfaceFetchCallback != null) {
                        surfaceFetchCallback.markSurfaceComplete();
                    }
                }

                @Override // com.oculus.graphql.oc.GraphQLUtil.Result
                public void onSuccess(JSONObject jSONObject) {
                    JSONObject optJSONObject = jSONObject.optJSONObject("viewer");
                    if (optJSONObject == null) {
                        Log.e(QuickPromotionController.TAG, "onFailure: missing viewer tag");
                        QuickPromotionController.this.invalidateCacheForSurface(i);
                        SurfaceFetchCallback surfaceFetchCallback = surfaceFetchCallback;
                        if (surfaceFetchCallback != null) {
                            surfaceFetchCallback.markSurfaceComplete();
                            return;
                        }
                        return;
                    }
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("eligible_promotions");
                    if (optJSONObject2 == null) {
                        Log.e(QuickPromotionController.TAG, "onFailure: missing eligible_promotions tag");
                        QuickPromotionController.this.invalidateCacheForSurface(i);
                        SurfaceFetchCallback surfaceFetchCallback2 = surfaceFetchCallback;
                        if (surfaceFetchCallback2 != null) {
                            surfaceFetchCallback2.markSurfaceComplete();
                            return;
                        }
                        return;
                    }
                    JSONArray optJSONArray = optJSONObject2.optJSONArray("edges");
                    if (optJSONArray == null) {
                        Log.e(QuickPromotionController.TAG, "onFailure: missing edges tag");
                        QuickPromotionController.this.invalidateCacheForSurface(i);
                        SurfaceFetchCallback surfaceFetchCallback3 = surfaceFetchCallback;
                        if (surfaceFetchCallback3 != null) {
                            surfaceFetchCallback3.markSurfaceComplete();
                            return;
                        }
                        return;
                    }
                    QuickPromotionController.this.storeListInSharedPreferences(i, optJSONArray);
                    String str = str;
                    if (str != null) {
                        QuickPromotionController.this.signalTrigger(str);
                    }
                    SurfaceFetchCallback surfaceFetchCallback4 = surfaceFetchCallback;
                    if (surfaceFetchCallback4 != null) {
                        surfaceFetchCallback4.markSurfaceComplete();
                    }
                }
            });
        } else if (surfaceFetchCallback != null) {
            surfaceFetchCallback.markSurfaceComplete();
        }
    }

    private int getSurfaceIDForPromotion(QPPromotion qPPromotion) {
        return (int) qPPromotion.surface_id;
    }

    private String getIDForPromotion(QPPromotion qPPromotion) {
        return qPPromotion.promotion_id;
    }

    private String getLoggingDataForPromotion(QPPromotion qPPromotion) {
        return qPPromotion.logging_data;
    }

    private boolean isCacheUpToDateForSurface(int i) {
        return !this.mSharedPreferences.getBoolean(getSurfaceIsDirtySharedPreferenceKey(i), true);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void storeListInSharedPreferences(int i, JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("promotions", jSONArray);
            jSONObject.put("timestamp", Long.toString(System.currentTimeMillis()));
            jSONObject.put(OculusContent.Profile.LOCALE, this.mLocale);
            this.mSharedPreferences.edit().putBoolean(getSurfaceIsDirtySharedPreferenceKey(i), false).putString(getSurfaceListSharedPreferenceKey(i), jSONObject.toString()).apply();
        } catch (JSONException e) {
            String str = TAG;
            Log.e(str, "Json error storing promotions: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void invalidateCacheForSurface(int i) {
        storeListInSharedPreferences(i, new JSONArray());
        this.mSharedPreferences.edit().putBoolean(getSurfaceIsDirtySharedPreferenceKey(i), true).apply();
    }

    private static String getSurfaceListSharedPreferenceKey(int i) {
        return SHARED_PREF_KEY_ROOT_PROMOTIONS_BY_SURFACE + Integer.toString(i);
    }

    private static String getSurfaceIsDirtySharedPreferenceKey(int i) {
        return SHARED_PREF_KEY_ROOT_PROMOTIONS_BY_SURFACE_DIRTY_FLAG + Integer.toString(i);
    }

    public static class QPPromotion extends JSONParcel {
        public boolean bypass_surface_delay;
        public QPPromotionContextual_filters contextual_filters;
        public boolean is_holdout;
        public String logging_data;
        public double max_impressions;
        public List<QPCreative> promotion_creatives;
        public String promotion_id;
        public QPTemplate promotion_template;
        public double surface_id;
        public List<String> triggers;

        @Override // com.oculus.common.quickpromotion.JSONParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.bypass_surface_delay = jSONObject.optBoolean("bypass_surface_delay");
            this.contextual_filters = QPPromotionContextual_filters.makeFromJSONObject(jSONObject.optJSONObject("contextual_filters"));
            this.is_holdout = jSONObject.optBoolean("is_holdout");
            this.logging_data = jSONObject.optString("logging_data");
            this.max_impressions = jSONObject.optDouble("max_impressions", 0.0d);
            this.promotion_creatives = JSONParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("promotion_creatives"), QPCreative.class);
            this.promotion_id = jSONObject.optString(QuickPromotionController.KEY_PROMOTION_ID);
            this.promotion_template = QPTemplate.makeFromJSONObject(jSONObject.optJSONObject("promotion_template"));
            this.surface_id = jSONObject.optDouble(QuickPromotionGraphQL.ARGUMENT_SURFACE_ID, 0.0d);
            this.triggers = JSONParcel.convertJSONArrayToStringList(jSONObject.optJSONArray("triggers"));
        }

        public static final QPPromotion makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPPromotion qPPromotion = new QPPromotion();
            qPPromotion.setFromJSONObject(jSONObject);
            return qPPromotion;
        }
    }

    public static class QPPromotionContextual_filters extends JSONParcel {
        public String clause_type;
        public List<QPPromotionContextual_filtersClauses> clauses;
        public List<QPFilter> filters;

        @Override // com.oculus.common.quickpromotion.JSONParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            ArrayList arrayList;
            ArrayList arrayList2 = null;
            this.clause_type = jSONObject.isNull("clause_type") ? null : jSONObject.optString("clause_type");
            if (jSONObject.isNull("filters")) {
                arrayList = null;
            } else {
                arrayList = JSONParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("filters"), QPFilter.class);
            }
            this.filters = arrayList;
            if (!jSONObject.isNull("clauses")) {
                arrayList2 = JSONParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("clauses"), QPPromotionContextual_filtersClauses.class);
            }
            this.clauses = arrayList2;
        }

        public static final QPPromotionContextual_filters makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPPromotionContextual_filters qPPromotionContextual_filters = new QPPromotionContextual_filters();
            qPPromotionContextual_filters.setFromJSONObject(jSONObject);
            return qPPromotionContextual_filters;
        }
    }

    public static class QPFilter extends JSONParcel {
        public List<QPParameter> extra_datas;
        public String filter_type;
        public String unknown_action;
        public QPParameter value;

        @Override // com.oculus.common.quickpromotion.JSONParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.extra_datas = JSONParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("extra_datas"), QPParameter.class);
            this.filter_type = jSONObject.optString("filter_type");
            this.unknown_action = jSONObject.optString("unknown_action");
            this.value = jSONObject.isNull(AssistantComponentContract.Components.TextComponent.VALUE) ? null : QPParameter.makeFromJSONObject(jSONObject.optJSONObject(AssistantComponentContract.Components.TextComponent.VALUE));
        }

        public static final QPFilter makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPFilter qPFilter = new QPFilter();
            qPFilter.setFromJSONObject(jSONObject);
            return qPFilter;
        }
    }

    public static class QPParameter extends JSONParcel {
        public Boolean bool_value;
        public String color_value;
        public Double float_value;
        public Double int_value;
        public String name;
        public boolean required;
        public String string_value;
        public String uri_value;

        @Override // com.oculus.common.quickpromotion.JSONParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String str = null;
            this.bool_value = jSONObject.isNull("bool_value") ? null : Boolean.valueOf(jSONObject.optBoolean("bool_value"));
            this.color_value = jSONObject.isNull("color_value") ? null : jSONObject.optString("color_value");
            this.float_value = jSONObject.isNull("float_value") ? null : Double.valueOf(jSONObject.optDouble("float_value", 0.0d));
            this.int_value = jSONObject.isNull("int_value") ? null : Double.valueOf(jSONObject.optDouble("int_value", 0.0d));
            this.name = jSONObject.optString("name");
            this.required = jSONObject.optBoolean(AssetContract.AssetTableColumns.REQUIRED);
            this.string_value = jSONObject.isNull("string_value") ? null : jSONObject.optString("string_value");
            if (!jSONObject.isNull("uri_value")) {
                str = jSONObject.optString("uri_value");
            }
            this.uri_value = str;
        }

        public static final QPParameter makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPParameter qPParameter = new QPParameter();
            qPParameter.setFromJSONObject(jSONObject);
            return qPParameter;
        }
    }

    public static class QPPromotionContextual_filtersClauses extends JSONParcel {
        public String clause_type;
        public List<QPPromotionContextual_filtersClausesClauses> clauses;
        public List<QPFilter> filters;

        @Override // com.oculus.common.quickpromotion.JSONParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            ArrayList arrayList;
            ArrayList arrayList2 = null;
            this.clause_type = jSONObject.isNull("clause_type") ? null : jSONObject.optString("clause_type");
            if (jSONObject.isNull("filters")) {
                arrayList = null;
            } else {
                arrayList = JSONParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("filters"), QPFilter.class);
            }
            this.filters = arrayList;
            if (!jSONObject.isNull("clauses")) {
                arrayList2 = JSONParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("clauses"), QPPromotionContextual_filtersClausesClauses.class);
            }
            this.clauses = arrayList2;
        }

        public static final QPPromotionContextual_filtersClauses makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPPromotionContextual_filtersClauses qPPromotionContextual_filtersClauses = new QPPromotionContextual_filtersClauses();
            qPPromotionContextual_filtersClauses.setFromJSONObject(jSONObject);
            return qPPromotionContextual_filtersClauses;
        }
    }

    public static class QPPromotionContextual_filtersClausesClauses extends JSONParcel {
        public String clause_type;
        public List<QPPromotionContextual_filtersClausesClausesClauses> clauses;
        public List<QPFilter> filters;

        @Override // com.oculus.common.quickpromotion.JSONParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            ArrayList arrayList;
            ArrayList arrayList2 = null;
            this.clause_type = jSONObject.isNull("clause_type") ? null : jSONObject.optString("clause_type");
            if (jSONObject.isNull("filters")) {
                arrayList = null;
            } else {
                arrayList = JSONParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("filters"), QPFilter.class);
            }
            this.filters = arrayList;
            if (!jSONObject.isNull("clauses")) {
                arrayList2 = JSONParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("clauses"), QPPromotionContextual_filtersClausesClausesClauses.class);
            }
            this.clauses = arrayList2;
        }

        public static final QPPromotionContextual_filtersClausesClauses makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPPromotionContextual_filtersClausesClauses qPPromotionContextual_filtersClausesClauses = new QPPromotionContextual_filtersClausesClauses();
            qPPromotionContextual_filtersClausesClauses.setFromJSONObject(jSONObject);
            return qPPromotionContextual_filtersClausesClauses;
        }
    }

    public static class QPPromotionContextual_filtersClausesClausesClauses extends JSONParcel {
        public String clause_type;
        public List<QPPromotionContextual_filtersClausesClausesClausesClauses> clauses;
        public List<QPFilter> filters;

        @Override // com.oculus.common.quickpromotion.JSONParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            ArrayList arrayList;
            ArrayList arrayList2 = null;
            this.clause_type = jSONObject.isNull("clause_type") ? null : jSONObject.optString("clause_type");
            if (jSONObject.isNull("filters")) {
                arrayList = null;
            } else {
                arrayList = JSONParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("filters"), QPFilter.class);
            }
            this.filters = arrayList;
            if (!jSONObject.isNull("clauses")) {
                arrayList2 = JSONParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("clauses"), QPPromotionContextual_filtersClausesClausesClausesClauses.class);
            }
            this.clauses = arrayList2;
        }

        public static final QPPromotionContextual_filtersClausesClausesClauses makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPPromotionContextual_filtersClausesClausesClauses qPPromotionContextual_filtersClausesClausesClauses = new QPPromotionContextual_filtersClausesClausesClauses();
            qPPromotionContextual_filtersClausesClausesClauses.setFromJSONObject(jSONObject);
            return qPPromotionContextual_filtersClausesClausesClauses;
        }
    }

    public static class QPPromotionContextual_filtersClausesClausesClausesClauses extends JSONParcel {
        public String clause_type;

        @Override // com.oculus.common.quickpromotion.JSONParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.clause_type = jSONObject.optString("clause_type");
        }

        public static final QPPromotionContextual_filtersClausesClausesClausesClauses makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPPromotionContextual_filtersClausesClausesClausesClauses qPPromotionContextual_filtersClausesClausesClausesClauses = new QPPromotionContextual_filtersClausesClausesClausesClauses();
            qPPromotionContextual_filtersClausesClausesClausesClauses.setFromJSONObject(jSONObject);
            return qPPromotionContextual_filtersClausesClausesClausesClauses;
        }
    }

    public static class QPCreative extends JSONParcel {
        public QPImage auto_size_image;
        public String content_text;
        public QPAction dismiss_action;
        public QPAction primary_action;
        public QPAction secondary_action;
        public String title_text;

        @Override // com.oculus.common.quickpromotion.JSONParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            QPImage qPImage;
            QPAction qPAction;
            QPAction qPAction2;
            QPAction qPAction3;
            String str = null;
            if (jSONObject.isNull("auto_size_image")) {
                qPImage = null;
            } else {
                qPImage = QPImage.makeFromJSONObject(jSONObject.optJSONObject("auto_size_image"));
            }
            this.auto_size_image = qPImage;
            this.content_text = jSONObject.isNull("content_text") ? null : jSONObject.optString("content_text");
            if (jSONObject.isNull("dismiss_action")) {
                qPAction = null;
            } else {
                qPAction = QPAction.makeFromJSONObject(jSONObject.optJSONObject("dismiss_action"));
            }
            this.dismiss_action = qPAction;
            if (jSONObject.isNull("primary_action")) {
                qPAction2 = null;
            } else {
                qPAction2 = QPAction.makeFromJSONObject(jSONObject.optJSONObject("primary_action"));
            }
            this.primary_action = qPAction2;
            if (jSONObject.isNull("secondary_action")) {
                qPAction3 = null;
            } else {
                qPAction3 = QPAction.makeFromJSONObject(jSONObject.optJSONObject("secondary_action"));
            }
            this.secondary_action = qPAction3;
            if (!jSONObject.isNull("title_text")) {
                str = jSONObject.optString("title_text");
            }
            this.title_text = str;
        }

        public static final QPCreative makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPCreative qPCreative = new QPCreative();
            qPCreative.setFromJSONObject(jSONObject);
            return qPCreative;
        }
    }

    public static class QPImage extends JSONParcel {
        public String dimensionless_cache_key;
        public double height;
        public boolean is_silhouette;
        public String mime_type;
        public String name;
        public double scale;
        public String uri;
        public double width;

        @Override // com.oculus.common.quickpromotion.JSONParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.dimensionless_cache_key = jSONObject.optString("dimensionless_cache_key");
            this.height = jSONObject.optDouble("height", 0.0d);
            this.is_silhouette = jSONObject.optBoolean("is_silhouette");
            this.mime_type = jSONObject.optString("mime_type");
            this.name = jSONObject.optString("name");
            this.scale = jSONObject.optDouble("scale", 0.0d);
            this.uri = jSONObject.optString(AssistantComponentContract.Components.RemoteImageViewComponent.URI);
            this.width = jSONObject.optDouble("width", 0.0d);
        }

        public static final QPImage makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPImage qPImage = new QPImage();
            qPImage.setFromJSONObject(jSONObject);
            return qPImage;
        }
    }

    public static class QPAction extends JSONParcel {
        public boolean dismiss_promotion;
        public double limit;
        public String title_text;
        public String url;

        @Override // com.oculus.common.quickpromotion.JSONParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.dismiss_promotion = jSONObject.optBoolean("dismiss_promotion");
            this.limit = jSONObject.optDouble("limit", 0.0d);
            String str = null;
            this.title_text = jSONObject.isNull("title_text") ? null : jSONObject.optString("title_text");
            if (!jSONObject.isNull("url")) {
                str = jSONObject.optString("url");
            }
            this.url = str;
        }

        public static final QPAction makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPAction qPAction = new QPAction();
            qPAction.setFromJSONObject(jSONObject);
            return qPAction;
        }
    }

    public static class QPTemplate extends JSONParcel {
        public String name;
        public List<QPParameter> parameters;

        @Override // com.oculus.common.quickpromotion.JSONParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.name = jSONObject.optString("name");
            this.parameters = JSONParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray(DialogDefinitionCommon.DIALOG_PARAMETERS_KEY), QPParameter.class);
        }

        public static final QPTemplate makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            QPTemplate qPTemplate = new QPTemplate();
            qPTemplate.setFromJSONObject(jSONObject);
            return qPTemplate;
        }
    }

    public static String extractContentTextFromPromotion(QPPromotion qPPromotion) {
        if (qPPromotion.promotion_creatives == null || qPPromotion.promotion_creatives.get(0) == null) {
            return null;
        }
        return qPPromotion.promotion_creatives.get(0).content_text;
    }

    public static String extractTriggerFromPromotion(QPPromotion qPPromotion) {
        if (qPPromotion.triggers == null) {
            return null;
        }
        return qPPromotion.triggers.get(0);
    }

    public static SystemUXRoute extractCTARouteFromPromotion(QPPromotion qPPromotion) {
        if (qPPromotion.promotion_creatives == null || qPPromotion.promotion_creatives.get(0) == null || qPPromotion.promotion_creatives.get(0).primary_action == null || TextUtils.isEmpty(qPPromotion.promotion_creatives.get(0).primary_action.url)) {
            return null;
        }
        String str = qPPromotion.promotion_creatives.get(0).primary_action.url;
        return SystemUXRoute.fromPath(str.substring(0, str.length() - 1));
    }

    public static String extractCTATitleFromPromotion(QPPromotion qPPromotion) {
        if (qPPromotion.promotion_creatives == null || qPPromotion.promotion_creatives.get(0) == null || qPPromotion.promotion_creatives.get(0).primary_action == null) {
            return null;
        }
        return qPPromotion.promotion_creatives.get(0).primary_action.title_text;
    }
}
