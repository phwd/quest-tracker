package com.oculus.panelapp.socialreauth;

import X.AbstractC003408r;
import X.AnonymousClass1uS;
import X.AnonymousClass1uW;
import X.AnonymousClass2Q8;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.core.app.NotificationCompat$WearableExtender;
import com.oculus.panelapp.socialreauth.databinding.SocialReauthTabletMainBindingImpl;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogListItem;
import com.oculus.systemdialog.DialogProgressIndicator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends AnonymousClass1uS {
    public static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    public static final int LAYOUT_SOCIALREAUTHTABLETMAIN = 1;

    public static class InnerBrLookup {
        public static final SparseArray<String> sKeys;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(40);
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
            sparseArray.put(27, "continueButtonElementsAlpha");
            sparseArray.put(28, "headerText");
            sparseArray.put(29, "footerText");
            sparseArray.put(30, "passwordInputType");
            sparseArray.put(31, "bodyVisibility");
            sparseArray.put(32, "continueButtonText");
            sparseArray.put(33, "passwordVisibilityBtnBackground");
            sparseArray.put(34, "passwordInputEnabled");
            sparseArray.put(35, "continueButtonEnabled");
            sparseArray.put(36, DialogListItem.DIALOG_LIST_ITEM_BODY_TEXT_KEY);
            sparseArray.put(37, "errorBodyVisibility");
            sparseArray.put(38, "errorBodyText");
        }
    }

    public static class InnerLayoutIdLookup {
        public static final HashMap<String, Integer> sKeys;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(1);
            sKeys = hashMap;
            hashMap.put("layout/social_reauth_tablet_main_0", Integer.valueOf((int) R.layout.social_reauth_tablet_main));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(1);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.social_reauth_tablet_main, 1);
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
        if (tag == null) {
            throw new RuntimeException("view must have a tag");
        } else if (i2 != 1) {
            return null;
        } else {
            if ("layout/social_reauth_tablet_main_0".equals(tag)) {
                return new SocialReauthTabletMainBindingImpl(r4, view);
            }
            StringBuilder sb = new StringBuilder("The tag for social_reauth_tablet_main is invalid. Received: ");
            sb.append(tag);
            throw new IllegalArgumentException(sb.toString());
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
