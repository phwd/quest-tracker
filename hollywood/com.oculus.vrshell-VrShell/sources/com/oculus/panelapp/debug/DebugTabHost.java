package com.oculus.panelapp.debug;

import android.content.Context;
import android.util.AttributeSet;
import com.oculus.vrshell.uicommon.MainNavTabHost;

public class DebugTabHost extends MainNavTabHost {

    public interface DebugTab {
        void initialize(ShellDebugPanelApp shellDebugPanelApp, DebugTabHost debugTabHost);
    }

    public DebugTabHost(Context context) {
        super(context);
    }

    public DebugTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DebugTabHost(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
