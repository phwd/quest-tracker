package com.oculus.panelapp.people;

import X.AbstractC003408r;
import X.AnonymousClass1uS;
import X.AnonymousClass1uW;
import X.AnonymousClass2Q8;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.core.app.NotificationCompat$WearableExtender;
import com.oculus.panelapp.people.databinding.PeopleNavBarBindingImpl;
import com.oculus.panelapp.people.databinding.PeopleQuickMessagePopupBindingImpl;
import com.oculus.panelapp.people.databinding.PeopleSearchFieldBindingImpl;
import com.oculus.panelapp.people.databinding.PeopleSearchResultHeaderBindingImpl;
import com.oculus.panelapp.people.databinding.PeopleSearchTopBarBindingImpl;
import com.oculus.panelapp.people.databinding.PeopleSearchViewBindingImpl;
import com.oculus.panelapp.people.databinding.PeopleTabletEmptyBindingImpl;
import com.oculus.panelapp.people.databinding.PeopleTabletMainViewBindingImpl;
import com.oculus.panelapp.people.databinding.PeopleTabletPeopleHorizontalUsersViewBindingImpl;
import com.oculus.panelapp.people.databinding.PeopleTabletPeopleListItemBindingImpl;
import com.oculus.panelapp.people.databinding.PeopleTabletPeopleUserCardBindingImpl;
import com.oculus.panelapp.people.databinding.PeopleTabletTopBarBindingImpl;
import com.oculus.panelapp.people.databinding.PeopleTabletUpsellCardBindingImpl;
import com.oculus.panelapp.people.databinding.PeopleViewBindingImpl;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogListItem;
import com.oculus.systemdialog.DialogProgressIndicator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends AnonymousClass1uS {
    public static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    public static final int LAYOUT_PEOPLENAVBAR = 1;
    public static final int LAYOUT_PEOPLEQUICKMESSAGEPOPUP = 2;
    public static final int LAYOUT_PEOPLESEARCHFIELD = 3;
    public static final int LAYOUT_PEOPLESEARCHRESULTHEADER = 4;
    public static final int LAYOUT_PEOPLESEARCHTOPBAR = 5;
    public static final int LAYOUT_PEOPLESEARCHVIEW = 6;
    public static final int LAYOUT_PEOPLETABLETEMPTY = 7;
    public static final int LAYOUT_PEOPLETABLETMAINVIEW = 8;
    public static final int LAYOUT_PEOPLETABLETPEOPLEHORIZONTALUSERSVIEW = 9;
    public static final int LAYOUT_PEOPLETABLETPEOPLELISTITEM = 10;
    public static final int LAYOUT_PEOPLETABLETPEOPLEUSERCARD = 11;
    public static final int LAYOUT_PEOPLETABLETTOPBAR = 12;
    public static final int LAYOUT_PEOPLETABLETUPSELLCARD = 13;
    public static final int LAYOUT_PEOPLEVIEW = 14;

    public static class InnerBrLookup {
        public static final SparseArray<String> sKeys;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(51);
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
            sparseArray.put(27, "presenceIconVisibility");
            sparseArray.put(28, "subtitleText");
            sparseArray.put(29, "showOnlineColor");
            sparseArray.put(30, "textGravity");
            sparseArray.put(31, "leftCtaIcon");
            sparseArray.put(32, "searchIconVisibility");
            sparseArray.put(33, "rightCtaIcon");
            sparseArray.put(34, "onlineSubtitleColor");
            sparseArray.put(35, DialogListItem.DIALOG_LIST_ITEM_TITLE_TEXT_KEY);
            sparseArray.put(36, "showSearch");
            sparseArray.put(37, "titleTextSize");
            sparseArray.put(38, "ctaIcon");
            sparseArray.put(39, "isSubtitleVisible");
            sparseArray.put(40, "isSuccess");
            sparseArray.put(41, "image");
            sparseArray.put(42, "ctaBackground");
            sparseArray.put(43, "lastActiveTime");
            sparseArray.put(44, "nameText");
            sparseArray.put(45, "isLoading");
            sparseArray.put(46, "friendsButtonText");
            sparseArray.put(47, "usernameText");
            sparseArray.put(48, "presenceIcon");
            sparseArray.put(49, "subtitleRowVisibility");
        }
    }

    public static class InnerLayoutIdLookup {
        public static final HashMap<String, Integer> sKeys;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(14);
            sKeys = hashMap;
            hashMap.put("layout/people_nav_bar_0", Integer.valueOf((int) R.layout.people_nav_bar));
            HashMap<String, Integer> hashMap2 = sKeys;
            hashMap2.put("layout/people_quick_message_popup_0", Integer.valueOf((int) R.layout.people_quick_message_popup));
            hashMap2.put("layout/people_search_field_0", Integer.valueOf((int) R.layout.people_search_field));
            hashMap2.put("layout/people_search_result_header_0", Integer.valueOf((int) R.layout.people_search_result_header));
            hashMap2.put("layout/people_search_top_bar_0", Integer.valueOf((int) R.layout.people_search_top_bar));
            hashMap2.put("layout/people_search_view_0", Integer.valueOf((int) R.layout.people_search_view));
            hashMap2.put("layout/people_tablet_empty_0", Integer.valueOf((int) R.layout.people_tablet_empty));
            hashMap2.put("layout/people_tablet_main_view_0", Integer.valueOf((int) R.layout.people_tablet_main_view));
            hashMap2.put("layout/people_tablet_people_horizontal_users_view_0", Integer.valueOf((int) R.layout.people_tablet_people_horizontal_users_view));
            hashMap2.put("layout/people_tablet_people_list_item_0", Integer.valueOf((int) R.layout.people_tablet_people_list_item));
            hashMap2.put("layout/people_tablet_people_user_card_0", Integer.valueOf((int) R.layout.people_tablet_people_user_card));
            hashMap2.put("layout/people_tablet_top_bar_0", Integer.valueOf((int) R.layout.people_tablet_top_bar));
            hashMap2.put("layout/people_tablet_upsell_card_0", Integer.valueOf((int) R.layout.people_tablet_upsell_card));
            hashMap2.put("layout/people_view_0", Integer.valueOf((int) R.layout.people_view));
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
        SparseIntArray sparseIntArray = new SparseIntArray(14);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.people_nav_bar, 1);
        sparseIntArray.put(R.layout.people_quick_message_popup, 2);
        sparseIntArray.put(R.layout.people_search_field, 3);
        sparseIntArray.put(R.layout.people_search_result_header, 4);
        sparseIntArray.put(R.layout.people_search_top_bar, 5);
        sparseIntArray.put(R.layout.people_search_view, 6);
        sparseIntArray.put(R.layout.people_tablet_empty, 7);
        sparseIntArray.put(R.layout.people_tablet_main_view, 8);
        sparseIntArray.put(R.layout.people_tablet_people_horizontal_users_view, 9);
        sparseIntArray.put(R.layout.people_tablet_people_list_item, 10);
        sparseIntArray.put(R.layout.people_tablet_people_user_card, 11);
        sparseIntArray.put(R.layout.people_tablet_top_bar, 12);
        sparseIntArray.put(R.layout.people_tablet_upsell_card, 13);
        sparseIntArray.put(R.layout.people_view, 14);
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
                    if ("layout/people_nav_bar_0".equals(tag)) {
                        return new PeopleNavBarBindingImpl(r4, view);
                    }
                    StringBuilder sb = new StringBuilder("The tag for people_nav_bar is invalid. Received: ");
                    sb.append(tag);
                    throw new IllegalArgumentException(sb.toString());
                case 2:
                    if ("layout/people_quick_message_popup_0".equals(tag)) {
                        return new PeopleQuickMessagePopupBindingImpl(r4, view);
                    }
                    StringBuilder sb2 = new StringBuilder("The tag for people_quick_message_popup is invalid. Received: ");
                    sb2.append(tag);
                    throw new IllegalArgumentException(sb2.toString());
                case 3:
                    if ("layout/people_search_field_0".equals(tag)) {
                        return new PeopleSearchFieldBindingImpl(r4, view);
                    }
                    StringBuilder sb3 = new StringBuilder("The tag for people_search_field is invalid. Received: ");
                    sb3.append(tag);
                    throw new IllegalArgumentException(sb3.toString());
                case 4:
                    if ("layout/people_search_result_header_0".equals(tag)) {
                        return new PeopleSearchResultHeaderBindingImpl(r4, view);
                    }
                    StringBuilder sb4 = new StringBuilder("The tag for people_search_result_header is invalid. Received: ");
                    sb4.append(tag);
                    throw new IllegalArgumentException(sb4.toString());
                case 5:
                    if ("layout/people_search_top_bar_0".equals(tag)) {
                        return new PeopleSearchTopBarBindingImpl(r4, view);
                    }
                    StringBuilder sb5 = new StringBuilder("The tag for people_search_top_bar is invalid. Received: ");
                    sb5.append(tag);
                    throw new IllegalArgumentException(sb5.toString());
                case 6:
                    if ("layout/people_search_view_0".equals(tag)) {
                        return new PeopleSearchViewBindingImpl(r4, view);
                    }
                    StringBuilder sb6 = new StringBuilder("The tag for people_search_view is invalid. Received: ");
                    sb6.append(tag);
                    throw new IllegalArgumentException(sb6.toString());
                case 7:
                    if ("layout/people_tablet_empty_0".equals(tag)) {
                        return new PeopleTabletEmptyBindingImpl(r4, view);
                    }
                    StringBuilder sb7 = new StringBuilder("The tag for people_tablet_empty is invalid. Received: ");
                    sb7.append(tag);
                    throw new IllegalArgumentException(sb7.toString());
                case 8:
                    if ("layout/people_tablet_main_view_0".equals(tag)) {
                        return new PeopleTabletMainViewBindingImpl(r4, view);
                    }
                    StringBuilder sb8 = new StringBuilder("The tag for people_tablet_main_view is invalid. Received: ");
                    sb8.append(tag);
                    throw new IllegalArgumentException(sb8.toString());
                case 9:
                    if ("layout/people_tablet_people_horizontal_users_view_0".equals(tag)) {
                        return new PeopleTabletPeopleHorizontalUsersViewBindingImpl(r4, view);
                    }
                    StringBuilder sb9 = new StringBuilder("The tag for people_tablet_people_horizontal_users_view is invalid. Received: ");
                    sb9.append(tag);
                    throw new IllegalArgumentException(sb9.toString());
                case 10:
                    if ("layout/people_tablet_people_list_item_0".equals(tag)) {
                        return new PeopleTabletPeopleListItemBindingImpl(r4, view);
                    }
                    StringBuilder sb10 = new StringBuilder("The tag for people_tablet_people_list_item is invalid. Received: ");
                    sb10.append(tag);
                    throw new IllegalArgumentException(sb10.toString());
                case 11:
                    if ("layout/people_tablet_people_user_card_0".equals(tag)) {
                        return new PeopleTabletPeopleUserCardBindingImpl(r4, view);
                    }
                    StringBuilder sb11 = new StringBuilder("The tag for people_tablet_people_user_card is invalid. Received: ");
                    sb11.append(tag);
                    throw new IllegalArgumentException(sb11.toString());
                case 12:
                    if ("layout/people_tablet_top_bar_0".equals(tag)) {
                        return new PeopleTabletTopBarBindingImpl(r4, view);
                    }
                    StringBuilder sb12 = new StringBuilder("The tag for people_tablet_top_bar is invalid. Received: ");
                    sb12.append(tag);
                    throw new IllegalArgumentException(sb12.toString());
                case 13:
                    if ("layout/people_tablet_upsell_card_0".equals(tag)) {
                        return new PeopleTabletUpsellCardBindingImpl(r4, view);
                    }
                    StringBuilder sb13 = new StringBuilder("The tag for people_tablet_upsell_card is invalid. Received: ");
                    sb13.append(tag);
                    throw new IllegalArgumentException(sb13.toString());
                case 14:
                    if ("layout/people_view_0".equals(tag)) {
                        return new PeopleViewBindingImpl(r4, view);
                    }
                    StringBuilder sb14 = new StringBuilder("The tag for people_view is invalid. Received: ");
                    sb14.append(tag);
                    throw new IllegalArgumentException(sb14.toString());
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
