package com.oculus.tablet.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.socialplatform.R;

public abstract class OsigSeekbarBinding extends AnonymousClass1uW {
    @NonNull
    public final ImageView icon;
    @Bindable
    public Drawable mIcon;
    @Bindable
    public int mProgress;
    @Bindable
    public Drawable mProgressDrawable;
    @NonNull
    public final SeekBar seekbar;

    public abstract void setIcon(@Nullable Drawable drawable);

    public abstract void setProgress(int i);

    public abstract void setProgressDrawable(@Nullable Drawable drawable);

    @Nullable
    public Drawable getIcon() {
        return this.mIcon;
    }

    public int getProgress() {
        return this.mProgress;
    }

    @Nullable
    public Drawable getProgressDrawable() {
        return this.mProgressDrawable;
    }

    public OsigSeekbarBinding(Object obj, View view, int i, ImageView imageView, SeekBar seekBar) {
        super(obj, view, i);
        this.icon = imageView;
        this.seekbar = seekBar;
    }

    public static OsigSeekbarBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static OsigSeekbarBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OsigSeekbarBinding) AnonymousClass1uW.bind(obj, view, R.layout.osig_seekbar);
    }

    @NonNull
    public static OsigSeekbarBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static OsigSeekbarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static OsigSeekbarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OsigSeekbarBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.osig_seekbar, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static OsigSeekbarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OsigSeekbarBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.osig_seekbar, null, false);
    }
}
