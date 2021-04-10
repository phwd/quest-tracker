package oculus.internal;

import android.os.NativeHandle;
import android.os.RemoteException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import vendor.oculus.hardware.keyboxinstaller.V1_0.IDeviceKeyboxInstaller;

public class KeyboxInstallerCompat {
    private static final String TAG = KeyboxInstallerCompat.class.getSimpleName();

    public static void installWidevineKeybox(String uri) throws FileNotFoundException, IOException, RemoteException {
        FileInputStream fis = new FileInputStream(uri);
        IDeviceKeyboxInstaller.getService().installWidevineKeybox(new NativeHandle(fis.getFD(), false));
        fis.close();
    }
}
