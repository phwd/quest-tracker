package defpackage;

import android.animation.Animator;
import android.view.View;
import java.util.Objects;
import org.chromium.components.browser_ui.photo_picker.PickerVideoPlayer;

/* renamed from: TC0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TC0 implements Animator.AnimatorListener {
    public final /* synthetic */ int F;
    public final /* synthetic */ PickerVideoPlayer G;

    public TC0(PickerVideoPlayer pickerVideoPlayer, int i) {
        this.G = pickerVideoPlayer;
        this.F = i;
    }

    public void onAnimationCancel(Animator animator) {
        PickerVideoPlayer pickerVideoPlayer = this.G;
        View view = pickerVideoPlayer.O;
        Objects.requireNonNull(pickerVideoPlayer);
    }

    public void onAnimationEnd(Animator animator) {
        PickerVideoPlayer pickerVideoPlayer = this.G;
        pickerVideoPlayer.R.setClickable(true);
        pickerVideoPlayer.T.setClickable(true);
        this.G.b(this.F);
        View view = this.G.O;
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
        PickerVideoPlayer pickerVideoPlayer = this.G;
        View view = pickerVideoPlayer.O;
        Objects.requireNonNull(pickerVideoPlayer);
    }
}
