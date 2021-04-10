package androidx.room;

import X.AnonymousClass02D;
import X.AnonymousClass0Ff;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.room.IMultiInstanceInvalidationService;
import java.util.HashMap;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
public class MultiInstanceInvalidationService extends Service {
    public int A00 = 0;
    public final RemoteCallbackList<IMultiInstanceInvalidationCallback> A01 = new AnonymousClass0Ff(this);
    public final HashMap<Integer, String> A02 = new HashMap<>();
    public final IMultiInstanceInvalidationService.Stub A03 = new IMultiInstanceInvalidationService.Stub() {
        /* class androidx.room.MultiInstanceInvalidationService.AnonymousClass2 */

        @Override // androidx.room.IMultiInstanceInvalidationService
        public final int A7J(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, String str) {
            if (str == null) {
                return 0;
            }
            MultiInstanceInvalidationService multiInstanceInvalidationService = MultiInstanceInvalidationService.this;
            RemoteCallbackList<IMultiInstanceInvalidationCallback> remoteCallbackList = multiInstanceInvalidationService.A01;
            synchronized (remoteCallbackList) {
                int i = multiInstanceInvalidationService.A00 + 1;
                multiInstanceInvalidationService.A00 = i;
                Integer valueOf = Integer.valueOf(i);
                if (remoteCallbackList.register(iMultiInstanceInvalidationCallback, valueOf)) {
                    multiInstanceInvalidationService.A02.put(valueOf, str);
                    return i;
                }
                multiInstanceInvalidationService.A00--;
                return 0;
            }
        }

        @Override // androidx.room.IMultiInstanceInvalidationService
        public final void A1Y(int i, String[] strArr) {
            MultiInstanceInvalidationService multiInstanceInvalidationService = MultiInstanceInvalidationService.this;
            RemoteCallbackList<IMultiInstanceInvalidationCallback> remoteCallbackList = multiInstanceInvalidationService.A01;
            synchronized (remoteCallbackList) {
                HashMap<Integer, String> hashMap = multiInstanceInvalidationService.A02;
                String str = hashMap.get(Integer.valueOf(i));
                if (str == null) {
                    Log.w("ROOM", "Remote invalidation client ID not registered");
                } else {
                    int beginBroadcast = remoteCallbackList.beginBroadcast();
                    for (int i2 = 0; i2 < beginBroadcast; i2++) {
                        try {
                            int intValue = ((Integer) remoteCallbackList.getBroadcastCookie(i2)).intValue();
                            String str2 = hashMap.get(Integer.valueOf(intValue));
                            if (i != intValue && str.equals(str2)) {
                                try {
                                    remoteCallbackList.getBroadcastItem(i2).A6A(strArr);
                                } catch (RemoteException e) {
                                    Log.w("ROOM", "Error invoking a remote callback", e);
                                }
                            }
                        } catch (Throwable th) {
                            remoteCallbackList.finishBroadcast();
                            throw th;
                        }
                    }
                    remoteCallbackList.finishBroadcast();
                }
            }
        }

        @Override // androidx.room.IMultiInstanceInvalidationService
        public final void A8f(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, int i) {
            MultiInstanceInvalidationService multiInstanceInvalidationService = MultiInstanceInvalidationService.this;
            RemoteCallbackList<IMultiInstanceInvalidationCallback> remoteCallbackList = multiInstanceInvalidationService.A01;
            synchronized (remoteCallbackList) {
                remoteCallbackList.unregister(iMultiInstanceInvalidationCallback);
                multiInstanceInvalidationService.A02.remove(Integer.valueOf(i));
            }
        }
    };

    @Nullable
    public final IBinder onBind(Intent intent) {
        return this.A03;
    }
}
