package com.oculus.panelapp.socialandroidbackpanel.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteListAdapter;
import com.oculus.socialplatform.R;

public abstract class SocialCreateVrInviteListItemBinding extends AnonymousClass1uW {
    @NonNull
    public final ConstraintLayout createVrInviteListItem;
    @NonNull
    public final OCButton createVrInviteListItemButton;
    @NonNull
    public final ImageView leftIcon;
    @Bindable
    public CreateVrInviteListAdapter.ListItem mViewModel;
    @NonNull
    public final ImageView rightGlyph;
    @NonNull
    public final OCTextView subtitle;
    @NonNull
    public final OCTextView title;

    public abstract void setViewModel(@Nullable CreateVrInviteListAdapter.ListItem listItem);

    @Nullable
    public CreateVrInviteListAdapter.ListItem getViewModel() {
        return this.mViewModel;
    }

    public SocialCreateVrInviteListItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, OCButton oCButton, ImageView imageView, ImageView imageView2, OCTextView oCTextView, OCTextView oCTextView2) {
        super(obj, view, i);
        this.createVrInviteListItem = constraintLayout;
        this.createVrInviteListItemButton = oCButton;
        this.leftIcon = imageView;
        this.rightGlyph = imageView2;
        this.subtitle = oCTextView;
        this.title = oCTextView2;
    }

    public static SocialCreateVrInviteListItemBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static SocialCreateVrInviteListItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SocialCreateVrInviteListItemBinding) AnonymousClass1uW.bind(obj, view, R.layout.social_create_vr_invite_list_item);
    }

    @NonNull
    public static SocialCreateVrInviteListItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static SocialCreateVrInviteListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static SocialCreateVrInviteListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialCreateVrInviteListItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_create_vr_invite_list_item, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static SocialCreateVrInviteListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (SocialCreateVrInviteListItemBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.social_create_vr_invite_list_item, null, false);
    }
}
