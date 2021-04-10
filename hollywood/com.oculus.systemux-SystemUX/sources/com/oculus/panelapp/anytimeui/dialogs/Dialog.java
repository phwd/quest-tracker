package com.oculus.panelapp.anytimeui.dialogs;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.vrshell.panels.GenericInputListener;
import com.oculus.vrshell.panels.SoundType;
import com.oculus.vrshell.panels.ViewState;

public abstract class Dialog extends LinearLayout implements ViewState.Listener, GenericInputListener {
    private static final String TAG = LoggingUtil.tag(Dialog.class);
    private AnytimeUIAndroidPanelAppV2 mPanelApp;

    public void configure(String str) {
    }

    public void destroy() {
    }

    public void initialize() {
    }

    @Override // com.oculus.vrshell.panels.GenericInputListener
    public boolean onActionButton() {
        return false;
    }

    @Override // com.oculus.vrshell.panels.GenericInputListener
    public boolean onBackButton() {
        return false;
    }

    @Override // com.oculus.vrshell.panels.ViewState.Listener
    public void onViewExit(View view) {
    }

    public Dialog(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        if (this.mPanelApp == null) {
            this.mPanelApp = anytimeUIAndroidPanelAppV2;
            try {
                initialize();
            } catch (Exception e) {
                Log.e(TAG, "initialize() failed", e);
                throw e;
            }
        } else {
            throw new RuntimeException("Dialog can only be initialized once!");
        }
    }

    public AnytimeUIAndroidPanelAppV2 getPanelApp() {
        throwIfNotInitialized();
        return this.mPanelApp;
    }

    @Override // com.oculus.vrshell.panels.ViewState.Listener
    public void onViewEnter(View view) {
        throwIfNotInitialized();
        this.mPanelApp.getCommandChannel().playAudio(SoundType.HOVER);
    }

    @Override // com.oculus.vrshell.panels.ViewState.Listener
    public void onViewClick(View view) {
        throwIfNotInitialized();
        this.mPanelApp.getCommandChannel().playAudio(SoundType.SELECT);
    }

    private void throwIfNotInitialized() {
        if (this.mPanelApp == null) {
            throw new RuntimeException("PanelApp is null, was the Dialog initialized?");
        }
    }
}
