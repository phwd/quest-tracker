package com.oculus.panelapp.social;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.common.quickpromotion.QuickPromotionController;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialFriendsHeaderBindingImpl;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialGuidedActionCardBindingImpl;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialHorizontalUsersViewBindingImpl;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialListHeaderV2BindingImpl;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialListItemV2BindingImpl;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialListNullRowV2BindingImpl;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialOfflineBindingImpl;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyFooterBindingImpl;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyHeaderBindingImpl;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialUserCardBindingImpl;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2BindingImpl;
import com.oculus.userserver.api.sharing.SharingManagerContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(11);
    private static final int LAYOUT_ANYTIMETABLETSOCIALFRIENDSHEADER = 1;
    private static final int LAYOUT_ANYTIMETABLETSOCIALGUIDEDACTIONCARD = 2;
    private static final int LAYOUT_ANYTIMETABLETSOCIALHORIZONTALUSERSVIEW = 3;
    private static final int LAYOUT_ANYTIMETABLETSOCIALLISTHEADERV2 = 4;
    private static final int LAYOUT_ANYTIMETABLETSOCIALLISTITEMV2 = 5;
    private static final int LAYOUT_ANYTIMETABLETSOCIALLISTNULLROWV2 = 6;
    private static final int LAYOUT_ANYTIMETABLETSOCIALOFFLINE = 7;
    private static final int LAYOUT_ANYTIMETABLETSOCIALPARTYFOOTER = 8;
    private static final int LAYOUT_ANYTIMETABLETSOCIALPARTYHEADER = 9;
    private static final int LAYOUT_ANYTIMETABLETSOCIALUSERCARD = 10;
    private static final int LAYOUT_ANYTIMETABLETSOCIALVIEWV2 = 11;

    static {
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_social_friends_header, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_social_guided_action_card, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_social_horizontal_users_view, 3);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_social_list_header_v2, 4);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_social_list_item_v2, 5);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_social_list_null_row_v2, 6);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_social_offline, 7);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_social_party_footer, 8);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_social_party_header, 9);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_social_user_card, 10);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.anytime_tablet_social_view_v2, 11);
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
                    if ("layout/anytime_tablet_social_friends_header_0".equals(tag)) {
                        return new AnytimeTabletSocialFriendsHeaderBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for anytime_tablet_social_friends_header is invalid. Received: " + tag);
                case 2:
                    if ("layout/anytime_tablet_social_guided_action_card_0".equals(tag)) {
                        return new AnytimeTabletSocialGuidedActionCardBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for anytime_tablet_social_guided_action_card is invalid. Received: " + tag);
                case 3:
                    if ("layout/anytime_tablet_social_horizontal_users_view_0".equals(tag)) {
                        return new AnytimeTabletSocialHorizontalUsersViewBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for anytime_tablet_social_horizontal_users_view is invalid. Received: " + tag);
                case 4:
                    if ("layout/anytime_tablet_social_list_header_v2_0".equals(tag)) {
                        return new AnytimeTabletSocialListHeaderV2BindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for anytime_tablet_social_list_header_v2 is invalid. Received: " + tag);
                case 5:
                    if ("layout/anytime_tablet_social_list_item_v2_0".equals(tag)) {
                        return new AnytimeTabletSocialListItemV2BindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for anytime_tablet_social_list_item_v2 is invalid. Received: " + tag);
                case 6:
                    if ("layout/anytime_tablet_social_list_null_row_v2_0".equals(tag)) {
                        return new AnytimeTabletSocialListNullRowV2BindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for anytime_tablet_social_list_null_row_v2 is invalid. Received: " + tag);
                case 7:
                    if ("layout/anytime_tablet_social_offline_0".equals(tag)) {
                        return new AnytimeTabletSocialOfflineBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for anytime_tablet_social_offline is invalid. Received: " + tag);
                case 8:
                    if ("layout/anytime_tablet_social_party_footer_0".equals(tag)) {
                        return new AnytimeTabletSocialPartyFooterBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for anytime_tablet_social_party_footer is invalid. Received: " + tag);
                case 9:
                    if ("layout/anytime_tablet_social_party_header_0".equals(tag)) {
                        return new AnytimeTabletSocialPartyHeaderBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for anytime_tablet_social_party_header is invalid. Received: " + tag);
                case 10:
                    if ("layout/anytime_tablet_social_user_card_0".equals(tag)) {
                        return new AnytimeTabletSocialUserCardBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for anytime_tablet_social_user_card is invalid. Received: " + tag);
                case 11:
                    if ("layout/anytime_tablet_social_view_v2_0".equals(tag)) {
                        return new AnytimeTabletSocialViewV2BindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for anytime_tablet_social_view_v2 is invalid. Received: " + tag);
                default:
                    return null;
            }
        } else {
            throw new RuntimeException("view must have a tag");
        }
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
        arrayList.add(new com.oculus.common.notifications.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.ocui.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.vrshellutil.DataBinderMapperImpl());
        arrayList.add(new com.oculus.tablet.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>(60);

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
            sKeys.put(22, "shouldShowActiveCallBarAdvancedControls");
            sKeys.put(23, "socialViewModel");
            sKeys.put(24, "partySpotsAvailable");
            sKeys.put(25, "subtitleText");
            sKeys.put(26, "muteMicrophoneButtonIcon");
            sKeys.put(27, "showLoading");
            sKeys.put(28, "actionButtonText");
            sKeys.put(29, "hoveredOverCard");
            sKeys.put(30, "barLowerSectionVisible");
            sKeys.put(31, "isInvitedUser");
            sKeys.put(32, QuickPromotionController.EVENT_VIEW);
            sKeys.put(33, "showPartyFooter");
            sKeys.put(34, "action");
            sKeys.put(35, "canShowStartParty");
            sKeys.put(36, "isFriend");
            sKeys.put(37, "muted");
            sKeys.put(38, "ctaIcon");
            sKeys.put(39, "isSpeaking");
            sKeys.put(40, "secondaryActionButton");
            sKeys.put(41, "showStartParty");
            sKeys.put(42, "activeCallButtonTitle");
            sKeys.put(43, "showAddFriend");
            sKeys.put(44, "isFBLinked");
            sKeys.put(45, "mutePartyVolume");
            sKeys.put(46, "nameText");
            sKeys.put(47, "primaryActionButton");
            sKeys.put(48, "activeCallBarFullVisible");
            sKeys.put(49, "activeCallBarTitle");
            sKeys.put(50, "usernameText");
            sKeys.put(51, "statusText");
            sKeys.put(52, "sharePartyButtonText");
            sKeys.put(53, "name");
            sKeys.put(54, "activeCallBarSimpleVisible");
            sKeys.put(55, "groupLaunchStatusText");
            sKeys.put(56, "party");
            sKeys.put(57, "isMuted");
            sKeys.put(58, "shouldShowSharePartyButton");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(11);

        private InnerLayoutIdLookup() {
        }

        static {
            sKeys.put("layout/anytime_tablet_social_friends_header_0", Integer.valueOf(R.layout.anytime_tablet_social_friends_header));
            sKeys.put("layout/anytime_tablet_social_guided_action_card_0", Integer.valueOf(R.layout.anytime_tablet_social_guided_action_card));
            sKeys.put("layout/anytime_tablet_social_horizontal_users_view_0", Integer.valueOf(R.layout.anytime_tablet_social_horizontal_users_view));
            sKeys.put("layout/anytime_tablet_social_list_header_v2_0", Integer.valueOf(R.layout.anytime_tablet_social_list_header_v2));
            sKeys.put("layout/anytime_tablet_social_list_item_v2_0", Integer.valueOf(R.layout.anytime_tablet_social_list_item_v2));
            sKeys.put("layout/anytime_tablet_social_list_null_row_v2_0", Integer.valueOf(R.layout.anytime_tablet_social_list_null_row_v2));
            sKeys.put("layout/anytime_tablet_social_offline_0", Integer.valueOf(R.layout.anytime_tablet_social_offline));
            sKeys.put("layout/anytime_tablet_social_party_footer_0", Integer.valueOf(R.layout.anytime_tablet_social_party_footer));
            sKeys.put("layout/anytime_tablet_social_party_header_0", Integer.valueOf(R.layout.anytime_tablet_social_party_header));
            sKeys.put("layout/anytime_tablet_social_user_card_0", Integer.valueOf(R.layout.anytime_tablet_social_user_card));
            sKeys.put("layout/anytime_tablet_social_view_v2_0", Integer.valueOf(R.layout.anytime_tablet_social_view_v2));
        }
    }
}
