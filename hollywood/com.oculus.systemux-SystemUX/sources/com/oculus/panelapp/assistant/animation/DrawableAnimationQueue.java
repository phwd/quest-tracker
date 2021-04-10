package com.oculus.panelapp.assistant.animation;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.oculus.panelapp.assistant.animation.AnimationDrawableWithCallback;
import java.util.ArrayDeque;
import java.util.Queue;

public class DrawableAnimationQueue {
    private Animation mActiveDrawable;
    private final ImageView mImageView;
    private final Queue<Animation> mQueue = new ArrayDeque();

    public DrawableAnimationQueue(ImageView imageView) {
        this.mImageView = imageView;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean startNext() {
        if (this.mQueue.size() <= 0) {
            return false;
        }
        clearActive(this.mActiveDrawable);
        Animation poll = this.mQueue.poll();
        this.mImageView.setImageDrawable((Drawable) poll.mAnimation);
        poll.setCallback();
        poll.start();
        if (poll.isStill()) {
            return true;
        }
        this.mActiveDrawable = poll;
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void clearActive(Animation animation) {
        if (animation != null) {
            animation.clearCallback();
        }
        if (this.mActiveDrawable == animation) {
            this.mActiveDrawable = null;
        }
    }

    public void start() {
        Animation animation = this.mActiveDrawable;
        if (animation != null) {
            animation.start();
        } else {
            startNext();
        }
    }

    public void stop() {
        Animation animation = this.mActiveDrawable;
        if (animation != null) {
            animation.stop();
        }
    }

    public void clear() {
        this.mQueue.clear();
    }

    public void cleanup() {
        if (this.mActiveDrawable != null) {
            this.mActiveDrawable = null;
        }
        this.mQueue.clear();
    }

    public DrawableAnimationQueue addDrawable(@NonNull Drawable drawable) {
        return addDrawable(drawable, true);
    }

    public DrawableAnimationQueue addDrawable(@NonNull Drawable drawable, boolean z) {
        if (drawable instanceof AnimatedVectorDrawable) {
            this.mQueue.add(new Animation((AnimatedVectorDrawable) drawable, z));
        } else {
            this.mQueue.add(new Animation(new AnimationDrawableWithCallback(drawable), z));
        }
        return this;
    }

    /* access modifiers changed from: private */
    public class Animation extends Animatable2.AnimationCallback implements AnimationDrawableWithCallback.OnLastFrameListener {
        public final Animatable mAnimation;
        public final boolean mLoop;

        public Animation(@NonNull Animatable animatable, boolean z) {
            this.mAnimation = animatable;
            this.mLoop = z;
        }

        public void start() {
            this.mAnimation.start();
        }

        public void stop() {
            this.mAnimation.stop();
        }

        public void onAnimationEnd(Drawable drawable) {
            super.onAnimationEnd(drawable);
            if (!DrawableAnimationQueue.this.startNext()) {
                Animatable animatable = (Animatable) drawable;
                Animatable animatable2 = this.mAnimation;
                if (animatable2 instanceof AnimatedVectorDrawable) {
                    if (this.mLoop) {
                        animatable.start();
                    } else {
                        onNonLoopingAnimationFinished();
                    }
                } else if (!this.mLoop && (animatable2 instanceof AnimationDrawableWithCallback)) {
                    animatable.stop();
                    onNonLoopingAnimationFinished();
                }
            }
        }

        private void onNonLoopingAnimationFinished() {
            DrawableAnimationQueue.this.clearActive(this);
        }

        @Override // com.oculus.panelapp.assistant.animation.AnimationDrawableWithCallback.OnLastFrameListener
        public boolean onLastFrame() {
            onAnimationEnd((Drawable) this.mAnimation);
            return true;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearCallback() {
            Animatable animatable = this.mAnimation;
            if (animatable instanceof AnimatedVectorDrawable) {
                ((AnimatedVectorDrawable) animatable).unregisterAnimationCallback(this);
            } else if (animatable instanceof AnimationDrawableWithCallback) {
                ((AnimationDrawableWithCallback) animatable).setAnimationFinishListener(null);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCallback() {
            Animatable animatable = this.mAnimation;
            if (animatable instanceof AnimatedVectorDrawable) {
                ((AnimatedVectorDrawable) animatable).registerAnimationCallback(this);
            } else if (animatable instanceof AnimationDrawableWithCallback) {
                ((AnimationDrawableWithCallback) animatable).setAnimationFinishListener(this);
            }
        }

        public boolean isStill() {
            Animatable animatable = this.mAnimation;
            if (!(animatable instanceof AnimationDrawableWithCallback) || ((AnimationDrawableWithCallback) animatable).getNumberOfFrames() != 1) {
                return false;
            }
            return true;
        }
    }
}
