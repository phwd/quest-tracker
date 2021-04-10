package oculus.internal.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.oculus.os.platform.internal.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OculusLockPinView extends LinearLayout {
    private static final float DISABLED_ALPHA = 0.35f;
    private static final int ERROR_ANIMATION_DURATION = 370;
    private static final float MAX_ALPHA = 1.0f;
    private final int MAX_DIGITS;
    private ArrayList<View> mBubbles;
    private SparseIntArray mButtonToDigit;
    private ArrayList<View> mButtons;
    private Callback mCallback;
    private final Handler mHandler;
    private String mPassword;
    private View mRootView;

    public interface Callback {
        void onPinEntered(String str);
    }

    public OculusLockPinView(Context context) {
        this(context, null);
    }

    public OculusLockPinView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.MAX_DIGITS = 8;
        this.mPassword = "";
        this.mHandler = new Handler(Looper.getMainLooper());
        initUI();
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    private void initUI() {
        this.mRootView = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.oculus_lock_pin_view, (ViewGroup) this, true);
        this.mButtonToDigit = new SparseIntArray(10);
        this.mButtonToDigit.append(R.id.button0, 0);
        this.mButtonToDigit.append(R.id.button1, 1);
        this.mButtonToDigit.append(R.id.button2, 2);
        this.mButtonToDigit.append(R.id.button3, 3);
        this.mButtonToDigit.append(R.id.button4, 4);
        this.mButtonToDigit.append(R.id.button5, 5);
        this.mButtonToDigit.append(R.id.button6, 6);
        this.mButtonToDigit.append(R.id.button7, 7);
        this.mButtonToDigit.append(R.id.button8, 8);
        this.mButtonToDigit.append(R.id.button9, 9);
        this.mBubbles = new ArrayList<>(8);
        this.mBubbles.add(this.mRootView.findViewById(R.id.pin_bubble_0));
        this.mBubbles.add(this.mRootView.findViewById(R.id.pin_bubble_1));
        this.mBubbles.add(this.mRootView.findViewById(R.id.pin_bubble_2));
        this.mBubbles.add(this.mRootView.findViewById(R.id.pin_bubble_3));
        this.mBubbles.add(this.mRootView.findViewById(R.id.pin_bubble_4));
        this.mBubbles.add(this.mRootView.findViewById(R.id.pin_bubble_5));
        this.mBubbles.add(this.mRootView.findViewById(R.id.pin_bubble_6));
        this.mBubbles.add(this.mRootView.findViewById(R.id.pin_bubble_7));
        this.mButtons = new ArrayList<>(12);
        View.OnClickListener digitListener = new View.OnClickListener() {
            /* class oculus.internal.widget.OculusLockPinView.AnonymousClass1 */

            public void onClick(View v) {
                OculusLockPinView oculusLockPinView = OculusLockPinView.this;
                oculusLockPinView.addDigit(oculusLockPinView.mButtonToDigit.get(v.getId()));
            }
        };
        for (int n = this.mButtonToDigit.size() - 1; n >= 0; n--) {
            Button b = (Button) this.mRootView.findViewById(this.mButtonToDigit.keyAt(n));
            b.setOnClickListener(digitListener);
            this.mButtons.add(b);
        }
        ImageButton enterButton = (ImageButton) this.mRootView.findViewById(R.id.buttonEnter);
        enterButton.setOnClickListener(new View.OnClickListener() {
            /* class oculus.internal.widget.OculusLockPinView.AnonymousClass2 */

            public void onClick(View v) {
                if (OculusLockPinView.this.mCallback != null) {
                    OculusLockPinView.this.mCallback.onPinEntered(OculusLockPinView.this.mPassword);
                }
            }
        });
        this.mButtons.add(enterButton);
        ImageButton deleteButton = (ImageButton) this.mRootView.findViewById(R.id.buttonDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            /* class oculus.internal.widget.OculusLockPinView.AnonymousClass3 */

            public void onClick(View v) {
                OculusLockPinView.this.removeLastDigit();
                OculusLockPinView.this.setBubbles();
            }
        });
        this.mButtons.add(deleteButton);
        enableDeleteEnter(false);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        Iterator<View> it = this.mButtons.iterator();
        while (it.hasNext()) {
            setButton(enabled, it.next());
        }
    }

    private void setButton(boolean enabled, View button) {
        float alpha = enabled ? MAX_ALPHA : DISABLED_ALPHA;
        if (button instanceof Button) {
            ((Button) button).setEnabled(enabled);
            ((Button) button).setAlpha(alpha);
        } else if (button instanceof ImageButton) {
            ((ImageButton) button).setEnabled(enabled);
            ((ImageButton) button).setAlpha(alpha);
        }
    }

    public void errorClearBubbles() {
        this.mHandler.post(new Runnable() {
            /* class oculus.internal.widget.$$Lambda$OculusLockPinView$dk1uAvopOdt3LwdI9ON3WAA0s */

            public final void run() {
                OculusLockPinView.this.lambda$errorClearBubbles$0$OculusLockPinView();
            }
        });
    }

    public /* synthetic */ void lambda$errorClearBubbles$0$OculusLockPinView() {
        Iterator<View> it = this.mBubbles.iterator();
        while (it.hasNext()) {
            final View bubble = it.next();
            if (bubble.getVisibility() != 8) {
                bubble.setBackgroundResource(R.drawable.filled_bubble_error);
                ObjectAnimator animation = ObjectAnimator.ofFloat(bubble, "alpha", MAX_ALPHA, 0.0f);
                animation.setDuration(370L);
                animation.addListener(new Animator.AnimatorListener() {
                    /* class oculus.internal.widget.OculusLockPinView.AnonymousClass4 */

                    public void onAnimationStart(Animator _animation) {
                    }

                    public void onAnimationRepeat(Animator _animation) {
                    }

                    public void onAnimationCancel(Animator _animation) {
                    }

                    public void onAnimationEnd(Animator animation) {
                        bubble.setVisibility(8);
                        bubble.setBackgroundResource(R.drawable.filled_bubble);
                        bubble.setAlpha(OculusLockPinView.MAX_ALPHA);
                        OculusLockPinView.this.clearPin();
                    }
                });
                animation.start();
            }
        }
    }

    public void clearPin() {
        this.mPassword = "";
        setBubbles();
        enableDeleteEnter(false);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addDigit(int digit) {
        if (this.mPassword.length() < 8) {
            this.mPassword += digit;
            setBubbles();
            enableDeleteEnter(true);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setBubbles() {
        int digits = Math.min(this.mPassword.length(), 8);
        List<View> visible_bubbles = this.mBubbles.subList(0, digits);
        List<View> invisible_bubbles = this.mBubbles.subList(digits, 8);
        for (View bubble : visible_bubbles) {
            bubble.setVisibility(0);
            setMarginRight(bubble, getResources().getDimensionPixelSize(R.dimen.bubble_margin));
        }
        if (visible_bubbles.size() > 0) {
            setMarginRight(visible_bubbles.get(visible_bubbles.size() - 1), 0);
        }
        for (View bubble2 : invisible_bubbles) {
            bubble2.setVisibility(8);
        }
    }

    public void enableDeleteEnter(boolean enable) {
        setButton(enable, (ImageButton) this.mRootView.findViewById(R.id.buttonEnter));
        setButton(enable, (ImageButton) this.mRootView.findViewById(R.id.buttonDelete));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeLastDigit() {
        if (this.mPassword.length() != 0) {
            String str = this.mPassword;
            this.mPassword = str.substring(0, str.length() - 1);
            if (this.mPassword.length() == 0) {
                enableDeleteEnter(false);
            }
        }
    }

    private void setMarginRight(View view, int marginPx) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        params.rightMargin = marginPx;
        view.setLayoutParams(params);
    }
}
