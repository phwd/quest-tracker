package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteListAdapter;

public abstract class SocialCreateVrInviteListItemBinding extends ViewDataBinding {
    @NonNull
    public final ConstraintLayout createVrInviteListItem;
    @NonNull
    public final OCButton createVrInviteListItemButton;
    @NonNull
    public final ImageView leftIcon;
    @Bindable
    protected CreateVrInviteListAdapter.ListItem mViewModel;
    @NonNull
    public final ImageView rightGlyph;
    @NonNull
    public final OCTextView subtitle;
    @NonNull
    public final OCTextView title;

    public abstract void setViewModel(@Nullable CreateVrInviteListAdapter.ListItem listItem);

    protected SocialCreateVrInviteListItemBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, OCButton oCButton, ImageView imageView, ImageView imageView2, OCTextView oCTextView, OCTextView oCTextView2) {
        super(obj, view, i);
        this.createVrInviteListItem = constraintLayout;
        this.createVrInviteListItemButton = oCButton;
        this.leftIcon = imageView;
        this.rightGlyph = imageView2;
        this.subtitle = oCTextView;
        this.title = oCTextView2;
    }

    @Nullable
    public CreateVrInviteListAdapter.ListItem getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static SocialCreateVrInviteListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SocialCreateVrInviteListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SocialCreateVrInviteListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_create_vr_invite_list_item, viewGroup, z, obj);
    }

    @NonNull
    public static SocialCreateVrInviteListItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SocialCreateVrInviteListItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SocialCreateVrInviteListItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_create_vr_invite_list_item, null, false, obj);
    }

    public static SocialCreateVrInviteListItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialCreateVrInviteListItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SocialCreateVrInviteListItemBinding) bind(obj, view, R.layout.social_create_vr_invite_list_item);
    }
}
