package com.oculus.panelapp.social.databinding;

import X.AbstractC003408r;
import X.AnonymousClass0AS;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;
import com.oculus.tablet.databinding.CommonTabletRectangularButtonBinding;
import com.oculus.vrshell.panels.views.ShellButton;

public class AnytimeTabletSocialFriendsHeaderBindingImpl extends AnytimeTabletSocialFriendsHeaderBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;

    static {
        AnonymousClass1ui r3 = new AnonymousClass1ui(4);
        sIncludes = r3;
        r3.A00(new String[]{"common_tablet_rectangular_button"}, new int[]{3}, new int[]{R.layout.common_tablet_rectangular_button});
    }

    private boolean onChangeButtonStartParty(CommonTabletRectangularButtonBinding commonTabletRectangularButtonBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    @Override // X.AnonymousClass1uW
    public boolean hasPendingBindings() {
        int i;
        synchronized (this) {
            i = (this.mDirtyFlags > 0 ? 1 : (this.mDirtyFlags == 0 ? 0 : -1));
        }
        if (i != 0 || this.buttonStartParty.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
        }
        this.buttonStartParty.invalidateAll();
        requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        int i;
        long j2;
        int i2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SocialParty socialParty = this.mParty;
        boolean z = this.mShowStartParty;
        String str = null;
        long j4 = j & 10;
        int i3 = 0;
        if (j4 != 0) {
            boolean z2 = false;
            if (socialParty == null) {
                z2 = true;
            }
            if (j4 != 0) {
                if (z2) {
                    j3 = 32;
                } else {
                    j3 = 16;
                }
                j |= j3;
            }
            if (socialParty != null) {
                i2 = socialParty.getSize();
            } else {
                i2 = 0;
            }
            i = 0;
            if (z2) {
                i = 8;
            }
            str = String.format(this.buttonGotoParty.getResources().getString(R.string.anytime_tablet_social_active_party), Integer.valueOf(i2));
        } else {
            i = 0;
        }
        long j5 = j & 12;
        if (j5 != 0) {
            if (j5 != 0) {
                if (z) {
                    j2 = 128;
                } else {
                    j2 = 64;
                }
                j |= j2;
            }
            if (!z) {
                i3 = 8;
            }
        }
        if ((10 & j) != 0) {
            C11051qV.A02(this.buttonGotoParty, str);
            this.buttonGotoParty.setVisibility(i);
        }
        if ((j & 12) != 0) {
            this.buttonStartParty.mRoot.setVisibility(i3);
        }
        if ((j & 8) != 0) {
            this.buttonStartParty.setText(this.mRoot.getResources().getString(R.string.anytime_tablet_social_start_party));
            this.buttonStartParty.setIcon(AnonymousClass1uW.getDrawableFromResource(this.mRoot, R.drawable.oc_icon_phone_filled_24_d2d2d2));
            OCTextView oCTextView = this.socialListHeaderText;
            C11051qV.A02(oCTextView, oCTextView.getResources().getString(R.string.anytime_tablet_social_people_header));
        }
        AnonymousClass1uW.executeBindingsOn(this.buttonStartParty);
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeButtonStartParty((CommonTabletRectangularButtonBinding) obj, i2);
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialFriendsHeaderBinding
    public void setParty(@Nullable SocialParty socialParty) {
        this.mParty = socialParty;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(29);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialFriendsHeaderBinding
    public void setShowStartParty(boolean z) {
        this.mShowStartParty = z;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(102);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (29 == i) {
            setParty((SocialParty) obj);
            return true;
        } else if (102 != i) {
            return false;
        } else {
            setShowStartParty(((Boolean) obj).booleanValue());
            return true;
        }
    }

    @Override // X.AnonymousClass1uW
    public void setLifecycleOwner(@Nullable AnonymousClass0AS r2) {
        super.setLifecycleOwner(r2);
        this.buttonStartParty.setLifecycleOwner(r2);
    }

    public AnytimeTabletSocialFriendsHeaderBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 4, sIncludes, (SparseIntArray) null));
    }

    public AnytimeTabletSocialFriendsHeaderBindingImpl(AbstractC003408r r11, View view, Object[] objArr) {
        super(r11, view, 1, (ShellButton) objArr[2], (CommonTabletRectangularButtonBinding) objArr[3], (ConstraintLayout) objArr[0], (OCTextView) objArr[1]);
        this.mDirtyFlags = -1;
        this.buttonGotoParty.setTag(null);
        this.friendsHeader.setTag(null);
        this.socialListHeaderText.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
