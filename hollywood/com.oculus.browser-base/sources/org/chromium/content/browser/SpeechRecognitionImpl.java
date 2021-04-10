package org.chromium.content.browser;

import J.N;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SpeechRecognitionImpl {

    /* renamed from: a  reason: collision with root package name */
    public static ComponentName f10917a;
    public int b;
    public final Intent c;
    public final RecognitionListener d;
    public SpeechRecognizer e;
    public long f;
    public boolean g = false;

    public SpeechRecognitionImpl(long j) {
        this.f = j;
        NY0 ny0 = new NY0(this);
        this.d = ny0;
        this.c = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        if (f10917a != null) {
            this.e = SpeechRecognizer.createSpeechRecognizer(ContextUtils.getApplicationContext(), f10917a);
        } else {
            this.e = SpeechRecognizer.createSpeechRecognizer(ContextUtils.getApplicationContext());
        }
        this.e.setRecognitionListener(ny0);
    }

    public static SpeechRecognitionImpl createSpeechRecognition(long j) {
        return new SpeechRecognitionImpl(j);
    }

    public final void a(int i) {
        long j = this.f;
        if (j != 0) {
            int i2 = this.b;
            if (i2 != 0) {
                if (i2 == 2) {
                    N.MFB2QCbe(j, this);
                }
                N.M2TRi32V(this.f, this);
                this.b = 0;
            }
            if (i != 0) {
                N.MUf_fHKN(this.f, this, i);
            }
            try {
                this.e.destroy();
            } catch (IllegalArgumentException e2) {
                StringBuilder i3 = AbstractC2531fV.i("Destroy threw exception ");
                i3.append(this.e);
                AbstractC1220Ua0.f("SpeechRecog", i3.toString(), e2);
            }
            this.e = null;
            N.MmgQYR9M(this.f, this);
            this.f = 0;
        }
    }

    public final void abortRecognition() {
        SpeechRecognizer speechRecognizer = this.e;
        if (speechRecognizer != null) {
            speechRecognizer.cancel();
            a(2);
        }
    }

    public final void startRecognition(String str, boolean z, boolean z2) {
        if (this.e != null) {
            this.g = z;
            this.c.putExtra("android.speech.extra.DICTATION_MODE", z);
            this.c.putExtra("android.speech.extra.LANGUAGE", str);
            this.c.putExtra("android.speech.extra.PARTIAL_RESULTS", z2);
            try {
                this.e.startListening(this.c);
            } catch (SecurityException unused) {
                Context applicationContext = ContextUtils.getApplicationContext();
                C1184Ti1.b(applicationContext, applicationContext.getResources().getString(R.string.f62340_resource_name_obfuscated_RES_2131953551), 0).b.show();
            }
        }
    }

    public final void stopRecognition() {
        SpeechRecognizer speechRecognizer = this.e;
        if (speechRecognizer != null) {
            this.g = false;
            speechRecognizer.stopListening();
        }
    }
}
