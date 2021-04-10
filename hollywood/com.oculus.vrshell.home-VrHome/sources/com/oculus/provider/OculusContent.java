package com.oculus.provider;

import android.net.Uri;
import android.text.TextUtils;
import com.facebook.secure.uriparser.SecureUriParser;
import com.oculus.common.build.BuildConstants;

public class OculusContent {
    public static final String AUTHORITY = BuildConstants.PACKAGE_NAME_HORIZON;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static class Gatekeeper {
        public static final String AUTHORITY = (OculusContent.AUTHORITY + ".gatekeeper");
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/fetch");
        public static final String IS_ENABLED = "gk_enabled";
        public static final String NAME_PARAM = "name";
    }

    public static class Paging {
        public static final String AFTER = "after";
        public static final String CURSOR = "cursor";
        public static final String FIRST = "first";
        public static final String HAS_NEXT = "has_next";
    }

    public static class PartyCalls {
        public static final String AUTHORITY = (OculusContent.AUTHORITY + ".livestreaming");
        public static final String CALLER_ID_COLUMN = "caller_id";
        public static final String CALLER_NAME_COLUMN = "caller_name";
        public static final String CALLER_PHOTO_COLUMN = "caller_photo";
        public static final String CALL_ID_COLUMN = "call_id";
        public static final String CURRENT_PARTY_ID_COLUMN = "current_party_id";
        public static final Uri INCOMING_CALLS_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/incoming_calls");
    }

    public static class PartyVideoStreams {
        public static final String AUTHORITY = (OculusContent.AUTHORITY + ".livestreaming");
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/party_video_streams");
        public static final String USER_ID_COLUMN = "user_id";
    }

    public static class Platform {
        public static final String AUTHORITY = (OculusContent.AUTHORITY + ".platformsdk");
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/query");
        public static final String MIC_PERMISSION_REQUEST_NAME = "microphone_permission";
        public static final String MIC_PERMISSION_RESPONSE = "microphone_status";
        public static final String PERMISSION_STATUS_RESPONSE = "permission_status";
        public static final String REQUEST_NAME_PARAM = "request";
        public static final String WRITE_STORAGE_PERMISSION_REQUEST_NAME = "write_storage_permission";
        public static final String WRITE_STORAGE_PERMISSION_RESPONSE = "write_storage_status";
    }

    public static class Profile {
        public static final Uri CONTENT_URI = Uri.parse("content://" + OculusContent.AUTHORITY + "/profile");
        public static final String DEFAULT_BROWSER = "default_browser";
        public static final String EMAIL = "email";
        public static final String FACEBOOK_EMAIL = "facebook_email";
        public static final String FACEBOOK_USER_ID = "facebook_user_id";
        public static final String HAS_DOCKED = "has_docked";
        public static final String HAS_FINISHED_FULL_VR_NUX = "has_finished_full_vr_nux";
        public static final String HAS_FINISHED_IPD_ADJUST = "has_finished_ipd_adjust";
        public static final String HAS_FINISHED_MONTEREY_NUX = "has_finished_monterey_nux";
        public static final String HAS_FINISHED_NUX = "has_finished_nux";
        public static final String HAS_OPTED_OUT_HSW = "has_opted_out_hsw";
        public static final String HAS_OPTED_OUT_OF_MALIBU_RECENTER = "has_opted_out_of_malibu_recenter";
        public static final String HAS_SEEN_CONFIRM_QUIT = "has_seen_confirm_quit";
        public static final String HAS_SEEN_CONFIRM_QUIT_NOTIF = "has_seen_confirm_quit_notif";
        public static final String HAS_SEEN_FOCUS = "has_seen_focus";
        public static final String HAS_SEEN_HAND_TRACKING_NUX = "has_seen_hand_tracking_nux";
        public static final String HAS_SEEN_INTERNET_BROWSER_PROMPT = "has_seen_internet_browser_prompt";
        public static final String HAS_SEEN_LONG_HSW = "has_seen_long_hsw";
        public static final String HAS_SEEN_NUX = "has_seen_nux";
        public static final String HAS_SEEN_PARTY_CALLS_NUX = "has_seen_party_calls_nux";
        public static final String HAS_SEEN_SAVED_PROMPT = "has_seen_saved_prompt";
        public static final String HAS_SEEN_TUTORIAL_PROMPT = "has_seen_tutorial_prompt";
        public static final String HIGH_PRIORITY_APPS_DOWNLOAD_STATUS = "high_priority_apps_download_status";
        public static final String HIGH_PRIORITY_APPS_DOWNLOAD_WAITTIME = "high_priority_apps_download_waittime";
        public static final String HIGH_PRIORITY_APP_DOWNLOAD_PACKAGES = "high_priority_apps_pkgs";
        public static final String ID = "id";
        public static final String IMAGE_URL = "image_url";
        public static final String IS_AUTO_UPDATES_ENABLED = "auto_updates_enabled";
        public static final String IS_LOGGED_IN = "is_logged_in";
        public static final String LOCALE = "locale";
        public static final String NUX_RESULT = "nux_result";
        public static final String NUX_SEEN_COUNT = "nux_seen_count";
        public static final String NUX_STATE = "nux_state";
        public static final String ROLLOUT_TOKEN = "rollout_token";
        public static final String USERNAME = "username";

