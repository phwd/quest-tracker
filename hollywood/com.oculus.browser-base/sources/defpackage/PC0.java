package defpackage;

import android.view.MotionEvent;
import android.view.View;
import org.chromium.components.browser_ui.photo_picker.PickerVideoPlayer;

/* renamed from: PC0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PC0 implements View.OnTouchListener {
    public final /* synthetic */ PickerVideoPlayer F;

    public PC0(PickerVideoPlayer pickerVideoPlayer) {
        this.F = pickerVideoPlayer;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        ((C3727mV) this.F.f0.f10494a).f10425a.onTouchEvent(motionEvent);
        return false;
    }
}
