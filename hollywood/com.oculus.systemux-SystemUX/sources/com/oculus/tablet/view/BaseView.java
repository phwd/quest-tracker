package com.oculus.tablet.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.SoundType;
import com.oculus.vrshell.panels.ViewState;

public abstract class BaseView extends ConstraintLayout implements ViewState.Listener {
    private long mLastUsedMillis;
    private AndroidPanelApp mPanelApp;

    public abstract void destroy();

    public abstract void onHide();

    public void onShow(String str) {
    }

    @Override // com.oculus.vrshell.panels.ViewState.Listener
    public void onViewExit(View view) {
    }

    public BaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(AndroidPanelApp androidPanelApp, ViewDataBinding viewDataBinding) {
        this.mPanelApp = androidPanelApp;
        markUsed();
    }

    public void markUsed() {
        this.mLastUsedMillis = System.currentTimeMillis();
    }

    public long getLastUsedMillis() {
        return this.mLastUsedMillis;
    }

    @Override // com.oculus.vrshell.panels.ViewState.Listener
    public void onViewEnter(View view) {
        this.mPanelApp.getCommandChannel().playAudio(SoundType.HOVER);
    }

    @Override // com.oculus.vrshell.panels.ViewState.Listener
    public void onViewClick(View view) {
        this.mPanelApp.getCommandChannel().playAudio(SoundType.SELECT);
    }
}
