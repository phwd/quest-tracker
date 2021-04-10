package X;

import android.os.Process;
import android.os.SystemClock;
import com.facebook.acra.config.StartupBlockingConfig;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingDeque;

/* renamed from: X.Xg  reason: case insensitive filesystem */
public final /* synthetic */ class RunnableC0429Xg implements Runnable {
    public static final String __redex_internal_original_name = "com.oculus.assistant.service.-$$Lambda$AudioInputNativeSource$ravQS9MsCP8pPAV2ZrR9o0o6DT4";
    public final /* synthetic */ C1375yA A00;

    public /* synthetic */ RunnableC0429Xg(C1375yA yAVar) {
        this.A00 = yAVar;
    }

    public final void run() {
        C1375yA yAVar = this.A00;
        Process.setThreadPriority(-16);
        C0139Dd.A09("AudioInputNativeSource", "Starting audio buffering.");
        LinkedBlockingDeque linkedBlockingDeque = yAVar.A04;
        linkedBlockingDeque.clear();
        boolean z = true;
        while (yAVar.A05.get() && (r0 = yAVar.A02) != null && !r0.isInterrupted()) {
            if (SystemClock.elapsedRealtime() - yAVar.A00 > StartupBlockingConfig.BLOCKING_UPLOAD_MAX_WAIT_MILLIS) {
                try {
                    C00799i.A00.logError("reached_max_time_buffer");
                    yAVar.close();
                    return;
                } catch (IOException unused) {
                    return;
                }
            } else {
                try {
                    byte[] A03 = yAVar.A01.A03();
                    if (A03 != null) {
                        if (z) {
                            YP A002 = YP.A00();
                            C0139Dd.A09("OculusAssistantInteractionLatencyLogger", "logFirstByteRead");
                            YO yo = A002.A01;
                            if (yo != null) {
                                yo.A01.markerPoint(yo.A00(), "audio_firstbyte_read");
                            }
                            C00799i.A00.logServiceEvent("first_native_service_byte");
                            z = false;
                        }
                        C0139Dd.A0F("AudioInputNativeSource", "Mic data is size: %d", Integer.valueOf(A03.length));
                        linkedBlockingDeque.offer(ByteBuffer.wrap(A03));
                        Thread.sleep(150);
                    }
                } catch (IllegalStateException | InterruptedException unused2) {
                }
            }
        }
    }
}
