package oculus.internal.widget;

import android.app.ActivityManager;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import com.android.internal.widget.LockPatternChecker;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockPatternView;
import com.oculus.os.platform.internal.R;

public class VerifyLockPatternView extends AbstractLockPatternView {
    private static final String TAG = VerifyLockPatternView.class.getSimpleName();
    private Callback mCallback;
    private final LockPatternUtils mLockPatternUtils;

    public interface Callback {
        void onCorrectPattern(String str);

        void onIncorrectPattern();
    }

    public VerifyLockPatternView(Context context) {
        this(context, null);
    }

    public VerifyLockPatternView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mLockPatternUtils = new LockPatternUtils(context);
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    /* access modifiers changed from: protected */
    @Override // oculus.internal.widget.AbstractLockPatternView
    public void onLegalPatternEntered(final String pattern) {
        int userId = ActivityManager.getCurrentUser();
        if (this.mLockPatternUtils.isLockPatternEnabled(userId)) {
            disable();
            this.mHelpTextView.setText("");
            LockPatternChecker.checkPattern(this.mLockPatternUtils, LockPatternUtils.stringToPattern(pattern), userId, new LockPatternChecker.OnCheckCallback() {
                /* class oculus.internal.widget.VerifyLockPatternView.AnonymousClass1 */

                public void onChecked(boolean matched, int throttleTimeoutMs) {
                    VerifyLockPatternView.this.onPatternChecked(pattern, matched, throttleTimeoutMs);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onPatternChecked(String pattern, boolean matched, int timeoutMs) {
        if (matched) {
            onCorrectPattern(pattern);
        } else {
            onIncorrectPattern(timeoutMs);
        }
    }

    private void onCorrectPattern(String pattern) {
        this.mOculusLockPatternView.clearPattern();
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onCorrectPattern(pattern);
        }
    }

    private void onIncorrectPattern(int timeoutMs) {
        this.mOculusLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Wrong);
        this.mOculusLockPatternView.setEnabled(false);
        if (timeoutMs == 0) {
            setTitle(R.string.lockscreen_try_again);
            this.mMessageTextView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.wrong_pattern_shake));
            reset();
        } else {
            this.mOculusLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Wrong);
            this.mOculusLockPatternView.setEnabled(false);
            new CountDownTimer((long) timeoutMs, 1000) {
                /* class oculus.internal.widget.VerifyLockPatternView.AnonymousClass2 */

                public void onTick(long millisUntilFinished) {
                    int timeoutS = ((int) millisUntilFinished) / 1000;
                    VerifyLockPatternView.this.mHelpTextView.setText(VerifyLockPatternView.this.getContext().getResources().getQuantityString(R.plurals.lockscreen_try_countdown, timeoutS, Integer.valueOf(timeoutS)));
                }

                public void onFinish() {
                    VerifyLockPatternView.this.reset();
                }
            }.start();
        }
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onIncorrectPattern();
        }
    }
}
