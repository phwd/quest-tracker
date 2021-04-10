package com.oculus.panelapp.managedlauncher;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import com.oculus.common.logutilities.LoggingUtil;

public class ManagedLauncherView extends FrameLayout {
    private static final String TAG = LoggingUtil.tag(ManagedLauncherView.class);
    private ManagedLauncherPanelApp mPanelApp;

    public ManagedLauncherView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.i(TAG, "ManagedLauncherView created");
    }

    public void initialize(ManagedLauncherPanelApp managedLauncherPanelApp) {
        this.mPanelApp = managedLauncherPanelApp;
        setAlpha(0.0f);
    }
}
