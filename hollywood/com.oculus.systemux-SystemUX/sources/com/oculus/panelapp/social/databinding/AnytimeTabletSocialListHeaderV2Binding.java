package com.oculus.panelapp.social.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.R;
import com.oculus.vrshell.panels.views.ShellButton;

public abstract class AnytimeTabletSocialListHeaderV2Binding extends ViewDataBinding {
    @NonNull
    public final ShellButton buttonAddFriend;
    @NonNull
    public final OCTextView headerTitle;
    @Bindable
    protected String mName;
    @Bindable
    protected boolean mShowAddFriend;
    @NonNull
    public final ConstraintLayout socialListHeaderItem;

    public abstract void setName(@Nullable String str);

    public abstract void setShowAddFriend(boolean z);

    protected AnytimeTabletSocialListHeaderV2Binding(Object obj, View view, int i, ShellButton shellButton, OCTextView oCTextView, ConstraintLayout constraintLayout) {
        super(obj, view, i);
        this.buttonAddFriend = shellButton;
        this.headerTitle = oCTextView;
        this.socialListHeaderItem = constraintLayout;
    }

    @Nullable
    public String getName() {
        return this.mName;
    }

    public boolean getShowAddFriend() {
        return this.mShowAddFriend;
    }

    @NonNull
    public static AnytimeTabletSocialListHeaderV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialListHeaderV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletSocialListHeaderV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_list_header_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletSocialListHeaderV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialListHeaderV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletSocialListHeaderV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_list_header_v2, null, false, obj);
    }

    public static AnytimeTabletSocialListHeaderV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletSocialListHeaderV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialListHeaderV2Binding) bind(obj, view, R.layout.anytime_tablet_social_list_header_v2);
    }
}
