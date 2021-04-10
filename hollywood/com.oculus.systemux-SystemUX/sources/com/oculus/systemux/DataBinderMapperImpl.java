package com.oculus.systemux;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.common.quickpromotion.QuickPromotionController;
import com.oculus.systemdialog.DialogListItem;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.userserver.api.sharing.SharingManagerContract;
import com.oculus.vrshell.panels.systemtooltip.TooltipDefinition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(0);

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        if (INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || view.getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
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
        ArrayList arrayList = new ArrayList(8);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.notifications.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.vrshellutil.DataBinderMapperImpl());
        arrayList.add(new com.oculus.panelapp.androiddialog.DataBinderMapperImpl());
        arrayList.add(new com.oculus.panelapp.anytimeui.DataBinderMapperImpl());
        arrayList.add(new com.oculus.panelapp.bugreporter.DataBinderMapperImpl());
        arrayList.add(new com.oculus.panelapp.continuingeducation.DataBinderMapperImpl());
        arrayList.add(new com.oculus.panelapp.library.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>(327);

        private InnerBrLookup() {
        }

        static {
            sKeys.put(0, "_all");
            sKeys.put(1, "isAdminPinRequired");
            sKeys.put(2, "filledKeypadBubble");
            sKeys.put(3, "socialViewModel");
            sKeys.put(4, "currentPlatform");
            sKeys.put(5, "companyName");
            sKeys.put(6, "captureAllowed");
            sKeys.put(7, "bio");
            sKeys.put(8, "isKeypadThrottled");
            sKeys.put(9, "castingText");
            sKeys.put(10, "recordingIcon");
            sKeys.put(11, "iconDrawable");
            sKeys.put(12, "buttonActionType");
            sKeys.put(13, "secondaryButtonDrawable");
            sKeys.put(14, "platformDropdownEnabled");
            sKeys.put(15, "text");
            sKeys.put(16, "drawableStart");
            sKeys.put(17, "app");
            sKeys.put(18, "loadingProfile");
            sKeys.put(19, "isPaused");
            sKeys.put(20, "item");
            sKeys.put(21, "showContextMenu");
            sKeys.put(22, "isCheckingForUpdatesVisible");
            sKeys.put(23, "realityTunerValue");
            sKeys.put(24, "isHandTrackingEnabled");
            sKeys.put(25, "partyButtonVisible");
            sKeys.put(26, "isKeypadBackspaceEnabled");
            sKeys.put(27, "showBiography");
            sKeys.put(28, "showSavingChangesSpinner");
            sKeys.put(29, "settingsViewModel");
            sKeys.put(30, "destinationUIViewModel");
            sKeys.put(31, "paddingStartDip");
            sKeys.put(32, "brightnessIcon");
            sKeys.put(33, "isInternetConnected");
            sKeys.put(34, "seenState");
            sKeys.put(35, "route");
            sKeys.put(36, "ingestedApps");
            sKeys.put(37, "showUnknownSources");
            sKeys.put(38, "sectionHeader");
            sKeys.put(39, "subtitle");
            sKeys.put(40, "userHasAvatar");
            sKeys.put(41, AssistantDialogContract.MultiselectionDialog.Section.ITEMS);
            sKeys.put(42, "dropdownAction");
            sKeys.put(43, "subtitleUri");
            sKeys.put(44, "systemApp");
            sKeys.put(45, "isHovered");
            sKeys.put(46, "unknownSources");
            sKeys.put(47, "isContextMenuButtonHovered");
            sKeys.put(48, "filterCounts");
            sKeys.put(49, "inviteEnabled");
            sKeys.put(50, "gameScreenshot");
            sKeys.put(51, "displayName");
            sKeys.put(52, "icon");
            sKeys.put(53, "heroImage");
            sKeys.put(54, "panelApp");
            sKeys.put(55, "isTrackingEnabled");
            sKeys.put(56, SharingManagerContract.ARGUMENT_IS_ENABLED);
            sKeys.put(57, "complexButtonActionType");
            sKeys.put(58, "sorterOptions");
            sKeys.put(59, "presenceString");
            sKeys.put(60, "presenceStringColor");
            sKeys.put(61, "navigationActionType");
            sKeys.put(62, "isScreenshotVisible");
            sKeys.put(63, "wifiButtonIcon");
            sKeys.put(64, "quitButtonText");
            sKeys.put(65, "showEditControls");
            sKeys.put(66, "hasCompleteProfile");
            sKeys.put(67, "sharingViewModel");
            sKeys.put(68, "sliderAction");
            sKeys.put(69, "hasContextMenu");
            sKeys.put(70, "isSavingChanges");
            sKeys.put(71, "lastSyncTimeText");
            sKeys.put(72, "platformOptions");
            sKeys.put(73, "launcherActions");
            sKeys.put(74, "lastSyncSubtitleText");
            sKeys.put(75, "imageAvailable");
            sKeys.put(76, "showBackButton");
            sKeys.put(77, "isAdminModeEnabled");
            sKeys.put(78, "progress");
            sKeys.put(79, "highlightHome");
            sKeys.put(80, "brightnessSeekbarVisible");
            sKeys.put(81, "showFriendCount");
            sKeys.put(82, "showPresenceString");
            sKeys.put(83, "isPrimaryButtonLoading");
            sKeys.put(84, "parentAppName");
            sKeys.put(85, "showPresenceIcon");
            sKeys.put(86, "showLoadingError");
            sKeys.put(87, TabletDeepLinkingUriUtil.ANDROID_SETTINGS_QUERY_SECTION_KEY);
            sKeys.put(88, "toggleAction");
            sKeys.put(89, "eventsEntryEnabled");
            sKeys.put(90, "keypadEntryLength");
            sKeys.put(91, "primaryButtonType");
            sKeys.put(92, "hasToolbarButton");
            sKeys.put(93, "notification");
            sKeys.put(94, "isAbuseReportRecording");
            sKeys.put(95, "lastUpdateTimeText");
            sKeys.put(96, "isPrototype");
            sKeys.put(97, "browserApp");
            sKeys.put(98, "primaryButtonDrawable");
            sKeys.put(99, "highlightLibrary");
            sKeys.put(100, "isKeypadEntryReadyToBeChecked");
            sKeys.put(101, "handsButtonVisible");
            sKeys.put(102, "thumbnail");
            sKeys.put(103, "recordingText");
            sKeys.put(104, "tvApp");
            sKeys.put(105, "hasInternetConnection");
            sKeys.put(106, "editMode");
            sKeys.put(107, "showProfileContent");
            sKeys.put(108, "currentFilter");
            sKeys.put(109, "viewModel");
            sKeys.put(110, "presenceIcon");
            sKeys.put(111, "isCheckForUpdatesVisible");
            sKeys.put(112, TooltipDefinition.TOOLTIP_POSITION_KEY);
            sKeys.put(113, "barViewModel");
            sKeys.put(114, "volumeIcon");
            sKeys.put(115, "licenseText");
            sKeys.put(116, AssistantDialogContract.Dialog.DESCRIPTION);
            sKeys.put(117, "infoBox");
            sKeys.put(118, "coloredBackground");
            sKeys.put(119, "realityTunerSliderVisible");
            sKeys.put(120, "currentSorter");
            sKeys.put(121, "secondaryButtonEnabled");
            sKeys.put(122, "wifiButtonCTA");
            sKeys.put(123, "onlinePresenceDotVisible");
            sKeys.put(124, "badgeCount");
            sKeys.put(125, "guardianIcon");
            sKeys.put(126, "nullState");
            sKeys.put(127, "isOverflowButtonLoading");
            sKeys.put(128, "isUserActive");
            sKeys.put(129, "highlight");
            sKeys.put(130, "guardianCTA");
            sKeys.put(131, "showSecondaryButton");
            sKeys.put(132, "statusViewModel");
            sKeys.put(133, "alias");
            sKeys.put(134, "liveStreamAllowed");
            sKeys.put(135, AssistantComponentContract.Components.TextComponent.VALUE);
            sKeys.put(136, "secondaryButtonType");
            sKeys.put(137, "filterOptions");
            sKeys.put(138, "liveStreamText");
            sKeys.put(139, "completeProfileButtonVisible");
            sKeys.put(140, "appName");
            sKeys.put(141, "showHeader");
            sKeys.put(142, "resources");
            sKeys.put(143, "squareImage");
            sKeys.put(144, "isVideo");
            sKeys.put(145, AssistantDialogContract.MultiselectionDialog.Items.LABEL);
            sKeys.put(146, "toolbarButtonTitle");
            sKeys.put(147, "showPrimaryButton");
            sKeys.put(148, "realName");
            sKeys.put(149, "environment");
            sKeys.put(150, "brightness");
            sKeys.put(151, "videoRecordingButtonEnabled");
            sKeys.put(152, "headerColoredSystemAppsEnabled");
            sKeys.put(153, "time");
            sKeys.put(154, "updateCheckState");
            sKeys.put(155, "currentItem");
            sKeys.put(156, "bugCategory");
            sKeys.put(157, "continueButtonEnabled");
            sKeys.put(158, "screenshot");
            sKeys.put(159, "includeScreenshot");
            sKeys.put(160, "descriptionText");
            sKeys.put(161, "attachLogs");
            sKeys.put(162, "continueButtonText");
            sKeys.put(163, "hasPreselectedPhoto");
            sKeys.put(164, "hasExceededFileSizeLimit");
            sKeys.put(165, "shouldShowActiveCallBarAdvancedControls");
            sKeys.put(166, "partySpotsAvailable");
            sKeys.put(167, "subtitleText");
            sKeys.put(168, "muteMicrophoneButtonIcon");
            sKeys.put(169, "title");
            sKeys.put(170, "showLoading");
            sKeys.put(171, "actionButtonText");
            sKeys.put(172, "hoveredOverCard");
            sKeys.put(173, "barLowerSectionVisible");
            sKeys.put(174, "isInvitedUser");
            sKeys.put(175, QuickPromotionController.EVENT_VIEW);
            sKeys.put(176, "showPartyFooter");
            sKeys.put(177, "action");
            sKeys.put(178, "canShowStartParty");
            sKeys.put(179, "isFriend");
            sKeys.put(180, "muted");
            sKeys.put(181, "ctaIcon");
            sKeys.put(182, "isSpeaking");
            sKeys.put(183, "secondaryActionButton");
            sKeys.put(184, "showStartParty");
            sKeys.put(185, "activeCallButtonTitle");
            sKeys.put(186, "showAddFriend");
            sKeys.put(187, "isFBLinked");
            sKeys.put(188, "mutePartyVolume");
            sKeys.put(189, "nameText");
            sKeys.put(190, "primaryActionButton");
            sKeys.put(191, "activeCallBarFullVisible");
            sKeys.put(192, "activeCallBarTitle");
            sKeys.put(193, "usernameText");
            sKeys.put(194, "statusText");
            sKeys.put(195, "sharePartyButtonText");
            sKeys.put(196, "name");
            sKeys.put(197, "activeCallBarSimpleVisible");
            sKeys.put(198, "groupLaunchStatusText");
            sKeys.put(199, "party");
            sKeys.put(200, "isMuted");
            sKeys.put(201, "shouldShowSharePartyButton");
            sKeys.put(202, "progressSpinnerVisible");
            sKeys.put(203, "primaryButtonDisabled");
            sKeys.put(204, "fbSectionButtonText");
            sKeys.put(205, "viewerAlias");
            sKeys.put(206, "backButtonVisible");
            sKeys.put(207, "messengerSectionTitleAlpha");
            sKeys.put(208, "currentPartyId");
            sKeys.put(209, "selectedFileDuration");
            sKeys.put(210, "hasCta");
            sKeys.put(211, DialogListItem.DIALOG_LIST_ITEM_TITLE_TEXT_KEY);
            sKeys.put(212, "selectedFilePath");
            sKeys.put(213, "unblockButtonText");
            sKeys.put(214, "id");
            sKeys.put(215, "partyId");
            sKeys.put(216, "fbBlockedMessengerUnblockSectionVisibility");
            sKeys.put(217, "inviteStep");
            sKeys.put(218, "buttonText");
            sKeys.put(219, "dialogTitleText");
            sKeys.put(220, "secondPartyUserImageUrl");
            sKeys.put(221, "hasLinkInvite");
            sKeys.put(222, "primaryActionButtonText");
            sKeys.put(223, "groupLaunchApplication");
            sKeys.put(224, "displayForBlocked");
            sKeys.put(225, "partyLeader");
            sKeys.put(226, "topText");
            sKeys.put(227, "subtitleVisibility");
            sKeys.put(228, "firstPartyUserImageUrl");
            sKeys.put(229, "digit5");
            sKeys.put(230, "blockButtonText");
            sKeys.put(231, "fbSectionTitle");
            sKeys.put(232, "digit1");
            sKeys.put(233, "digit2");
            sKeys.put(234, "iconButtonVisible");
            sKeys.put(235, "digit3");
            sKeys.put(236, "dialogTitle");
            sKeys.put(237, "digit4");
            sKeys.put(238, "iconButtonIcon");
            sKeys.put(239, "fbSectionVisibility");
            sKeys.put(240, "digit0");
            sKeys.put(241, "targetName");
            sKeys.put(242, "participantSelectorVisibility");
            sKeys.put(243, "tertiaryButtonDisabled");
            sKeys.put(244, "isDestinationsSpinnerVisible");
            sKeys.put(245, "selectedFileDimensions");
            sKeys.put(246, "blockedUsersWarning");
            sKeys.put(247, "fbUnblockSectionVisibility");
            sKeys.put(248, "partyUrl");
            sKeys.put(249, "sectionDividerVisibility");
            sKeys.put(250, "blockedPartyUsers");
            sKeys.put(251, "bottomText");
            sKeys.put(252, "fbSectionButtonEnabled");
            sKeys.put(253, "secondaryButtonDisabled");
            sKeys.put(254, "unblockButtonEnabled");
            sKeys.put(255, "informationBoxText");
            sKeys.put(256, "viewerViewModel");
            sKeys.put(257, "isCreatingOrUpdating");
            sKeys.put(258, "socialUserViewModel");
            sKeys.put(259, "messengerSectionButtonEnabled");
            sKeys.put(260, "selectedFileName");
            sKeys.put(261, "rightGlyph");
            sKeys.put(262, "partyTypeDescription");
            sKeys.put(263, "blockSectionVisibility");
            sKeys.put(264, "blockUnblockViewSpinnerVisibility");
            sKeys.put(265, "selectedAppDisplayName");
            sKeys.put(266, "groupLaunchDestination");
            sKeys.put(267, "messengerUnblockSectionVisibility");
            sKeys.put(268, "body");
            sKeys.put(269, "participant");
            sKeys.put(270, "isButtonEnabled");
            sKeys.put(271, "fbBlockSectionVisibility");
            sKeys.put(272, "checked");
            sKeys.put(273, "primaryButtonText");
            sKeys.put(274, "digit");
            sKeys.put(275, "messengerBlockSectionVisibility");
            sKeys.put(276, "selectedApp");
            sKeys.put(277, "displayablePartyUsers");
            sKeys.put(278, "primaryActionButtonEnabled");
            sKeys.put(279, "profilePhotoUrl");
            sKeys.put(280, "selectedFileTypeAndSize");
            sKeys.put(281, "hasSeenVRInviteProfileNux");
            sKeys.put(282, "dialogBodyText");
            sKeys.put(283, "partyTypeIconImage");
            sKeys.put(284, "userType");
            sKeys.put(285, "partyUsers");
            sKeys.put(286, "blockButtonEnabled");
            sKeys.put(287, "progressBarVisible");
            sKeys.put(288, "isReady");
            sKeys.put(289, "partyType");
            sKeys.put(290, "isInviteOnly");
            sKeys.put(291, "primaryButtonStyle");
            sKeys.put(292, "groupLaunchApps");
            sKeys.put(293, "fbUnblockMessengerExplanation");
            sKeys.put(294, "privacyViewModel");
            sKeys.put(295, "alpha");
            sKeys.put(296, "progressBarProgress");
            sKeys.put(297, "groupLaunchImageUrl");
            sKeys.put(298, "errorViewModel");
            sKeys.put(299, "messengerSectionVisibility");
            sKeys.put(300, "partyTitle");
            sKeys.put(301, "chooseLaterVisible");
            sKeys.put(302, "selectedAppDisplayShortDescription");
            sKeys.put(303, "selectedFileDateAdded");
            sKeys.put(304, "message");
            sKeys.put(305, "secondaryButtonText");
            sKeys.put(306, "partyViewModel");
            sKeys.put(307, "profileVisible");
            sKeys.put(308, "unblockSectionVisibility");
            sKeys.put(309, "tertiaryButtonText");
            sKeys.put(310, "selectedAppGroupJoinDetails");
            sKeys.put(311, "messengerSectionTitle");
            sKeys.put(312, "messengerSectionButtonText");
            sKeys.put(313, "user");
            sKeys.put(314, "activeIndicator");
            sKeys.put(315, "progressDrawable");
            sKeys.put(316, "inactiveDrawable");
            sKeys.put(317, "sideNavItem");
            sKeys.put(318, "ctaText");
            sKeys.put(319, "isActive");
            sKeys.put(320, "showProgressPercentage");
            sKeys.put(321, "activeDrawable");
            sKeys.put(322, "background");
            sKeys.put(323, AssistantDialogContract.MultiselectionDialog.Section.HEADER);
            sKeys.put(324, "titleIcon");
            sKeys.put(325, "splash");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(0);

        private InnerLayoutIdLookup() {
        }
    }
}
