package com.oculus.assistant.service.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.oculus.assistant.service.api.attention.IOculusAssistantAttentionService;
import com.oculus.assistant.service.api.control.IOculusAssistantControlService;
import com.oculus.assistant.service.api.transcription.IOculusAssistantTranscriptionService;

public interface IOculusAssistantService extends IInterface {
    IOculusAssistantAttentionService getAttentionService();

    IOculusAssistantControlService getControlService();

    IOculusAssistantTranscriptionService getTranscriptionService();

    public abstract class Stub extends Binder implements IOculusAssistantService {
        public static final String DESCRIPTOR = "com.oculus.assistant.service.api.IOculusAssistantService";
        public static final int TRANSACTION_getAttentionService = 2;
        public static final int TRANSACTION_getControlService = 1;
        public static final int TRANSACTION_getTranscriptionService = 3;

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IBinder iBinder = null;
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                IOculusAssistantControlService controlService = getControlService();
                parcel2.writeNoException();
                if (controlService != null) {
                    iBinder = controlService.asBinder();
                }
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                IOculusAssistantAttentionService attentionService = getAttentionService();
                parcel2.writeNoException();
                if (attentionService != null) {
                    iBinder = attentionService.asBinder();
                }
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                IOculusAssistantTranscriptionService transcriptionService = getTranscriptionService();
                parcel2.writeNoException();
                if (transcriptionService != null) {
                    iBinder = transcriptionService.asBinder();
                }
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel2.writeStrongBinder(iBinder);
            return true;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }
    }
}
