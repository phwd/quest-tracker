package com.oculus.panelapp.assistant.animation;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.RequiresApi;

public class AnimationDrawableWithCallback extends AnimationDrawable {
    private boolean mInitialized;
    private int mLastFrame;
    private OnLastFrameListener mLastFrameListener;
    private boolean mPlaying;

    public interface OnLastFrameListener {
        boolean onLastFrame();
    }

    @RequiresApi(api = 21)
    public AnimationDrawableWithCallback(Context context, int i) {
        this(context.getDrawable(i));
    }

    public AnimationDrawableWithCallback(Drawable drawable) {
        if (drawable != null) {
            if (drawable instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
                for (int i = 0; i < animationDrawable.getNumberOfFrames(); i++) {
                    addFrame(animationDrawable.getFrame(i), animationDrawable.getDuration(i));
                }
            } else {
                addFrame(drawable, 15);
            }
            this.mInitialized = true;
            return;
        }
        throw new IllegalArgumentException("Provided drawable for AnimationDrawableWithCallback is null.");
    }

    public void setAnimationFinishListener(OnLastFrameListener onLastFrameListener) {
        this.mLastFrameListener = onLastFrameListener;
    }

    public void start() {
        super.start();
        this.mPlaying = true;
    }

    public void stop() {
        super.stop();
        this.mPlaying = false;
    }

    public boolean selectDrawable(int i) {
        OnLastFrameListener onLastFrameListener;
        if (this.mPlaying) {
            this.mLastFrame = i;
        } else {
            i = this.mLastFrame;
        }
        if (!this.mInitialized || i != getNumberOfFrames() - 1 || (onLastFrameListener = this.mLastFrameListener) == null || !onLastFrameListener.onLastFrame()) {
            return super.selectDrawable(i);
        }
        return true;
    }
}
