package com.oculus.panelapp.social;

import X.AbstractC003408r;
import X.AnonymousClass1uS;
import X.AnonymousClass1uW;
import X.AnonymousClass2Q8;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.core.app.NotificationCompat$WearableExtender;
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
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogProgressIndicator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends AnonymousClass1uS {
    public static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    public static final int LAYOUT_ANYTIMETABLETSOCIALFRIENDSHEADER = 1;
    public static final int LAYOUT_ANYTIMETABLETSOCIALGUIDEDACTIONCARD = 2;
    public static final int LAYOUT_ANYTIMETABLETSOCIALHORIZONTALUSERSVIEW = 3;
    public static final int LAYOUT_ANYTIMETABLETSOCIALLISTHEADERV2 = 4;
    public static final int LAYOUT_ANYTIMETABLETSOCIALLISTITEMV2 = 5;
    public static final int LAYOUT_ANYTIMETABLETSOCIALLISTNULLROWV2 = 6;
    public static final int LAYOUT_ANYTIMETABLETSOCIALOFFLINE = 7;
    public static final int LAYOUT_ANYTIMETABLETSOCIALPARTYFOOTER = 8;
    public static final int LAYOUT_ANYTIMETABLETSOCIALPARTYHEADER = 9;
    public static final int LAYOUT_ANYTIMETABLETSOCIALUSERCARD = 10;
    public static final int LAYOUT_ANYTIMETABLETSOCIALVIEWV2 = 11;

    public static class InnerBrLookup {
        public static final SparseArray<String> sKeys;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(54);
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
            sparseArray.put(20, "icon");
            sparseArray.put(21, "progressDrawable");
            sparseArray.put(22, "text");
            sparseArray.put(23, "socialViewModel");
            sparseArray.put(24, "partySpotsAvailable");
            sparseArray.put(25, "subtitleText");
            sparseArray.put(26, "showLoading");
            sparseArray.put(27, "actionButtonText");
            sparseArray.put(28, "hoveredOverCard");
            sparseArray.put(29, "isInvitedUser");
            sparseArray.put(30, "view");
            sparseArray.put(31, "showPartyFooter");
            sparseArray.put(32, "action");
            sparseArray.put(33, "canShowStartParty");
            sparseArray.put(34, "isFriend");
            sparseArray.put(35, "muted");
            sparseArray.put(36, "ctaIcon");
            sparseArray.put(37, "isSpeaking");
            sparseArray.put(38, "secondaryActionButton");
            sparseArray.put(39, "showStartParty");
            sparseArray.put(40, "showAddFriend");
            sparseArray.put(41, "isFBLinked");
            sparseArray.put(42, "mutePartyVolume");
            sparseArray.put(43, "nameText");
            sparseArray.put(44, "primaryActionButton");
            sparseArray.put(45, "usernameText");
            sparseArray.put(46, "statusText");
            sparseArray.put(47, "sharePartyButtonText");
            sparseArray.put(48, "name");
            sparseArray.put(49, "groupLaunchStatusText");
            sparseArray.put(50, "party");
            sparseArray.put(51, "isMuted");
            sparseArray.put(52, "shouldShowSharePartyButton");
        }
    }

    public static class InnerLayoutIdLookup {
        public static final HashMap<String, Integer> sKeys;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(11);
            sKeys = hashMap;
            hashMap.put("layout/anytime_tablet_social_friends_header_0", Integer.valueOf((int) R.layout.anytime_tablet_social_friends_header));
            HashMap<String, Integer> hashMap2 = sKeys;
            hashMap2.put("layout/anytime_tablet_social_guided_action_card_0", Integer.valueOf((int) R.layout.anytime_tablet_social_guided_action_card));
            hashMap2.put("layout/anytime_tablet_social_horizontal_users_view_0", Integer.valueOf((int) R.layout.anytime_tablet_social_horizontal_users_view));
            hashMap2.put("layout/anytime_tablet_social_list_header_v2_0", Integer.valueOf((int) R.layout.anytime_tablet_social_list_header_v2));
            hashMap2.put("layout/anytime_tablet_social_list_item_v2_0", Integer.valueOf((int) R.layout.anytime_tablet_social_list_item_v2));
            hashMap2.put("layout/anytime_tablet_social_list_null_row_v2_0", Integer.valueOf((int) R.layout.anytime_tablet_social_list_null_row_v2));
            hashMap2.put("layout/anytime_tablet_social_offline_0", Integer.valueOf((int) R.layout.anytime_tablet_social_offline));
            hashMap2.put("layout/anytime_tablet_social_party_footer_0", Integer.valueOf((int) R.layout.anytime_tablet_social_party_footer));
            hashMap2.put("layout/anytime_tablet_social_party_header_0", Integer.valueOf((int) R.layout.anytime_tablet_social_party_header));
            hashMap2.put("layout/anytime_tablet_social_user_card_0", Integer.valueOf((int) R.layout.anytime_tablet_social_user_card));
            hashMap2.put("layout/anytime_tablet_social_view_v2_0", Integer.valueOf((int) R.layout.anytime_tablet_social_view_v2));
        }
    }

    @Override // X.AnonymousClass1uS
    public List<AnonymousClass1uS> collectDependencies() {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(new AnonymousClass2Q8());
        arrayList.add(new com.oculus.common.ocui.DataBinderMapperImpl());
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
        sparseIntArray.put(R.layout.anytime_tablet_social_friends_header, 1);
        sparseIntArray.put(R.layout.anytime_tablet_social_guided_action_card, 2);
        sparseIntArray.put(R.layout.anytime_tablet_social_horizontal_users_view, 3);
        sparseIntArray.put(R.layout.anytime_tablet_social_list_header_v2, 4);
        sparseIntArray.put(R.layout.anytime_tablet_social_list_item_v2, 5);
        sparseIntArray.put(R.layout.anytime_tablet_social_list_null_row_v2, 6);
        sparseIntArray.put(R.layout.anytime_tablet_social_offline, 7);
        sparseIntArray.put(R.layout.anytime_tablet_social_party_footer, 8);
        sparseIntArray.put(R.layout.anytime_tablet_social_party_header, 9);
        sparseIntArray.put(R.layout.anytime_tablet_social_user_card, 10);
        sparseIntArray.put(R.layout.anytime_tablet_social_view_v2, 11);
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
                    if ("layout/anytime_tablet_social_friends_header_0".equals(tag)) {
                        return new AnytimeTabletSocialFriendsHeaderBindingImpl(r4, view);
                    }
                    StringBuilder sb = new StringBuilder("The tag for anytime_tablet_social_friends_header is invalid. Received: ");
                    sb.append(tag);
                    throw new IllegalArgumentException(sb.toString());
                case 2:
                    if ("layout/anytime_tablet_social_guided_action_card_0".equals(tag)) {
                        return new AnytimeTabletSocialGuidedActionCardBindingImpl(r4, view);
                    }
                    StringBuilder sb2 = new StringBuilder("The tag for anytime_tablet_social_guided_action_card is invalid. Received: ");
                    sb2.append(tag);
                    throw new IllegalArgumentException(sb2.toString());
                case 3:
                    if ("layout/anytime_tablet_social_horizontal_users_view_0".equals(tag)) {
                        return new AnytimeTabletSocialHorizontalUsersViewBindingImpl(r4, view);
                    }
                    StringBuilder sb3 = new StringBuilder("The tag for anytime_tablet_social_horizontal_users_view is invalid. Received: ");
                    sb3.append(tag);
                    throw new IllegalArgumentException(sb3.toString());
                case 4:
                    if ("layout/anytime_tablet_social_list_header_v2_0".equals(tag)) {
                        return new AnytimeTabletSocialListHeaderV2BindingImpl(r4, view);
                    }
                    StringBuilder sb4 = new StringBuilder("The tag for anytime_tablet_social_list_header_v2 is invalid. Received: ");
                    sb4.append(tag);
                    throw new IllegalArgumentException(sb4.toString());
                case 5:
                    if ("layout/anytime_tablet_social_list_item_v2_0".equals(tag)) {
                        return new AnytimeTabletSocialListItemV2BindingImpl(r4, view);
                    }
                    StringBuilder sb5 = new StringBuilder("The tag for anytime_tablet_social_list_item_v2 is invalid. Received: ");
                    sb5.append(tag);
                    throw new IllegalArgumentException(sb5.toString());
                case 6:
                    if ("layout/anytime_tablet_social_list_null_row_v2_0".equals(tag)) {
                        return new AnytimeTabletSocialListNullRowV2BindingImpl(r4, view);
                    }
                    StringBuilder sb6 = new StringBuilder("The tag for anytime_tablet_social_list_null_row_v2 is invalid. Received: ");
                    sb6.append(tag);
                    throw new IllegalArgumentException(sb6.toString());
                case 7:
                    if ("layout/anytime_tablet_social_offline_0".equals(tag)) {
                        return new AnytimeTabletSocialOfflineBindingImpl(r4, view);
                    }
                    StringBuilder sb7 = new StringBuilder("The tag for anytime_tablet_social_offline is invalid. Received: ");
                    sb7.append(tag);
                    throw new IllegalArgumentException(sb7.toString());
                case 8:
                    if ("layout/anytime_tablet_social_party_footer_0".equals(tag)) {
                        return new AnytimeTabletSocialPartyFooterBindingImpl(r4, view);
                    }
                    StringBuilder sb8 = new StringBuilder("The tag for anytime_tablet_social_party_footer is invalid. Received: ");
                    sb8.append(tag);
                    throw new IllegalArgumentException(sb8.toString());
                case 9:
                    if ("layout/anytime_tablet_social_party_header_0".equals(tag)) {
                        return new AnytimeTabletSocialPartyHeaderBindingImpl(r4, view);
                    }
                    StringBuilder sb9 = new StringBuilder("The tag for anytime_tablet_social_party_header is invalid. Received: ");
                    sb9.append(tag);
                    throw new IllegalArgumentException(sb9.toString());
                case 10:
                    if ("layout/anytime_tablet_social_user_card_0".equals(tag)) {
                        return new AnytimeTabletSocialUserCardBindingImpl(r4, view);
                    }
                    StringBuilder sb10 = new StringBuilder("The tag for anytime_tablet_social_user_card is invalid. Received: ");
                    sb10.append(tag);
                    throw new IllegalArgumentException(sb10.toString());
                case 11:
                    if ("layout/anytime_tablet_social_view_v2_0".equals(tag)) {
                        return new AnytimeTabletSocialViewV2BindingImpl(r4, view);
                    }
                    StringBuilder sb11 = new StringBuilder("The tag for anytime_tablet_social_view_v2 is invalid. Received: ");
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
