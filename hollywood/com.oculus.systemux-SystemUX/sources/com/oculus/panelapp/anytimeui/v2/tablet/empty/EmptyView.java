package com.oculus.panelapp.anytimeui.v2.tablet.empty;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.tablet.view.BaseView;
import com.oculus.vrshell.panels.AndroidPanelApp;

public class EmptyView extends BaseView {
    private static final String TAG = LoggingUtil.tag(EmptyView.class);

    public EmptyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "Constructing EmptyView");
    }

    @Override // com.oculus.tablet.view.BaseView
    public void initialize(AndroidPanelApp androidPanelApp, ViewDataBinding viewDataBinding) {
        super.initialize(androidPanelApp, viewDataBinding);
        Log.d(TAG, "Initializing EmptyView");
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        Log.d(TAG, "Destroying EmptyView");
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onShow(String str) {
        Log.d(TAG, "Showing EmptyView");
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
        Log.d(TAG, "Hiding EmptyView");
    }
}
