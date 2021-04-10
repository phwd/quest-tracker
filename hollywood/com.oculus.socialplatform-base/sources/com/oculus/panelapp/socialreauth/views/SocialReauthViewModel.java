package com.oculus.panelapp.socialreauth.views;

import X.AnonymousClass1uc;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import androidx.databinding.Bindable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.securedactions.SecuredActions;
import com.oculus.socialplatform.R;
import com.oculus.tablet.view.ViewModelLifecycle;

public class SocialReauthViewModel extends AnonymousClass1uc implements ViewModelLifecycle {
    public static final String TAG = LoggingUtil.tag(SocialReauthViewModel.class);
    public String mErrorBodyText;
    public boolean mHasEncounteredError;
    public boolean mIsAuthenticationInProgress;
    public boolean mIsPasswordShown;
    public boolean mIsShowingEducationInfo;
    public Resources mResources;
    public String mUserName;

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        this.mResources = null;
    }

    public void setHasEncounteredError(Integer num) {
        Resources resources;
        int i;
        this.mHasEncounteredError = true;
        if (num != null) {
            if (num.equals(Integer.valueOf((int) SecuredActions.INCORRECT_PASSWORD_ERR_SUBCODE))) {
                resources = this.mResources;
                i = R.string.anytime_tablet_social_reauth_password_incorrect_body;
            } else if (num.equals(Integer.valueOf((int) SecuredActions.TOO_MANY_ATTEMPTS_ERR_SUBCODE))) {
                resources = this.mResources;
                i = R.string.anytime_tablet_social_reauth_too_many_failed_attempts_body;
            }
            this.mErrorBodyText = resources.getString(i);
            notifyPropertyChanged(55);
            notifyPropertyChanged(61);
            notifyPropertyChanged(63);
        }
        resources = this.mResources;
        i = R.string.anytime_tablet_social_reauth_generic_error_body;
        this.mErrorBodyText = resources.getString(i);
        notifyPropertyChanged(55);
        notifyPropertyChanged(61);
        notifyPropertyChanged(63);
    }

    @Bindable
    public String getBodyText() {
        Resources resources;
        int i;
        if (this.mIsShowingEducationInfo) {
            resources = this.mResources;
            i = R.string.anytime_tablet_social_reauth_body_with_reason;
        } else {
            resources = this.mResources;
            i = R.string.anytime_tablet_social_reauth_body;
        }
        return resources.getString(i);
    }

    @Bindable
    public int getBodyVisibility() {
        if (this.mHasEncounteredError) {
            return 4;
        }
        return 0;
    }

    @Bindable
    public float getContinueButtonElementsAlpha() {
        if (!this.mIsAuthenticationInProgress) {
            return 1.0f;
        }
        TypedValue typedValue = new TypedValue();
        this.mResources.getValue(R.dimen.ocopacity_disabled_opacity, typedValue, true);
        return typedValue.getFloat();
    }

    @Bindable
    public boolean getContinueButtonEnabled() {
        return !this.mIsAuthenticationInProgress;
    }

    @Bindable
    public String getContinueButtonText() {
        return this.mResources.getString(R.string.anytime_tablet_social_reauth_button, this.mUserName);
    }

    @Bindable
    public String getErrorBodyText() {
        return this.mErrorBodyText;
    }

    @Bindable
    public int getErrorBodyVisibility() {
        if (this.mHasEncounteredError) {
            return 0;
        }
        return 8;
    }

    @Bindable
    public String getFooterText() {
        Resources resources;
        int i;
        if (this.mIsShowingEducationInfo) {
            resources = this.mResources;
            i = R.string.anytime_tablet_social_reauth_sharing_education;
        } else {
            resources = this.mResources;
            i = R.string.anytime_tablet_social_reauth_oculus_chats_explanation;
        }
        return resources.getString(i);
    }

    @Bindable
    public String getHeaderText() {
        return this.mResources.getString(R.string.anytime_tablet_social_reauth_header, this.mUserName);
    }

    @Bindable
    public boolean getPasswordInputEnabled() {
        return !this.mIsAuthenticationInProgress;
    }

    @Bindable
    public int getPasswordInputType() {
        if (this.mIsPasswordShown) {
            return 145;
        }
        return 129;
    }

    @Bindable
    public Drawable getPasswordVisibilityBtnBackground() {
        Resources resources;
        int i;
        if (this.mIsPasswordShown) {
            resources = this.mResources;
            i = R.drawable.password_input_visibility_toggle_btn_visible;
        } else {
            resources = this.mResources;
            i = R.drawable.password_input_visibility_toggle_btn_hidden;
        }
        return resources.getDrawable(i, null);
    }

    public void setIsAuthenticationInProgress(boolean z) {
        this.mIsAuthenticationInProgress = z;
        notifyPropertyChanged(51);
        notifyPropertyChanged(59);
        notifyPropertyChanged(58);
    }

    public void setIsShowingEducationInfo(boolean z) {
        this.mIsShowingEducationInfo = z;
        notifyPropertyChanged(53);
        notifyPropertyChanged(60);
    }

    public void setUserName(String str) {
        this.mUserName = str;
        notifyPropertyChanged(52);
        notifyPropertyChanged(56);
    }

    public void togglePasswordShown() {
        this.mIsPasswordShown = !this.mIsPasswordShown;
        notifyPropertyChanged(57);
        notifyPropertyChanged(54);
    }

    public SocialReauthViewModel(Resources resources) {
        this.mResources = resources;
    }
}
