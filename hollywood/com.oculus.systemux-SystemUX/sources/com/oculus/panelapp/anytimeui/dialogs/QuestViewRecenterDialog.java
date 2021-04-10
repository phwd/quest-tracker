package com.oculus.panelapp.anytimeui.dialogs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.anytimeui.R;

public class QuestViewRecenterDialog extends Dialog {
    private static final String TAG = LoggingUtil.tag(QuestViewRecenterDialog.class);

    public QuestViewRecenterDialog(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.oculus.panelapp.anytimeui.dialogs.Dialog
    public void initialize() {
        if (new SettingsManager().getBoolean(SettingsManager.HAND_TRACKING_ENABLED, false)) {
            ((TextView) findViewById(R.id.text_view)).setText(getResources().getString(R.string.quest_view_recenter_dialog_description_hands));
        }
    }
}
