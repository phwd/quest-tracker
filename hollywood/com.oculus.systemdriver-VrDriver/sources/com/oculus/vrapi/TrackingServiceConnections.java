package com.oculus.vrapi;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;

public class TrackingServiceConnections {
    private static final String TAG = "TrackingServiceConnections";
    private static final Hashtable<IBinder, Client> mCallbackMap = new Hashtable<>();
    private static boolean mInitialized = false;
    private static Object mLockObject = new Object();
    private static final HashSet<Long> mRegisteredRemotes = new HashSet<>();

    public static native boolean notifyRemoteButtonState(long j, int i);

    public static native boolean notifyRemoteConnected(long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8);

    public static native boolean notifyRemoteDisconnected(long j);

    public static void initialize() {
        mInitialized = true;
    }

    public static void shutdown() {
        Long[] remotes;
        synchronized (mLockObject) {
            remotes = (Long[]) mRegisteredRemotes.toArray(new Long[0]);
            mCallbackMap.clear();
        }
        if (remotes.length > 0) {
            Log.w(TAG, remotes.length + " remotes are still registered.  Forcibly unregistering.");
            for (Long remote : remotes) {
                notifyRemoteDisconnected(remote.longValue());
            }
        }
        verifyNoClientConnections();
        mInitialized = false;
    }

    public static boolean isInitialized() {
        return mInitialized;
    }

    public static void registerClient(ITrackingServiceClient callback, String clientDescription) {
        assertNotDestroyed();
        Log.i(TAG, "registerClientCallback from: " + clientDescription);
        try {
            synchronized (mLockObject) {
                Client client = new Client(callback, clientDescription);
                mCallbackMap.put(callback.asBinder(), client);
                Iterator<Long> it = mRegisteredRemotes.iterator();
                while (it.hasNext()) {
                    notifyClientRemoteConnected(it.next().longValue(), client);
                }
            }
        } catch (RemoteException e) {
            Log.e("TAG", "RemoteException during registerClientCallback" + e);
        }
    }

    public static void unregisterClient(ITrackingServiceClient callback) {
        assertNotDestroyed();
        Log.i(TAG, "unregisterClientCallback");
        removeRegisteredClient(callback);
    }

    public static void verifyNoClientConnections() {
        assertNotDestroyed();
        if (mCallbackMap.size() > 0) {
            Log.e(TAG, "Client callback are still registered when asked to verify that there are no connections");
        }
    }

    /* access modifiers changed from: private */
    public static void removeRegisteredClient(ITrackingServiceClient callback) {
        synchronized (mLockObject) {
            Client removedClient = mCallbackMap.remove(callback.asBinder());
            if (removedClient != null) {
                Log.i(TAG, "Removed registration for client " + removedClient.mDescription);
                removedClient.destroy();
            } else {
                Log.e(TAG, "Failed to remove client callback because callback was not in array");
            }
        }
    }

    public static boolean notifyRemoteConnectedFromNative(long deviceIdentifier) {
        assertNotDestroyed();
        synchronized (mLockObject) {
            if (mRegisteredRemotes.contains(Long.valueOf(deviceIdentifier))) {
                Log.e(TAG, "Tried to register remote connection for device " + Long.toHexString(deviceIdentifier) + " which has already been registered");
                return false;
            }
            mRegisteredRemotes.add(Long.valueOf(deviceIdentifier));
            Log.i(TAG, "Remote connected: " + Long.toHexString(deviceIdentifier));
            for (Client client : mCallbackMap.values()) {
                notifyClientRemoteConnected(deviceIdentifier, client);
            }
            return true;
        }
    }

    private static void notifyClientRemoteConnected(long deviceIdentifier, Client client) {
        try {
            Log.i(TAG, "Notifying client " + client.mDescription + " of new remote " + Long.toHexString(deviceIdentifier));
            client.mClient.onRemoteConnected(deviceIdentifier);
        } catch (RemoteException ex) {
            Log.e(TAG, "notifyClientRemoteConnected failed: " + ex);
        }
    }

    public static void notifyRemoteDisconnectedFromNative(long deviceIdentifier) {
        assertNotDestroyed();
        synchronized (mLockObject) {
            if (!mRegisteredRemotes.remove(Long.valueOf(deviceIdentifier))) {
                Log.e(TAG, "Requested disconnect of remote " + deviceIdentifier + " that was not registered");
                return;
            }
            for (Client client : mCallbackMap.values()) {
                notifyClientRemoteDisconnected(deviceIdentifier, client);
            }
        }
    }

    private static void notifyClientRemoteDisconnected(long deviceIdentifier, Client client) {
        try {
            Log.v(TAG, String.format("Notifying client %s of disconnect of remote %d", client.mDescription, Long.valueOf(deviceIdentifier)));
            client.mClient.onRemoteDisconnected(deviceIdentifier);
        } catch (RemoteException ex) {
            Log.e(TAG, "notifyClientRemoteDisconnected failed: " + ex);
        }
    }

    public static void notifyRemoteButtonStateFromNative(long deviceIdentifier, int buttonData) {
        assertNotDestroyed();
        synchronized (mLockObject) {
            for (Client client : mCallbackMap.values()) {
                try {
                    client.mClient.onButtonDown(deviceIdentifier, buttonData);
                } catch (RemoteException ex) {
                    Log.e(TAG, "notifyRemoteButtonState failed: " + ex);
                }
            }
        }
    }

    private static void assertNotDestroyed() {
        if (!mInitialized) {
            throw new AssertionError("TrackingServiceConnections is not initialized");
        }
    }

    /* access modifiers changed from: private */
    public static class Client implements IBinder.DeathRecipient {
        final ITrackingServiceClient mClient;
        final String mDescription;

        Client(ITrackingServiceClient client, String clientDescription) throws RemoteException {
            this.mClient = client;
            this.mDescription = clientDescription;
            this.mClient.asBinder().linkToDeath(this, 0);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void destroy() {
            this.mClient.asBinder().unlinkToDeath(this, 0);
        }

        public void binderDied() {
            Log.i(TrackingServiceConnections.TAG, "Client died. Removing callback");
            TrackingServiceConnections.removeRegisteredClient(this.mClient);
        }
    }
}
