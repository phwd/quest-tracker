package com.oculus.modules;

import android.content.Context;
import android.util.Log;
import com.google.common.collect.ImmutableList;
import com.oculus.modules.codegen.QuickExperimentsModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.panellib.ThreadExecutor;
import com.oculus.util.FileUtils;
import com.oculus.vrshell.home.HomeEventLogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONException;
import org.json.JSONObject;

public class QuickExperimentsModuleImpl extends QuickExperimentsModule {
    public static final String CONFIG_ERROR = "error";
    public static final String CONFIG_ERROR_DETAILS = "error_details";
    public static final String CONFIG_EVENT_NAME = "oculus_config";
    public static final String CONFIG_STATUS = "status";
    private static final String FB_GRAPHAPI_ENDPOINT = "https://graph.facebook.com";
    private static final String HORIZON_FB_APP_TOKEN = "1437758943160428|3d88b3e2a4e039224c5377a3fcd71d38";
    private static final String QE_API_PATH = "sessionless_test_experiment_members";
    private static final String QE_CACHE_FILE = "VrShellHomeQECacheFile";
    private static final String QE_OVERRIDES_FILE = "VrShellHomeQEOverridesFile";
    private static final String QE_TIMESTAMP_FILE = "VrShellHomeQETimeStampFile";
    private static final String TAG = QuickExperimentsModuleImpl.class.getSimpleName();
    @Nullable
    private static volatile QuickExperimentsModule.GetCachedQEsResult mCachedQEs = null;
    private static final List<Resolver<QuickExperimentsModule.GetCachedQEsResult>> mInitialFetchCallbacks = new ArrayList();
    private static final ReentrantLock mInitialFetchLock = new ReentrantLock(true);
    @Nullable
    private static QuickExperimentsModuleImpl mInstance = null;
    public static final ImmutableList<String> qeList = new ImmutableList.Builder().add((Object) "sessionless__oculus_cache_cinema_category_universe").add((Object) "sessionless__oculus_chats").add((Object) "sessionless__oculus_cinema_menu_universe").add((Object) "sessionless__oculus_explore_autoplay_universe").add((Object) "sessionless__oculus_explore_preference_customization").add((Object) "sessionless__oculus_explore_recents_universe").add((Object) "sessionless__oculus_explore_topic_following_universe").add((Object) "sessionless__oculus_explore_quick_view_universe").add((Object) "sessionless__oculus_explore_context_menu").add((Object) "sessionless__oculus_gaming_activity_overview_universe").add((Object) "sessionless__oculus_journey_universe_1").add((Object) "sessionless__oculus_journey_universe_2").add((Object) "sessionless__oculus_journey_universe_3").add((Object) "sessionless__oculus_journey_universe_4").add((Object) "sessionless__oculus_journey_universe_5").add((Object) "sessionless__oculus_journey_universe_6").add((Object) "sessionless__oculus_journey_universe_7").add((Object) "sessionless__oculus_journey_universe_8").add((Object) "sessionless__oculus_journey_universe_9").add((Object) "sessionless__oculus_journey_universe_10").add((Object) "sessionless__oculus_journey_universe_11").add((Object) "sessionless__oculus_journey_universe_12").add((Object) "sessionless__oculus_journey_universe_13").add((Object) "sessionless__oculus_journey_universe_14").add((Object) "sessionless__oculus_journey_universe_15").add((Object) "sessionless__oculus_journey_universe_16").add((Object) "sessionless__oculus_journey_universe_17").add((Object) "sessionless__oculus_journey_universe_18").add((Object) "sessionless__oculus_journey_universe_19").add((Object) "sessionless__oculus_journey_universe_20").add((Object) "sessionless__oculus_home_badge_color_universe").add((Object) "sessionless__oculus_home_customize_viewtypes_universe").add((Object) "sessionless__oculus_home_exit_resume_notification_universe").add((Object) "sessionless__oculus_home_marketing_unit_special_effects").add((Object) "sessionless__oculus_home_no_internet_indicator_universe").add((Object) "sessionless__oculus_home_redesign_nav_bar_universe").add((Object) "sessionless__oculus_home_saved_universe").add((Object) "sessionless__oculus_home_search_scrolling_universe").add((Object) "sessionless__oculus_home_search_typeahead_v1").add((Object) "sessionless__oculus_home_social_events_message_guests_entrypoint_universe").add((Object) "sessionless__oculus_home_video_chaining").add((Object) "sessionless__oculus_horizon_party_live_streaming_universe").add((Object) "sessionless__oculus_join_me_links_m1_uss").add((Object) "sessionless__oculus_join_me_links_m2").add((Object) "sessionless__oculus_mobile_explore_prefetching").add((Object) "sessionless__oculus_mobile_home_search_prompt_universe").add((Object) "sessionless__oculus_mobile_search_exact_match_universe").add((Object) "sessionless__oculus_profile_report_universe").add((Object) "sessionless__oculus_quest_fbconnect_tip_universe").add((Object) "sessionless__oculus_recommendations_kudos_in_feed_universe").add((Object) "sessionless__oculus_search_home_suggested_keywords_universe").add((Object) "sessionless__oculus_search_home_web_search_universe").add((Object) "sessionless__oculus_search_home_live_search_universe").add((Object) "sessionless__oculus_search_keyboard_offset").add((Object) "sessionless__oculus_search_vr_dication_button_universe").add((Object) "sessionless__oculus_store_3d_hero_unit_universe").add((Object) "sessionless__oculus_store_3d_pdp_promo_universe").add((Object) "sessionless__oculus_store_add_on_content_reviews_universe").add((Object) "sessionless__oculus_store_disco_vr_pdp").add((Object) "sessionless__oculus_store_disco_vr_bundle_pdp").add((Object) "sessionless__oculus_store_erc_universe").add((Object) "sessionless__oculus_store_landing_disk_cache").add((Object) "sessionless__oculus_store_pdp_transition_universe").add((Object) "sessionless__oculus_store_pdp_video_player_universe").add((Object) "sessionless__oculus_store_quest_vr_filtering_2_all_sections").add((Object) "sessionless__oculus_store_quest_vr_filtering_2_section_id").add((Object) "sessionless__oculus_store_quest_vr_filtering_2_view_id").add((Object) "sessionless__oculus_store_pdp_universe").add((Object) "sessionless__oculus_video_video_chaining_universe").add((Object) "sessionless__oculus_vrshell_qetest_universe").add((Object) "sessionless__oculus_vrshell_store_collections_personalization_universe").add((Object) "sessionless__oculus_vrshell_store_collections_universe").add((Object) "sessionless__oculus_vrshell_store_dev_updates_universe").add((Object) "sessionless__oculus_vrshell_store_developer_apps_universe").add((Object) "sessionless__oculus_vrshell_store_purchase_flow_universe").add((Object) "sessionless__oculus_vrshell_store_redesign_layout_universe").add((Object) "sessionless__oculus_vrshell_store_redesign_row_scroll_universe").add((Object) "sessionless__oculus_vrshell_store_redesign_tiles_universe").add((Object) "sessionless__oculus_vrshell_store_search_universe").add((Object) "sessionless__oculus_store_pin_alternatives").add((Object) "sessionless__oculus_quest_profile_universe").add((Object) "sessionless__oculus_store_360_videos_universe").add((Object) "sessionless__oculus_feed_pagination_early_fetch").add((Object) "sessionless__oculus_feeds_hero_unit_v2").add((Object) "sessionless__oculus_fitness_tracker").add((Object) "sessionless__oculus_store_scroll_tray_universe").add((Object) "sessionless__oculus_pymk_experiments").add((Object) "sessionless__oculus_twilight_store_prompt").add((Object) "sessionless__oculus_friend_widget_poll_universe").add((Object) "sessionless__oculus_play_with_others_subfeed_universe").add((Object) "sessionless__oculus_feed_3d_hero_unit_universe").add((Object) "sessionless__oculus_explore_subfeeds_universe").add((Object) "sessionless__oculus_vr_refund_policy_upsell").add((Object) "sessionless__arvr_acq_achievements_shareables").add((Object) "sessionless__oculus_tv_search_null_state_feed_universe").add((Object) "sessionless__oculus_tv_search_null_state_keywords_universe").add((Object) "sessionless__oculus_living_room_sharing_universe").add((Object) "sessionless__oculus_living_room_scroll_tray_universe").add((Object) "sessionless__oculus_living_room_void_mode_universe").add((Object) "sessionless__oculus_living_room_fullscreen_collections").add((Object) "sessionless__oculus_search_categories_pagination_universe").add((Object) "sessionless__oculus_living_room_hide_story").add((Object) "sessionless__oculus_search_content_density_universe").add((Object) "sessionless__oculus_occx_creator_portfolio_integration").add((Object) "sessionless__oculus_remove_entitlement_from_prime").add((Object) "sessionless__oculus_explore_tv_video_renderer").add((Object) "sessionless__oculus_store_contextual_section_id_universe").add((Object) "sessionless__oculus_store_contextual_store_view_id_universe").build();
    private Context mContext = null;

