package oculus.internal.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockPatternView;
import com.oculus.os.platform.internal.R;
import java.util.List;

public abstract class AbstractLockPatternView extends LinearLayout {
    private static final int MIN_PATTERN_LENGTH = 4;
    private static final String TAG = AbstractLockPatternView.class.getSimpleName();
    protected TextView mHelpTextView;
    protected TextView mMessageTextView;
    protected OculusLockPatternView mOculusLockPatternView;

    /* access modifiers changed from: protected */
    public abstract void onLegalPatternEntered(String str);

    public AbstractLockPatternView(Context context) {
        this(context, null);
    }

    public AbstractLockPatternView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(1);
        initUI();
    }

    private void initUI() {
        View rootView = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.abstract_lock_pattern_view, (ViewGroup) this, true);
        this.mOculusLockPatternView = (OculusLockPatternView) rootView.findViewById(R.id.oculus_lock_pattern_view);
        this.mOculusLockPatternView.setOnPatternListener(new LockPatternView.OnPatternListener() {
            /* class oculus.internal.widget.AbstractLockPatternView.AnonymousClass1 */

            public void onPatternStart() {
            }

            public void onPatternCleared() {
            }

            public void onPatternCellAdded(List<LockPatternView.Cell> list) {
            }

            public void onPatternDetected(List<LockPatternView.Cell> pattern) {
                if (pattern.size() < 4) {
                    AbstractLockPatternView.this.setHelpText(R.string.pattern_too_short);
                    AbstractLockPatternView.this.mOculusLockPatternView.clearPattern();
                    return;
                }
                AbstractLockPatternView.this.onLegalPatternEntered(LockPatternUtils.patternToString(pattern));
            }
        });
        this.mMessageTextView = (TextView) rootView.findViewById(R.id.main_message);
        this.mHelpTextView = (TextView) rootView.findViewById(R.id.help_message);
    }

    public void setTitle(int resId) {
        this.mMessageTextView.setText(resId);
    }

    public void setHelpText(int resId) {
        this.mHelpTextView.setText(resId);
    }

    public void reset() {
        this.mOculusLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Correct);
        this.mOculusLockPatternView.clearPattern();
        this.mOculusLockPatternView.setEnabled(true);
        this.mHelpTextView.setText("");
        this.mMessageTextView.setText(getContext().getResources().getString(R.string.lockscreen_draw_unlock));
    }

    public void disable() {
        this.mHelpTextView.setText("");
        this.mOculusLockPatternView.setEnabled(false);
    }
}
