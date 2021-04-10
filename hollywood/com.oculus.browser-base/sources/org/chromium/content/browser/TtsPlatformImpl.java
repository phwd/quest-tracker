package org.chromium.content.browser;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import java.util.List;
import java.util.Locale;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TtsPlatformImpl {

    /* renamed from: a  reason: collision with root package name */
    public long f10920a;
    public final TextToSpeech b;
    public boolean c = false;
    public List d;
    public String e;
    public C5825yo1 f;

    public TtsPlatformImpl(long j) {
        this.f10920a = j;
        TextToSpeech textToSpeech = new TextToSpeech(ContextUtils.getApplicationContext(), new C4635ro1(this));
        this.b = textToSpeech;
        textToSpeech.setOnUtteranceProgressListener(new C5485wo1(this));
    }

    public static TtsPlatformImpl create(long j) {
        return new TtsPlatformImpl(j);
    }

    public final void destroy() {
        this.f10920a = 0;
    }

    public final int getVoiceCount() {
        return this.d.size();
    }

    public final String getVoiceLanguage(int i) {
        return ((C5995zo1) this.d.get(i)).b;
    }

    public final String getVoiceName(int i) {
        return ((C5995zo1) this.d.get(i)).f11770a;
    }

    public final boolean isInitialized() {
        return this.c;
    }

    public final boolean speak(int i, String str, String str2, float f2, float f3, float f4) {
        if (!this.c) {
            this.f = new C5825yo1(this, i, str, str2, f2, f3, f4, null);
            return true;
        }
        if (this.f != null) {
            this.f = null;
        }
        if (!str2.equals(this.e)) {
            this.b.setLanguage(new Locale(str2));
            this.e = str2;
        }
        this.b.setSpeechRate(f2);
        this.b.setPitch(f3);
        Bundle bundle = new Bundle();
        if (((double) f4) != 1.0d) {
            bundle.putFloat("volume", f4);
        }
        if (this.b.speak(str, 0, bundle, Integer.toString(i)) == 0) {
            return true;
        }
        return false;
    }

    public final void stop() {
        if (this.c) {
            this.b.stop();
        }
        if (this.f != null) {
            this.f = null;
        }
    }
}
