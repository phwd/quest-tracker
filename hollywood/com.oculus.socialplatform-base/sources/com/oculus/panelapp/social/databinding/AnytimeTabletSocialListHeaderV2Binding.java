package com.oculus.panelapp.social.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.views.ShellButton;

public abstract class AnytimeTabletSocialListHeaderV2Binding extends AnonymousClass1uW {
    @NonNull
    public final ShellButton buttonAddFriend;
    @NonNull
    public final OCTextView headerTitle;
    @Bindable
    public String mName;
    @Bindable
    public boolean mShowAddFriend;
    @NonNull
    public final ConstraintLayout socialListHeaderItem;

    public abstract void setName(@Nullable String str);

    public abstract void setShowAddFriend(boolean z);

    @Nullable
    public String getName() {
        return this.mName;
    }

    public boolean getShowAddFriend() {
        return this.mShowAddFriend;
    }

    public AnytimeTabletSocialListHeaderV2Binding(Object obj, View view, int i, ShellButton shellButton, OCTextView oCTextView, ConstraintLayout constraintLayout) {
        super(obj, view, i);
        this.buttonAddFriend = shellButton;
        this.headerTitle = oCTextView;
        this.socialListHeaderItem = constraintLayout;
    }

    public static AnytimeTabletSocialListHeaderV2Binding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletSocialListHeaderV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletSocialListHeaderV2Binding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_social_list_header_v2);
    }

    @NonNull
    public static AnytimeTabletSocialListHeaderV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletSocialListHeaderV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialListHeaderV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialListHeaderV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_list_header_v2, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletSocialListHeaderV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletSocialListHeaderV2Binding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_social_list_header_v2, null, false);
    }
}
