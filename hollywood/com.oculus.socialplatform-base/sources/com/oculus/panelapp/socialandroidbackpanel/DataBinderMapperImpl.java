package com.oculus.panelapp.socialandroidbackpanel;

import X.AbstractC003408r;
import X.AnonymousClass1uS;
import X.AnonymousClass1uW;
import X.AnonymousClass2Q8;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.core.app.NotificationCompat$WearableExtender;
import com.oculus.panelapp.socialandroidbackpanel.databinding.ApplicationInvitesViewBindingImpl;
import com.oculus.panelapp.socialandroidbackpanel.databinding.DialogTitleBindingImpl;
import com.oculus.panelapp.socialandroidbackpanel.databinding.ErrorViewBindingImpl;
import com.oculus.panelapp.socialandroidbackpanel.databinding.JoinPartyViewBindingImpl;
import com.oculus.panelapp.socialandroidbackpanel.databinding.ListItemBindingImpl;
import com.oculus.panelapp.socialandroidbackpanel.databinding.PartyPrivacyMenuItemToggleBindingImpl;
import com.oculus.panelapp.socialandroidbackpanel.databinding.PartyPrivacyViewBindingImpl;
import com.oculus.panelapp.socialandroidbackpanel.databinding.SocialCreateVrInviteDialogBindingImpl;
import com.oculus.panelapp.socialandroidbackpanel.databinding.SocialCreateVrInviteListItemBindingImpl;
import com.oculus.panelapp.socialandroidbackpanel.databinding.SocialMenuItemToggleBindingImpl;
import com.oculus.panelapp.socialandroidbackpanel.databinding.StartMessengerCallViewBindingImpl;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogListItem;
import com.oculus.systemdialog.DialogProgressIndicator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends AnonymousClass1uS {
    public static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    public static final int LAYOUT_APPLICATIONINVITESVIEW = 1;
    public static final int LAYOUT_DIALOGTITLE = 2;
    public static final int LAYOUT_ERRORVIEW = 3;
    public static final int LAYOUT_JOINPARTYVIEW = 4;
    public static final int LAYOUT_LISTITEM = 5;
    public static final int LAYOUT_PARTYPRIVACYMENUITEMTOGGLE = 6;
    public static final int LAYOUT_PARTYPRIVACYVIEW = 7;
    public static final int LAYOUT_SOCIALCREATEVRINVITEDIALOG = 8;
    public static final int LAYOUT_SOCIALCREATEVRINVITELISTITEM = 9;
    public static final int LAYOUT_SOCIALMENUITEMTOGGLE = 10;
    public static final int LAYOUT_STARTMESSENGERCALLVIEW = 11;

    public static class InnerBrLookup {
        public static final SparseArray<String> sKeys;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(77);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "activeIndicator");
            sparseArray.put(2, "buttonText");
            sparseArray.put(3, "sideNavItem");
            sparseArray.put(4, "inactiveDrawable");
            sparseArray.put(5, "ctaText");
            sparseArray.put(6, "label");
            sparseArray.put(7, "badgeCount");
            sparseArray.put(8, "title");
            sparseArray.put(9, "isActive");
            sparseArray.put(10, "enabled");
            sparseArray.put(11, "showProgressPercentage");
            sparseArray.put(12, "activeDrawable");
            sparseArray.put(13, NotificationCompat$WearableExtender.KEY_BACKGROUND);
            sparseArray.put(14, "subtitle");
            sparseArray.put(15, "header");
            sparseArray.put(16, DialogProgressIndicator.DIALOG_PROGRESS_INDICATOR_PROGRESS_KEY);
            sparseArray.put(17, "viewModel");
            sparseArray.put(18, "titleIcon");
            sparseArray.put(19, "splash");
            sparseArray.put(20, "settingsVisibility");
            sparseArray.put(21, "chatVisibility");
            sparseArray.put(22, "peopleVisibility");
            sparseArray.put(23, "socialTabletSideNavViewModel");
            sparseArray.put(24, "icon");
            sparseArray.put(25, "progressDrawable");
            sparseArray.put(26, "text");
            sparseArray.put(27, "rightGlyph");
            sparseArray.put(28, "partyTypeDescription");
            sparseArray.put(29, "viewerAlias");
            sparseArray.put(30, "selectedAppDisplayName");
            sparseArray.put(31, "groupLaunchDestination");
            sparseArray.put(32, "currentPartyId");
            sparseArray.put(33, "hasCta");
            sparseArray.put(34, DialogListItem.DIALOG_LIST_ITEM_TITLE_TEXT_KEY);
            sparseArray.put(35, "checked");
            sparseArray.put(36, "id");
            sparseArray.put(37, "partyId");
            sparseArray.put(38, "inviteStep");
            sparseArray.put(39, "dialogTitleText");
            sparseArray.put(40, "selectedApp");
            sparseArray.put(41, "secondPartyUserImageUrl");
            sparseArray.put(42, "hasLinkInvite");
            sparseArray.put(43, "primaryActionButtonText");
            sparseArray.put(44, "primaryActionButtonEnabled");
            sparseArray.put(45, "groupLaunchApplication");
            sparseArray.put(46, "topText");
            sparseArray.put(47, "partyPrivacyViewModel");
            sparseArray.put(48, "profilePhotoUrl");
            sparseArray.put(49, "subtitleVisibility");
            sparseArray.put(50, "viewerProfilePhotoUrl");
            sparseArray.put(51, "firstPartyUserImageUrl");
            sparseArray.put(52, "hasSeenVRInviteProfileNux");
            sparseArray.put(53, "partyTypeIconImage");
            sparseArray.put(54, "userType");
            sparseArray.put(55, "isDestinationsSpinnerVisible");
            sparseArray.put(56, "applicationInvitesViewModel");
            sparseArray.put(57, "isReady");
            sparseArray.put(58, "joinPartyViewModel");
            sparseArray.put(59, "blockedUsersWarning");
            sparseArray.put(60, "partyUrl");
            sparseArray.put(61, "partyType");
            sparseArray.put(62, "isInviteOnly");
            sparseArray.put(63, "bottomText");
            sparseArray.put(64, "groupLaunchApps");
            sparseArray.put(65, "alias");
            sparseArray.put(66, "groupLaunchImageUrl");
            sparseArray.put(67, "errorViewModel");
            sparseArray.put(68, "partyTitle");
            sparseArray.put(69, "chooseLaterVisible");
            sparseArray.put(70, "selectedAppDisplayShortDescription");
            sparseArray.put(71, "message");
            sparseArray.put(72, "profileVisible");
            sparseArray.put(73, "selectedAppGroupJoinDetails");
            sparseArray.put(74, "isCreatingOrUpdating");
            sparseArray.put(75, "socialUserViewModel");
        }
    }

    public static class InnerLayoutIdLookup {
        public static final HashMap<String, Integer> sKeys;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(11);
            sKeys = hashMap;
            hashMap.put("layout/application_invites_view_0", Integer.valueOf((int) R.layout.application_invites_view));
            HashMap<String, Integer> hashMap2 = sKeys;
            hashMap2.put("layout/dialog_title_0", Integer.valueOf((int) R.layout.dialog_title));
            hashMap2.put("layout/error_view_0", Integer.valueOf((int) R.layout.error_view));
            hashMap2.put("layout/join_party_view_0", Integer.valueOf((int) R.layout.join_party_view));
            hashMap2.put("layout/list_item_0", Integer.valueOf((int) R.layout.list_item));
            hashMap2.put("layout/party_privacy_menu_item_toggle_0", Integer.valueOf((int) R.layout.party_privacy_menu_item_toggle));
            hashMap2.put("layout/party_privacy_view_0", Integer.valueOf((int) R.layout.party_privacy_view));
            hashMap2.put("layout/social_create_vr_invite_dialog_0", Integer.valueOf((int) R.layout.social_create_vr_invite_dialog));
            hashMap2.put("layout/social_create_vr_invite_list_item_0", Integer.valueOf((int) R.layout.social_create_vr_invite_list_item));
            hashMap2.put("layout/social_menu_item_toggle_0", Integer.valueOf((int) R.layout.social_menu_item_toggle));
            hashMap2.put("layout/start_messenger_call_view_0", Integer.valueOf((int) R.layout.start_messenger_call_view));
        }
    }

    @Override // X.AnonymousClass1uS
    public List<AnonymousClass1uS> collectDependencies() {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(new AnonymousClass2Q8());
        arrayList.add(new com.oculus.common.ocui.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.socialtablet.DataBinderMapperImpl());
        arrayList.add(new com.oculus.tablet.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // X.AnonymousClass1uS
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(11);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.application_invites_view, 1);
        sparseIntArray.put(R.layout.dialog_title, 2);
        sparseIntArray.put(R.layout.error_view, 3);
        sparseIntArray.put(R.layout.join_party_view, 4);
        sparseIntArray.put(R.layout.list_item, 5);
        sparseIntArray.put(R.layout.party_privacy_menu_item_toggle, 6);
        sparseIntArray.put(R.layout.party_privacy_view, 7);
        sparseIntArray.put(R.layout.social_create_vr_invite_dialog, 8);
        sparseIntArray.put(R.layout.social_create_vr_invite_list_item, 9);
        sparseIntArray.put(R.layout.social_menu_item_toggle, 10);
        sparseIntArray.put(R.layout.start_messenger_call_view, 11);
    }

    @Override // X.AnonymousClass1uS
    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    @Override // X.AnonymousClass1uS
    public AnonymousClass1uW getDataBinder(AbstractC003408r r4, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag != null) {
            switch (i2) {
                case 1:
                    if ("layout/application_invites_view_0".equals(tag)) {
                        return new ApplicationInvitesViewBindingImpl(r4, view);
                    }
                    StringBuilder sb = new StringBuilder("The tag for application_invites_view is invalid. Received: ");
                    sb.append(tag);
                    throw new IllegalArgumentException(sb.toString());
                case 2:
                    if ("layout/dialog_title_0".equals(tag)) {
                        return new DialogTitleBindingImpl(r4, view);
                    }
                    StringBuilder sb2 = new StringBuilder("The tag for dialog_title is invalid. Received: ");
                    sb2.append(tag);
                    throw new IllegalArgumentException(sb2.toString());
                case 3:
                    if ("layout/error_view_0".equals(tag)) {
                        return new ErrorViewBindingImpl(r4, view);
                    }
                    StringBuilder sb3 = new StringBuilder("The tag for error_view is invalid. Received: ");
                    sb3.append(tag);
                    throw new IllegalArgumentException(sb3.toString());
                case 4:
                    if ("layout/join_party_view_0".equals(tag)) {
                        return new JoinPartyViewBindingImpl(r4, view);
                    }
                    StringBuilder sb4 = new StringBuilder("The tag for join_party_view is invalid. Received: ");
                    sb4.append(tag);
                    throw new IllegalArgumentException(sb4.toString());
                case 5:
                    if ("layout/list_item_0".equals(tag)) {
                        return new ListItemBindingImpl(r4, view);
                    }
                    StringBuilder sb5 = new StringBuilder("The tag for list_item is invalid. Received: ");
                    sb5.append(tag);
                    throw new IllegalArgumentException(sb5.toString());
                case 6:
                    if ("layout/party_privacy_menu_item_toggle_0".equals(tag)) {
                        return new PartyPrivacyMenuItemToggleBindingImpl(r4, view);
                    }
                    StringBuilder sb6 = new StringBuilder("The tag for party_privacy_menu_item_toggle is invalid. Received: ");
                    sb6.append(tag);
                    throw new IllegalArgumentException(sb6.toString());
                case 7:
                    if ("layout/party_privacy_view_0".equals(tag)) {
                        return new PartyPrivacyViewBindingImpl(r4, view);
                    }
                    StringBuilder sb7 = new StringBuilder("The tag for party_privacy_view is invalid. Received: ");
                    sb7.append(tag);
                    throw new IllegalArgumentException(sb7.toString());
                case 8:
                    if ("layout/social_create_vr_invite_dialog_0".equals(tag)) {
                        return new SocialCreateVrInviteDialogBindingImpl(r4, view);
                    }
                    StringBuilder sb8 = new StringBuilder("The tag for social_create_vr_invite_dialog is invalid. Received: ");
                    sb8.append(tag);
                    throw new IllegalArgumentException(sb8.toString());
                case 9:
                    if ("layout/social_create_vr_invite_list_item_0".equals(tag)) {
                        return new SocialCreateVrInviteListItemBindingImpl(r4, view);
                    }
                    StringBuilder sb9 = new StringBuilder("The tag for social_create_vr_invite_list_item is invalid. Received: ");
                    sb9.append(tag);
                    throw new IllegalArgumentException(sb9.toString());
                case 10:
                    if ("layout/social_menu_item_toggle_0".equals(tag)) {
                        return new SocialMenuItemToggleBindingImpl(r4, view);
                    }
                    StringBuilder sb10 = new StringBuilder("The tag for social_menu_item_toggle is invalid. Received: ");
                    sb10.append(tag);
                    throw new IllegalArgumentException(sb10.toString());
                case 11:
                    if ("layout/start_messenger_call_view_0".equals(tag)) {
                        return new StartMessengerCallViewBindingImpl(r4, view);
                    }
                    StringBuilder sb11 = new StringBuilder("The tag for start_messenger_call_view is invalid. Received: ");
                    sb11.append(tag);
                    throw new IllegalArgumentException(sb11.toString());
                default:
                    return null;
            }
        } else {
            throw new RuntimeException("view must have a tag");
        }
    }

    @Override // X.AnonymousClass1uS
    public AnonymousClass1uW getDataBinder(AbstractC003408r r3, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
