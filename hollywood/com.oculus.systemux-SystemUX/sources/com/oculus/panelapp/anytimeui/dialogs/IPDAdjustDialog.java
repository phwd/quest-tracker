package com.oculus.panelapp.anytimeui.dialogs;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.notifications.NotificationDataSetService;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.dialogs.VideoStatusListener;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;

public class IPDAdjustDialog extends Dialog implements VideoStatusListener {
    private static final String SKIP_TO_STATE_URI_PARAM = "skip_to_state";
    private static final String TAG = LoggingUtil.tag(IPDAdjustDialog.class);
    private final int FADE_IN_ANIMATION_DURATION = 200;
    private final int FADE_OUT_ANIMATION_DURATION = 300;
    private ImageView mCalibrationImage;
    private View mDialogView;
    private TextView mInstructionsTextLeft;
    private TextView mInstructionsTextRight;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;

    /* access modifiers changed from: private */
    public enum AnimationDirection {
        IN,
        OUT
    }

    public enum IPDAdjustState {
        START,
        FIT_AND_HORIZONTAL_VIDEO,
        HORIZONTAL_INTERACTIVE,
        VERTICAL_VIDEO,
        VERTICAL_INTERACTIVE,
        DONE
    }

    public IPDAdjustDialog(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "IPDAdjustDialog dialog constructed");
    }

    @Override // com.oculus.panelapp.anytimeui.dialogs.Dialog
    public void initialize() {
        Log.d(TAG, "IPDAdjustDialog initialize");
        this.mPanelApp = getPanelApp();
        this.mDialogView = findViewById(R.id.ipd_adjust_dialog);
        this.mCalibrationImage = (ImageView) findViewById(R.id.ipd_adjust_dialog_calibration_image);
        this.mInstructionsTextLeft = (TextView) findViewById(R.id.ipd_adjust_dialog_instructions_text_left);
        this.mInstructionsTextRight = (TextView) findViewById(R.id.ipd_adjust_dialog_instructions_text_right);
        IPDAdjustState iPDAdjustState = this.mPanelApp.getIPDAdjustState();
        if (iPDAdjustState == IPDAdjustState.START) {
            this.mPanelApp.actionSetVolumeLayerEnabled(false);
            this.mPanelApp.actionSetControllerVisibility(false);
            NotificationDataSetService.getInstance().suppressToasts();
            return;
        }
        if (iPDAdjustState == IPDAdjustState.DONE) {
            this.mPanelApp.actionSetVolumeLayerEnabled(true);
            this.mPanelApp.actionSetControllerVisibility(true);
            NotificationDataSetService.getInstance().unsuppressToasts();
            this.mPanelApp.actionDialogResult("ok");
            iPDAdjustState = IPDAdjustState.START;
        }
        enterState(iPDAdjustState, false);
    }

    @Override // com.oculus.panelapp.anytimeui.dialogs.Dialog
    public void configure(String str) {
        Log.d(TAG, "IPDAdjustDialog configure with request URI: " + str);
        IPDAdjustState iPDAdjustState = this.mPanelApp.getIPDAdjustState();
        String queryParameter = Uri.parse(str).getQueryParameter(SKIP_TO_STATE_URI_PARAM);
        this.mPanelApp.setIPDAdjustSkipToState(TextUtils.isEmpty(queryParameter) ? null : IPDAdjustState.valueOf(queryParameter));
        IPDAdjustState iPDAdjustSkipToState = this.mPanelApp.getIPDAdjustSkipToState();
        if (iPDAdjustSkipToState != null) {
            iPDAdjustState = iPDAdjustSkipToState;
        } else if (iPDAdjustState == IPDAdjustState.START) {
            iPDAdjustState = IPDAdjustState.FIT_AND_HORIZONTAL_VIDEO;
        }
        boolean z = false;
        int i = AnonymousClass3.$SwitchMap$com$oculus$panelapp$anytimeui$dialogs$IPDAdjustDialog$IPDAdjustState[iPDAdjustState.ordinal()];
        if (i == 1 || i == 2) {
            z = true;
        }
        enterState(iPDAdjustState, z);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void enterState(IPDAdjustState iPDAdjustState, boolean z) {
        String str = TAG;
        Log.d(str, "IPDAdjustDialog enterState(" + iPDAdjustState.name() + ")");
        this.mPanelApp.getIPDAdjustSkipToState();
        switch (iPDAdjustState) {
            case HORIZONTAL_INTERACTIVE:
                playFadeAnimation(this.mDialogView, AnimationDirection.IN, null);
                break;
            case VERTICAL_INTERACTIVE:
                this.mCalibrationImage.setImageResource(R.drawable.img_ipd_vertical);
                this.mInstructionsTextLeft.setText(R.string.ipd_adjust_dialog_instructions_vertical);
                this.mInstructionsTextRight.setText(R.string.ipd_adjust_dialog_instructions_vertical);
                playFadeAnimation(this.mDialogView, AnimationDirection.IN, null);
                break;
            case START:
            case DONE:
                break;
            case FIT_AND_HORIZONTAL_VIDEO:
                this.mPanelApp.actionPlayVideo("apk://com.oculus.vrshell/assets/quest_nux_fit_fit_and_horizontal.mp4");
                break;
            case VERTICAL_VIDEO:
                this.mPanelApp.actionPlayVideo("apk://com.oculus.vrshell/assets/quest_nux_fit_vertical.mp4");
                break;
            default:
                String str2 = TAG;
                Log.w(str2, "Unhandled IPD adjust dialog state: " + iPDAdjustState.name());
                break;
        }
        this.mPanelApp.setIPDAdjustState(iPDAdjustState, z);
    }

    @Override // com.oculus.panelapp.anytimeui.dialogs.VideoStatusListener
    public void onVideoStatus(VideoStatusListener.VideoState videoState) {
        if (videoState == VideoStatusListener.VideoState.FAILED || videoState == VideoStatusListener.VideoState.FINISHED) {
            onVideoComplete();
        }
    }

    private void onVideoComplete() {
        Log.d(TAG, "IPDAdjustDialog onVideoComplete");
        IPDAdjustState iPDAdjustState = this.mPanelApp.getIPDAdjustState();
        if (this.mPanelApp.getIPDAdjustSkipToState() == iPDAdjustState) {
            goToDoneState();
            return;
        }
        switch (iPDAdjustState) {
            case HORIZONTAL_INTERACTIVE:
            case VERTICAL_INTERACTIVE:
            case START:
            case DONE:
                return;
            case FIT_AND_HORIZONTAL_VIDEO:
                enterState(IPDAdjustState.HORIZONTAL_INTERACTIVE, true);
                return;
            case VERTICAL_VIDEO:
                enterState(IPDAdjustState.VERTICAL_INTERACTIVE, true);
                return;
            default:
                String str = TAG;
                Log.w(str, "Unhandled IPD adjust dialog state: " + iPDAdjustState.name());
                return;
        }
    }

    @Override // com.oculus.vrshell.panels.GenericInputListener, com.oculus.panelapp.anytimeui.dialogs.Dialog
    public boolean onActionButton() {
        Log.e(TAG, "IPDAdjustDialog onActionButton");
        IPDAdjustState iPDAdjustState = this.mPanelApp.getIPDAdjustState();
        if (this.mPanelApp.getIPDAdjustSkipToState() == iPDAdjustState) {
            goToDoneState();
            return false;
        }
        switch (iPDAdjustState) {
            case HORIZONTAL_INTERACTIVE:
                playFadeAnimation(this.mDialogView, AnimationDirection.OUT, new Runnable() {
                    /* class com.oculus.panelapp.anytimeui.dialogs.IPDAdjustDialog.AnonymousClass1 */

                    public void run() {
                        IPDAdjustDialog.this.enterState(IPDAdjustState.VERTICAL_VIDEO, true);
                    }
                });
                break;
            case VERTICAL_INTERACTIVE:
                goToDoneState();
                break;
            case START:
            case DONE:
            case FIT_AND_HORIZONTAL_VIDEO:
            case VERTICAL_VIDEO:
                break;
            default:
                String str = TAG;
                Log.w(str, "Unhandled IPD adjust dialog state: " + iPDAdjustState.name());
                break;
        }
        return false;
    }

    private void goToDoneState() {
        playFadeAnimation(this.mDialogView, AnimationDirection.OUT, new Runnable() {
            /* class com.oculus.panelapp.anytimeui.dialogs.IPDAdjustDialog.AnonymousClass2 */

            public void run() {
                IPDAdjustDialog.this.mPanelApp.setIPDAdjustState(IPDAdjustState.DONE, true);
            }
        });
    }

    private void playFadeAnimation(View view, AnimationDirection animationDirection, Runnable runnable) {
        view.animate().alpha(animationDirection == AnimationDirection.OUT ? 0.0f : 1.0f).setDuration(animationDirection == AnimationDirection.OUT ? 300 : 200).withEndAction(runnable).start();
    }
}
