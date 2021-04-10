package com.oculus.panelapp.bugreporter;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.panelapp.bugreporter.databinding.BugReportCameraRollImageBindingImpl;
import com.oculus.panelapp.bugreporter.databinding.BugReportCameraRollPlaceholderBindingImpl;
import com.oculus.panelapp.bugreporter.databinding.BugReportConfirmationViewBindingImpl;
import com.oculus.panelapp.bugreporter.databinding.BugReportDataPermissionViewBindingImpl;
import com.oculus.panelapp.bugreporter.databinding.BugReportDescriptionViewBindingImpl;
import com.oculus.panelapp.bugreporter.databinding.BugReportMediaViewBindingImpl;
import com.oculus.panelapp.bugreporter.databinding.BugReportOsUpdateViewBindingImpl;
import com.oculus.panelapp.bugreporter.databinding.BugReporterCancelViewBindingImpl;
import com.oculus.userserver.api.sharing.SharingManagerContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(8);
    private static final int LAYOUT_BUGREPORTCAMERAROLLIMAGE = 1;
    private static final int LAYOUT_BUGREPORTCAMERAROLLPLACEHOLDER = 2;
    private static final int LAYOUT_BUGREPORTCONFIRMATIONVIEW = 3;
    private static final int LAYOUT_BUGREPORTDATAPERMISSIONVIEW = 4;
    private static final int LAYOUT_BUGREPORTDESCRIPTIONVIEW = 5;
    private static final int LAYOUT_BUGREPORTERCANCELVIEW = 8;
    private static final int LAYOUT_BUGREPORTMEDIAVIEW = 6;
    private static final int LAYOUT_BUGREPORTOSUPDATEVIEW = 7;

    static {
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.bug_report_camera_roll_image, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.bug_report_camera_roll_placeholder, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.bug_report_confirmation_view, 3);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.bug_report_data_permission_view, 4);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.bug_report_description_view, 5);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.bug_report_media_view, 6);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.bug_report_os_update_view, 7);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.bug_reporter_cancel_view, 8);
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
                    if ("layout/bug_report_camera_roll_image_0".equals(tag)) {
                        return new BugReportCameraRollImageBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for bug_report_camera_roll_image is invalid. Received: " + tag);
                case 2:
                    if ("layout/bug_report_camera_roll_placeholder_0".equals(tag)) {
                        return new BugReportCameraRollPlaceholderBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for bug_report_camera_roll_placeholder is invalid. Received: " + tag);
                case 3:
                    if ("layout/bug_report_confirmation_view_0".equals(tag)) {
                        return new BugReportConfirmationViewBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for bug_report_confirmation_view is invalid. Received: " + tag);
                case 4:
                    if ("layout/bug_report_data_permission_view_0".equals(tag)) {
                        return new BugReportDataPermissionViewBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for bug_report_data_permission_view is invalid. Received: " + tag);
                case 5:
                    if ("layout/bug_report_description_view_0".equals(tag)) {
                        return new BugReportDescriptionViewBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for bug_report_description_view is invalid. Received: " + tag);
                case 6:
                    if ("layout/bug_report_media_view_0".equals(tag)) {
                        return new BugReportMediaViewBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for bug_report_media_view is invalid. Received: " + tag);
                case 7:
                    if ("layout/bug_report_os_update_view_0".equals(tag)) {
                        return new BugReportOsUpdateViewBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for bug_report_os_update_view is invalid. Received: " + tag);
                case 8:
                    if ("layout/bug_reporter_cancel_view_0".equals(tag)) {
                        return new BugReporterCancelViewBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for bug_reporter_cancel_view is invalid. Received: " + tag);
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
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.notifications.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.ocui.DataBinderMapperImpl());
        arrayList.add(new com.oculus.common.vrshellutil.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>(29);

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
            sKeys.put(19, "bugCategory");
            sKeys.put(20, "continueButtonEnabled");
            sKeys.put(21, "screenshot");
            sKeys.put(22, "includeScreenshot");
            sKeys.put(23, "descriptionText");
            sKeys.put(24, "attachLogs");
            sKeys.put(25, "continueButtonText");
            sKeys.put(26, "hasPreselectedPhoto");
            sKeys.put(27, "hasExceededFileSizeLimit");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(8);

        private InnerLayoutIdLookup() {
        }

        static {
            sKeys.put("layout/bug_report_camera_roll_image_0", Integer.valueOf(R.layout.bug_report_camera_roll_image));
            sKeys.put("layout/bug_report_camera_roll_placeholder_0", Integer.valueOf(R.layout.bug_report_camera_roll_placeholder));
            sKeys.put("layout/bug_report_confirmation_view_0", Integer.valueOf(R.layout.bug_report_confirmation_view));
            sKeys.put("layout/bug_report_data_permission_view_0", Integer.valueOf(R.layout.bug_report_data_permission_view));
            sKeys.put("layout/bug_report_description_view_0", Integer.valueOf(R.layout.bug_report_description_view));
            sKeys.put("layout/bug_report_media_view_0", Integer.valueOf(R.layout.bug_report_media_view));
            sKeys.put("layout/bug_report_os_update_view_0", Integer.valueOf(R.layout.bug_report_os_update_view));
            sKeys.put("layout/bug_reporter_cancel_view_0", Integer.valueOf(R.layout.bug_reporter_cancel_view));
        }
    }
}