        public static Uri getUriFromNuxPref(String NuxPref) {
            if (!TextUtils.isEmpty(NuxPref)) {
                return new Uri.Builder().scheme("content").authority(OculusContent.AUTHORITY).path("profile").appendPath(NuxPref).build();
            }
            throw new IllegalArgumentException("NUX Preference cannot be null/empty");
        }
    }

    public static class FriendList {
        public static final String ACTIVE_COLUMN = "is_current";
        public static final String ACTIVITY_APP_PACKAGE_NAME_COLUMN = "package_name";
        public static final String ACTIVITY_DEEPLINK_COLUMN = "deeplink";
        public static final String ACTIVITY_ID_PARAM = "activity_id";
        public static final String ACTIVITY_IMAGE_URI_COLUMN = "image_uri";
        public static final String ACTIVITY_SUBTITLE_COLUMN = "subtitle";
        public static final String ACTIVITY_TITLE_COLUMN = "title";
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
        public static final String CAN_VIEWER_MESSAGE_COLUMN = "can_viewer_message";
        public static final String CURRENT_PARTY_COLUMN = "current_party";
        public static final String CURRENT_PARTY_ID_COLUMN = "current_party_id";
        public static final String DEEP_LINK_TARGET_ID_VALUE = "deeplink_target_id";
        public static final String DESTINATION_DEEPLINK_MESSAGE_FOR_DEEPLINK_TARGET_COLUMN = "deeplink_message_for_deeplink_target";
        public static final String DESTINATION_DESCRIPTION = "destination_description";
        public static final String DESTINATION_DISPLAY_NAME = "destination_display_name";
        public static final String DESTINATION_ID = "destination_id";
        public static final String DESTINATION_ID_PARAM = "destination_id";
        public static final String DESTINATION_IMAGE = "destination_image";
        public static final String DESTINATION_IS_EXTERNAL_DEEPLINKABLE = "destination_is_external_deeplinkable";
        public static final String DESTINATION_MAX_CAPACITY = "destination_max_capacity";
        public static final String FB_FRIEND_BIO = "fb_friend_bio";
        public static final String FB_FRIEND_ID_PARAM = "fb_friend_id";
        public static final String FB_FRIEND_IS_CURRENTLY_ACTIVE = "fb_friend_is_currently_active";
        public static final String FB_FRIEND_LAST_ACTIVE_TIME = "fb_friend_last_active_time";
        public static final String FB_FRIEND_NAME = "fb_friend_name";
        public static final String FB_FRIEND_OC_USER_CAN_BE_INVITED_TO_PARTY = "fb_friend_oc_user_party_can_be_invited_to_party";
        public static final String FB_FRIEND_OC_USER_FRIEND_STATUS = "fb_friend_oc_user_friend_status";
        public static final String FB_FRIEND_OC_USER_ID = "fb_friend_oc_user_id";
        public static final String FB_FRIEND_OC_USER_IS_CURRENT = "fb_friend_oc_user_is_current";
        public static final String FB_FRIEND_OC_USER_JOINABLE_PARTY_ID = "fb_friend_oc_user_joinable_party_id";
        public static final String FB_FRIEND_OC_USER_LAST_ACTIVE_TIME = "fb_friend_oc_user_last_active_time";
        public static final String FB_FRIEND_OC_USER_PRESENCE = "fb_friend_oc_user_presence";
        public static final String FB_FRIEND_OC_USER_PRESENCE_STATUS = "fb_friend_oc_user_presence_status";
        public static final String FB_FRIEND_PROFILE_PICTURE_URI = "fb_friend_profile_picture_uri";
        public static final String FB_LINKED_STATUS_COLUMN = "fb_linked_status";
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
        public static Uri GET_APP_SCOREBOARDS_INFO_URI = null;
        public static Uri GET_BLOCKED_USER_ID = null;
        public static Uri GET_FB_FRIEND_PRIMARY_PROFILE = null;
        public static Uri GET_FRIENDS_URI = null;
        public static Uri GET_GROUP_LAUNCH_APPLICATION_DESTINATIONS_URI = null;
        public static Uri GET_GROUP_LAUNCH_SUPPORTED_APPLICATIONS_URI = null;
        public static Uri GET_LINKED_ACCOUNTS_INFO = null;
        public static Uri GET_PARTY_FBID = null;
        public static Uri GET_PARTY_HAS_PARTICIPANT_RECENTLY_SPOKEN = null;
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
        public static final String LINK_NONCE_VALUE = "link_nonce";
        public static final String MESSAGE_BODY_VALUE = "body";
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
        public static final String PARTY_ACTIVITY_PREFIX = "party_activity_";
        public static final String PARTY_ACTIVITY_SUBTITLE_COLUMN = "party_activity_subtitle";
        public static final String PARTY_ACTIVITY_TITLE_COLUMN = "party_activity_title";
        public static final String PARTY_BLOCKED_INVITED_USERS_COLUMN = "party_blocked_invited_users";
        public static final String PARTY_BLOCKED_USERS_COLUMN = "party_blocked_current_users";
        public static final String PARTY_BLOCKED_USERS_COUNT_COLUMN = "party_has_blocked_users_count_column";
        public static Uri PARTY_CANCEL_INVITE = null;
        public static final String PARTY_CAN_INVITE_USER = "party_can_invite_user";
        public static Uri PARTY_CREATE = null;
        public static final String PARTY_CURRENT_USERS_COLUMN = "party_current_users";
        public static Uri PARTY_DEACTIVATE_LINK_INVITE = null;
        public static final String PARTY_FBID_COLUMN = "party_fbid";
        public static Uri PARTY_GET_CURRENT_URI = null;
        public static Uri PARTY_GET_CURRENT_URI_V2 = null;
        public static Uri PARTY_GET_INFO_WITH_ACTIVITY = null;
        public static Uri PARTY_GET_INFO_WITH_MEMBERS = null;
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
        public static final String PARTY_JOIN_ERROR_COLUMN = "party_join_error";
        public static final String PARTY_JOIN_POLICY = "join_policy";
        public static Uri PARTY_JOIN_WITH_ERROR_CALLBACK = null;
        public static Uri PARTY_KICK = null;
        public static Uri PARTY_LEAVE = null;
        public static final String PARTY_MAX_SIZE = "party_max_size";
        public static final String PARTY_MICROPHONE_INPUT_LOCATION = "party_microphone_input_location";
        public static final String PARTY_ONLY_QUEST_USERS_COLUMN = "party_only_quest_users";
        public static final String PARTY_PER_PERSON_MUTE_VALUE = "party_per_person_mute_value";
        public static final String PARTY_PRIVACY = "party_privacy";
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
        public static final String PARTY_TYPE = "party_type";
        public static final String PARTY_TYPE_VALUE = "party_type";
        public static final String PARTY_URL = "party_url";
        public static final String PARTY_USERS_COLUMN = "party_users";
        public static final String PARTY_USERS_COUNT_COLUMN = "party_users_count";
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
        public static final String SOCIAL_SETTING_VALUE = "social_setting_value";
        public static final String THREAD_ID_VALUE = "thread_id";
        public static Uri USER_BLOCK = null;
        public static final String USER_BLOCKED_BY_VIEWER_COLUMN = "blocked_by_viewer";
        public static final String USER_IDS_VALUE = "user_ids";
        public static final String USER_ID_COLUMN = "id";
        public static final String USER_ID_VALUE = "user_id";
        public static final String USER_NAME_COLUMN = "name";
        public static Uri USER_SEARCH = null;
        public static final String USER_SEARCH_AVATAR_URL = "avatar";
        public static final String USER_SEARCH_FRIEND_STATUS = "friend_status";
        public static final String USER_SEARCH_NAME = "name";
        public static final String USER_SEARCH_STRING_PARAM = "query_string";
        public static final String USER_SEARCH_TYPE_PARAM = "search_mode";
        public static final String USER_TO_UNINVITE_VALUE = "user_to_uninvite";
        public static Uri USER_UNBLOCK = null;
        public static final String USER_VALUE = "user";
        public static final String VIEWER_CURRENT_PARTY_ID = "viewer_current_party_id";
        public static final String VIEWER_FB_ID = "viewer_fb_id";

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

