package X;

import com.facebook.msys.mci.network.common.UrlResponse;
import java.io.IOException;
import javax.annotation.Nullable;

/* renamed from: X.1O1  reason: invalid class name */
public interface AnonymousClass1O1 {
    void executeInNetworkContext(AnonymousClass1Kd v);

    void markDataTaskAsCompletedCallback(String str, String str2, int i, UrlResponse urlResponse, @Nullable byte[] bArr, @Nullable String str3, @Nullable IOException iOException);

    void updateDataTaskDownloadProgressCallback(String str, long j, long j2, long j3);

    void updateDataTaskUploadProgressCallback(String str, long j, long j2, long j3);
}
