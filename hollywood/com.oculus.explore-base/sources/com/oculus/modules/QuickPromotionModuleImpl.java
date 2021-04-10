package com.oculus.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.oculus.common.build.BuildConfig;
import com.oculus.modules.codegen.QuickPromotionModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.panellib.GraphQLUtil;
import com.oculus.panellib.quickpromotion.QuickPromotionGraphQL;
import com.oculus.panellib.quickpromotion.QuickPromotionLogEvent;
import com.oculus.panellib.quickpromotion.QuickPromotionLogging;
import com.oculus.panellib.quickpromotion.QuickPromotionSurfaceIDs;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuickPromotionModuleImpl extends QuickPromotionModule {
    private static final String SHARED_PREF_KEY_ROOT_PROMOTIONS_BY_SURFACE = "/promotions/";
    private static final String SHARED_PREF_KEY_ROOT_PROMOTIONS_BY_SURFACE_DIRTY_FLAG = "/promotions/dirty/";
    private static final String SHARED_PREF_NAME = "quick_promotion_preferences";
    private static final String TAG = QuickPromotionModule.class.getSimpleName();
    private static QuickPromotionModuleImpl mInstance = null;
    private static final ReentrantLock mInstanceLock = new ReentrantLock(true);
    private static Interceptor mInterceptor = null;
    private static boolean mShouldSendActionGraphQL = true;
    private static boolean mShouldSendExposureGraphQL = true;
    private static boolean mShouldSendImpressionGraphQL = true;
    private String mAccessToken;
    private Context mContext;
    private boolean mEnabled = false;
    private HashMap<Integer, Integer> mEnabledSurfaces = null;
    private boolean mInitialized = false;
    private final String mLocale;
    private OkHttpClient mOkHttpClient;
    private final SharedPreferences mSharedPreferences;
    private List<Integer> mSurfacePriorityList = null;

    public QuickPromotionModuleImpl(Context context) {
        this.mContext = context;
        this.mSharedPreferences = getSharedPreferences(context);
        Locale locale = context.getResources().getConfiguration().locale;
        this.mLocale = (locale == null ? Locale.getDefault() : locale).toString();
        QuickPromotionLogging.init(context);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004c, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004d, code lost:
        r3 = r2;
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006a, code lost:
        r2 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private okhttp3.OkHttpClient getHttpClient() {
        /*
        // Method dump skipped, instructions count: 108
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.QuickPromotionModuleImpl.getHttpClient():okhttp3.OkHttpClient");
    }

    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void shutdownModule() {
        mInstanceLock.lock();
        try {
            mInstance = null;
        } finally {
            mInstanceLock.unlock();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0045, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0046, code lost:
        r2 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0063, code lost:
        r1 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setHttpClientInterceptor(okhttp3.Interceptor r5) {
        /*
        // Method dump skipped, instructions count: 101
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.QuickPromotionModuleImpl.setHttpClientInterceptor(okhttp3.Interceptor):void");
    }

    public static void onLogout(Context context) {
        getSharedPreferences(context).edit().clear().apply();
        Log.d(TAG, "Cleared Quick Promotion cache");
    }

    public static abstract class SurfaceFetchCallback {
        final AtomicInteger mSurfaceCount = new AtomicInteger();

        public abstract void onFetchComplete();

        public void setSurfaceCount(int surfaceCount) {
            this.mSurfaceCount.set(surfaceCount);
        }

        public void markSurfaceComplete() {
            int count = this.mSurfaceCount.decrementAndGet();
            if (count < 0) {
                throw new RuntimeException("Surface fetch completed more times than the number of surfaces.");
            } else if (count == 0) {
                onFetchComplete();
            }
        }
    }

    public static void fetchForAllSurfaces(Context context, OkHttpClient client, String accessToken, QuickPromotionModule.QPApplication application, SurfaceFetchCallback callback) {
        Log.d(TAG, "Fetching Quick Promotions for all surfaces.");
        QuickPromotionLogging.logOverallFetchStart();
        QuickPromotionModuleImpl module = new QuickPromotionModuleImpl(context);
        String initializationError = module.initialize(application, true, mShouldSendActionGraphQL, mShouldSendExposureGraphQL, mShouldSendImpressionGraphQL);
        if (initializationError != null) {
            Log.e(TAG, "Initializing Quick Promotions failed: " + initializationError);
            QuickPromotionLogging.logOverallFetchFailure("initialization_failed", initializationError);
            throw new RuntimeException("fetchForAllSurfaces(): " + initializationError);
        }
        module.setAccessToken(accessToken);
        if (callback != null) {
            callback.setSurfaceCount(module.mSurfacePriorityList.size());
        }
        for (Integer num : module.mSurfacePriorityList) {
            module.fetchForSurface(client, num.intValue(), null, callback);
        }
        Log.d(TAG, "Fetched Quick Promotions for all surfaces.");
        QuickPromotionLogging.logOverallFetchSuccess();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREF_NAME, 0);
    }

    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void initializeImpl(QuickPromotionModule.QPApplication application, boolean enabled, boolean shouldSendActionGraphQL, boolean shouldSendExposureGraphQL, boolean shouldSendImpressionGraphQL, Resolver<Void> resolver) {
        Log.d(TAG, "Initialized and " + (enabled ? BuildConfig.PROVIDER_SUFFIX : "not ") + "enabled.");
        String initializationError = initialize(application, enabled, shouldSendActionGraphQL, shouldSendExposureGraphQL, shouldSendImpressionGraphQL);
        if (initializationError == null) {
            resolver.resolve(null);
        } else {
            resolver.reject(new Throwable("[{\"message\":\"" + initializationError + "\"}]"));
        }
    }

    private String initialize(QuickPromotionModule.QPApplication application, boolean enabled, boolean shouldSendActionGraphQL, boolean shouldSendExposureGraphQL, boolean shouldSendImpressionGraphQL) {
        if (this.mInitialized) {
            return "Already initialized.";
        }
        this.mInitialized = true;
        mShouldSendActionGraphQL = shouldSendActionGraphQL;
        mShouldSendExposureGraphQL = shouldSendExposureGraphQL;
        mShouldSendImpressionGraphQL = shouldSendImpressionGraphQL;
        this.mEnabled = enabled;
        this.mAccessToken = null;
        this.mEnabledSurfaces = new HashMap<>();
        this.mSurfacePriorityList = QuickPromotionSurfaceIDs.getSurfacePriorityList(application);
        if (this.mSurfacePriorityList == null) {
            return "Unable to set surface priority list for application: " + application.name();
        }
        return null;
    }

    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void setAccessTokenImpl(String accessToken, Resolver<Void> resolver) {
        Log.d(TAG, "Set access token.");
        if (setAccessToken(accessToken)) {
            resolver.resolve(null);
        } else {
            resolver.reject(new Throwable("[{\"message\":\"Failed to set access token.\"}]"));
        }
    }

    private boolean setAccessToken(String accessToken) {
        this.mAccessToken = accessToken;
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void refetchAllSurfacesImpl(final Resolver<Void> resolver) {
        if (!this.mInitialized) {
            resolver.reject(new Throwable("[{\"message\":\"Not initialized.\"}]"));
            return;
        }
        SurfaceFetchCallback callback = new SurfaceFetchCallback() {
            /* class com.oculus.modules.QuickPromotionModuleImpl.AnonymousClass1 */

            @Override // com.oculus.modules.QuickPromotionModuleImpl.SurfaceFetchCallback
            public void onFetchComplete() {
                resolver.resolve(null);
            }
        };
        callback.setSurfaceCount(this.mSurfacePriorityList.size());
        for (Integer num : this.mSurfacePriorityList) {
            fetchForSurface(getHttpClient(), num.intValue(), null, callback);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void refetchSurfaceImpl(double surfaceID, Resolver<Void> resolver) {
        if (!this.mInitialized) {
            resolver.reject(new Throwable("[{\"message\":\"Not initialized.\"}]"));
            return;
        }
        fetchForSurface(getHttpClient(), (int) surfaceID, null, null);
        resolver.resolve(null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void signalTriggerImpl(String trigger, Resolver<Void> resolver) {
        Log.d(TAG, "Signal trigger '" + trigger + "'.");
        String error = signalTrigger(trigger);
        if (error != null) {
            resolver.reject(new Throwable(error));
        } else {
            resolver.resolve(null);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String signalTrigger(String trigger) {
        if (!this.mInitialized) {
            return "[{\"message\":\"Not initialized.\"}]";
        }
        JSONArray potentialPromotions = new JSONArray();
        for (Integer surfaceID : this.mSurfacePriorityList) {
            if (this.mEnabledSurfaces.get(surfaceID) != null) {
                JSONObject promotionCache = getPromotionsForSurface(surfaceID.intValue(), trigger);
                if (promotionCache == null) {
                    Log.d(TAG, "Promotion cache empty. Refetching surface '" + surfaceID + "' for trigger '" + trigger + "'.");
                    return null;
                }
                long cacheTimestamp = 0;
                try {
                    cacheTimestamp = Long.parseLong(promotionCache.optString("timestamp"));
                } catch (NumberFormatException e) {
                    Log.e(TAG, "Json error in fetching timestamp: " + e.getMessage());
                }
                JSONArray promotions = promotionCache.optJSONArray("promotions");
                if (promotions == null) {
                    return "[{\"message\":\"Json error in trigger signal: invalid promotions.\"}]";
                }
                int promotionCount = promotions.length();
                for (int i = 0; i < promotionCount; i++) {
                    JSONObject promotionEdge = promotions.optJSONObject(i);
                    if (promotionEdge == null) {
                        return "[{\"message\":\"Json error in trigger signal: invalid promotion edge.\"}]";
                    }
                    long currentTime = System.currentTimeMillis();
                    if ((1000 * ((long) promotionEdge.optInt("client_ttl_seconds"))) + cacheTimestamp >= System.currentTimeMillis()) {
                        JSONObject timeRange = promotionEdge.optJSONObject("time_range");
                        if (timeRange == null) {
                            return "[{\"message\":\"Json error in trigger signal: invalid promotion time range.\"}]";
                        }
                        long start = timeRange.optLong("start") * 1000;
                        long end = timeRange.optLong("end") * 1000;
                        if (currentTime >= start && end >= currentTime) {
                            boolean isHoldout = promotionEdge.optBoolean("is_holdout");
                            JSONObject promotion = promotionEdge.optJSONObject("node");
                            if (promotion == null) {
                                return "[{\"message\":\"Json error in trigger signal: invalid promotion node.\"}]";
                            }
                            try {
                                promotion.put("is_holdout", isHoldout);
                                promotion.put("surface_id", surfaceID);
                                JSONArray triggers = promotion.optJSONArray("triggers");
                                if (triggers == null) {
                                    return "[{\"message\":\"Json error in trigger signal: no triggers.\"}]";
                                }
                                int triggerCount = triggers.length();
                                for (int j = 0; j < triggerCount; j++) {
                                    String refTrigger = triggers.optString(j);
                                    if (refTrigger == null) {
                                        return "[{\"message\":\"Json error in trigger signal: invalid trigger.\"}]";
                                    }
                                    if (refTrigger.equals(trigger)) {
                                        potentialPromotions.put(promotion);
                                    }
                                }
                                continue;
                            } catch (JSONException e2) {
                                Log.e(TAG, "Json error: " + e2.getMessage());
                                return "[{\"message\":\"Json error in trigger signal: unable to attach holdout status and surface ID.\"}]";
                            }
                        }
                    }
                }
                continue;
            }
        }
        try {
            JSONObject eventData = new JSONObject();
            eventData.put("promotions", potentialPromotions);
            eventData.put("trigger", trigger);
            emitOnTryToShow(QuickPromotionModule.QPTryToShowEvent.makeFromJSONObject(eventData));
            return null;
        } catch (JSONException e3) {
            Log.e(TAG, "Json error: " + e3.getMessage());
            return "[{\"message\":\"Json error in trigger signal: error sending potential promotions.\"}]";
        }
    }

    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void enableSurfaceImpl(double surfaceID, Resolver<Void> resolver) {
        int surfaceIDInt = (int) surfaceID;
        if (!this.mInitialized) {
            resolver.reject(new Throwable("[{\"message\":\"Not initialized.\"}]"));
            return;
        }
        Log.d(TAG, "Enable surface #" + surfaceIDInt + ".");
        Integer enabled = this.mEnabledSurfaces.get(Integer.valueOf(surfaceIDInt));
        if (enabled == null) {
            enabled = 0;
        }
        this.mEnabledSurfaces.put(Integer.valueOf(surfaceIDInt), Integer.valueOf(enabled.intValue() + 1));
        resolver.resolve(null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void disableSurfaceImpl(double surfaceID, Resolver<Void> resolver) {
        int surfaceIDInt = (int) surfaceID;
        if (!this.mInitialized) {
            resolver.reject(new Throwable("[{\"message\":\"Not initialized.\"}]"));
            return;
        }
        Log.d(TAG, "Disable surface #" + surfaceIDInt + ".");
        Integer enabled = this.mEnabledSurfaces.get(Integer.valueOf(surfaceIDInt));
        if (enabled == null) {
            resolver.reject(new Throwable("[{\"message\":\"Surface not enabled.\"}]"));
            return;
        }
        if (enabled.intValue() <= 1) {
            this.mEnabledSurfaces.remove(Integer.valueOf(surfaceIDInt));
        } else {
            this.mEnabledSurfaces.put(Integer.valueOf(surfaceIDInt), Integer.valueOf(enabled.intValue() - 1));
        }
        resolver.resolve(null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void markPromotionHoldoutExposedImpl(QuickPromotionModule.QPPromotion promotion, String trigger, Resolver<Void> resolver) {
        markPromotionExposedHelper(promotion, trigger, resolver, true);
    }

    /* access modifiers changed from: protected */
    public void markPromotionExposedHelper(QuickPromotionModule.QPPromotion promotion, final String trigger, Resolver<Void> resolver, final boolean is_holdout) {
        if (!this.mInitialized) {
            resolver.reject(new Throwable("[{\"message\":\"Not initialized.\"}]"));
            return;
        }
        int surfaceID = getSurfaceIDForPromotion(promotion);
        final String promotionID = getIDForPromotion(promotion);
        final String logData = getLoggingDataForPromotion(promotion);
        if (is_holdout) {
            QuickPromotionLogging.logPromotionHoldoutExposure(promotionID, surfaceID, trigger, logData);
        } else {
            QuickPromotionLogging.logPromotionExposure(promotionID, surfaceID, trigger, logData);
        }
        if (this.mAccessToken != null && mShouldSendExposureGraphQL) {
            GraphQLUtil.query(getHttpClient(), "Mutation QuickPromotionLogEventData : QuickPromotionLogEventResponsePayload {quick_promotion_log_event(<input>) {client_mutation_id,}}", this.mAccessToken, QuickPromotionGraphQL.getParamsForExposure(promotionID, logData, surfaceID, trigger), new GraphQLUtil.Callback() {
                /* class com.oculus.modules.QuickPromotionModuleImpl.AnonymousClass2 */

                @Override // com.oculus.panellib.GraphQLUtil.Callback
                public void onFailure(String exception) {
                    Log.e(QuickPromotionModuleImpl.TAG, "onFailure: " + exception);
                    if (is_holdout) {
                        QuickPromotionLogEvent.logHoldoutExposureEvent(QuickPromotionModuleImpl.this.mContext, promotionID, trigger, logData);
                    } else {
                        QuickPromotionLogEvent.logExposureEvent(QuickPromotionModuleImpl.this.mContext, promotionID, trigger, logData);
                    }
                }

                @Override // com.oculus.panellib.GraphQLUtil.Callback
                public void onSuccess(JSONObject response) {
                    Log.i(QuickPromotionModuleImpl.TAG, "markPromotionExposed() successful");
                }
            });
        }
        resolver.resolve(null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void markPromotionExposedImpl(QuickPromotionModule.QPPromotion promotion, String trigger, Resolver<Void> resolver) {
        markPromotionExposedHelper(promotion, trigger, resolver, false);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void markPromotionShownImpl(QuickPromotionModule.QPPromotion promotion, final String trigger, Resolver<Void> resolver) {
        if (!this.mInitialized) {
            resolver.reject(new Throwable("[{\"message\":\"Not initialized.\"}]"));
            return;
        }
        int surfaceID = getSurfaceIDForPromotion(promotion);
        final String promotionID = getIDForPromotion(promotion);
        final String logData = getLoggingDataForPromotion(promotion);
        QuickPromotionLogging.logPromotionImpression(promotionID, surfaceID, trigger, logData);
        if (this.mAccessToken != null && mShouldSendImpressionGraphQL) {
            GraphQLUtil.query(getHttpClient(), "Mutation QuickPromotionLogEventData : QuickPromotionLogEventResponsePayload {quick_promotion_log_event(<input>) {client_mutation_id,}}", this.mAccessToken, QuickPromotionGraphQL.getParamsForView(promotionID, logData, surfaceID, trigger), new GraphQLUtil.Callback() {
                /* class com.oculus.modules.QuickPromotionModuleImpl.AnonymousClass3 */

                @Override // com.oculus.panellib.GraphQLUtil.Callback
                public void onFailure(String exception) {
                    Log.e(QuickPromotionModuleImpl.TAG, "onFailure: " + exception);
                    QuickPromotionLogEvent.logViewEvent(QuickPromotionModuleImpl.this.mContext, promotionID, trigger, logData);
                }

                @Override // com.oculus.panellib.GraphQLUtil.Callback
                public void onSuccess(JSONObject response) {
                    Log.i(QuickPromotionModuleImpl.TAG, "markPromotionShown() successful");
                }
            });
        }
        resolver.resolve(null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void markPromotionActionImpl(QuickPromotionModule.QPPromotion promotion, final String trigger, final String action, Resolver<Void> resolver) {
        if (!this.mInitialized) {
            resolver.reject(new Throwable("[{\"message\":\"Not initialized.\"}]"));
            return;
        }
        int surfaceID = getSurfaceIDForPromotion(promotion);
        final String promotionID = getIDForPromotion(promotion);
        final String logData = getLoggingDataForPromotion(promotion);
        QuickPromotionLogging.logPromotionAction(action, promotionID, surfaceID, trigger, logData);
        if (this.mAccessToken != null && mShouldSendActionGraphQL) {
            GraphQLUtil.query(getHttpClient(), "Mutation QuickPromotionLogEventData : QuickPromotionLogEventResponsePayload {quick_promotion_log_event(<input>) {client_mutation_id,}}", this.mAccessToken, QuickPromotionGraphQL.getParamsForAction(promotionID, logData, surfaceID, trigger, action), new GraphQLUtil.Callback() {
                /* class com.oculus.modules.QuickPromotionModuleImpl.AnonymousClass4 */

                @Override // com.oculus.panellib.GraphQLUtil.Callback
                public void onFailure(String exception) {
                    Log.e(QuickPromotionModuleImpl.TAG, "onFailure: " + exception);
                    QuickPromotionLogEvent.logClickEvent(QuickPromotionModuleImpl.this.mContext, promotionID, trigger, logData, action);
                }

                @Override // com.oculus.panellib.GraphQLUtil.Callback
                public void onSuccess(JSONObject response) {
                    Log.i(QuickPromotionModuleImpl.TAG, "markPromotionAction() successful");
                }
            });
        }
        resolver.resolve(null);
    }

    private JSONObject getPromotionsForSurface(int surfaceID, String trigger) {
        if (!this.mEnabled) {
            return null;
        }
        if (!isCacheUpToDateForSurface(surfaceID)) {
            fetchForSurface(getHttpClient(), surfaceID, trigger, null);
            return null;
        }
        try {
            JSONObject promotions = new JSONObject(this.mSharedPreferences.getString(getSurfaceListSharedPreferenceKey(surfaceID), null));
            String cacheLocale = promotions.getString("locale");
            if (cacheLocale.equals(this.mLocale)) {
                return promotions;
            }
            Log.i(TAG, "Mismatched cache locale: `" + cacheLocale + "` vs `" + this.mLocale + "`.");
            return null;
        } catch (JSONException e) {
            return null;
        }
    }

    private void fetchForSurface(OkHttpClient client, final int surfaceID, final String trigger, final SurfaceFetchCallback callback) {
        JSONObject queryParams;
        QuickPromotionLogging.logPromotionFetchStart(surfaceID, trigger);
        try {
            queryParams = new JSONObject();
            queryParams.put("surface_id", surfaceID);
        } catch (JSONException e) {
            Log.e(TAG, "Json error: " + e.getMessage());
            queryParams = null;
        }
        if (this.mAccessToken != null) {
            GraphQLUtil.query(client, "viewer() {eligible_promotions.surface_nux_id(<surface_id>).include_holdouts(true).supports_client_filters(true) {edges {client_ttl_seconds,is_holdout,node {bypass_surface_delay,contextual_filters {clause_type,filters {extra_datas {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,},filter_type,unknown_action,value {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,},},clauses {clause_type,filters {extra_datas {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,},filter_type,unknown_action,value {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,},},clauses {clause_type,filters {extra_datas {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,},filter_type,unknown_action,value {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,},},clauses {clause_type,filters {extra_datas {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,},filter_type,unknown_action,value {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,},},clauses {clause_type,},},},},},logging_data,max_impressions,promotion_creatives {auto_size_image {dimensionless_cache_key,height,is_silhouette,mime_type,name,scale,uri,width,},content_text,dismiss_action {dismiss_promotion,limit,title_text,url,},primary_action {dismiss_promotion,limit,title_text,url,},secondary_action {dismiss_promotion,limit,title_text,url,},title_text,},promotion_id,promotion_template {name,parameters {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,}},triggers,},time_range {start,end},},},}", this.mAccessToken, queryParams, new GraphQLUtil.Callback() {
                /* class com.oculus.modules.QuickPromotionModuleImpl.AnonymousClass5 */

                @Override // com.oculus.panellib.GraphQLUtil.Callback
                public void onFailure(String exception) {
                    Log.e(QuickPromotionModuleImpl.TAG, "onFailure: " + exception);
                    QuickPromotionLogging.logPromotionFetchFailure(surfaceID, trigger, "exception", exception);
                    if (callback != null) {
                        callback.markSurfaceComplete();
                    }
                }

                @Override // com.oculus.panellib.GraphQLUtil.Callback
                public void onSuccess(JSONObject response) {
                    JSONObject viewer = response.optJSONObject("viewer");
                    if (viewer == null) {
                        Log.e(QuickPromotionModuleImpl.TAG, "onFailure: missing viewer tag");
                        QuickPromotionLogging.logPromotionFetchFailure(surfaceID, trigger, "missing viewer tag", null);
                        QuickPromotionModuleImpl.this.invalidateCacheForSurface(surfaceID);
                        if (callback != null) {
                            callback.markSurfaceComplete();
                            return;
                        }
                        return;
                    }
                    JSONObject eligiblePromotions = viewer.optJSONObject("eligible_promotions");
                    if (eligiblePromotions == null) {
                        Log.e(QuickPromotionModuleImpl.TAG, "onFailure: missing eligible_promotions tag");
                        QuickPromotionLogging.logPromotionFetchFailure(surfaceID, trigger, "missing eligible_promotion tag", null);
                        QuickPromotionModuleImpl.this.invalidateCacheForSurface(surfaceID);
                        if (callback != null) {
                            callback.markSurfaceComplete();
                            return;
                        }
                        return;
                    }
                    JSONArray edges = eligiblePromotions.optJSONArray("edges");
                    if (edges == null) {
                        Log.e(QuickPromotionModuleImpl.TAG, "onFailure: missing edges tag");
                        QuickPromotionLogging.logPromotionFetchFailure(surfaceID, trigger, "missing edges tag", null);
                        QuickPromotionModuleImpl.this.invalidateCacheForSurface(surfaceID);
                        if (callback != null) {
                            callback.markSurfaceComplete();
                            return;
                        }
                        return;
                    }
                    QuickPromotionModuleImpl.this.storeListInSharedPreferences(surfaceID, edges);
                    if (trigger != null) {
                        QuickPromotionModuleImpl.this.signalTrigger(trigger);
                    }
                    if (callback != null) {
                        callback.markSurfaceComplete();
                    }
                    QuickPromotionLogging.logPromotionFetchSuccess(surfaceID, trigger);
                }
            });
            return;
        }
        QuickPromotionLogging.logPromotionFetchFailure(surfaceID, trigger, "missing access token", null);
        if (callback != null) {
            callback.markSurfaceComplete();
        }
    }

    private int getSurfaceIDForPromotion(QuickPromotionModule.QPPromotion promotion) {
        return (int) promotion.surface_id;
    }

    private String getIDForPromotion(QuickPromotionModule.QPPromotion promotion) {
        return promotion.promotion_id;
    }

    private String getLoggingDataForPromotion(QuickPromotionModule.QPPromotion promotion) {
        return promotion.logging_data;
    }

    private boolean isCacheUpToDateForSurface(int surfaceID) {
        return !this.mSharedPreferences.getBoolean(getSurfaceIsDirtySharedPreferenceKey(surfaceID), true);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void storeListInSharedPreferences(int surfaceID, JSONArray promotions) {
        JSONObject promotionsToStore = new JSONObject();
        try {
            promotionsToStore.put("promotions", promotions);
            promotionsToStore.put("timestamp", Long.toString(System.currentTimeMillis()));
            promotionsToStore.put("locale", this.mLocale);
            this.mSharedPreferences.edit().putBoolean(getSurfaceIsDirtySharedPreferenceKey(surfaceID), false).putString(getSurfaceListSharedPreferenceKey(surfaceID), promotionsToStore.toString()).apply();
        } catch (JSONException e) {
            Log.e(TAG, "Json error storing promotions: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void invalidateCacheForSurface(int surfaceID) {
        storeListInSharedPreferences(surfaceID, new JSONArray());
        this.mSharedPreferences.edit().putBoolean(getSurfaceIsDirtySharedPreferenceKey(surfaceID), true).apply();
    }

    private static String getSurfaceListSharedPreferenceKey(int surfaceID) {
        return SHARED_PREF_KEY_ROOT_PROMOTIONS_BY_SURFACE + Integer.toString(surfaceID);
    }

    private static String getSurfaceIsDirtySharedPreferenceKey(int surfaceID) {
        return SHARED_PREF_KEY_ROOT_PROMOTIONS_BY_SURFACE_DIRTY_FLAG + Integer.toString(surfaceID);
    }
}
