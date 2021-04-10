package X;

import com.facebook.assistant.oacr.OacrVoiceInteractionListener;
import java.nio.ByteBuffer;

/* renamed from: X.g0  reason: case insensitive filesystem */
public final class C0719g0 implements OacrVoiceInteractionListener {
    public final /* synthetic */ C0740gP A00;

    @Override // com.facebook.assistant.oacr.OacrVoiceInteractionListener
    public final void onError(String str, String str2) {
        C0139Dd.A0O("AssistantClientPlatform_NativeExecutor", "onError: %s, interactionId: %s", str, str2);
        C0740gP.A16.post(new C0714fu(this, str2, str));
    }

    @Override // com.facebook.assistant.oacr.OacrVoiceInteractionListener
    public final void onInteractionComplete(boolean z, String str, String str2) {
    }

    public C0719g0(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    @Override // com.facebook.assistant.oacr.OacrVoiceInteractionListener
    public final void onAction(String str, ByteBuffer byteBuffer, String str2, boolean z) {
        C00879r.A00.A05(EnumC00909u.OACR_ON_ACTION_RECEIVED);
        C0139Dd.A0H("AssistantClientPlatform_NativeExecutor", "onAction, shortwaveId %s, interactionId %s ", str, str2);
        C0740gP.A16.post(new C0716fx(this, str2, z, byteBuffer, str));
    }

    @Override // com.facebook.assistant.oacr.OacrVoiceInteractionListener
    public final void onFinalTranscription(ByteBuffer byteBuffer, String str) {
        C0139Dd.A0F("AssistantClientPlatform_NativeExecutor", "onFinalTranscription: interactionId %s", str);
        C0740gP.A16.post(new fw(this, str, byteBuffer));
    }

    @Override // com.facebook.assistant.oacr.OacrVoiceInteractionListener
    public final void onIntermediateTranscription(ByteBuffer byteBuffer, String str) {
        C0139Dd.A0F("AssistantClientPlatform_NativeExecutor", "onIntermediateTranscription: interactionId %s", str);
        C0740gP.A16.post(new C0715fv(this, str, byteBuffer));
    }

    @Override // com.facebook.assistant.oacr.OacrVoiceInteractionListener
    public final void onKeywordVerificationComplete(boolean z, String str) {
        C0139Dd.A0F("AssistantClientPlatform_NativeExecutor", "onKeywordVerificationComplete, interactionId %s", str);
        C0740gP.A16.post(new C0718fz(this, z));
    }

    @Override // com.facebook.assistant.oacr.OacrVoiceInteractionListener
    public final void onOnDeviceTts(String str, String str2) {
        C0139Dd.A0F("AssistantClientPlatform_NativeExecutor", "onOnDeviceTts, interactionId %s", str2);
        C0740gP.A16.post(new C0717fy(this, str2, str));
    }
}
