package com.oculus.panelapp.androiddialog.dialogs.social.profile_card;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Observable;
import androidx.databinding.library.baseAdapters.BR;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.databinding.SocialProfileCardViewBinding;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialUserRequestFactory;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialUserViewModel;

public class SocialProfileCard extends ConstraintLayout {
    private SocialProfileCardViewBinding mBinding;
    private boolean mFetchData = false;
    private RequestBuilder<Bitmap> mGlideFetcher;
    private RequestManager mGlideManager;
    private SocialUserViewModel mSocialUserViewModel;
    private String mSubtitle;

    public SocialProfileCard(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initAttributes(context, attributeSet);
    }

    private void initAttributes(Context context, @Nullable AttributeSet attributeSet) {
        setupBinding(context);
        initCallbacks();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.SocialProfileCard, 0, 0);
        this.mGlideManager = Glide.with(getContext());
        this.mGlideFetcher = this.mGlideManager.asBitmap();
        this.mSocialUserViewModel = new SocialUserViewModel(new SocialUserRequestFactory(context));
        try {
            this.mSocialUserViewModel.setId(obtainStyledAttributes.getString(R.styleable.SocialProfileCard_user_id));
            this.mSocialUserViewModel.setUserType(obtainStyledAttributes.getInteger(R.styleable.SocialProfileCard_user_type, 0) == 0 ? SocialUserViewModel.UserType.VIEWER : SocialUserViewModel.UserType.USER);
            if (obtainStyledAttributes.hasValue(R.styleable.SocialProfileCard_user_profile_picture)) {
                this.mSocialUserViewModel.setProfilePhotoUrl(obtainStyledAttributes.getString(R.styleable.SocialProfileCard_user_profile_picture));
            }
            this.mSocialUserViewModel.setAlias(obtainStyledAttributes.getString(R.styleable.SocialProfileCard_user_alias));
            this.mSubtitle = obtainStyledAttributes.getString(R.styleable.SocialProfileCard_subtitle);
            this.mFetchData = obtainStyledAttributes.getBoolean(R.styleable.SocialProfileCard_fetch_data, true);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void initCallbacks() {
        this.mSocialUserViewModel.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.social.profile_card.SocialProfileCard.AnonymousClass1 */

            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i) {
                if (i == BR.profilePhotoUrl) {
                    ((RequestBuilder) ((RequestBuilder) SocialProfileCard.this.mGlideFetcher.load(SocialProfileCard.this.mSocialUserViewModel.getProfilePhotoUrl()).placeholder(R.drawable.oc_icon_profile_circle_filled_48_d2d2d2)).circleCrop()).into(SocialProfileCard.this.mBinding.socialProfileCardImage);
                }
            }
        });
    }

    private void setupBinding(Context context) {
        this.mBinding = SocialProfileCardViewBinding.inflate(LayoutInflater.from(context));
        this.mBinding.setUser(this.mSocialUserViewModel);
        this.mBinding.socialProfileCardSubtitle.setText(this.mSubtitle);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mFetchData) {
            this.mSocialUserViewModel.fetch();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mSocialUserViewModel.destroy();
        this.mGlideManager.clear(this.mBinding.socialProfileCardImage);
    }

    public void setUser(SocialUserViewModel socialUserViewModel) {
        this.mSocialUserViewModel = socialUserViewModel;
        this.mBinding.setUser(this.mSocialUserViewModel);
        initCallbacks();
    }
}
