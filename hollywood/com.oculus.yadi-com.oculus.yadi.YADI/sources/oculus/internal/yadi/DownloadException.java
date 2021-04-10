package oculus.internal.yadi;

import android.os.Bundle;
import java.io.IOException;

/* access modifiers changed from: package-private */
public class DownloadException extends IOException {
    DownloadException() {
    }

    DownloadException(String str) {
        super(str);
    }

    public static DownloadException Server(String str) {
        return new Server(str);
    }

    public static DownloadException Client(String str) {
        return new Client(str);
    }

    public static DownloadException Reschedule() {
        return new Reschedule();
    }

    public static DownloadException ResumableReschedule(Bundle bundle) {
        return new ResumableReschedule(bundle);
    }

    public static DownloadException InternalError() {
        return new DownloadException();
    }

    static class Server extends DownloadException {
        Server(String str) {
            super("Server: " + str);
        }
    }

    static class Client extends DownloadException {
        Client(String str) {
            super("Client: " + str);
        }
    }

    /* access modifiers changed from: package-private */
    public static class Reschedule extends DownloadException {
        Reschedule() {
            super("Reschedule");
        }
    }

    static class ResumableReschedule extends Reschedule {
        final Bundle resumeInfo;

        ResumableReschedule(Bundle bundle) {
            this.resumeInfo = bundle;
        }
    }
}
