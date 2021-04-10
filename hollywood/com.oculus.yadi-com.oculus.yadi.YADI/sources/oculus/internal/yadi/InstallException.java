package oculus.internal.yadi;

import com.oculus.os.yadi.RemoteResource;

/* access modifiers changed from: package-private */
public class InstallException extends Exception {
    public final RemoteResource fault;

    InstallException(String str, RemoteResource remoteResource) {
        super(str);
        this.fault = remoteResource;
    }

    InstallException(String str, RemoteResource remoteResource, Throwable th) {
        super(str, th);
        this.fault = remoteResource;
    }
}
