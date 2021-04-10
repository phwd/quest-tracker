package defpackage;

import android.animation.Animator;
import android.widget.ImageView;
import java.util.Objects;
import org.chromium.components.browser_ui.photo_picker.PickerVideoPlayer;

/* renamed from: SC0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SC0 implements Animator.AnimatorListener {
    public final /* synthetic */ PickerVideoPlayer F;

    public SC0(PickerVideoPlayer pickerVideoPlayer) {
        this.F = pickerVideoPlayer;
    }

    public void onAnimationCancel(Animator animator) {
        PickerVideoPlayer pickerVideoPlayer = this.F;
        ImageView imageView = pickerVideoPlayer.Q;
        Objects.requireNonNull(pickerVideoPlayer);
    }

    public void onAnimationEnd(Animator animator) {
        this.F.Q.setClickable(false);
        PickerVideoPlayer pickerVideoPlayer = this.F;
        ImageView imageView = pickerVideoPlayer.Q;
        Objects.requireNonNull(pickerVideoPlayer);
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
        PickerVideoPlayer pickerVideoPlayer = this.F;
        pickerVideoPlayer.N = false;
        ImageView imageView = pickerVideoPlayer.Q;
        Objects.requireNonNull(pickerVideoPlayer);
    }
}
