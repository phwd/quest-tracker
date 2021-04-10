package com.oculus.panelapp.socialandroidbackpanel.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.breakpad.BreakpadManager;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.socialandroidbackpanel.views.join_party.JoinPartyView;
import com.oculus.panelapp.socialandroidbackpanel.views.join_party.JoinPartyViewModel;
import com.oculus.panelapp.socialandroidbackpanel.views.list_item.ListItem;
import com.oculus.socialplatform.R;

public class JoinPartyViewBindingImpl extends JoinPartyViewBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeJoinPartyViewModel(JoinPartyViewModel joinPartyViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 204) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == 184) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == 186) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == 199) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == 220) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == 222) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i == 183) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (i == 207) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (i == 213) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (i == 205) {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        } else if (i != 195) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= BreakpadManager.MD_FB_WITH_UNWINDSTACK_STREAM;
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
            this.mDirtyFlags = BreakpadManager.MD_FB_INSTALL_ALT_STACK;
        }
        requestRebind();
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.databinding.JoinPartyViewBinding
    public void setJoinPartyViewModel(@Nullable JoinPartyViewModel joinPartyViewModel) {
        updateRegistration(0, joinPartyViewModel);
        this.mJoinPartyViewModel = joinPartyViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(212);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.join_party_title, 4);
        sparseIntArray.put(R.id.join_party_secondary_button, 5);
        sparseIntArray.put(R.id.join_party_primary_button, 6);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0039, code lost:
        if (r17 == null) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0061, code lost:
        if (r13 == null) goto L_0x0063;
     */
    @Override // X.AnonymousClass1uW
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 538
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.socialandroidbackpanel.databinding.JoinPartyViewBindingImpl.executeBindings():void");
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeJoinPartyViewModel((JoinPartyViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (212 != i) {
            return false;
        }
        setJoinPartyViewModel((JoinPartyViewModel) obj);
        return true;
    }

    public JoinPartyViewBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 7, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public JoinPartyViewBindingImpl(AbstractC003408r r14, View view, Object[] objArr) {
        super(r14, view, 1, (JoinPartyView) objArr[0], (ListItem) objArr[2], (ListItem) objArr[1], (ListItem) objArr[3], (OCButton) objArr[6], (OCButton) objArr[5], (OCTextView) objArr[4]);
        this.mDirtyFlags = -1;
        this.joinParty.setTag(null);
        this.joinPartyDestinationRow.setTag(null);
        this.joinPartyJoiningAsRow.setTag(null);
        this.joinPartyPartyDescriptionRow.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
