package com.oculus.common.quickpromotion;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.common.quickpromotion.databinding.QpTooltipViewBindingImpl;
import com.oculus.userserver.api.sharing.SharingManagerContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(1);
    private static final int LAYOUT_QPTOOLTIPVIEW = 1;

    static {
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.qp_tooltip_view, 1);
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
            return null;
        } else {
            if ("layout/qp_tooltip_view_0".equals(tag)) {
                return new QpTooltipViewBindingImpl(dataBindingComponent, view);
            }
            throw new IllegalArgumentException("The tag for qp_tooltip_view is invalid. Received: " + tag);
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
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.ocui.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.vrshellutil.DataBinderMapperImpl());
        arrayList.add(new com.oculus.tablet.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>(23);

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
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(1);

        private InnerLayoutIdLookup() {
        }

        static {
            sKeys.put("layout/qp_tooltip_view_0", Integer.valueOf(R.layout.qp_tooltip_view));
        }
    }
}
