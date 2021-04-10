package com.oculus.common.quickpromotion.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.quickpromotion.R;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;

public abstract class QpTooltipViewBinding extends ViewDataBinding {
    @NonNull
    public final OCButton dismissCtaIcon;
    @NonNull
    public final ImageView tooltipCaretDown;
    @NonNull
    public final ImageView tooltipCaretLeft;
    @NonNull
    public final ImageView tooltipCaretRight;
    @NonNull
    public final ImageView tooltipCaretUp;
    @NonNull
    public final ImageView tooltipIcon;
    @NonNull
    public final View tooltipMain;
    @NonNull
    public final OCTextView tooltipText;

    protected QpTooltipViewBinding(Object obj, View view, int i, OCButton oCButton, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, View view2, OCTextView oCTextView) {
        super(obj, view, i);
        this.dismissCtaIcon = oCButton;
        this.tooltipCaretDown = imageView;
        this.tooltipCaretLeft = imageView2;
        this.tooltipCaretRight = imageView3;
        this.tooltipCaretUp = imageView4;
        this.tooltipIcon = imageView5;
        this.tooltipMain = view2;
        this.tooltipText = oCTextView;
    }

    @NonNull
    public static QpTooltipViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static QpTooltipViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (QpTooltipViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.qp_tooltip_view, viewGroup, z, obj);
    }

    @NonNull
    public static QpTooltipViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static QpTooltipViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (QpTooltipViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.qp_tooltip_view, null, false, obj);
    }

    public static QpTooltipViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static QpTooltipViewBinding bind(@NonNull View view, @Nullable Object obj) {
        return (QpTooltipViewBinding) bind(obj, view, R.layout.qp_tooltip_view);
    }
}
