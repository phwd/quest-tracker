package com.oculus.panelapp.anytimeui.v2.tablet.sharing;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCConstants;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.SharingThumbnailButtonBinding;
import com.oculus.vrshell.panels.CompositeOnClickListener;
import com.oculus.vrshell.panels.CompositeOnHoverListener;
import com.oculus.vrshell.panels.ViewState;

public class SharingThumbnailButton extends LinearLayout {
    private boolean isViewStateListenerSet = false;
    private SharingThumbnailButtonBinding mBinding;
    private CompositeOnClickListener mCompositeClickListener;
    private CompositeOnHoverListener mCompositeHoverListener;

    public SharingThumbnailButton(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBinding = SharingThumbnailButtonBinding.inflate(LayoutInflater.from(context), this, true);
        this.mCompositeClickListener = new CompositeOnClickListener();
        super.setOnClickListener(this.mCompositeClickListener);
        this.mCompositeHoverListener = new CompositeOnHoverListener();
        super.setOnHoverListener(this.mCompositeHoverListener);
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.ocimagecard_inner_zoom);
        loadAnimation.reset();
        this.mBinding.thumbnailImage.setOnHoverListener(new View.OnHoverListener(loadAnimation) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.sharing.$$Lambda$SharingThumbnailButton$VDYFL3tUgXWHMXGulZe6gnwI3s */
            private final /* synthetic */ Animation f$0;

            {
                this.f$0 = r1;
            }

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return SharingThumbnailButton.lambda$new$488(this.f$0, view, motionEvent);
            }
        });
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SharingThumbnailButton);
        try {
            this.mBinding.setThumbnail(obtainStyledAttributes.getDrawable(R.styleable.SharingThumbnailButton_thumbnail));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    static /* synthetic */ boolean lambda$new$488(Animation animation, View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            view.startAnimation(animation);
            return false;
        } else if (actionMasked != 10) {
            return false;
        } else {
            view.clearAnimation();
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.isViewStateListenerSet) {
            this.isViewStateListenerSet = true;
            ViewState.setupViewStateListeners(this);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mCompositeClickListener.addListener(onClickListener);
    }

    public void setOnHoverListener(View.OnHoverListener onHoverListener) {
        this.mCompositeHoverListener.addListener(onHoverListener);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setAlpha(OCConstants.getOpacity(getResources(), z));
    }

    public void setThumbnail(Drawable drawable) {
        this.mBinding.setThumbnail(drawable);
    }

    public void setIsVideo(boolean z) {
        this.mBinding.setIsVideo(z);
    }
}
