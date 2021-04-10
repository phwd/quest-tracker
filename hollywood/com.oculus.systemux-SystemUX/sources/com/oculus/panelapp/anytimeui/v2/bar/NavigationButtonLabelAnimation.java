package com.oculus.panelapp.anytimeui.v2.bar;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.vrshell.panels.DensityUtils;

public class NavigationButtonLabelAnimation {
    private static final int ANIMATION_DURATION = 150;
    private static final String BUTTON_ID_KEY = "button_id";
    private static final float END_ALPHA_SELECTED = 1.0f;
    private static final float END_ALPHA_UNSELECTED = 0.6f;
    private static final float END_HEIGHT = 36.0f;
    private static final String LOG_EVENT_ID = "oculus_auiv2_nav_bar_label_display";
    private static final float START_ALPHA = 0.0f;
    private static final float START_HEIGHT = 46.0f;
    private static final String VISIBILITY_KEY = "visibility";
    private ValueAnimator mAnimator;
    private ClickEventButtonId mButtonId;
    private int mEndHeight;
    private Handler mHandler = new Handler();
    private boolean mIsVisible;
    private OCTextView mLabel;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private int mStartHeight;
    private final UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    public NavigationButtonLabelAnimation(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ClickEventButtonId clickEventButtonId, OCButton oCButton, OCTextView oCTextView) {
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mButtonId = clickEventButtonId;
        this.mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(this.mPanelApp.getContext());
        this.mUnifiedTelemetryLogger.init(this.mPanelApp.getContext().getApplicationContext());
        this.mLabel = oCTextView;
        DisplayMetrics displayMetrics = this.mLabel.getContext().getResources().getDisplayMetrics();
        this.mStartHeight = DensityUtils.dipToPixelsInt(START_HEIGHT, displayMetrics);
        this.mEndHeight = DensityUtils.dipToPixelsInt(END_HEIGHT, displayMetrics);
        initialize(oCButton);
    }

    private void initialize(OCButton oCButton) {
        oCButton.setOnHoverListener(new View.OnHoverListener(oCButton) {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$NavigationButtonLabelAnimation$3OgV07FFh6YIp_5oG_c7KpkNTlw */
            private final /* synthetic */ OCButton f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return NavigationButtonLabelAnimation.this.lambda$initialize$103$NavigationButtonLabelAnimation(this.f$1, view, motionEvent);
            }
        });
    }

    public /* synthetic */ boolean lambda$initialize$103$NavigationButtonLabelAnimation(OCButton oCButton, View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            animateBasedOnHoverState(true, oCButton.isSelected());
        } else if (actionMasked == 10) {
            animateBasedOnHoverState(false, oCButton.isSelected());
        }
        return false;
    }

    private void animateBasedOnHoverState(boolean z, boolean z2) {
        if (z) {
            show(z2);
        } else {
            hide();
        }
    }

    private void show(boolean z) {
        if (!this.mIsVisible) {
            this.mIsVisible = true;
            this.mHandler.postDelayed(new Runnable() {
                /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$NavigationButtonLabelAnimation$qktIFQFOyyKAUKRLJs0pLpLAMV8 */

                public final void run() {
                    NavigationButtonLabelAnimation.this.lambda$show$104$NavigationButtonLabelAnimation();
                }
            }, 150);
            logLabelVisibility();
            animate(0.0f, z ? 1.0f : END_ALPHA_UNSELECTED);
        }
    }

    public /* synthetic */ void lambda$show$104$NavigationButtonLabelAnimation() {
        this.mLabel.setSelected(true);
    }

    private void hide() {
        if (this.mIsVisible) {
            this.mIsVisible = false;
            this.mLabel.setSelected(false);
            logLabelVisibility();
            animate(this.mLabel.getAlpha(), 0.0f);
        }
    }

    private void animate(float f, float f2) {
        int i;
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            i = 150;
        } else {
            f = ((Float) this.mAnimator.getAnimatedValue()).floatValue();
            i = (int) this.mAnimator.getCurrentPlayTime();
            this.mAnimator.end();
        }
        this.mAnimator = ValueAnimator.ofFloat(f, f2);
        this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$NavigationButtonLabelAnimation$_R0ZH_Qvloxl6HM7hoiu0aQfWvQ */

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                NavigationButtonLabelAnimation.this.lambda$animate$105$NavigationButtonLabelAnimation(valueAnimator);
            }
        });
        this.mAnimator.setDuration((long) i);
        this.mAnimator.start();
    }

    public /* synthetic */ void lambda$animate$105$NavigationButtonLabelAnimation(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        if (this.mLabel.getAlpha() != floatValue) {
            this.mLabel.setAlpha(floatValue);
            this.mLabel.getLayoutParams().height = calculateHeight(floatValue);
            this.mLabel.requestLayout();
        }
    }

    private int calculateHeight(float f) {
        int i = this.mStartHeight;
        return (int) (((float) i) - (((float) (i - this.mEndHeight)) * f));
    }

    private void logLabelVisibility() {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(LOG_EVENT_ID);
        analyticsEvent.setExtra("button_id", this.mButtonId.getTelemetryButtonId());
        analyticsEvent.setExtra(VISIBILITY_KEY, Boolean.valueOf(this.mIsVisible));
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }
}
