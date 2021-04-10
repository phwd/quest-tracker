package com.oculus.common.ocui;

import X.AbstractC003408r;
import X.AnonymousClass1uS;
import X.AnonymousClass1uW;
import X.AnonymousClass2Q8;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.core.app.NotificationCompat$WearableExtender;
import com.oculus.common.ocui.databinding.OcbadgeBindingImpl;
import com.oculus.common.ocui.databinding.OccontextMenuItemBindingImpl;
import com.oculus.common.ocui.databinding.OcdropdownBindingImpl;
import com.oculus.common.ocui.databinding.OcemptyLayoutBindingImpl;
import com.oculus.common.ocui.databinding.OcnotchedSliderBindingImpl;
import com.oculus.common.ocui.databinding.OcplaceholderGlintBindingImpl;
import com.oculus.common.ocui.databinding.OcprogressbarBindingImpl;
import com.oculus.common.ocui.databinding.OcselectBindingImpl;
import com.oculus.common.ocui.databinding.OcselectIconOnlyBindingImpl;
import com.oculus.common.ocui.databinding.OcsidenavBadgeBindingImpl;
import com.oculus.common.ocui.databinding.OcsidenavBindingImpl;
import com.oculus.common.ocui.databinding.OcsidenavItemBindingImpl;
import com.oculus.common.ocui.databinding.OctileButtonBindingImpl;
import com.oculus.common.ocui.databinding.OctooltipBindingImpl;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogProgressIndicator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends AnonymousClass1uS {
    public static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    public static final int LAYOUT_OCBADGE = 1;
    public static final int LAYOUT_OCCONTEXTMENUITEM = 2;
    public static final int LAYOUT_OCDROPDOWN = 3;
    public static final int LAYOUT_OCEMPTYLAYOUT = 4;
    public static final int LAYOUT_OCNOTCHEDSLIDER = 5;
    public static final int LAYOUT_OCPLACEHOLDERGLINT = 6;
    public static final int LAYOUT_OCPROGRESSBAR = 7;
    public static final int LAYOUT_OCSELECT = 8;
    public static final int LAYOUT_OCSELECTICONONLY = 9;
    public static final int LAYOUT_OCSIDENAV = 10;
    public static final int LAYOUT_OCSIDENAVBADGE = 11;
    public static final int LAYOUT_OCSIDENAVITEM = 12;
    public static final int LAYOUT_OCTILEBUTTON = 13;
    public static final int LAYOUT_OCTOOLTIP = 14;

    public static class InnerBrLookup {
        public static final SparseArray<String> sKeys;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(20);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "activeDrawable");
            sparseArray.put(2, "activeIndicator");
            sparseArray.put(3, NotificationCompat$WearableExtender.KEY_BACKGROUND);
            sparseArray.put(4, "badgeCount");
            sparseArray.put(5, "buttonText");
            sparseArray.put(6, "ctaText");
            sparseArray.put(7, "enabled");
            sparseArray.put(8, "header");
            sparseArray.put(9, "inactiveDrawable");
            sparseArray.put(10, "isActive");
            sparseArray.put(11, "label");
            sparseArray.put(12, DialogProgressIndicator.DIALOG_PROGRESS_INDICATOR_PROGRESS_KEY);
            sparseArray.put(13, "showProgressPercentage");
            sparseArray.put(14, "sideNavItem");
            sparseArray.put(15, "splash");
            sparseArray.put(16, "subtitle");
            sparseArray.put(17, "title");
            sparseArray.put(18, "titleIcon");
            sparseArray.put(19, "viewModel");
        }
    }

    public static class InnerLayoutIdLookup {
        public static final HashMap<String, Integer> sKeys;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(14);
            sKeys = hashMap;
            hashMap.put("layout/ocbadge_0", Integer.valueOf((int) R.layout.ocbadge));
            HashMap<String, Integer> hashMap2 = sKeys;
            hashMap2.put("layout/occontext_menu_item_0", Integer.valueOf((int) R.layout.occontext_menu_item));
            hashMap2.put("layout/ocdropdown_0", Integer.valueOf((int) R.layout.ocdropdown));
            hashMap2.put("layout/ocempty_layout_0", Integer.valueOf((int) R.layout.ocempty_layout));
            hashMap2.put("layout/ocnotched_slider_0", Integer.valueOf((int) R.layout.ocnotched_slider));
            hashMap2.put("layout/ocplaceholder_glint_0", Integer.valueOf((int) R.layout.ocplaceholder_glint));
            hashMap2.put("layout/ocprogressbar_0", Integer.valueOf((int) R.layout.ocprogressbar));
            hashMap2.put("layout/ocselect_0", Integer.valueOf((int) R.layout.ocselect));
            hashMap2.put("layout/ocselect_icon_only_0", Integer.valueOf((int) R.layout.ocselect_icon_only));
            hashMap2.put("layout/ocsidenav_0", Integer.valueOf((int) R.layout.ocsidenav));
            hashMap2.put("layout/ocsidenav_badge_0", Integer.valueOf((int) R.layout.ocsidenav_badge));
            hashMap2.put("layout/ocsidenav_item_0", Integer.valueOf((int) R.layout.ocsidenav_item));
            hashMap2.put("layout/octile_button_0", Integer.valueOf((int) R.layout.octile_button));
            hashMap2.put("layout/octooltip_0", Integer.valueOf((int) R.layout.octooltip));
        }
    }

    @Override // X.AnonymousClass1uS
    public List<AnonymousClass1uS> collectDependencies() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new AnonymousClass2Q8());
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
        sparseIntArray.put(R.layout.ocbadge, 1);
        sparseIntArray.put(R.layout.occontext_menu_item, 2);
        sparseIntArray.put(R.layout.ocdropdown, 3);
        sparseIntArray.put(R.layout.ocempty_layout, 4);
        sparseIntArray.put(R.layout.ocnotched_slider, 5);
        sparseIntArray.put(R.layout.ocplaceholder_glint, 6);
        sparseIntArray.put(R.layout.ocprogressbar, 7);
        sparseIntArray.put(R.layout.ocselect, 8);
        sparseIntArray.put(R.layout.ocselect_icon_only, 9);
        sparseIntArray.put(R.layout.ocsidenav, 10);
        sparseIntArray.put(R.layout.ocsidenav_badge, 11);
        sparseIntArray.put(R.layout.ocsidenav_item, 12);
        sparseIntArray.put(R.layout.octile_button, 13);
        sparseIntArray.put(R.layout.octooltip, 14);
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
                    if ("layout/ocbadge_0".equals(tag)) {
                        return new OcbadgeBindingImpl(r4, view);
                    }
                    StringBuilder sb = new StringBuilder("The tag for ocbadge is invalid. Received: ");
                    sb.append(tag);
                    throw new IllegalArgumentException(sb.toString());
                case 2:
                    if ("layout/occontext_menu_item_0".equals(tag)) {
                        return new OccontextMenuItemBindingImpl(r4, view);
                    }
                    StringBuilder sb2 = new StringBuilder("The tag for occontext_menu_item is invalid. Received: ");
                    sb2.append(tag);
                    throw new IllegalArgumentException(sb2.toString());
                case 3:
                    if ("layout/ocdropdown_0".equals(tag)) {
                        return new OcdropdownBindingImpl(r4, view);
                    }
                    StringBuilder sb3 = new StringBuilder("The tag for ocdropdown is invalid. Received: ");
                    sb3.append(tag);
                    throw new IllegalArgumentException(sb3.toString());
                case 4:
                    if ("layout/ocempty_layout_0".equals(tag)) {
                        return new OcemptyLayoutBindingImpl(r4, view);
                    }
                    StringBuilder sb4 = new StringBuilder("The tag for ocempty_layout is invalid. Received: ");
                    sb4.append(tag);
                    throw new IllegalArgumentException(sb4.toString());
                case 5:
                    if ("layout/ocnotched_slider_0".equals(tag)) {
                        return new OcnotchedSliderBindingImpl(r4, new View[]{view});
                    }
                    StringBuilder sb5 = new StringBuilder("The tag for ocnotched_slider is invalid. Received: ");
                    sb5.append(tag);
                    throw new IllegalArgumentException(sb5.toString());
                case 6:
                    if ("layout/ocplaceholder_glint_0".equals(tag)) {
                        return new OcplaceholderGlintBindingImpl(r4, view);
                    }
                    StringBuilder sb6 = new StringBuilder("The tag for ocplaceholder_glint is invalid. Received: ");
                    sb6.append(tag);
                    throw new IllegalArgumentException(sb6.toString());
                case 7:
                    if ("layout/ocprogressbar_0".equals(tag)) {
                        return new OcprogressbarBindingImpl(r4, view);
                    }
                    StringBuilder sb7 = new StringBuilder("The tag for ocprogressbar is invalid. Received: ");
                    sb7.append(tag);
                    throw new IllegalArgumentException(sb7.toString());
                case 8:
                    if ("layout/ocselect_0".equals(tag)) {
                        return new OcselectBindingImpl(r4, view);
                    }
                    StringBuilder sb8 = new StringBuilder("The tag for ocselect is invalid. Received: ");
                    sb8.append(tag);
                    throw new IllegalArgumentException(sb8.toString());
                case 9:
                    if ("layout/ocselect_icon_only_0".equals(tag)) {
                        return new OcselectIconOnlyBindingImpl(r4, view);
                    }
                    StringBuilder sb9 = new StringBuilder("The tag for ocselect_icon_only is invalid. Received: ");
                    sb9.append(tag);
                    throw new IllegalArgumentException(sb9.toString());
                case 10:
                    if ("layout/ocsidenav_0".equals(tag)) {
                        return new OcsidenavBindingImpl(r4, view);
                    }
                    StringBuilder sb10 = new StringBuilder("The tag for ocsidenav is invalid. Received: ");
                    sb10.append(tag);
                    throw new IllegalArgumentException(sb10.toString());
                case 11:
                    if ("layout/ocsidenav_badge_0".equals(tag)) {
                        return new OcsidenavBadgeBindingImpl(r4, view);
                    }
                    StringBuilder sb11 = new StringBuilder("The tag for ocsidenav_badge is invalid. Received: ");
                    sb11.append(tag);
                    throw new IllegalArgumentException(sb11.toString());
                case 12:
                    if ("layout/ocsidenav_item_0".equals(tag)) {
                        return new OcsidenavItemBindingImpl(r4, view);
                    }
                    StringBuilder sb12 = new StringBuilder("The tag for ocsidenav_item is invalid. Received: ");
                    sb12.append(tag);
                    throw new IllegalArgumentException(sb12.toString());
                case 13:
                    if ("layout/octile_button_0".equals(tag)) {
                        return new OctileButtonBindingImpl(r4, view);
                    }
                    StringBuilder sb13 = new StringBuilder("The tag for octile_button is invalid. Received: ");
                    sb13.append(tag);
                    throw new IllegalArgumentException(sb13.toString());
                case 14:
                    if ("layout/octooltip_0".equals(tag)) {
                        return new OctooltipBindingImpl(r4, view);
                    }
                    StringBuilder sb14 = new StringBuilder("The tag for octooltip is invalid. Received: ");
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
    public AnonymousClass1uW getDataBinder(AbstractC003408r r5, View[] viewArr, int i) {
        int i2;
        if (!(viewArr == null || viewArr.length == 0 || (i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i)) <= 0)) {
            Object tag = viewArr[0].getTag();
            if (tag == null) {
                throw new RuntimeException("view must have a tag");
            } else if (i2 == 5) {
                if ("layout/ocnotched_slider_0".equals(tag)) {
                    return new OcnotchedSliderBindingImpl(r5, viewArr);
                }
                StringBuilder sb = new StringBuilder("The tag for ocnotched_slider is invalid. Received: ");
                sb.append(tag);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        return null;
    }
}
