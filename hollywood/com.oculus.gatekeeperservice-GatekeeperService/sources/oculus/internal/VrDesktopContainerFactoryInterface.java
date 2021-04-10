package oculus.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Surface;

public interface VrDesktopContainerFactoryInterface {
    VrDesktopContainerInterface create(Context context, Surface surface, int i, int i2, int i3, IBinder iBinder) throws RemoteException;
}
