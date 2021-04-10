package com.oculus.panelapp.library;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.common.quickpromotion.QuickPromotionController;
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
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.ocui.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.vrshellutil.DataBinderMapperImpl());
        arrayList.add(new com.oculus.panelapp.anytimeui.DataBinderMapperImpl());
        arrayList.add(new com.oculus.tablet.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>(207);

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
            sKeys.put(156, "shouldShowActiveCallBarAdvancedControls");
            sKeys.put(157, "partySpotsAvailable");
            sKeys.put(158, "subtitleText");
            sKeys.put(159, "muteMicrophoneButtonIcon");
            sKeys.put(160, "title");
            sKeys.put(161, "showLoading");
            sKeys.put(162, "actionButtonText");
            sKeys.put(163, "hoveredOverCard");
            sKeys.put(164, "barLowerSectionVisible");
            sKeys.put(165, "isInvitedUser");
            sKeys.put(166, QuickPromotionController.EVENT_VIEW);
            sKeys.put(167, "showPartyFooter");
            sKeys.put(168, "action");
            sKeys.put(169, "canShowStartParty");
            sKeys.put(170, "isFriend");
            sKeys.put(171, "muted");
            sKeys.put(172, "ctaIcon");
            sKeys.put(173, "isSpeaking");
            sKeys.put(174, "secondaryActionButton");
            sKeys.put(175, "showStartParty");
            sKeys.put(176, "activeCallButtonTitle");
            sKeys.put(177, "showAddFriend");
            sKeys.put(178, "isFBLinked");
            sKeys.put(179, "mutePartyVolume");
            sKeys.put(180, "nameText");
            sKeys.put(181, "primaryActionButton");
            sKeys.put(182, "activeCallBarFullVisible");
            sKeys.put(183, "activeCallBarTitle");
            sKeys.put(184, "usernameText");
            sKeys.put(185, "statusText");
            sKeys.put(186, "sharePartyButtonText");
            sKeys.put(187, "name");
            sKeys.put(188, "activeCallBarSimpleVisible");
            sKeys.put(189, "groupLaunchStatusText");
            sKeys.put(190, "party");
            sKeys.put(191, "isMuted");
            sKeys.put(192, "shouldShowSharePartyButton");
            sKeys.put(193, "activeIndicator");
            sKeys.put(194, "progressDrawable");
            sKeys.put(195, "buttonText");
            sKeys.put(196, "inactiveDrawable");
            sKeys.put(197, "sideNavItem");
            sKeys.put(198, "ctaText");
            sKeys.put(199, "isActive");
            sKeys.put(200, "showProgressPercentage");
            sKeys.put(201, "activeDrawable");
            sKeys.put(202, "background");
            sKeys.put(203, AssistantDialogContract.MultiselectionDialog.Section.HEADER);
            sKeys.put(204, "titleIcon");
            sKeys.put(205, "splash");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(0);

        private InnerLayoutIdLookup() {
        }
    }
}
