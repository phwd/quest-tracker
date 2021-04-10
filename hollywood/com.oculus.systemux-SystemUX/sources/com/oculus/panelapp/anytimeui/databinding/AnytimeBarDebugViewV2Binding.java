package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.bar.DebugBarView;

public abstract class AnytimeBarDebugViewV2Binding extends ViewDataBinding {
    @NonNull
    public final OCButton bugButton;
    @NonNull
    public final DebugBarView debugBar;
    @NonNull
    public final OCButton dogfoodButton;
    @NonNull
    public final OCButton settingsButton;
    @NonNull
    public final OCTextView systemUsage;

    protected AnytimeBarDebugViewV2Binding(Object obj, View view, int i, OCButton oCButton, DebugBarView debugBarView, OCButton oCButton2, OCButton oCButton3, OCTextView oCTextView) {
        super(obj, view, i);
        this.bugButton = oCButton;
        this.debugBar = debugBarView;
        this.dogfoodButton = oCButton2;
        this.settingsButton = oCButton3;
        this.systemUsage = oCTextView;
    }

    @NonNull
    public static AnytimeBarDebugViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeBarDebugViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeBarDebugViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_bar_debug_view_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeBarDebugViewV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeBarDebugViewV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeBarDebugViewV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_bar_debug_view_v2, null, false, obj);
    }

    public static AnytimeBarDebugViewV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeBarDebugViewV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeBarDebugViewV2Binding) bind(obj, view, R.layout.anytime_bar_debug_view_v2);
    }
}
