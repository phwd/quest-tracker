package X;

import com.facebook.msys.mci.network.common.DataTask;
import com.facebook.msys.mci.network.common.UrlRequest;
import com.facebook.msys.mci.network.common.UrlResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/* renamed from: X.1O3  reason: invalid class name */
public class AnonymousClass1O3 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.msys.mci.network.system.HttpUrlConnectionNetworkSessionListenerManager$2$1";
    public final /* synthetic */ DataTask A00;
    public final /* synthetic */ AnonymousClass1O1 A01;
    public final /* synthetic */ AnonymousClass1O4 A02;

    public AnonymousClass1O3(AnonymousClass1O4 r1, DataTask dataTask, AnonymousClass1O1 r3) {
        this.A02 = r1;
        this.A00 = dataTask;
        this.A01 = r3;
    }

    public final void run() {
        IOException e;
        AnonymousClass1O1 r5;
        UrlRequest urlRequest;
        String str;
        String str2;
        DataTask dataTask = this.A00;
        int i = dataTask.mTaskType;
        if (i != 0) {
            if (i == 1) {
                AnonymousClass1O7 r2 = this.A02.A00;
                r5 = this.A01;
                urlRequest = dataTask.mUrlRequest;
                try {
                    File createTempFile = File.createTempFile("NetworkSessionDownload", null, r2.A02);
                    FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                    try {
                        r5.executeInNetworkContext(new C06031Nz(dataTask, r5, AnonymousClass1O7.A00(r2, dataTask.mTaskIdentifier, urlRequest, r5, fileOutputStream, false, true), null, createTempFile, null));
                        fileOutputStream.close();
                        return;
                    } catch (Throwable unused) {
                    }
                } catch (IOException e2) {
                    e = e2;
                    str = "NetworkSession";
                    str2 = "IOException while executing handleDownloadTaskType";
                    AnonymousClass0MD.A07(str, str2, e);
                    r5.executeInNetworkContext(new C06031Nz(dataTask, r5, new UrlResponse(urlRequest, 0, new HashMap()), null, null, e));
                    return;
                }
            } else if (!(i == 2 || i == 3)) {
                throw new UnsupportedOperationException(AnonymousClass006.A04("DataTask type ", i, " not yet supported"));
            }
        }
        AnonymousClass1O7 r22 = this.A02.A00;
        r5 = this.A01;
        str = "NetworkSession";
        urlRequest = dataTask.mUrlRequest;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            r5.executeInNetworkContext(new C06031Nz(dataTask, r5, AnonymousClass1O7.A00(r22, dataTask.mTaskIdentifier, urlRequest, r5, byteArrayOutputStream, true, false), byteArrayOutputStream.toByteArray(), null, null));
            return;
        } catch (IOException e3) {
            e = e3;
            str2 = "IOException while executing handleDataTaskType";
            AnonymousClass0MD.A07(str, str2, e);
            r5.executeInNetworkContext(new C06031Nz(dataTask, r5, new UrlResponse(urlRequest, 0, new HashMap()), null, null, e));
            return;
        }
        throw th;
    }
}
