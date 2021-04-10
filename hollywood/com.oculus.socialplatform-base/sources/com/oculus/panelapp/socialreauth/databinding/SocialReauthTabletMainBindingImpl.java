package com.oculus.panelapp.socialreauth.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.breakpad.BreakpadManager;
import com.oculus.common.socialtablet.navbar.SocialTabletSideNav;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCScrollView;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.socialreauth.views.PasswordInput;
import com.oculus.panelapp.socialreauth.views.SocialReauthView;
import com.oculus.panelapp.socialreauth.views.SocialReauthViewModel;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.AndroidPanelLayer;

public class SocialReauthTabletMainBindingImpl extends SocialReauthTabletMainBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeViewModel(SocialReauthViewModel socialReauthViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 52) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == 60) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == 55) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == 63) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == 61) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == 58) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i == 54) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (i == 57) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (i == 59) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (i == 51) {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        } else if (i == 56) {
            synchronized (this) {
                this.mDirtyFlags |= BreakpadManager.MD_FB_WITH_UNWINDSTACK_STREAM;
            }
            return true;
        } else if (i != 53) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= BreakpadManager.MD_FB_INSTALL_ALT_STACK;
            }
            return true;
        }
    }

    @Override // X.AnonymousClass1uW
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8192;
        }
        requestRebind();
    }

    @Override // com.oculus.panelapp.socialreauth.databinding.SocialReauthTabletMainBinding
    public void setViewModel(@Nullable SocialReauthViewModel socialReauthViewModel) {
        updateRegistration(0, socialReauthViewModel);
        this.mViewModel = socialReauthViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.social_tablet_side_nav, 10);
        sparseIntArray.put(R.id.body_scroll_view, 11);
        sparseIntArray.put(R.id.password_input_label, 12);
        sparseIntArray.put(R.id.password_input_background, 13);
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        String str;
        String str2;
        String str3;
        String str4;
        Drawable drawable;
        boolean z;
        int i;
        boolean z2;
        int i2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        float f = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        SocialReauthViewModel socialReauthViewModel = this.mViewModel;
        int i3 = 0;
        String str5 = null;
        if ((16383 & j) != 0) {
            if ((j & 8197) == 0 || socialReauthViewModel == null) {
                str = null;
            } else {
                str = socialReauthViewModel.getBodyText();
            }
            if ((j & 8449) == 0 || socialReauthViewModel == null) {
                drawable = null;
            } else {
                drawable = socialReauthViewModel.getPasswordVisibilityBtnBackground();
            }
            if ((j & 8705) == 0 || socialReauthViewModel == null) {
                z = false;
            } else {
                z = !socialReauthViewModel.mIsAuthenticationInProgress;
            }
            if ((j & 12289) == 0 || socialReauthViewModel == null) {
                str3 = null;
            } else {
                str3 = socialReauthViewModel.getFooterText();
            }
            if ((j & 8195) == 0 || socialReauthViewModel == null) {
                str4 = null;
            } else {
                str4 = socialReauthViewModel.getHeaderText();
            }
            if ((j & 8209) == 0 || socialReauthViewModel == null) {
                str2 = null;
            } else {
                str2 = socialReauthViewModel.mErrorBodyText;
            }
            if ((j & 8225) == 0 || socialReauthViewModel == null) {
                i = 0;
            } else {
                i = socialReauthViewModel.getErrorBodyVisibility();
            }
            if ((j & 8257) == 0 || socialReauthViewModel == null) {
                z2 = false;
            } else {
                z2 = !socialReauthViewModel.mIsAuthenticationInProgress;
            }
            if ((j & 8321) == 0 || socialReauthViewModel == null) {
                i2 = 0;
            } else {
                i2 = socialReauthViewModel.getPasswordInputType();
            }
            if (!((j & 9217) == 0 || socialReauthViewModel == null)) {
                f = socialReauthViewModel.getContinueButtonElementsAlpha();
            }
            if (!((j & 8201) == 0 || socialReauthViewModel == null)) {
                i3 = socialReauthViewModel.getBodyVisibility();
            }
            if (!((j & 10241) == 0 || socialReauthViewModel == null)) {
                str5 = socialReauthViewModel.getContinueButtonText();
            }
        } else {
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            drawable = null;
            z = false;
            i = 0;
            z2 = false;
            i2 = 0;
        }
        if ((j & 8197) != 0) {
            C11051qV.A02(this.body, str);
        }
        if ((j & 8201) != 0) {
            this.body.setVisibility(i3);
        }
        if ((j & 8705) != 0) {
            this.continueBtn.setEnabled(z);
        }
        if ((j & 9217) != 0 && AnonymousClass1uW.SDK_INT >= 11) {
            this.continueBtnIcon.setAlpha(f);
            this.continueBtnLabel.setAlpha(f);
        }
        if ((j & 10241) != 0) {
            C11051qV.A02(this.continueBtnLabel, str5);
        }
        if ((j & 8209) != 0) {
            C11051qV.A02(this.errorBody, str2);
        }
        if ((8225 & j) != 0) {
            this.errorBody.setVisibility(i);
        }
        if ((j & 12289) != 0) {
            C11051qV.A02(this.footer, str3);
        }
        if ((j & 8195) != 0) {
            C11051qV.A02(this.header, str4);
        }
        if ((8257 & j) != 0) {
            this.passwordInput.setEnabled(z2);
        }
        if ((8321 & j) != 0 && AnonymousClass1uW.SDK_INT >= 3) {
            this.passwordInput.setInputType(i2);
        }
        if ((j & 8449) != 0) {
            this.togglePasswordVisibilityBtn.setBackground(drawable);
        }
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((SocialReauthViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (62 != i) {
            return false;
        }
        setViewModel((SocialReauthViewModel) obj);
        return true;
    }

    public SocialReauthTabletMainBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 14, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public SocialReauthTabletMainBindingImpl(AbstractC003408r r34, View view, Object[] objArr) {
        super(r34, view, 1, (OCTextView) objArr[2], (OCScrollView) objArr[11], (OCButton) objArr[6], (ImageView) objArr[7], (OCTextView) objArr[8], (OCTextView) objArr[3], (OCTextView) objArr[9], (OCTextView) objArr[1], (PasswordInput) objArr[4], (View) objArr[13], (OCTextView) objArr[12], (SocialReauthView) objArr[0], (SocialTabletSideNav) objArr[10], (OCButton) objArr[5]);
        this.mDirtyFlags = -1;
        this.body.setTag(null);
        this.continueBtn.setTag(null);
        this.continueBtnIcon.setTag(null);
        this.continueBtnLabel.setTag(null);
        this.errorBody.setTag(null);
        this.footer.setTag(null);
        this.header.setTag(null);
        this.passwordInput.setTag(null);
        this.socialReauthView.setTag(null);
        this.togglePasswordVisibilityBtn.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
