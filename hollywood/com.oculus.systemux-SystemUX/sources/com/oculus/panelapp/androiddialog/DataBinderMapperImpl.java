package com.oculus.panelapp.androiddialog;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.panelapp.androiddialog.databinding.BackToTopBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.BlockDialogBulletBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.CustomSystemDialogBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.DialogActionSpacePrimarySecondaryBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.DialogActionSpaceSecondaryBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.DialogBodyBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.DialogTitleBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.EnterpriseCastToBrowserPinDialogBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.EnterpriseCastToBrowserPinDigitBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.EnterpriseKioskNotInstalledDialogBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.ErrorDialogBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.FacebookBlockDialogBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.FilePickerDialogBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.FilePickerPreviewBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.FilePickerRowBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.FilePreviewerDialogBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.FilePreviewerPreviewBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.FilePreviewerToolbarBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.LocalStreamPrivacyCheckBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.MessengerIntegrityDialogBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.MessengerIntegrityParticipantSelectItemBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.SocialCreateVrInviteDialogBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.SocialCreateVrInviteListItemBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.SocialJoinPartyDialogBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.SocialListItemBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.SocialMenuItemToggleBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.SocialPartyPrivacyDialogBindingImpl;
import com.oculus.panelapp.androiddialog.databinding.SocialProfileCardViewBindingImpl;
import com.oculus.systemdialog.DialogListItem;
import com.oculus.userserver.api.sharing.SharingManagerContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(28);
    private static final int LAYOUT_BACKTOTOP = 1;
    private static final int LAYOUT_BLOCKDIALOGBULLET = 2;
    private static final int LAYOUT_CUSTOMSYSTEMDIALOG = 3;
    private static final int LAYOUT_DIALOGACTIONSPACEPRIMARYSECONDARY = 4;
    private static final int LAYOUT_DIALOGACTIONSPACESECONDARY = 5;
    private static final int LAYOUT_DIALOGBODY = 6;
    private static final int LAYOUT_DIALOGTITLE = 7;
    private static final int LAYOUT_ENTERPRISECASTTOBROWSERPINDIALOG = 8;
    private static final int LAYOUT_ENTERPRISECASTTOBROWSERPINDIGIT = 9;
    private static final int LAYOUT_ENTERPRISEKIOSKNOTINSTALLEDDIALOG = 10;
    private static final int LAYOUT_ERRORDIALOG = 11;
    private static final int LAYOUT_FACEBOOKBLOCKDIALOG = 12;
    private static final int LAYOUT_FILEPICKERDIALOG = 13;
    private static final int LAYOUT_FILEPICKERPREVIEW = 14;
    private static final int LAYOUT_FILEPICKERROW = 15;
    private static final int LAYOUT_FILEPREVIEWERDIALOG = 16;
    private static final int LAYOUT_FILEPREVIEWERPREVIEW = 17;
    private static final int LAYOUT_FILEPREVIEWERTOOLBAR = 18;
    private static final int LAYOUT_LOCALSTREAMPRIVACYCHECK = 19;
    private static final int LAYOUT_MESSENGERINTEGRITYDIALOG = 20;
    private static final int LAYOUT_MESSENGERINTEGRITYPARTICIPANTSELECTITEM = 21;
    private static final int LAYOUT_SOCIALCREATEVRINVITEDIALOG = 22;
    private static final int LAYOUT_SOCIALCREATEVRINVITELISTITEM = 23;
    private static final int LAYOUT_SOCIALJOINPARTYDIALOG = 24;
    private static final int LAYOUT_SOCIALLISTITEM = 25;
    private static final int LAYOUT_SOCIALMENUITEMTOGGLE = 26;
    private static final int LAYOUT_SOCIALPARTYPRIVACYDIALOG = 27;
    private static final int LAYOUT_SOCIALPROFILECARDVIEW = 28;

    static {
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.back_to_top, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.block_dialog_bullet, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.custom_system_dialog, 3);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_action_space_primary_secondary, 4);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_action_space_secondary, 5);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_body, 6);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.dialog_title, 7);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.enterprise_cast_to_browser_pin_dialog, 8);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.enterprise_cast_to_browser_pin_digit, 9);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.enterprise_kiosk_not_installed_dialog, 10);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.error_dialog, 11);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.facebook_block_dialog, 12);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.file_picker_dialog, 13);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.file_picker_preview, 14);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.file_picker_row, 15);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.file_previewer_dialog, 16);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.file_previewer_preview, 17);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.file_previewer_toolbar, 18);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.local_stream_privacy_check, 19);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.messenger_integrity_dialog, 20);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.messenger_integrity_participant_select_item, 21);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.social_create_vr_invite_dialog, 22);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.social_create_vr_invite_list_item, 23);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.social_join_party_dialog, 24);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.social_list_item, 25);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.social_menu_item_toggle, 26);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.social_party_privacy_dialog, 27);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.social_profile_card_view, 28);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag != null) {
            switch (i2) {
                case 1:
                    if ("layout/back_to_top_0".equals(tag)) {
                        return new BackToTopBindingImpl(dataBindingComponent, new View[]{view});
                    }
                    throw new IllegalArgumentException("The tag for back_to_top is invalid. Received: " + tag);
                case 2:
                    if ("layout/block_dialog_bullet_0".equals(tag)) {
                        return new BlockDialogBulletBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for block_dialog_bullet is invalid. Received: " + tag);
                case 3:
                    if ("layout/custom_system_dialog_0".equals(tag)) {
                        return new CustomSystemDialogBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for custom_system_dialog is invalid. Received: " + tag);
                case 4:
                    if ("layout/dialog_action_space_primary_secondary_0".equals(tag)) {
                        return new DialogActionSpacePrimarySecondaryBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_action_space_primary_secondary is invalid. Received: " + tag);
                case 5:
                    if ("layout/dialog_action_space_secondary_0".equals(tag)) {
                        return new DialogActionSpaceSecondaryBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_action_space_secondary is invalid. Received: " + tag);
                case 6:
                    if ("layout/dialog_body_0".equals(tag)) {
                        return new DialogBodyBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_body is invalid. Received: " + tag);
                case 7:
                    if ("layout/dialog_title_0".equals(tag)) {
                        return new DialogTitleBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for dialog_title is invalid. Received: " + tag);
                case 8:
                    if ("layout/enterprise_cast_to_browser_pin_dialog_0".equals(tag)) {
                        return new EnterpriseCastToBrowserPinDialogBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for enterprise_cast_to_browser_pin_dialog is invalid. Received: " + tag);
                case 9:
                    if ("layout/enterprise_cast_to_browser_pin_digit_0".equals(tag)) {
                        return new EnterpriseCastToBrowserPinDigitBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for enterprise_cast_to_browser_pin_digit is invalid. Received: " + tag);
                case 10:
                    if ("layout/enterprise_kiosk_not_installed_dialog_0".equals(tag)) {
                        return new EnterpriseKioskNotInstalledDialogBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for enterprise_kiosk_not_installed_dialog is invalid. Received: " + tag);
                case 11:
                    if ("layout/error_dialog_0".equals(tag)) {
                        return new ErrorDialogBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for error_dialog is invalid. Received: " + tag);
                case 12:
                    if ("layout/facebook_block_dialog_0".equals(tag)) {
                        return new FacebookBlockDialogBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for facebook_block_dialog is invalid. Received: " + tag);
                case 13:
                    if ("layout/file_picker_dialog_0".equals(tag)) {
                        return new FilePickerDialogBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for file_picker_dialog is invalid. Received: " + tag);
                case 14:
                    if ("layout/file_picker_preview_0".equals(tag)) {
                        return new FilePickerPreviewBindingImpl(dataBindingComponent, new View[]{view});
                    }
                    throw new IllegalArgumentException("The tag for file_picker_preview is invalid. Received: " + tag);
                case 15:
                    if ("layout/file_picker_row_0".equals(tag)) {
                        return new FilePickerRowBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for file_picker_row is invalid. Received: " + tag);
                case 16:
                    if ("layout/file_previewer_dialog_0".equals(tag)) {
                        return new FilePreviewerDialogBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for file_previewer_dialog is invalid. Received: " + tag);
                case 17:
                    if ("layout/file_previewer_preview_0".equals(tag)) {
                        return new FilePreviewerPreviewBindingImpl(dataBindingComponent, new View[]{view});
                    }
                    throw new IllegalArgumentException("The tag for file_previewer_preview is invalid. Received: " + tag);
                case 18:
                    if ("layout/file_previewer_toolbar_0".equals(tag)) {
                        return new FilePreviewerToolbarBindingImpl(dataBindingComponent, new View[]{view});
                    }
                    throw new IllegalArgumentException("The tag for file_previewer_toolbar is invalid. Received: " + tag);
                case 19:
                    if ("layout/local_stream_privacy_check_0".equals(tag)) {
                        return new LocalStreamPrivacyCheckBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for local_stream_privacy_check is invalid. Received: " + tag);
                case 20:
                    if ("layout/messenger_integrity_dialog_0".equals(tag)) {
                        return new MessengerIntegrityDialogBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for messenger_integrity_dialog is invalid. Received: " + tag);
                case 21:
                    if ("layout/messenger_integrity_participant_select_item_0".equals(tag)) {
                        return new MessengerIntegrityParticipantSelectItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for messenger_integrity_participant_select_item is invalid. Received: " + tag);
                case 22:
                    if ("layout/social_create_vr_invite_dialog_0".equals(tag)) {
                        return new SocialCreateVrInviteDialogBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for social_create_vr_invite_dialog is invalid. Received: " + tag);
                case 23:
                    if ("layout/social_create_vr_invite_list_item_0".equals(tag)) {
                        return new SocialCreateVrInviteListItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for social_create_vr_invite_list_item is invalid. Received: " + tag);
                case 24:
                    if ("layout/social_join_party_dialog_0".equals(tag)) {
                        return new SocialJoinPartyDialogBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for social_join_party_dialog is invalid. Received: " + tag);
                case 25:
                    if ("layout/social_list_item_0".equals(tag)) {
                        return new SocialListItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for social_list_item is invalid. Received: " + tag);
                case 26:
                    if ("layout/social_menu_item_toggle_0".equals(tag)) {
                        return new SocialMenuItemToggleBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for social_menu_item_toggle is invalid. Received: " + tag);
                case 27:
                    if ("layout/social_party_privacy_dialog_0".equals(tag)) {
                        return new SocialPartyPrivacyDialogBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for social_party_privacy_dialog is invalid. Received: " + tag);
                case 28:
                    if ("layout/social_profile_card_view_0".equals(tag)) {
                        return new SocialProfileCardViewBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for social_profile_card_view is invalid. Received: " + tag);
                default:
                    return null;
            }
        } else {
            throw new RuntimeException("view must have a tag");
        }
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        int i2;
        if (!(viewArr == null || viewArr.length == 0 || (i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i)) <= 0)) {
            Object tag = viewArr[0].getTag();
            if (tag == null) {
                throw new RuntimeException("view must have a tag");
            } else if (i2 != 1) {
                if (i2 != 14) {
                    if (i2 != 17) {
                        if (i2 == 18) {
                            if ("layout/file_previewer_toolbar_0".equals(tag)) {
                                return new FilePreviewerToolbarBindingImpl(dataBindingComponent, viewArr);
                            }
                            throw new IllegalArgumentException("The tag for file_previewer_toolbar is invalid. Received: " + tag);
                        }
                    } else if ("layout/file_previewer_preview_0".equals(tag)) {
                        return new FilePreviewerPreviewBindingImpl(dataBindingComponent, viewArr);
                    } else {
                        throw new IllegalArgumentException("The tag for file_previewer_preview is invalid. Received: " + tag);
                    }
                } else if ("layout/file_picker_preview_0".equals(tag)) {
                    return new FilePickerPreviewBindingImpl(dataBindingComponent, viewArr);
                } else {
                    throw new IllegalArgumentException("The tag for file_picker_preview is invalid. Received: " + tag);
                }
            } else if ("layout/back_to_top_0".equals(tag)) {
                return new BackToTopBindingImpl(dataBindingComponent, viewArr);
            } else {
                throw new IllegalArgumentException("The tag for back_to_top is invalid. Received: " + tag);
            }
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.ocui.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.vrshellutil.DataBinderMapperImpl());
        arrayList.add(new com.oculus.tablet.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>(136);

        private InnerBrLookup() {
        }

        static {
            sKeys.put(0, "_all");
            sKeys.put(1, "activeIndicator");
            sKeys.put(2, "icon");
            sKeys.put(3, "progress");
            sKeys.put(4, "progressDrawable");
            sKeys.put(5, "text");
            sKeys.put(6, SharingManagerContract.ARGUMENT_IS_ENABLED);
            sKeys.put(7, "buttonText");
            sKeys.put(8, "inactiveDrawable");
            sKeys.put(9, "sideNavItem");
            sKeys.put(10, "ctaText");
            sKeys.put(11, "title");
            sKeys.put(12, "badgeCount");
            sKeys.put(13, "isActive");
            sKeys.put(14, "showProgressPercentage");
            sKeys.put(15, "activeDrawable");
            sKeys.put(16, "background");
            sKeys.put(17, "subtitle");
            sKeys.put(18, AssistantDialogContract.MultiselectionDialog.Section.HEADER);
            sKeys.put(19, "viewModel");
            sKeys.put(20, "titleIcon");
            sKeys.put(21, "splash");
            sKeys.put(22, "progressSpinnerVisible");
            sKeys.put(23, "primaryButtonDisabled");
            sKeys.put(24, "fbSectionButtonText");
            sKeys.put(25, "viewerAlias");
            sKeys.put(26, "backButtonVisible");
            sKeys.put(27, "messengerSectionTitleAlpha");
            sKeys.put(28, "currentPartyId");
            sKeys.put(29, "selectedFileDuration");
            sKeys.put(30, "hasCta");
            sKeys.put(31, DialogListItem.DIALOG_LIST_ITEM_TITLE_TEXT_KEY);
            sKeys.put(32, "selectedFilePath");
            sKeys.put(33, "unblockButtonText");
            sKeys.put(34, "id");
            sKeys.put(35, "partyId");
            sKeys.put(36, "fbBlockedMessengerUnblockSectionVisibility");
            sKeys.put(37, "inviteStep");
            sKeys.put(38, "dialogTitleText");
            sKeys.put(39, "secondPartyUserImageUrl");
            sKeys.put(40, "hasLinkInvite");
            sKeys.put(41, "primaryActionButtonText");
            sKeys.put(42, "groupLaunchApplication");
            sKeys.put(43, "displayForBlocked");
            sKeys.put(44, "partyLeader");
            sKeys.put(45, "topText");
            sKeys.put(46, "subtitleVisibility");
            sKeys.put(47, "firstPartyUserImageUrl");
            sKeys.put(48, "digit5");
            sKeys.put(49, "blockButtonText");
            sKeys.put(50, "fbSectionTitle");
            sKeys.put(51, "digit1");
            sKeys.put(52, "digit2");
            sKeys.put(53, "iconButtonVisible");
            sKeys.put(54, "digit3");
            sKeys.put(55, "dialogTitle");
            sKeys.put(56, "digit4");
            sKeys.put(57, "iconButtonIcon");
            sKeys.put(58, "fbSectionVisibility");
            sKeys.put(59, "digit0");
            sKeys.put(60, "targetName");
            sKeys.put(61, "participantSelectorVisibility");
            sKeys.put(62, "tertiaryButtonDisabled");
            sKeys.put(63, "isDestinationsSpinnerVisible");
            sKeys.put(64, "selectedFileDimensions");
            sKeys.put(65, "blockedUsersWarning");
            sKeys.put(66, "fbUnblockSectionVisibility");
            sKeys.put(67, "partyUrl");
            sKeys.put(68, "sectionDividerVisibility");
            sKeys.put(69, "blockedPartyUsers");
            sKeys.put(70, "bottomText");
            sKeys.put(71, "fbSectionButtonEnabled");
            sKeys.put(72, "secondaryButtonDisabled");
            sKeys.put(73, "unblockButtonEnabled");
            sKeys.put(74, "informationBoxText");
            sKeys.put(75, "viewerViewModel");
            sKeys.put(76, "isCreatingOrUpdating");
            sKeys.put(77, "socialUserViewModel");
            sKeys.put(78, "messengerSectionButtonEnabled");
            sKeys.put(79, "selectedFileName");
            sKeys.put(80, "rightGlyph");
            sKeys.put(81, "partyTypeDescription");
            sKeys.put(82, "blockSectionVisibility");
            sKeys.put(83, "blockUnblockViewSpinnerVisibility");
            sKeys.put(84, "selectedAppDisplayName");
            sKeys.put(85, "groupLaunchDestination");
            sKeys.put(86, "messengerUnblockSectionVisibility");
            sKeys.put(87, "body");
            sKeys.put(88, "participant");
            sKeys.put(89, "isButtonEnabled");
            sKeys.put(90, "fbBlockSectionVisibility");
            sKeys.put(91, "checked");
            sKeys.put(92, "primaryButtonText");
            sKeys.put(93, "digit");
            sKeys.put(94, "messengerBlockSectionVisibility");
            sKeys.put(95, "selectedApp");
            sKeys.put(96, "displayablePartyUsers");
            sKeys.put(97, "primaryActionButtonEnabled");
            sKeys.put(98, "profilePhotoUrl");
            sKeys.put(99, "selectedFileTypeAndSize");
            sKeys.put(100, "hasSeenVRInviteProfileNux");
            sKeys.put(101, "dialogBodyText");
            sKeys.put(102, "partyTypeIconImage");
            sKeys.put(103, "userType");
            sKeys.put(104, "partyUsers");
            sKeys.put(105, "blockButtonEnabled");
            sKeys.put(106, "progressBarVisible");
            sKeys.put(107, "isReady");
            sKeys.put(108, "partyType");
            sKeys.put(109, "isInviteOnly");
            sKeys.put(110, "primaryButtonStyle");
            sKeys.put(111, "groupLaunchApps");
            sKeys.put(112, "fbUnblockMessengerExplanation");
            sKeys.put(113, "privacyViewModel");
            sKeys.put(114, "alpha");
            sKeys.put(115, "progressBarProgress");
            sKeys.put(116, "alias");
            sKeys.put(117, "groupLaunchImageUrl");
            sKeys.put(118, "errorViewModel");
            sKeys.put(119, "messengerSectionVisibility");
            sKeys.put(120, "partyTitle");
            sKeys.put(121, "chooseLaterVisible");
            sKeys.put(122, "selectedAppDisplayShortDescription");
            sKeys.put(123, "resources");
            sKeys.put(124, "selectedFileDateAdded");
            sKeys.put(125, "message");
            sKeys.put(126, "secondaryButtonText");
            sKeys.put(127, "partyViewModel");
            sKeys.put(128, "profileVisible");
            sKeys.put(129, "unblockSectionVisibility");
            sKeys.put(130, "tertiaryButtonText");
            sKeys.put(131, "selectedAppGroupJoinDetails");
            sKeys.put(132, "messengerSectionTitle");
            sKeys.put(133, "messengerSectionButtonText");
            sKeys.put(134, "user");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(28);

        private InnerLayoutIdLookup() {
        }

        static {
            sKeys.put("layout/back_to_top_0", Integer.valueOf(R.layout.back_to_top));
            sKeys.put("layout/block_dialog_bullet_0", Integer.valueOf(R.layout.block_dialog_bullet));
            sKeys.put("layout/custom_system_dialog_0", Integer.valueOf(R.layout.custom_system_dialog));
            sKeys.put("layout/dialog_action_space_primary_secondary_0", Integer.valueOf(R.layout.dialog_action_space_primary_secondary));
            sKeys.put("layout/dialog_action_space_secondary_0", Integer.valueOf(R.layout.dialog_action_space_secondary));
            sKeys.put("layout/dialog_body_0", Integer.valueOf(R.layout.dialog_body));
            sKeys.put("layout/dialog_title_0", Integer.valueOf(R.layout.dialog_title));
            sKeys.put("layout/enterprise_cast_to_browser_pin_dialog_0", Integer.valueOf(R.layout.enterprise_cast_to_browser_pin_dialog));
            sKeys.put("layout/enterprise_cast_to_browser_pin_digit_0", Integer.valueOf(R.layout.enterprise_cast_to_browser_pin_digit));
            sKeys.put("layout/enterprise_kiosk_not_installed_dialog_0", Integer.valueOf(R.layout.enterprise_kiosk_not_installed_dialog));
            sKeys.put("layout/error_dialog_0", Integer.valueOf(R.layout.error_dialog));
            sKeys.put("layout/facebook_block_dialog_0", Integer.valueOf(R.layout.facebook_block_dialog));
            sKeys.put("layout/file_picker_dialog_0", Integer.valueOf(R.layout.file_picker_dialog));
            sKeys.put("layout/file_picker_preview_0", Integer.valueOf(R.layout.file_picker_preview));
            sKeys.put("layout/file_picker_row_0", Integer.valueOf(R.layout.file_picker_row));
            sKeys.put("layout/file_previewer_dialog_0", Integer.valueOf(R.layout.file_previewer_dialog));
            sKeys.put("layout/file_previewer_preview_0", Integer.valueOf(R.layout.file_previewer_preview));
            sKeys.put("layout/file_previewer_toolbar_0", Integer.valueOf(R.layout.file_previewer_toolbar));
            sKeys.put("layout/local_stream_privacy_check_0", Integer.valueOf(R.layout.local_stream_privacy_check));
            sKeys.put("layout/messenger_integrity_dialog_0", Integer.valueOf(R.layout.messenger_integrity_dialog));
            sKeys.put("layout/messenger_integrity_participant_select_item_0", Integer.valueOf(R.layout.messenger_integrity_participant_select_item));
            sKeys.put("layout/social_create_vr_invite_dialog_0", Integer.valueOf(R.layout.social_create_vr_invite_dialog));
            sKeys.put("layout/social_create_vr_invite_list_item_0", Integer.valueOf(R.layout.social_create_vr_invite_list_item));
            sKeys.put("layout/social_join_party_dialog_0", Integer.valueOf(R.layout.social_join_party_dialog));
            sKeys.put("layout/social_list_item_0", Integer.valueOf(R.layout.social_list_item));
            sKeys.put("layout/social_menu_item_toggle_0", Integer.valueOf(R.layout.social_menu_item_toggle));
            sKeys.put("layout/social_party_privacy_dialog_0", Integer.valueOf(R.layout.social_party_privacy_dialog));
            sKeys.put("layout/social_profile_card_view_0", Integer.valueOf(R.layout.social_profile_card_view));
        }
    }
}
