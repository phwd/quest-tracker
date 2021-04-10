package com.oculus.autoupdates.database;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.autoupdates.AutoUpdatesModule;

@Generated({"By: InjectorProcessor"})
public class AutoUpdatesDBManagerAutoProvider extends AbstractProvider<AutoUpdatesDBManager> {
    @Override // javax.inject.Provider
    public AutoUpdatesDBManager get() {
        return new AutoUpdatesDBManager(AutoUpdatesModule._UL__ULSEP_com_oculus_autoupdates_database_AutoUpdatesDBHelper_ULSEP_ACCESS_METHOD(this), AutoUpdatesModule._UL__ULSEP_com_google_common_util_concurrent_ListeningExecutorService_ULSEP_com_oculus_autoupdates_AutoUpdateExecutor_ULSEP_ACCESS_METHOD(this));
    }
}
