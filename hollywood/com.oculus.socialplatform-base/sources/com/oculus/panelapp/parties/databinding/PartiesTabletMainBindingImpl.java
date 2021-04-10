package com.oculus.panelapp.parties.databinding;

import X.AbstractC003408r;
import X.AnonymousClass0AS;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.parties.views.PartiesView;
import com.oculus.panelapp.parties.views.PartiesViewModel;
import com.oculus.socialplatform.R;

public class PartiesTabletMainBindingImpl extends PartiesTabletMainBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;
    @NonNull
    public final OCTextView mboundView1;
    @NonNull
    public final OCSpinner mboundView2;

    static {
        AnonymousClass1ui r4 = new AnonymousClass1ui(7);
        sIncludes = r4;
        r4.A00(new String[]{"parties_tablet_party_header", "party_travel_footer"}, new int[]{4, 5}, new int[]{R.layout.parties_tablet_party_header, R.layout.party_travel_footer});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.header_guideline, 6);
    }

    private boolean onChangePartiesViewModel(PartiesViewModel partiesViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == 6) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == 22) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == 18) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == 28) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i != 13) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
    }

    private boolean onChangePartyFooter(PartyTravelFooterBinding partyTravelFooterBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangePartyHeader(PartiesTabletPartyHeaderBinding partiesTabletPartyHeaderBinding, int i) {
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
        if (i != 0 || this.partyHeader.hasPendingBindings() || this.partyFooter.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256;
        }
        this.partyHeader.invalidateAll();
        this.partyFooter.invalidateAll();
        requestRebind();
    }

    @Override // com.oculus.panelapp.parties.databinding.PartiesTabletMainBinding
    public void setPartiesViewModel(@Nullable PartiesViewModel partiesViewModel) {
        updateRegistration(2, partiesViewModel);
        this.mPartiesViewModel = partiesViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(27);
        super.requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0042, code lost:
        if (r10 != false) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005b, code lost:
        if (r13 != false) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0075, code lost:
        if (r14 != false) goto L_0x0077;
     */
    @Override // X.AnonymousClass1uW
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 259
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.parties.databinding.PartiesTabletMainBindingImpl.executeBindings():void");
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangePartyFooter((PartyTravelFooterBinding) obj, i2);
        }
        if (i == 1) {
            return onChangePartyHeader((PartiesTabletPartyHeaderBinding) obj, i2);
        }
        if (i != 2) {
            return false;
        }
        return onChangePartiesViewModel((PartiesViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (27 != i) {
            return false;
        }
        setPartiesViewModel((PartiesViewModel) obj);
        return true;
    }

    @Override // X.AnonymousClass1uW
    public void setLifecycleOwner(@Nullable AnonymousClass0AS r2) {
        super.setLifecycleOwner(r2);
        this.partyHeader.setLifecycleOwner(r2);
        this.partyFooter.setLifecycleOwner(r2);
    }

    public PartiesTabletMainBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 7, sIncludes, sViewsWithIds));
    }

    public PartiesTabletMainBindingImpl(AbstractC003408r r12, View view, Object[] objArr) {
        super(r12, view, 3, (Guideline) objArr[6], (OCRecyclerView) objArr[3], (PartiesView) objArr[0], (PartyTravelFooterBinding) objArr[5], (PartiesTabletPartyHeaderBinding) objArr[4]);
        this.mDirtyFlags = -1;
        this.horizontalRecycler.setTag(null);
        OCTextView oCTextView = (OCTextView) objArr[1];
        this.mboundView1 = oCTextView;
        oCTextView.setTag(null);
        OCSpinner oCSpinner = (OCSpinner) objArr[2];
        this.mboundView2 = oCSpinner;
        oCSpinner.setTag(null);
        this.partiesView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
