package oculus.internal.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.os.platform.internal.R;
import oculus.internal.widget.OculusLockPinView;

public abstract class AbstractLockPinView extends LinearLayout {
    private static final String TAG = AbstractLockPinView.class.getSimpleName();
    protected OculusLockPinView mOculusLockPinView;
    private View mRootView;
    protected TextView mTitleTextView;

    /* access modifiers changed from: protected */
    public abstract void onLegalPinEntered(String str);

    public AbstractLockPinView(Context context) {
        this(context, null);
    }

    public AbstractLockPinView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(1);
        initUI();
    }

    private void initUI() {
        this.mRootView = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.abstract_lock_pin_view, (ViewGroup) this, true);
        this.mTitleTextView = (TextView) this.mRootView.findViewById(R.id.title_textview);
        this.mOculusLockPinView = (OculusLockPinView) this.mRootView.findViewById(R.id.oculus_lock_pin_view);
        this.mOculusLockPinView.setCallback(new OculusLockPinView.Callback() {
            /* class oculus.internal.widget.AbstractLockPinView.AnonymousClass1 */

            @Override // oculus.internal.widget.OculusLockPinView.Callback
            public void onPinEntered(String pin) {
                AbstractLockPinView.this.onLegalPinEntered(pin);
            }
        });
    }

    public void reset() {
        reset(false);
    }

    public void reset(boolean error) {
        if (!error) {
            this.mOculusLockPinView.clearPin();
        }
        this.mOculusLockPinView.setEnabled(true);
        this.mOculusLockPinView.enableDeleteEnter(false);
        this.mTitleTextView.setText(getContext().getResources().getString(R.string.lockscreen_enter_pin));
    }

    public void disable() {
        this.mOculusLockPinView.setEnabled(false);
    }
}