    public QuickExperimentsModuleImpl(Context context) {
        mInstance = this;
        this.mContext = context;
    }

    @Override // com.oculus.modules.codegen.QuickExperimentsModule
    public void shutdownModule() {
        mInstance = null;
    }

    public static void initialFetchAsync(final Context context) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.QuickExperimentsModuleImpl.AnonymousClass1 */

            /* JADX INFO: finally extract failed */
            @Override // java.util.concurrent.Callable
            public Void call() {
                JSONObject cache = null;
                JSONObject overrides = new JSONObject();
                double timestamp = 0.0d;
                try {
                    String cacheAsString = FileUtils.readFromInternalStorage(context, QuickExperimentsModuleImpl.QE_CACHE_FILE);
                    if (!cacheAsString.isEmpty()) {
                        cache = new JSONObject(cacheAsString);
                    }
                } catch (Exception e) {
                    Log.e(QuickExperimentsModuleImpl.TAG, "initialFetchAsync(): Failed to parse cache. Json error: " + e.getMessage());
                    HomeEventLogger.reportEvent("oculus_config", "status", "qe_local_read_failed", "error", "parse_cache", "error_details", e.getMessage());
                }
                try {
                    String overridesAsString = FileUtils.readFromInternalStorage(context, QuickExperimentsModuleImpl.QE_OVERRIDES_FILE);
                    if (!overridesAsString.isEmpty()) {
                        overrides = new JSONObject(overridesAsString);
                    }
                } catch (Exception e2) {
                    Log.e(QuickExperimentsModuleImpl.TAG, "initialFetchAsync(): Failed to parse overrides. Json error: " + e2.getMessage());
                    HomeEventLogger.reportEvent("oculus_config", "status", "qe_local_read_failed", "error", "parse_overrides", "error_details", e2.getMessage());
                }
                try {
                    String timestampAsString = FileUtils.readFromInternalStorage(context, QuickExperimentsModuleImpl.QE_TIMESTAMP_FILE);
                    if (!timestampAsString.isEmpty()) {
                        timestamp = new JSONObject(timestampAsString).optDouble("timestamp");
                    }
                } catch (Exception e3) {
                    Log.e(QuickExperimentsModuleImpl.TAG, "initialFetchAsync(): Failed to parse timestamp. Json error: " + e3.getMessage());
                    HomeEventLogger.reportEvent("oculus_config", "status", "qe_local_read_failed", "error", "parse_timestamp", "error_details", e3.getMessage());
                }
                QuickExperimentsModuleImpl.mInitialFetchLock.lock();
                try {
                    QuickExperimentsModule.GetCachedQEsResult unused = QuickExperimentsModuleImpl.mCachedQEs = new QuickExperimentsModule.GetCachedQEsResult();
                    QuickExperimentsModuleImpl.mCachedQEs.cache = QuickExperimentsModule.GetCachedQEsResultCache.makeFromJSONObject(cache);
                    QuickExperimentsModuleImpl.mCachedQEs.overrides = overrides;
                    QuickExperimentsModuleImpl.mCachedQEs.timestamp = timestamp;
                    if (QuickExperimentsModuleImpl.mInstance != null) {
                        for (Resolver<QuickExperimentsModule.GetCachedQEsResult> callback : QuickExperimentsModuleImpl.mInitialFetchCallbacks) {
                            QuickExperimentsModuleImpl.mInstance.internalFetchQEsCallback(callback);
                        }
                    }
                    QuickExperimentsModuleImpl.mInitialFetchLock.unlock();
                    return null;
                } catch (Throwable th) {
                    QuickExperimentsModuleImpl.mInitialFetchLock.unlock();
                    throw th;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void internalFetchQEsCallback(Resolver<QuickExperimentsModule.GetCachedQEsResult> resolver) {
        resolver.resolve(mCachedQEs);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.QuickExperimentsModule
    public void getCachedQEsImpl(Resolver<QuickExperimentsModule.GetCachedQEsResult> resolver) {
        if (mCachedQEs != null) {
            internalFetchQEsCallback(resolver);
            return;
        }
        mInitialFetchLock.lock();
        try {
            if (mCachedQEs != null) {
                internalFetchQEsCallback(resolver);
            } else {
                mInitialFetchCallbacks.add(resolver);
            }
        } finally {
            mInitialFetchLock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.QuickExperimentsModule
    public void removeOverrideImpl(String universeName, Resolver<Void> resolver) {
        String overrides = FileUtils.readFromInternalStorage(this.mContext, QE_OVERRIDES_FILE);
        if (!overrides.isEmpty()) {
            try {
                JSONObject overrideJson = new JSONObject(overrides);
                overrideJson.remove(universeName);
                FileUtils.writeToInternalStorage(this.mContext, QE_OVERRIDES_FILE, overrideJson.toString());
            } catch (JSONException e) {
                Log.e(TAG, "removeOverride(): Json error: " + e.getMessage());
            }
        }
        resolver.resolve(null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.QuickExperimentsModule
    public void overrideImpl(String universeName, JSONObject value, Resolver<Void> resolver) {
        JSONObject overrideJson;
        String overrides = FileUtils.readFromInternalStorage(this.mContext, QE_OVERRIDES_FILE);
        if (!overrides.isEmpty()) {
            try {
                overrideJson = new JSONObject(overrides);
            } catch (JSONException e) {
                Log.e(TAG, "override(): Json error: " + e.getMessage());
                return;
            }
        } else {
            overrideJson = new JSONObject();
        }
        try {
            overrideJson.put(universeName, value);
        } catch (JSONException e2) {
            Log.e(TAG, "override(): Json error: " + e2.getMessage());
        }
        FileUtils.writeToInternalStorage(this.mContext, QE_OVERRIDES_FILE, overrideJson.toString());
        resolver.resolve(null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.QuickExperimentsModule
    public void remoteFetchQEsImpl(final String userId) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.modules.QuickExperimentsModuleImpl.AnonymousClass2 */

            /* JADX WARNING: Code restructure failed: missing block: B:21:0x0056, code lost:
                r3 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:22:0x0057, code lost:
                r3 = r2;
                r2 = r3;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:37:0x0076, code lost:
                r3 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:38:0x0077, code lost:
                r3 = r2;
                r2 = r3;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:47:0x008b, code lost:
                r2 = th;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:48:0x008d, code lost:
                r2 = th;
             */
            @Override // java.util.concurrent.Callable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Void call() {
                /*
                // Method dump skipped, instructions count: 143
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.QuickExperimentsModuleImpl.AnonymousClass2.call():java.lang.Void");
            }
        });
    }

    public static void fetchAndCacheQEs(final Context context, OkHttpClient client, final String userId, final Runnable onComplete, final Runnable onFailure) {
        try {
            HomeEventLogger.reportEvent("oculus_config", "status", "qe_fetch_started");
            HttpUrl.Builder urlBuilder = HttpUrl.parse(FB_GRAPHAPI_ENDPOINT).newBuilder();
            urlBuilder.addPathSegment(QE_API_PATH);
            urlBuilder.addQueryParameter("access_token", HORIZON_FB_APP_TOKEN);
            urlBuilder.addQueryParameter("member", userId);
            for (int i = 0; i < qeList.size(); i++) {
                urlBuilder.addQueryParameter("quick_experiment_ids[" + i + "]", "testexpt:qe:" + qeList.get(i));
            }
            for (int i2 = 0; i2 < qeList.size(); i2++) {
                urlBuilder.addQueryParameter("quick_experiment_hashes[" + i2 + "]", "");
            }
            Request request = new Request.Builder().url(urlBuilder.build().toString()).build();
            Log.d(TAG, request.toString());
            client.newCall(request).enqueue(new Callback() {
                /* class com.oculus.modules.QuickExperimentsModuleImpl.AnonymousClass3 */

                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException e) {
                    Log.e(QuickExperimentsModuleImpl.TAG, "Failed to fetch QEs: " + e.toString());
                    HomeEventLogger.reportEvent("oculus_config", "status", "qe_fetch_failed", "error", "exception", "error_details", e.getMessage());
                    if (onFailure != null) {
                        onFailure.run();
                    }
                }

                /* JADX WARNING: Removed duplicated region for block: B:19:0x00b3  */
                /* JADX WARNING: Removed duplicated region for block: B:26:0x0155  */
                /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
                @Override // okhttp3.Callback
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onResponse(okhttp3.Call r19, okhttp3.Response r20) throws java.io.IOException {
                    /*
                    // Method dump skipped, instructions count: 356
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.QuickExperimentsModuleImpl.AnonymousClass3.onResponse(okhttp3.Call, okhttp3.Response):void");
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Failed to fetch and cache QEs: " + e.toString());
            HomeEventLogger.reportEvent("oculus_config", "status", "qe_fetch_failed", "error", "cache_error", "error_details", e.getMessage());
            if (onFailure != null) {
                onFailure.run();
            }
        }
    }

    public static void onLogout(Context context) {
        Log.d(TAG, "onLogout - clearing files");
        FileUtils.deleteFromInternalStorage(context, QE_CACHE_FILE);
        FileUtils.deleteFromInternalStorage(context, QE_TIMESTAMP_FILE);
    }
}
