package com.oculus.panelapp.dogfood.tabs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import com.oculus.panelapp.dogfood.DogfoodPanelApp;
import com.oculus.panelapp.dogfood.R;
import com.oculus.panelapp.dogfood.tabs.DogfoodTabHost;

public class DogfoodResetTab extends RelativeLayout implements DogfoodTabHost.DogfoodTab {
    private Button mAcceptButton;
    private Button mDeclineButton;

    public DogfoodResetTab(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.oculus.panelapp.dogfood.tabs.DogfoodTabHost.DogfoodTab
    public void initialize(DogfoodPanelApp dogfoodPanelApp, DogfoodTabHost dogfoodTabHost) {
        dogfoodTabHost.addTab("reset", "OTA", R.id.dogfood_ota_tab);
        this.mDeclineButton.setText(R.string.dogfood_reject_reset_button_text);
        this.mAcceptButton.setText(R.string.dogfood_accept_reset_button_text);
        this.mDeclineButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.dogfood.tabs.DogfoodResetTab.AnonymousClass1 */

            public void onClick(View view) {
            }
        });
        this.mAcceptButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.dogfood.tabs.DogfoodResetTab.AnonymousClass2 */

            public void onClick(View view) {
            }
        });
    }
}
