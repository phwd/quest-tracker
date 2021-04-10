package com.oculus.tablet.utils.backtotop;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.vrshell.panels.DensityUtils;

public class BackToTopButtonManager {
    private static final int APP_COUNT_LARGE_LIMIT = 120;
    private static final int APP_COUNT_MEDIUM_LIMIT = 75;
    private static final int APP_COUNT_SMALL_LIMIT = 50;
    private static final int BACK_TO_TOP_ANIMATION_DURATION_LARGE = 2000;
    private static final int BACK_TO_TOP_ANIMATION_DURATION_MEDIUM = 1500;
    private static final int BACK_TO_TOP_ANIMATION_DURATION_SMALL = 1000;
    private static final int BACK_TO_TOP_ANIMATION_DURATION_SMALL_MEDIUM = 1200;
    private static final int BACK_TO_TOP_BUTTON_ANIMATION_DURATION = 200;
    private static final int BACK_TO_TOP_BUTTON_LAYOUT_MAX_HEIGHT_DP = 53;
    private static final int BACK_TO_TOP_BUTTON_LAYOUT_MIN_HEIGHT_DP = 1;
    private static String TAG = LoggingUtil.tag(BackToTopButtonManager.class);
    private ValueAnimator mAnimator;
    private BackToTopButtonProvider mBinding;
    private Context mContext;
    private boolean mIsVisible;
    private ValueAnimator mScrollAnimator;
    private ScrollCallback mScrollCallback;
    private int mScrollToTopPreviousValue;

    private int getScrollTime(int i) {
        if (i >= 120) {
            return BACK_TO_TOP_ANIMATION_DURATION_LARGE;
        }
        if (i >= 75) {
            return BACK_TO_TOP_ANIMATION_DURATION_MEDIUM;
        }
        if (i >= 50) {
            return BACK_TO_TOP_ANIMATION_DURATION_SMALL_MEDIUM;
        }
        return 1000;
    }

    public BackToTopButtonManager(Context context) {
        this.mContext = context;
    }

    public void initialize(BackToTopButtonProvider backToTopButtonProvider, ScrollCallback scrollCallback, BackToTopButtonActionCallback backToTopButtonActionCallback) {
        this.mBinding = backToTopButtonProvider;
        this.mScrollCallback = scrollCallback;
        OCButton backToTopButton = this.mBinding.getBackToTopButton();
        this.mBinding.setLayoutHeight(DensityUtils.dipToPixelsInt(1.0f, this.mContext.getResources().getDisplayMetrics()));
        backToTopButton.setOnClickListener(new View.OnClickListener(backToTopButtonActionCallback) {
            /* class com.oculus.tablet.utils.backtotop.$$Lambda$BackToTopButtonManager$wf3hvcQQ7VpNCU0ZWUKTt8Kaq8 */
            private final /* synthetic */ BackToTopButtonActionCallback f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                BackToTopButtonManager.this.lambda$initialize$10$BackToTopButtonManager(this.f$1, view);
            }
        });
        if (backToTopButtonActionCallback != null) {
            backToTopButton.setOnHoverListener(new View.OnHoverListener() {
                /* class com.oculus.tablet.utils.backtotop.$$Lambda$BackToTopButtonManager$qzjKJswmLU4PZYTznwaeE2_eJDg */

                public final boolean onHover(View view, MotionEvent motionEvent) {
                    return BackToTopButtonManager.lambda$initialize$11(BackToTopButtonActionCallback.this, view, motionEvent);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initialize$10$BackToTopButtonManager(BackToTopButtonActionCallback backToTopButtonActionCallback, View view) {
        if (backToTopButtonActionCallback != null) {
            backToTopButtonActionCallback.onClick();
        }
        this.mScrollCallback.smoothScrollToPosition(0);
    }

    static /* synthetic */ boolean lambda$initialize$11(BackToTopButtonActionCallback backToTopButtonActionCallback, View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            backToTopButtonActionCallback.onHoverEnter();
            return false;
        } else if (actionMasked != 10) {
            return false;
        } else {
            backToTopButtonActionCallback.onHoverExit();
            return false;
        }
    }

    public void doActionBasedOnPosition(int i) {
        if (i > 0) {
            show();
        } else {
            hide();
        }
    }

    private void show() {
        if (!this.mIsVisible) {
            this.mIsVisible = true;
            DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
            animate(DensityUtils.dipToPixelsInt(1.0f, displayMetrics), DensityUtils.dipToPixelsInt(53.0f, displayMetrics));
        }
    }

    private void hide() {
        if (this.mIsVisible) {
            this.mIsVisible = false;
            DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
            animate(DensityUtils.dipToPixelsInt(53.0f, displayMetrics), DensityUtils.dipToPixelsInt(1.0f, displayMetrics));
        }
    }

    private void animate(int i, int i2) {
        int i3;
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            i3 = 200;
        } else {
            i = ((Integer) this.mAnimator.getAnimatedValue()).intValue();
            i3 = (int) this.mAnimator.getCurrentPlayTime();
            this.mAnimator.end();
        }
        this.mAnimator = ValueAnimator.ofInt(i, i2);
        this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /* class com.oculus.tablet.utils.backtotop.$$Lambda$BackToTopButtonManager$D2Xmhqg1l2sDPk27eSpLO6F76kQ */

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                BackToTopButtonManager.this.lambda$animate$12$BackToTopButtonManager(valueAnimator);
            }
        });
        this.mAnimator.setDuration((long) i3);
        this.mAnimator.start();
    }

    public /* synthetic */ void lambda$animate$12$BackToTopButtonManager(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        if (this.mBinding.getLayoutHeight() != intValue) {
            this.mBinding.setLayoutHeight(intValue);
            this.mBinding.requestLayout();
        }
    }

    public void animateScrollToTop(OCRecyclerView oCRecyclerView) {
        this.mScrollToTopPreviousValue = (int) (((float) oCRecyclerView.computeVerticalScrollOffset()) * 1.5f);
        Log.d(TAG, String.format("Start Scrolling list YPos: %d", Integer.valueOf(this.mScrollToTopPreviousValue)));
        ValueAnimator valueAnimator = this.mScrollAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            this.mScrollAnimator = ValueAnimator.ofInt(this.mScrollToTopPreviousValue, 0);
            this.mScrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(oCRecyclerView) {
                /* class com.oculus.tablet.utils.backtotop.$$Lambda$BackToTopButtonManager$aYy0Rru9oCkGpmOgBAyy9aS3XpA */
                private final /* synthetic */ OCRecyclerView f$1;

                {
                    this.f$1 = r2;
                }

                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BackToTopButtonManager.this.lambda$animateScrollToTop$13$BackToTopButtonManager(this.f$1, valueAnimator);
                }
            });
            this.mScrollAnimator.setDuration((long) getScrollTime(oCRecyclerView.getAdapter().getItemCount()));
            this.mScrollAnimator.start();
        }
    }

    public /* synthetic */ void lambda$animateScrollToTop$13$BackToTopButtonManager(OCRecyclerView oCRecyclerView, ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        oCRecyclerView.scrollBy(0, intValue - this.mScrollToTopPreviousValue);
        this.mScrollToTopPreviousValue = intValue;
    }

    public boolean isScrollToTopAnimationRunning() {
        ValueAnimator valueAnimator = this.mScrollAnimator;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public void maybeStopScrollToTopAnimation() {
        if (isScrollToTopAnimationRunning()) {
            this.mScrollAnimator.cancel();
            this.mScrollAnimator = null;
        }
    }
}
