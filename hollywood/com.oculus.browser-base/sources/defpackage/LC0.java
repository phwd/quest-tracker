package defpackage;

import android.media.MediaPlayer;
import org.chromium.components.browser_ui.photo_picker.PickerVideoPlayer;

/* renamed from: LC0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class LC0 implements MediaPlayer.OnPreparedListener {

    /* renamed from: a  reason: collision with root package name */
    public final PickerVideoPlayer f8412a;

    public LC0(PickerVideoPlayer pickerVideoPlayer) {
        this.f8412a = pickerVideoPlayer;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        PickerVideoPlayer pickerVideoPlayer = this.f8412a;
        pickerVideoPlayer.L = mediaPlayer;
        pickerVideoPlayer.e();
        pickerVideoPlayer.L.setOnVideoSizeChangedListener(new OC0(pickerVideoPlayer));
    }
}