        public static class PartyMicrophoneInputLocation {
            public static final int APP = 0;
            public static final int PARTY = 1;
        }

        public enum UserInviteState {
            DEFAULT_UNKNOWN,
            INVITED,
            JOINED
        }

        public static void rebuild(String authority) {
            AUTHORITY = authority + ".friendlist";
            GET_FRIENDS_URI = Uri.parse("content://" + AUTHORITY + "/get_friends");
            GET_PARTY_INVITES = Uri.parse("content://" + AUTHORITY + "/get_party_invites");
            GET_PARTY_INVITE_INFO = Uri.parse("content://" + AUTHORITY + "/get_party_invite_info");
            GET_PARTY_PRIVACY_INFO = Uri.parse("content://" + AUTHORITY + "/get_party_privacy_info");
            GET_PARTY_INVITE_INFO_WITH_NONCE = Uri.parse("content://" + AUTHORITY + "/get_party_invite_info_with_nonce");
            PARTY_GET_JOINABLE_PARTIES_URI = Uri.parse("content://" + AUTHORITY + "/party_get_joinable");
            GET_PARTY_FBID = Uri.parse("content://" + AUTHORITY + "/get_party_fbid");
            GET_GROUP_LAUNCH_SUPPORTED_APPLICATIONS_URI = Uri.parse("content://" + AUTHORITY + "/get_group_launch_supported_applications");
            GET_GROUP_LAUNCH_APPLICATION_DESTINATIONS_URI = Uri.parse("content://" + AUTHORITY + "/get_group_launch_application_destinations");
            GET_APP_SCOREBOARDS_INFO_URI = Uri.parse("content://" + AUTHORITY + "/get_app_scoreboards_info");
            GET_APPS_SCOREBOARDS_INFO_URI = Uri.parse("content://" + AUTHORITY + "/get_apps_scoreboards_info");
            PARTY_GET_CURRENT_URI = Uri.parse("content://" + AUTHORITY + "/party_get_current");
            PARTY_GET_CURRENT_URI_V2 = Uri.parse("content://" + AUTHORITY + "/party_get_current_v2");
            PEOPLE_NEARBY_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/people_nearby");
            PYMK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/pymks");
            PYMK_HIDE_USER = Uri.parse("content://" + AUTHORITY + "/pymk_hide_user");
            MESSAGE_SEND_TO_THREAD = Uri.parse("content://" + AUTHORITY + "/message_send_to_thread");
            FRIEND_REQUESTS_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/friend_requests");
            FRIEND_LIST_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/party_invitable");
            USER_BLOCK = Uri.parse("content://" + AUTHORITY + "/user_block");
            USER_UNBLOCK = Uri.parse("content://" + AUTHORITY + "/user_unblock");
            FRIEND_SEND_REQUEST = Uri.parse("content://" + AUTHORITY + "/friend_request_send");
            FRIEND_ACCEPT_REQUEST = Uri.parse("content://" + AUTHORITY + "/friend_request_accept");
            FRIEND_REJECT_REQUEST = Uri.parse("content://" + AUTHORITY + "/friend_reject_request");
            FRIEND_CANCEL_REQUEST = Uri.parse("content://" + AUTHORITY + "/friend_request_cancel");
            FRIEND_REMOVE = Uri.parse("content://" + AUTHORITY + "/friend_remove");
            GET_SOCIAL_ACTIVITY = Uri.parse("content://" + AUTHORITY + "/social_activity");
            PARTY_INVITE_USERS_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/party_invite_users");
            PARTY_CREATE = Uri.parse("content://" + AUTHORITY + "/party_create");
            PARTY_JOIN = Uri.parse("content://" + AUTHORITY + "/party_join");
            PARTY_JOIN_WITH_ERROR_CALLBACK = Uri.parse("content://" + AUTHORITY + "/party_join_with_error_callback");
            PARTY_LEAVE = Uri.parse("content://" + AUTHORITY + "/party_leave");
            PARTY_CANCEL_INVITE = Uri.parse("content://" + AUTHORITY + "/party_cancel_invite");
            PARTY_SET_TYPE = Uri.parse("content://" + AUTHORITY + "/party_set_type");
            PARTY_ACTIVATE_LINK_INVITE = Uri.parse("content://" + AUTHORITY + "/party_activate_link_invite");
            PARTY_DEACTIVATE_LINK_INVITE = Uri.parse("content://" + AUTHORITY + "/party_deactivate_link_invite");
            PARTY_KICK = Uri.parse("content://" + AUTHORITY + "/party_kick");
            PARTY_PROPOSED_GROUP_LAUNCH_DESTINATION_CREATE = Uri.parse("content://" + AUTHORITY + "/party_proposed_group_launch_destination_create");
            PARTY_PROPOSED_GROUP_LAUNCH_DESTINATION_REMOVE = Uri.parse("content://" + AUTHORITY + "/party_proposed_group_launch_destination_remove");
            PARTY_SET_VOLUME = Uri.parse("content://" + AUTHORITY + "/party_set_volume");
            PARTY_GET_VOLUME = Uri.parse("content://" + AUTHORITY + "/party_get_volume");
            PARTY_SET_SELF_MUTE = Uri.parse("content://" + AUTHORITY + "/party_set_self_mute");
            PARTY_GET_SELF_MUTE = Uri.parse("content://" + AUTHORITY + "/party_get_self_mute");
            PARTY_SET_PER_PERSON_MUTE_STATUS = Uri.parse("content://" + AUTHORITY + "/party_set_per_person_mute_status");
            PARTY_GET_INFO_WITH_ACTIVITY = Uri.parse("content://" + AUTHORITY + "/party_get_info_with_activity");
            PARTY_SEND_MICROPHONE_INPUT_TO_APP = Uri.parse("content://" + AUTHORITY + "/party_send_microphone_input_to_app");
            PARTY_SEND_MICROPHONE_INPUT_TO_PARTY = Uri.parse("content://" + AUTHORITY + "/party_send_microphone_input_to_party");
            PARTY_GET_MICROPHONE_INPUT_LOCATION = Uri.parse("content://" + AUTHORITY + "/party_get_microphone_input_location");
            GET_LINKED_ACCOUNTS_INFO = Uri.parse("content://" + AUTHORITY + "/get_linked_accounts_info");
            GET_PARTY_HAS_PARTICIPANT_RECENTLY_SPOKEN = Uri.parse("content://" + AUTHORITY + "/get_party_has_participant_recently_spoken");
            PARTY_GET_INFO_WITH_MEMBERS = Uri.parse("content://" + AUTHORITY + "/party_get_info_with_members");
            GROUP_LAUNCH_SET_USER_RESPONSE = Uri.parse("content://" + AUTHORITY + "/group_launch_user_response");
            GROUP_LAUNCH_LAUNCH_HANDLE = Uri.parse("content://" + AUTHORITY + "/group_launch_launch_handle");
            GROUP_LAUNCH_SOLO_LAUNCH_HANDLE = Uri.parse("content://" + AUTHORITY + "/group_launch_solo_launch_handle");
            GET_PROFILE_CONTENT = Uri.parse("content://" + AUTHORITY + "/get_profile_content");
            PROFILE_SET_BIO = Uri.parse("content://" + AUTHORITY + "/profile_set_bio");
            GET_FB_FRIEND_PRIMARY_PROFILE = Uri.parse("content://" + AUTHORITY + "/get_fb_friend_primary_profile");
            GROUP_LAUNCH_SET_STATE = Uri.parse("content://" + AUTHORITY + "/group_launch_set_state");
            GET_SOCIAL_VIEWER = Uri.parse("content://" + AUTHORITY + "/get_social_viewer");
            GET_VR_PROFILE_CONTENT = Uri.parse("content://" + AUTHORITY + "/get_vr_profile_content");
            USER_SEARCH = SecureUriParser.parseStrict("content://" + AUTHORITY + "/user_search");
            GET_BLOCKED_USER_ID = Uri.parse("content://" + AUTHORITY + "/get_blocked_id");
            SET_NUX_FLAG_FOR_USER = SecureUriParser.parseStrict("content://" + AUTHORITY + "/set_nux_flag_for_user");
        }
    }
}
