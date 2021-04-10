package com.oculus.appmanager.info;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractAssistedProvider;
import com.facebook.inject.InjectorLike;
import com.google.common.collect.ImmutableList;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.appmanager.info.database.ApkUpdateExtrasManager;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.debug.DebugMode;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.extras.Extras;
import java.util.Map;
import javax.annotation.Nullable;

@Generated({"By: InjectorProcessor"})
public class ApkUpdateInfoProvider extends AbstractAssistedProvider<ApkUpdateInfo> {
    public ApkUpdateInfoProvider(InjectorLike injectorLike) {
        super(injectorLike);
    }

    public ApkUpdateInfo get(long j, String str, ApkUpdateInfoContract.UpdateType updateType, long j2, long j3, boolean z, long j4, @Nullable String str2, RequestOrigin requestOrigin, ImmutableList<Long> immutableList, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, Extras extras, @Nullable Map<String, String> map) {
        return new ApkUpdateInfo(j, str, updateType, j2, j3, z, j4, str2, requestOrigin, immutableList, str3, str4, str5, str6, str7, str8, str9, str10, extras, map, ApkUpdateInfoDispatcher._UL__ULSEP_com_oculus_appmanager_info_ApkUpdateInfoDispatcher_ULSEP_ACCESS_METHOD(this), ApkUpdateExtrasManager._UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateExtrasManager_ULSEP_ACCESS_METHOD(this), DebugMode._UL__ULSEP_com_oculus_debug_DebugMode_ULSEP_ACCESS_METHOD(this), InterfacesModule._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_ACCESS_METHOD(this));
    }
}
