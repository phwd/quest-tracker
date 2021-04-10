package com.oculus.horizon.service.result;

import android.os.Bundle;
import android.os.RemoteException;
import com.oculus.horizon.service.OVRService;
import javax.annotation.Nullable;

@Deprecated
public abstract class ResultBuilderNoBundle<T> {
    public ResultBuilder mBuilder;
    public OVRService mService;
    @Nullable
    public T mValue;

    @Nullable
    public T A00(Bundle bundle) throws RemoteException {
        this.mBuilder.A00(bundle);
        Bundle bundle2 = this.mBuilder.mErrorBundle;
        if (bundle2 == null) {
            return this.mValue;
        }
        throw new RemoteException(bundle2.getString("error_message"));
    }

    public ResultBuilderNoBundle(OVRService oVRService) {
        this.mService = oVRService;
        this.mBuilder = new ResultBuilder(oVRService) {
            /* class com.oculus.horizon.service.result.ResultBuilderNoBundle.AnonymousClass1 */
        };
    }
}
