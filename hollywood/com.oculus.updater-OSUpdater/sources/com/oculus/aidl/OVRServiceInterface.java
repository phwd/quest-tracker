package com.oculus.aidl;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.facebook.ultralight.UL;
import java.util.Map;

public interface OVRServiceInterface extends IInterface {
    Bundle assetFileDeleteById(Bundle bundle) throws RemoteException;

    Bundle assetFileDeleteByName(Bundle bundle) throws RemoteException;

    Bundle assetFileDownloadById(Bundle bundle) throws RemoteException;

    Bundle assetFileDownloadByName(Bundle bundle) throws RemoteException;

    Bundle assetFileDownloadCancelById(Bundle bundle) throws RemoteException;

    Bundle assetFileDownloadCancelByName(Bundle bundle) throws RemoteException;

    Bundle assetFileList() throws RemoteException;

    Bundle assetFileStatusById(Bundle bundle) throws RemoteException;

    Bundle assetFileStatusByName(Bundle bundle) throws RemoteException;

    Bundle getAppScopedAccessToken(Bundle bundle) throws RemoteException;

    Bundle getBaseURLEndpoint(Bundle bundle) throws RemoteException;

    Bundle getCloudStorage2UserDirectoryPath(Bundle bundle) throws RemoteException;

    Bundle getCurrentMapUuid(Bundle bundle) throws RemoteException;

    Bundle getDeviceScopedAccessToken(Bundle bundle) throws RemoteException;

    Bundle getEntitlementCheckBundle(Bundle bundle) throws RemoteException;

    Intent getIAPCheckoutFlowIntent(String str, Bundle bundle) throws RemoteException;

    Bundle getInstalledVRApplications(Bundle bundle) throws RemoteException;

    Intent getInvitableUsersFlowIntent(Bundle bundle) throws RemoteException;

    Bundle getLatestAvailableAppInformation(Bundle bundle) throws RemoteException;

    Bundle getSharedMicrophoneData(Bundle bundle) throws RemoteException;

    ParcelFileDescriptor getSharedMicrophoneSocketFD(Bundle bundle) throws RemoteException;

    Bundle getSystemVoipData(Bundle bundle) throws RemoteException;

    String getSystemVoipMicrophoneMuted(Bundle bundle) throws RemoteException;

    boolean getSystemVoipPassthrough(Bundle bundle) throws RemoteException;

    String getSystemVoipStatus(Bundle bundle) throws RemoteException;

    Bundle getViewerPurchasesDurableCacheJSON(Bundle bundle) throws RemoteException;

    int isAppEntitled() throws RemoteException;

    Bundle languagePackGetCurrent(Bundle bundle) throws RemoteException;

    Bundle languagePackSetCurrent(Bundle bundle) throws RemoteException;

    Bundle launchOtherApp(Bundle bundle) throws RemoteException;

    Map loadPublicProfileForUser(String str) throws RemoteException;

    Bundle logToMarauder(String str, Bundle bundle) throws RemoteException;

    Bundle partyChatGetVolume(Bundle bundle) throws RemoteException;

    Bundle partyChatSetVolume(Bundle bundle) throws RemoteException;

    Bundle registerProcessToken(Bundle bundle) throws RemoteException;

    Bundle requestMap(Bundle bundle) throws RemoteException;

    Map savePublicProfileData(String str, String str2, String str3, String str4) throws RemoteException;

    Bundle setRichPresence(Bundle bundle) throws RemoteException;

    Bundle setSystemVoipMicrophoneMuted(Bundle bundle) throws RemoteException;

    ParcelFileDescriptor setSystemVoipPassthrough(Bundle bundle) throws RemoteException;

    Bundle setSystemVoipSuppressed(Bundle bundle) throws RemoteException;

    Bundle shareMap(Bundle bundle) throws RemoteException;

    void sharedMicrophoneDisableNoiseSuppressor(Bundle bundle) throws RemoteException;

    void sharedMicrophoneEnableNoiseSuppressor(Bundle bundle) throws RemoteException;

    Bundle sharedMicrophoneStop(Bundle bundle) throws RemoteException;

    Bundle startPartyChat(Bundle bundle) throws RemoteException;

    Bundle stopPartyChat(Bundle bundle) throws RemoteException;

