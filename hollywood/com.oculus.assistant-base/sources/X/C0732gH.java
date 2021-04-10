package X;

import com.facebook.assistant.oacr.AudioReader;

/* renamed from: X.gH  reason: case insensitive filesystem */
public final class C0732gH implements AudioReader.Callback {
    public final /* synthetic */ C0733gI A00;
    public final /* synthetic */ String A01;

    @Override // com.facebook.assistant.oacr.AudioReader.Callback
    public final void onComplete() {
    }

    @Override // com.facebook.assistant.oacr.AudioReader.Callback
    public final void onError(Exception exc) {
        String message;
        C0139Dd.A0V("AssistantClientPlatform", exc, "error in reading audio source, interactionId %s", this.A01);
        if (exc.getMessage() == null) {
            message = "Empty exception message in AudioReader's onError";
        } else {
            message = exc.getMessage();
        }
        C0740gP.A16.post(new C0731gG(this, message));
    }

    public C0732gH(C0733gI gIVar, String str) {
        this.A00 = gIVar;
        this.A01 = str;
    }
}
