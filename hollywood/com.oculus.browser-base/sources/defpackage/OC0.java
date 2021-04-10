package defpackage;

import android.media.MediaPlayer;
import org.chromium.components.browser_ui.photo_picker.PickerVideoPlayer;

/* renamed from: OC0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class OC0 implements MediaPlayer.OnVideoSizeChangedListener {

    /* renamed from: a  reason: collision with root package name */
    public final PickerVideoPlayer f8609a;

    public OC0(PickerVideoPlayer pickerVideoPlayer) {
        this.f8609a = pickerVideoPlayer;
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        this.f8609a.c();
    }
}
