package com.oculus.downloader.dispatcher;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class OculusDownloadListenerDispatcherAutoProvider extends AbstractProvider<OculusDownloadListenerDispatcher> {
    @Override // javax.inject.Provider
    public OculusDownloadListenerDispatcher get() {
        return new OculusDownloadListenerDispatcher(DispatcherModule._UL__ULSEP_java_util_Set_ULLT_com_oculus_downloader_OculusDownloadListener_ULGT__ULSEP_ACCESS_METHOD(this));
    }
}
