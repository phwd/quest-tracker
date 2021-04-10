package com.oculus.panelapp.assistant.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import androidx.annotation.DimenRes;
import com.oculus.panelapp.assistant.animation.listeners.AnimStartListener;
import com.oculus.panelapp.assistant.animation.listeners.AnimSuccessListener;
import java.util.ArrayList;
import java.util.List;

public class AnimationSetManager {
    public static final int ANIMATION_DURATION = 500;
    private static final int FADE_DURATION = 200;
    private static final float[] FADE_IN = {0.0f, 1.0f};
    private static final float[] FADE_OUT = {1.0f, 0.0f};
    private static final int SCALE_DURATION = 200;
    private static final float[] SCALE_IN = {0.0f, 1.0f};
    private static final float[] SCALE_OUT = {1.0f, 0.0f};
    private static final int VIEW_SIZE_DURATION = 370;
    private final String TAG = AnimationSetManager.class.getSimpleName();
    private AnimatorSet mContainerAnimator;
    private boolean mDebugAnimations = false;
    private List<Animator> mPendingAnimations = new ArrayList();

    public void clearPendingAnimations() {
        this.mPendingAnimations.clear();
    }

    public ObjectAnimator enqueueFade(View view, boolean z) {
        return enqueueFade(this.mPendingAnimations, view, z);
    }

    public ObjectAnimator enqueueFade(View view, boolean z, int i) {
        return enqueueFade(this.mPendingAnimations, view, z, i, new float[0]);
    }

    private ObjectAnimator enqueueFade(List<Animator> list, View view, boolean z) {
        return enqueueFade(list, view, z, 200, new float[0]);
    }

    private ObjectAnimator enqueueFade(List<Animator> list, View view, boolean z, int i, float... fArr) {
        ObjectAnimator createFade = createFade(view, z, i, fArr);
        list.add(createFade);
        return createFade;
    }

    public ObjectAnimator createFade(View view, boolean z) {
        return createFade(view, z, 200, new float[0]);
    }

    public ObjectAnimator createFade(View view, boolean z, int i, float... fArr) {
        if (fArr == null || fArr.length == 0) {
            fArr = z ? FADE_IN : FADE_OUT;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, fArr);
        ofFloat.setDuration((long) i);
        if (z) {
            ofFloat.addListener(new AnimStartListener(new Runnable(view) {
                /* class com.oculus.panelapp.assistant.animation.$$Lambda$AnimationSetManager$3qTalUi_Prnm_xYXE8rsuO2bmU */
                private final /* synthetic */ View f$0;

                {
                    this.f$0 = r1;
                }

                public final void run() {
                    this.f$0.setVisibility(0);
                }
            }));
        }
        ofFloat.setInterpolator(new LinearInterpolator());
        StringBuilder sb = new StringBuilder();
        sb.append("enqueueFade ");
        sb.append(z ? "fading in" : "fading out");
        log(ofFloat, sb.toString());
        return ofFloat;
    }

    public ObjectAnimator enqueueContinueFade(View view, boolean z) {
        return enqueueContinueFade(this.mPendingAnimations, view, z);
    }

    private ObjectAnimator enqueueContinueFade(List<Animator> list, View view, boolean z) {
        return enqueueContinueFade(list, view, z, 200);
    }

    private ObjectAnimator enqueueContinueFade(List<Animator> list, View view, boolean z, int i) {
        ObjectAnimator createContinueFade = createContinueFade(view, z, i);
        list.add(createContinueFade);
        return createContinueFade;
    }

    public ObjectAnimator createContinueFade(View view, boolean z) {
        return createContinueFade(view, z, 200);
    }

