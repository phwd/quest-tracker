package X;

import com.facebook.msys.mci.network.common.DataTask;
import com.facebook.msys.mci.network.common.DataTaskListener;
import java.util.concurrent.RejectedExecutionException;

/* renamed from: X.1O4  reason: invalid class name */
public class AnonymousClass1O4 implements DataTaskListener {
    public final /* synthetic */ AnonymousClass1O7 A00;

    @Override // com.facebook.msys.mci.network.common.DataTaskListener
    public final void onUpdateStreamingDataTask(byte[] bArr, String str, AnonymousClass1O1 r3) {
    }

    public AnonymousClass1O4(AnonymousClass1O7 r1) {
        this.A00 = r1;
    }

    @Override // com.facebook.msys.mci.network.common.DataTaskListener
    public final void onNewTask(DataTask dataTask, AnonymousClass1O1 r5) {
        try {
            this.A00.A04.execute(new AnonymousClass1O3(this, dataTask, r5));
        } catch (RejectedExecutionException e) {
            AnonymousClass0MD.A07("NetworkSession", "data task rejected for execution", e);
            throw e;
        }
    }
}
