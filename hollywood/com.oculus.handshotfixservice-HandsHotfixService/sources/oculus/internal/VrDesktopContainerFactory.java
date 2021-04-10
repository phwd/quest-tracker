package oculus.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Surface;

public class VrDesktopContainerFactory implements VrDesktopContainerFactoryInterface {
    @Override // oculus.internal.VrDesktopContainerFactoryInterface
    public VrDesktopContainerInterface create(Context context, Surface surface, int width, int height, int displayDensity, IBinder activityToken) throws RemoteException {
        return new VrDesktopContainer(context, surface, width, height, displayDensity);
    }
}
