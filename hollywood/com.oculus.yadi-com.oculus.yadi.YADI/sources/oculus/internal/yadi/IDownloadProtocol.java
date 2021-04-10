package oculus.internal.yadi;

import android.net.Network;
import android.os.Bundle;
import android.security.net.config.ApplicationConfig;
import com.oculus.os.yadi.RemoteResource;
import com.oculus.os.yadi.YadiStatus;
import java.io.File;
import java.io.IOException;

/* access modifiers changed from: package-private */
public interface IDownloadProtocol {

    @FunctionalInterface
    public interface ProgressFunc {
        YadiStatus update(long j, long j2);
    }

    void download(Network network, ApplicationConfig applicationConfig, RemoteResource remoteResource, File file, Bundle bundle, ProgressFunc progressFunc) throws IOException;

    long sizeOf(Network network, ApplicationConfig applicationConfig, RemoteResource remoteResource) throws IOException;
}
