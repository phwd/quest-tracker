package com.oculus.tablet.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1pW;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.socialplatform.R;

public class OsigSeekbarBindingImpl extends OsigSeekbarBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;
    @NonNull
    public final ConstraintLayout mboundView0;

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
            this.mDirtyFlags = 8;
        }
        requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        long j2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Drawable drawable = this.mProgressDrawable;
        Drawable drawable2 = this.mIcon;
        Drawable drawable3 = null;
        int i = this.mProgress;
        long j3 = j & 9;
        boolean z = false;
        if (j3 != 0) {
            if (drawable != null) {
                z = true;
            }
            if (j3 != 0) {
                if (z) {
                    j2 = 32;
                } else {
                    j2 = 16;
                }
                j |= j2;
            }
        }
        long j4 = 10 & j;
        long j5 = 12 & j;
        long j6 = j & 9;
        if (j6 != 0) {
            if (!z) {
                drawable = AnonymousClass1pW.A00(this.seekbar.getContext(), R.drawable.anytime_tablet_settings_seekbar_progress_v2);
            }
            drawable3 = drawable;
        }
        if (j4 != 0) {
            this.icon.setImageDrawable(drawable2);
        }
        if (j5 != 0) {
            SeekBar seekBar = this.seekbar;
            if (i != seekBar.getProgress()) {
                seekBar.setProgress(i);
            }
        }
        if (j6 != 0) {
            this.seekbar.setProgressDrawable(drawable3);
        }
    }

    @Override // com.oculus.tablet.databinding.OsigSeekbarBinding
    public void setIcon(@Nullable Drawable drawable) {
        this.mIcon = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(211);
        super.requestRebind();
    }

    @Override // com.oculus.tablet.databinding.OsigSeekbarBinding
    public void setProgress(int i) {
        this.mProgress = i;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(179);
        super.requestRebind();
    }

    @Override // com.oculus.tablet.databinding.OsigSeekbarBinding
    public void setProgressDrawable(@Nullable Drawable drawable) {
        this.mProgressDrawable = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(233);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (233 == i) {
            setProgressDrawable((Drawable) obj);
            return true;
        } else if (211 == i) {
            setIcon((Drawable) obj);
            return true;
        } else if (179 != i) {
            return false;
        } else {
            setProgress(((Number) obj).intValue());
            return true;
        }
    }

    public OsigSeekbarBindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 3, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public OsigSeekbarBindingImpl(AbstractC003408r r9, View view, Object[] objArr) {
        super(r9, view, 0, (ImageView) objArr[2], (SeekBar) objArr[1]);
        this.mDirtyFlags = -1;
        this.icon.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.seekbar.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
