package com.oculus.tablet.view;

import X.AnonymousClass1uW;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.SoundType;
import com.oculus.vrshell.panels.ViewState;

public abstract class BaseView extends ConstraintLayout implements ViewState.Listener {
    public long mLastUsedMillis;
    public AndroidPanelApp mPanelApp;

    public abstract void destroy();

    public abstract void onHide();

    public void onShow(String str) {
    }

    @Override // com.oculus.vrshell.panels.ViewState.Listener
    public void onViewExit(View view) {
    }

    public long getLastUsedMillis() {
        return this.mLastUsedMillis;
    }

    public void initialize(AndroidPanelApp androidPanelApp, AnonymousClass1uW r2) {
        this.mPanelApp = androidPanelApp;
        markUsed();
    }

    @Override // com.oculus.vrshell.panels.ViewState.Listener
    public void onViewClick(View view) {
        this.mPanelApp.mFrameCommandChannel.playAudio(SoundType.SELECT);
    }

    @Override // com.oculus.vrshell.panels.ViewState.Listener
    public void onViewEnter(View view) {
        this.mPanelApp.mFrameCommandChannel.playAudio(SoundType.HOVER);
    }

    public void markUsed() {
        this.mLastUsedMillis = System.currentTimeMillis();
    }

    public BaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
