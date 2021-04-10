package com.oculus.panelapp.anytimeui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.common.ToggleButton;
import com.oculus.panelapp.anytimeui.v2.tablet.internalsettings.InternalSettingsEditText;
import com.oculus.panelapp.anytimeui.v2.tablet.internalsettings.InternalSettingsGeneralView;

public class AnytimeTabletInternalSettingsGeneralViewV2BindingImpl extends AnytimeTabletInternalSettingsGeneralViewV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final InternalSettingsGeneralView mboundView0;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    static {
        sViewsWithIds.put(R.id.debug_shell_settings_title, 1);
        sViewsWithIds.put(R.id.debug_shell_settings_button, 2);
        sViewsWithIds.put(R.id.debug_pin_debug_bar_title, 3);
        sViewsWithIds.put(R.id.debug_pin_debug_bar_button, 4);
        sViewsWithIds.put(R.id.debug_keyboard_test_title, 5);
        sViewsWithIds.put(R.id.debug_keyboard_test_input, 6);
        sViewsWithIds.put(R.id.debug_file_picker_title, 7);
        sViewsWithIds.put(R.id.debug_file_picker_button, 8);
        sViewsWithIds.put(R.id.debug_file_picker_selected_file_title, 9);
        sViewsWithIds.put(R.id.debug_file_picker_selected_file, 10);
        sViewsWithIds.put(R.id.debug_file_previewer_title, 11);
        sViewsWithIds.put(R.id.button_file_previewer_debug, 12);
        sViewsWithIds.put(R.id.onboarding_tutorial_title, 13);
        sViewsWithIds.put(R.id.onboarding_tutorial, 14);
        sViewsWithIds.put(R.id.debug_launch_apps_in_tablet_space_title, 15);
        sViewsWithIds.put(R.id.debug_launch_apps_in_tablet_space_button, 16);
    }

    public AnytimeTabletInternalSettingsGeneralViewV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 17, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletInternalSettingsGeneralViewV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (OCButton) objArr[12], (OCButton) objArr[8], (OCTextView) objArr[10], (OCTextView) objArr[9], (OCTextView) objArr[7], (OCTextView) objArr[11], (InternalSettingsEditText) objArr[6], (OCTextView) objArr[5], (ToggleButton) objArr[16], (OCTextView) objArr[15], (ToggleButton) objArr[4], (OCTextView) objArr[3], (OCButton) objArr[2], (OCTextView) objArr[1], (OCButton) objArr[14], (OCTextView) objArr[13]);
        this.mDirtyFlags = -1;
        this.mboundView0 = (InternalSettingsGeneralView) objArr[0];
        this.mboundView0.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            long j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }
}
