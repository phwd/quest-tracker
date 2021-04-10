package com.oculus.vrshell.panels.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import com.oculus.vrshell.panels.CompositeOnClickListener;
import com.oculus.vrshell.panels.CompositeOnHoverListener;
import com.oculus.vrshell.panels.ViewState;

public class ShellButton extends Button {
    private boolean isViewStateListenerSet;
    private final CompositeOnClickListener mCompositeClickListener = new CompositeOnClickListener(this);
    private final CompositeOnHoverListener mCompositeHoverListener;

    public ShellButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setOnClickListener(this.mCompositeClickListener);
        this.mCompositeHoverListener = new CompositeOnHoverListener();
        super.setOnHoverListener(this.mCompositeHoverListener);
    }

    public void clearOnClickListener() {
        this.mCompositeClickListener.clearListeners();
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
}
