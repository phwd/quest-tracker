package oculus.internal;

import android.content.Intent;
import android.os.RemoteException;
import android.view.InputEvent;

public interface VrDesktopContainerInterface {
    void addBackgroundSurface() throws RemoteException;

    void clear() throws RemoteException;

    boolean injectEvent(InputEvent inputEvent) throws RemoteException;

    boolean isActive();

    void release() throws RemoteException;

    void resumeTopActivity() throws RemoteException;

    void setInputFocusEnabled(boolean z) throws RemoteException;

    void setVolume(float f) throws RemoteException;

    int startActivity(Intent intent) throws RemoteException;

    void suspendActivities() throws RemoteException;
}
