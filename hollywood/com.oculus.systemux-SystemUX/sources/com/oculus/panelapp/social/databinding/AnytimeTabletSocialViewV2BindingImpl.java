package com.oculus.panelapp.social.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSpinner;
import com.oculus.panelapp.social.BR;
import com.oculus.panelapp.social.R;
import com.oculus.panelapp.social.SocialView;

public class AnytimeTabletSocialViewV2BindingImpl extends AnytimeTabletSocialViewV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(7);
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    @NonNull
    private final SocialView mboundView0;

    static {
        sIncludes.setIncludes(0, new String[]{"anytime_tablet_social_friends_header", "anytime_tablet_social_party_header", "anytime_tablet_social_party_footer"}, new int[]{4, 5, 6}, new int[]{R.layout.anytime_tablet_social_friends_header, R.layout.anytime_tablet_social_party_header, R.layout.anytime_tablet_social_party_footer});
    }

    public AnytimeTabletSocialViewV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletSocialViewV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 3, (AnytimeTabletSocialFriendsHeaderBinding) objArr[4], (OCRecyclerView) objArr[2], (AnytimeTabletSocialPartyFooterBinding) objArr[6], (AnytimeTabletSocialPartyHeaderBinding) objArr[5], (OCRecyclerView) objArr[3], (OCSpinner) objArr[1]);
        this.mDirtyFlags = -1;
        this.friendsList.setTag(null);
        this.mboundView0 = (SocialView) objArr[0];
        this.mboundView0.setTag(null);
        this.partyList.setTag(null);
        this.secondaryLoadingSpinner.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 512;
        }
        this.friendsHeader.invalidateAll();
        this.partyHeader.invalidateAll();
        this.partyFooter.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.partyHeader.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r4.partyFooter.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0028, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.friendsHeader.hasPendingBindings() == false) goto L_0x0016;
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
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x002a }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x002a }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x002a }
            com.oculus.panelapp.social.databinding.AnytimeTabletSocialFriendsHeaderBinding r0 = r4.friendsHeader
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyHeaderBinding r0 = r4.partyHeader
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r1
        L_0x001f:
            com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyFooterBinding r0 = r4.partyFooter
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0028
            return r1
        L_0x0028:
            r0 = 0
            return r0
        L_0x002a:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2BindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.showLoading == i) {
            setShowLoading(((Boolean) obj).booleanValue());
        } else if (BR.party == i) {
            setParty((SocialParty) obj);
        } else if (BR.showPartyFooter == i) {
            setShowPartyFooter(((Boolean) obj).booleanValue());
        } else if (BR.canShowStartParty == i) {
            setCanShowStartParty(((Boolean) obj).booleanValue());
        } else if (BR.muted == i) {
            setMuted(((Boolean) obj).booleanValue());
        } else if (BR.view != i) {
            return false;
        } else {
            setView((SocialView.SocialViewType) obj);
        }
        return true;
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2Binding
    public void setShowLoading(boolean z) {
        this.mShowLoading = z;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.showLoading);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2Binding
    public void setParty(@Nullable SocialParty socialParty) {
        this.mParty = socialParty;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.party);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2Binding
    public void setShowPartyFooter(boolean z) {
        this.mShowPartyFooter = z;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.showPartyFooter);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2Binding
    public void setCanShowStartParty(boolean z) {
        this.mCanShowStartParty = z;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.canShowStartParty);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2Binding
    public void setMuted(boolean z) {
        this.mMuted = z;
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2Binding
    public void setView(@Nullable SocialView.SocialViewType socialViewType) {
        this.mView = socialViewType;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(BR.view);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.friendsHeader.setLifecycleOwner(lifecycleOwner);
        this.partyHeader.setLifecycleOwner(lifecycleOwner);
        this.partyFooter.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangePartyFooter((AnytimeTabletSocialPartyFooterBinding) obj, i2);
        }
        if (i == 1) {
            return onChangePartyHeader((AnytimeTabletSocialPartyHeaderBinding) obj, i2);
        }
        if (i != 2) {
            return false;
        }
        return onChangeFriendsHeader((AnytimeTabletSocialFriendsHeaderBinding) obj, i2);
    }

    private boolean onChangePartyFooter(AnytimeTabletSocialPartyFooterBinding anytimeTabletSocialPartyFooterBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangePartyHeader(AnytimeTabletSocialPartyHeaderBinding anytimeTabletSocialPartyHeaderBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeFriendsHeader(AnytimeTabletSocialFriendsHeaderBinding anytimeTabletSocialFriendsHeaderBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0172  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0103  */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 394
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2BindingImpl.executeBindings():void");
    }
}