    public ObjectAnimator createContinueFade(View view, boolean z, int i) {
        ObjectAnimator objectAnimator;
        if (z) {
            objectAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, view.getAlpha(), 1.0f);
            objectAnimator.setDuration((long) ((int) (((float) i) * (1.0f - view.getAlpha()))));
            objectAnimator.addListener(new AnimStartListener(new Runnable(view) {
                /* class com.oculus.panelapp.assistant.animation.$$Lambda$AnimationSetManager$lfQmu0m0dMRhztWvenIoint3oE8 */
                private final /* synthetic */ View f$0;

                {
                    this.f$0 = r1;
                }

                public final void run() {
                    this.f$0.setVisibility(0);
                }
            }));
        } else {
            objectAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, view.getAlpha(), 0.0f);
            objectAnimator.setDuration((long) ((int) (((float) i) * view.getAlpha())));
        }
        objectAnimator.setInterpolator(new LinearInterpolator());
        StringBuilder sb = new StringBuilder();
        sb.append("enqueueContinueFade ");
        sb.append(z ? "fading in" : "fading out");
        log(objectAnimator, sb.toString());
        return objectAnimator;
    }

    public ObjectAnimator enqueueScale(View view, boolean z) {
        return enqueueScale(this.mPendingAnimations, view, z);
    }

    public ObjectAnimator enqueueContinueScale(View view, boolean z) {
        return enqueueContinueScale(this.mPendingAnimations, view, z);
    }

    private ObjectAnimator enqueueScale(List<Animator> list, View view, boolean z) {
        return enqueueScale(list, view, z, 200, new float[0]);
    }

    private ObjectAnimator enqueueScale(List<Animator> list, View view, boolean z, int i, float... fArr) {
        if (fArr == null || fArr.length == 0) {
            fArr = z ? SCALE_IN : SCALE_OUT;
        }
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(View.SCALE_X, fArr), PropertyValuesHolder.ofFloat(View.SCALE_Y, fArr));
        ofPropertyValuesHolder.setDuration((long) i);
        ofPropertyValuesHolder.setInterpolator(new LinearInterpolator());
        list.add(ofPropertyValuesHolder);
        StringBuilder sb = new StringBuilder();
        sb.append("enqueueScale ");
        sb.append(z ? "scale in" : "scale out");
        log(ofPropertyValuesHolder, sb.toString());
        return ofPropertyValuesHolder;
    }

    private ObjectAnimator enqueueContinueScale(List<Animator> list, View view, boolean z) {
        return enqueueContinueScale(list, view, z, 200);
    }

    private ObjectAnimator enqueueContinueScale(List<Animator> list, View view, boolean z, int i) {
        ObjectAnimator objectAnimator;
        if (z) {
            objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(View.SCALE_X, view.getScaleX(), 1.0f), PropertyValuesHolder.ofFloat(View.SCALE_Y, view.getScaleY(), 1.0f));
            objectAnimator.setDuration((long) ((int) (((float) i) * (1.0f - view.getScaleX()))));
        } else {
            objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(View.SCALE_X, view.getScaleX(), 0.0f), PropertyValuesHolder.ofFloat(View.SCALE_Y, view.getScaleY(), 0.0f));
            objectAnimator.setDuration((long) ((int) (((float) i) * (1.0f - view.getScaleX()))));
        }
        objectAnimator.setInterpolator(new LinearInterpolator());
        list.add(objectAnimator);
        StringBuilder sb = new StringBuilder();
        sb.append("enqueueContinueScale ");
        sb.append(z ? "scale in" : "scale out");
        log(objectAnimator, sb.toString());
        return objectAnimator;
    }

    public ObjectAnimator enqueueFadeAndScale(View view, boolean z) {
        return enqueueFadeAndScale(this.mPendingAnimations, view, z);
    }

    public ObjectAnimator enqueueFadeAndScale(View view, boolean z, int i) {
        return enqueueFadeAndScale(this.mPendingAnimations, view, z, i, new float[0]);
    }

    public ObjectAnimator enqueueContinueFadeAndScale(View view, boolean z) {
        return enqueueContinueFadeAndScale(this.mPendingAnimations, view, z);
    }

    public ObjectAnimator enqueueContinueFadeAndScale(View view, boolean z, int i) {
        return enqueueContinueFadeAndScale(this.mPendingAnimations, view, z, i);
    }

    private ObjectAnimator enqueueFadeAndScale(List<Animator> list, View view, boolean z) {
        return enqueueFadeAndScale(list, view, z, 200, new float[0]);
    }

    private ObjectAnimator enqueueContinueFadeAndScale(List<Animator> list, View view, boolean z) {
        return enqueueContinueFadeAndScale(list, view, z, 200);
    }

    private ObjectAnimator enqueueFadeAndScale(List<Animator> list, View view, boolean z, int i, float... fArr) {
        if (fArr == null || fArr.length == 0) {
            fArr = z ? SCALE_IN : SCALE_OUT;
        }
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(View.SCALE_X, fArr), PropertyValuesHolder.ofFloat(View.SCALE_Y, fArr), PropertyValuesHolder.ofFloat(View.ALPHA, fArr));
        ofPropertyValuesHolder.setDuration((long) i);
        ofPropertyValuesHolder.setInterpolator(new LinearInterpolator());
        list.add(ofPropertyValuesHolder);
        StringBuilder sb = new StringBuilder();
        sb.append("enqueueFadeAndScale ");
        sb.append(z ? "in" : "out");
        log(ofPropertyValuesHolder, sb.toString());
        return ofPropertyValuesHolder;
    }

    private ObjectAnimator enqueueContinueFadeAndScale(List<Animator> list, View view, boolean z, int i) {
        ObjectAnimator objectAnimator;
        if (z) {
            objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(View.SCALE_X, view.getScaleX(), 1.0f), PropertyValuesHolder.ofFloat(View.SCALE_Y, view.getScaleY(), 1.0f), PropertyValuesHolder.ofFloat(View.ALPHA, view.getAlpha(), 1.0f));
            objectAnimator.setDuration((long) ((int) (((float) i) * (1.0f - view.getScaleX()))));
        } else {
            objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(View.SCALE_X, view.getScaleX(), 0.0f), PropertyValuesHolder.ofFloat(View.SCALE_Y, view.getScaleY(), 0.0f), PropertyValuesHolder.ofFloat(View.ALPHA, view.getAlpha(), 0.0f));
            objectAnimator.setDuration((long) ((int) (((float) i) * (1.0f - view.getScaleX()))));
        }
        objectAnimator.setInterpolator(new LinearInterpolator());
        list.add(objectAnimator);
        StringBuilder sb = new StringBuilder();
        sb.append("enqueueContinueFadeAndScale ");
        sb.append(z ? "in" : "out");
        log(objectAnimator, sb.toString());
        return objectAnimator;
    }

    private Animator enqueueViewWidth(List<Animator> list, final View view, final int i, final int i2) {
        final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ValueAnimator animator = new ContinueValueAnimator<Integer>() {
            /* class com.oculus.panelapp.assistant.animation.AnimationSetManager.AnonymousClass1 */

            @Override // com.oculus.panelapp.assistant.animation.ContinueValueAnimator
            public void onUpdateValue(float f) {
                int intValue = ((Integer) getInitialValue()).intValue();
                ViewGroup.LayoutParams layoutParams = layoutParams;
                layoutParams.width = intValue + ((int) (((float) (i - intValue)) * f));
                view.setLayoutParams(layoutParams);
                view.requestLayout();
            }

            @Override // com.oculus.panelapp.assistant.animation.ContinueValueAnimator
            public Integer onSetInitialValue(Animator animator) {
                int measuredWidth = view.getMeasuredWidth();
                long duration = animator.getDuration();
                int i = i;
                if (i > measuredWidth) {
                    int i2 = i2;
                    animator.setDuration((duration * ((long) (i2 - measuredWidth))) / ((long) i2));
                } else if (i < measuredWidth) {
                    animator.setDuration((duration * ((long) measuredWidth)) / ((long) i2));
                }
                return Integer.valueOf(measuredWidth);
            }
        }.getAnimator();
        animator.setDuration(370L);
        list.add(animator);
        return animator;
    }

    public Animator enqueueViewWidth(View view, int i, int i2) {
        return enqueueViewWidth(this.mPendingAnimations, view, i, i2);
    }

    public Animator enqueueViewWidthRes(View view, @DimenRes int i, @DimenRes int i2) {
        Resources resources = view.getResources();
        return enqueueViewWidth(this.mPendingAnimations, view, resources.getDimensionPixelSize(i), resources.getDimensionPixelOffset(i2));
    }

    public Animator enqueue(Animator animator) {
        this.mPendingAnimations.add(animator);
        return animator;
    }

    public Animator enqueuePlayAnimationsTogether(Animator... animatorArr) {
        ArrayList arrayList = new ArrayList();
        for (Animator animator : animatorArr) {
            arrayList.add(animator);
        }
        return enqueuePlayAnimationsTogether(this.mPendingAnimations, arrayList);
    }

    private Animator enqueuePlayAnimationsTogether(List<Animator> list, List<Animator> list2) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(list2);
        list.add(animatorSet);
        return animatorSet;
    }

    public void cancel() {
        AnimatorSet animatorSet = this.mContainerAnimator;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
    }

    public AnimatorSet playPendingAnimations(String str) {
        return playPendingAnimations(str, null);
    }

    public AnimatorSet playPendingAnimations(String str, AnimSuccessListener animSuccessListener) {
        cancel();
        this.mContainerAnimator = new AnimatorSet();
        this.mContainerAnimator.playSequentially(this.mPendingAnimations);
        this.mPendingAnimations.clear();
        AnimatorSet animatorSet = this.mContainerAnimator;
        log(animatorSet, "playAnimationsSequentially: " + str);
        if (animSuccessListener != null) {
            this.mContainerAnimator.addListener(animSuccessListener);
        }
        this.mContainerAnimator.start();
        return this.mContainerAnimator;
    }

    private void log(Animator animator, final String str) {
        if (this.mDebugAnimations) {
            animator.addListener(new Animator.AnimatorListener() {
                /* class com.oculus.panelapp.assistant.animation.AnimationSetManager.AnonymousClass2 */

                public void onAnimationStart(Animator animator) {
                    String str = AnimationSetManager.this.TAG;
                    Log.d(str, str + " started.");
                }

                public void onAnimationEnd(Animator animator) {
                    String str = AnimationSetManager.this.TAG;
                    Log.d(str, str + " ended.");
                }

                public void onAnimationCancel(Animator animator) {
                    String str = AnimationSetManager.this.TAG;
                    Log.d(str, str + " cancelled.");
                }

                public void onAnimationRepeat(Animator animator) {
                    String str = AnimationSetManager.this.TAG;
                    Log.d(str, str + " repeated.");
                }
            });
        }
    }

    public void setDebuggingEnabled(boolean z) {
        this.mDebugAnimations = z;
    }
}
