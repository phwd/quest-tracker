package com.oculus.deviceconfigclient;

import X.AnonymousClass0NO;
import X.AnonymousClass0b8;
import X.AnonymousClass0b9;
import X.C00910Hi;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.deviceconfigclient.shared.Constants;
import com.oculus.deviceconfigclient.shared.StorageInternalRepresentation;

@SuppressLint({"AvoidSubClassing", "BadSuperClassSecureBroadcastReceiver.OculusSecureBroadcastReceiverBase"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceConfigClientReceiver extends C00910Hi {
    public static final String GENERIC_ERROR = "Some error occured.";
    public static final String TAG = "DeviceConfigClientReceiver";
    public static final boolean mDebugLog = false;

    public static class SetClientValuesAction implements AnonymousClass0b8 {
        @Override // X.AnonymousClass0b8
        @SuppressLint({"CatchGeneralException", "LogMethodNoExceptionInCatch"})
        public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r6) {
            Bundle extras = intent.getExtras();
            if (extras == null || DeviceConfigClient.sInUse.get() != 0) {
                AnonymousClass0NO.A08(DeviceConfigClientReceiver.TAG, DeviceConfigClientReceiver.GENERIC_ERROR);
                return;
            }
            try {
                StorageInternalRepresentation storageInternalRepresentation = (StorageInternalRepresentation) extras.getSerializable(Constants.INTENT_EXTRA_INTERNAL_REPRESENTATION);
                if (storageInternalRepresentation != null) {
                    ConfigStorageCache.A00(context, storageInternalRepresentation);
                } else {
                    AnonymousClass0NO.A08(DeviceConfigClientReceiver.TAG, DeviceConfigClientReceiver.GENERIC_ERROR);
                }
            } catch (Exception unused) {
                AnonymousClass0NO.A08(DeviceConfigClientReceiver.TAG, DeviceConfigClientReceiver.GENERIC_ERROR);
            }
        }
    }

    public DeviceConfigClientReceiver() {
        super(Constants.DC_SET_CLIENT_VALUES_ACTION, new SetClientValuesAction());
    }
}
