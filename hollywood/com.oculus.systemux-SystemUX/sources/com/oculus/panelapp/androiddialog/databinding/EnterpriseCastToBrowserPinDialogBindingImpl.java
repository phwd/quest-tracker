package com.oculus.panelapp.androiddialog.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.text.Html;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.enterprise.EnterpriseCastToBrowserPinDialog;

public class EnterpriseCastToBrowserPinDialogBindingImpl extends EnterpriseCastToBrowserPinDialogBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(10);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sIncludes.setIncludes(0, new String[]{"dialog_title", "enterprise_cast_to_browser_pin_digit", "enterprise_cast_to_browser_pin_digit", "enterprise_cast_to_browser_pin_digit", "enterprise_cast_to_browser_pin_digit", "enterprise_cast_to_browser_pin_digit", "enterprise_cast_to_browser_pin_digit"}, new int[]{2, 3, 4, 5, 6, 7, 8}, new int[]{R.layout.dialog_title, R.layout.enterprise_cast_to_browser_pin_digit, R.layout.enterprise_cast_to_browser_pin_digit, R.layout.enterprise_cast_to_browser_pin_digit, R.layout.enterprise_cast_to_browser_pin_digit, R.layout.enterprise_cast_to_browser_pin_digit, R.layout.enterprise_cast_to_browser_pin_digit});
        sViewsWithIds.put(R.id.enterprise_cast_to_browser_pin_dialog_cancel_button, 9);
    }

    public EnterpriseCastToBrowserPinDialogBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds));
    }

    private EnterpriseCastToBrowserPinDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 7, (EnterpriseCastToBrowserPinDialog) objArr[0], (OCTextView) objArr[1], (OCButton) objArr[9], (DialogTitleBinding) objArr[2], (EnterpriseCastToBrowserPinDigitBinding) objArr[3], (EnterpriseCastToBrowserPinDigitBinding) objArr[4], (EnterpriseCastToBrowserPinDigitBinding) objArr[5], (EnterpriseCastToBrowserPinDigitBinding) objArr[6], (EnterpriseCastToBrowserPinDigitBinding) objArr[7], (EnterpriseCastToBrowserPinDigitBinding) objArr[8]);
        this.mDirtyFlags = -1;
        this.enterpriseCastToBrowserPinDialog.setTag(null);
        this.enterpriseCastToBrowserPinDialogBody.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8192;
        }
        this.enterpriseCastToBrowserPinDialogTitle.invalidateAll();
        this.enterpriseCastToBrowserPinDigit0.invalidateAll();
        this.enterpriseCastToBrowserPinDigit1.invalidateAll();
        this.enterpriseCastToBrowserPinDigit2.invalidateAll();
        this.enterpriseCastToBrowserPinDigit3.invalidateAll();
        this.enterpriseCastToBrowserPinDigit4.invalidateAll();
        this.enterpriseCastToBrowserPinDigit5.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.enterpriseCastToBrowserPinDigit0.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r4.enterpriseCastToBrowserPinDigit1.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        if (r4.enterpriseCastToBrowserPinDigit2.hasPendingBindings() == false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        if (r4.enterpriseCastToBrowserPinDigit3.hasPendingBindings() == false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0040, code lost:
        if (r4.enterpriseCastToBrowserPinDigit4.hasPendingBindings() == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0042, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0049, code lost:
        if (r4.enterpriseCastToBrowserPinDigit5.hasPendingBindings() == false) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004b, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004c, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.enterpriseCastToBrowserPinDialogTitle.hasPendingBindings() == false) goto L_0x0016;
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
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x004e }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x004e }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x004e }
            com.oculus.panelapp.androiddialog.databinding.DialogTitleBinding r0 = r4.enterpriseCastToBrowserPinDialogTitle
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.oculus.panelapp.androiddialog.databinding.EnterpriseCastToBrowserPinDigitBinding r0 = r4.enterpriseCastToBrowserPinDigit0
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r1
        L_0x001f:
            com.oculus.panelapp.androiddialog.databinding.EnterpriseCastToBrowserPinDigitBinding r0 = r4.enterpriseCastToBrowserPinDigit1
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0028
            return r1
        L_0x0028:
            com.oculus.panelapp.androiddialog.databinding.EnterpriseCastToBrowserPinDigitBinding r0 = r4.enterpriseCastToBrowserPinDigit2
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0031
            return r1
        L_0x0031:
            com.oculus.panelapp.androiddialog.databinding.EnterpriseCastToBrowserPinDigitBinding r0 = r4.enterpriseCastToBrowserPinDigit3
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x003a
            return r1
        L_0x003a:
            com.oculus.panelapp.androiddialog.databinding.EnterpriseCastToBrowserPinDigitBinding r0 = r4.enterpriseCastToBrowserPinDigit4
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0043
            return r1
        L_0x0043:
            com.oculus.panelapp.androiddialog.databinding.EnterpriseCastToBrowserPinDigitBinding r0 = r4.enterpriseCastToBrowserPinDigit5
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x004c
            return r1
        L_0x004c:
            r0 = 0
            return r0
        L_0x004e:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.databinding.EnterpriseCastToBrowserPinDialogBindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.digit0 == i) {
            setDigit0((String) obj);
        } else if (BR.digit4 == i) {
            setDigit4((String) obj);
        } else if (BR.digit3 == i) {
            setDigit3((String) obj);
        } else if (BR.digit2 == i) {
            setDigit2((String) obj);
        } else if (BR.digit1 == i) {
            setDigit1((String) obj);
        } else if (BR.digit5 != i) {
            return false;
        } else {
            setDigit5((String) obj);
        }
        return true;
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.EnterpriseCastToBrowserPinDialogBinding
    public void setDigit0(@Nullable String str) {
        this.mDigit0 = str;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(BR.digit0);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.EnterpriseCastToBrowserPinDialogBinding
    public void setDigit4(@Nullable String str) {
        this.mDigit4 = str;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(BR.digit4);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.EnterpriseCastToBrowserPinDialogBinding
    public void setDigit3(@Nullable String str) {
        this.mDigit3 = str;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(BR.digit3);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.EnterpriseCastToBrowserPinDialogBinding
    public void setDigit2(@Nullable String str) {
        this.mDigit2 = str;
        synchronized (this) {
            this.mDirtyFlags |= 1024;
        }
        notifyPropertyChanged(BR.digit2);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.EnterpriseCastToBrowserPinDialogBinding
    public void setDigit1(@Nullable String str) {
        this.mDigit1 = str;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        notifyPropertyChanged(BR.digit1);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.EnterpriseCastToBrowserPinDialogBinding
    public void setDigit5(@Nullable String str) {
        this.mDigit5 = str;
        synchronized (this) {
            this.mDirtyFlags |= 4096;
        }
        notifyPropertyChanged(BR.digit5);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.enterpriseCastToBrowserPinDialogTitle.setLifecycleOwner(lifecycleOwner);
        this.enterpriseCastToBrowserPinDigit0.setLifecycleOwner(lifecycleOwner);
        this.enterpriseCastToBrowserPinDigit1.setLifecycleOwner(lifecycleOwner);
        this.enterpriseCastToBrowserPinDigit2.setLifecycleOwner(lifecycleOwner);
        this.enterpriseCastToBrowserPinDigit3.setLifecycleOwner(lifecycleOwner);
        this.enterpriseCastToBrowserPinDigit4.setLifecycleOwner(lifecycleOwner);
        this.enterpriseCastToBrowserPinDigit5.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeEnterpriseCastToBrowserPinDialogTitle((DialogTitleBinding) obj, i2);
            case 1:
                return onChangeEnterpriseCastToBrowserPinDigit5((EnterpriseCastToBrowserPinDigitBinding) obj, i2);
            case 2:
                return onChangeEnterpriseCastToBrowserPinDigit4((EnterpriseCastToBrowserPinDigitBinding) obj, i2);
            case 3:
                return onChangeEnterpriseCastToBrowserPinDigit3((EnterpriseCastToBrowserPinDigitBinding) obj, i2);
            case 4:
                return onChangeEnterpriseCastToBrowserPinDigit2((EnterpriseCastToBrowserPinDigitBinding) obj, i2);
            case 5:
                return onChangeEnterpriseCastToBrowserPinDigit1((EnterpriseCastToBrowserPinDigitBinding) obj, i2);
            case 6:
                return onChangeEnterpriseCastToBrowserPinDigit0((EnterpriseCastToBrowserPinDigitBinding) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeEnterpriseCastToBrowserPinDialogTitle(DialogTitleBinding dialogTitleBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeEnterpriseCastToBrowserPinDigit5(EnterpriseCastToBrowserPinDigitBinding enterpriseCastToBrowserPinDigitBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeEnterpriseCastToBrowserPinDigit4(EnterpriseCastToBrowserPinDigitBinding enterpriseCastToBrowserPinDigitBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeEnterpriseCastToBrowserPinDigit3(EnterpriseCastToBrowserPinDigitBinding enterpriseCastToBrowserPinDigitBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeEnterpriseCastToBrowserPinDigit2(EnterpriseCastToBrowserPinDigitBinding enterpriseCastToBrowserPinDigitBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeEnterpriseCastToBrowserPinDigit1(EnterpriseCastToBrowserPinDigitBinding enterpriseCastToBrowserPinDigitBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeEnterpriseCastToBrowserPinDigit0(EnterpriseCastToBrowserPinDigitBinding enterpriseCastToBrowserPinDigitBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = this.mDigit0;
        String str2 = this.mDigit4;
        String str3 = this.mDigit3;
        String str4 = this.mDigit2;
        String str5 = this.mDigit1;
        String str6 = this.mDigit5;
        int i = ((8320 & j) > 0 ? 1 : ((8320 & j) == 0 ? 0 : -1));
        int i2 = ((8448 & j) > 0 ? 1 : ((8448 & j) == 0 ? 0 : -1));
        int i3 = ((8704 & j) > 0 ? 1 : ((8704 & j) == 0 ? 0 : -1));
        int i4 = ((9216 & j) > 0 ? 1 : ((9216 & j) == 0 ? 0 : -1));
        int i5 = ((10240 & j) > 0 ? 1 : ((10240 & j) == 0 ? 0 : -1));
        int i6 = ((j & 12288) > 0 ? 1 : ((j & 12288) == 0 ? 0 : -1));
        if ((j & 8192) != 0) {
            TextViewBindingAdapter.setText(this.enterpriseCastToBrowserPinDialogBody, Html.fromHtml(this.enterpriseCastToBrowserPinDialogBody.getResources().getString(R.string.enterprise_cast_to_browser_dialog_body_text)));
            this.enterpriseCastToBrowserPinDialogTitle.setDialogTitleText(getRoot().getResources().getString(R.string.enterprise_cast_to_browser_dialog_title));
        }
        if (i != 0) {
            this.enterpriseCastToBrowserPinDigit0.setDigit(str);
        }
        if (i5 != 0) {
            this.enterpriseCastToBrowserPinDigit1.setDigit(str5);
        }
        if (i4 != 0) {
            this.enterpriseCastToBrowserPinDigit2.setDigit(str4);
        }
        if (i3 != 0) {
            this.enterpriseCastToBrowserPinDigit3.setDigit(str3);
        }
        if (i2 != 0) {
            this.enterpriseCastToBrowserPinDigit4.setDigit(str2);
        }
        if (i6 != 0) {
            this.enterpriseCastToBrowserPinDigit5.setDigit(str6);
        }
        executeBindingsOn(this.enterpriseCastToBrowserPinDialogTitle);
        executeBindingsOn(this.enterpriseCastToBrowserPinDigit0);
        executeBindingsOn(this.enterpriseCastToBrowserPinDigit1);
        executeBindingsOn(this.enterpriseCastToBrowserPinDigit2);
        executeBindingsOn(this.enterpriseCastToBrowserPinDigit3);
        executeBindingsOn(this.enterpriseCastToBrowserPinDigit4);
        executeBindingsOn(this.enterpriseCastToBrowserPinDigit5);
    }
}
