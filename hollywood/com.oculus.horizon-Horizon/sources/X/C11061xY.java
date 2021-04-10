package X;

import android.annotation.TargetApi;
import android.os.Handler;
import androidx.annotation.RequiresApi;
import com.facebook.infer.annotation.Nullsafe;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@RequiresApi(18)
@TargetApi(18)
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1xY  reason: invalid class name and case insensitive filesystem */
public final class C11061xY implements AnonymousClass1xD {
    public final Handler A00;
    public final Handler A01;
    public final AbstractC11091xe A02;
    public final C11211xt A03;
    public final AtomicBoolean A04 = new AtomicBoolean(false);
    public final AtomicBoolean A05 = new AtomicBoolean(false);
    public final AnonymousClass1z3 A06;

    public static String A00(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "Unknown message" : "MSG_RELEASE" : "MSG_STOP_RECORDING" : "MSG_PREPARE_AND_START_RECORDING" : "MSG_START_RECORDING" : "MSG_PREPARE";
    }

    @Override // X.AnonymousClass1xD
    public final void release() {
        Handler handler = this.A00;
        handler.sendMessage(handler.obtainMessage(5, new Object[0]));
    }

    @Override // X.AnonymousClass1xD
    public final void A7U(List<AnonymousClass1yR> list, AbstractC11131xk r6, Handler handler) {
        Object[] objArr = {list, new AnonymousClass1xc(this, r6), handler};
        Handler handler2 = this.A00;
        handler2.sendMessage(handler2.obtainMessage(1, objArr));
    }

    @Override // X.AnonymousClass1xD
    public final void A9L(C11191xr r12, AnonymousClass1xh r13) {
        if (this.A04.compareAndSet(false, true)) {
            String A012 = this.A03.A01();
            UUID.randomUUID().toString();
            AbstractC11091xe r4 = this.A02;
            r4.A5S("recording_requested", "RecordingControllerImpl", (long) hashCode(), A012, null);
            r4.A5P(19);
            Object[] objArr = {r12, new AnonymousClass1xb(this, A012, r13)};
            Handler handler = this.A00;
            handler.sendMessage(handler.obtainMessage(2, objArr));
            return;
        }
        C11081xd r7 = new C11081xd("Duplicated START request");
        this.A02.A5R("recording_controller_error", "RecordingControllerImpl", (long) hashCode(), "", r7, "high", "startRecording");
        r13.A5o(r7);
    }

    @Override // X.AnonymousClass1xD
    public final void A9U(boolean z) {
        Handler handler = this.A00;
        if (!handler.hasMessages(5)) {
            handler.removeCallbacksAndMessages(null);
            this.A05.set(true);
            handler.sendMessage(handler.obtainMessage(4, new Object[0]));
        }
    }

    public C11061xY(C11211xt r5, AbstractC11091xe r6, AnonymousClass1z3 r7) {
        this.A03 = r5;
        this.A02 = r6;
        this.A06 = r7;
        C09541mY r3 = C09541mY.A03;
        Handler A002 = C09541mY.A00(r3, "RecordingThread", null);
        this.A01 = A002;
        this.A03.A00 = A002;
        this.A00 = C09541mY.A00(r3, "RecordingControllerMessageThread", new C11071xZ(this, A002, this.A02));
    }
}
