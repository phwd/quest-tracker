package com.oculus.autoupdates;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.google.common.util.concurrent.ListeningExecutorService;

@Generated({"By: InjectorProcessor"})
public class ListeningExecutorService_com_oculus_autoupdates_AutoUpdateExecutorMethodAutoProvider extends AbstractProvider<ListeningExecutorService> {
    @Override // javax.inject.Provider
    public ListeningExecutorService get() {
        return AutoUpdatesModule.provideListeningExecutorService();
    }
}
