package com.oculus.antipiracy;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.os.ICompanionServer;
import com.oculus.util.thread.ThreadUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_antipiracy_AntiPiracyMethods_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID"})
public class AntiPiracyManager {
    public static final String COMPANION_SERVER = "com.oculus.companion.server.CompanionServer";
    public static final String EVENT_ANTI_PIRACY_DEV_STATUS_FETCH_FAILED = "oculus_anti_piracy_failed_to_fetch_developer_status";
    public static final String EVENT_ANTI_PIRCY_KILLSWITCH_ACTION_COMPLETED = "oculus_anti_piracy_completed_killswitch_action";
    public static final String EVENT_FAILED_TO_PERFORM_ANTI_PIRACY_KILLSWITH_ACTION = "oculus_anti_piracy_failed_to_perform_killswitch_action";
    public static final String EVENT_STARTED_ANTI_PIRACY_KILLSWITCH_ACTION = "oculus_anti_piracy_started_killswitch_action";
    public static final String TAG = "AntiPiracyManager";
    public static final String VR_DEVELOPER_MODE = "vrmode_developer_mode";
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final AntiPiracyMethods mAntiPiracyMethods;
    @Inject
    public final Provider<Credentials> mCredentialsProvider;

    public static final class CompanionServerConnection implements ServiceConnection {
        public final CountDownLatch latch = new CountDownLatch(1);
        @Nullable
        public ICompanionServer server;

        public final void onServiceDisconnected(ComponentName componentName) {
            this.server = null;
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.server = ICompanionServer.Stub.asInterface(iBinder);
            this.latch.countDown();
        }
    }

    @AutoGeneratedAccessMethod
    public static final AntiPiracyManager A00(AbstractC06640p5 r1) {
        return (AntiPiracyManager) AnonymousClass117.A00(54, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final AntiPiracyManager A01(AbstractC06640p5 r1) {
        return new AntiPiracyManager(r1);
    }

    @Nullable
    public final OculusDeveloperQueryResponse A02() {
        OculusDeveloperQueryResponse oculusDeveloperStatus = this.mAntiPiracyMethods.mMethods.getOculusDeveloperStatus(GraphQLQuery.OCULUS_DEVELOPER_STATUS_QUERY);
        if (oculusDeveloperStatus == null || oculusDeveloperStatus.mOculusDeveloperStatus == null) {
            ((EventManager) AnonymousClass0J2.A03(2, 242, this._UL_mInjectionContext)).A23(EVENT_ANTI_PIRACY_DEV_STATUS_FETCH_FAILED, null, false).A5L();
        }
        return oculusDeveloperStatus;
    }

    public final void A03() throws InterruptedException {
        boolean A04;
        Event A23;
        ((ThreadUtils) AnonymousClass0J2.A03(1, 583, this._UL_mInjectionContext)).A05();
        CompanionServerConnection companionServerConnection = new CompanionServerConnection();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.oculus.companion.server", "com.oculus.companion.server.CompanionServer"));
        if (((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext)).bindService(intent, companionServerConnection, 1)) {
            try {
                if (companionServerConnection.latch.await(5, TimeUnit.SECONDS)) {
                    ICompanionServer iCompanionServer = companionServerConnection.server;
                    if (iCompanionServer != null) {
                        try {
                            ((EventManager) AnonymousClass0J2.A03(2, 242, this._UL_mInjectionContext)).A23(EVENT_STARTED_ANTI_PIRACY_KILLSWITCH_ACTION, null, false).A5L();
                            iCompanionServer.performAntiPiracyKillSwitchAction();
                        } catch (RemoteException e) {
                            AnonymousClass0NO.A0B(TAG, "Error during transaction with CompanionServer", e);
                        }
                        if (!A04) {
                            Event A232 = ((EventManager) AnonymousClass0J2.A03(2, 242, this._UL_mInjectionContext)).A23(EVENT_ANTI_PIRCY_KILLSWITCH_ACTION_COMPLETED, null, false);
                        }
                        return;
                    }
                    throw new IllegalStateException("Failed to get CompanionServer connection Object");
                }
                throw new IllegalStateException("Timed out waiting to bind to CompanionServer");
            } finally {
                if (A04()) {
                    AnonymousClass0NO.A08(TAG, "Failed to Disable Developer access on Device");
                    A23 = ((EventManager) AnonymousClass0J2.A03(2, 242, this._UL_mInjectionContext)).A23(EVENT_FAILED_TO_PERFORM_ANTI_PIRACY_KILLSWITH_ACTION, null, false);
                } else {
                    A23 = ((EventManager) AnonymousClass0J2.A03(2, 242, this._UL_mInjectionContext)).A23(EVENT_ANTI_PIRCY_KILLSWITCH_ACTION_COMPLETED, null, false);
                }
                A23.A5L();
                ((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext)).unbindService(companionServerConnection);
            }
        } else {
            throw new IllegalStateException("Unable to bind to CompanionServer");
        }
    }

    public final boolean A04() {
        if (Settings.Global.getInt(((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext)).getContentResolver(), VR_DEVELOPER_MODE, 0) != 0) {
            return true;
        }
        return false;
    }

    @Inject
    public AntiPiracyManager(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r3);
        this.mAntiPiracyMethods = AntiPiracyMethods.A00(r3);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r3);
    }
}
