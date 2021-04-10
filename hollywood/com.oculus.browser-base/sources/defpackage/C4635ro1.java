package defpackage;

import android.speech.tts.TextToSpeech;
import java.util.Objects;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.TtsPlatformImpl;

/* renamed from: ro1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4635ro1 implements TextToSpeech.OnInitListener {

    /* renamed from: a  reason: collision with root package name */
    public final TtsPlatformImpl f11225a;

    public C4635ro1(TtsPlatformImpl ttsPlatformImpl) {
        this.f11225a = ttsPlatformImpl;
    }

    public void onInit(int i) {
        TtsPlatformImpl ttsPlatformImpl = this.f11225a;
        Objects.requireNonNull(ttsPlatformImpl);
        if (i == 0) {
            PostTask.c(Zo1.f9374a, new RunnableC5315vo1(ttsPlatformImpl));
        }
    }
}
