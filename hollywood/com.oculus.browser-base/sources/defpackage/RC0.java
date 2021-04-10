package defpackage;

import android.animation.Animator;
import android.view.View;
import java.util.Objects;
import org.chromium.components.browser_ui.photo_picker.PickerVideoPlayer;

/* renamed from: RC0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RC0 implements Animator.AnimatorListener {
    public final /* synthetic */ PickerVideoPlayer F;

    public RC0(PickerVideoPlayer pickerVideoPlayer) {
        this.F = pickerVideoPlayer;
    }

    public void onAnimationCancel(Animator animator) {
        PickerVideoPlayer pickerVideoPlayer = this.F;
        View view = pickerVideoPlayer.O;
        Objects.requireNonNull(pickerVideoPlayer);
    }

    public void onAnimationEnd(Animator animator) {
        PickerVideoPlayer pickerVideoPlayer = this.F;
        pickerVideoPlayer.R.setClickable(false);
        pickerVideoPlayer.T.setClickable(false);
        this.F.d0 = false;
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
        PickerVideoPlayer pickerVideoPlayer = this.F;
        View view = pickerVideoPlayer.O;
        Objects.requireNonNull(pickerVideoPlayer);
    }
}
