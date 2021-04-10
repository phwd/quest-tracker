package X;

import android.os.AsyncTask;
import com.facebook.assistant.clientplatform.keyboard.modeltypeahead.ByteLMWordTypeaheadProvider;

/* renamed from: X.h7  reason: case insensitive filesystem */
public final class C0778h7 implements AbstractC0105Aj {
    public final /* synthetic */ ByteLMWordTypeaheadProvider A00;

    public C0778h7(ByteLMWordTypeaheadProvider byteLMWordTypeaheadProvider) {
        this.A00 = byteLMWordTypeaheadProvider;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        AsyncTask.execute(new RunnableC00668t(this, ak));
    }
}
