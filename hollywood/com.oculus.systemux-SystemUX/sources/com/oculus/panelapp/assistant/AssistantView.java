package com.oculus.panelapp.assistant;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import com.oculus.common.logutilities.LoggingUtil;

public class AssistantView extends FrameLayout {
    private static String TAG = LoggingUtil.tag(AssistantView.class);
    public AssistantPanelApp mPanelApp;

    public AssistantView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.i(TAG, "AssistantView created");
    }

    public void initialize(AssistantPanelApp assistantPanelApp) {
        this.mPanelApp = assistantPanelApp;
    }
}
