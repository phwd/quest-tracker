package com.oculus.vrshell.panels.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import com.oculus.ocui.OCConstants;
import com.oculus.vrshell.panels.CompositeOnClickListener;
import com.oculus.vrshell.panels.CompositeOnHoverListener;
import com.oculus.vrshell.panels.ViewState;

@Deprecated
public class ShellButton extends Button {
    public boolean isViewStateListenerSet;
    public final CompositeOnClickListener mCompositeClickListener;
    public final CompositeOnHoverListener mCompositeHoverListener;

    public void clearOnClickListener() {
        this.mCompositeClickListener.clearListeners();
    }

    public void clearOnHoverListener() {
        this.mCompositeHoverListener.clearListeners();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mCompositeClickListener.addListener(onClickListener);
    }

    public void setOnHoverListener(View.OnHoverListener onHoverListener) {
        this.mCompositeHoverListener.addListener(onHoverListener);
    }

    public ShellButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        CompositeOnClickListener compositeOnClickListener = new CompositeOnClickListener();
        this.mCompositeClickListener = compositeOnClickListener;
        super.setOnClickListener(compositeOnClickListener);
        CompositeOnHoverListener compositeOnHoverListener = new CompositeOnHoverListener();
        this.mCompositeHoverListener = compositeOnHoverListener;
        super.setOnHoverListener(compositeOnHoverListener);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.isViewStateListenerSet) {
            this.isViewStateListenerSet = true;
            ViewState.setupViewStateListeners(this);
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setAlpha(OCConstants.getOpacity(getResources(), z));
    }
}
