package com.oculus.deviceconfigclient;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.debug.log.BLog;
import com.facebook.secure.receiver.ActionReceiver;
import com.facebook.secure.receiver.BroadcastReceiverLike;
import com.facebook.secure.receiver.DynamicSecureBroadcastReceiver;
import com.oculus.deviceconfigclient.shared.Constants;
import com.oculus.deviceconfigclient.shared.StorageInternalRepresentation;

@SuppressLint({"AvoidSubClassing", "BadSuperClassSecureBroadcastReceiver.OculusSecureBroadcastReceiverBase"})
public class DeviceConfigClientReceiver extends DynamicSecureBroadcastReceiver {
    private static final String GENERIC_ERROR = "Some error occured.";
    private static final String TAG = DeviceConfigClientReceiver.class.getSimpleName();
    private static final boolean mDebugLog = false;

    public DeviceConfigClientReceiver() {
        super(Constants.DC_SET_CLIENT_VALUES_ACTION, new SetClientValuesAction());
    }

    public static class SetClientValuesAction implements ActionReceiver {
        @Override // com.facebook.secure.receiver.ActionReceiver
        @SuppressLint({"CatchGeneralException", "LogMethodNoExceptionInCatch"})
        public void onReceive(Context context, Intent intent, BroadcastReceiverLike receiver) {
            Bundle bundle = intent.getExtras();
            if (bundle == null) {
                BLog.e(DeviceConfigClientReceiver.TAG, DeviceConfigClientReceiver.GENERIC_ERROR);
            } else if (DeviceConfigClient.isInUse()) {
                BLog.e(DeviceConfigClientReceiver.TAG, DeviceConfigClientReceiver.GENERIC_ERROR);
            } else {
                try {
                    StorageInternalRepresentation internalRepresentation = (StorageInternalRepresentation) bundle.getSerializable(Constants.INTENT_EXTRA_INTERNAL_REPRESENTATION);
                    if (internalRepresentation != null) {
                        ConfigStorageCache.writeRepresentationToStorageCache(context, internalRepresentation);
                    } else {
                        BLog.e(DeviceConfigClientReceiver.TAG, DeviceConfigClientReceiver.GENERIC_ERROR);
                    }
                } catch (Exception e) {
                    BLog.e(DeviceConfigClientReceiver.TAG, DeviceConfigClientReceiver.GENERIC_ERROR);
                }
            }
        }
    }
}
