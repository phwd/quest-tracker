package oculus.internal.yadi;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Process;
import android.util.Log;
import com.oculus.os.yadi.RemoteApp;
import com.oculus.os.yadi.RemoteResource;
import com.oculus.os.yadi.YadiStatus;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import oculus.internal.functional.Pair;
import oculus.internal.functional.Try;
import oculus.internal.yadi.Awaitable;
import oculus.internal.yadi.Db;
import oculus.internal.yadi.InstallService;
import oculus.internal.yadi.YadiExecutor;

public final class InstallService {
    private final File _cachePath;
    private final Context _context;
    private Db _db;
    private Executor _executor;
    private PackageManager _packageManager;
    private volatile boolean _running;
    private DownloadService _yadiDownload;

    public InstallService(Context context, File file) throws IOException {
        this._context = context;
        this._cachePath = file;
        if (!this._cachePath.exists()) {
            int myUid = Process.myUid();
            Os.mkdir_dashp(this._cachePath, 448, myUid, myUid);
        }
    }

    public synchronized void init(DownloadService downloadService) {
        this._yadiDownload = downloadService;
        this._packageManager = this._context.getPackageManager();
    }

    public synchronized void start() {
        if (!this._running) {
            this._running = true;
            this._db = new Db(this._context);
            this._executor = new Executor();
        }
    }

    public synchronized void stop() {
        if (this._running) {
            this._running = false;
            this._db.close();
            this._executor.shutdown();
        }
    }

    public void enqueueInstall(RemoteApp remoteApp, RemoteResource[] remoteResourceArr, Consumer<Intent> consumer) {
        this._executor.enqueue(new Installation(remoteApp, remoteResourceArr, new YadiTaskId(0, remoteApp.appId), consumer));
    }

    /* access modifiers changed from: private */
    public class Executor extends YadiExecutor<YadiTaskId, Installation> {
        Executor() {
            super(1, 0);
        }

