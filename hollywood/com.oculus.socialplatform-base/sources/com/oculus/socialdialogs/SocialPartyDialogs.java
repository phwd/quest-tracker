package com.oculus.socialdialogs;

import android.content.res.Resources;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionBase;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogIcon;
import com.oculus.systemdialog.DialogInformationBox;
import com.oculus.systemdialog.DialogList;
import com.oculus.systemdialog.DialogListItem;
import com.oculus.systemdialog.DialogListItemImageType;
import com.oculus.systemdialog.DialogListTitle;
import com.oculus.systemdialog.DialogListType;
import java.util.List;

public class SocialPartyDialogs {
    public static final String BLOCK_ACTION = "block";
    public static final String CANCEL_ACTION = "cancel";
    public static final String INVITE_ACTION = "invite";
    public static final String KICK_ACTION = "kick";
    public static final String LEAVE_ACTION = "leave";
    public static final String SWITCH_ACTION = "switch";

    public static DialogDefinitionBase getBlockDialog(String str, Resources resources) {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("social_dialogs_block", resources.getString(R.string.social_dialogs_block_title, str), resources.getString(R.string.social_dialogs_block_description));
        dialogDefinitionCustom.mDialogPrimaryButton = new DialogButtonText("block", resources.getString(R.string.social_dialogs_block_action));
        dialogDefinitionCustom.mDialogSecondaryButton = new DialogButtonText("cancel", resources.getString(R.string.social_dialogs_cancel));
        dialogDefinitionCustom.mDialogControllerBackButton = new DialogButton("cancel");
        dialogDefinitionCustom.mDialogInformationBox = new DialogInformationBox(resources.getString(R.string.social_dialogs_unfriend_infobox), DialogIcon.InformationBox.INFO);
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionBase getLeaveDirectJoinPartyDialog(Resources resources) {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("social_dialogs_party_leave_active_party", resources.getString(R.string.social_dialogs_direct_join_party_leave_title), resources.getString(R.string.social_dialogs_direct_join_party_leave_description));
        dialogDefinitionCustom.mDialogPrimaryButton = new DialogButtonText(LEAVE_ACTION, resources.getString(R.string.social_dialogs_party_leave_title));
        dialogDefinitionCustom.mDialogSecondaryButton = new DialogButtonText("cancel", resources.getString(R.string.social_dialogs_cancel));
        dialogDefinitionCustom.mDialogControllerBackButton = new DialogButton("cancel");
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionBase getLeaveInviteOnlyPartyDialog(Resources resources) {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("social_dialogs_party_leave_active_party", resources.getString(R.string.social_dialogs_party_leave_title), resources.getString(R.string.social_dialogs_party_leave_description));
        dialogDefinitionCustom.mDialogPrimaryButton = new DialogButtonText(LEAVE_ACTION, resources.getString(R.string.social_dialogs_party_leave_title));
        dialogDefinitionCustom.mDialogSecondaryButton = new DialogButtonText("cancel", resources.getString(R.string.social_dialogs_cancel));
        dialogDefinitionCustom.mDialogControllerBackButton = new DialogButton("cancel");
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionBase getPartyInviteDialog(Resources resources, List<SocialUser> list) {
        String str;
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("social_dialogs_party_invite", resources.getString(R.string.social_dialogs_party_invite_title), "");
        DialogList dialogList = new DialogList(DialogListType.MULTI_SELECT);
        boolean z = false;
        if (list == null || list.isEmpty()) {
            dialogDefinitionCustom.mDialogBody = resources.getString(R.string.social_dialogs_empty_friend_list_description);
            z = true;
        } else {
            dialogList.mDialogListTitle = new DialogListTitle(resources.getString(R.string.social_dialogs_party_invite_list_title), false);
            int i = 0;
            while (i < list.size() && i < 20) {
                SocialUser socialUser = list.get(i);
                String str2 = socialUser.mID;
                String str3 = socialUser.mAlias;
                String str4 = socialUser.mPresenceString;
                if (socialUser.mProfilePhotoURL.isEmpty()) {
                    str = null;
                } else {
                    str = socialUser.mProfilePhotoURL;
                }
                dialogList.addListItem(new DialogListItem(str2, str3, str4, str, DialogListItemImageType.AVATAR));
                i++;
            }
            dialogDefinitionCustom.mDialogList = dialogList;
        }
        DialogButtonText dialogButtonText = new DialogButtonText(INVITE_ACTION, resources.getString(R.string.social_dialogs_party_invite_confirm));
        dialogButtonText.mButtonDisabled = z;
        dialogDefinitionCustom.mDialogPrimaryButton = dialogButtonText;
        dialogDefinitionCustom.mDialogSecondaryButton = new DialogButtonText("cancel", resources.getString(R.string.social_dialogs_cancel));
        dialogDefinitionCustom.mDialogControllerBackButton = new DialogButton("cancel");
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionBase getPartyKickDialog(String str, Resources resources) {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("social_dialogs_party_kick", resources.getString(R.string.social_dialogs_party_kick_title, str), resources.getString(R.string.social_dialogs_party_kick_description, str));
        dialogDefinitionCustom.mDialogPrimaryButton = new DialogButtonText(KICK_ACTION, resources.getString(R.string.social_dialogs_party_kick_action));
        dialogDefinitionCustom.mDialogSecondaryButton = new DialogButtonText("cancel", resources.getString(R.string.social_dialogs_cancel));
        dialogDefinitionCustom.mDialogControllerBackButton = new DialogButton("cancel");
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionBase getSetToFriendsOfParticipantsPartyDialog(Resources resources) {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("social_dialogs_party_set_friends_of_participants", resources.getString(R.string.social_dialogs_party_privacy_friends_title), resources.getString(R.string.social_dialogs_party_privacy_friends_description));
        dialogDefinitionCustom.mDialogPrimaryButton = new DialogButtonText(SWITCH_ACTION, resources.getString(R.string.social_dialogs_party_privacy_confirm));
        dialogDefinitionCustom.mDialogSecondaryButton = new DialogButtonText("cancel", resources.getString(R.string.social_dialogs_cancel));
        dialogDefinitionCustom.mDialogControllerBackButton = new DialogButton("cancel");
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionBase getSetToInviteOnlyPartyDialog(Resources resources) {
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("social_dialogs_party_set_friends_of_participants", resources.getString(R.string.social_dialogs_party_privacy_invite_title), resources.getString(R.string.social_dialogs_party_privacy_invite_description));
        dialogDefinitionCustom.mDialogPrimaryButton = new DialogButtonText(SWITCH_ACTION, resources.getString(R.string.social_dialogs_party_privacy_confirm));
        dialogDefinitionCustom.mDialogSecondaryButton = new DialogButtonText("cancel", resources.getString(R.string.social_dialogs_cancel));
        dialogDefinitionCustom.mDialogControllerBackButton = new DialogButton("cancel");
        return dialogDefinitionCustom;
    }
}
