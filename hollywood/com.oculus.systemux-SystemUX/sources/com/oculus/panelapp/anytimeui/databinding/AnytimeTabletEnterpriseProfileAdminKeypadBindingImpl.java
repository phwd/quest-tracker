package com.oculus.panelapp.anytimeui.databinding;

import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.facebook.systrace.Systrace;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileViewModel;

public class AnytimeTabletEnterpriseProfileAdminKeypadBindingImpl extends AnytimeTabletEnterpriseProfileAdminKeypadBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(23);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sIncludes.setIncludes(0, new String[]{"anytime_tablet_enterprise_admin_keypad_button", "anytime_tablet_enterprise_admin_keypad_button", "anytime_tablet_enterprise_admin_keypad_button", "anytime_tablet_enterprise_admin_keypad_button", "anytime_tablet_enterprise_admin_keypad_button", "anytime_tablet_enterprise_admin_keypad_button", "anytime_tablet_enterprise_admin_keypad_button", "anytime_tablet_enterprise_admin_keypad_button", "anytime_tablet_enterprise_admin_keypad_button", "anytime_tablet_enterprise_admin_keypad_button", "anytime_tablet_enterprise_admin_keypad_button"}, new int[]{9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, new int[]{R.layout.anytime_tablet_enterprise_admin_keypad_button, R.layout.anytime_tablet_enterprise_admin_keypad_button, R.layout.anytime_tablet_enterprise_admin_keypad_button, R.layout.anytime_tablet_enterprise_admin_keypad_button, R.layout.anytime_tablet_enterprise_admin_keypad_button, R.layout.anytime_tablet_enterprise_admin_keypad_button, R.layout.anytime_tablet_enterprise_admin_keypad_button, R.layout.anytime_tablet_enterprise_admin_keypad_button, R.layout.anytime_tablet_enterprise_admin_keypad_button, R.layout.anytime_tablet_enterprise_admin_keypad_button, R.layout.anytime_tablet_enterprise_admin_keypad_button});
        sViewsWithIds.put(R.id.admin_keypad_title, 20);
        sViewsWithIds.put(R.id.admin_keypad_bubble_space, 21);
        sViewsWithIds.put(R.id.admin_keypad_bubble_guideline, 22);
    }

    public AnytimeTabletEnterpriseProfileAdminKeypadBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 23, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletEnterpriseProfileAdminKeypadBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 12, (AnytimeTabletEnterpriseAdminKeypadButtonBinding) objArr[18], (AnytimeTabletEnterpriseAdminKeypadButtonBinding) objArr[9], (AnytimeTabletEnterpriseAdminKeypadButtonBinding) objArr[10], (AnytimeTabletEnterpriseAdminKeypadButtonBinding) objArr[11], (AnytimeTabletEnterpriseAdminKeypadButtonBinding) objArr[12], (AnytimeTabletEnterpriseAdminKeypadButtonBinding) objArr[13], (AnytimeTabletEnterpriseAdminKeypadButtonBinding) objArr[14], (AnytimeTabletEnterpriseAdminKeypadButtonBinding) objArr[15], (AnytimeTabletEnterpriseAdminKeypadButtonBinding) objArr[16], (AnytimeTabletEnterpriseAdminKeypadButtonBinding) objArr[17], (AnytimeTabletEnterpriseAdminKeypadButtonBinding) objArr[19], (Guideline) objArr[22], (View) objArr[21], (ConstraintLayout) objArr[0], (View) objArr[1], (View) objArr[2], (View) objArr[3], (View) objArr[4], (View) objArr[5], (View) objArr[6], (View) objArr[7], (View) objArr[8], (OCTextView) objArr[20]);
        this.mDirtyFlags = -1;
        this.adminKeypadContainer.setTag(null);
        this.adminKeypadEmptyBubble0.setTag(null);
        this.adminKeypadEmptyBubble1.setTag(null);
        this.adminKeypadEmptyBubble2.setTag(null);
        this.adminKeypadEmptyBubble3.setTag(null);
        this.adminKeypadFilledBubble0.setTag(null);
        this.adminKeypadFilledBubble1.setTag(null);
        this.adminKeypadFilledBubble2.setTag(null);
        this.adminKeypadFilledBubble3.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 65536;
        }
        this.adminKeypad1.invalidateAll();
        this.adminKeypad2.invalidateAll();
        this.adminKeypad3.invalidateAll();
        this.adminKeypad4.invalidateAll();
        this.adminKeypad5.invalidateAll();
        this.adminKeypad6.invalidateAll();
        this.adminKeypad7.invalidateAll();
        this.adminKeypad8.invalidateAll();
        this.adminKeypad9.invalidateAll();
        this.adminKeypad0.invalidateAll();
        this.adminKeypadBackspace.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.adminKeypad2.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r4.adminKeypad3.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        if (r4.adminKeypad4.hasPendingBindings() == false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        if (r4.adminKeypad5.hasPendingBindings() == false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0040, code lost:
        if (r4.adminKeypad6.hasPendingBindings() == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0042, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0049, code lost:
        if (r4.adminKeypad7.hasPendingBindings() == false) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004b, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0052, code lost:
        if (r4.adminKeypad8.hasPendingBindings() == false) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0054, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005b, code lost:
        if (r4.adminKeypad9.hasPendingBindings() == false) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005d, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0064, code lost:
        if (r4.adminKeypad0.hasPendingBindings() == false) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0066, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006d, code lost:
        if (r4.adminKeypadBackspace.hasPendingBindings() == false) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x006f, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0070, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.adminKeypad1.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeTabletEnterpriseProfileAdminKeypadBindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.viewModel != i) {
            return false;
        }
        setViewModel((EnterpriseProfileViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletEnterpriseProfileAdminKeypadBinding
    public void setViewModel(@Nullable EnterpriseProfileViewModel enterpriseProfileViewModel) {
        updateRegistration(11, enterpriseProfileViewModel);
        this.mViewModel = enterpriseProfileViewModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.adminKeypad1.setLifecycleOwner(lifecycleOwner);
        this.adminKeypad2.setLifecycleOwner(lifecycleOwner);
        this.adminKeypad3.setLifecycleOwner(lifecycleOwner);
        this.adminKeypad4.setLifecycleOwner(lifecycleOwner);
        this.adminKeypad5.setLifecycleOwner(lifecycleOwner);
        this.adminKeypad6.setLifecycleOwner(lifecycleOwner);
        this.adminKeypad7.setLifecycleOwner(lifecycleOwner);
        this.adminKeypad8.setLifecycleOwner(lifecycleOwner);
        this.adminKeypad9.setLifecycleOwner(lifecycleOwner);
        this.adminKeypad0.setLifecycleOwner(lifecycleOwner);
        this.adminKeypadBackspace.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeAdminKeypad9((AnytimeTabletEnterpriseAdminKeypadButtonBinding) obj, i2);
            case 1:
                return onChangeAdminKeypad5((AnytimeTabletEnterpriseAdminKeypadButtonBinding) obj, i2);
            case 2:
                return onChangeAdminKeypad1((AnytimeTabletEnterpriseAdminKeypadButtonBinding) obj, i2);
            case 3:
                return onChangeAdminKeypad6((AnytimeTabletEnterpriseAdminKeypadButtonBinding) obj, i2);
            case 4:
                return onChangeAdminKeypad2((AnytimeTabletEnterpriseAdminKeypadButtonBinding) obj, i2);
            case 5:
                return onChangeAdminKeypad7((AnytimeTabletEnterpriseAdminKeypadButtonBinding) obj, i2);
            case 6:
                return onChangeAdminKeypad3((AnytimeTabletEnterpriseAdminKeypadButtonBinding) obj, i2);
            case 7:
                return onChangeAdminKeypad8((AnytimeTabletEnterpriseAdminKeypadButtonBinding) obj, i2);
            case 8:
                return onChangeAdminKeypad4((AnytimeTabletEnterpriseAdminKeypadButtonBinding) obj, i2);
            case 9:
                return onChangeAdminKeypadBackspace((AnytimeTabletEnterpriseAdminKeypadButtonBinding) obj, i2);
            case 10:
                return onChangeAdminKeypad0((AnytimeTabletEnterpriseAdminKeypadButtonBinding) obj, i2);
            case 11:
                return onChangeViewModel((EnterpriseProfileViewModel) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeAdminKeypad9(AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeAdminKeypad5(AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeAdminKeypad1(AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeAdminKeypad6(AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeAdminKeypad2(AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeAdminKeypad7(AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeAdminKeypad3(AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeAdminKeypad8(AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeAdminKeypad4(AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeAdminKeypadBackspace(AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeAdminKeypad0(AnytimeTabletEnterpriseAdminKeypadButtonBinding anytimeTabletEnterpriseAdminKeypadButtonBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1024;
        }
        return true;
    }

    private boolean onChangeViewModel(EnterpriseProfileViewModel enterpriseProfileViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        } else if (i == BR.keypadEntryLength) {
            synchronized (this) {
                this.mDirtyFlags |= 20480;
            }
            return true;
        } else if (i == BR.isKeypadThrottled) {
            synchronized (this) {
                this.mDirtyFlags |= 24576;
            }
            return true;
        } else if (i == BR.filledKeypadBubble) {
            synchronized (this) {
                this.mDirtyFlags |= 8192;
            }
            return true;
        } else if (i != BR.isKeypadBackspaceEnabled) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 16384;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        Drawable drawable;
        boolean z;
        int i;
        int i2;
        boolean z2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        long j2;
        int i9;
        int i10;
        int i11;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        EnterpriseProfileViewModel enterpriseProfileViewModel = this.mViewModel;
        if ((129024 & j) != 0) {
            boolean isKeypadBackspaceEnabled = ((j & 83968) == 0 || enterpriseProfileViewModel == null) ? false : enterpriseProfileViewModel.getIsKeypadBackspaceEnabled();
            int i12 = ((j & 71680) > 0 ? 1 : ((j & 71680) == 0 ? 0 : -1));
            if (i12 != 0) {
                if (enterpriseProfileViewModel != null) {
                    i11 = enterpriseProfileViewModel.getKeypadEntryLength();
                } else {
                    i11 = 0;
                }
                boolean z3 = true;
                boolean z4 = i11 <= 0;
                boolean z5 = i11 > 0;
                z2 = i11 < 4;
                boolean z6 = i11 <= 1;
                boolean z7 = i11 > 3;
                boolean z8 = i11 <= 2;
                boolean z9 = i11 > 2;
                boolean z10 = i11 <= 3;
                if (i11 <= 1) {
                    z3 = false;
                }
                if (i12 != 0) {
                    j |= z4 ? Systrace.TRACE_TAG_COMPONENTS : 2097152;
                }
                if ((j & 71680) != 0) {
                    j |= z5 ? Systrace.TRACE_TAG_PROFILO : Systrace.TRACE_TAG_REACT_JS_VM_CALLS;
                }
                if ((j & 71680) != 0) {
                    j |= z6 ? Systrace.TRACE_TAG_COMPACTDISK : Systrace.TRACE_TAG_APP_CORE_INFRA;
                }
                if ((j & 71680) != 0) {
                    j |= z7 ? 1048576 : 524288;
                }
                if ((j & 71680) != 0) {
                    j |= z8 ? Systrace.TRACE_TAG_REACT_FRESCO : Systrace.TRACE_TAG_VIEW_PERF_HARNESS;
                }
                if ((j & 71680) != 0) {
                    j |= z9 ? 1073741824 : Systrace.TRACE_TAG_COMPONENT_SCRIPT;
                }
                if ((j & 71680) != 0) {
                    j |= z10 ? Systrace.TRACE_TAG_JS_VM : Systrace.TRACE_TAG_REACT_VIEW;
                }
                if ((j & 71680) != 0) {
                    j |= z3 ? 262144 : 131072;
                }
                i8 = z4 ? 0 : 4;
                i = z5 ? 0 : 4;
                i9 = z6 ? 0 : 4;
                int i13 = z7 ? 0 : 4;
                i4 = z8 ? 0 : 4;
                i5 = z9 ? 0 : 4;
                i3 = z10 ? 0 : 4;
                i2 = z3 ? 0 : 4;
                i10 = i13;
            } else {
                i8 = 0;
                i5 = 0;
                i4 = 0;
                i3 = 0;
                z2 = false;
                i2 = 0;
                i = 0;
                i10 = 0;
                i9 = 0;
            }
            if (!((j & 100352) == 0 || enterpriseProfileViewModel == null)) {
                enterpriseProfileViewModel.getIsKeypadThrottled();
            }
            if ((j & 75776) == 0 || enterpriseProfileViewModel == null) {
                i7 = i9;
                drawable = null;
            } else {
                drawable = enterpriseProfileViewModel.getFilledKeypadBubble();
                i7 = i9;
            }
            z = isKeypadBackspaceEnabled;
            i6 = i10;
        } else {
            i8 = 0;
            i7 = 0;
            i6 = 0;
            i5 = 0;
            i4 = 0;
            i3 = 0;
            z2 = false;
            i2 = 0;
            i = 0;
            z = false;
            drawable = null;
        }
        if ((j & 65536) != 0) {
            this.adminKeypad0.setLabel(getRoot().getResources().getString(R.string.anytime_tablet_enterprise_admin_keypad_num_0));
            this.adminKeypad1.setLabel(getRoot().getResources().getString(R.string.anytime_tablet_enterprise_admin_keypad_num_1));
            this.adminKeypad2.setLabel(getRoot().getResources().getString(R.string.anytime_tablet_enterprise_admin_keypad_num_2));
            this.adminKeypad3.setLabel(getRoot().getResources().getString(R.string.anytime_tablet_enterprise_admin_keypad_num_3));
            this.adminKeypad4.setLabel(getRoot().getResources().getString(R.string.anytime_tablet_enterprise_admin_keypad_num_4));
            this.adminKeypad5.setLabel(getRoot().getResources().getString(R.string.anytime_tablet_enterprise_admin_keypad_num_5));
            this.adminKeypad6.setLabel(getRoot().getResources().getString(R.string.anytime_tablet_enterprise_admin_keypad_num_6));
            this.adminKeypad7.setLabel(getRoot().getResources().getString(R.string.anytime_tablet_enterprise_admin_keypad_num_7));
            this.adminKeypad8.setLabel(getRoot().getResources().getString(R.string.anytime_tablet_enterprise_admin_keypad_num_8));
            this.adminKeypad9.setLabel(getRoot().getResources().getString(R.string.anytime_tablet_enterprise_admin_keypad_num_9));
            this.adminKeypadBackspace.setDrawableStart(getDrawableFromResource(getRoot(), R.drawable.oc_icon_keyboard_back_filled_24_d2d2d2));
            this.adminKeypadBackspace.setPaddingStartDip(12);
            j2 = 71680;
        } else {
            j2 = 71680;
        }
        if ((j2 & j) != 0) {
            this.adminKeypad0.setEnabled(Boolean.valueOf(z2));
            this.adminKeypad1.setEnabled(Boolean.valueOf(z2));
            this.adminKeypad2.setEnabled(Boolean.valueOf(z2));
            this.adminKeypad3.setEnabled(Boolean.valueOf(z2));
            this.adminKeypad4.setEnabled(Boolean.valueOf(z2));
            this.adminKeypad5.setEnabled(Boolean.valueOf(z2));
            this.adminKeypad6.setEnabled(Boolean.valueOf(z2));
            this.adminKeypad7.setEnabled(Boolean.valueOf(z2));
            this.adminKeypad8.setEnabled(Boolean.valueOf(z2));
            this.adminKeypad9.setEnabled(Boolean.valueOf(z2));
            this.adminKeypadEmptyBubble0.setVisibility(i8);
            this.adminKeypadEmptyBubble1.setVisibility(i7);
            this.adminKeypadEmptyBubble2.setVisibility(i4);
            this.adminKeypadEmptyBubble3.setVisibility(i3);
            this.adminKeypadFilledBubble0.setVisibility(i);
            this.adminKeypadFilledBubble1.setVisibility(i2);
            this.adminKeypadFilledBubble2.setVisibility(i5);
            this.adminKeypadFilledBubble3.setVisibility(i6);
        }
        if ((83968 & j) != 0) {
            this.adminKeypadBackspace.setEnabled(Boolean.valueOf(z));
        }
        if ((j & 75776) != 0) {
            ViewBindingAdapter.setBackground(this.adminKeypadFilledBubble0, drawable);
            ViewBindingAdapter.setBackground(this.adminKeypadFilledBubble1, drawable);
            ViewBindingAdapter.setBackground(this.adminKeypadFilledBubble2, drawable);
            ViewBindingAdapter.setBackground(this.adminKeypadFilledBubble3, drawable);
        }
        executeBindingsOn(this.adminKeypad1);
        executeBindingsOn(this.adminKeypad2);
        executeBindingsOn(this.adminKeypad3);
        executeBindingsOn(this.adminKeypad4);
        executeBindingsOn(this.adminKeypad5);
        executeBindingsOn(this.adminKeypad6);
        executeBindingsOn(this.adminKeypad7);
        executeBindingsOn(this.adminKeypad8);
        executeBindingsOn(this.adminKeypad9);
        executeBindingsOn(this.adminKeypad0);
        executeBindingsOn(this.adminKeypadBackspace);
    }
}
