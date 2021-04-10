package oculus.internal.ui;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import oculus.internal.ui.ClearActivityUtils;

public class VrTimePickerDialog extends TimePickerDialog {
    private static final int DEFAULT_THEME = 16974916;
    private ClearActivityUtils.ForcePauseUtil mForcePauseUtil = new ClearActivityUtils.ForcePauseUtil();
    private VrUiWrapper mVrLifecycle = new VrUiWrapper();
    private VrWindowAttachedStateListener mWindowAttachedListener;

    public VrTimePickerDialog(Context context, TimePickerDialog.OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, DEFAULT_THEME, listener, hourOfDay, minute, is24HourView);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mVrLifecycle.onCreate(getContext(), getWindow());
        this.mWindowAttachedListener = new VrWindowAttachedStateListener(getWindow().getDecorView(), new Runnable() {
            /* class oculus.internal.ui.VrTimePickerDialog.AnonymousClass1 */

            public void run() {
                VrTimePickerDialog.this.mVrLifecycle.onResume();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        this.mForcePauseUtil.onStart(getContext());
        this.mWindowAttachedListener.start();
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        this.mForcePauseUtil.onStop(getContext());
        this.mWindowAttachedListener.stop();
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