        /* access modifiers changed from: protected */
        public YadiStatus run(Installation installation) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("oculus.internal.yadi.ACTION_INSTALL_COMPLETE");
            intentFilter.addCategory(installation.app.appId);
            InstallService.this._context.registerReceiver(installation, intentFilter);
            try {
                Intent intent = new Intent();
                intent.putExtra("YadiTag", installation.taskId.name);
                YadiStatus run = installation.run(intent);
                intent.putExtra("YadiStatus", (Parcelable) run);
                installation.progress.accept(intent);
                return run;
            } finally {
                InstallService.this._context.unregisterReceiver(installation);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public class Installation extends BroadcastReceiver implements YadiExecutor.Task<YadiTaskId> {
        private final HashMap<RemoteResource, Long> _bytesDownloaded = new HashMap<>();
        private final CompletableFuture<Void> _installComplete = new CompletableFuture<>();
        private long _lastUpdateTime = 0;
        private Optional<Long> _totalDownloadSize = Optional.empty();
        final RemoteApp app;
        final Map<String, RemoteResource> assets;
        final RemoteResource binaryResource;
        final Consumer<Intent> progress;
        final YadiTaskId taskId;

        static /* synthetic */ Try lambda$reserveSpace$16(Try r0) {
            return r0;
        }

        Installation(RemoteApp remoteApp, RemoteResource[] remoteResourceArr, YadiTaskId yadiTaskId, Consumer<Intent> consumer) {
            this.app = remoteApp;
            this.assets = new HashMap(remoteResourceArr.length - 1);
            RemoteResource remoteResource = null;
            for (RemoteResource remoteResource2 : remoteResourceArr) {
                if (remoteResource2.resourceId.equals(remoteApp.binaryResourceId)) {
                    remoteResource = remoteResource2;
                } else {
                    this.assets.put(remoteResource2.resourceId, remoteResource2);
                }
            }
            this.binaryResource = remoteResource;
            this.taskId = yadiTaskId;
            this.progress = consumer;
        }

        public YadiStatus run(Intent intent) {
            if (!InstallService.this._db.needsUpdate(this.app)) {
                intent.putExtra("YadiPackageName", InstallService.this._db.getPackageName(this.app).get());
                intent.putExtra("YadiNoOp", true);
                return YadiStatus.Complete;
            }
            intent.putExtra("YadiNoOp", false);
            return (YadiStatus) reserveSpace().map($$Lambda$Qbglmbxoq1fk0_rAR0OE8_9aERM.INSTANCE).onSuccess(new Consumer() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$Svqhf1zMZzr1iHoSFoowFCoMWUQ */

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    InstallService.Installation.this.lambda$run$0$InstallService$Installation((Pair) obj);
                }
            }).flatMap(new Function() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$PzVTZR1ZhuyNMz64afJrVjZq_hA */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return InstallService.Installation.this.lambda$run$1$InstallService$Installation((Pair) obj);
                }
            }).map(new Function(intent) {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$ALHwEKN1VmEMWVaP0G7Wy4DMBT8 */
                private final /* synthetic */ Intent f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return InstallService.Installation.this.lambda$run$2$InstallService$Installation(this.f$1, (Boolean) obj);
                }
            }).flatMap(new Function() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$EUQmux58P8hGNvcgFROELPSUk */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return InstallService.Installation.this.lambda$run$3$InstallService$Installation((Optional) obj);
                }
            }).tryMap(new Try.F1() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$QdoxWMoJyxsDEQga21TrHCnjf8 */

                public final Object get(Object obj) {
                    return InstallService.Installation.this.lambda$run$4$InstallService$Installation((PackageInfo) obj);
                }
            }).tryMap(new Try.F1() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$zCopMSRipSIr5YFeT7LSk3yH3kc */

                public final Object get(Object obj) {
                    return InstallService.Installation.this.lambda$run$5$InstallService$Installation((ApplicationInfo) obj);
                }
            }).tryMap(new Try.F1(intent) {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$1nv5Su8CIEGTjXnCm7TIxl45PI */
                private final /* synthetic */ Intent f$1;

                {
                    this.f$1 = r2;
                }

                public final Object get(Object obj) {
                    return InstallService.Installation.this.lambda$run$6$InstallService$Installation(this.f$1, (ApplicationInfo) obj);
                }
            }).onFailure(new Consumer(intent) {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$cwvN8sBTd1IWu3xEFjm_Em9tAXY */
                private final /* synthetic */ Intent f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    InstallService.Installation.this.lambda$run$7$InstallService$Installation(this.f$1, (Exception) obj);
                }
            }).orElse(YadiStatus.Terminated);
        }

        public /* synthetic */ void lambda$run$0$InstallService$Installation(Pair pair) {
            this._totalDownloadSize = (Optional) pair.left;
        }

        public /* synthetic */ Try lambda$run$1$InstallService$Installation(Pair pair) {
            return downloadUpdatedResources((List) pair.right);
        }

        public /* synthetic */ Optional lambda$run$2$InstallService$Installation(Intent intent, Boolean bool) {
            long j = 0;
            for (Long l : this._bytesDownloaded.values()) {
                j += l.longValue();
            }
            intent.putExtra("YadiBytes", j);
            intent.putExtra("YadiStatus", (Parcelable) YadiStatus.Installing);
            this.progress.accept(intent);
            return InstallService.this._db.asInactiveResource(this.binaryResource);
        }

        public /* synthetic */ ApplicationInfo lambda$run$4$InstallService$Installation(PackageInfo packageInfo) throws Exception {
            return InstallService.this._packageManager.getApplicationInfo(packageInfo.packageName, 0);
        }

        public /* synthetic */ ApplicationInfo lambda$run$5$InstallService$Installation(ApplicationInfo applicationInfo) throws Exception {
            InstallServiceHelpers.installAssets(InstallService.this._db, InstallService.this._cachePath, applicationInfo, this.assets.values());
            return applicationInfo;
        }

        public /* synthetic */ YadiStatus lambda$run$6$InstallService$Installation(Intent intent, ApplicationInfo applicationInfo) throws Exception {
            InstallServiceHelpers.cleanupOldResources(InstallService.this._db, InstallService.this._cachePath, applicationInfo, this.app, this.binaryResource, this.assets.values());
            InstallService.this._db.deployApp(this.app, applicationInfo.packageName, true);
            long j = 0;
            for (Long l : this._bytesDownloaded.values()) {
                j += l.longValue();
            }
            intent.putExtra("YadiBytes", j);
            intent.putExtra("YadiPackageName", applicationInfo.packageName);
            return YadiStatus.Complete;
        }

        public /* synthetic */ void lambda$run$7$InstallService$Installation(Intent intent, Exception exc) {
            InstallServiceHelpers.purgeInactiveResources(InstallService.this._db, InstallService.this._cachePath, this.app);
            logAndReportException(exc, intent);
        }

        @Override // oculus.internal.yadi.YadiExecutor.Task
        public void before() {
            InstallServiceHelpers.cleanupCache(InstallService.this._db, InstallService.this._cachePath, this.binaryResource, this.assets.values());
            InstallService.this._db.getPackageName(this.app).ifPresent(new Consumer() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$KMEmlTxYX6BjHqK8b2hRpT3LZE */

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    InstallService.Installation.this.lambda$before$8$InstallService$Installation((String) obj);
                }
            });
        }

        public /* synthetic */ void lambda$before$8$InstallService$Installation(String str) {
            try {
                InstallService.this._packageManager.getPackageInfo(str, 0);
            } catch (PackageManager.NameNotFoundException unused) {
                InstallService.this._db.purgeApp(this.app);
            }
        }

        @Override // oculus.internal.yadi.YadiExecutor.Task
        public void cancel() {
            Intent intent = new Intent();
            intent.putExtra("YadiStatus", (Parcelable) YadiStatus.Terminated);
            intent.putExtra("YadiError", "Canceled");
            this.progress.accept(intent);
        }

        @Override // oculus.internal.yadi.YadiExecutor.Task
        public void after(YadiStatus yadiStatus) {
            InstallServiceHelpers.getInstallIntent(InstallService.this._context, "oculus.internal.yadi.ACTION_INSTALL_COMPLETE", this.app).cancel();
        }

        @Override // oculus.internal.yadi.YadiExecutor.Task
        public YadiTaskId getIdentifier() {
            return this.taskId;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!"oculus.internal.yadi.ACTION_INSTALL_COMPLETE".equals(action)) {
                Log.wtf("YadiInstall", "Invalid action received: " + action);
            }
            boolean z = true;
            int intExtra = intent.getIntExtra("android.content.pm.extra.STATUS", 1);
            int intExtra2 = intent.getIntExtra("android.content.pm.extra.LEGACY_STATUS", -110);
            if (!(intExtra == 0 || intExtra == 5 || intExtra2 == -25)) {
                z = false;
            }
            if (z) {
                this._installComplete.complete(null);
                return;
            }
            String stringExtra = intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE");
            Log.e("YadiInstall", "Failed to install " + this.binaryResource.filename + ": " + intExtra);
            this._installComplete.completeExceptionally(new DownloadException(stringExtra));
        }

        /* access modifiers changed from: private */
        /* renamed from: handleSizeOfUpdate */
        public void lambda$dispatchSizeOf$34$InstallService$Installation(Awaitable.Yadi yadi, Intent intent) {
            Bundle extras = intent.getExtras();
            int i = AnonymousClass1.$SwitchMap$com$oculus$os$yadi$YadiStatus[extras.getParcelable("YadiStatus").ordinal()];
            if (i == 1) {
                yadi.future.complete(extras);
            } else if (i == 2) {
                yadi.future.completeExceptionally(new DownloadException(extras.getString("YadiError")));
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: handleDownloadUpdate */
        public void lambda$dispatchDownload$35$InstallService$Installation(Awaitable.Yadi yadi, Intent intent) {
            Bundle extras = intent.getExtras();
            int i = AnonymousClass1.$SwitchMap$com$oculus$os$yadi$YadiStatus[extras.getParcelable("YadiStatus").ordinal()];
            if (i == 1) {
                handleDownloadProgress(yadi.name, Long.valueOf(extras.getLong("YadiBytes")));
                yadi.future.complete(extras);
            } else if (i == 2) {
                yadi.future.completeExceptionally(new DownloadException(extras.getString("YadiError")));
            } else if (i == 3) {
                handleDownloadProgress(yadi.name, Long.valueOf(extras.getLong("YadiBytes")));
            }
        }

        private void handleDownloadProgress(RemoteResource remoteResource, Long l) {
            long currentTimeMillis = System.currentTimeMillis();
            synchronized (this._bytesDownloaded) {
                this._bytesDownloaded.put(remoteResource, l);
                long j = 0;
                for (Long l2 : this._bytesDownloaded.values()) {
                    j += l2.longValue();
                }
                long j2 = currentTimeMillis - this._lastUpdateTime;
                if (j2 >= 5000) {
                    this._lastUpdateTime += j2;
                    Intent intent = new Intent();
                    intent.putExtra("YadiSize", this._totalDownloadSize.orElse(-1L));
                    intent.putExtra("YadiBytes", j);
                    intent.putExtra("YadiStatus", (Parcelable) YadiStatus.Downloading);
                    this.progress.accept(intent);
                }
            }
        }

        private Try<List<Pair<RemoteResource, Optional<Long>>>> reserveSpace() {
            Stream concat = Stream.concat(this.assets.values().stream(), Stream.of(this.binaryResource));
            Db db = InstallService.this._db;
            Objects.requireNonNull(db);
            List list = (List) concat.filter(new Predicate() {
                /* class oculus.internal.yadi.$$Lambda$Jnr5oa5V7vJXxgHyGvNqJiDDM5A */

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Db.this.didContentChange((RemoteResource) obj);
                }
            }).collect(Collectors.toList());
            return ((Try) ((List) list.stream().map(new Function() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$CVl8wzbWyr8gRXTRa9FHnxVfcNE */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return InstallService.Installation.this.lambda$reserveSpace$10$InstallService$Installation((RemoteResource) obj);
                }
            }).collect(Collectors.toList())).stream().map(new Function() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$PGvibcggvquDL3Xvc9ADe_VYzS0 */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return InstallService.Installation.this.lambda$reserveSpace$12$InstallService$Installation((Try) obj);
                }
            }).collect(Try.fold(Collectors.toList()))).map(new Function() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$p0HqOjpgQt7cZuPlepJPFGjtkSo */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return InstallService.Installation.this.lambda$reserveSpace$15$InstallService$Installation((List) obj);
                }
            }).flatMap($$Lambda$InstallService$Installation$LXtnVMsJUIBI550smqRb5Nzv78.INSTANCE).onFailure(new Consumer(list) {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$FtSXgRiW4LnxbiL5U33aZfAkeRU */
                private final /* synthetic */ List f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    InstallService.Installation.this.lambda$reserveSpace$18$InstallService$Installation(this.f$1, (Exception) obj);
                }
            });
        }

        public /* synthetic */ Try lambda$reserveSpace$10$InstallService$Installation(RemoteResource remoteResource) {
            return Try.Try(new Try.F0(remoteResource) {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$ovLzVKikTiCFHg3_sECXAi1CtY */
                private final /* synthetic */ RemoteResource f$1;

                {
                    this.f$1 = r2;
                }

                public final Object get() {
                    return InstallService.Installation.this.lambda$reserveSpace$9$InstallService$Installation(this.f$1);
                }
            });
        }

        public /* synthetic */ Try lambda$reserveSpace$12$InstallService$Installation(Try r2) {
            return r2.tryMap(new Try.F1() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$RowfWHD2q2ia1_gwj6I1bLqfI */

                public final Object get(Object obj) {
                    return InstallService.Installation.this.lambda$reserveSpace$11$InstallService$Installation((Awaitable.Yadi) obj);
                }
            }).map(new Function() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$wX6nQn6AUE90iyIDkfPwqo4ARMY */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return InstallService.Installation.this.parseSizeOfBundle((Pair) obj);
                }
            });
        }

        public /* synthetic */ Pair lambda$reserveSpace$11$InstallService$Installation(Awaitable.Yadi yadi) throws Exception {
            return InstallServiceHelpers.waitFor(InstallService.this._db, yadi, "Checking file size");
        }

        public /* synthetic */ Try lambda$reserveSpace$15$InstallService$Installation(List list) {
            return (Try) list.stream().map(new Function() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$sYpOFf6a2pcotZmyB19QUDNUvm8 */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return InstallService.Installation.this.lambda$reserveSpace$14$InstallService$Installation((Pair) obj);
                }
            }).collect(Try.fold(Collectors.toList()));
        }

        public /* synthetic */ Pair lambda$reserveSpace$13$InstallService$Installation(Pair pair) throws Exception {
            InstallServiceHelpers.fallocateSpace(InstallService.this._cachePath, InstallService.this._db, pair);
            return pair;
        }

        public /* synthetic */ Try lambda$reserveSpace$14$InstallService$Installation(Pair pair) {
            return Try.Try(new Try.F0(pair) {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$SmTsx0SoZp9ZSYOEsn6K3ha94A */
                private final /* synthetic */ Pair f$1;

                {
                    this.f$1 = r2;
                }

                public final Object get() {
                    return InstallService.Installation.this.lambda$reserveSpace$13$InstallService$Installation(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$reserveSpace$18$InstallService$Installation(List list, Exception exc) {
            list.forEach(new Consumer() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$8zWv90b5QP63VgHKA9WasDCcVM0 */

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    InstallService.Installation.this.lambda$reserveSpace$17$InstallService$Installation((RemoteResource) obj);
                }
            });
        }

        public /* synthetic */ void lambda$reserveSpace$17$InstallService$Installation(RemoteResource remoteResource) {
            Log.d("YadiInstall", "Download failed for " + remoteResource.downloadUri);
            InstallService.this._db.removeResource(Db.ResourceTable.temporary, remoteResource);
            new File(InstallService.this._cachePath, YadiPath.forDownload(remoteResource)).delete();
        }

        private Try<Boolean> downloadUpdatedResources(List<RemoteResource> list) {
            return ((Try) ((List) list.stream().map(new Function() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$Tr38GmMxBsqG3IC1dDArTwsErkU */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return InstallService.Installation.this.lambda$downloadUpdatedResources$20$InstallService$Installation((RemoteResource) obj);
                }
            }).collect(Collectors.toList())).stream().map(new Function() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$PWYHSKMq2NjW3_WSydlYiSy1__M */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return InstallService.Installation.this.lambda$downloadUpdatedResources$22$InstallService$Installation((Try) obj);
                }
            }).collect(Try.fold(Collectors.toList()))).map($$Lambda$InstallService$Installation$Z4BNyGqWD0nD48ZeZFJo4k70YM.INSTANCE);
        }

        public /* synthetic */ Try lambda$downloadUpdatedResources$20$InstallService$Installation(RemoteResource remoteResource) {
            return Try.Try(new Try.F0(remoteResource) {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$3nZPnHdGG39e0jJ5Mm5ESv1lNIo */
                private final /* synthetic */ RemoteResource f$1;

                {
                    this.f$1 = r2;
                }

                public final Object get() {
                    return InstallService.Installation.this.lambda$downloadUpdatedResources$19$InstallService$Installation(this.f$1);
                }
            });
        }

        public /* synthetic */ Try lambda$downloadUpdatedResources$22$InstallService$Installation(Try r2) {
            return r2.tryMap(new Try.F1() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$iifj4r5iyKmOU2vqGiTGsN0_S0 */

                public final Object get(Object obj) {
                    return InstallService.Installation.this.lambda$downloadUpdatedResources$21$InstallService$Installation((Awaitable.Yadi) obj);
                }
            }).tryMap(new Try.F1() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$JIR9PrtEcdTbz3xYwQ86V7jKlY */

                public final Object get(Object obj) {
                    return InstallService.Installation.this.handleCompleteDownload((RemoteResource) obj);
                }
            });
        }

        public /* synthetic */ RemoteResource lambda$downloadUpdatedResources$21$InstallService$Installation(Awaitable.Yadi yadi) throws Exception {
            return (RemoteResource) InstallServiceHelpers.waitFor(InstallService.this._db, yadi, "Downloading file").left;
        }

        /* access modifiers changed from: private */
        /* renamed from: installAppIfNeeded */
        public Try<PackageInfo> lambda$run$3$InstallService$Installation(Optional<RemoteResource> optional) {
            return optional.map(new Function() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$iza8X0BTqT81AWWWgfyxdlKvfLI */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return InstallService.Installation.this.lambda$installAppIfNeeded$29$InstallService$Installation((RemoteResource) obj);
                }
            }).orElseGet(new Supplier() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$zXcEtxbDEyFdu_HSfLjI0c04_uU */

                @Override // java.util.function.Supplier
                public final Object get() {
                    return InstallService.Installation.this.lambda$installAppIfNeeded$32$InstallService$Installation();
                }
            }).tryMap(new Try.F1() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$dSLMJ3FcYZmw7tbCcF3YEiAJ24 */

                public final Object get(Object obj) {
                    return InstallService.Installation.this.lambda$installAppIfNeeded$33$InstallService$Installation((PackageInfo) obj);
                }
            });
        }

        public /* synthetic */ Try lambda$installAppIfNeeded$29$InstallService$Installation(RemoteResource remoteResource) {
            File file = new File(InstallService.this._cachePath, YadiPath.forDownload(remoteResource));
            return Try.Try(new Try.F0(file, remoteResource) {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$WXnM_SFZdLcQGpj75s9idPRVkB8 */
                private final /* synthetic */ File f$0;
                private final /* synthetic */ RemoteResource f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final Object get() {
                    return Verifier.verifyPackageInfo(this.f$0, this.f$1);
                }
            }).flatMap(new Function(remoteResource, file) {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$5guumqtL1IxSgKAYzqr15fyh5zA */
                private final /* synthetic */ RemoteResource f$1;
                private final /* synthetic */ File f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return InstallService.Installation.this.lambda$installAppIfNeeded$25$InstallService$Installation(this.f$1, this.f$2, (PackageInfo) obj);
                }
            }).tryMap(new Try.F1(InstallServiceHelpers.getInstallIntent(InstallService.this._context, "oculus.internal.yadi.ACTION_INSTALL_COMPLETE", this.app), file) {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$K6kf0V90EwzjGBeqtydNbFjdrU */
                private final /* synthetic */ PendingIntent f$0;
                private final /* synthetic */ File f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final Object get(Object obj) {
                    return InstallServiceHelpers.installApp((Pair) obj, this.f$0.getIntentSender(), this.f$1);
                }
            }).flatMap(new Function(file, remoteResource) {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$__hNJdDjqzafTzMvjbQPAV71CIQ */
                private final /* synthetic */ File f$1;
                private final /* synthetic */ RemoteResource f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return InstallService.Installation.this.lambda$installAppIfNeeded$28$InstallService$Installation(this.f$1, this.f$2, (PackageInfo) obj);
                }
            });
        }

        public /* synthetic */ Try lambda$installAppIfNeeded$25$InstallService$Installation(RemoteResource remoteResource, File file, PackageInfo packageInfo) {
            return InstallServiceHelpers.getInstallerSession(InstallService.this._packageManager, remoteResource, packageInfo, file);
        }

        public /* synthetic */ Try lambda$installAppIfNeeded$28$InstallService$Installation(File file, RemoteResource remoteResource, PackageInfo packageInfo) {
            return Try.Try(new Try.F0(file, remoteResource, packageInfo) {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$2nFI1sg30g2fLWFNzw2UmpsrLZo */
                private final /* synthetic */ File f$1;
                private final /* synthetic */ RemoteResource f$2;
                private final /* synthetic */ PackageInfo f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final Object get() {
                    return InstallService.Installation.this.lambda$installAppIfNeeded$27$InstallService$Installation(this.f$1, this.f$2, this.f$3);
                }
            });
        }

        public /* synthetic */ PackageInfo lambda$installAppIfNeeded$27$InstallService$Installation(File file, RemoteResource remoteResource, PackageInfo packageInfo) throws Exception {
            this._installComplete.get();
            file.delete();
            InstallService.this._db.deployResource(Db.ResourceTable.local, remoteResource, null, true);
            return packageInfo;
        }

        public /* synthetic */ Try lambda$installAppIfNeeded$32$InstallService$Installation() {
            return Try.Try(new Try.F0() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$giraQ5cI7dJWG73ZeSsve7LALXg */

                public final Object get() {
                    return InstallService.Installation.this.lambda$installAppIfNeeded$31$InstallService$Installation();
                }
            });
        }

        public /* synthetic */ Optional lambda$installAppIfNeeded$30$InstallService$Installation(String str) {
            return InstallServiceHelpers.getPackageInfo(InstallService.this._packageManager, str);
        }

        public /* synthetic */ PackageInfo lambda$installAppIfNeeded$31$InstallService$Installation() throws Exception {
            return InstallService.this._db.getPackageName(this.app).flatMap(new Function() {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$HrjV2v60hrYMSgKRmqF6ijE_btg */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return InstallService.Installation.this.lambda$installAppIfNeeded$30$InstallService$Installation((String) obj);
                }
            }).orElseThrow($$Lambda$kv4flc7xtt2muGAPAKd_iXTNks.INSTANCE);
        }

        public /* synthetic */ PackageInfo lambda$installAppIfNeeded$33$InstallService$Installation(PackageInfo packageInfo) throws Exception {
            InstallService.this._db.deployApp(this.app, packageInfo.packageName, false);
            return packageInfo;
        }

        /* access modifiers changed from: private */
        /* renamed from: dispatchSizeOf */
        public Awaitable.Yadi lambda$reserveSpace$9$InstallService$Installation(RemoteResource remoteResource) throws InstallException {
            Awaitable.Yadi Yadi = Awaitable.Yadi(remoteResource);
            InstallService.this._yadiDownload.enqueueSizeOf(remoteResource, new Consumer(Yadi) {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$Rn0JBqhgH4YWV9GJNQGvyTHeWMM */
                private final /* synthetic */ Awaitable.Yadi f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    InstallService.Installation.this.lambda$dispatchSizeOf$34$InstallService$Installation(this.f$1, (Intent) obj);
                }
            });
            return Yadi;
        }

        /* access modifiers changed from: private */
        /* renamed from: dispatchDownload */
        public Awaitable.Yadi lambda$downloadUpdatedResources$19$InstallService$Installation(RemoteResource remoteResource) throws InstallException {
            Awaitable.Yadi Yadi = Awaitable.Yadi(remoteResource);
            InstallService.this._yadiDownload.enqueueDownload(remoteResource, new File(InstallService.this._cachePath, YadiPath.forDownload(remoteResource)).toString(), new Consumer(Yadi) {
                /* class oculus.internal.yadi.$$Lambda$InstallService$Installation$6yCdwObaHCSItDLi01egkaHAYiY */
                private final /* synthetic */ Awaitable.Yadi f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    InstallService.Installation.this.lambda$dispatchDownload$35$InstallService$Installation(this.f$1, (Intent) obj);
                }
            }, InstallService.this._db.getResumeInfo(remoteResource).orElse(Bundle.EMPTY));
            return Yadi;
        }

        /* access modifiers changed from: private */
        public Pair<RemoteResource, Optional<Long>> parseSizeOfBundle(Pair<RemoteResource, Bundle> pair) {
            long j = ((Bundle) pair.right).getLong("YadiSize");
            return Pair.Pair((RemoteResource) pair.left, j < 0 ? Optional.empty() : Optional.of(Long.valueOf(j)));
        }

        /* access modifiers changed from: private */
        public RemoteResource handleCompleteDownload(RemoteResource remoteResource) throws InstallException {
            Verifier.verifyContentDigest(new File(InstallService.this._cachePath, YadiPath.forDownload(remoteResource)), remoteResource);
            InstallService.this._db.deployResource(Db.ResourceTable.local, remoteResource, null, false);
            return remoteResource;
        }

        private void logAndReportException(Exception exc, Intent intent) {
            if (exc instanceof InstallException) {
                InstallException installException = (InstallException) exc;
                if (installException.getCause() instanceof DownloadException) {
                    intent.putExtra("YadiError", String.format("Download error %s for %s", installException.getCause().getMessage(), installException.fault.filename));
                    return;
                }
                Log.e("YadiInstall", installException.getMessage() + " for " + installException.fault.filename, installException);
                intent.putExtra("YadiError", installException.getMessage() + ":" + installException.fault.filename);
                return;
            }
            Log.e("YadiInstall", "Exception during install", exc);
            intent.putExtra("YadiError", "Internal error");
        }

        public String toString() {
            return Installation.class.getSimpleName() + "(" + this.app.toString() + ")";
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: oculus.internal.yadi.InstallService$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$os$yadi$YadiStatus = new int[YadiStatus.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.oculus.os.yadi.YadiStatus[] r0 = com.oculus.os.yadi.YadiStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                oculus.internal.yadi.InstallService.AnonymousClass1.$SwitchMap$com$oculus$os$yadi$YadiStatus = r0
                int[] r0 = oculus.internal.yadi.InstallService.AnonymousClass1.$SwitchMap$com$oculus$os$yadi$YadiStatus     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.os.yadi.YadiStatus r1 = com.oculus.os.yadi.YadiStatus.Complete     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = oculus.internal.yadi.InstallService.AnonymousClass1.$SwitchMap$com$oculus$os$yadi$YadiStatus     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.os.yadi.YadiStatus r1 = com.oculus.os.yadi.YadiStatus.Terminated     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = oculus.internal.yadi.InstallService.AnonymousClass1.$SwitchMap$com$oculus$os$yadi$YadiStatus     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.os.yadi.YadiStatus r1 = com.oculus.os.yadi.YadiStatus.Downloading     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: oculus.internal.yadi.InstallService.AnonymousClass1.<clinit>():void");
        }
    }
}
