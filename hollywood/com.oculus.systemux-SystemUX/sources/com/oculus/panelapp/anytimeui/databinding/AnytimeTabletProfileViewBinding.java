package com.oculus.panelapp.anytimeui.databinding;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCEmptyLayout;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileEditText;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileViewModel;

public abstract class AnytimeTabletProfileViewBinding extends ViewDataBinding {
    @NonNull
    public final ImageView avatar;
    @NonNull
    public final OCTextView biography;
    @NonNull
    public final OCButton buttonSwitchAccount;
    @NonNull
    public final OCSpinner confirmEditLoadingSpinner;
    @NonNull
    public final Group contentGroup;
    @NonNull
    public final OCButton editAvatar;
    @NonNull
    public final OCTextView editBioCharacterCount;
    @NonNull
    public final ProfileEditText editBiography;
    @NonNull
    public final OCTextView friendCount;
    @NonNull
    public final ImageView loadingErrorImage;
    @NonNull
    public final OCTextView loadingErrorSubtitle;
    @NonNull
    public final OCTextView loadingErrorTitle;
    @Bindable
    protected Resources mResources;
    @Bindable
    protected ProfileViewModel mViewModel;
    @NonNull
    public final ImageView messengerPresenceIcon;
    @NonNull
    public final OCEmptyLayout offlineState;
    @NonNull
    public final OCSpinner overflowButtonLoadingSpinner;
    @NonNull
    public final OCTextView presenceString;
    @NonNull
    public final OCSpinner primaryButtonLoadingSpinner;
    @NonNull
    public final OCButton profileBackButton;
    @NonNull
    public final OCButton profileConfirmEdit;
    @NonNull
    public final OCTextView profileHeader;
    @NonNull
    public final OCSpinner profileLoadingSpinner;
    @NonNull
    public final OCButton profileOverflow;
    @NonNull
    public final ImageView profilePicture;
    @NonNull
    public final ImageView profilePictureEditOverlay;
    @NonNull
    public final OCButton profilePrimaryButton;
    @NonNull
    public final OCTextView profilePrimaryName;
    @NonNull
    public final OCButton profileSecondaryButton;

    public abstract void setResources(@Nullable Resources resources);

    public abstract void setViewModel(@Nullable ProfileViewModel profileViewModel);

    protected AnytimeTabletProfileViewBinding(Object obj, View view, int i, ImageView imageView, OCTextView oCTextView, OCButton oCButton, OCSpinner oCSpinner, Group group, OCButton oCButton2, OCTextView oCTextView2, ProfileEditText profileEditText, OCTextView oCTextView3, ImageView imageView2, OCTextView oCTextView4, OCTextView oCTextView5, ImageView imageView3, OCEmptyLayout oCEmptyLayout, OCSpinner oCSpinner2, OCTextView oCTextView6, OCSpinner oCSpinner3, OCButton oCButton3, OCButton oCButton4, OCTextView oCTextView7, OCSpinner oCSpinner4, OCButton oCButton5, ImageView imageView4, ImageView imageView5, OCButton oCButton6, OCTextView oCTextView8, OCButton oCButton7) {
        super(obj, view, i);
        this.avatar = imageView;
        this.biography = oCTextView;
        this.buttonSwitchAccount = oCButton;
        this.confirmEditLoadingSpinner = oCSpinner;
        this.contentGroup = group;
        this.editAvatar = oCButton2;
        this.editBioCharacterCount = oCTextView2;
        this.editBiography = profileEditText;
        this.friendCount = oCTextView3;
        this.loadingErrorImage = imageView2;
        this.loadingErrorSubtitle = oCTextView4;
        this.loadingErrorTitle = oCTextView5;
        this.messengerPresenceIcon = imageView3;
        this.offlineState = oCEmptyLayout;
        this.overflowButtonLoadingSpinner = oCSpinner2;
        this.presenceString = oCTextView6;
        this.primaryButtonLoadingSpinner = oCSpinner3;
        this.profileBackButton = oCButton3;
        this.profileConfirmEdit = oCButton4;
        this.profileHeader = oCTextView7;
        this.profileLoadingSpinner = oCSpinner4;
        this.profileOverflow = oCButton5;
        this.profilePicture = imageView4;
        this.profilePictureEditOverlay = imageView5;
        this.profilePrimaryButton = oCButton6;
        this.profilePrimaryName = oCTextView8;
        this.profileSecondaryButton = oCButton7;
    }

    @Nullable
    public ProfileViewModel getViewModel() {
        return this.mViewModel;
    }

    @Nullable
    public Resources getResources() {
        return this.mResources;
    }

    @NonNull
    public static AnytimeTabletProfileViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletProfileViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletProfileViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_profile_view, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletProfileViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletProfileViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletProfileViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_profile_view, null, false, obj);
    }

    public static AnytimeTabletProfileViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletProfileViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletProfileViewBinding) bind(obj, view, R.layout.anytime_tablet_profile_view);
    }
}
