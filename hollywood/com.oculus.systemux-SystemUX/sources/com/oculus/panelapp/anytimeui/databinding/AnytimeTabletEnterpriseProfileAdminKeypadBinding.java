package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileViewModel;

public abstract class AnytimeTabletEnterpriseProfileAdminKeypadBinding extends ViewDataBinding {
    @NonNull
    public final AnytimeTabletEnterpriseAdminKeypadButtonBinding adminKeypad0;
    @NonNull
    public final AnytimeTabletEnterpriseAdminKeypadButtonBinding adminKeypad1;
    @NonNull
    public final AnytimeTabletEnterpriseAdminKeypadButtonBinding adminKeypad2;
    @NonNull
    public final AnytimeTabletEnterpriseAdminKeypadButtonBinding adminKeypad3;
    @NonNull
    public final AnytimeTabletEnterpriseAdminKeypadButtonBinding adminKeypad4;
    @NonNull
    public final AnytimeTabletEnterpriseAdminKeypadButtonBinding adminKeypad5;
    @NonNull
    public final AnytimeTabletEnterpriseAdminKeypadButtonBinding adminKeypad6;
    @NonNull
    public final AnytimeTabletEnterpriseAdminKeypadButtonBinding adminKeypad7;
    @NonNull
    public final AnytimeTabletEnterpriseAdminKeypadButtonBinding adminKeypad8;
    @NonNull
    public final AnytimeTabletEnterpriseAdminKeypadButtonBinding adminKeypad9;
    @NonNull
    public final AnytimeTabletEnterpriseAdminKeypadButtonBinding adminKeypadBackspace;
    @NonNull
    public final Guideline adminKeypadBubbleGuideline;
    @NonNull
    public final View adminKeypadBubbleSpace;
    @NonNull
    public final ConstraintLayout adminKeypadContainer;
    @NonNull
    public final View adminKeypadEmptyBubble0;
    @NonNull
    public final View adminKeypadEmptyBubble1;
    @NonNull
    public final View adminKeypadEmptyBubble2;
    @NonNull
    public final View adminKeypadEmptyBubble3;
    @NonNull
    public final View adminKeypadFilledBubble0;
    @NonNull
    public final View adminKeypadFilledBubble1;
    @NonNull
    public final View adminKeypadFilledBubble2;
    @NonNull
    public final View adminKeypadFilledBubble3;
    @NonNull
    public final OCTextView adminKeypadTitle;
    @Bindable
    protected EnterpriseProfileViewModel mViewModel;

    public abstract void setViewModel(@Nullable EnterpriseProfileViewModel enterpriseProfileViewModel);

    protected AnytimeTabletEnterpriseProfileAdminKeypadBinding(Object obj, View view, int i, AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding, AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding2, AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding3, AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding4, AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding5, AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding6, AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding7, AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding8, AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding9, AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding10, AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding11, Guideline guideline, View view2, ConstraintLayout constraintLayout, View view3, View view4, View view5, View view6, View view7, View view8, View view9, View view10, OCTextView oCTextView) {
        super(obj, view, i);
        this.adminKeypad0 = anytimeTabletEnterpriseAdminKeypadButtonBinding;
        setContainedBinding(this.adminKeypad0);
        this.adminKeypad1 = anytimeTabletEnterpriseAdminKeypadButtonBinding2;
        setContainedBinding(this.adminKeypad1);
        this.adminKeypad2 = anytimeTabletEnterpriseAdminKeypadButtonBinding3;
        setContainedBinding(this.adminKeypad2);
        this.adminKeypad3 = anytimeTabletEnterpriseAdminKeypadButtonBinding4;
        setContainedBinding(this.adminKeypad3);
        this.adminKeypad4 = anytimeTabletEnterpriseAdminKeypadButtonBinding5;
        setContainedBinding(this.adminKeypad4);
        this.adminKeypad5 = anytimeTabletEnterpriseAdminKeypadButtonBinding6;
        setContainedBinding(this.adminKeypad5);
        this.adminKeypad6 = anytimeTabletEnterpriseAdminKeypadButtonBinding7;
        setContainedBinding(this.adminKeypad6);
        this.adminKeypad7 = anytimeTabletEnterpriseAdminKeypadButtonBinding8;
        setContainedBinding(this.adminKeypad7);
        this.adminKeypad8 = anytimeTabletEnterpriseAdminKeypadButtonBinding9;
        setContainedBinding(this.adminKeypad8);
        this.adminKeypad9 = anytimeTabletEnterpriseAdminKeypadButtonBinding10;
        setContainedBinding(this.adminKeypad9);
        this.adminKeypadBackspace = anytimeTabletEnterpriseAdminKeypadButtonBinding11;
        setContainedBinding(this.adminKeypadBackspace);
        this.adminKeypadBubbleGuideline = guideline;
        this.adminKeypadBubbleSpace = view2;
        this.adminKeypadContainer = constraintLayout;
        this.adminKeypadEmptyBubble0 = view3;
        this.adminKeypadEmptyBubble1 = view4;
        this.adminKeypadEmptyBubble2 = view5;
        this.adminKeypadEmptyBubble3 = view6;
        this.adminKeypadFilledBubble0 = view7;
        this.adminKeypadFilledBubble1 = view8;
        this.adminKeypadFilledBubble2 = view9;
        this.adminKeypadFilledBubble3 = view10;
        this.adminKeypadTitle = oCTextView;
    }

    @Nullable
    public EnterpriseProfileViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static AnytimeTabletEnterpriseProfileAdminKeypadBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletEnterpriseProfileAdminKeypadBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletEnterpriseProfileAdminKeypadBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_enterprise_profile_admin_keypad, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletEnterpriseProfileAdminKeypadBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletEnterpriseProfileAdminKeypadBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletEnterpriseProfileAdminKeypadBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_enterprise_profile_admin_keypad, null, false, obj);
    }

    public static AnytimeTabletEnterpriseProfileAdminKeypadBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletEnterpriseProfileAdminKeypadBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletEnterpriseProfileAdminKeypadBinding) bind(obj, view, R.layout.anytime_tablet_enterprise_profile_admin_keypad);
    }
}
