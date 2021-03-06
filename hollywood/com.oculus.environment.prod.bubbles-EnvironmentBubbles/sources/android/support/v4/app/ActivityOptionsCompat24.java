package android.support.v4.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.View;

class ActivityOptionsCompat24 {
    private final ActivityOptions mActivityOptions;

    public static ActivityOptionsCompat24 makeCustomAnimation(Context context, int i, int i2) {
        return new ActivityOptionsCompat24(ActivityOptions.makeCustomAnimation(context, i, i2));
    }

    public static ActivityOptionsCompat24 makeScaleUpAnimation(View view, int i, int i2, int i3, int i4) {
        return new ActivityOptionsCompat24(ActivityOptions.makeScaleUpAnimation(view, i, i2, i3, i4));
    }

    public static ActivityOptionsCompat24 makeThumbnailScaleUpAnimation(View view, Bitmap bitmap, int i, int i2) {
        return new ActivityOptionsCompat24(ActivityOptions.makeThumbnailScaleUpAnimation(view, bitmap, i, i2));
    }

    public static ActivityOptionsCompat24 makeSceneTransitionAnimation(Activity activity, View view, String str) {
        return new ActivityOptionsCompat24(ActivityOptions.makeSceneTransitionAnimation(activity, view, str));
    }

    public static ActivityOptionsCompat24 makeSceneTransitionAnimation(Activity activity, View[] viewArr, String[] strArr) {
        Pair[] pairArr;
        if (viewArr != null) {
            pairArr = new Pair[viewArr.length];
            for (int i = 0; i < pairArr.length; i++) {
                pairArr[i] = Pair.create(viewArr[i], strArr[i]);
            }
        } else {
            pairArr = null;
        }
        return new ActivityOptionsCompat24(ActivityOptions.makeSceneTransitionAnimation(activity, pairArr));
    }

    public static ActivityOptionsCompat24 makeClipRevealAnimation(View view, int i, int i2, int i3, int i4) {
        return new ActivityOptionsCompat24(ActivityOptions.makeClipRevealAnimation(view, i, i2, i3, i4));
    }

    public static ActivityOptionsCompat24 makeTaskLaunchBehind() {
        return new ActivityOptionsCompat24(ActivityOptions.makeTaskLaunchBehind());
    }

    public static ActivityOptionsCompat24 makeBasic() {
        return new ActivityOptionsCompat24(ActivityOptions.makeBasic());
    }

    private ActivityOptionsCompat24(ActivityOptions activityOptions) {
        this.mActivityOptions = activityOptions;
    }

    public ActivityOptionsCompat24 setLaunchBounds(@Nullable Rect rect) {
        return new ActivityOptionsCompat24(this.mActivityOptions.setLaunchBounds(rect));
    }

    public Rect getLaunchBounds() {
        return this.mActivityOptions.getLaunchBounds();
    }

    public Bundle toBundle() {
        return this.mActivityOptions.toBundle();
    }

    public void update(ActivityOptionsCompat24 activityOptionsCompat24) {
        this.mActivityOptions.update(activityOptionsCompat24.mActivityOptions);
    }

    public void requestUsageTimeReport(PendingIntent pendingIntent) {
        this.mActivityOptions.requestUsageTimeReport(pendingIntent);
    }
}
