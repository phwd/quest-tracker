package com.oculus.socialplatform.tablet;

import X.AbstractC003408r;
import X.AnonymousClass1uS;
import X.AnonymousClass1uW;
import X.AnonymousClass2Q8;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.core.app.NotificationCompat$WearableExtender;
import com.oculus.provider.OculusContent;
import com.oculus.systemdialog.DialogListItem;
import com.oculus.systemdialog.DialogProgressIndicator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends AnonymousClass1uS {
    public static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(0);

    public static class InnerBrLookup {
        public static final SparseArray<String> sKeys;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(236);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "footerSecondaryActionButtonText");
            sparseArray.put(2, "footerAppText");
            sparseArray.put(3, "shouldShowAudioConnected");
            sparseArray.put(4, "shouldShowSubtitleSeparator");
            sparseArray.put(5, "shouldShowCtaSpinner");
            sparseArray.put(6, "descriptionText");
            sparseArray.put(7, "fbActionEnabled");
            sparseArray.put(8, "footerPrimaryActionButtonBackground");
            sparseArray.put(9, "shouldShowLeftCta");
            sparseArray.put(10, "isHoverOverlayShowing");
            sparseArray.put(11, "footerShouldShowChangeDestinationButton");
            sparseArray.put(12, "rightCtaIcon");
            sparseArray.put(13, "shouldShowPartyFooter");
            sparseArray.put(14, "partyTravelFooterViewModel");
            sparseArray.put(15, "shouldShowMuteOverlay");
            sparseArray.put(16, "ctaText");
            sparseArray.put(17, "shouldShowCta");
            sparseArray.put(18, "shouldShowPartyLoading");
            sparseArray.put(19, "footerShouldShowSecondaryActionButton");
            sparseArray.put(20, "footerPrimaryActionButtonText");
            sparseArray.put(21, "mutePartyVolume");
            sparseArray.put(22, "shouldShowPartyDescriptionText");
            sparseArray.put(23, "isLoading");
            sparseArray.put(24, "shouldShowPartyAccessControls");
            sparseArray.put(25, "shouldShowOverflowMenu");
            sparseArray.put(26, "groupLaunchStatusText");
            sparseArray.put(27, "partiesViewModel");
            sparseArray.put(28, "isPartyActive");
            sparseArray.put(29, "party");
            sparseArray.put(30, "footerShouldEnableButtons");
            sparseArray.put(31, "subtitleText");
            sparseArray.put(32, "partySpotsAvailable");
            sparseArray.put(33, "isReady");
            sparseArray.put(34, "partyUserCardViewModel");
            sparseArray.put(35, "hasLinkSharing");
            sparseArray.put(36, "inviteToPartyCardViewModel");
            sparseArray.put(37, "isOnline");
            sparseArray.put(38, "leftCtaIcon");
            sparseArray.put(39, "footerShouldShowDestinationInfo");
            sparseArray.put(40, "footerShouldShowPrimaryActionButton");
            sparseArray.put(41, "shouldShowSpeakingIndicator");
            sparseArray.put(42, "footerShouldShowPrimaryActionButtonSpinner");
            sparseArray.put(43, "footerDestinationText");
            sparseArray.put(44, "shouldShowGroupLaunch");
            sparseArray.put(45, "footerShouldShowSecondaryActionButtonSpinner");
            sparseArray.put(46, "shouldUseActiveTextColor");
            sparseArray.put(47, "ctaIcon");
            sparseArray.put(48, "footerStatusText");
            sparseArray.put(49, OculusContent.Profile.USERNAME);
            sparseArray.put(50, "shouldShowRightCta");
            sparseArray.put(51, "continueButtonElementsAlpha");
            sparseArray.put(52, "headerText");
            sparseArray.put(53, "footerText");
            sparseArray.put(54, "passwordInputType");
            sparseArray.put(55, "bodyVisibility");
            sparseArray.put(56, "continueButtonText");
            sparseArray.put(57, "passwordVisibilityBtnBackground");
            sparseArray.put(58, "passwordInputEnabled");
            sparseArray.put(59, "continueButtonEnabled");
            sparseArray.put(60, DialogListItem.DIALOG_LIST_ITEM_BODY_TEXT_KEY);
            sparseArray.put(61, "errorBodyVisibility");
            sparseArray.put(62, "viewModel");
            sparseArray.put(63, "errorBodyText");
            sparseArray.put(64, "presenceIconVisibility");
            sparseArray.put(65, "showOnlineColor");
            sparseArray.put(66, "textGravity");
            sparseArray.put(67, "title");
            sparseArray.put(68, "searchIconVisibility");
            sparseArray.put(69, "onlineSubtitleColor");
            sparseArray.put(70, DialogListItem.DIALOG_LIST_ITEM_TITLE_TEXT_KEY);
            sparseArray.put(71, "showSearch");
            sparseArray.put(72, "titleTextSize");
            sparseArray.put(73, "isSubtitleVisible");
            sparseArray.put(74, "isSuccess");
            sparseArray.put(75, "image");
            sparseArray.put(76, "ctaBackground");
            sparseArray.put(77, "lastActiveTime");
            sparseArray.put(78, "nameText");
            sparseArray.put(79, "friendsButtonText");
            sparseArray.put(80, "subtitle");
            sparseArray.put(81, "usernameText");
            sparseArray.put(82, "presenceIcon");
            sparseArray.put(83, "subtitleRowVisibility");
            sparseArray.put(84, "selectedSetting");
            sparseArray.put(85, "isDeviceLockPatternSet");
            sparseArray.put(86, "signOutButtonEnabled");
            sparseArray.put(87, "userName");
            sparseArray.put(88, "isLoaded");
            sparseArray.put(89, "socialViewModel");
            sparseArray.put(90, "showLoading");
            sparseArray.put(91, "actionButtonText");
            sparseArray.put(92, "hoveredOverCard");
            sparseArray.put(93, "isInvitedUser");
            sparseArray.put(94, "view");
            sparseArray.put(95, "showPartyFooter");
            sparseArray.put(96, "action");
            sparseArray.put(97, "canShowStartParty");
            sparseArray.put(98, "isFriend");
            sparseArray.put(99, "muted");
            sparseArray.put(100, "isSpeaking");
            sparseArray.put(101, "secondaryActionButton");
            sparseArray.put(102, "showStartParty");
            sparseArray.put(103, "showAddFriend");
            sparseArray.put(104, "isFBLinked");
            sparseArray.put(105, "primaryActionButton");
            sparseArray.put(106, "statusText");
            sparseArray.put(107, "sharePartyButtonText");
            sparseArray.put(108, "name");
            sparseArray.put(109, "isMuted");
            sparseArray.put(110, "shouldShowSharePartyButton");
            sparseArray.put(111, "messageListVisibility");
            sparseArray.put(112, "reactionsTotalCount");
            sparseArray.put(113, "groupThreadBlockedViewManageBlockButtonText");
            sparseArray.put(114, "reactionsRemainingCount");
            sparseArray.put(115, "groupThreadBlockedViewTitleText");
            sparseArray.put(116, "isPartyButtonEnabled");
            sparseArray.put(117, "xmaBubbleViewModel");
            sparseArray.put(118, "firstReactionEmoji");
            sparseArray.put(119, "shouldShowFavicon");
            sparseArray.put(120, "isFacepileThreadIcon");
            sparseArray.put(121, "reportButtonVisibility");
            sparseArray.put(122, "messageDisplayText");
            sparseArray.put(123, "displayText");
            sparseArray.put(124, "secondReaction");
            sparseArray.put(125, "draftThreadHeaderVisibility");
            sparseArray.put(126, "attachmentSubtitle");
            sparseArray.put(127, "composerExpandedInputVisibility");
            sparseArray.put(128, "reactionTotalCountVisibility");
            sparseArray.put(129, "isPartyLinkLoading");
            sparseArray.put(130, "partyButtonText");
            sparseArray.put(131, "firstReactionUsername");
            sparseArray.put(132, "messengerViewModel");
            sparseArray.put(133, "composerBlockedViewVisibility");
            sparseArray.put(134, "composerBlockedViewContent");
            sparseArray.put(135, "currentThreadHeaderVisibility");
            sparseArray.put(136, "attachmentTitle");
            sparseArray.put(137, "shouldShowSenderInfo");
            sparseArray.put(138, "partyButtonDrawable");
            sparseArray.put(139, "thirdReactionEmoji");
            sparseArray.put(140, "shouldShowDisplayText");
            sparseArray.put(141, "secondReactionUsername");
            sparseArray.put(142, "composerCollapsedInputVisibility");
            sparseArray.put(143, "groupThreadBlockedViewBodyText");
            sparseArray.put(144, "currentThreadTitle");
            sparseArray.put(145, "displayTextTypeface");
            sparseArray.put(146, "reactionUsername");
            sparseArray.put(147, "reactionRemainingCountVisibility");
            sparseArray.put(148, "groupThreadBlockedViewManageBlockButtonVisibility");
            sparseArray.put(149, "thirdReaction");
            sparseArray.put(150, "contextMenuVisibility");
            sparseArray.put(151, "displayTextColor");
            sparseArray.put(152, "audioInputButtonVisibility");
            sparseArray.put(153, "reaction");
            sparseArray.put(154, "tabletTitleText");
            sparseArray.put(155, "thread");
            sparseArray.put(156, "message");
            sparseArray.put(157, "leftbarViewVisibility");
            sparseArray.put(158, "thirdReactionUsername");
            sparseArray.put(159, "firstReaction");
            sparseArray.put(160, "composeBarType");
            sparseArray.put(161, "composerVisibility");
            sparseArray.put(162, "secondReactionVisibility");
            sparseArray.put(163, "thirdReactionVisibility");
            sparseArray.put(164, "partyButtonVisibility");
            sparseArray.put(165, "secondReactionEmoji");
            sparseArray.put(166, "reactionEmoji");
            sparseArray.put(167, "activeIndicator");
            sparseArray.put(168, "buttonText");
            sparseArray.put(169, "sideNavItem");
            sparseArray.put(170, "inactiveDrawable");
            sparseArray.put(171, "label");
            sparseArray.put(172, "badgeCount");
            sparseArray.put(173, "isActive");
            sparseArray.put(174, "enabled");
            sparseArray.put(175, "showProgressPercentage");
            sparseArray.put(176, "activeDrawable");
            sparseArray.put(177, NotificationCompat$WearableExtender.KEY_BACKGROUND);
            sparseArray.put(178, "header");
            sparseArray.put(179, DialogProgressIndicator.DIALOG_PROGRESS_INDICATOR_PROGRESS_KEY);
            sparseArray.put(180, "titleIcon");
            sparseArray.put(181, "splash");
            sparseArray.put(182, "rightGlyph");
            sparseArray.put(183, "partyTypeDescription");
            sparseArray.put(184, "viewerAlias");
            sparseArray.put(185, "selectedAppDisplayName");
            sparseArray.put(186, "groupLaunchDestination");
            sparseArray.put(187, "currentPartyId");
            sparseArray.put(188, "hasCta");
            sparseArray.put(189, "checked");
            sparseArray.put(190, "id");
            sparseArray.put(191, "partyId");
            sparseArray.put(192, "inviteStep");
            sparseArray.put(193, "dialogTitleText");
            sparseArray.put(194, "selectedApp");
            sparseArray.put(195, "secondPartyUserImageUrl");
            sparseArray.put(196, "hasLinkInvite");
            sparseArray.put(197, "primaryActionButtonText");
            sparseArray.put(198, "primaryActionButtonEnabled");
            sparseArray.put(199, "groupLaunchApplication");
            sparseArray.put(200, "topText");
            sparseArray.put(201, "partyPrivacyViewModel");
            sparseArray.put(202, "profilePhotoUrl");
            sparseArray.put(203, "subtitleVisibility");
            sparseArray.put(204, "viewerProfilePhotoUrl");
            sparseArray.put(205, "firstPartyUserImageUrl");
            sparseArray.put(206, "hasSeenVRInviteProfileNux");
            sparseArray.put(207, "partyTypeIconImage");
            sparseArray.put(208, "userType");
            sparseArray.put(209, "isDestinationsSpinnerVisible");
            sparseArray.put(210, "applicationInvitesViewModel");
            sparseArray.put(211, "icon");
            sparseArray.put(212, "joinPartyViewModel");
            sparseArray.put(213, "blockedUsersWarning");
            sparseArray.put(214, "partyUrl");
            sparseArray.put(215, "partyType");
            sparseArray.put(216, "isInviteOnly");
            sparseArray.put(217, "bottomText");
            sparseArray.put(218, "groupLaunchApps");
            sparseArray.put(219, "alias");
            sparseArray.put(220, "groupLaunchImageUrl");
            sparseArray.put(221, "errorViewModel");
            sparseArray.put(222, "partyTitle");
            sparseArray.put(223, "chooseLaterVisible");
            sparseArray.put(224, "selectedAppDisplayShortDescription");
            sparseArray.put(225, "profileVisible");
            sparseArray.put(226, "selectedAppGroupJoinDetails");
            sparseArray.put(227, "isCreatingOrUpdating");
            sparseArray.put(228, "socialUserViewModel");
            sparseArray.put(229, "settingsVisibility");
            sparseArray.put(230, "chatVisibility");
            sparseArray.put(231, "peopleVisibility");
            sparseArray.put(232, "socialTabletSideNavViewModel");
            sparseArray.put(233, "progressDrawable");
            sparseArray.put(234, "text");
        }
    }

    public static class InnerLayoutIdLookup {
        public static final HashMap<String, Integer> sKeys = new HashMap<>(0);
    }

    @Override // X.AnonymousClass1uS
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // X.AnonymousClass1uS
    public List<AnonymousClass1uS> collectDependencies() {
        ArrayList arrayList = new ArrayList(11);
        arrayList.add(new AnonymousClass2Q8());
        arrayList.add(new com.oculus.common.ocui.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.socialtablet.DataBinderMapperImpl());
        arrayList.add(new com.oculus.panelapp.messenger.DataBinderMapperImpl());
        arrayList.add(new com.oculus.panelapp.parties.DataBinderMapperImpl());
        arrayList.add(new com.oculus.panelapp.people.DataBinderMapperImpl());
        arrayList.add(new com.oculus.panelapp.social.DataBinderMapperImpl());
        arrayList.add(new com.oculus.panelapp.socialandroidbackpanel.DataBinderMapperImpl());
        arrayList.add(new com.oculus.panelapp.socialreauth.DataBinderMapperImpl());
        arrayList.add(new com.oculus.panelapp.socialsettings.DataBinderMapperImpl());
        arrayList.add(new com.oculus.tablet.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // X.AnonymousClass1uS
    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    @Override // X.AnonymousClass1uS
    public AnonymousClass1uW getDataBinder(AbstractC003408r r3, View view, int i) {
        if (INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || view.getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // X.AnonymousClass1uS
    public AnonymousClass1uW getDataBinder(AbstractC003408r r3, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
