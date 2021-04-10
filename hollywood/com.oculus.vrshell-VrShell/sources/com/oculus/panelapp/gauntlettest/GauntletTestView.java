package com.oculus.panelapp.gauntlettest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.oculus.common.logutilities.LoggingUtil;

public class GauntletTestView extends LinearLayout {
    private static String TAG = LoggingUtil.tag(GauntletTestView.class);
    private GauntletTestPanelApp mApp;

    public GauntletTestView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        clearFocus();
        Log.i(TAG, "GauntletTestView created");
    }

    public void initialize(final GauntletTestPanelApp gauntletTestPanelApp) {
        this.mApp = gauntletTestPanelApp;
        ((Button) findViewById(R.id.gauntlet_test_panel_exit_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.gauntlettest.GauntletTestView.AnonymousClass1 */

            public void onClick(View view) {
                gauntletTestPanelApp.getCommandChannel().quit();
            }
        });
        ((Button) findViewById(R.id.gauntlet_test_panel_simplekeyboard)).setOnClickListener(new SimpleKeyboardButtonListener());
    }

    private class SimpleKeyboardButtonListener implements View.OnClickListener {
        public SimpleKeyboardButtonListener() {
        }

        public void onClick(View view) {
            if (GauntletTestView.this.mApp != null) {
                GauntletTestView.this.mApp.getCommandChannel().openKeyboard("simplekeyboard", 0.0f, 0.0f, "", "TEXT_SIMPLE", "");
            }
        }
    }
}
