package oculus.internal.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import oculus.internal.ui.ClearActivityUtils;

public class VrDatePickerDialog extends DatePickerDialog {
    private static final int DEFAULT_THEME = 16974916;
    private ClearActivityUtils.ForcePauseUtil mForcePauseUtil = new ClearActivityUtils.ForcePauseUtil();
    private VrUiWrapper mVrLifecycle = new VrUiWrapper();
    private VrWindowAttachedStateListener mWindowAttachedListener;

    public VrDatePickerDialog(Context context, DatePickerDialog.OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth) {
        super(context, DEFAULT_THEME, listener, year, monthOfYear, dayOfMonth);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mVrLifecycle.onCreate(getContext(), getWindow());
        this.mWindowAttachedListener = new VrWindowAttachedStateListener(getWindow().getDecorView(), new Runnable() {
            /* class oculus.internal.ui.VrDatePickerDialog.AnonymousClass1 */

            public void run() {
                VrDatePickerDialog.this.mVrLifecycle.onResume();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        this.mWindowAttachedListener.start();
        this.mForcePauseUtil.onStart(getContext());
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        this.mWindowAttachedListener.stop();
        this.mForcePauseUtil.onStop(getContext());
        if (this.mWindowAttachedListener.wasWindowAttached()) {
            this.mVrLifecycle.onPause();
        }
        this.mVrLifecycle.onDestroy();
        super.onStop();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.mVrLifecycle.onWindowFocusChanged(hasFocus);
    }

    public void setForcePauseBackgroundActivity(boolean shouldPauseActivity) {
        this.mForcePauseUtil.setForcePauseBackgroundActivity(shouldPauseActivity);
    }
}
