package com.oculus.horizoncontent.horizon;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.DeviceConfigUtil;
import java.util.concurrent.atomic.AtomicBoolean;

public class HorizonContent {
    public static String AUTHORITY = null;
    @VisibleForTesting
    static final String PARTY_INFRA_GK_MC = "oculus_shared_social:is_socialplatform_parties_infra_enabled";
    private static String TAG = LoggingUtil.tag(HorizonContent.class);
    public static AtomicBoolean sWasPartyInfraGKFetched = new AtomicBoolean(false);

    public interface PartyInfraGKCallback {
        void call();
    }

    public static void build(String str) {
        AUTHORITY = str;
        FriendList.build(str);
    }

    public static void buildDeviceSynchronized(Application application) {
        build("com.oculus.horizon");
        runOncePartyInfraGKFetched(application.getApplicationContext(), null);
    }

    @VisibleForTesting
    public static void reset() {
        sWasPartyInfraGKFetched.set(false);
        build("com.oculus.horizon");
    }

    public static void runOncePartyInfraGKFetched(Context context, @Nullable final PartyInfraGKCallback partyInfraGKCallback) {
        if (!sWasPartyInfraGKFetched.get() || partyInfraGKCallback == null) {
            final Long valueOf = Long.valueOf(System.currentTimeMillis());
            DeviceConfigUtil.getDeviceBooleanAsync(context, PARTY_INFRA_GK_MC, new DeviceConfigUtil.GetDeviceBooleanCallback() {
                /* class com.oculus.horizoncontent.horizon.HorizonContent.AnonymousClass1 */

                @Override // com.oculus.horizoncontent.horizon.DeviceConfigUtil.GetDeviceBooleanCallback
                public void onValue(boolean z) {
                    if (z) {
                        Log.i(HorizonContent.TAG, "Routing to socialplatform content provider");
                        HorizonContent.build("com.oculus.socialplatform");
                    } else {
                        Log.i(HorizonContent.TAG, "Routing to horizon content provider");
                        HorizonContent.build("com.oculus.horizon");
                    }
                    Long valueOf = Long.valueOf(System.currentTimeMillis());
                    String str = HorizonContent.TAG;
                    Log.i(str, "took " + Long.toString(valueOf.longValue() - valueOf.longValue()) + " ms to getDeviceBoolean()");
                    HorizonContent.sWasPartyInfraGKFetched.set(true);
                    PartyInfraGKCallback partyInfraGKCallback = partyInfraGKCallback;
                    if (partyInfraGKCallback != null) {
                        partyInfraGKCallback.call();
                    }
                }
            });
            return;
        }
        partyInfraGKCallback.call();
    }

