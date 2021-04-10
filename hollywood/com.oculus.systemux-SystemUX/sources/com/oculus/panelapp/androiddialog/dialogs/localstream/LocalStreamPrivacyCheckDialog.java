package com.oculus.panelapp.androiddialog.dialogs.localstream;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCToggle;
import com.oculus.panelapp.androiddialog.AndroidDialogPanelApp;
import com.oculus.panelapp.androiddialog.Dialog;
import com.oculus.panelapp.androiddialog.databinding.LocalStreamPrivacyCheckBinding;
import java.util.function.Consumer;

public class LocalStreamPrivacyCheckDialog extends ConstraintLayout implements Dialog {
    private static final String ACTION_LOCAL_STREAM_PRIVACY_CHECK_RESPONSE = "com.oculus.systemactivities.LOCAL_STREAM_PRIVACY_CHECK.RESPONSE";
    private static final int ALLOW_BROADCAST_RESULT = 1;
    private static final int ALLOW_BROADCAST_RESULT_WITH_PRIVACY_CHECK_DISABLED = 2;
    private static final String ALLOW_DIALOG_RESULT = "allow";
    private static final String ALLOW_DIALOG_RESULT_WITH_PRIVACY_CHECK_DISABLED = "allow_and_disable_privacy_check";
    private static final int DENY_BROADCAST_RESULT = -1;
    private static final String DENY_DIALOG_RESULT = "deny";
    private static final String LOCAL_STREAM_PACKAGE = "com.oculus.companion.server";
    private static final String LOCAL_STREAM_PERMISSION = "com.oculus.companion.server.permission.SEND_INTENTS";
    private static final String TAG = LoggingUtil.tag(LocalStreamPrivacyCheckDialog.class);
    private final Context mContext;
    AndroidDialogPanelApp mPanelApp;

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public void destroy() {
    }

    public LocalStreamPrivacyCheckDialog(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "LocalStreamPrivacyCheckDialog dialog constructed");
        this.mContext = context;
    }

    @VisibleForTesting
    static void initialize(Consumer<String> consumer, Consumer<Integer> consumer2, OCToggle oCToggle, OCButton oCButton, OCButton oCButton2) {
        oCButton.setOnClickListener(new View.OnClickListener(consumer2, consumer) {
            /* class com.oculus.panelapp.androiddialog.dialogs.localstream.$$Lambda$LocalStreamPrivacyCheckDialog$3yOYLIHadMIS3I9DnlGFP299RpU */
            private final /* synthetic */ Consumer f$0;
            private final /* synthetic */ Consumer f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                LocalStreamPrivacyCheckDialog.lambda$initialize$74(this.f$0, this.f$1, view);
            }
        });
        oCButton2.setOnClickListener(new View.OnClickListener(consumer2, consumer) {
            /* class com.oculus.panelapp.androiddialog.dialogs.localstream.$$Lambda$LocalStreamPrivacyCheckDialog$SIAiGYYEgOdZfFzpCYVoDHtGorw */
            private final /* synthetic */ Consumer f$1;
            private final /* synthetic */ Consumer f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                LocalStreamPrivacyCheckDialog.lambda$initialize$75(OCToggle.this, this.f$1, this.f$2, view);
            }
        });
    }

    static /* synthetic */ void lambda$initialize$74(Consumer consumer, Consumer consumer2, View view) {
        consumer.accept(-1);
        consumer2.accept("deny");
    }

    static /* synthetic */ void lambda$initialize$75(OCToggle oCToggle, Consumer consumer, Consumer consumer2, View view) {
        if (oCToggle.isChecked()) {
            consumer.accept(2);
            consumer2.accept(ALLOW_DIALOG_RESULT_WITH_PRIVACY_CHECK_DISABLED);
            return;
        }
        consumer.accept(1);
        consumer2.accept("allow");
    }

    public void initialize(AndroidDialogPanelApp androidDialogPanelApp, LocalStreamPrivacyCheckBinding localStreamPrivacyCheckBinding) {
        this.mPanelApp = androidDialogPanelApp;
        androidDialogPanelApp.getClass();
        initialize(new Consumer() {
            /* class com.oculus.panelapp.androiddialog.dialogs.localstream.$$Lambda$dD6_e6eGIv8wIlUu8XcSIAsQkPY */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AndroidDialogPanelApp.this.sendResult((String) obj);
            }
        }, new Consumer() {
            /* class com.oculus.panelapp.androiddialog.dialogs.localstream.$$Lambda$LocalStreamPrivacyCheckDialog$92bXec5XkllKQoCEVSvclXi24M */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                LocalStreamPrivacyCheckDialog.this.broadcastPrivacyCheckResult((Integer) obj);
            }
        }, localStreamPrivacyCheckBinding.disablePrivacyCheckToggle, localStreamPrivacyCheckBinding.denyButton, localStreamPrivacyCheckBinding.allowButton);
    }

    /* access modifiers changed from: private */
    public void broadcastPrivacyCheckResult(Integer num) {
        Intent intent = new Intent(ACTION_LOCAL_STREAM_PRIVACY_CHECK_RESPONSE);
        intent.putExtra("allowed", num);
        intent.setPackage("com.oculus.companion.server");
        this.mContext.sendBroadcast(intent, LOCAL_STREAM_PERMISSION);
        String str = TAG;
        Log.d(str, "Broadcast intent com.oculus.systemactivities.LOCAL_STREAM_PRIVACY_CHECK.RESPONSE allowed: " + num);
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public boolean onControllerBackButton() {
        this.mPanelApp.closeDialog();
        return true;
    }
}
