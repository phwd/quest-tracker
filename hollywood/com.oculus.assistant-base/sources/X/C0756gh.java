package X;

import com.facebook.assistant.clientplatform.keyboard.learning.LearningListener;
import com.google.common.util.concurrent.ListenableFuture;

/* renamed from: X.gh  reason: case insensitive filesystem */
public final class C0756gh implements AbstractC0382Ut {
    public final /* synthetic */ LearningListener A00;

    public C0756gh(LearningListener learningListener) {
        this.A00 = learningListener;
    }

    @Override // X.AbstractC0382Ut
    public final ListenableFuture A1F(Object obj) {
        C0904oW oWVar = (C0904oW) obj;
        C0139Dd.A09("com.facebook.assistant.clientplatform.keyboard.learning.LearningListener", "Scheduling job executing");
        if (oWVar == null) {
            return new AnonymousClass1u(false);
        }
        C0912of ofVar = C0912of.A00;
        C0139Dd.A0F("com.facebook.assistant.clientplatform.keyboard.learning.LearningListener", "Submitting executor: %s", "assistant_smart_keyboard_executor");
        return AnonymousClass1O.A00(oWVar.A01.A00(), new C0900oS(oWVar, ofVar), oWVar.A02);
    }
}
