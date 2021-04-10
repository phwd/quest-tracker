package com.oculus.tablet.utils.backtotop;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.vrshell.panels.DensityUtils;

public class BackToTopButtonManager {
    public static final int APP_COUNT_LARGE_LIMIT = 120;
    public static final int APP_COUNT_MEDIUM_LIMIT = 75;
    public static final int APP_COUNT_SMALL_LIMIT = 50;
    public static final int BACK_TO_TOP_ANIMATION_DURATION_LARGE = 2000;
    public static final int BACK_TO_TOP_ANIMATION_DURATION_MEDIUM = 1500;
    public static final int BACK_TO_TOP_ANIMATION_DURATION_SMALL = 1000;
    public static final int BACK_TO_TOP_ANIMATION_DURATION_SMALL_MEDIUM = 1200;
    public static final int BACK_TO_TOP_BUTTON_ANIMATION_DURATION = 200;
    public static final int BACK_TO_TOP_BUTTON_LAYOUT_MAX_HEIGHT_DP = 53;
    public static final int BACK_TO_TOP_BUTTON_LAYOUT_MIN_HEIGHT_DP = 1;
    public static String TAG = LoggingUtil.tag(BackToTopButtonManager.class);
    public ValueAnimator mAnimator;
    public BackToTopButtonProvider mBinding;
    public Context mContext;
    public boolean mIsVisible;
    public ValueAnimator mScrollAnimator;
    public ScrollCallback mScrollCallback;
    public int mScrollToTopPreviousValue;

    private int getScrollTime(int i) {
        if (i >= 120) {
            return 2000;
        }
        if (i >= 75) {
            return BACK_TO_TOP_ANIMATION_DURATION_MEDIUM;
        }
        if (i >= 50) {
            return BACK_TO_TOP_ANIMATION_DURATION_SMALL_MEDIUM;
        }
        return 1000;
    }

    private void animate(int i, int i2) {
        int i3;
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            i3 = 200;
        } else {
            i = ((Number) this.mAnimator.getAnimatedValue()).intValue();
            i3 = (int) this.mAnimator.getCurrentPlayTime();
            this.mAnimator.end();
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(i, i2);
        this.mAnimator = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /* class com.oculus.tablet.utils.backtotop.$$Lambda$BackToTopButtonManager$vhDawrqJtyWNAbIGMp_S_SYV2kA2 */

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                BackToTopButtonManager.this.lambda$animate$2$BackToTopButtonManager(valueAnimator);
            }
        });
        this.mAnimator.setDuration((long) i3);
        this.mAnimator.start();
    }

    private void hide() {
        if (this.mIsVisible) {
            this.mIsVisible = false;
            DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
            animate(DensityUtils.dipToPixelsInt(53.0f, displayMetrics), DensityUtils.dipToPixelsInt(1.0f, displayMetrics));
        }
    }

    private void show() {
        if (!this.mIsVisible) {
            this.mIsVisible = true;
            DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
            animate(DensityUtils.dipToPixelsInt(1.0f, displayMetrics), DensityUtils.dipToPixelsInt(53.0f, displayMetrics));
        }
    }

    public void doActionBasedOnPosition(int i) {
        if (i > 0) {
            show();
        } else {
            hide();
        }
    }

    public void initialize(BackToTopButtonProvider backToTopButtonProvider, ScrollCallback scrollCallback, BackToTopButtonActionCallback backToTopButtonActionCallback) {
        this.mBinding = backToTopButtonProvider;
        this.mScrollCallback = scrollCallback;
        OCButton oCButton = backToTopButtonProvider.backToTopButton;
        backToTopButtonProvider.setLayoutHeight(DensityUtils.dipToPixelsInt(1.0f, this.mContext.getResources().getDisplayMetrics()));
        oCButton.setOnClickListener(new View.OnClickListener(backToTopButtonActionCallback) {
            /* class com.oculus.tablet.utils.backtotop.$$Lambda$BackToTopButtonManager$DVPEc3Henj4169QC_lf24JXby4M2 */
            public final /* synthetic */ BackToTopButtonActionCallback f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                BackToTopButtonManager.this.lambda$initialize$0$BackToTopButtonManager(this.f$1, view);
            }
        });
        if (backToTopButtonActionCallback != null) {
            oCButton.setOnHoverListener(new View.OnHoverListener() {
                /* class com.oculus.tablet.utils.backtotop.$$Lambda$BackToTopButtonManager$37fmc7zVKCX98YrYTdxZqMUeQ6k2 */

                public final boolean onHover(View view, MotionEvent motionEvent) {
                    return BackToTopButtonManager.lambda$initialize$1(BackToTopButtonActionCallback.this, view, motionEvent);
                }
            });
        }
    }

    public boolean isScrollToTopAnimationRunning() {
        ValueAnimator valueAnimator = this.mScrollAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            return false;
        }
        return true;
    }

    public /* synthetic */ void lambda$initialize$0$BackToTopButtonManager(BackToTopButtonActionCallback backToTopButtonActionCallback, View view) {
        if (backToTopButtonActionCallback != null) {
            backToTopButtonActionCallback.onClick();
        }
        this.mScrollCallback.smoothScrollToPosition(0);
    }

    public BackToTopButtonManager(Context context) {
        this.mContext = context;
    }

    public static /* synthetic */ boolean lambda$initialize$1(BackToTopButtonActionCallback backToTopButtonActionCallback, View view, MotionEvent motionEvent) {
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

    public void animateScrollToTop(OCRecyclerView oCRecyclerView) {
        this.mScrollToTopPreviousValue = (int) (((float) oCRecyclerView.computeVerticalScrollOffset()) * 1.5f);
        ValueAnimator valueAnimator = this.mScrollAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            ValueAnimator ofInt = ValueAnimator.ofInt(this.mScrollToTopPreviousValue, 0);
            this.mScrollAnimator = ofInt;
            ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(oCRecyclerView) {
                /* class com.oculus.tablet.utils.backtotop.$$Lambda$BackToTopButtonManager$lGlTksU8slpd6sADJugorVMRlYI2 */
                public final /* synthetic */ OCRecyclerView f$1;

                {
                    this.f$1 = r2;
                }

                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BackToTopButtonManager.this.lambda$animateScrollToTop$3$BackToTopButtonManager(this.f$1, valueAnimator);
                }
            });
            this.mScrollAnimator.setDuration((long) getScrollTime(oCRecyclerView.mAdapter.getItemCount()));
            this.mScrollAnimator.start();
        }
    }

    public /* synthetic */ void lambda$animate$2$BackToTopButtonManager(ValueAnimator valueAnimator) {
        int intValue = ((Number) valueAnimator.getAnimatedValue()).intValue();
        if (this.mBinding.getLayoutHeight() != intValue) {
            this.mBinding.setLayoutHeight(intValue);
            this.mBinding.requestLayout();
        }
    }

    public /* synthetic */ void lambda$animateScrollToTop$3$BackToTopButtonManager(OCRecyclerView oCRecyclerView, ValueAnimator valueAnimator) {
        int intValue = ((Number) valueAnimator.getAnimatedValue()).intValue();
        oCRecyclerView.scrollBy(0, intValue - this.mScrollToTopPreviousValue);
        this.mScrollToTopPreviousValue = intValue;
    }

    public void maybeStopScrollToTopAnimation() {
        if (isScrollToTopAnimationRunning()) {
            this.mScrollAnimator.cancel();
            this.mScrollAnimator = null;
        }
    }
}