    Bundle updatePlatformContext(Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements OVRServiceInterface {
        public Stub() {
            attachInterface(this, "com.oculus.aidl.OVRServiceInterface");
        }

        public static OVRServiceInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.oculus.aidl.OVRServiceInterface");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof OVRServiceInterface)) {
                return new Proxy(iBinder);
            }
            return (OVRServiceInterface) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                Bundle bundle = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        int isAppEntitled = isAppEntitled();
                        parcel2.writeNoException();
                        parcel2.writeInt(isAppEntitled);
                        return true;
                    case 2:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        String readString = parcel.readString();
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Intent iAPCheckoutFlowIntent = getIAPCheckoutFlowIntent(readString, bundle);
                        parcel2.writeNoException();
                        if (iAPCheckoutFlowIntent != null) {
                            parcel2.writeInt(1);
                            iAPCheckoutFlowIntent.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 3:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        Map loadPublicProfileForUser = loadPublicProfileForUser(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeMap(loadPublicProfileForUser);
                        return true;
                    case 4:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        Map savePublicProfileData = savePublicProfileData(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeMap(savePublicProfileData);
                        return true;
                    case 5:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle appScopedAccessToken = getAppScopedAccessToken(bundle);
                        parcel2.writeNoException();
                        if (appScopedAccessToken != null) {
                            parcel2.writeInt(1);
                            appScopedAccessToken.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 6:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle baseURLEndpoint = getBaseURLEndpoint(bundle);
                        parcel2.writeNoException();
                        if (baseURLEndpoint != null) {
                            parcel2.writeInt(1);
                            baseURLEndpoint.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 7:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle entitlementCheckBundle = getEntitlementCheckBundle(bundle);
                        parcel2.writeNoException();
                        if (entitlementCheckBundle != null) {
                            parcel2.writeInt(1);
                            entitlementCheckBundle.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 8:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        String readString2 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle logToMarauder = logToMarauder(readString2, bundle);
                        parcel2.writeNoException();
                        if (logToMarauder != null) {
                            parcel2.writeInt(1);
                            logToMarauder.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 9:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle latestAvailableAppInformation = getLatestAvailableAppInformation(bundle);
                        parcel2.writeNoException();
                        if (latestAvailableAppInformation != null) {
                            parcel2.writeInt(1);
                            latestAvailableAppInformation.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 10:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Intent invitableUsersFlowIntent = getInvitableUsersFlowIntent(bundle);
                        parcel2.writeNoException();
                        if (invitableUsersFlowIntent != null) {
                            parcel2.writeInt(1);
                            invitableUsersFlowIntent.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 11:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle installedVRApplications = getInstalledVRApplications(bundle);
                        parcel2.writeNoException();
                        if (installedVRApplications != null) {
                            parcel2.writeInt(1);
                            installedVRApplications.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        ParcelFileDescriptor systemVoipPassthrough = setSystemVoipPassthrough(bundle);
                        parcel2.writeNoException();
                        if (systemVoipPassthrough != null) {
                            parcel2.writeInt(1);
                            systemVoipPassthrough.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle systemVoipMicrophoneMuted = setSystemVoipMicrophoneMuted(bundle);
                        parcel2.writeNoException();
                        if (systemVoipMicrophoneMuted != null) {
                            parcel2.writeInt(1);
                            systemVoipMicrophoneMuted.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_android_os_PowerManager_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle systemVoipSuppressed = setSystemVoipSuppressed(bundle);
                        parcel2.writeNoException();
                        if (systemVoipSuppressed != null) {
                            parcel2.writeInt(1);
                            systemVoipSuppressed.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_oculus_multiuser_UserClassifier_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle systemVoipData = getSystemVoipData(bundle);
                        parcel2.writeNoException();
                        if (systemVoipData != null) {
                            parcel2.writeInt(1);
                            systemVoipData.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_oculus_auth_storage_AuthDatastore_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        boolean systemVoipPassthrough2 = getSystemVoipPassthrough(bundle);
                        parcel2.writeNoException();
                        parcel2.writeInt(systemVoipPassthrough2 ? 1 : 0);
                        return true;
                    case UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        String systemVoipStatus = getSystemVoipStatus(bundle);
                        parcel2.writeNoException();
                        parcel2.writeString(systemVoipStatus);
                        return true;
                    case UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentAppVersionMap_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        String systemVoipMicrophoneMuted2 = getSystemVoipMicrophoneMuted(bundle);
                        parcel2.writeNoException();
                        parcel2.writeString(systemVoipMicrophoneMuted2);
                        return true;
                    case UL.id._UL__ULSEP_com_oculus_updater_core_monitors_SystemUpdatePolicyMonitor_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle startPartyChat = startPartyChat(bundle);
                        parcel2.writeNoException();
                        if (startPartyChat != null) {
                            parcel2.writeInt(1);
                            startPartyChat.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle stopPartyChat = stopPartyChat(bundle);
                        parcel2.writeNoException();
                        if (stopPartyChat != null) {
                            parcel2.writeInt(1);
                            stopPartyChat.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle partyChatSetVolume = partyChatSetVolume(bundle);
                        parcel2.writeNoException();
                        if (partyChatSetVolume != null) {
                            parcel2.writeInt(1);
                            partyChatSetVolume.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_java_lang_Boolean_ULSEP_com_oculus_http_core_annotations_ReportGraphBackendException_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle partyChatGetVolume = partyChatGetVolume(bundle);
                        parcel2.writeNoException();
                        if (partyChatGetVolume != null) {
                            parcel2.writeInt(1);
                            partyChatGetVolume.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenSubscriber_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle sharedMicrophoneData = getSharedMicrophoneData(bundle);
                        parcel2.writeNoException();
                        if (sharedMicrophoneData != null) {
                            parcel2.writeInt(1);
                            sharedMicrophoneData.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_oculus_updater_core_os_SystemProperties_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        ParcelFileDescriptor sharedMicrophoneSocketFD = getSharedMicrophoneSocketFD(bundle);
                        parcel2.writeNoException();
                        if (sharedMicrophoneSocketFD != null) {
                            parcel2.writeInt(1);
                            sharedMicrophoneSocketFD.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 25:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle sharedMicrophoneStop = sharedMicrophoneStop(bundle);
                        parcel2.writeNoException();
                        if (sharedMicrophoneStop != null) {
                            parcel2.writeInt(1);
                            sharedMicrophoneStop.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_oculus_http_core_interceptor_OculusAuthorizationInterceptor_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        sharedMicrophoneDisableNoiseSuppressor(bundle);
                        parcel2.writeNoException();
                        return true;
                    case UL.id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        sharedMicrophoneEnableNoiseSuppressor(bundle);
                        parcel2.writeNoException();
                        return true;
                    case UL.id._UL__ULSEP_com_oculus_executors_OculusThreadExecutor_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle updatePlatformContext = updatePlatformContext(bundle);
                        parcel2.writeNoException();
                        if (updatePlatformContext != null) {
                            parcel2.writeInt(1);
                            updatePlatformContext.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_android_net_wifi_WifiManager_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle assetFileDownloadById = assetFileDownloadById(bundle);
                        parcel2.writeNoException();
                        if (assetFileDownloadById != null) {
                            parcel2.writeInt(1);
                            assetFileDownloadById.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle assetFileDownloadByName = assetFileDownloadByName(bundle);
                        parcel2.writeNoException();
                        if (assetFileDownloadByName != null) {
                            parcel2.writeInt(1);
                            assetFileDownloadByName.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle assetFileDownloadCancelById = assetFileDownloadCancelById(bundle);
                        parcel2.writeNoException();
                        if (assetFileDownloadCancelById != null) {
                            parcel2.writeInt(1);
                            assetFileDownloadCancelById.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_facebook_xanalytics_XAnalyticsProvider_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle assetFileDownloadCancelByName = assetFileDownloadCancelByName(bundle);
                        parcel2.writeNoException();
                        if (assetFileDownloadCancelByName != null) {
                            parcel2.writeInt(1);
                            assetFileDownloadCancelByName.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle assetFileDeleteById = assetFileDeleteById(bundle);
                        parcel2.writeNoException();
                        if (assetFileDeleteById != null) {
                            parcel2.writeInt(1);
                            assetFileDeleteById.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_oculus_logging_utils_EventManagerImpl_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle assetFileDeleteByName = assetFileDeleteByName(bundle);
                        parcel2.writeNoException();
                        if (assetFileDeleteByName != null) {
                            parcel2.writeInt(1);
                            assetFileDeleteByName.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_oculus_updater_core_monitors_IdleMonitor_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle assetFileStatusById = assetFileStatusById(bundle);
                        parcel2.writeNoException();
                        if (assetFileStatusById != null) {
                            parcel2.writeInt(1);
                            assetFileStatusById.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle assetFileStatusByName = assetFileStatusByName(bundle);
                        parcel2.writeNoException();
                        if (assetFileStatusByName != null) {
                            parcel2.writeInt(1);
                            assetFileStatusByName.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_facebook_gk_store_GatekeeperWriter_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        Bundle assetFileList = assetFileList();
                        parcel2.writeNoException();
                        if (assetFileList != null) {
                            parcel2.writeInt(1);
                            assetFileList.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_oculus_updater_device_DeviceInfo_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle languagePackGetCurrent = languagePackGetCurrent(bundle);
                        parcel2.writeNoException();
                        if (languagePackGetCurrent != null) {
                            parcel2.writeInt(1);
                            languagePackGetCurrent.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle languagePackSetCurrent = languagePackSetCurrent(bundle);
                        parcel2.writeNoException();
                        if (languagePackSetCurrent != null) {
                            parcel2.writeInt(1);
                            languagePackSetCurrent.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle cloudStorage2UserDirectoryPath = getCloudStorage2UserDirectoryPath(bundle);
                        parcel2.writeNoException();
                        if (cloudStorage2UserDirectoryPath != null) {
                            parcel2.writeInt(1);
                            cloudStorage2UserDirectoryPath.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_java_util_Locale_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle launchOtherApp = launchOtherApp(bundle);
                        parcel2.writeNoException();
                        if (launchOtherApp != null) {
                            parcel2.writeInt(1);
                            launchOtherApp.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_facebook_common_manifest_AppBuildInfoReader_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle richPresence = setRichPresence(bundle);
                        parcel2.writeNoException();
                        if (richPresence != null) {
                            parcel2.writeInt(1);
                            richPresence.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_oculus_updater_core_os_UpdateEngine_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle registerProcessToken = registerProcessToken(bundle);
                        parcel2.writeNoException();
                        if (registerProcessToken != null) {
                            parcel2.writeInt(1);
                            registerProcessToken.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle viewerPurchasesDurableCacheJSON = getViewerPurchasesDurableCacheJSON(bundle);
                        parcel2.writeNoException();
                        if (viewerPurchasesDurableCacheJSON != null) {
                            parcel2.writeInt(1);
                            viewerPurchasesDurableCacheJSON.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle currentMapUuid = getCurrentMapUuid(bundle);
                        parcel2.writeNoException();
                        if (currentMapUuid != null) {
                            parcel2.writeInt(1);
                            currentMapUuid.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle shareMap = shareMap(bundle);
                        parcel2.writeNoException();
                        if (shareMap != null) {
                            parcel2.writeInt(1);
                            shareMap.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle requestMap = requestMap(bundle);
                        parcel2.writeNoException();
                        if (requestMap != null) {
                            parcel2.writeInt(1);
                            requestMap.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case UL.id._UL__ULSEP_com_facebook_auth_viewercontext_ViewerContextManager_ULSEP_com_facebook_auth_viewercontext_ViewerContextManagerForContext_ULSEP_BINDING_ID:
                        parcel.enforceInterface("com.oculus.aidl.OVRServiceInterface");
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        Bundle deviceScopedAccessToken = getDeviceScopedAccessToken(bundle);
                        parcel2.writeNoException();
                        if (deviceScopedAccessToken != null) {
                            parcel2.writeInt(1);
                            deviceScopedAccessToken.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.oculus.aidl.OVRServiceInterface");
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements OVRServiceInterface {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public int isAppEntitled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Intent getIAPCheckoutFlowIntent(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Map loadPublicProfileForUser(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Map savePublicProfileData(String str, String str2, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle getAppScopedAccessToken(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle getBaseURLEndpoint(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle getEntitlementCheckBundle(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle logToMarauder(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle getLatestAvailableAppInformation(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Intent getInvitableUsersFlowIntent(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle getInstalledVRApplications(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public ParcelFileDescriptor setSystemVoipPassthrough(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle setSystemVoipMicrophoneMuted(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle setSystemVoipSuppressed(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle getSystemVoipData(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public boolean getSystemVoipPassthrough(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    boolean z = true;
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public String getSystemVoipStatus(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public String getSystemVoipMicrophoneMuted(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle startPartyChat(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle stopPartyChat(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle partyChatSetVolume(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle partyChatGetVolume(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle getSharedMicrophoneData(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public ParcelFileDescriptor getSharedMicrophoneSocketFD(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle sharedMicrophoneStop(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public void sharedMicrophoneDisableNoiseSuppressor(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public void sharedMicrophoneEnableNoiseSuppressor(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle updatePlatformContext(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle assetFileDownloadById(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle assetFileDownloadByName(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle assetFileDownloadCancelById(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle assetFileDownloadCancelByName(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle assetFileDeleteById(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle assetFileDeleteByName(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle assetFileStatusById(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle assetFileStatusByName(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle assetFileList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    this.mRemote.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle languagePackGetCurrent(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle languagePackSetCurrent(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle getCloudStorage2UserDirectoryPath(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle launchOtherApp(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle setRichPresence(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle registerProcessToken(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle getViewerPurchasesDurableCacheJSON(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle getCurrentMapUuid(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle shareMap(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle requestMap(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(47, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.OVRServiceInterface
            public Bundle getDeviceScopedAccessToken(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.OVRServiceInterface");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(48, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
