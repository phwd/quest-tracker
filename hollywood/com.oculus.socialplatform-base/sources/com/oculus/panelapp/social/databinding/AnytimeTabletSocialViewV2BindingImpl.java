package com.oculus.panelapp.social.databinding;

import X.AbstractC003408r;
import X.AnonymousClass0AS;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSpinner;
import com.oculus.panelapp.social.SocialView;
import com.oculus.socialplatform.R;

public class AnytimeTabletSocialViewV2BindingImpl extends AnytimeTabletSocialViewV2Binding {
    @Nullable
    public static final AnonymousClass1ui sIncludes;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;
    @NonNull
    public final SocialView mboundView0;

    static {
        AnonymousClass1ui r5 = new AnonymousClass1ui(7);
        sIncludes = r5;
        r5.A00(new String[]{"anytime_tablet_social_friends_header", "anytime_tablet_social_party_header", "anytime_tablet_social_party_footer"}, new int[]{4, 5, 6}, new int[]{R.layout.anytime_tablet_social_friends_header, R.layout.anytime_tablet_social_party_header, R.layout.anytime_tablet_social_party_footer});
    }

    private boolean onChangeFriendsHeader(AnytimeTabletSocialFriendsHeaderBinding anytimeTabletSocialFriendsHeaderBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangePartyFooter(AnytimeTabletSocialPartyFooterBinding anytimeTabletSocialPartyFooterBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangePartyHeader(AnytimeTabletSocialPartyHeaderBinding anytimeTabletSocialPartyHeaderBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    @Override // X.AnonymousClass1uW
    public boolean hasPendingBindings() {
        int i;
        synchronized (this) {
            i = (this.mDirtyFlags > 0 ? 1 : (this.mDirtyFlags == 0 ? 0 : -1));
        }
        if (i != 0 || this.friendsHeader.hasPendingBindings() || this.partyHeader.hasPendingBindings() || this.partyFooter.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 512;
        }
        this.friendsHeader.invalidateAll();
        this.partyHeader.invalidateAll();
        this.partyFooter.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0051, code lost:
        if (r8 != false) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x008b, code lost:
        if (r17 != false) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00cf, code lost:
        if (r13 != true) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x00e7, code lost:
        if (r14 != false) goto L_0x00e9;
     */
    @Override // X.AnonymousClass1uW
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 390
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2BindingImpl.executeBindings():void");
    }

    @Override // X.AnonymousClass1uW
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

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2Binding
    public void setCanShowStartParty(boolean z) {
        this.mCanShowStartParty = z;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(97);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2Binding
    public void setParty(@Nullable SocialParty socialParty) {
        this.mParty = socialParty;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(29);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2Binding
    public void setShowLoading(boolean z) {
        this.mShowLoading = z;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(90);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2Binding
    public void setShowPartyFooter(boolean z) {
        this.mShowPartyFooter = z;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(95);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (90 == i) {
            setShowLoading(((Boolean) obj).booleanValue());
            return true;
        } else if (29 == i) {
            setParty((SocialParty) obj);
            return true;
        } else if (95 == i) {
            setShowPartyFooter(((Boolean) obj).booleanValue());
            return true;
        } else if (97 == i) {
            setCanShowStartParty(((Boolean) obj).booleanValue());
            return true;
        } else if (99 == i) {
            setMuted(((Boolean) obj).booleanValue());
            return true;
        } else if (94 != i) {
            return false;
        } else {
            setView((SocialView.SocialViewType) obj);
            return true;
        }
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2Binding
    public void setView(@Nullable SocialView.SocialViewType socialViewType) {
        this.mView = socialViewType;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(94);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public void setLifecycleOwner(@Nullable AnonymousClass0AS r2) {
        super.setLifecycleOwner(r2);
        this.friendsHeader.setLifecycleOwner(r2);
        this.partyHeader.setLifecycleOwner(r2);
        this.partyFooter.setLifecycleOwner(r2);
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2Binding
    public void setMuted(boolean z) {
        this.mMuted = z;
    }

    public AnytimeTabletSocialViewV2BindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 7, sIncludes, (SparseIntArray) null));
    }

    public AnytimeTabletSocialViewV2BindingImpl(AbstractC003408r r13, View view, Object[] objArr) {
        super(r13, view, 3, (AnytimeTabletSocialFriendsHeaderBinding) objArr[4], (OCRecyclerView) objArr[2], (AnytimeTabletSocialPartyFooterBinding) objArr[6], (AnytimeTabletSocialPartyHeaderBinding) objArr[5], (OCRecyclerView) objArr[3], (OCSpinner) objArr[1]);
        this.mDirtyFlags = -1;
        this.friendsList.setTag(null);
        SocialView socialView = (SocialView) objArr[0];
        this.mboundView0 = socialView;
        socialView.setTag(null);
        this.partyList.setTag(null);
        this.secondaryLoadingSpinner.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
