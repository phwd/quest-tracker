package X;

import com.facebook.assistant.oacr.AudioPlayerChannelForwarder;
import com.facebook.assistant.oacr.Oacr;
import java.nio.channels.WritableByteChannel;

/* renamed from: X.fo  reason: case insensitive filesystem */
public final class C0708fo implements Oacr.AudioPlayer {
    public final /* synthetic */ C0740gP A00;

    public C0708fo(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    @Override // com.facebook.assistant.oacr.Oacr.AudioPlayer
    public final WritableByteChannel play(String str, String str2, String str3) {
        C00879r.A00.A05(EnumC00909u.OACR_TTS_START_RECEIVED);
        C0139Dd.A0H("AssistantClientPlatform_NativeExecutor", "Oacr.AudioPlayer.Play, mimeType: %s, interactionId: %s", str2, str3);
        C0740gP gPVar = this.A00;
        l0 l0Var = gPVar.A0B;
        if (l0Var != null) {
            return new AudioPlayerChannelForwarder(C0740gP.A16, str2, str3, l0Var, gPVar.A0o);
        }
        C0139Dd.A0D("AssistantClientPlatform_NativeExecutor", "OACR wants to stream TTS while TTSPlayer is null");
        return null;
    }
}
