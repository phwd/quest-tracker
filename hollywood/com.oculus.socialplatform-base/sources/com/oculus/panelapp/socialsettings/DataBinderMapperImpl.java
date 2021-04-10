package com.oculus.panelapp.socialsettings;

import X.AbstractC003408r;
import X.AnonymousClass1uS;
import X.AnonymousClass1uW;
import X.AnonymousClass2Q8;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.core.app.NotificationCompat$WearableExtender;
import com.oculus.panelapp.socialsettings.databinding.SocialSettingsActiveStatusBindingImpl;
import com.oculus.panelapp.socialsettings.databinding.SocialSettingsMessengerAccountBindingImpl;
import com.oculus.panelapp.socialsettings.databinding.SocialSettingsNotificationsBindingImpl;
import com.oculus.panelapp.socialsettings.databinding.SocialSettingsSideNavBindingImpl;
import com.oculus.panelapp.socialsettings.databinding.SocialSettingsSideNavItemBindingImpl;
import com.oculus.panelapp.socialsettings.databinding.SocialSettingsTabletMainBindingImpl;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogProgressIndicator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends AnonymousClass1uS {
    public static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    public static final int LAYOUT_SOCIALSETTINGSACTIVESTATUS = 1;
    public static final int LAYOUT_SOCIALSETTINGSMESSENGERACCOUNT = 2;
    public static final int LAYOUT_SOCIALSETTINGSNOTIFICATIONS = 3;
    public static final int LAYOUT_SOCIALSETTINGSSIDENAV = 4;
    public static final int LAYOUT_SOCIALSETTINGSSIDENAVITEM = 5;
    public static final int LAYOUT_SOCIALSETTINGSTABLETMAIN = 6;

    public static class InnerBrLookup {
        public static final SparseArray<String> sKeys;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(33);
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
            sparseArray.put(27, "selectedSetting");
            sparseArray.put(28, "isDeviceLockPatternSet");
            sparseArray.put(29, "signOutButtonEnabled");
            sparseArray.put(30, "userName");
            sparseArray.put(31, "isLoaded");
        }
    }

    public static class InnerLayoutIdLookup {
        public static final HashMap<String, Integer> sKeys;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(6);
            sKeys = hashMap;
            hashMap.put("layout/social_settings_active_status_0", Integer.valueOf((int) R.layout.social_settings_active_status));
            HashMap<String, Integer> hashMap2 = sKeys;
            hashMap2.put("layout/social_settings_messenger_account_0", Integer.valueOf((int) R.layout.social_settings_messenger_account));
            hashMap2.put("layout/social_settings_notifications_0", Integer.valueOf((int) R.layout.social_settings_notifications));
            hashMap2.put("layout/social_settings_side_nav_0", Integer.valueOf((int) R.layout.social_settings_side_nav));
            hashMap2.put("layout/social_settings_side_nav_item_0", Integer.valueOf((int) R.layout.social_settings_side_nav_item));
            hashMap2.put("layout/social_settings_tablet_main_0", Integer.valueOf((int) R.layout.social_settings_tablet_main));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(6);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.social_settings_active_status, 1);
        sparseIntArray.put(R.layout.social_settings_messenger_account, 2);
        sparseIntArray.put(R.layout.social_settings_notifications, 3);
        sparseIntArray.put(R.layout.social_settings_side_nav, 4);
        sparseIntArray.put(R.layout.social_settings_side_nav_item, 5);
        sparseIntArray.put(R.layout.social_settings_tablet_main, 6);
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
                    if ("layout/social_settings_active_status_0".equals(tag)) {
                        return new SocialSettingsActiveStatusBindingImpl(r4, view);
                    }
                    StringBuilder sb = new StringBuilder("The tag for social_settings_active_status is invalid. Received: ");
                    sb.append(tag);
                    throw new IllegalArgumentException(sb.toString());
                case 2:
                    if ("layout/social_settings_messenger_account_0".equals(tag)) {
                        return new SocialSettingsMessengerAccountBindingImpl(r4, view);
                    }
                    StringBuilder sb2 = new StringBuilder("The tag for social_settings_messenger_account is invalid. Received: ");
                    sb2.append(tag);
                    throw new IllegalArgumentException(sb2.toString());
                case 3:
                    if ("layout/social_settings_notifications_0".equals(tag)) {
                        return new SocialSettingsNotificationsBindingImpl(r4, view);
                    }
                    StringBuilder sb3 = new StringBuilder("The tag for social_settings_notifications is invalid. Received: ");
                    sb3.append(tag);
                    throw new IllegalArgumentException(sb3.toString());
                case 4:
                    if ("layout/social_settings_side_nav_0".equals(tag)) {
                        return new SocialSettingsSideNavBindingImpl(r4, view);
                    }
                    StringBuilder sb4 = new StringBuilder("The tag for social_settings_side_nav is invalid. Received: ");
                    sb4.append(tag);
                    throw new IllegalArgumentException(sb4.toString());
                case 5:
                    if ("layout/social_settings_side_nav_item_0".equals(tag)) {
                        return new SocialSettingsSideNavItemBindingImpl(r4, view);
                    }
                    StringBuilder sb5 = new StringBuilder("The tag for social_settings_side_nav_item is invalid. Received: ");
                    sb5.append(tag);
                    throw new IllegalArgumentException(sb5.toString());
                case 6:
                    if ("layout/social_settings_tablet_main_0".equals(tag)) {
                        return new SocialSettingsTabletMainBindingImpl(r4, view);
                    }
                    StringBuilder sb6 = new StringBuilder("The tag for social_settings_tablet_main is invalid. Received: ");
                    sb6.append(tag);
                    throw new IllegalArgumentException(sb6.toString());
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