    public static class FriendList {
        public static final String ACTIVE_COLUMN = "is_current";
        public static final String ACTIVITY_APP_PACKAGE_NAME_COLUMN = "package_name";
        public static final String ACTIVITY_DEEPLINK_COLUMN = "deeplink";
        public static final String ACTIVITY_ID_PARAM = "activity_id";
        public static final String ACTIVITY_IMAGE_URI_COLUMN = "image_uri";
        public static final String ACTIVITY_SUBTITLE_COLUMN = "subtitle";
        public static final String ACTIVITY_TITLE_COLUMN = "title";
        public static final String AFTER_PARAM = "after";
        public static final String ALIAS_COLUMN = "alias";
        public static final String APPLICATION_DISPLAY_NAME_COLUMN = "application_display_name";
        public static final String APPLICATION_DISPLAY_SHORT_DESCRIPTION_COLUMN = "application_display_short_description";
        public static final String APPLICATION_ID_COLUMN = "application_id";
        public static final String APPLICATION_IS_VIEWER_ENTITLED_COLUMN = "application_is_viewer_entitled";
        public static final String APPLICATION_MAX_GROUP_LAUNCH_CAPACITY_COLUMN = "application_max_group_launch_capacity";
        public static final String APPLICATION_PACKAGE_NAME_COLUMN = "package_name";
        public static final String APPLICATION_PARTY_USER_ENTITLEMENT_COUNT_COLUMN = "application_party_user_entitlement_count";
        public static final String APP_IDS_PARAM = "application_ids";
        public static final String APP_ID_PARAM = "application_id";
        public static String AUTHORITY = null;
        public static final String AVATAR_IMAGE_URL = "avatar_image_url";
        public static final String BIOGRAPHY_STRING = "biography";
        public static final String BLOCKED_USER_ID = "blocked_user_id";
        public static final String BODY_PARAM = "body";
        public static final String CAN_VIEWER_MESSAGE_COLUMN = "can_viewer_message";
        public static final String CURRENT_PARTY_ID_COLUMN = "current_party_id";
        public static final String CURSOR_COLUMN = "cursor";
        public static final String DEEPLINK_TARGET_ID_PARAM = "deeplink_target_id";
        public static final String DESTINATION_DEEPLINK_MESSAGE_FOR_DEEPLINK_TARGET_COLUMN = "deeplink_message_for_deeplink_target";
        public static final String DESTINATION_DISPLAY_NAME = "destination_display_name";
        public static final String DESTINATION_ID = "destination_id";
        public static final String DESTINATION_ID_PARAM = "destination_id";
        public static final String DESTINATION_IMAGE = "destination_image";
        public static final String DESTINATION_IS_EXTERNAL_DEEPLINKABLE = "destination_is_external_deeplinkable";
        public static final String DESTINATION_MAX_CAPACITY = "destination_max_capacity";
        public static final String FB_FRIEND_BIO_COLUMN = "fb_friend_bio";
        public static final String FB_FRIEND_ID = "fb_friend_id";
        public static final String FB_FRIEND_IS_CURRENTLY_ACTIVE_COLUMN = "fb_friend_is_currently_active";
        public static final String FB_FRIEND_LAST_ACTIVE_TIME_COLUMN = "fb_friend_last_active_time";
        public static final String FB_FRIEND_NAME_COLUMN = "fb_friend_name";
        public static final String FB_FRIEND_OC_USER_CAN_BE_INVITED_TO_PARTY_COLUMN = "fb_friend_oc_user_party_can_be_invited_to_party";
        public static final String FB_FRIEND_OC_USER_FRIEND_STATUS_COLUMN = "fb_friend_oc_user_friend_status";
        public static final String FB_FRIEND_OC_USER_ID_COLUMN = "fb_friend_oc_user_id";
        public static final String FB_FRIEND_OC_USER_IS_CURRENT_COLUMN = "fb_friend_oc_user_is_current";
        public static final String FB_FRIEND_OC_USER_JOINABLE_PARTY_ID_COLUMN = "fb_friend_oc_user_joinable_party_id";
        public static final String FB_FRIEND_OC_USER_LAST_ACTIVE_TIME_COLUMN = "fb_friend_oc_user_last_active_time";
        public static final String FB_FRIEND_OC_USER_PRESENCE_COLUMN = "fb_friend_oc_user_presence";
        public static final String FB_FRIEND_OC_USER_PRESENCE_STATUS_COLUMN = "fb_friend_oc_user_presence_status";
        public static final String FB_FRIEND_PROFILE_PICTURE_URI_COLUMN = "fb_friend_profile_picture_uri";
        public static final String FB_LINKED_STATUS_COLUMN = "fb_linked_status";
        public static final String FIRST_PARAM = "first";
        public static final String FRIENDSHIP_STATUS_COLUMN = "friendship_status";
        public static Uri FRIEND_ACCEPT_REQUEST = null;
        public static Uri FRIEND_CANCEL_REQUEST = null;
        public static final String FRIEND_COUNT = "friend_count";
        public static Uri FRIEND_LIST_CONTENT_URI = null;
        public static Uri FRIEND_REJECT_REQUEST = null;
        public static Uri FRIEND_REMOVE = null;
        public static Uri FRIEND_REQUESTS_CONTENT_URI = null;
        public static final String FRIEND_REQUEST_SOURCE = "source";
        public static Uri FRIEND_SEND_REQUEST = null;
        public static final String FRIEND_VALUE = "friend";
        public static Uri GET_APPS_SCOREBOARDS_INFO_URI = null;
        public static Uri GET_BLOCKED_USER_ID = null;
        public static Uri GET_FB_FRIEND_PRIMARY_PROFILE = null;
        public static Uri GET_GROUP_LAUNCH_APPLICATION_DESTINATIONS_URI = null;
        public static Uri GET_GROUP_LAUNCH_SUPPORTED_APPLICATIONS_URI = null;
        public static Uri GET_LINKED_ACCOUNTS_INFO = null;
        public static Uri GET_PARTY_FBID = null;
        public static Uri GET_PARTY_HAS_PARTICIPANT_RECENTLY_SPOKEN = null;
        public static Uri GET_PARTY_INFO_WITH_ACTIVITY = null;
        public static Uri GET_PARTY_INVITES = null;
        public static Uri GET_PARTY_INVITE_INFO = null;
        public static Uri GET_PARTY_INVITE_INFO_WITH_NONCE = null;
        public static Uri GET_PARTY_PRIVACY_INFO = null;
        public static Uri GET_PROFILE_CONTENT = null;
        public static Uri GET_SOCIAL_ACTIVITY = null;
        public static Uri GET_SOCIAL_VIEWER = null;
        public static Uri GET_VR_PROFILE_CONTENT = null;
        public static final String GROUP_LAUNCH_ID_COLUMN = "group_launch_id";
        public static final String GROUP_LAUNCH_ID_PARAM = "group_launch_id";
        public static Uri GROUP_LAUNCH_LAUNCH_HANDLE = null;
        public static final String GROUP_LAUNCH_ONLY_QUEST_USERS_COLUMN = "group_launhc_only_quest_users";
        public static final String GROUP_LAUNCH_RESPONSE_PARAM = "response";
        public static Uri GROUP_LAUNCH_SET_STATE = null;
        public static Uri GROUP_LAUNCH_SET_USER_RESPONSE = null;
        public static Uri GROUP_LAUNCH_SOLO_LAUNCH_HANDLE = null;
        public static final String GROUP_LAUNCH_STATE_COLUMN = "group_launch_state";
        public static final String GROUP_LAUNCH_STATE_PARAM = "group_launch_state";
        public static final String GROUP_LAUNCH_USER_RESPONSE_COLUMN = "group_launch_user_response";
        public static final String HAS_NEXT_COLUMN = "has_next";
        public static final String HAS_SEEN_VR_INVITE_PROFILE_NUX_COLUMN = "has_seen_vr_invite_profile_nux";
        public static final String IGNORE_RICH_PRESENCE_PARAM = "ignore_rich_presence";
        public static final String IMAGE_URL_COLUMN = "image_url";
        public static final String INVITE_ACTIVITY_ID_VALUE = "invite_activity_id";
        public static final String INVITE_STATE_COLUMN = "invite_state";
        public static final String IS_IN_ROOM_COLUMN = "is_in_room";
        public static final String JOINABLE_PARTY_ID_COLUMN = "joinable_party_id";
        public static final String LAST_ACTIVE_COLUMN = "last_active_description";
        public static final String LAST_ACTIVE_TIME_COLUMN = "last_active_time";
        public static final String LAST_PRESENCE_COLUMN = "last_presence";
        public static final String LINK_NONCE_PARAM = "link_nonce";
        public static Uri MESSAGE_SEND_TO_THREAD = null;
        public static final String MESSAGE_THREAD_KEY_PARAM = "message_thread_key";
        public static final String MUTUAL_CONTEXT_STRING_COLUMN = "mutual_context_string";
        public static final String NUX_FLAG_PARAM = "nux_flag";
        public static final String NUX_VALUE_PARAM = "nux_value";
        public static final String ORDERBY_PARAM = "orderby";
        public static Uri PARTY_ACTIVATE_LINK_INVITE = null;
        public static final String PARTY_ACTIVITY_APP_ID_COLUMN = "party_activity_app_id";
        public static final String PARTY_ACTIVITY_DEEPLINK_COLUMN = "party_activity_deeplink";
        public static final String PARTY_ACTIVITY_ID_COLUMN = "party_activity_id";
        public static final String PARTY_ACTIVITY_IMAGE_URI_COLUMN = "party_activity_image_uri";
        public static final String PARTY_ACTIVITY_PACKAGE_NAME_COLUMN = "party_activity_package_name";
        public static final String PARTY_ACTIVITY_SUBTITLE_COLUMN = "party_activity_subtitle";
        public static final String PARTY_ACTIVITY_TITLE_COLUMN = "party_activity_title";
        public static final String PARTY_BLOCKED_INVITED_USERS_COLUMN = "party_blocked_invited_users";
        public static final String PARTY_BLOCKED_USERS_COLUMN = "party_blocked_current_users";
        public static Uri PARTY_CANCEL_INVITE = null;
        public static final String PARTY_CAN_INVITE_USER = "party_can_invite_user";
        public static Uri PARTY_CREATE = null;
        public static final String PARTY_CURRENT_USERS_COUNT_COLUMN = "party_users_count";
        public static Uri PARTY_DEACTIVATE_LINK_INVITE = null;
        public static final String PARTY_DIALOG_SOURCE = "source";
        public static final String PARTY_FBID_COLUMN = "party_fbid";
        public static Uri PARTY_GET_CURRENT = null;
        public static Uri PARTY_GET_CURRENT_V2 = null;
        public static Uri PARTY_GET_JOINABLE_PARTIES_URI = null;
        public static Uri PARTY_GET_MICROPHONE_INPUT_LOCATION = null;
        public static Uri PARTY_GET_SELF_MUTE = null;
        public static Uri PARTY_GET_VOLUME = null;
        public static final String PARTY_HAS_LINK_INVITE = "party_has_link_invite";
        public static final String PARTY_HAS_PARTICIPANT_RECENTLY_SPOKEN = "party_has_participant_recently_spoken";
        public static final String PARTY_ID_COLUMN = "party_id";
        public static final String PARTY_ID_PARAM = "party_id";
        public static final String PARTY_ID_VALUE = "party_id";
        public static final String PARTY_INVITED_USERS_COLUMN = "party_invited_users";
        public static final String PARTY_INVITE_SENDER_ID_COLUMN = "party_invite_sender_id";
        public static Uri PARTY_INVITE_USERS_CONTENT_URI = null;
        public static final String PARTY_IS_USER_MUTED_COLUMN = "party_is_user_muted";
        public static Uri PARTY_JOIN = null;
        public static final String PARTY_JOIN_POLICY_COLUMN = "join_policy";
        public static Uri PARTY_KICK = null;
        public static Uri PARTY_LEAVE = null;
        public static final String PARTY_MAX_SIZE = "party_max_size";
        public static final String PARTY_MAX_SIZE_COLUMN = "party_max_size";
        public static final String PARTY_MICROPHONE_INPUT_LOCATION = "party_microphone_input_location";
        public static final String PARTY_ONLY_QUEST_USERS_COLUMN = "party_only_quest_users";
        public static final String PARTY_PER_PERSON_MUTE_VALUE = "party_per_person_mute_value";
        public static Uri PARTY_PROPOSED_GROUP_LAUNCH_DESTINATION_CREATE = null;
        public static Uri PARTY_PROPOSED_GROUP_LAUNCH_DESTINATION_REMOVE = null;
        public static final String PARTY_SELF_MUTE_VALUE = "party_self_mute";
        public static Uri PARTY_SEND_MICROPHONE_INPUT_TO_APP = null;
        public static Uri PARTY_SEND_MICROPHONE_INPUT_TO_PARTY = null;
        public static Uri PARTY_SET_PER_PERSON_MUTE_STATUS = null;
        public static Uri PARTY_SET_SELF_MUTE = null;
        public static Uri PARTY_SET_TYPE = null;
        public static Uri PARTY_SET_VOLUME = null;
        public static final String PARTY_SOCIAL_ACTIVITY_APPLICATION_DISPLAY_NAME_COLUMN = "party_social_activity_application_display_name";
        public static final String PARTY_SOCIAL_ACTIVITY_APPLICATION_ICON_URL_COLUMN = "party_social_activity_icon_url";
        public static final String PARTY_SOCIAL_ACTIVITY_APPLICATION_ID_COLUMN = "party_social_activity_application_id";
        public static final String PARTY_SOCIAL_ACTIVITY_APPLICATION_MAX_CAPACITY_COLUMN = "party_social_activity_application_max_capacity";
        public static final String PARTY_SOCIAL_ACTIVITY_APPLICATION_PACKAGE_NAME_COLUMN = "party_social_activity_application_package_name";
        public static final String PARTY_SOCIAL_ACTIVITY_DEEPLINK_COLUMN = "party_social_activity_deeplink";
        public static final String PARTY_SOCIAL_ACTIVITY_DOES_SUPPORT_GROUP_LAUNCH = "party_social_activity_does_support_group_launch";
        public static final String PARTY_SOCIAL_ACTIVITY_ID_COLUMN = "party_social_activity_id";
        public static final String PARTY_SOCIAL_ACTIVITY_TITLE_COLUMN = "party_social_activity_title";
        public static final String PARTY_TYPE_COLUMN = "party_type";
        public static final String PARTY_TYPE_VALUE = "party_type";
        public static final String PARTY_URL = "party_url";
        public static final String PARTY_USERS_COLUMN = "party_current_users";
        public static final String PARTY_VOLUME_COLUMN = "party_volume";
        public static final String PARTY_VOLUME_VALUE = "party_volume";
        public static Uri PEOPLE_NEARBY_CONTENT_URI = null;
        public static final String PRESENCE_APP_PACKAGE_APP_ID_COLUMN = "presence_app_package_app_id";
        public static final String PRESENCE_APP_PACKAGE_NAME_COLUMN = "presence_app_package_name";
        public static final String PRESENCE_COLUMN = "presence";
        public static final String PRESENCE_DEEP_LINK_LAUNCH_PARAMS_COLUMN = "presence_deep_link_launch_params";
        public static final String PRESENCE_STATUS_COLUMN = "presence_status";
        public static final String PROFILE_IMAGE_URL = "profile_image_url";
        public static Uri PROFILE_SET_BIO = null;
        public static Uri PYMK_CONTENT_URI = null;
        public static Uri PYMK_HIDE_USER = null;
        public static final String ROLE_PARAM = "role";
        public static final String ROW_TYPE_COLUMN = "row_type";
        public static Uri SET_NUX_FLAG_FOR_USER = null;
        public static Uri SOCIAL_FRIENDS_URI = null;
        public static final String THREAD_ID_PARAM = "thread_id";
        public static Uri USER_BLOCK = null;
        public static final String USER_BLOCKED_BY_VIEWER_COLUMN = "blocked_by_viewer";
        public static final String USER_IDS_VALUE = "user_ids";
        public static final String USER_ID_COLUMN = "id";
        public static final String USER_ID_VALUE = "user_id";
        public static final String USER_NAME_COLUMN = "name";
        public static Uri USER_SEARCH = null;
        public static final String USER_SEARCH_ALIAS = "alias";
        public static final String USER_SEARCH_FRIEND_STATUS = "friend_status";
        public static final String USER_SEARCH_NAME = "name";
        public static final String USER_SEARCH_PROFILE_PICTURE_URL = "avatar";
        public static final String USER_SEARCH_STRING_PARAM = "query_string";
        public static final String USER_SEARCH_TYPE_PARAM = "search_mode";
        public static final String USER_TO_UNINVITE_VALUE = "user_to_uninvite";
        public static Uri USER_UNBLOCK = null;
        public static final String USER_VALUE = "user";
        public static final String VIEWER_CURRENT_PARTY_ID_COLUMN = "viewer_current_party_id";
        public static final String VIEWER_FB_ID_COLUMN = "viewer_fb_id";

