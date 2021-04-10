package oculus.internal.widget;

import android.app.ActivityManager;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import com.android.internal.widget.LockPatternChecker;
import com.android.internal.widget.LockPatternUtils;
import com.oculus.os.platform.internal.R;

public class VerifyLockPinView extends AbstractLockPinView {
    private static final String TAG = VerifyLockPinView.class.getSimpleName();
    private Callback mCallback;
    private final LockPatternUtils mLockPatternUtils;

    public interface Callback {
        void onCorrectPin(String str);
    }

    public VerifyLockPinView(Context context) {
        this(context, null);
    }

    public VerifyLockPinView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mLockPatternUtils = new LockPatternUtils(context);
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    /* access modifiers changed from: protected */
    @Override // oculus.internal.widget.AbstractLockPinView
    public void onLegalPinEntered(final String pin) {
        int userId = ActivityManager.getCurrentUser();
        if (this.mLockPatternUtils.isLockPasswordEnabled(userId)) {
            disable();
            LockPatternChecker.checkPassword(this.mLockPatternUtils, pin, userId, new LockPatternChecker.OnCheckCallback() {
                /* class oculus.internal.widget.VerifyLockPinView.AnonymousClass1 */

                public void onChecked(boolean matched, int throttleTimeoutMs) {
                    VerifyLockPinView.this.onPinChecked(pin, matched, throttleTimeoutMs);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onPinChecked(String pin, boolean matched, int throttleTimeoutMs) {
        if (matched) {
            onCorrectPin(pin);
        } else {
            onIncorrectPin(throttleTimeoutMs);
        }
    }

    private void onCorrectPin(String pin) {
        this.mOculusLockPinView.clearPin();
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onCorrectPin(pin);
        }
    }

    private void onIncorrectPin(int throttleTimeoutMs) {
        this.mOculusLockPinView.errorClearBubbles();
        if (throttleTimeoutMs == 0) {
            reset(true);
        } else {
            new CountDownTimer((long) throttleTimeoutMs, 1000) {
                /* class oculus.internal.widget.VerifyLockPinView.AnonymousClass2 */

                public void onTick(long millisUntilFinished) {
                    int timeoutS = ((int) millisUntilFinished) / 1000;
                    VerifyLockPinView.this.mTitleTextView.setText(VerifyLockPinView.this.getContext().getResources().getQuantityString(R.plurals.lockscreen_try_countdown, timeoutS, Integer.valueOf(timeoutS)));
                }

                public void onFinish() {
                    VerifyLockPinView.this.reset();
                }
            }.start();
        }
    }
}
