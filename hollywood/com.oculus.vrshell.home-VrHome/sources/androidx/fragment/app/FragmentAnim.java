package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.fragment.R;
import androidx.fragment.app.FragmentTransition;

/* access modifiers changed from: package-private */
public class FragmentAnim {
    static AnimationOrAnimator loadAnimation(Context context, FragmentContainer fragmentContainer, Fragment fragment, boolean enter) {
        int animResourceId;
        int transit = fragment.getNextTransition();
        int nextAnim = fragment.getNextAnim();
        fragment.setNextAnim(0);
        View container = fragmentContainer.onFindViewById(fragment.mContainerId);
        if (!(container == null || container.getTag(R.id.visible_removing_fragment_view_tag) == null)) {
            container.setTag(R.id.visible_removing_fragment_view_tag, null);
        }
        if (fragment.mContainer != null && fragment.mContainer.getLayoutTransition() != null) {
            return null;
        }
        Animation animation = fragment.onCreateAnimation(transit, enter, nextAnim);
        if (animation != null) {
            return new AnimationOrAnimator(animation);
        }
        Animator animator = fragment.onCreateAnimator(transit, enter, nextAnim);
        if (animator != null) {
            return new AnimationOrAnimator(animator);
        }
        if (nextAnim != 0) {
            boolean isAnim = "anim".equals(context.getResources().getResourceTypeName(nextAnim));
            boolean successfulLoad = false;
            if (isAnim) {
                try {
                    Animation animation2 = AnimationUtils.loadAnimation(context, nextAnim);
                    if (animation2 != null) {
                        return new AnimationOrAnimator(animation2);
                    }
                    successfulLoad = true;
                } catch (Resources.NotFoundException e) {
                    throw e;
                } catch (RuntimeException e2) {
                }
            }
            if (!successfulLoad) {
                try {
                    Animator animator2 = AnimatorInflater.loadAnimator(context, nextAnim);
                    if (animator2 != null) {
                        return new AnimationOrAnimator(animator2);
                    }
                } catch (RuntimeException e3) {
                    if (isAnim) {
                        throw e3;
                    }
                    Animation animation3 = AnimationUtils.loadAnimation(context, nextAnim);
                    if (animation3 != null) {
                        return new AnimationOrAnimator(animation3);
                    }
                }
            }
        }
        if (transit == 0 || (animResourceId = transitToAnimResourceId(transit, enter)) < 0) {
            return null;
        }
        return new AnimationOrAnimator(AnimationUtils.loadAnimation(context, animResourceId));
    }

    static void animateRemoveFragment(final Fragment fragment, AnimationOrAnimator anim, final FragmentTransition.Callback callback) {
        final View viewToAnimate = fragment.mView;
        final ViewGroup container = fragment.mContainer;
        container.startViewTransition(viewToAnimate);
        final CancellationSignal signal = new CancellationSignal();
        signal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
            /* class androidx.fragment.app.FragmentAnim.AnonymousClass1 */

            @Override // androidx.core.os.CancellationSignal.OnCancelListener
            public void onCancel() {
                if (fragment.getAnimatingAway() != null) {
                    View v = fragment.getAnimatingAway();
                    fragment.setAnimatingAway(null);
                    v.clearAnimation();
                }
                fragment.setAnimator(null);
            }
        });
        callback.onStart(fragment, signal);
        if (anim.animation != null) {
            Animation animation = new EndViewTransitionAnimation(anim.animation, container, viewToAnimate);
            fragment.setAnimatingAway(fragment.mView);
            animation.setAnimationListener(new Animation.AnimationListener() {
                /* class androidx.fragment.app.FragmentAnim.AnonymousClass2 */

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    container.post(new Runnable() {
                        /* class androidx.fragment.app.FragmentAnim.AnonymousClass2.AnonymousClass1 */

                        public void run() {
                            if (fragment.getAnimatingAway() != null) {
                                fragment.setAnimatingAway(null);
                                callback.onComplete(fragment, signal);
                            }
                        }
                    });
                }

                public void onAnimationRepeat(Animation animation) {
                }
            });
            fragment.mView.startAnimation(animation);
            return;
        }
        Animator animator = anim.animator;
        fragment.setAnimator(anim.animator);
        animator.addListener(new AnimatorListenerAdapter() {
            /* class androidx.fragment.app.FragmentAnim.AnonymousClass3 */

            public void onAnimationEnd(Animator anim) {
                container.endViewTransition(viewToAnimate);
                Animator animator = fragment.getAnimator();
                fragment.setAnimator(null);
                if (animator != null && container.indexOfChild(viewToAnimate) < 0) {
                    callback.onComplete(fragment, signal);
                }
            }
        });
        animator.setTarget(fragment.mView);
        animator.start();
    }

    private static int transitToAnimResourceId(int transit, boolean enter) {
        switch (transit) {
            case 4097:
                return enter ? R.anim.fragment_open_enter : R.anim.fragment_open_exit;
            case 4099:
                return enter ? R.anim.fragment_fade_enter : R.anim.fragment_fade_exit;
            case 8194:
                return enter ? R.anim.fragment_close_enter : R.anim.fragment_close_exit;
            default:
                return -1;
        }
    }

    /* access modifiers changed from: package-private */
    public static class AnimationOrAnimator {
        public final Animation animation;
        public final Animator animator;

        AnimationOrAnimator(Animation animation2) {
            this.animation = animation2;
            this.animator = null;
            if (animation2 == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        AnimationOrAnimator(Animator animator2) {
            this.animation = null;
            this.animator = animator2;
            if (animator2 == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* access modifiers changed from: private */
    public static class EndViewTransitionAnimation extends AnimationSet implements Runnable {
        private boolean mAnimating = true;
        private final View mChild;
        private boolean mEnded;
        private final ViewGroup mParent;
        private boolean mTransitionEnded;

        EndViewTransitionAnimation(Animation animation, ViewGroup parent, View child) {
            super(false);
            this.mParent = parent;
            this.mChild = child;
            addAnimation(animation);
            this.mParent.post(this);
        }

        public boolean getTransformation(long currentTime, Transformation t) {
            this.mAnimating = true;
            if (this.mEnded) {
                if (!this.mTransitionEnded) {
                    return true;
                }
                return false;
            } else if (super.getTransformation(currentTime, t)) {
                return true;
            } else {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
                return true;
            }
        }

        public boolean getTransformation(long currentTime, Transformation outTransformation, float scale) {
            this.mAnimating = true;
            if (this.mEnded) {
                if (!this.mTransitionEnded) {
                    return true;
                }
                return false;
            } else if (super.getTransformation(currentTime, outTransformation, scale)) {
                return true;
            } else {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
                return true;
            }
        }

        public void run() {
            if (this.mEnded || !this.mAnimating) {
                this.mParent.endViewTransition(this.mChild);
                this.mTransitionEnded = true;
                return;
            }
            this.mAnimating = false;
            this.mParent.post(this);
        }
    }
}
