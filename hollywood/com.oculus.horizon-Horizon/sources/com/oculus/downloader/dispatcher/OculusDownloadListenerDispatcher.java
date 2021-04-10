package com.oculus.downloader.dispatcher;

import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.oculus.dispatcher.ListenerDispatcher;
import com.oculus.downloader.OculusDownloadListener;
import java.util.Set;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_java_util_Set_ULLT_com_oculus_downloader_OculusDownloadListener_ULGT__ULSEP_BINDING_ID"})
@ApplicationScoped
public class OculusDownloadListenerDispatcher extends ListenerDispatcher<OculusDownloadListener> {
    public static volatile OculusDownloadListenerDispatcher _UL__ULSEP_com_oculus_downloader_dispatcher_OculusDownloadListenerDispatcher_ULSEP_INSTANCE;

    @Inject
    public OculusDownloadListenerDispatcher(Set<OculusDownloadListener> set) {
        super(set);
    }
}
