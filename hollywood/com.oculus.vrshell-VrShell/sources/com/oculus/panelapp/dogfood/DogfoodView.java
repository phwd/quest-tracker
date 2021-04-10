package com.oculus.panelapp.dogfood;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.oculus.panelapp.dogfood.tabs.DogfoodTabHost;
import com.oculus.vrshell.panels.SoundType;
import com.oculus.vrshell.panels.ViewState;

public final class DogfoodView extends LinearLayout implements ViewState.Listener {
    private static final int[] TAB_IDS = {R.id.dogfood_ota_tab, R.id.dogfood_builds_tab, R.id.dogfood_developer_settings_tab, R.id.dogfood_assignments_tab};
    private DogfoodPanelApp mPanelApp;
    private DogfoodTabHost mTabHost;

    @Override // com.oculus.vrshell.panels.ViewState.Listener
    public void onViewExit(View view) {
    }

    public DogfoodView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(DogfoodPanelApp dogfoodPanelApp) {
        this.mPanelApp = dogfoodPanelApp;
        this.mTabHost = (DogfoodTabHost) findViewById(R.id.dogfood_panel_tabhost);
        this.mTabHost.setup();
        for (int i : TAB_IDS) {
            ((DogfoodTabHost.DogfoodTab) findViewById(i)).initialize(dogfoodPanelApp, this.mTabHost);
        }
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
