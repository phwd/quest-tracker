package defpackage;

import J.N;
import android.os.Bundle;
import android.speech.RecognitionListener;
import com.oculus.os.Version;
import java.util.ArrayList;
import org.chromium.content.browser.SpeechRecognitionImpl;

/* renamed from: NY0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NY0 implements RecognitionListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SpeechRecognitionImpl f8553a;

    public NY0(SpeechRecognitionImpl speechRecognitionImpl) {
        this.f8553a = speechRecognitionImpl;
    }

    public final void a(Bundle bundle, boolean z) {
        SpeechRecognitionImpl speechRecognitionImpl = this.f8553a;
        if (speechRecognitionImpl.f != 0) {
            if (speechRecognitionImpl.g && z) {
                z = false;
            }
            ArrayList<String> stringArrayList = bundle.getStringArrayList("results_recognition");
            float[] floatArray = bundle.getFloatArray("confidence_scores");
            SpeechRecognitionImpl speechRecognitionImpl2 = this.f8553a;
            N.MnkvkoGY(speechRecognitionImpl2.f, speechRecognitionImpl2, (String[]) stringArrayList.toArray(new String[stringArrayList.size()]), floatArray, z);
        }
    }

    public void onBeginningOfSpeech() {
        SpeechRecognitionImpl speechRecognitionImpl = this.f8553a;
        long j = speechRecognitionImpl.f;
        if (j != 0) {
            speechRecognitionImpl.b = 2;
            N.M2Nl8ID9(j, speechRecognitionImpl);
        }
    }

    public void onBufferReceived(byte[] bArr) {
    }

    public void onEndOfSpeech() {
        SpeechRecognitionImpl speechRecognitionImpl = this.f8553a;
        if (!speechRecognitionImpl.g) {
            long j = speechRecognitionImpl.f;
            if (j != 0) {
                N.MFB2QCbe(j, speechRecognitionImpl);
                SpeechRecognitionImpl speechRecognitionImpl2 = this.f8553a;
                N.M2TRi32V(speechRecognitionImpl2.f, speechRecognitionImpl2);
                this.f8553a.b = 0;
            }
        }
    }

    public void onError(int i) {
        int i2;
        switch (i) {
            case 1:
            case 2:
            case 4:
                i2 = 4;
                break;
            case 3:
                i2 = 3;
                break;
            case 5:
                i2 = 2;
                break;
            case 6:
                i2 = 1;
                break;
            case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                i2 = 9;
                break;
            case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
            case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                i2 = 5;
                break;
            default:
                return;
        }
        this.f8553a.a(i2);
    }

    public void onEvent(int i, Bundle bundle) {
    }

    public void onPartialResults(Bundle bundle) {
        a(bundle, true);
    }

    public void onReadyForSpeech(Bundle bundle) {
        SpeechRecognitionImpl speechRecognitionImpl = this.f8553a;
        long j = speechRecognitionImpl.f;
        if (j != 0) {
            speechRecognitionImpl.b = 1;
            N.MdhaXGcn(j, speechRecognitionImpl);
        }
    }

    public void onResults(Bundle bundle) {
        a(bundle, false);
        this.f8553a.a(0);
    }

    public void onRmsChanged(float f) {
    }
}
