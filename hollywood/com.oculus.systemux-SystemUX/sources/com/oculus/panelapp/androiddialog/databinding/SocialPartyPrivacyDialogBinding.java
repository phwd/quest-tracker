package com.oculus.panelapp.androiddialog.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyDialog;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyViewModel;

public abstract class SocialPartyPrivacyDialogBinding extends ViewDataBinding {
    @NonNull
    public final OCButton closeButton;
    @NonNull
    public final SocialMenuItemToggleBinding joinPolicyToggleMenuItem;
    @NonNull
    public final OCTextView link;
    @NonNull
    public final SocialMenuItemToggleBinding linkPolicyToggleMenuItem;
    @NonNull
    public final OCTextView linkTitle;
    @Bindable
    protected SocialPartyPrivacyViewModel mPrivacyViewModel;
    @NonNull
    public final OCButton shareButton;
    @NonNull
    public final SocialPartyPrivacyDialog socialPartyPrivacyDialog;
    @NonNull
    public final View socialPartyPrivacyLinkAndShare;
    @NonNull
    public final DialogTitleBinding title;

    public abstract void setPrivacyViewModel(@Nullable SocialPartyPrivacyViewModel socialPartyPrivacyViewModel);

    protected SocialPartyPrivacyDialogBinding(Object obj, View view, int i, OCButton oCButton, SocialMenuItemToggleBinding socialMenuItemToggleBinding, OCTextView oCTextView, SocialMenuItemToggleBinding socialMenuItemToggleBinding2, OCTextView oCTextView2, OCButton oCButton2, SocialPartyPrivacyDialog socialPartyPrivacyDialog2, View view2, DialogTitleBinding dialogTitleBinding) {
        super(obj, view, i);
        this.closeButton = oCButton;
        this.joinPolicyToggleMenuItem = socialMenuItemToggleBinding;
        setContainedBinding(this.joinPolicyToggleMenuItem);
        this.link = oCTextView;
        this.linkPolicyToggleMenuItem = socialMenuItemToggleBinding2;
        setContainedBinding(this.linkPolicyToggleMenuItem);
        this.linkTitle = oCTextView2;
        this.shareButton = oCButton2;
        this.socialPartyPrivacyDialog = socialPartyPrivacyDialog2;
        this.socialPartyPrivacyLinkAndShare = view2;
        this.title = dialogTitleBinding;
        setContainedBinding(this.title);
    }

    @Nullable
    public SocialPartyPrivacyViewModel getPrivacyViewModel() {
        return this.mPrivacyViewModel;
    }

    @NonNull
    public static SocialPartyPrivacyDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SocialPartyPrivacyDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SocialPartyPrivacyDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_party_privacy_dialog, viewGroup, z, obj);
    }

    @NonNull
    public static SocialPartyPrivacyDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SocialPartyPrivacyDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SocialPartyPrivacyDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.social_party_privacy_dialog, null, false, obj);
    }

    public static SocialPartyPrivacyDialogBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SocialPartyPrivacyDialogBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SocialPartyPrivacyDialogBinding) bind(obj, view, R.layout.social_party_privacy_dialog);
    }
}
