package com.oculus.deviceconfigclient;

import X.AbstractC02700jf;
import X.AnonymousClass0MD;
import X.AnonymousClass0Uh;
import X.AnonymousClass0jg;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.deviceconfigclient.shared.Constants;
import com.oculus.deviceconfigclient.shared.StorageInternalRepresentation;

@SuppressLint({"AvoidSubClassing", "BadSuperClassSecureBroadcastReceiver.OculusSecureBroadcastReceiverBase"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceConfigClientReceiver extends AnonymousClass0Uh {
    public static final String GENERIC_ERROR = "Some error occured.";
    public static final String TAG = "DeviceConfigClientReceiver";
    public static final boolean mDebugLog = false;

    public static class SetClientValuesAction implements AbstractC02700jf {
        @Override // X.AbstractC02700jf
        @SuppressLint({"CatchGeneralException", "LogMethodNoExceptionInCatch"})
        public void onReceive(Context context, Intent intent, AnonymousClass0jg r6) {
            Bundle extras = intent.getExtras();
            if (extras == null || DeviceConfigClient.isInUse()) {
                AnonymousClass0MD.A04(DeviceConfigClientReceiver.TAG, DeviceConfigClientReceiver.GENERIC_ERROR);
                return;
            }
            try {
                StorageInternalRepresentation storageInternalRepresentation = (StorageInternalRepresentation) extras.getSerializable(Constants.INTENT_EXTRA_INTERNAL_REPRESENTATION);
                if (storageInternalRepresentation != null) {
                    ConfigStorageCache.writeRepresentationToStorageCache(context, storageInternalRepresentation);
                } else {
                    AnonymousClass0MD.A04(DeviceConfigClientReceiver.TAG, DeviceConfigClientReceiver.GENERIC_ERROR);
                }
            } catch (Exception unused) {
                AnonymousClass0MD.A04(DeviceConfigClientReceiver.TAG, DeviceConfigClientReceiver.GENERIC_ERROR);
            }
        }
    }

    public DeviceConfigClientReceiver() {
        super(Constants.DC_SET_CLIENT_VALUES_ACTION, new SetClientValuesAction());
    }

    public static /* synthetic */ String access$000() {
        return TAG;
    }
}
