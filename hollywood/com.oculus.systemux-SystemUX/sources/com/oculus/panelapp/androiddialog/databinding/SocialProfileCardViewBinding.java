package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialUserViewModel;

public abstract class SocialProfileCardViewBinding extends ViewDataBinding {
    @Bindable
    protected SocialUserViewModel mUser;
    @NonNull
    public final OCTextView socialProfileCardAlias;
    @NonNull
    public final ImageView socialProfileCardImage;
    @NonNull
    public final OCTextView socialProfileCardSubtitle;

    public abstract void setUser(@Nullable SocialUserViewModel socialUserViewModel);

    protected SocialProfileCardViewBinding(Object obj, View view, int i, OCTextView oCTextView, ImageView imageView, OCTextView oCTextView2) {
        super(obj, view, i);
        this.socialProfileCardAlias = oCTextView;
        this.socialProfileCardImage = imageView;
        this.socialProfileCardSubtitle = oCTextView2;
    }

    @Nullable
    public SocialUserViewModel getUser() {
        return this.mUser;
    }

    @NonNull
    public static SocialProfileCardViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SocialProfileCardViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SocialProfileCardViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_profile_card_view, viewGroup, z, obj);
    }

    @NonNull
    public static SocialProfileCardViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SocialProfileCardViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SocialProfileCardViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_profile_card_view, null, false, obj);
    }

    public static SocialProfileCardViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialProfileCardViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SocialProfileCardViewBinding) bind(obj, view, R.layout.social_profile_card_view);
    }
}
