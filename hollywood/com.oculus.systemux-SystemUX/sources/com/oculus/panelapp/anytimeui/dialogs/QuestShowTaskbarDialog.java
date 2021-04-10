package com.oculus.panelapp.anytimeui.dialogs;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import com.oculus.panelapp.anytimeui.R;

public class QuestShowTaskbarDialog extends Dialog {
    private static final String TAG = LoggingUtil.tag(QuestShowTaskbarDialog.class);
    private SettingsObserverCallback mHandTrackingSettingsObserverCallback;
    private Handler mHandler;
    private SettingsManager mSettingsManager = new SettingsManager();

    public QuestShowTaskbarDialog(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHandler = new Handler(context.getMainLooper());
    }

    @Override // com.oculus.panelapp.anytimeui.dialogs.Dialog
    public void initialize() {
        super.initialize();
        updateText();
        this.mHandTrackingSettingsObserverCallback = new SettingsObserverCallback() {
            /* class com.oculus.panelapp.anytimeui.dialogs.QuestShowTaskbarDialog.AnonymousClass1 */

            @Override // com.oculus.os.SettingsObserverCallback
            public void onSettingChange(String str) {
                QuestShowTaskbarDialog.this.updateText();
            }
        };
        this.mSettingsManager.registerSettingsObserver(SettingsManager.HAND_TRACKING_ENABLED, this.mHandTrackingSettingsObserverCallback, this.mHandler);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateText() {
        TextView textView = (TextView) findViewById(R.id.text_view);
        if (textView != null) {
            textView.setText(getResources().getString(this.mSettingsManager.getBoolean(SettingsManager.HAND_TRACKING_ENABLED, false) ? R.string.quest_show_taskbar_hands_dialog_description : R.string.quest_show_taskbar_dialog_description));
        }
    }
}
