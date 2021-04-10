package com.oculus.horizon.notifications.core;

import com.oculus.common.socialtablet.notif.SocialNotificationUtils;

public enum SocialNotificationType {
    EVENT_START("oculus_event_start"),
    EVENT_INVITE("oc_vr_event_invite"),
    FRIEND_REQUEST_RECEIVED("oculus_friend_request"),
    FRIEND_REQUEST_ACCEPTED("oculus_friend_request_accept"),
    MESSAGING_NEW_MESSAGE(SocialNotificationUtils.OCULUS_CHATS_NOTIFICATION_TYPE),
    PARTY_INVITE_RECEIVED("oculus_party_invite"),
    PARTY_GATHER_IN_APPLICATION_RECEIVED("oculus_party_gather_in_application"),
    PARTY_UNINVITE_RECEIVED("oculus_party_uninvite"),
    PARTY_UPDATE_RECEIVED("oculus_party_update"),
    ROOM_INVITE_RECEIVED("oculus_room_invite"),
    USER_STATUS_UPDATED("oculus_user_status"),
    ACHIEVEMENT_UNLOCKED("oculus_achievement_unlock"),
    OC_FACEBOOK_LINK_STATUS_CHANGED("oc_facebook_link_status_changed"),
    QUEUED_REMOTE_LAUNCH("oculus_queued_remote_launch"),
    SAVE_VR_FB_POST("oculus_save_vr_fb_post");
    
    public final String graphQLName;

    public static boolean isEventNotification(String str) {
        if (EVENT_START.graphQLName.equals(str) || EVENT_INVITE.graphQLName.equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isFriendRelatedNotification(String str) {
        if (FRIEND_REQUEST_ACCEPTED.graphQLName.equals(str) || FRIEND_REQUEST_RECEIVED.graphQLName.equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isFromAccepter(String str) {
        return FRIEND_REQUEST_ACCEPTED.graphQLName.equals(str);
    }

    public static boolean isFromSender(String str) {
        if (FRIEND_REQUEST_RECEIVED.graphQLName.equals(str) || ROOM_INVITE_RECEIVED.graphQLName.equals(str) || PARTY_INVITE_RECEIVED.graphQLName.equals(str) || PARTY_GATHER_IN_APPLICATION_RECEIVED.graphQLName.equals(str) || PARTY_UNINVITE_RECEIVED.graphQLName.equals(str) || MESSAGING_NEW_MESSAGE.graphQLName.equals(str) || EVENT_INVITE.graphQLName.equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isMessagingNotification(String str) {
        return MESSAGING_NEW_MESSAGE.graphQLName.equals(str);
    }

    public static boolean isNotShownToUser(String str) {
        if (USER_STATUS_UPDATED.graphQLName.equals(str) || PARTY_GATHER_IN_APPLICATION_RECEIVED.graphQLName.equals(str) || PARTY_UPDATE_RECEIVED.graphQLName.equals(str) || PARTY_UNINVITE_RECEIVED.graphQLName.equals(str) || OC_FACEBOOK_LINK_STATUS_CHANGED.graphQLName.equals(str) || QUEUED_REMOTE_LAUNCH.graphQLName.equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isPresenceNotification(String str) {
        if (FRIEND_REQUEST_RECEIVED.graphQLName.equals(str) || FRIEND_REQUEST_ACCEPTED.graphQLName.equals(str) || USER_STATUS_UPDATED.graphQLName.equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isSaveToVRNotification(String str) {
        return SAVE_VR_FB_POST.graphQLName.equals(str);
    }

    public static boolean isSocialNotification(String str) {
        if (FRIEND_REQUEST_RECEIVED.graphQLName.equals(str) || FRIEND_REQUEST_ACCEPTED.graphQLName.equals(str) || USER_STATUS_UPDATED.graphQLName.equals(str) || ROOM_INVITE_RECEIVED.graphQLName.equals(str) || PARTY_INVITE_RECEIVED.graphQLName.equals(str) || PARTY_GATHER_IN_APPLICATION_RECEIVED.graphQLName.equals(str) || PARTY_UNINVITE_RECEIVED.graphQLName.equals(str) || PARTY_UPDATE_RECEIVED.graphQLName.equals(str) || MESSAGING_NEW_MESSAGE.graphQLName.equals(str) || EVENT_START.graphQLName.equals(str) || EVENT_INVITE.graphQLName.equals(str) || ACHIEVEMENT_UNLOCKED.graphQLName.equals(str) || OC_FACEBOOK_LINK_STATUS_CHANGED.graphQLName.equals(str) || QUEUED_REMOTE_LAUNCH.graphQLName.equals(str) || SAVE_VR_FB_POST.graphQLName.equals(str)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: public */
    SocialNotificationType(String str) {
        this.graphQLName = str;
    }

    public static boolean isPartyNotification(NotificationType notificationType) {
        return NotificationType.PARTY_INVITE.equals(notificationType) || NotificationType.PARTY_INVITED_TO_APPLICATION.equals(notificationType) || NotificationType.PARTY_GATHER_IN_APPLICATION.equals(notificationType);
    }

    public static boolean isPartyNotification(String str) {
        return PARTY_INVITE_RECEIVED.graphQLName.equals(str) || PARTY_GATHER_IN_APPLICATION_RECEIVED.graphQLName.equals(str) || PARTY_UPDATE_RECEIVED.graphQLName.equals(str) || PARTY_UNINVITE_RECEIVED.graphQLName.equals(str);
    }

    public static boolean isRoomInviteNotification(NotificationType notificationType) {
        return NotificationType.ROOM_INVITE.equals(notificationType);
    }

    public static boolean isRoomInviteNotification(String str) {
        return ROOM_INVITE_RECEIVED.graphQLName.equals(str);
    }

    public static boolean shouldExcludeFromMarkingRead(NotificationType notificationType) {
        return isPartyNotification(notificationType) || isRoomInviteNotification(notificationType);
    }

    public static boolean shouldExcludeFromMarkingRead(String str) {
        return isPartyNotification(str) || isRoomInviteNotification(str);
    }
}
