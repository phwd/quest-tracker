package defpackage;

import android.media.MediaPlayer;
import org.chromium.components.browser_ui.photo_picker.PickerVideoPlayer;

/* renamed from: QC0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QC0 implements MediaPlayer.OnCompletionListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PickerVideoPlayer f8743a;

    public QC0(PickerVideoPlayer pickerVideoPlayer) {
        this.f8743a = pickerVideoPlayer;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        PickerVideoPlayer pickerVideoPlayer = this.f8743a;
        int i = PickerVideoPlayer.F;
        pickerVideoPlayer.g();
        this.f8743a.i();
        this.f8743a.d(false, 0);
    }
}
