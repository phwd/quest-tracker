package X;

import android.os.RemoteCallbackList;
import androidx.room.IMultiInstanceInvalidationCallback;
import androidx.room.MultiInstanceInvalidationService;

/* renamed from: X.0Ff  reason: invalid class name */
public class AnonymousClass0Ff extends RemoteCallbackList<IMultiInstanceInvalidationCallback> {
    public final /* synthetic */ MultiInstanceInvalidationService A00;

    public AnonymousClass0Ff(MultiInstanceInvalidationService multiInstanceInvalidationService) {
        this.A00 = multiInstanceInvalidationService;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.os.IInterface, java.lang.Object] */
    @Override // android.os.RemoteCallbackList
    public final void onCallbackDied(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, Object obj) {
        this.A00.A02.remove(Integer.valueOf(((Integer) obj).intValue()));
    }
}
