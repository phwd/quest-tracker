package com.oculus.panelapp.anytimeui.databinding;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.library.model.App;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCHighlight;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;

public abstract class AnytimeTabletLibrarySystemAppButtonV2Binding extends ViewDataBinding {
    @NonNull
    public final OCButton button;
    @NonNull
    public final OCHighlight highlight;
    @NonNull
    public final ImageView iconImage;
    @Bindable
    protected Drawable mColoredBackground;
    @Bindable
    protected boolean mHighlight;
    @Bindable
    protected Drawable mIcon;
    @Bindable
    protected boolean mIsHovered;
    @Bindable
    protected App mSystemApp;
    @Bindable
    protected String mText;
    @NonNull
    public final ProgressBar progressBar;
    @NonNull
    public final OCTextView textView;
    @NonNull
    public final View updateDot;

    public abstract void setColoredBackground(@Nullable Drawable drawable);

    public abstract void setHighlight(boolean z);

    public abstract void setIcon(@Nullable Drawable drawable);

    public abstract void setIsHovered(boolean z);

    public abstract void setSystemApp(@Nullable App app);

    public abstract void setText(@Nullable String str);

    protected AnytimeTabletLibrarySystemAppButtonV2Binding(Object obj, View view, int i, OCButton oCButton, OCHighlight oCHighlight, ImageView imageView, ProgressBar progressBar2, OCTextView oCTextView, View view2) {
        super(obj, view, i);
        this.button = oCButton;
        this.highlight = oCHighlight;
        this.iconImage = imageView;
        this.progressBar = progressBar2;
        this.textView = oCTextView;
        this.updateDot = view2;
    }

    @Nullable
    public Drawable getIcon() {
        return this.mIcon;
    }

    @Nullable
    public String getText() {
        return this.mText;
    }

    @Nullable
    public App getSystemApp() {
        return this.mSystemApp;
    }

    public boolean getIsHovered() {
        return this.mIsHovered;
    }

    @Nullable
    public Drawable getColoredBackground() {
        return this.mColoredBackground;
    }

    public boolean getHighlight() {
        return this.mHighlight;
    }

    @NonNull
    public static AnytimeTabletLibrarySystemAppButtonV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLibrarySystemAppButtonV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletLibrarySystemAppButtonV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_library_system_app_button_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletLibrarySystemAppButtonV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLibrarySystemAppButtonV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletLibrarySystemAppButtonV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_library_system_app_button_v2, null, false, obj);
    }

    public static AnytimeTabletLibrarySystemAppButtonV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletLibrarySystemAppButtonV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletLibrarySystemAppButtonV2Binding) bind(obj, view, R.layout.anytime_tablet_library_system_app_button_v2);
    }
}
