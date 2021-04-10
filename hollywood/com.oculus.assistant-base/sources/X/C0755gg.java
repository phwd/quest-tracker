package X;

import android.os.AsyncTask;
import com.facebook.assistant.clientplatform.keyboard.learning.LearningListener;

/* renamed from: X.gg  reason: case insensitive filesystem */
public final class C0755gg implements AbstractC0105Aj {
    public final /* synthetic */ LearningListener A00;

    public C0755gg(LearningListener learningListener) {
        this.A00 = learningListener;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AsyncTask.execute(new AnonymousClass8m(this, ak));
    }
}
