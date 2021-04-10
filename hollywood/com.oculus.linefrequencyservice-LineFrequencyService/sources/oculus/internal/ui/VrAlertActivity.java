package oculus.internal.ui;

import android.os.Bundle;
import android.util.TypedValue;
import com.android.internal.app.AlertActivity;

public class VrAlertActivity extends AlertActivity {
    private static final int ICON_NO_ID = -1;
    private VrUiWrapper vrLifecycle;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        onCreate(savedInstanceState, new VrUiWrapper());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: oculus.internal.ui.VrAlertActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState, VrUiWrapper wrapper) {
        VrAlertActivity.super.onCreate(savedInstanceState);
        this.vrLifecycle = wrapper;
        this.vrLifecycle.onCreate(this, getWindow());
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        VrAlertActivity.super.onResume();
        this.vrLifecycle.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.vrLifecycle.onPause();
        VrAlertActivity.super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.vrLifecycle.onDestroy();
        VrAlertActivity.super.onDestroy();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        VrAlertActivity.super.onWindowFocusChanged(hasFocus);
        this.vrLifecycle.onWindowFocusChanged(hasFocus);
    }

    /* access modifiers changed from: protected */
    public void setupAlert() {
        if (this.mAlertParams.mIcon == null) {
            TypedValue alertIconTyped = new TypedValue();
            if (getTheme().resolveAttribute(16843605, alertIconTyped, true)) {
                this.mAlertParams.mIcon = getDrawable(alertIconTyped.resourceId);
            }
        }
        VrAlertActivity.super.setupAlert();
    }

    /* access modifiers changed from: protected */
    public void initiateDialog(String title) {
        initiateDialog(title, null, null, null, -1);
    }

    /* access modifiers changed from: protected */
    public void initiateDialog(String title, String message) {
        initiateDialog(title, message, null, null, -1);
    }

    /* access modifiers changed from: protected */
    public void initiateDialog(String title, int icon) {
        initiateDialog(title, null, null, null, icon);
    }

    /* access modifiers changed from: protected */
    public void initiateDialog(String title, String message, int icon) {
        initiateDialog(title, message, null, null, icon);
    }

    /* access modifiers changed from: protected */
    public void initiateDialog(String title, String message, String positiveButtonText) {
        initiateDialog(title, message, positiveButtonText, null, -1);
    }

    /* access modifiers changed from: protected */
    public void initiateDialog(String title, String message, String positiveButtonText, String negativeButtonText) {
        initiateDialog(title, message, positiveButtonText, negativeButtonText, -1);
    }

    /* access modifiers changed from: protected */
    public void initiateDialog(String title, String message, String positiveButtonText, String negativeButtonText, int icon) {
        this.mAlertParams.mTitle = title;
        this.mAlertParams.mMessage = message;
        this.mAlertParams.mPositiveButtonText = positiveButtonText;
        this.mAlertParams.mNegativeButtonText = negativeButtonText;
        if (icon != -1) {
            this.mAlertParams.mIcon = getDrawable(icon);
        }
        setupAlert();
    }
}
