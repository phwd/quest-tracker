package oculus.internal.yadi;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.Process;
import android.security.net.config.ApplicationConfig;
import android.util.Log;
import com.oculus.os.yadi.RemoteResource;
import com.oculus.os.yadi.YadiStatus;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import oculus.internal.yadi.DownloadException;
import oculus.internal.yadi.IDownloadProtocol;
import oculus.internal.yadi.YadiExecutor;

public final class DownloadService implements ConnectivityManager.OnNetworkActiveListener {
    private static final Map<RemoteResource.ServerType, IDownloadProtocol> DEFAULT_PROTOCOLS;
    private static final boolean IS_SYSTEM = (Process.myUid() == 1000);
    private ConnectivityManager _connectivity;
    private final Context _context;
    private Executor _executor;
    private final int _parallelism;
    private PowerManager _powerManager;
    private final Map<RemoteResource.ServerType, IDownloadProtocol> _protocols;
    private volatile boolean _running;
    private final Network _testNetwork;
    private WifiManager _wifiManager;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(RemoteResource.ServerType.HTTP, new HttpDownloadProtocol());
        DEFAULT_PROTOCOLS = Collections.unmodifiableMap(hashMap);
    }

    DownloadService(Context context, int i, Map<RemoteResource.ServerType, IDownloadProtocol> map, Network network) {
        this._protocols = map;
        this._context = context;
        this._parallelism = i;
        this._testNetwork = network;
    }

    public DownloadService(Context context, int i) {
        this(context, i, DEFAULT_PROTOCOLS, null);
    }

    public synchronized void init() {
        if (this._connectivity == null) {
            this._connectivity = (ConnectivityManager) this._context.getSystemService("connectivity");
            this._powerManager = (PowerManager) this._context.getSystemService("power");
            this._wifiManager = (WifiManager) this._context.getSystemService("wifi");
            if (this._connectivity == null || this._powerManager == null || this._wifiManager == null) {
                this._connectivity = null;
                this._powerManager = null;
                throw new IllegalStateException("Unable to initialize DownloadService");
            }
        }
    }

    public synchronized void start() {
        if (!this._running) {
            if (this._connectivity != null) {
                this._connectivity.addDefaultNetworkActiveListener(this);
                this._running = true;
                this._executor = new Executor(this._parallelism, 10);
                return;
            }
            throw new IllegalStateException("Uninitialized");
        }
    }

    public synchronized void stop() {
        if (this._running) {
            this._running = false;
            this._executor.shutdown();
            this._connectivity.removeDefaultNetworkActiveListener(this);
        }
    }

    public void enqueueDownload(RemoteResource remoteResource, String str, Consumer<Intent> consumer, Bundle bundle) {
        if (this._running) {
            this._executor.enqueue(new Download(remoteResource, str, consumer, bundle, new YadiTaskId(1, remoteResource.resourceId), Binder.getCallingUid(), Utils.getCallingNetsecConfig(this._context)));
            return;
        }
        throw new IllegalStateException("Not running");
    }

    public void enqueueSizeOf(RemoteResource remoteResource, Consumer<Intent> consumer) {
        if (this._running) {
            this._executor.enqueue(new SizeOf(remoteResource, consumer, new YadiTaskId(0, remoteResource.resourceId), Binder.getCallingUid(), Utils.getCallingNetsecConfig(this._context)));
            return;
        }
        throw new IllegalStateException("Not running");
    }

    private Network getActiveNetwork() {
        Network network = this._testNetwork;
        if (network != null) {
            return network;
        }
        try {
            return this._connectivity.getActiveNetwork();
        } catch (Exception e) {
            Log.wtf("YadiDownload", "ConnectivityService remote exception", e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Network waitForNetwork() {
        Network activeNetwork;
        synchronized (this._connectivity) {
            activeNetwork = getActiveNetwork();
            while (activeNetwork == null && this._running) {
                try {
                    Log.d("YadiDownload", "No network available, sleeping.");
                    this._connectivity.wait();
                    activeNetwork = getActiveNetwork();
                } catch (InterruptedException unused) {
                }
            }
        }
        return activeNetwork;
    }

    public void onNetworkActive() {
        Log.d("YadiDownload", "Network state changed");
        synchronized (this._connectivity) {
            this._connectivity.notifyAll();
        }
    }

    /* access modifiers changed from: private */
    public class Executor extends YadiExecutor<YadiTaskId, NetworkTask> {
        Executor(int i, int i2) {
            super(i, i2);
        }

        /* access modifiers changed from: protected */
        @Override // oculus.internal.yadi.YadiExecutor
        public void wakeAllActiveTasks() {
            super.wakeAllActiveTasks();
            synchronized (DownloadService.this._connectivity) {
                DownloadService.this._connectivity.notifyAll();
            }
        }

        /* access modifiers changed from: protected */
        public YadiStatus run(NetworkTask networkTask) {
            YadiStatus yadiStatus;
            YadiStatus yadiStatus2;
            Intent intent = new Intent();
            YadiStatus yadiStatus3 = YadiStatus.Terminated;
            try {
                intent.putExtra("YadiTag", networkTask.taskId.name);
                runTask(networkTask, intent);
                yadiStatus = YadiStatus.Complete;
            } catch (DownloadException.Reschedule unused) {
                yadiStatus = YadiStatus.Paused;
            } catch (DownloadException e) {
                yadiStatus2 = YadiStatus.Terminated;
                intent.putExtra("YadiError", e.getMessage());
                yadiStatus = yadiStatus2;
            } catch (Exception e2) {
                yadiStatus2 = YadiStatus.Terminated;
                intent.putExtra("YadiError", "Unknown");
                Log.e("YadiDownload", "Uncaught exception", e2);
                yadiStatus = yadiStatus2;
            }
            intent.putExtra("YadiStatus", (Parcelable) yadiStatus);
            networkTask.progress.accept(intent);
            return yadiStatus;
        }

        private Network getNetworkForTask(NetworkTask networkTask) {
            if (Utils.isLoopbackHost(networkTask.request.downloadUri)) {
                return new Network(0);
            }
            return DownloadService.this.waitForNetwork();
        }

        private void runTask(NetworkTask networkTask, Intent intent) throws IOException {
            Network networkForTask = getNetworkForTask(networkTask);
            if (networkForTask == null || !DownloadService.this._running) {
                throw new DownloadException.Reschedule();
            }
            networkTask._wakeLock.acquire();
            networkTask._wifiLock.acquire();
            try {
                networkTask.run(networkForTask, intent);
            } finally {
                networkTask._wifiLock.release();
                networkTask._wakeLock.release();
            }
        }
    }

    /* access modifiers changed from: private */
    public abstract class NetworkTask implements YadiExecutor.Task<YadiTaskId> {
        final PowerManager.WakeLock _wakeLock;
        final WifiManager.WifiLock _wifiLock;
        public final ApplicationConfig config;
        public final Consumer<Intent> progress;
        public final RemoteResource request;
        public final YadiTaskId taskId;
        public final int uid;

        @Override // oculus.internal.yadi.YadiExecutor.Task
        public void after(YadiStatus yadiStatus) {
        }

        @Override // oculus.internal.yadi.YadiExecutor.Task
        public void before() {
        }

        /* access modifiers changed from: package-private */
        public abstract void run(Network network, Intent intent) throws IOException;

        NetworkTask(RemoteResource remoteResource, Consumer<Intent> consumer, YadiTaskId yadiTaskId, int i, ApplicationConfig applicationConfig) {
            this.request = remoteResource;
            this.progress = consumer;
            this.taskId = yadiTaskId;
            this.uid = i;
            this.config = applicationConfig;
            PowerManager powerManager = DownloadService.this._powerManager;
            this._wakeLock = powerManager.newWakeLock(1, "YADI-" + remoteResource.resourceId);
            WifiManager wifiManager = DownloadService.this._wifiManager;
            this._wifiLock = wifiManager.createWifiLock(1, "YADI-" + remoteResource.resourceId);
        }

        @Override // oculus.internal.yadi.YadiExecutor.Task
        public YadiTaskId getIdentifier() {
            return this.taskId;
        }

        @Override // oculus.internal.yadi.YadiExecutor.Task
        public void cancel() {
            Intent intent = new Intent();
            intent.putExtra("YadiStatus", (Parcelable) YadiStatus.Terminated);
            intent.putExtra("YadiError", "Canceled");
            this.progress.accept(intent);
        }

        public String toString() {
            return getClass().getSimpleName() + "(" + this.request.toString() + ")";
        }
    }

    private class Download extends NetworkTask implements IDownloadProtocol.ProgressFunc {
        private final File _destination;
        private long _lastUpdateBytes;
        private long _lastUpdateTime;
        private Bundle _resumeInfo;
        private long _totalDownloadSize;
        private Intent _updates;

        private Download(RemoteResource remoteResource, String str, Consumer<Intent> consumer, Bundle bundle, YadiTaskId yadiTaskId, int i, ApplicationConfig applicationConfig) {
            super(remoteResource, consumer, yadiTaskId, i, applicationConfig);
            this._updates = new Intent();
            this._lastUpdateBytes = 0;
            this._totalDownloadSize = 0;
            this._lastUpdateTime = 0;
            this._destination = new File(str);
            this._resumeInfo = bundle;
        }

        @Override // oculus.internal.yadi.DownloadService.NetworkTask, oculus.internal.yadi.YadiExecutor.Task
        public void after(YadiStatus yadiStatus) {
            if (yadiStatus == YadiStatus.Terminated) {
                this._destination.delete();
            }
        }

        @Override // oculus.internal.yadi.DownloadService.NetworkTask, oculus.internal.yadi.YadiExecutor.Task
        public void cancel() {
            super.cancel();
            this._destination.delete();
        }

        @Override // oculus.internal.yadi.IDownloadProtocol.ProgressFunc
        public YadiStatus update(long j, long j2) {
            long currentTimeMillis = System.currentTimeMillis() - this._lastUpdateTime;
            long j3 = j - this._lastUpdateBytes;
            this._totalDownloadSize = j;
            if ((j2 != -1 && 20 * j3 >= j2) || j3 >= 2000000 || currentTimeMillis >= 20000) {
                Intent intent = new Intent();
                intent.putExtra("YadiTag", this.taskId.name);
                intent.putExtra("YadiStatus", (Parcelable) YadiStatus.Downloading);
                intent.putExtra("YadiBytes", j);
                intent.putExtra("YadiSize", j2);
                this._lastUpdateTime += currentTimeMillis;
                this._lastUpdateBytes = j;
                this.progress.accept(intent);
            }
            return YadiStatus.Downloading;
        }

        /* access modifiers changed from: package-private */
        @Override // oculus.internal.yadi.DownloadService.NetworkTask
        public void run(Network network, Intent intent) throws IOException {
            try {
                ((IDownloadProtocol) DownloadService.this._protocols.get(this.request.protocol)).download(network, this.config, this.request, this._destination, this._resumeInfo, this);
                intent.putExtra("YadiBytes", this._totalDownloadSize);
            } catch (DownloadException.ResumableReschedule e) {
                intent.putExtra("YadiResume", e.resumeInfo);
                this._resumeInfo = e.resumeInfo;
                throw e;
            } catch (DownloadException.Reschedule e2) {
                intent.putExtra("YadiResume", Bundle.EMPTY);
                this._resumeInfo = Bundle.EMPTY;
                this._destination.delete();
                throw e2;
            }
        }
    }

    private class SizeOf extends NetworkTask {
        private SizeOf(RemoteResource remoteResource, Consumer<Intent> consumer, YadiTaskId yadiTaskId, int i, ApplicationConfig applicationConfig) {
            super(remoteResource, consumer, yadiTaskId, i, applicationConfig);
        }

        @Override // oculus.internal.yadi.DownloadService.NetworkTask
        public void run(Network network, Intent intent) throws IOException {
            intent.putExtra("YadiSize", ((IDownloadProtocol) DownloadService.this._protocols.get(this.request.protocol)).sizeOf(network, this.config, this.request));
        }
    }
}
