package com.oculus.panelapp.androiddialog.dialogs.enterprise;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.androiddialog.AndroidDialogPanelApp;
import com.oculus.panelapp.androiddialog.Dialog;
import com.oculus.panelapp.androiddialog.databinding.EnterpriseCastToBrowserPinDialogBinding;
import org.json.JSONException;
import org.json.JSONObject;

public final class EnterpriseCastToBrowserPinDialog extends ConstraintLayout implements Dialog {
    private static final String BROADCAST_CAST_WWW_ANSWER_RECEIVED = "broadcast_cast_www_answer_received";
    private static final String HORIZON_COMPONENT_NAME = "com.oculus.horizon.service_media.OVRMediaService";
    private static final String HORIZON_MESSAGE_TYPE_KEY = "message_type";
    private static final String HORIZON_PACKAGE_NAME = "com.oculus.horizon";
    private static final String HORIZON_STOP_LOCAL_STREAM_ACTION = "com.oculus.horizon.STOP_LOCAL_STREAM";
    private static final String PIN_PARAM = "pin";
    private static final String TAG = LoggingUtil.tag(EnterpriseCastToBrowserPinDialog.class);
    private static final int TIMEOUT_MS = 60000;
    private EnterpriseCastToBrowserPinDialogBinding mBinding;
    private Context mContext;
    private AndroidDialogPanelApp mPanelApp;
    private String mPin;
    private Handler mTimeoutHandler;

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public boolean onControllerBackButton() {
        return false;
    }

    public EnterpriseCastToBrowserPinDialog(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.i(TAG, "Constructed EnterpriseCastToBrowserPinDialog.");
        this.mContext = context;
    }

    public void initialize(AndroidDialogPanelApp androidDialogPanelApp, EnterpriseCastToBrowserPinDialogBinding enterpriseCastToBrowserPinDialogBinding, JSONObject jSONObject) {
        this.mPanelApp = androidDialogPanelApp;
        this.mBinding = enterpriseCastToBrowserPinDialogBinding;
        initializeParameters(jSONObject);
        initializeDigits();
        initializeDismissCheck();
        initializeCancelButton();
    }

    private void initializeDismissCheck() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_CAST_WWW_ANSWER_RECEIVED);
        this.mContext.registerReceiver(new StreamingStartedBroadcastReceiver(), intentFilter);
        this.mTimeoutHandler = new Handler();
        this.mTimeoutHandler.postDelayed(new Runnable() {
            /* class com.oculus.panelapp.androiddialog.dialogs.enterprise.EnterpriseCastToBrowserPinDialog.AnonymousClass1 */

            public void run() {
                Log.d(EnterpriseCastToBrowserPinDialog.TAG, "Closing due to timeout while waiting for answer");
                EnterpriseCastToBrowserPinDialog.this.destroy();
            }
        }, 60000);
    }

    private void initializeCancelButton() {
        this.mBinding.enterpriseCastToBrowserPinDialogCancelButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.enterprise.$$Lambda$EnterpriseCastToBrowserPinDialog$o3XCN_PehEcZJYQFjQG0yEOOCkE */

            public final void onClick(View view) {
                EnterpriseCastToBrowserPinDialog.this.lambda$initializeCancelButton$1$EnterpriseCastToBrowserPinDialog(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeCancelButton$1$EnterpriseCastToBrowserPinDialog(View view) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.horizon", HORIZON_COMPONENT_NAME));
        intent.putExtra(HORIZON_MESSAGE_TYPE_KEY, HORIZON_STOP_LOCAL_STREAM_ACTION);
        this.mContext.startService(intent);
        destroy();
    }

    private void initializeParameters(JSONObject jSONObject) {
        try {
            this.mPin = jSONObject.getString("pin");
        } catch (JSONException e) {
            Log.e(TAG, "Error initializing parameters", e);
        }
    }

    private void initializeDigits() {
        String str = this.mPin;
        if (str == null || str.length() != 6) {
            String str2 = TAG;
            Log.e(str2, "Invalid PIN received: " + this.mPin);
            return;
        }
        EnterpriseCastToBrowserPinDialogBinding enterpriseCastToBrowserPinDialogBinding = this.mBinding;
        enterpriseCastToBrowserPinDialogBinding.setDigit0("" + this.mPin.charAt(0));
        EnterpriseCastToBrowserPinDialogBinding enterpriseCastToBrowserPinDialogBinding2 = this.mBinding;
        enterpriseCastToBrowserPinDialogBinding2.setDigit1("" + this.mPin.charAt(1));
        EnterpriseCastToBrowserPinDialogBinding enterpriseCastToBrowserPinDialogBinding3 = this.mBinding;
        enterpriseCastToBrowserPinDialogBinding3.setDigit2("" + this.mPin.charAt(2));
        EnterpriseCastToBrowserPinDialogBinding enterpriseCastToBrowserPinDialogBinding4 = this.mBinding;
        enterpriseCastToBrowserPinDialogBinding4.setDigit3("" + this.mPin.charAt(3));
        EnterpriseCastToBrowserPinDialogBinding enterpriseCastToBrowserPinDialogBinding5 = this.mBinding;
        enterpriseCastToBrowserPinDialogBinding5.setDigit4("" + this.mPin.charAt(4));
        EnterpriseCastToBrowserPinDialogBinding enterpriseCastToBrowserPinDialogBinding6 = this.mBinding;
        enterpriseCastToBrowserPinDialogBinding6.setDigit5("" + this.mPin.charAt(5));
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public synchronized void destroy() {
        this.mTimeoutHandler.removeCallbacksAndMessages(null);
        this.mPanelApp.closeDialog();
    }

    /* access modifiers changed from: private */
    public class StreamingStartedBroadcastReceiver extends BroadcastReceiver {
        private StreamingStartedBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null || !action.equals(EnterpriseCastToBrowserPinDialog.BROADCAST_CAST_WWW_ANSWER_RECEIVED)) {
                String str = EnterpriseCastToBrowserPinDialog.TAG;
                Log.w(str, "Received an unrecognized intent: " + action);
                return;
            }
            EnterpriseCastToBrowserPinDialog.this.mTimeoutHandler.removeCallbacksAndMessages(null);
            EnterpriseCastToBrowserPinDialog.this.mPanelApp.sendResult(EnterpriseCastToBrowserPinDialog.BROADCAST_CAST_WWW_ANSWER_RECEIVED);
        }
    }
}
