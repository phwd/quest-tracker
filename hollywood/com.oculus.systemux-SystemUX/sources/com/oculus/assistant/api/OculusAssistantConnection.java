package com.oculus.assistant.api;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.assistant.api.IOculusAssistantService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OculusAssistantConnection implements ServiceConnection {
    private static final String TAG = "OAConnection";
    private final Map<String, OculusAssistantSubscription> mActiveSubscriptions = Collections.synchronizedMap(new HashMap());
    private IOculusAssistantService mOculusAssistantService = null;

    /* access modifiers changed from: protected */
    public void onAssistantConnected() {
    }

    /* access modifiers changed from: protected */
    public void onAssistantDisconnected() {
    }

    public boolean isConnectedToAssistant() {
        return this.mOculusAssistantService != null;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.mOculusAssistantService = IOculusAssistantService.Stub.asInterface(iBinder);
        subscribePendingSubscriptions();
        onAssistantConnected();
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.mOculusAssistantService = null;
        onAssistantDisconnected();
    }

    public void post(String str, Bundle bundle) {
        IOculusAssistantService iOculusAssistantService = this.mOculusAssistantService;
        if (iOculusAssistantService != null) {
            try {
                iOculusAssistantService.post(str, bundle);
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to post message to Assistant: ", e);
            }
        }
    }

    public void subscribe(OculusAssistantSubscription oculusAssistantSubscription) {
        String uuid = UUID.randomUUID().toString();
        if (!isConnectedToAssistant()) {
            oculusAssistantSubscription.setSubscriberKey(uuid);
            this.mActiveSubscriptions.put(uuid, oculusAssistantSubscription);
            return;
        }
        String subscribe = subscribe(oculusAssistantSubscription.getMessageType(), oculusAssistantSubscription.getSubscriber());
        if (subscribe != null) {
            oculusAssistantSubscription.setSubscriberKey(subscribe);
            this.mActiveSubscriptions.put(subscribe, oculusAssistantSubscription);
        }
    }

    public void unsubscribe(OculusAssistantSubscription oculusAssistantSubscription) {
        this.mActiveSubscriptions.remove(oculusAssistantSubscription.getSubscriberKey());
        if (isConnectedToAssistant()) {
            try {
                this.mOculusAssistantService.unsubscribe(oculusAssistantSubscription.getMessageType(), oculusAssistantSubscription.getSubscriberKey());
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to unsubscribe to Assistant: ", e);
            }
        }
    }

    private void subscribePendingSubscriptions() {
        ArrayList<OculusAssistantSubscription> arrayList = new ArrayList();
        for (Map.Entry<String, OculusAssistantSubscription> entry : this.mActiveSubscriptions.entrySet()) {
            entry.getValue().setSubscriberKey(subscribe(entry.getValue().getMessageType(), entry.getValue().getSubscriber()));
            arrayList.add(entry.getValue());
        }
        this.mActiveSubscriptions.clear();
        for (OculusAssistantSubscription oculusAssistantSubscription : arrayList) {
            this.mActiveSubscriptions.put(oculusAssistantSubscription.getSubscriberKey(), oculusAssistantSubscription);
        }
    }

    private String subscribe(String str, IOculusAssistantSubscriber iOculusAssistantSubscriber) {
        try {
            return this.mOculusAssistantService.subscribe(str, iOculusAssistantSubscriber);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to subscribe to Assistant: ", e);
            return null;
        }
    }
}
