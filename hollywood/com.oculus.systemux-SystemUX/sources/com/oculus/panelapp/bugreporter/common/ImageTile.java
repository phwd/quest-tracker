package com.oculus.panelapp.bugreporter.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.panelapp.bugreporter.R;
import com.oculus.panelapp.bugreporter.databinding.BugReportCameraRollImageBinding;

public class ImageTile extends ConstraintLayout {
    public ImageTile(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(BugReportCameraRollImageBinding bugReportCameraRollImageBinding) {
        bugReportCameraRollImageBinding.imageContainer.setClipToOutline(true);
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.ocimagecard_inner_zoom);
        loadAnimation.reset();
        bugReportCameraRollImageBinding.image.setOnHoverListener(new View.OnHoverListener(loadAnimation) {
            /* class com.oculus.panelapp.bugreporter.common.$$Lambda$ImageTile$_QmWGGdq3XahGCYkcd0ufxd2Ho8 */
            private final /* synthetic */ Animation f$0;

            {
                this.f$0 = r1;
            }

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return ImageTile.lambda$initialize$22(this.f$0, view, motionEvent);
            }
        });
    }

    static /* synthetic */ boolean lambda$initialize$22(Animation animation, View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            view.startAnimation(animation);
            return false;
        } else if (actionMasked != 10) {
            return false;
        } else {
            view.clearAnimation();
            return false;
        }
    }
}
