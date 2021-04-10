package com.oculus.vrshell.home.module;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import com.adobe.xmp.XMPConst;
import com.google.common.collect.ImmutableList;
import com.oculus.panellib.ThreadExecutor;
import com.oculus.panellib.modules.RCTBaseJavaModule;
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

public class GateKeepers extends RCTBaseJavaModule {
    public static final String CONFIG_ERROR = "error";
    public static final String CONFIG_ERROR_DETAILS = "error_details";
    public static final String CONFIG_EVENT_NAME = "oculus_config";
    public static final String CONFIG_STATUS = "status";
    private static final String GK_API_PATH = "features";
    private static final String GK_CACHE_FILE = "VrShellHomeGKCacheFile";
    private static final String GK_EXTRA_FILE = "VrShellHomeGKExtraFile";
    private static final String GK_OVERRIDES_FILE = "VrShellHomeGKOverridesFile";
    private static String MODULE_NAME = GateKeepers.class.getSimpleName();
    private static final String OCULUS_GRAPHAPI_ENDPOINT = "https://graph.oculus.com";
    private static final String TAG = MODULE_NAME;
    public static final ImmutableList<String> gkList = new ImmutableList.Builder().add((Object) "fb_linking_confirmation").add((Object) "oc_challenges_growth_experimentation").add((Object) "oc_challenges_panel_gate").add((Object) "oc_challenges_share_button").add((Object) "oc_experiences_vr_events_enabled").add((Object) "oc_notifs_nuc_xr_device").add((Object) "oc_referral_system_master_gk").add((Object) "oc_scoreboard_challenges").add((Object) "oc_sharing_image_editor").add((Object) "oc_sharing_video_editor").add((Object) "oc_social_h1_2020_mobile_sharing_updates").add((Object) "oculus_abuse_recording_overlay_software_button").add((Object) "oculus_abuse_reporters_enqueue_in_test_srt_queue").add((Object) "oculus_activation_reserved_clubhouse").add((Object) "oculus_avatar_can_see_sdk2_editor_employee").add((Object) "oculus_avatar_use_update_ipc").add((Object) "oculus_avatar_v2_upsell").add((Object) "oculus_block_after_report").add((Object) "oculus_can_see_vr_qps").add((Object) "oculus_configurable_mtp_dialog").add((Object) "oculus_dash_avatar_editor").add((Object) "oculus_dash_lock_dash").add((Object) "oculus_dash_no_worlds_store").add((Object) "oculus_dash_window_focus").add((Object) "oculus_developer").add((Object) "oculus_enable_non_vr_apps_launch_v2").add((Object) "oculus_enable_send_to_self_messenger_button").add((Object) "oculus_event_creation_invr_dash").add((Object) "oculus_event_creation_invr").add((Object) "oculus_explore_invite_parties_in_friend_widget").add((Object) "oculus_explore_load_center_pane_before_sides").add((Object) "oculus_explore_widgets_bloks").add((Object) "oculus_explore_subfeed_badging").add((Object) "oculus_growth_quest_oga_killswitch").add((Object) "oculus_growth_quest_oga_pointer_killswitch").add((Object) "oculus_home_oculus_employees").add((Object) "oculus_home_qp_exposure_logging").add((Object) "oculus_home_qp_graphql_action_logging_killswitch").add((Object) "oculus_home_qp_graphql_imp_logging_killswitch").add((Object) "oculus_home_qp_graphql_logging_killswitch").add((Object) "oculus_home_saved_media_save_button").add((Object) "oculus_home_social_create_event_octextinput").add((Object) "oculus_home_validation_for_mobile_config_gk").add((Object) "oculus_home2_wishlists").add((Object) "oculus_is_trusted_user").add((Object) "oc_journey_vignettes_dogfooding").add((Object) "oculus_livestream_to_groups").add((Object) "oculus_livestream_to_pages").add((Object) "oculus_living_room_tooltip").add((Object) "oculus_messenger_attachments").add((Object) "oculus_messenger_block_action").add((Object) "oculus_messenger_clickable_message_links").add((Object) "oculus_messenger_deeplinking").add((Object) "oculus_messenger_quick_actions").add((Object) "oculus_messenger_social_panel_mobile").add((Object) "oculus_messenger_strangers").add((Object) "oculus_messenger_voip_in_threads").add((Object) "oculus_mobile_fb_gaming_tag_for_livestream").add((Object) "oculus_mobile_fb_gaming_tag_for_photo").add((Object) "oculus_mobile_instant_replay").add((Object) "oculus_mobile_save_to_fb_gaming_profile").add((Object) "oculus_mobile_sysux_bug_report_share_sheet_entry").add((Object) "oculus_ocui_font_scaling").add((Object) "oculus_parties_2d_join").add((Object) "oculus_party_can_see_direct_join_buttons").add((Object) "oculus_party_cancel_invites").add((Object) "oculus_party_joinable_calls").add((Object) "oculus_party_joinable_social_feed").add((Object) "oculus_party_new_parties_panel_app").add((Object) "oculus_party_public_parties_master").add((Object) "oculus_party_show_os_upgrade_warning_toast").add((Object) "oculus_party_voip_use_verts_impl").add((Object) "oculus_pc_developer_settings").add((Object) "oculus_pdp_app_support_website").add((Object) "oculus_pe_new_user_hero").add((Object) "oculus_photosharing_enable_new_privacy_content").add((Object) "oculus_presence_invite_master").add((Object) "oculus_pymk_chat_targeting").add((Object) "oculus_pymk_v0_targeting").add((Object) "oculus_quest_infinite_office_platform").add((Object) "oculus_quest_peopletab_pymk").add((Object) "oculus_quest_profile_revamp").add((Object) "oculus_report_auto_video_capture").add((Object) "oculus_search_typeahead").add((Object) "oculus_search_x2_contextual_search").add((Object) "oculus_share_media_auto_sync").add((Object) "oculus_share_media_to_fb_stories").add((Object) "oculus_share_photo_to_groups").add((Object) "oculus_skyline_abuse_prevention").add((Object) "oculus_skyline_app_privacy").add((Object) "oculus_skyline_chainmail").add((Object) "oculus_skyline_disable_sensor_warnings").add((Object) "oculus_skyline_event_user_list").add((Object) "oculus_social_bundle_fb_link_gate").add((Object) "oculus_social_bundle_server_templates").add((Object) "oculus_social_feed").add((Object) "oculus_social_hub_enabled_in_vr").add((Object) "oculus_social_panel_new_query_renderer").add((Object) "oculus_store_fb_linking_vr_gk").add((Object) "oculus_store_promo_codes_rollout").add((Object) "oculus_store_promotion_engine_hero_unit").add((Object) "oculus_store_rift_on_quest").add((Object) "oculus_store_subscriptions_client_gating").add((Object) "oculus_support_general_destinations_start_party").add((Object) "oculus_universal_share_sheet_messenger_killswitch").add((Object) "oculus_store_search_button_in_chrome").add((Object) "oculus_store_subscriptions_purchase_flow").add((Object) "oculus_store_vr_einvoice_flow").add((Object) "oculus_video_allow_screen_capture").add((Object) "oculus_vrshell_anytimeui_v2_profile_revamp").add((Object) "oculus_vrshell_home_quick_promotion_v0").add((Object) "oculus_vrshell_tv_quick_promotion_v0").add((Object) "oculus_living_room_tooltip").add((Object) "oculus_tv_developers").add((Object) "oculus_universal_share_sheet_events_enabled").add((Object) "oculus_universal_share_sheet_messenger_killswitch").add((Object) "oculus_uss_enable_recent_destinations").add((Object) "oculus_video_allow_screen_capture").add((Object) "oculus_vr_event_user_list").add((Object) "oculus_vr_events_enable_guest_invite_events").add((Object) "oculus_vr_events_parties_entrypoint").add((Object) "oculus_vr_fb_link").add((Object) "oculus_vr_group_launch_from_parties").add((Object) "oculus_vrshell_anytimeui_report_via_social_dialog").add((Object) "oculus_vrshell_home_quick_promotion_v0").add((Object) "oculus_vrshell_tv_quick_promotion_v0").add((Object) "oculus_worlds_recommended_places").add((Object) "oculus_store_account_tab_enabled").add((Object) "oculus_editor_toggle").add((Object) "oculus_store_vr_app_gifting").add((Object) "oculus_avatar_editor_undo_redo").add((Object) "oculus_avatar_editor_show_choice_tooltip").add((Object) "oculus_subscription_disclosure_korea").add((Object) "oculus_subscription_disclosure_japan").build();
    @Nullable
    private static volatile String mCachedGKsJSON = null;
    private static final List<Integer> mInitialFetchCallbacks = new ArrayList();
    private static final ReentrantLock mInitialFetchLock = new ReentrantLock(true);
    @Nullable
    private static GateKeepers mInstance = null;
    private Context mContext = null;

