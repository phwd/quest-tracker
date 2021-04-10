package defpackage;

import android.view.GestureDetector;
import android.view.MotionEvent;
import org.chromium.components.browser_ui.photo_picker.PickerVideoPlayer;

/* renamed from: VC0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VC0 extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PickerVideoPlayer f9070a;

    public VC0(PickerVideoPlayer pickerVideoPlayer, PC0 pc0) {
        this.f9070a = pickerVideoPlayer;
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        PickerVideoPlayer pickerVideoPlayer = this.f9070a;
        float x = motionEvent.getX();
        int currentPosition = pickerVideoPlayer.L.getCurrentPosition();
        pickerVideoPlayer.L.getDuration();
        pickerVideoPlayer.j(currentPosition + (x > pickerVideoPlayer.Q.getX() + ((float) (pickerVideoPlayer.Q.getWidth() / 2)) ? 10000 : -10000));
        pickerVideoPlayer.i();
        pickerVideoPlayer.d(false, 1);
        return true;
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        PickerVideoPlayer pickerVideoPlayer = this.f9070a;
        if (pickerVideoPlayer.N) {
            pickerVideoPlayer.b(3);
        } else {
            pickerVideoPlayer.d(true, 2);
        }
        return true;
    }
}
