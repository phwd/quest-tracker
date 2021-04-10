package com.oculus.panelapp.bugreporter.common;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import com.oculus.panelapp.bugreporter.BugReporterPanelApp;
import com.oculus.panelapp.bugreporter.util.BugReporterUtil;

public abstract class BaseView extends ConstraintLayout {
    public abstract void initialize(BugReporterPanelApp bugReporterPanelApp, BugReporterUtil bugReporterUtil, ViewDataBinding viewDataBinding);

    public abstract void onHide();

    public BaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
