package com.oculus.common.ocui.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class OctooltipBinding extends AnonymousClass1uW {
    @NonNull
    public final OCButton dismissCtaIcon;
    @NonNull
    public final View tooltipBody;
    @NonNull
    public final ImageView tooltipCaretDown;
    @NonNull
    public final ImageView tooltipCaretLeft;
    @NonNull
    public final ImageView tooltipCaretRight;
    @NonNull
    public final ImageView tooltipCaretUp;
    @NonNull
    public final ConstraintLayout tooltipContainer;
    @NonNull
    public final ImageView tooltipIcon;
    @NonNull
    public final OCTextView tooltipText;

    public OctooltipBinding(Object obj, View view, int i, OCButton oCButton, View view2, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ConstraintLayout constraintLayout, ImageView imageView5, OCTextView oCTextView) {
        super(obj, view, i);
        this.dismissCtaIcon = oCButton;
        this.tooltipBody = view2;
        this.tooltipCaretDown = imageView;
        this.tooltipCaretLeft = imageView2;
        this.tooltipCaretRight = imageView3;
        this.tooltipCaretUp = imageView4;
        this.tooltipContainer = constraintLayout;
        this.tooltipIcon = imageView5;
        this.tooltipText = oCTextView;
    }

    public static OctooltipBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static OctooltipBinding bind(@NonNull View view, @Nullable Object obj) {
        return (OctooltipBinding) AnonymousClass1uW.bind(obj, view, R.layout.octooltip);
    }

    @NonNull
    public static OctooltipBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static OctooltipBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static OctooltipBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OctooltipBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.octooltip, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static OctooltipBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (OctooltipBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.octooltip, null, false);
    }
}
