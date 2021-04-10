package com.oculus.modules;

import X.AnonymousClass006;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.core.app.NotificationCompat$CarExtender;
import com.oculus.modules.codegen.QuickPromotionModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.panellib.GraphQLUtil;
import com.oculus.panellib.quickpromotion.QuickPromotionGraphQL;
import com.oculus.panellib.quickpromotion.QuickPromotionLogEvent;
import com.oculus.panellib.quickpromotion.QuickPromotionLogging;
import com.oculus.panellib.quickpromotion.QuickPromotionSurfaceIDs;
import com.oculus.provider.OculusContent;
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
    public static final String SHARED_PREF_KEY_ROOT_PROMOTIONS_BY_SURFACE = "/promotions/";
    public static final String SHARED_PREF_KEY_ROOT_PROMOTIONS_BY_SURFACE_DIRTY_FLAG = "/promotions/dirty/";
    public static final String SHARED_PREF_NAME = "quick_promotion_preferences";
    public static final String TAG = "QuickPromotionModule";
    public static QuickPromotionModuleImpl mInstance = null;
    public static final ReentrantLock mInstanceLock = new ReentrantLock(true);
    public static Interceptor mInterceptor = null;
    public static boolean mShouldSendActionGraphQL = true;
    public static boolean mShouldSendExposureGraphQL = true;
    public static boolean mShouldSendImpressionGraphQL = true;
    public String mAccessToken;
    public Context mContext;
    public boolean mEnabled = false;
    public HashMap<Integer, Integer> mEnabledSurfaces = null;
    public boolean mInitialized = false;
    public final String mLocale;
    public OkHttpClient mOkHttpClient;
    public final SharedPreferences mSharedPreferences;
    public List<Integer> mSurfacePriorityList = null;

    public static abstract class SurfaceFetchCallback {
        public final AtomicInteger mSurfaceCount = new AtomicInteger();

        public abstract void onFetchComplete();

        public void markSurfaceComplete() {
            int decrementAndGet = this.mSurfaceCount.decrementAndGet();
            if (decrementAndGet < 0) {
                throw new RuntimeException("Surface fetch completed more times than the number of surfaces.");
            } else if (decrementAndGet == 0) {
                onFetchComplete();
            }
        }

        public void setSurfaceCount(int i) {
            this.mSurfaceCount.set(i);
        }
    }

    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void disableSurfaceImpl(double d, Resolver<Void> resolver) {
        String str;
        int i = (int) d;
        if (!this.mInitialized) {
            str = "[{\"message\":\"Not initialized.\"}]";
        } else {
            HashMap<Integer, Integer> hashMap = this.mEnabledSurfaces;
            Integer valueOf = Integer.valueOf(i);
            Integer num = hashMap.get(valueOf);
            if (num == null) {
                str = "[{\"message\":\"Surface not enabled.\"}]";
            } else {
                int intValue = num.intValue();
                if (intValue <= 1) {
                    this.mEnabledSurfaces.remove(valueOf);
                } else {
                    this.mEnabledSurfaces.put(valueOf, Integer.valueOf(intValue - 1));
                }
                resolver.resolve(null);
                return;
            }
        }
        resolver.reject(new Throwable(str));
    }

    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void enableSurfaceImpl(double d, Resolver<Void> resolver) {
        int i = (int) d;
        if (!this.mInitialized) {
            resolver.reject(new Throwable("[{\"message\":\"Not initialized.\"}]"));
            return;
        }
        HashMap<Integer, Integer> hashMap = this.mEnabledSurfaces;
        Integer valueOf = Integer.valueOf(i);
        Integer num = hashMap.get(valueOf);
        if (num == null) {
            num = 0;
        }
        this.mEnabledSurfaces.put(valueOf, Integer.valueOf(num.intValue() + 1));
        resolver.resolve(null);
    }

    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void markPromotionActionImpl(QuickPromotionModule.QPPromotion qPPromotion, final String str, final String str2, Resolver<Void> resolver) {
        if (!this.mInitialized) {
            resolver.reject(new Throwable("[{\"message\":\"Not initialized.\"}]"));
            return;
        }
        int i = (int) qPPromotion.surface_id;
        final String str3 = qPPromotion.promotion_id;
        final String str4 = qPPromotion.logging_data;
        QuickPromotionLogging.logPromotionAction(str2, str3, i, str);
        if (this.mAccessToken != null && mShouldSendActionGraphQL) {
            GraphQLUtil.query(getHttpClient(), QuickPromotionGraphQL.LOG_EVENT_MUTATION, this.mAccessToken, QuickPromotionGraphQL.getParamsForAction(str3, str4, i, str, str2), new GraphQLUtil.Callback() {
                /* class com.oculus.modules.QuickPromotionModuleImpl.AnonymousClass4 */

                @Override // com.oculus.panellib.GraphQLUtil.Callback
                public void onFailure(String str) {
                    Log.e("QuickPromotionModule", AnonymousClass006.A07("onFailure: ", str));
                    QuickPromotionLogEvent.logClickEvent(QuickPromotionModuleImpl.this.mContext, str3, str, str4, str2);
                }

                @Override // com.oculus.panellib.GraphQLUtil.Callback
                public void onSuccess(JSONObject jSONObject) {
                }
            });
        }
        resolver.resolve(null);
    }

    public void markPromotionExposedHelper(QuickPromotionModule.QPPromotion qPPromotion, final String str, Resolver<Void> resolver, final boolean z) {
        if (!this.mInitialized) {
            resolver.reject(new Throwable("[{\"message\":\"Not initialized.\"}]"));
            return;
        }
        int i = (int) qPPromotion.surface_id;
        final String str2 = qPPromotion.promotion_id;
        final String str3 = qPPromotion.logging_data;
        if (z) {
            QuickPromotionLogging.logPromotionHoldoutExposure(str2, i, str);
        } else {
            QuickPromotionLogging.logPromotionExposure(str2, i, str);
        }
        if (this.mAccessToken != null && mShouldSendExposureGraphQL) {
            GraphQLUtil.query(getHttpClient(), QuickPromotionGraphQL.LOG_EVENT_MUTATION, this.mAccessToken, QuickPromotionGraphQL.getParamsForExposure(str2, str3, i, str), new GraphQLUtil.Callback() {
                /* class com.oculus.modules.QuickPromotionModuleImpl.AnonymousClass2 */

                @Override // com.oculus.panellib.GraphQLUtil.Callback
                public void onFailure(String str) {
                    Log.e("QuickPromotionModule", AnonymousClass006.A07("onFailure: ", str));
                    if (z) {
                        QuickPromotionLogEvent.logHoldoutExposureEvent(QuickPromotionModuleImpl.this.mContext, str2, str, str3);
                    } else {
                        QuickPromotionLogEvent.logExposureEvent(QuickPromotionModuleImpl.this.mContext, str2, str, str3);
                    }
                }

                @Override // com.oculus.panellib.GraphQLUtil.Callback
                public void onSuccess(JSONObject jSONObject) {
                }
            });
        }
        resolver.resolve(null);
    }

    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void markPromotionExposedImpl(QuickPromotionModule.QPPromotion qPPromotion, String str, Resolver<Void> resolver) {
        markPromotionExposedHelper(qPPromotion, str, resolver, false);
    }

    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void markPromotionHoldoutExposedImpl(QuickPromotionModule.QPPromotion qPPromotion, String str, Resolver<Void> resolver) {
        markPromotionExposedHelper(qPPromotion, str, resolver, true);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private okhttp3.OkHttpClient getHttpClient() {
        /*
            r5 = this;
            okhttp3.OkHttpClient r0 = r5.mOkHttpClient
            if (r0 != 0) goto L_0x0048
            java.lang.String r1 = "QuickPromotionModule"
            java.lang.String r0 = "createHttpClient"
            com.oculus.panellib.SystraceBlock r4 = new com.oculus.panellib.SystraceBlock
            r4.<init>(r1, r0)
            okhttp3.OkHttpClient$Builder r3 = new okhttp3.OkHttpClient$Builder     // Catch:{ all -> 0x0041 }
            r3.<init>()     // Catch:{ all -> 0x0041 }
            r1 = 6
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ all -> 0x0041 }
            r3.connectTimeout(r1, r0)     // Catch:{ all -> 0x0041 }
            java.util.concurrent.locks.ReentrantLock r0 = com.oculus.modules.QuickPromotionModuleImpl.mInstanceLock     // Catch:{ all -> 0x0041 }
            r0.lock()     // Catch:{ all -> 0x0041 }
            com.oculus.modules.QuickPromotionModuleImpl.mInstance = r5     // Catch:{ all -> 0x003a }
            okhttp3.Interceptor r1 = com.oculus.modules.QuickPromotionModuleImpl.mInterceptor     // Catch:{ all -> 0x003a }
            if (r1 == 0) goto L_0x0029
            java.util.List<okhttp3.Interceptor> r0 = r3.networkInterceptors     // Catch:{ all -> 0x003a }
            r0.add(r1)     // Catch:{ all -> 0x003a }
        L_0x0029:
            java.util.concurrent.locks.ReentrantLock r0 = com.oculus.modules.QuickPromotionModuleImpl.mInstanceLock
            r0.unlock()
            okhttp3.OkHttpClient r0 = r3.build()
            r5.mOkHttpClient = r0
            r4.close()
            okhttp3.OkHttpClient r0 = r5.mOkHttpClient
            return r0
        L_0x003a:
            r1 = move-exception
            java.util.concurrent.locks.ReentrantLock r0 = com.oculus.modules.QuickPromotionModuleImpl.mInstanceLock
            r0.unlock()
            throw r1
        L_0x0041:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0043 }
        L_0x0043:
            r0 = move-exception
            r4.close()     // Catch:{ all -> 0x0047 }
        L_0x0047:
            throw r0
        L_0x0048:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.QuickPromotionModuleImpl.getHttpClient():okhttp3.OkHttpClient");
    }

    private JSONObject getPromotionsForSurface(int i, String str) {
        if (this.mEnabled) {
            if (!isCacheUpToDateForSurface(i)) {
                fetchForSurface(getHttpClient(), i, str, null);
            } else {
                try {
                    JSONObject jSONObject = new JSONObject(this.mSharedPreferences.getString(getSurfaceListSharedPreferenceKey(i), null));
                    if (jSONObject.getString(OculusContent.Profile.LOCALE).equals(this.mLocale)) {
                        return jSONObject;
                    }
                } catch (JSONException unused) {
                    return null;
                }
            }
        }
        return null;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREF_NAME, 0);
    }

    private int getSurfaceIDForPromotion(QuickPromotionModule.QPPromotion qPPromotion) {
        return (int) qPPromotion.surface_id;
    }

    public static String getSurfaceIsDirtySharedPreferenceKey(int i) {
        return AnonymousClass006.A07(SHARED_PREF_KEY_ROOT_PROMOTIONS_BY_SURFACE_DIRTY_FLAG, Integer.toString(i));
    }

    public static String getSurfaceListSharedPreferenceKey(int i) {
        return AnonymousClass006.A07(SHARED_PREF_KEY_ROOT_PROMOTIONS_BY_SURFACE, Integer.toString(i));
    }

    private String initialize(QuickPromotionModule.QPApplication qPApplication, boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.mInitialized) {
            return "Already initialized.";
        }
        this.mInitialized = true;
        mShouldSendActionGraphQL = z2;
        mShouldSendExposureGraphQL = z3;
        mShouldSendImpressionGraphQL = z4;
        this.mEnabled = z;
        this.mAccessToken = null;
        this.mEnabledSurfaces = new HashMap<>();
        List<Integer> surfacePriorityList = QuickPromotionSurfaceIDs.getSurfacePriorityList(qPApplication);
        this.mSurfacePriorityList = surfacePriorityList;
        if (surfacePriorityList == null) {
            return AnonymousClass006.A07("Unable to set surface priority list for application: ", qPApplication.name());
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void invalidateCacheForSurface(int i) {
        storeListInSharedPreferences(i, new JSONArray());
        this.mSharedPreferences.edit().putBoolean(getSurfaceIsDirtySharedPreferenceKey(i), true).apply();
    }

    private boolean isCacheUpToDateForSurface(int i) {
        return !this.mSharedPreferences.getBoolean(getSurfaceIsDirtySharedPreferenceKey(i), true);
    }

    private boolean setAccessToken(String str) {
        this.mAccessToken = str;
        return true;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003d, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setHttpClientInterceptor(okhttp3.Interceptor r4) {
        /*
            com.oculus.modules.QuickPromotionModuleImpl.mInterceptor = r4
            java.lang.String r1 = "QuickPromotionModule"
            java.lang.String r0 = "setHttpClientInterceptor"
            com.oculus.panellib.SystraceBlock r3 = new com.oculus.panellib.SystraceBlock
            r3.<init>(r1, r0)
            java.util.concurrent.locks.ReentrantLock r0 = com.oculus.modules.QuickPromotionModuleImpl.mInstanceLock     // Catch:{ all -> 0x0037 }
            r0.lock()     // Catch:{ all -> 0x0037 }
            com.oculus.modules.QuickPromotionModuleImpl r2 = com.oculus.modules.QuickPromotionModuleImpl.mInstance     // Catch:{ all -> 0x0030 }
            if (r2 == 0) goto L_0x0027
            okhttp3.OkHttpClient r0 = r2.mOkHttpClient     // Catch:{ all -> 0x0030 }
            if (r0 == 0) goto L_0x0027
            okhttp3.OkHttpClient$Builder r1 = r0.newBuilder()     // Catch:{ all -> 0x0030 }
            java.util.List<okhttp3.Interceptor> r0 = r1.networkInterceptors     // Catch:{ all -> 0x0030 }
            r0.add(r4)     // Catch:{ all -> 0x0030 }
            okhttp3.OkHttpClient r0 = r1.build()     // Catch:{ all -> 0x0030 }
            r2.mOkHttpClient = r0     // Catch:{ all -> 0x0030 }
        L_0x0027:
            java.util.concurrent.locks.ReentrantLock r0 = com.oculus.modules.QuickPromotionModuleImpl.mInstanceLock
            r0.unlock()
            r3.close()
            return
        L_0x0030:
            r1 = move-exception
            java.util.concurrent.locks.ReentrantLock r0 = com.oculus.modules.QuickPromotionModuleImpl.mInstanceLock
            r0.unlock()
            throw r1
        L_0x0037:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r0 = move-exception
            r3.close()     // Catch:{ all -> 0x003d }
        L_0x003d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.QuickPromotionModuleImpl.setHttpClientInterceptor(okhttp3.Interceptor):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String signalTrigger(String str) {
        if (!this.mInitialized) {
            return "[{\"message\":\"Not initialized.\"}]";
        }
        JSONArray jSONArray = new JSONArray();
        for (Integer num : this.mSurfacePriorityList) {
            if (this.mEnabledSurfaces.get(num) != null) {
                JSONObject promotionsForSurface = getPromotionsForSurface(num.intValue(), str);
                if (promotionsForSurface == null) {
                    return null;
                }
                long j = 0;
                try {
                    j = Long.parseLong(promotionsForSurface.optString(NotificationCompat$CarExtender.KEY_TIMESTAMP));
                } catch (NumberFormatException e) {
                    Log.e("QuickPromotionModule", AnonymousClass006.A07("Json error in fetching timestamp: ", e.getMessage()));
                }
                JSONArray optJSONArray = promotionsForSurface.optJSONArray("promotions");
                if (optJSONArray == null) {
                    return "[{\"message\":\"Json error in trigger signal: invalid promotions.\"}]";
                }
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject == null) {
                        return "[{\"message\":\"Json error in trigger signal: invalid promotion edge.\"}]";
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    if ((((long) optJSONObject.optInt("client_ttl_seconds")) * 1000) + j >= System.currentTimeMillis()) {
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject("time_range");
                        if (optJSONObject2 == null) {
                            return "[{\"message\":\"Json error in trigger signal: invalid promotion time range.\"}]";
                        }
                        long optLong = optJSONObject2.optLong("end") * 1000;
                        if (currentTimeMillis >= optJSONObject2.optLong("start") * 1000 && optLong >= currentTimeMillis) {
                            boolean optBoolean = optJSONObject.optBoolean("is_holdout");
                            JSONObject optJSONObject3 = optJSONObject.optJSONObject("node");
                            if (optJSONObject3 == null) {
                                return "[{\"message\":\"Json error in trigger signal: invalid promotion node.\"}]";
                            }
                            try {
                                optJSONObject3.put("is_holdout", optBoolean);
                                optJSONObject3.put("surface_id", num);
                                JSONArray optJSONArray2 = optJSONObject3.optJSONArray("triggers");
                                if (optJSONArray2 == null) {
                                    return "[{\"message\":\"Json error in trigger signal: no triggers.\"}]";
                                }
                                int length2 = optJSONArray2.length();
                                for (int i2 = 0; i2 < length2; i2++) {
                                    String optString = optJSONArray2.optString(i2);
                                    if (optString == null) {
                                        return "[{\"message\":\"Json error in trigger signal: invalid trigger.\"}]";
                                    }
                                    if (optString.equals(str)) {
                                        jSONArray.put(optJSONObject3);
                                    }
                                }
                                continue;
                            } catch (JSONException e2) {
                                Log.e("QuickPromotionModule", AnonymousClass006.A07("Json error: ", e2.getMessage()));
                                return "[{\"message\":\"Json error in trigger signal: unable to attach holdout status and surface ID.\"}]";
                            }
                        }
                    }
                }
                continue;
            }
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("promotions", jSONArray);
            jSONObject.put("trigger", str);
            emitOnTryToShow(QuickPromotionModule.QPTryToShowEvent.makeFromJSONObject(jSONObject));
            return null;
        } catch (JSONException e3) {
            Log.e("QuickPromotionModule", AnonymousClass006.A07("Json error: ", e3.getMessage()));
            return "[{\"message\":\"Json error in trigger signal: error sending potential promotions.\"}]";
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void storeListInSharedPreferences(int i, JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("promotions", jSONArray);
            jSONObject.put(NotificationCompat$CarExtender.KEY_TIMESTAMP, Long.toString(System.currentTimeMillis()));
            jSONObject.put(OculusContent.Profile.LOCALE, this.mLocale);
            this.mSharedPreferences.edit().putBoolean(getSurfaceIsDirtySharedPreferenceKey(i), false).putString(getSurfaceListSharedPreferenceKey(i), jSONObject.toString()).apply();
        } catch (JSONException e) {
            Log.e("QuickPromotionModule", AnonymousClass006.A07("Json error storing promotions: ", e.getMessage()));
        }
    }

    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void markPromotionShownImpl(QuickPromotionModule.QPPromotion qPPromotion, final String str, Resolver<Void> resolver) {
        if (!this.mInitialized) {
            resolver.reject(new Throwable("[{\"message\":\"Not initialized.\"}]"));
            return;
        }
        int i = (int) qPPromotion.surface_id;
        final String str2 = qPPromotion.promotion_id;
        final String str3 = qPPromotion.logging_data;
        QuickPromotionLogging.logPromotionImpression(str2, i, str);
        if (this.mAccessToken != null && mShouldSendImpressionGraphQL) {
            GraphQLUtil.query(getHttpClient(), QuickPromotionGraphQL.LOG_EVENT_MUTATION, this.mAccessToken, QuickPromotionGraphQL.getParamsForView(str2, str3, i, str), new GraphQLUtil.Callback() {
                /* class com.oculus.modules.QuickPromotionModuleImpl.AnonymousClass3 */

                @Override // com.oculus.panellib.GraphQLUtil.Callback
                public void onFailure(String str) {
                    Log.e("QuickPromotionModule", AnonymousClass006.A07("onFailure: ", str));
                    QuickPromotionLogEvent.logViewEvent(QuickPromotionModuleImpl.this.mContext, str2, str, str3);
                }

                @Override // com.oculus.panellib.GraphQLUtil.Callback
                public void onSuccess(JSONObject jSONObject) {
                }
            });
        }
        resolver.resolve(null);
    }

    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void refetchAllSurfacesImpl(final Resolver<Void> resolver) {
        if (!this.mInitialized) {
            resolver.reject(new Throwable("[{\"message\":\"Not initialized.\"}]"));
            return;
        }
        AnonymousClass1 r4 = new SurfaceFetchCallback() {
            /* class com.oculus.modules.QuickPromotionModuleImpl.AnonymousClass1 */

            @Override // com.oculus.modules.QuickPromotionModuleImpl.SurfaceFetchCallback
            public void onFetchComplete() {
                resolver.resolve(null);
            }
        };
        r4.setSurfaceCount(this.mSurfacePriorityList.size());
        for (Integer num : this.mSurfacePriorityList) {
            fetchForSurface(getHttpClient(), num.intValue(), null, r4);
        }
    }

    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void refetchSurfaceImpl(double d, Resolver<Void> resolver) {
        if (!this.mInitialized) {
            resolver.reject(new Throwable("[{\"message\":\"Not initialized.\"}]"));
            return;
        }
        fetchForSurface(getHttpClient(), (int) d, null, null);
        resolver.resolve(null);
    }

    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void setAccessTokenImpl(String str, Resolver<Void> resolver) {
        this.mAccessToken = str;
        resolver.resolve(null);
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

    public QuickPromotionModuleImpl(Context context) {
        this.mContext = context;
        this.mSharedPreferences = getSharedPreferences(context);
        Locale locale = context.getResources().getConfiguration().locale;
        this.mLocale = (locale == null ? Locale.getDefault() : locale).toString();
        QuickPromotionLogging.init(context);
    }

    public static /* synthetic */ String access$000() {
        return "QuickPromotionModule";
    }

    public static void fetchForAllSurfaces(Context context, OkHttpClient okHttpClient, String str, QuickPromotionModule.QPApplication qPApplication, SurfaceFetchCallback surfaceFetchCallback) {
        QuickPromotionLogging.logOverallFetchStart();
        QuickPromotionModuleImpl quickPromotionModuleImpl = new QuickPromotionModuleImpl(context);
        String initialize = quickPromotionModuleImpl.initialize(qPApplication, true, mShouldSendActionGraphQL, mShouldSendExposureGraphQL, mShouldSendImpressionGraphQL);
        if (initialize == null) {
            quickPromotionModuleImpl.mAccessToken = str;
            if (surfaceFetchCallback != null) {
                surfaceFetchCallback.setSurfaceCount(quickPromotionModuleImpl.mSurfacePriorityList.size());
            }
            for (Integer num : quickPromotionModuleImpl.mSurfacePriorityList) {
                quickPromotionModuleImpl.fetchForSurface(okHttpClient, num.intValue(), null, surfaceFetchCallback);
            }
            QuickPromotionLogging.logOverallFetchSuccess();
            return;
        }
        Log.e("QuickPromotionModule", AnonymousClass006.A07("Initializing Quick Promotions failed: ", initialize));
        QuickPromotionLogging.logOverallFetchFailure("initialization_failed", initialize);
        throw new RuntimeException(AnonymousClass006.A07("fetchForAllSurfaces(): ", initialize));
    }

    private void fetchForSurface(OkHttpClient okHttpClient, final int i, final String str, final SurfaceFetchCallback surfaceFetchCallback) {
        JSONObject jSONObject;
        QuickPromotionLogging.logPromotionFetchStart(i, str);
        try {
            jSONObject = new JSONObject();
            jSONObject.put("surface_id", i);
        } catch (JSONException e) {
            Log.e("QuickPromotionModule", AnonymousClass006.A07("Json error: ", e.getMessage()));
            jSONObject = null;
        }
        String str2 = this.mAccessToken;
        if (str2 != null) {
            GraphQLUtil.query(okHttpClient, QuickPromotionGraphQL.QUICK_PROMOTION_QUERY, str2, jSONObject, new GraphQLUtil.Callback() {
                /* class com.oculus.modules.QuickPromotionModuleImpl.AnonymousClass5 */

                @Override // com.oculus.panellib.GraphQLUtil.Callback
                public void onFailure(String str) {
                    Log.e("QuickPromotionModule", AnonymousClass006.A07("onFailure: ", str));
                    QuickPromotionLogging.logPromotionFetchFailure(i, str, "exception", str);
                    SurfaceFetchCallback surfaceFetchCallback = surfaceFetchCallback;
                    if (surfaceFetchCallback != null) {
                        surfaceFetchCallback.markSurfaceComplete();
                    }
                }

                @Override // com.oculus.panellib.GraphQLUtil.Callback
                public void onSuccess(JSONObject jSONObject) {
                    int i;
                    String str;
                    String str2;
                    JSONObject optJSONObject = jSONObject.optJSONObject("viewer");
                    if (optJSONObject == null) {
                        Log.e("QuickPromotionModule", "onFailure: missing viewer tag");
                        i = i;
                        str = str;
                        str2 = "missing viewer tag";
                    } else {
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject("eligible_promotions");
                        if (optJSONObject2 == null) {
                            Log.e("QuickPromotionModule", "onFailure: missing eligible_promotions tag");
                            i = i;
                            str = str;
                            str2 = "missing eligible_promotion tag";
                        } else {
                            JSONArray optJSONArray = optJSONObject2.optJSONArray("edges");
                            if (optJSONArray == null) {
                                Log.e("QuickPromotionModule", "onFailure: missing edges tag");
                                i = i;
                                str = str;
                                str2 = "missing edges tag";
                            } else {
                                QuickPromotionModuleImpl.this.storeListInSharedPreferences(i, optJSONArray);
                                String str3 = str;
                                if (str3 != null) {
                                    QuickPromotionModuleImpl.this.signalTrigger(str3);
                                }
                                SurfaceFetchCallback surfaceFetchCallback = surfaceFetchCallback;
                                if (surfaceFetchCallback != null) {
                                    surfaceFetchCallback.markSurfaceComplete();
                                }
                                QuickPromotionLogging.logPromotionFetchSuccess(i, str);
                                return;
                            }
                        }
                    }
                    QuickPromotionLogging.logPromotionFetchFailure(i, str, str2, null);
                    QuickPromotionModuleImpl.this.invalidateCacheForSurface(i);
                    SurfaceFetchCallback surfaceFetchCallback2 = surfaceFetchCallback;
                    if (surfaceFetchCallback2 != null) {
                        surfaceFetchCallback2.markSurfaceComplete();
                    }
                }
            });
            return;
        }
        QuickPromotionLogging.logPromotionFetchFailure(i, str, "missing access token", null);
        if (surfaceFetchCallback != null) {
            surfaceFetchCallback.markSurfaceComplete();
        }
    }

    public static void onLogout(Context context) {
        getSharedPreferences(context).edit().clear().apply();
    }

    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void initializeImpl(QuickPromotionModule.QPApplication qPApplication, boolean z, boolean z2, boolean z3, boolean z4, Resolver<Void> resolver) {
        String initialize = initialize(qPApplication, z, z2, z3, z4);
        if (initialize == null) {
            resolver.resolve(null);
        } else {
            resolver.reject(new Throwable(AnonymousClass006.A09("[{\"message\":\"", initialize, "\"}]")));
        }
    }

    @Override // com.oculus.modules.codegen.QuickPromotionModule
    public void signalTriggerImpl(String str, Resolver<Void> resolver) {
        String signalTrigger = signalTrigger(str);
        if (signalTrigger != null) {
            resolver.reject(new Throwable(signalTrigger));
        } else {
            resolver.resolve(null);
        }
    }

    private String getIDForPromotion(QuickPromotionModule.QPPromotion qPPromotion) {
        return qPPromotion.promotion_id;
    }

    private String getLoggingDataForPromotion(QuickPromotionModule.QPPromotion qPPromotion) {
        return qPPromotion.logging_data;
    }
}