    public GateKeepers(Context context) {
        mInstance = this;
        this.mContext = context;
    }

    public void shutdownModule() {
        mInstance = null;
    }

    public String getModuleName() {
        return MODULE_NAME;
    }

    public static void initialFetchAsync(final Context context) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.vrshell.home.module.GateKeepers.AnonymousClass1 */

            /* JADX INFO: finally extract failed */
            @Override // java.util.concurrent.Callable
            public Void call() {
                String cache = "null";
                String overrides = "null";
                String extra = "{\"timestamp\":0,\"id\":null}";
                try {
                    String tempCache = FileUtils.readFromInternalStorage(context, GateKeepers.GK_CACHE_FILE);
                    if (!tempCache.isEmpty()) {
                        cache = tempCache;
                    }
                    String tempOverrides = FileUtils.readFromInternalStorage(context, GateKeepers.GK_OVERRIDES_FILE);
                    if (!tempOverrides.isEmpty()) {
                        overrides = tempOverrides;
                    }
                    String tempExtra = FileUtils.readFromInternalStorage(context, GateKeepers.GK_EXTRA_FILE);
                    if (!tempExtra.isEmpty()) {
                        extra = tempExtra;
                    }
                } catch (Exception e) {
                    HomeEventLogger.reportEvent("oculus_config", "status", "gk_local_read_failed", "error", "exception", "error_details", e.getMessage());
                }
                String value = String.format("{\"cache\":%s,\"overrides\":%s,\"extra\":%s}", cache, overrides, extra);
                GateKeepers.mInitialFetchLock.lock();
                try {
                    String unused = GateKeepers.mCachedGKsJSON = value;
                    if (GateKeepers.mInstance != null) {
                        for (Integer callbackID : GateKeepers.mInitialFetchCallbacks) {
                            GateKeepers.mInstance.internalFetchGKsCallback(callbackID.intValue());
                        }
                    }
                    GateKeepers.mInitialFetchLock.unlock();
                    return null;
                } catch (Throwable th) {
                    GateKeepers.mInitialFetchLock.unlock();
                    throw th;
                }
            }
        }, "GateKeepers::initialFetchAsync");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void internalFetchGKsCallback(int callbackID) {
        nativeInvokeCallback(this.RVRCtxTag, callbackID, "[" + mCachedGKsJSON + "]");
    }

    public static List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("fetchCachedGKs", "+ii"));
        list.add(new Pair<>("removeOverride", "+sii"));
        list.add(new Pair<>("override", "+sbii"));
        list.add(new Pair<>("remoteFetchGKs", "ss"));
        return list;
    }

    public void fetchCachedGKs(int resolveID, int rejectID) {
        if (mCachedGKsJSON != null) {
            internalFetchGKsCallback(resolveID);
            return;
        }
        mInitialFetchLock.lock();
        try {
            if (mCachedGKsJSON != null) {
                internalFetchGKsCallback(resolveID);
            } else {
                mInitialFetchCallbacks.add(Integer.valueOf(resolveID));
            }
        } finally {
            mInitialFetchLock.unlock();
        }
    }

    public void removeOverride(String gkName, int resolveID, int rejectID) {
        String overrides = FileUtils.readFromInternalStorage(this.mContext, GK_OVERRIDES_FILE);
        if (!overrides.isEmpty()) {
            try {
                JSONObject overrideJson = new JSONObject(overrides);
                overrideJson.remove(gkName);
                FileUtils.writeToInternalStorage(this.mContext, GK_OVERRIDES_FILE, overrideJson.toString());
            } catch (JSONException e) {
                Log.e(TAG, "removeOverride(): Json error", e);
            }
        }
        nativeInvokeCallback(this.RVRCtxTag, resolveID, XMPConst.ARRAY_ITEM_NAME);
    }

    public void override(String gkName, boolean value, int resolveID, int rejectID) {
        JSONObject overrideJson;
        String overrides = FileUtils.readFromInternalStorage(this.mContext, GK_OVERRIDES_FILE);
        if (!overrides.isEmpty()) {
            try {
                overrideJson = new JSONObject(overrides);
            } catch (JSONException e) {
                Log.e(TAG, "override(): Json error", e);
                return;
            }
        } else {
            overrideJson = new JSONObject();
        }
        try {
            overrideJson.put(gkName, value);
        } catch (JSONException e2) {
            Log.e(TAG, "override(): Json error", e2);
        }
        FileUtils.writeToInternalStorage(this.mContext, GK_OVERRIDES_FILE, overrideJson.toString());
        nativeInvokeCallback(this.RVRCtxTag, resolveID, XMPConst.ARRAY_ITEM_NAME);
    }

    public void remoteFetchGKs(final String accessToken, final String userId) {
        ThreadExecutor.getInstance().execute(new Callable<Void>() {
            /* class com.oculus.vrshell.home.module.GateKeepers.AnonymousClass2 */

            /* JADX WARNING: Code restructure failed: missing block: B:21:0x0058, code lost:
                r2 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:22:0x0059, code lost:
                r2 = r0;
                r0 = r2;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:37:0x0078, code lost:
                r2 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:38:0x0079, code lost:
                r7 = r0;
                r0 = r2;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:47:0x008c, code lost:
                r0 = th;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:48:0x008e, code lost:
                r0 = th;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:49:0x008f, code lost:
                r2 = null;
             */
            @Override // java.util.concurrent.Callable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Void call() {
                /*
                // Method dump skipped, instructions count: 145
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.home.module.GateKeepers.AnonymousClass2.call():java.lang.Void");
            }
        });
    }

    public static void fetchAndCacheGKs(final Context context, OkHttpClient client, String accessToken, final String userId, final Runnable onComplete, final Runnable onFailure) {
        try {
            HomeEventLogger.reportEvent("oculus_config", "status", "gk_fetch_started");
            HttpUrl.Builder urlBuilder = HttpUrl.parse(OCULUS_GRAPHAPI_ENDPOINT).newBuilder();
            urlBuilder.addPathSegment(GK_API_PATH);
            for (int i = 0; i < gkList.size(); i++) {
                urlBuilder.addQueryParameter("projects[" + i + "]", gkList.get(i));
            }
            urlBuilder.addQueryParameter("access_token", accessToken);
            Request request = new Request.Builder().url(urlBuilder.build().toString()).build();
            Log.d(TAG, request.toString());
            client.newCall(request).enqueue(new Callback() {
                /* class com.oculus.vrshell.home.module.GateKeepers.AnonymousClass3 */

                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException e) {
                    Log.e(GateKeepers.TAG, "Failed to fetch GKs", e);
                    HomeEventLogger.reportEvent("oculus_config", "status", "gk_fetch_failed", "error", "exception", "error_details", e.getMessage());
                    if (onFailure != null) {
                        onFailure.run();
                    }
                }

                /* JADX WARNING: Removed duplicated region for block: B:22:0x00e7  */
                /* JADX WARNING: Removed duplicated region for block: B:29:0x016b  */
                /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
                @Override // okhttp3.Callback
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onResponse(okhttp3.Call r20, okhttp3.Response r21) throws java.io.IOException {
                    /*
                    // Method dump skipped, instructions count: 375
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.home.module.GateKeepers.AnonymousClass3.onResponse(okhttp3.Call, okhttp3.Response):void");
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Failed to fetch and cache GKs", e);
            HomeEventLogger.reportEvent("oculus_config", "status", "gk_fetch_failed", "error", "cache_error", "error_details", e.getMessage());
            if (onFailure != null) {
                onFailure.run();
            }
        }
    }

    public static void onLogout(Context context) {
        Log.d(TAG, "onLogout - clearing files");
        FileUtils.deleteFromInternalStorage(context, GK_CACHE_FILE);
    }
}
