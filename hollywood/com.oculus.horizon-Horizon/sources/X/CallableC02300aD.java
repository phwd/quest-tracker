package X;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemClock;
import com.facebook.push.fbns.ipc.FbnsAIDLRequest;
import com.facebook.push.fbns.ipc.FbnsAIDLResult;
import com.facebook.push.fbns.ipc.IFbnsAIDLService;
import com.facebook.rti.push.service.FbnsService;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Callable;

/* renamed from: X.0aD  reason: invalid class name and case insensitive filesystem */
public class CallableC02300aD implements Callable<FbnsAIDLResult> {
    public final /* synthetic */ FbnsAIDLRequest A00;
    public final /* synthetic */ C02320aG A01;

    public CallableC02300aD(C02320aG r1, FbnsAIDLRequest fbnsAIDLRequest) {
        this.A01 = r1;
        this.A00 = fbnsAIDLRequest;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final FbnsAIDLResult call() throws Exception {
        Integer num;
        IFbnsAIDLService iFbnsAIDLService;
        String packageName;
        try {
            C02320aG r3 = this.A01;
            synchronized (r3) {
                r3.A00++;
                long j = 200;
                int i = 1;
                while (true) {
                    Integer num2 = r3.A02;
                    num = AnonymousClass007.A0C;
                    if (num2 == num) {
                        break;
                    } else if (i > 5) {
                        AnonymousClass0NO.A0E("FbnsAIDLClientManager", "Max Try reached for binding to FbnsAIDLService, threadId %d", Long.valueOf(Thread.currentThread().getId()));
                        break;
                    } else {
                        Thread currentThread = Thread.currentThread();
                        currentThread.getId();
                        SystemClock.elapsedRealtime();
                        if (r3.A02 != AnonymousClass007.A01) {
                            if (Looper.getMainLooper().getThread() == currentThread) {
                                AnonymousClass0NO.A08("FbnsAIDLClientManager", "This operation can't be run on UI thread");
                                break;
                            }
                            currentThread.getId();
                            SystemClock.elapsedRealtime();
                            Context context = r3.A03;
                            AbstractC01570Vx r2 = (AbstractC01570Vx) AnonymousClass0W2.A00;
                            Iterator it = Arrays.asList(r2.A02(), r2.A01()).iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    packageName = context.getPackageName();
                                    break;
                                }
                                packageName = (String) it.next();
                                if (AnonymousClass0WZ.A01(context, packageName)) {
                                    break;
                                }
                            }
                            ComponentName componentName = new ComponentName(packageName, FbnsService.A01(packageName));
                            Intent intent = new Intent(IFbnsAIDLService.class.getName());
                            intent.setComponent(componentName);
                            new C01890Xx(context).A02(intent);
                            if (C02320aG.A01(r3, intent)) {
                                break;
                            }
                        }
                        r3.wait(j);
                        j *= 2;
                        i++;
                    }
                }
            }
            FbnsAIDLRequest fbnsAIDLRequest = this.A00;
            FbnsAIDLResult fbnsAIDLResult = new FbnsAIDLResult(Bundle.EMPTY);
            try {
                synchronized (r3) {
                    if (r3.A02 == num) {
                        iFbnsAIDLService = r3.A01;
                        if (iFbnsAIDLService == null) {
                            throw new RemoteException("AIDLService is null");
                        }
                    } else {
                        throw new RemoteException("AIDLService is not bound");
                    }
                }
                if (EnumC02330aI.fromOperationType(fbnsAIDLRequest.A00).hasReturn()) {
                    Bundle bundle = ((FbnsAIDLResult) fbnsAIDLRequest).A00;
                    if (bundle == null) {
                        bundle = Bundle.EMPTY;
                    }
                    bundle.toString();
                    fbnsAIDLResult = iFbnsAIDLService.A7l(fbnsAIDLRequest);
                } else {
                    Bundle bundle2 = ((FbnsAIDLResult) fbnsAIDLRequest).A00;
                    if (bundle2 == null) {
                        bundle2 = Bundle.EMPTY;
                    }
                    bundle2.toString();
                    iFbnsAIDLService.A9i(fbnsAIDLRequest);
                }
            } catch (DeadObjectException e) {
                AnonymousClass0NO.A0B("FbnsAIDLClientManager", "Fbns AIDL request got DeadObjectException", e);
            } catch (RemoteException e2) {
                AnonymousClass0NO.A0B("FbnsAIDLClientManager", "Fbns AIDL request got RemoteException", e2);
            }
            C02320aG.A00(r3);
            return fbnsAIDLResult;
        } catch (Throwable th) {
            C02320aG.A00(this.A01);
            throw th;
        }
    }
}
