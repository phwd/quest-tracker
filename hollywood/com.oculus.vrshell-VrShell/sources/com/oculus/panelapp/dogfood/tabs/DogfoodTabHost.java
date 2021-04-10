package com.oculus.panelapp.dogfood.tabs;

import android.content.Context;
import android.util.AttributeSet;
import com.oculus.panelapp.dogfood.DogfoodPanelApp;
import com.oculus.vrshell.uicommon.MainNavTabHost;

public class DogfoodTabHost extends MainNavTabHost {

    public interface DogfoodTab {
        void initialize(DogfoodPanelApp dogfoodPanelApp, DogfoodTabHost dogfoodTabHost);
    }

    public DogfoodTabHost(Context context) {
        super(context);
    }

    public DogfoodTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DogfoodTabHost(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
