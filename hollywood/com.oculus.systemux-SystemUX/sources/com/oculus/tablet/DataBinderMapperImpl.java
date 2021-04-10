package com.oculus.tablet;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.tablet.databinding.CommonTabletRectangularButtonBindingImpl;
import com.oculus.tablet.databinding.OsigButtonBorderlessBindingImpl;
import com.oculus.tablet.databinding.OsigSeekbarBindingImpl;
import com.oculus.userserver.api.sharing.SharingManagerContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(3);
    private static final int LAYOUT_COMMONTABLETRECTANGULARBUTTON = 1;
    private static final int LAYOUT_OSIGBUTTONBORDERLESS = 2;
    private static final int LAYOUT_OSIGSEEKBAR = 3;

    static {
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.common_tablet_rectangular_button, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.osig_button_borderless, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.osig_seekbar, 3);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag == null) {
            throw new RuntimeException("view must have a tag");
        } else if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return null;
                }
                if ("layout/osig_seekbar_0".equals(tag)) {
                    return new OsigSeekbarBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for osig_seekbar is invalid. Received: " + tag);
            } else if ("layout/osig_button_borderless_0".equals(tag)) {
                return new OsigButtonBorderlessBindingImpl(dataBindingComponent, view);
            } else {
                throw new IllegalArgumentException("The tag for osig_button_borderless is invalid. Received: " + tag);
            }
        } else if ("layout/common_tablet_rectangular_button_0".equals(tag)) {
            return new CommonTabletRectangularButtonBindingImpl(dataBindingComponent, view);
        } else {
            throw new IllegalArgumentException("The tag for common_tablet_rectangular_button is invalid. Received: " + tag);
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
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.ocui.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.vrshellutil.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>(23);

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
            sKeys.put(19, "icon");
            sKeys.put(20, "progressDrawable");
            sKeys.put(21, "text");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(3);

        private InnerLayoutIdLookup() {
        }

        static {
            sKeys.put("layout/common_tablet_rectangular_button_0", Integer.valueOf(R.layout.common_tablet_rectangular_button));
            sKeys.put("layout/osig_button_borderless_0", Integer.valueOf(R.layout.osig_button_borderless));
            sKeys.put("layout/osig_seekbar_0", Integer.valueOf(R.layout.osig_seekbar));
        }
    }
}
