package com.oculus.common.socialtablet.parties;

import android.content.Context;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.notifications.NotificationSender;
import com.oculus.socialplatform.R;

public class PartyNotificationHelper {
    public static final String TAG = LoggingUtil.tag(PartyNotificationHelper.class);
    public static PartyNotificationHelper sPartyNotificationHelper;

    private void sendInternalNotification(Context context, String str, String str2) {
        new NotificationSender.Builder(str2, str, null, R.drawable.oc_icon_info_filled_24).send(context);
    }

    public static PartyNotificationHelper getSingleton() {
        PartyNotificationHelper partyNotificationHelper = sPartyNotificationHelper;
        if (partyNotificationHelper != null) {
            return partyNotificationHelper;
        }
        PartyNotificationHelper partyNotificationHelper2 = new PartyNotificationHelper();
        sPartyNotificationHelper = partyNotificationHelper2;
        return partyNotificationHelper2;
    }

    public void sendFBInviteSharedConfirmationToast(Context context, String str, String str2) {
        sendInternalNotification(context, context.getResources().getString(R.string.party_notification_fb_link_shared_message, str2), str);
    }

    public void sendFBInviteSharedConfirmationToastGroupGeneric(Context context, String str) {
        sendInternalNotification(context, context.getResources().getString(R.string.party_notification_fb_link_shared_message_group), str);
    }

    public void sendFBInviteSharedConfirmationToastMoreThanTwoPeople(Context context, String str, String str2, int i) {
        sendInternalNotification(context, context.getResources().getQuantityString(R.plurals.party_notification_fb_link_shared_message_more_than_two_people, i, str2, Integer.valueOf(i)), str);
    }

    public void sendFBInviteSharedConfirmationToastTwoPeople(Context context, String str, String str2, String str3) {
        sendInternalNotification(context, context.getResources().getString(R.string.party_notification_fb_link_shared_message_two_people, str2, str3), str);
    }

    public void sendFriendPartyFullToast(Context context, String str) {
        sendInternalNotification(context, context.getResources().getString(R.string.party_notification_friend_party_full_message), str);
    }

    public void sendGenericErrorToast(Context context, String str) {
        sendInternalNotification(context, context.getResources().getString(R.string.party_notification_generic_error_message), str);
    }

    public void sendLinkSharingOffToast(Context context, String str) {
        sendInternalNotification(context, context.getResources().getString(R.string.party_notification_link_sharing_off_message), str);
    }

    public void sendOCInviteSentConfirmationToast(Context context, String str, String str2) {
        sendInternalNotification(context, context.getResources().getString(R.string.party_notification_oc_invite_sent_message, str2), str);
    }

    public void sendOCInviteSentConfirmationToastGroupGeneric(Context context, String str) {
        sendInternalNotification(context, context.getResources().getString(R.string.party_notification_oc_invite_sent_message_group), str);
    }

    public void sendOCInviteSentConfirmationToastMoreThanTwoPeople(Context context, String str, String str2, int i) {
        sendInternalNotification(context, context.getResources().getQuantityString(R.plurals.party_notification_oc_invite_sent_message_more_than_two_people, i, str2, Integer.valueOf(i)), str);
    }

    public void sendOCInviteSentConfirmationToastTwoPeople(Context context, String str, String str2, String str3) {
        sendInternalNotification(context, context.getResources().getString(R.string.party_notification_oc_invite_sent_message_two_people, str2, str3), str);
    }

    public void sendViewerInPartyToast(Context context, String str) {
        sendInternalNotification(context, context.getResources().getString(R.string.party_notification_viewer_already_in_party_message), str);
    }

    public void sendViewerPartyFullToast(Context context, String str) {
        sendInternalNotification(context, context.getResources().getString(R.string.party_notification_viewer_party_full_message), str);
    }
}
