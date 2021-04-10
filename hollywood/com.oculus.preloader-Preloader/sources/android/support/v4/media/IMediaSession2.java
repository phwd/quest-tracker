package android.support.v4.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

public interface IMediaSession2 extends IInterface {

    public static abstract class Stub extends Binder implements IMediaSession2 {
        public static IMediaSession2 asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("android.support.v4.media.IMediaSession2");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMediaSession2)) {
                return new Proxy(iBinder);
            }
            return (IMediaSession2) queryLocalInterface;
        }

        private static class Proxy implements IMediaSession2 {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }
        }
    }
}
