package com.oculus.panelapp.social.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.panelapp.social.R;

public abstract class AnytimeTabletSocialHorizontalUsersViewBinding extends ViewDataBinding {
    @NonNull
    public final OCRecyclerView horizontalRecycler;
    @NonNull
    public final ConstraintLayout socialHorizontalCards;

    protected AnytimeTabletSocialHorizontalUsersViewBinding(Object obj, View view, int i, OCRecyclerView oCRecyclerView, ConstraintLayout constraintLayout) {
        super(obj, view, i);
        this.horizontalRecycler = oCRecyclerView;
        this.socialHorizontalCards = constraintLayout;
    }

    @NonNull
    public static AnytimeTabletSocialHorizontalUsersViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialHorizontalUsersViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletSocialHorizontalUsersViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_horizontal_users_view, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletSocialHorizontalUsersViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialHorizontalUsersViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletSocialHorizontalUsersViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_social_horizontal_users_view, null, false, obj);
    }

    public static AnytimeTabletSocialHorizontalUsersViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletSocialHorizontalUsersViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialHorizontalUsersViewBinding) bind(obj, view, R.layout.anytime_tablet_social_horizontal_users_view);
    }
}
