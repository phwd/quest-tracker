package org.webrtc;

import X.AnonymousClass006;
import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.webrtc.NetworkMonitorAutoDetect;

public class NetworkMonitor {
    public static final String TAG = "NetworkMonitor";
    public static NetworkMonitor instance;
    public final Context applicationContext;
    public NetworkMonitorAutoDetect autoDetector;
    public NetworkMonitorAutoDetect.ConnectionType currentConnectionType = NetworkMonitorAutoDetect.ConnectionType.CONNECTION_UNKNOWN;
    public final ArrayList<Long> nativeNetworkObservers;
    public final ArrayList<NetworkObserver> networkObservers;

    public interface NetworkObserver {
        void onConnectionTypeChanged(NetworkMonitorAutoDetect.ConnectionType connectionType);
    }

    private native void nativeNotifyConnectionTypeChanged(long j);

    private native void nativeNotifyOfActiveNetworkList(long j, NetworkMonitorAutoDetect.NetworkInformation[] networkInformationArr);

    private native void nativeNotifyOfNetworkConnect(long j, NetworkMonitorAutoDetect.NetworkInformation networkInformation);

    private native void nativeNotifyOfNetworkDisconnect(long j, int i);

    public static void addNetworkObserver(NetworkObserver networkObserver) {
        instance.addNetworkObserverInternal(networkObserver);
    }

    private void addNetworkObserverInternal(NetworkObserver networkObserver) {
        this.networkObservers.add(networkObserver);
    }

    public static void assertIsTrue(boolean z) {
        if (!z) {
            throw new AssertionError("Expected to be true");
        }
    }

    private void destroyAutoDetector() {
        NetworkMonitorAutoDetect networkMonitorAutoDetect = this.autoDetector;
        if (networkMonitorAutoDetect != null) {
            networkMonitorAutoDetect.destroy();
            this.autoDetector = null;
        }
    }

    public static NetworkMonitorAutoDetect getAutoDetectorForTest() {
        return instance.autoDetector;
    }

    private int getCurrentDefaultNetId() {
        NetworkMonitorAutoDetect networkMonitorAutoDetect = this.autoDetector;
        if (networkMonitorAutoDetect == null) {
            return -1;
        }
        return networkMonitorAutoDetect.connectivityManagerDelegate.getDefaultNetId();
    }

    public static boolean isInitialized() {
        if (instance != null) {
            return true;
        }
        return false;
    }

    public static boolean isOnline() {
        NetworkMonitorAutoDetect.ConnectionType connectionType = instance.currentConnectionType;
        if (connectionType == NetworkMonitorAutoDetect.ConnectionType.CONNECTION_UNKNOWN || connectionType == NetworkMonitorAutoDetect.ConnectionType.CONNECTION_NONE) {
            return false;
        }
        return true;
    }

