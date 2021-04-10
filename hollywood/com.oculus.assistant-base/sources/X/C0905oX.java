package X;

import android.os.RemoteException;
import com.facebook.papaya.IPapayaLogSink;
import com.facebook.papaya.client.ILogSink;

/* renamed from: X.oX  reason: case insensitive filesystem */
public final class C0905oX implements ILogSink {
    public final /* synthetic */ IPapayaLogSink A00;

    @Override // com.facebook.papaya.client.ILogSink
    public final String getId() {
        return "global_log_sink";
    }

    public C0905oX(IPapayaLogSink iPapayaLogSink) {
        this.A00 = iPapayaLogSink;
    }

    @Override // com.facebook.papaya.client.ILogSink
    public final void event(long j, long j2, long j3, int i, String str) {
        try {
            this.A00.event(j, j2, j3, i, str);
        } catch (RemoteException e) {
            C0139Dd.A07(Gv.class, e, "Failed to send event to log sink", new Object[0]);
        }
    }

    @Override // com.facebook.papaya.client.ILogSink
    public final void log(long j, long j2, long j3, int i, String str, int i2, String str2) {
        try {
            this.A00.log(j, j2, j3, i, str, i2, str2);
        } catch (RemoteException e) {
            C0139Dd.A07(Gv.class, e, "Failed to send log to log sink", new Object[0]);
        }
    }
}