        public static class PartyInviteRowType {
            public static final int BLOCKED_INVITED_USER = 8;
            public static final int BLOCKED_PARTY_USER = 7;
            public static final int CURRENT_PARTY = 9;
            public static final int GROUP_LAUNCH_APPLICATION = 5;
            public static final int GROUP_LAUNCH_DESTINATION = 6;
            public static final int INVITED_BY = 2;
            public static final int INVITED_USER = 3;
            public static final int PARTY = 0;
            public static final int PARTY_LEADER = 1;
            public static final int PARTY_USER = 4;
        }

        public enum UserInviteState {
            DEFAULT_UNKNOWN,
            INVITED,
            JOINED
        }

        public static void build(String str) {
            AUTHORITY = str + ".friendlist";
            String str2 = "content://" + AUTHORITY;
            PARTY_GET_JOINABLE_PARTIES_URI = Uri.parse(str2 + "/party_get_joinable");
            GET_GROUP_LAUNCH_SUPPORTED_APPLICATIONS_URI = Uri.parse(str2 + "/get_group_launch_supported_applications");
            GET_GROUP_LAUNCH_APPLICATION_DESTINATIONS_URI = Uri.parse(str2 + "/get_group_launch_application_destinations");
            GET_APPS_SCOREBOARDS_INFO_URI = Uri.parse(str2 + "/get_apps_scoreboards_info");
            PARTY_GET_CURRENT = Uri.parse(str2 + "/party_get_current");
            PARTY_GET_CURRENT_V2 = Uri.parse(str2 + "/party_get_current_v2");
            PEOPLE_NEARBY_CONTENT_URI = Uri.parse(str2 + "/people_nearby");
            PYMK_CONTENT_URI = Uri.parse(str2 + "/pymks");
            PYMK_HIDE_USER = Uri.parse(str2 + "/pymk_hide_user");
            MESSAGE_SEND_TO_THREAD = Uri.parse(str2 + "/message_send_to_thread");
            FRIEND_REQUESTS_CONTENT_URI = Uri.parse(str2 + "/friend_requests");
            SOCIAL_FRIENDS_URI = Uri.parse(str2 + "/get_friends");
            USER_SEARCH = Uri.parse(str2 + "/user_search");
            USER_BLOCK = Uri.parse(str2 + "/user_block");
            USER_UNBLOCK = Uri.parse(str2 + "/user_unblock");
            FRIEND_SEND_REQUEST = Uri.parse(str2 + "/friend_request_send");
            FRIEND_ACCEPT_REQUEST = Uri.parse(str2 + "/friend_request_accept");
            FRIEND_REJECT_REQUEST = Uri.parse(str2 + "/friend_reject_request");
            FRIEND_CANCEL_REQUEST = Uri.parse(str2 + "/friend_request_cancel");
            FRIEND_REMOVE = Uri.parse(str2 + "/friend_remove");
            FRIEND_LIST_CONTENT_URI = Uri.parse(str2 + "/party_invitable");
            GET_SOCIAL_ACTIVITY = Uri.parse(str2 + "/social_activity");
            PARTY_INVITE_USERS_CONTENT_URI = Uri.parse(str2 + "/party_invite_users");
            PARTY_CREATE = Uri.parse(str2 + "/party_create");
            PARTY_JOIN = Uri.parse(str2 + "/party_join");
            PARTY_KICK = Uri.parse(str2 + "/party_kick");
            PARTY_LEAVE = Uri.parse(str2 + "/party_leave");
            PARTY_CANCEL_INVITE = Uri.parse(str2 + "/party_cancel_invite");
            PARTY_SET_TYPE = Uri.parse(str2 + "/party_set_type");
            PARTY_ACTIVATE_LINK_INVITE = Uri.parse(str2 + "/party_activate_link_invite");
            PARTY_DEACTIVATE_LINK_INVITE = Uri.parse(str2 + "/party_deactivate_link_invite");
            PARTY_SET_VOLUME = Uri.parse(str2 + "/party_set_volume");
            PARTY_GET_VOLUME = Uri.parse(str2 + "/party_get_volume");
            PARTY_SET_SELF_MUTE = Uri.parse(str2 + "/party_set_self_mute");
            PARTY_GET_SELF_MUTE = Uri.parse(str2 + "/party_get_self_mute");
            PARTY_SET_PER_PERSON_MUTE_STATUS = Uri.parse(str2 + "/party_set_per_person_mute_status");
            PARTY_SEND_MICROPHONE_INPUT_TO_APP = Uri.parse(str2 + "/party_send_microphone_input_to_app");
            PARTY_SEND_MICROPHONE_INPUT_TO_PARTY = Uri.parse(str2 + "/party_send_microphone_input_to_party");
            PARTY_GET_MICROPHONE_INPUT_LOCATION = Uri.parse(str2 + "/party_get_microphone_input_location");
            GET_PARTY_INFO_WITH_ACTIVITY = Uri.parse(str2 + "/party_get_info_with_activity");
            GET_PARTY_INVITES = Uri.parse(str2 + "/get_party_invites");
            GET_PARTY_INVITE_INFO = Uri.parse(str2 + "/get_party_invite_info");
            GET_PARTY_PRIVACY_INFO = Uri.parse(str2 + "/get_party_privacy_info");
            GET_PARTY_INVITE_INFO_WITH_NONCE = Uri.parse(str2 + "/get_party_invite_info_with_nonce");
            GET_LINKED_ACCOUNTS_INFO = Uri.parse(str2 + "/get_linked_accounts_info");
            GET_PARTY_FBID = Uri.parse(str2 + "/get_party_fbid");
            GET_PARTY_HAS_PARTICIPANT_RECENTLY_SPOKEN = Uri.parse(str2 + "/get_party_has_participant_recently_spoken");
            PARTY_PROPOSED_GROUP_LAUNCH_DESTINATION_CREATE = Uri.parse(str2 + "/party_proposed_group_launch_destination_create");
            PARTY_PROPOSED_GROUP_LAUNCH_DESTINATION_REMOVE = Uri.parse(str2 + "/party_proposed_group_launch_destination_remove");
            GROUP_LAUNCH_SET_USER_RESPONSE = Uri.parse(str2 + "/group_launch_user_response");
            GET_PROFILE_CONTENT = Uri.parse(str2 + "/get_profile_content");
            GROUP_LAUNCH_LAUNCH_HANDLE = Uri.parse(str2 + "/group_launch_launch_handle");
            GROUP_LAUNCH_SOLO_LAUNCH_HANDLE = Uri.parse(str2 + "/group_launch_solo_launch_handle");
            PROFILE_SET_BIO = Uri.parse(str2 + "/profile_set_bio");
            GROUP_LAUNCH_SET_STATE = Uri.parse(str2 + "/group_launch_set_state");
            GET_SOCIAL_VIEWER = Uri.parse(str2 + "/get_social_viewer");
            GET_VR_PROFILE_CONTENT = Uri.parse(str2 + "/get_vr_profile_content");
            GET_FB_FRIEND_PRIMARY_PROFILE = Uri.parse(str2 + "/get_fb_friend_primary_profile");
            GET_BLOCKED_USER_ID = Uri.parse(str2 + "/get_blocked_id");
            SET_NUX_FLAG_FOR_USER = Uri.parse(str2 + "/set_nux_flag_for_user");
        }
    }

