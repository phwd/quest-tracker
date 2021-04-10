package com.oculus.panelapp.social.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletSocialHorizontalUsersViewBinding extends AnonymousClass1uW {
    @NonNull
    public final OCRecyclerView horizontalRecycler;
    @NonNull
    public final ConstraintLayout socialHorizontalCards;

    public AnytimeTabletSocialHorizontalUsersViewBinding(Object obj, View view, int i, OCRecyclerView oCRecyclerView, ConstraintLayout constraintLayout) {
        super(obj, view, i);
        this.horizontalRecycler = oCRecyclerView;
        this.socialHorizontalCards = constraintLayout;
    }

    public static AnytimeTabletSocialHorizontalUsersViewBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletSocialHorizontalUsersViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialHorizontalUsersViewBinding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_social_horizontal_users_view);
    }

    @NonNull
    public static AnytimeTabletSocialHorizontalUsersViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletSocialHorizontalUsersViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialHorizontalUsersViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialHorizontalUsersViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_horizontal_users_view, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialHorizontalUsersViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialHorizontalUsersViewBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_horizontal_users_view, null, false);
    }
}
