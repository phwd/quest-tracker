package com.oculus.panelapp.social.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.BR;
import com.oculus.tablet.R;
import com.oculus.tablet.databinding.CommonTabletRectangularButtonBinding;
import com.oculus.vrshell.panels.views.ShellButton;

public class AnytimeTabletSocialFriendsHeaderBindingImpl extends AnytimeTabletSocialFriendsHeaderBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(4);
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    static {
        sIncludes.setIncludes(0, new String[]{"common_tablet_rectangular_button"}, new int[]{3}, new int[]{R.layout.common_tablet_rectangular_button});
    }

    public AnytimeTabletSocialFriendsHeaderBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletSocialFriendsHeaderBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ShellButton) objArr[2], (CommonTabletRectangularButtonBinding) objArr[3], (ConstraintLayout) objArr[0], (OCTextView) objArr[1]);
        this.mDirtyFlags = -1;
        this.buttonGotoParty.setTag(null);
        this.friendsHeader.setTag(null);
        this.socialListHeaderText.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
        }
        this.buttonStartParty.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.buttonStartParty.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r4 = this;
            monitor-enter(r4)
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x0018 }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x0018 }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x0018 }
            com.oculus.tablet.databinding.CommonTabletRectangularButtonBinding r0 = r4.buttonStartParty
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            r0 = 0
            return r0
        L_0x0018:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.databinding.AnytimeTabletSocialFriendsHeaderBindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.party == i) {
            setParty((SocialParty) obj);
        } else if (BR.showStartParty != i) {
            return false;
        } else {
            setShowStartParty(((Boolean) obj).booleanValue());
        }
        return true;
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialFriendsHeaderBinding
    public void setParty(@Nullable SocialParty socialParty) {
        this.mParty = socialParty;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.party);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialFriendsHeaderBinding
    public void setShowStartParty(boolean z) {
        this.mShowStartParty = z;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.showStartParty);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.buttonStartParty.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeButtonStartParty((CommonTabletRectangularButtonBinding) obj, i2);
    }

    private boolean onChangeButtonStartParty(CommonTabletRectangularButtonBinding commonTabletRectangularButtonBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        int i;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SocialParty socialParty = this.mParty;
        boolean z = this.mShowStartParty;
        String str = null;
        int i2 = ((j & 10) > 0 ? 1 : ((j & 10) == 0 ? 0 : -1));
        int i3 = 0;
        if (i2 != 0) {
            boolean z2 = socialParty == null;
            if (i2 != 0) {
                j |= z2 ? 32 : 16;
            }
            int size = socialParty != null ? socialParty.getSize() : 0;
            i = z2 ? 8 : 0;
            str = String.format(this.buttonGotoParty.getResources().getString(com.oculus.panelapp.social.R.string.anytime_tablet_social_active_party), Integer.valueOf(size));
        } else {
            i = 0;
        }
        int i4 = ((j & 12) > 0 ? 1 : ((j & 12) == 0 ? 0 : -1));
        if (i4 != 0) {
            if (i4 != 0) {
                j |= z ? 128 : 64;
            }
            if (!z) {
                i3 = 8;
            }
        }
        if ((10 & j) != 0) {
            TextViewBindingAdapter.setText(this.buttonGotoParty, str);
            this.buttonGotoParty.setVisibility(i);
        }
        if ((j & 12) != 0) {
            this.buttonStartParty.getRoot().setVisibility(i3);
        }
        if ((j & 8) != 0) {
            this.buttonStartParty.setText(getRoot().getResources().getString(com.oculus.panelapp.social.R.string.anytime_tablet_social_start_party));
            this.buttonStartParty.setIcon(getDrawableFromResource(getRoot(), com.oculus.panelapp.social.R.drawable.oc_icon_phone_filled_24_d2d2d2));
            TextViewBindingAdapter.setText(this.socialListHeaderText, this.socialListHeaderText.getResources().getString(com.oculus.panelapp.social.R.string.anytime_tablet_social_people_header));
        }
        executeBindingsOn(this.buttonStartParty);
    }
}