    public static class PlatformPluginManager {

        public static class MicrophoneMuteState {
            public static final int MUTED = 1;
            public static final int UNKNOWN = 0;
            public static final int UNMUTED = 2;

            public static int validate(int i) {
                if (i == 0 || i == 1 || i == 2) {
                    return i;
                }
                throw new IllegalArgumentException("Unrecognized mute state");
            }

            /* JADX WARNING: Removed duplicated region for block: B:17:0x0038  */
            /* JADX WARNING: Removed duplicated region for block: B:23:0x0046 A[RETURN] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public static int valueOf(java.lang.String r5) throws java.lang.IllegalArgumentException {
                /*
                    int r0 = r5.hashCode()
                    r1 = 73726283(0x464f94b, float:2.6915741E-36)
                    r2 = 0
                    r3 = 2
                    r4 = 1
                    if (r0 == r1) goto L_0x002b
                    r1 = 433141802(0x19d1382a, float:2.1632778E-23)
                    if (r0 == r1) goto L_0x0021
                    r1 = 435201618(0x19f0a652, float:2.4882595E-23)
                    if (r0 == r1) goto L_0x0017
                    goto L_0x0035
                L_0x0017:
                    java.lang.String r0 = "UNMUTED"
                    boolean r5 = r5.equals(r0)
                    if (r5 == 0) goto L_0x0035
                    r5 = r3
                    goto L_0x0036
                L_0x0021:
                    java.lang.String r0 = "UNKNOWN"
                    boolean r5 = r5.equals(r0)
                    if (r5 == 0) goto L_0x0035
                    r5 = r2
                    goto L_0x0036
                L_0x002b:
                    java.lang.String r0 = "MUTED"
                    boolean r5 = r5.equals(r0)
                    if (r5 == 0) goto L_0x0035
                    r5 = r4
                    goto L_0x0036
                L_0x0035:
                    r5 = -1
                L_0x0036:
                    if (r5 == 0) goto L_0x0046
                    if (r5 == r4) goto L_0x0045
                    if (r5 != r3) goto L_0x003d
                    return r3
                L_0x003d:
                    java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
                    java.lang.String r0 = "Unrecognized mute state"
                    r5.<init>(r0)
                    throw r5
                L_0x0045:
                    return r4
                L_0x0046:
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizoncontent.horizon.HorizonContent.PlatformPluginManager.MicrophoneMuteState.valueOf(java.lang.String):int");
            }
        }
    }
}
