package defpackage;

import android.speech.tts.UtteranceProgressListener;
import java.util.Objects;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.TtsPlatformImpl;

/* renamed from: wo1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5485wo1 extends UtteranceProgressListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TtsPlatformImpl f11571a;

    public C5485wo1(TtsPlatformImpl ttsPlatformImpl) {
        this.f11571a = ttsPlatformImpl;
    }

    public void onDone(String str) {
        TtsPlatformImpl ttsPlatformImpl = this.f11571a;
        Objects.requireNonNull(ttsPlatformImpl);
        PostTask.c(Zo1.f9374a, new RunnableC4805so1(ttsPlatformImpl, str));
    }

    @Deprecated
    public void onError(String str) {
    }

    public void onError(String str, int i) {
        TtsPlatformImpl ttsPlatformImpl = this.f11571a;
        Objects.requireNonNull(ttsPlatformImpl);
        PostTask.c(Zo1.f9374a, new RunnableC4975to1(ttsPlatformImpl, str));
    }

    public void onStart(String str) {
        TtsPlatformImpl ttsPlatformImpl = this.f11571a;
        Objects.requireNonNull(ttsPlatformImpl);
        PostTask.c(Zo1.f9374a, new RunnableC5145uo1(ttsPlatformImpl, str));
    }
}
