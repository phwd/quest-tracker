package X;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.DeadObjectException;
import android.os.TransactionTooLargeException;
import com.facebook.analytics2.logger.AlarmBasedUploadService;
import java.util.Arrays;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class HW {
    @GuardedBy("UploadServiceProcessUtil.class")
    public static HW A02;
    public static final String A03 = AlarmBasedUploadService.class.getName();
    public final Context A00;
    @Nullable
    public volatile String A01;

    public static synchronized HW A00(Context context) {
        HW hw;
        synchronized (HW.class) {
            hw = A02;
            if (hw == null) {
                hw = new HW(context.getApplicationContext());
                A02 = hw;
            }
        }
        return hw;
    }

    public HW(Context context) {
        this.A00 = context;
    }

    public final boolean A01() {
        String str;
        String A002 = MX.A00();
        synchronized (this) {
            if (this.A01 == null) {
                String str2 = A03;
                String str3 = null;
                try {
                    Context context = this.A00;
                    ServiceInfo[] serviceInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 516).services;
                    if (serviceInfoArr != null) {
                        int i = 0;
                        while (true) {
                            if (i >= serviceInfoArr.length) {
                                break;
                            }
                            ServiceInfo serviceInfo = serviceInfoArr[i];
                            if (serviceInfo.name.equals(str2)) {
                                str3 = serviceInfo.processName;
                                break;
                            }
                            i++;
                        }
                        this.A01 = str3;
                    }
                    Mu.A05("UploadServiceProcessUtil", "Unable to find the UploadService! Services registered: %s", Arrays.deepToString(serviceInfoArr));
                } catch (PackageManager.NameNotFoundException unused) {
                    throw new IllegalStateException(AnonymousClass06.A05("Package ", this.A00.getPackageName(), " cannot be found!"));
                } catch (RuntimeException e) {
                    Throwable th = e;
                    while (th.getCause() != null) {
                        th = th.getCause();
                    }
                    if (th instanceof DeadObjectException) {
                        Mu.A02("UploadServiceProcessUtil", "DeadObjectException when trying to get package manager from context", e);
                    } else if (th instanceof TransactionTooLargeException) {
                        Mu.A02("UploadServiceProcessUtil", "TransactionTooLargeException Exception when trying to get package manager from context", e);
                    } else {
                        throw e;
                    }
                }
                this.A01 = str3;
            }
            str = this.A01;
        }
        if (str == null) {
            return false;
        }
        return str.equals(A002);
    }
}