    private void notifyObserversOfConnectionTypeChange(NetworkMonitorAutoDetect.ConnectionType connectionType) {
        Iterator<Long> it = this.nativeNetworkObservers.iterator();
        while (it.hasNext()) {
            nativeNotifyConnectionTypeChanged(it.next().longValue());
        }
        Iterator<NetworkObserver> it2 = this.networkObservers.iterator();
        while (it2.hasNext()) {
            it2.next().onConnectionTypeChanged(connectionType);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyObserversOfNetworkConnect(NetworkMonitorAutoDetect.NetworkInformation networkInformation) {
        Iterator<Long> it = this.nativeNetworkObservers.iterator();
        while (it.hasNext()) {
            nativeNotifyOfNetworkConnect(it.next().longValue(), networkInformation);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyObserversOfNetworkDisconnect(int i) {
        Iterator<Long> it = this.nativeNetworkObservers.iterator();
        while (it.hasNext()) {
            nativeNotifyOfNetworkDisconnect(it.next().longValue(), i);
        }
    }

    public static void removeNetworkObserver(NetworkObserver networkObserver) {
        instance.removeNetworkObserverInternal(networkObserver);
    }

    private void removeNetworkObserverInternal(NetworkObserver networkObserver) {
        this.networkObservers.remove(networkObserver);
    }

    public static void resetInstanceForTests(Context context) {
        instance = new NetworkMonitor(context);
    }

    public static void setAutoDetectConnectivityState(boolean z) {
        instance.setAutoDetectConnectivityStateInternal(z);
    }

    private void setAutoDetectConnectivityStateInternal(boolean z) {
        if (!z) {
            destroyAutoDetector();
        } else if (this.autoDetector == null) {
            NetworkMonitorAutoDetect networkMonitorAutoDetect = new NetworkMonitorAutoDetect(new NetworkMonitorAutoDetect.Observer() {
                /* class org.webrtc.NetworkMonitor.AnonymousClass1 */

                @Override // org.webrtc.NetworkMonitorAutoDetect.Observer
                public void onConnectionTypeChanged(NetworkMonitorAutoDetect.ConnectionType connectionType) {
                    NetworkMonitor.this.updateCurrentConnectionType(connectionType);
                }

                @Override // org.webrtc.NetworkMonitorAutoDetect.Observer
                public void onNetworkConnect(NetworkMonitorAutoDetect.NetworkInformation networkInformation) {
                    NetworkMonitor.this.notifyObserversOfNetworkConnect(networkInformation);
                }

                @Override // org.webrtc.NetworkMonitorAutoDetect.Observer
                public void onNetworkDisconnect(int i) {
                    NetworkMonitor.this.notifyObserversOfNetworkDisconnect(i);
                }
            }, this.applicationContext);
            this.autoDetector = networkMonitorAutoDetect;
            updateCurrentConnectionType(NetworkMonitorAutoDetect.getConnectionType(networkMonitorAutoDetect.connectivityManagerDelegate.getNetworkState()));
            updateActiveNetworkList();
        }
    }

    private void startMonitoring(long j) {
        Logging.d(TAG, AnonymousClass006.A04("Start monitoring from native observer ", j));
        this.nativeNetworkObservers.add(Long.valueOf(j));
        setAutoDetectConnectivityStateInternal(true);
    }

    private void stopMonitoring(long j) {
        Logging.d(TAG, AnonymousClass006.A04("Stop monitoring from native observer ", j));
        destroyAutoDetector();
        this.nativeNetworkObservers.remove(Long.valueOf(j));
    }

    private void updateActiveNetworkList() {
        List<NetworkMonitorAutoDetect.NetworkInformation> activeNetworkList = this.autoDetector.connectivityManagerDelegate.getActiveNetworkList();
        if (activeNetworkList != null && activeNetworkList.size() != 0) {
            NetworkMonitorAutoDetect.NetworkInformation[] networkInformationArr = (NetworkMonitorAutoDetect.NetworkInformation[]) activeNetworkList.toArray(new NetworkMonitorAutoDetect.NetworkInformation[activeNetworkList.size()]);
            Iterator<Long> it = this.nativeNetworkObservers.iterator();
            while (it.hasNext()) {
                nativeNotifyOfActiveNetworkList(it.next().longValue(), networkInformationArr);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateCurrentConnectionType(NetworkMonitorAutoDetect.ConnectionType connectionType) {
        this.currentConnectionType = connectionType;
        notifyObserversOfConnectionTypeChange(connectionType);
    }

    public NetworkMonitor(Context context) {
        assertIsTrue(context != null);
        this.applicationContext = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.nativeNetworkObservers = new ArrayList<>();
        this.networkObservers = new ArrayList<>();
    }

    private NetworkMonitorAutoDetect.ConnectionType getCurrentConnectionType() {
        return this.currentConnectionType;
    }

    public static NetworkMonitor getInstance() {
        return instance;
    }

    public static NetworkMonitor init(Context context) {
        if (!isInitialized()) {
            instance = new NetworkMonitor(context);
        }
        return instance;
    }
}
