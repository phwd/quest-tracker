package com.oculus.appmanager.installer.contract;

import X.AbstractC06640p5;
import X.AnonymousClass117;
import android.content.Context;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.oculus.util.service.ServiceFutures;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_service_ServiceFutures_ULSEP_BINDING_ID"})
public class InstallerServiceContract {
    public static final String ACCESS_TOKEN_KEY = "access_token";
    public static final String DOWNLOAD_ID = "download_id";
    public static final String INSTALLER_CLASS = "com.oculus.appmanager.installer.service.InstallerService";
    public static final String INSTALLER_PACKAGE = "com.oculus.horizon";
    public static final String RETRY_VERIFYING_ATTEMPT = "retry_verifying_attempt";
    public static final String TAG = "InstallerServiceContract";
    public static final String UPDATE_ID = "update_id";
    public Context mContext;
    @Inject
    @Eager
    public final ServiceFutures mServiceFutures;

    public static class Cancel {
        public static final String ACTION_CANCEL = "action_cancel";
        public static final String ACTION_CANCELED = "action_canceled";
    }

    public static class Consistency {
        public static final String ACTION_CONSISTENCY_FOR_ID = "action_consistency_for_id";
    }

    public static class Download {
        public static final String ACTION_DOWNLOAD_AND_INSTALL = "action_download_and_install";
        public static final String ACTION_DOWNLOAD_COMPLETE = "action_download_complete";
        public static final String ACTION_DOWNLOAD_NOTIFICATION = "action_notify_download_complete";
    }

    public static class Failure {
        public static final String ACTION_FAILED = "action_failed";
    }

    public static class Install {
        public static final String ACTION_INSTALL_COMPLETED = "action_install_complete";
        public static final String ACTION_INSTALL_SUCCESSFUL = "action_install_success";
    }

    public static class Misc {
        public static final String ACTION_BOOT_CLEANUP = "action_boot_cleanup";
        public static final String ACTION_CONTINUE_UPDATE_TICKLE = "action_continue_next_update";
    }

    public static class Retry {
        public static final String ACTION_RETRY = "action_retry";
    }

    public static class Uninstall {
        public static final String ACTION_UNINSTALL = "action_uninstall";
        public static final String ACTION_UNINSTALL_COMPLETED = "action_uninstall_complete";
    }

    public static class Verify {
        public static final String ACTION_VERIFICATION_COMPLETE = "action_verification_complete";
    }

    @Inject
    public InstallerServiceContract(AbstractC06640p5 r2, @UnsafeContextInjection Context context) {
        this.mServiceFutures = (ServiceFutures) AnonymousClass117.A00(269, r2);
        this.mContext = context;
    }
}
