package com.oculus.common.ocui;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
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
import com.oculus.userserver.api.sharing.SharingManagerContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(13);
    private static final int LAYOUT_OCBADGE = 1;
    private static final int LAYOUT_OCCONTEXTMENUITEM = 2;
    private static final int LAYOUT_OCDROPDOWN = 3;
    private static final int LAYOUT_OCEMPTYLAYOUT = 4;
    private static final int LAYOUT_OCNOTCHEDSLIDER = 5;
    private static final int LAYOUT_OCPLACEHOLDERGLINT = 6;
    private static final int LAYOUT_OCPROGRESSBAR = 7;
    private static final int LAYOUT_OCSELECT = 8;
    private static final int LAYOUT_OCSELECTICONONLY = 9;
    private static final int LAYOUT_OCSIDENAV = 10;
    private static final int LAYOUT_OCSIDENAVBADGE = 11;
    private static final int LAYOUT_OCSIDENAVITEM = 12;
    private static final int LAYOUT_OCTILEBUTTON = 13;

    static {
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ocbadge, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.occontext_menu_item, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ocdropdown, 3);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ocempty_layout, 4);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ocnotched_slider, 5);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ocplaceholder_glint, 6);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ocprogressbar, 7);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ocselect, 8);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ocselect_icon_only, 9);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ocsidenav, 10);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ocsidenav_badge, 11);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ocsidenav_item, 12);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.octile_button, 13);
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
                    if ("layout/ocbadge_0".equals(tag)) {
                        return new OcbadgeBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for ocbadge is invalid. Received: " + tag);
                case 2:
                    if ("layout/occontext_menu_item_0".equals(tag)) {
                        return new OccontextMenuItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for occontext_menu_item is invalid. Received: " + tag);
                case 3:
                    if ("layout/ocdropdown_0".equals(tag)) {
                        return new OcdropdownBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for ocdropdown is invalid. Received: " + tag);
                case 4:
                    if ("layout/ocempty_layout_0".equals(tag)) {
                        return new OcemptyLayoutBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for ocempty_layout is invalid. Received: " + tag);
                case 5:
                    if ("layout/ocnotched_slider_0".equals(tag)) {
                        return new OcnotchedSliderBindingImpl(dataBindingComponent, new View[]{view});
                    }
                    throw new IllegalArgumentException("The tag for ocnotched_slider is invalid. Received: " + tag);
                case 6:
                    if ("layout/ocplaceholder_glint_0".equals(tag)) {
                        return new OcplaceholderGlintBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for ocplaceholder_glint is invalid. Received: " + tag);
                case 7:
                    if ("layout/ocprogressbar_0".equals(tag)) {
                        return new OcprogressbarBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for ocprogressbar is invalid. Received: " + tag);
                case 8:
                    if ("layout/ocselect_0".equals(tag)) {
                        return new OcselectBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for ocselect is invalid. Received: " + tag);
                case 9:
                    if ("layout/ocselect_icon_only_0".equals(tag)) {
                        return new OcselectIconOnlyBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for ocselect_icon_only is invalid. Received: " + tag);
                case 10:
                    if ("layout/ocsidenav_0".equals(tag)) {
                        return new OcsidenavBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for ocsidenav is invalid. Received: " + tag);
                case 11:
                    if ("layout/ocsidenav_badge_0".equals(tag)) {
                        return new OcsidenavBadgeBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for ocsidenav_badge is invalid. Received: " + tag);
                case 12:
                    if ("layout/ocsidenav_item_0".equals(tag)) {
                        return new OcsidenavItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for ocsidenav_item is invalid. Received: " + tag);
                case 13:
                    if ("layout/octile_button_0".equals(tag)) {
                        return new OctileButtonBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for octile_button is invalid. Received: " + tag);
                default:
                    return null;
            }
        } else {
            throw new RuntimeException("view must have a tag");
        }
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        int i2;
        if (!(viewArr == null || viewArr.length == 0 || (i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i)) <= 0)) {
            Object tag = viewArr[0].getTag();
            if (tag == null) {
                throw new RuntimeException("view must have a tag");
            } else if (i2 == 5) {
                if ("layout/ocnotched_slider_0".equals(tag)) {
                    return new OcnotchedSliderBindingImpl(dataBindingComponent, viewArr);
                }
                throw new IllegalArgumentException("The tag for ocnotched_slider is invalid. Received: " + tag);
            }
        }
        return null;
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
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>(20);

        private InnerBrLookup() {
        }

        static {
            sKeys.put(0, "_all");
            sKeys.put(1, "activeIndicator");
            sKeys.put(2, "buttonText");
            sKeys.put(3, "inactiveDrawable");
            sKeys.put(4, "sideNavItem");
            sKeys.put(5, "ctaText");
            sKeys.put(6, "title");
            sKeys.put(7, "badgeCount");
            sKeys.put(8, "isActive");
            sKeys.put(9, SharingManagerContract.ARGUMENT_IS_ENABLED);
            sKeys.put(10, "showProgressPercentage");
            sKeys.put(11, "activeDrawable");
            sKeys.put(12, "background");
            sKeys.put(13, "subtitle");
            sKeys.put(14, AssistantDialogContract.MultiselectionDialog.Section.HEADER);
            sKeys.put(15, "viewModel");
            sKeys.put(16, "progress");
            sKeys.put(17, "titleIcon");
            sKeys.put(18, "splash");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(13);

        private InnerLayoutIdLookup() {
        }

        static {
            sKeys.put("layout/ocbadge_0", Integer.valueOf(R.layout.ocbadge));
            sKeys.put("layout/occontext_menu_item_0", Integer.valueOf(R.layout.occontext_menu_item));
            sKeys.put("layout/ocdropdown_0", Integer.valueOf(R.layout.ocdropdown));
            sKeys.put("layout/ocempty_layout_0", Integer.valueOf(R.layout.ocempty_layout));
            sKeys.put("layout/ocnotched_slider_0", Integer.valueOf(R.layout.ocnotched_slider));
            sKeys.put("layout/ocplaceholder_glint_0", Integer.valueOf(R.layout.ocplaceholder_glint));
            sKeys.put("layout/ocprogressbar_0", Integer.valueOf(R.layout.ocprogressbar));
            sKeys.put("layout/ocselect_0", Integer.valueOf(R.layout.ocselect));
            sKeys.put("layout/ocselect_icon_only_0", Integer.valueOf(R.layout.ocselect_icon_only));
            sKeys.put("layout/ocsidenav_0", Integer.valueOf(R.layout.ocsidenav));
            sKeys.put("layout/ocsidenav_badge_0", Integer.valueOf(R.layout.ocsidenav_badge));
            sKeys.put("layout/ocsidenav_item_0", Integer.valueOf(R.layout.ocsidenav_item));
            sKeys.put("layout/octile_button_0", Integer.valueOf(R.layout.octile_button));
        }
    }
}
