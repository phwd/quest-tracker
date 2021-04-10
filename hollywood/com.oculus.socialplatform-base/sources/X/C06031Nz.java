package X;

import com.facebook.msys.mci.network.common.DataTask;
import com.facebook.msys.mci.network.common.UrlResponse;
import java.io.File;
import java.io.IOException;

/* renamed from: X.1Nz  reason: invalid class name and case insensitive filesystem */
public class C06031Nz extends AnonymousClass1Kd {
    public static final String __redex_internal_original_name = "com.facebook.msys.mci.network.common.NetworkUtils$1";
    public final /* synthetic */ DataTask A00;
    public final /* synthetic */ AnonymousClass1O1 A01;
    public final /* synthetic */ UrlResponse A02;
    public final /* synthetic */ File A03;
    public final /* synthetic */ IOException A04;
    public final /* synthetic */ String A05 = "NetworkSession";
    public final /* synthetic */ byte[] A06;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C06031Nz(DataTask dataTask, AnonymousClass1O1 r4, UrlResponse urlResponse, byte[] bArr, File file, IOException iOException) {
        super("markDataTaskCompleted");
        this.A00 = dataTask;
        this.A01 = r4;
        this.A02 = urlResponse;
        this.A06 = bArr;
        this.A03 = file;
        this.A04 = iOException;
    }

    public final void run() {
        File file;
        String str;
        DataTask dataTask = this.A00;
        String str2 = dataTask.mTaskCategory;
        String str3 = dataTask.mTaskIdentifier;
        int i = dataTask.mTaskType;
        try {
            AnonymousClass1O1 r1 = this.A01;
            UrlResponse urlResponse = this.A02;
            byte[] bArr = this.A06;
            file = this.A03;
            if (file != null) {
                str = file.getCanonicalPath();
            } else {
                str = null;
            }
            r1.markDataTaskAsCompletedCallback(str2, str3, i, urlResponse, bArr, str, this.A04);
        } catch (IOException e) {
            AnonymousClass0MD.A07(this.A05, "IOException while executing markDataTaskCompleted", e);
            this.A01.markDataTaskAsCompletedCallback(str2, str3, i, this.A02, this.A06, null, e);
            file = this.A03;
        } catch (Throwable th) {
            File file2 = this.A03;
            if (file2 != null) {
                file2.delete();
            }
            throw th;
        }
        if (file != null) {
            file.delete();
        }
    }
}
