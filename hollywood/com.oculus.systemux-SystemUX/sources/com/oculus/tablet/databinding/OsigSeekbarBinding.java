package com.oculus.tablet.databinding;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.tablet.R;

public abstract class OsigSeekbarBinding extends ViewDataBinding {
    @NonNull
    public final ImageView icon;
    @Bindable
    protected Drawable mIcon;
    @Bindable
    protected int mProgress;
    @Bindable
    protected Drawable mProgressDrawable;
    @NonNull
    public final SeekBar seekbar;

    public abstract void setIcon(@Nullable Drawable drawable);

    public abstract void setProgress(int i);

    public abstract void setProgressDrawable(@Nullable Drawable drawable);

    protected OsigSeekbarBinding(Object obj, View view, int i, ImageView imageView, SeekBar seekBar) {
        super(obj, view, i);
        this.icon = imageView;
        this.seekbar = seekBar;
    }

    @Nullable
    public Drawable getIcon() {
        return this.mIcon;
    }

    @Nullable
    public Drawable getProgressDrawable() {
        return this.mProgressDrawable;
    }

    public int getProgress() {
        return this.mProgress;
    }

    @NonNull
    public static OsigSeekbarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OsigSeekbarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (OsigSeekbarBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.osig_seekbar, viewGroup, z, obj);
    }

    @NonNull
    public static OsigSeekbarBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static OsigSeekbarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (OsigSeekbarBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.osig_seekbar, null, false, obj);
    }

    public static OsigSeekbarBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static OsigSeekbarBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OsigSeekbarBinding) bind(obj, view, R.layout.osig_seekbar);
    }
}
