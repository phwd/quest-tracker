package oculus.internal.yadi;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import com.oculus.os.yadi.RemoteApp;
import com.oculus.os.yadi.RemoteResource;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import oculus.internal.functional.Pair;
import oculus.internal.functional.Try;
import oculus.internal.yadi.Awaitable;
import oculus.internal.yadi.Db;

/* access modifiers changed from: package-private */
public final class InstallServiceHelpers {
    static Optional<PackageInfo> getPackageInfo(PackageManager packageManager, String str) {
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            return packageInfo == null ? Optional.empty() : Optional.of(packageInfo);
        } catch (PackageManager.NameNotFoundException unused) {
            return Optional.empty();
        }
    }

    static Pair<Optional<Long>, List<RemoteResource>> foldDownloadSize(List<Pair<RemoteResource, Optional<Long>>> list) {
        return Pair.Pair(list.stream().map($$Lambda$pr2Sv6XH6ZB1m4wQ9EYQRkwmY.INSTANCE).reduce((R) Optional.of(0L), $$Lambda$InstallServiceHelpers$hJ1u0Ck38qA4Qktcf_LfZ1Qtw94.INSTANCE), (List) list.stream().map($$Lambda$VEK2dqyOOzCSBhroT1qKKyUBNx8.INSTANCE).collect(Collectors.toList()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0045, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        $closeResource(r6, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0049, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static oculus.internal.functional.Pair<com.oculus.os.yadi.RemoteResource, java.util.Optional<java.lang.Long>> fallocateSpace(java.io.File r5, oculus.internal.yadi.Db r6, oculus.internal.functional.Pair<com.oculus.os.yadi.RemoteResource, java.util.Optional<java.lang.Long>> r7) throws oculus.internal.yadi.InstallException {
        /*
        // Method dump skipped, instructions count: 119
        */
        throw new UnsupportedOperationException("Method not decompiled: oculus.internal.yadi.InstallServiceHelpers.fallocateSpace(java.io.File, oculus.internal.yadi.Db, oculus.internal.functional.Pair):oculus.internal.functional.Pair");
    }

    private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
        if (th != null) {
            try {
                autoCloseable.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            autoCloseable.close();
        }
    }

    static Pair<RemoteResource, Bundle> waitFor(Db db, Awaitable.Yadi yadi, String str) throws InstallException {
        try {
            T t = yadi.future.get();
            if (t != null) {
                Pair<RemoteResource, Bundle> Pair = Pair.Pair(yadi.name, t);
                db.removeResource(Db.ResourceTable.temporary, yadi.name);
                return Pair;
            }
            throw new InstallException("result null", yadi.name);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof InstallException) {
                throw ((InstallException) cause);
            } else if (cause instanceof DownloadException) {
                throw new InstallException(str, yadi.name, cause);
            } else {
                throw new InstallException(str, yadi.name, e);
            }
        } catch (InterruptedException | CancellationException e2) {
            throw new InstallException(str, yadi.name, e2);
        } catch (Throwable th) {
            db.removeResource(Db.ResourceTable.temporary, yadi.name);
            throw th;
        }
    }

    static void cleanupOldResources(Db db, File file, ApplicationInfo applicationInfo, RemoteApp remoteApp, RemoteResource remoteResource, Collection<RemoteResource> collection) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        hashSet.add(Pair.Pair(remoteResource.resourceId, Utils.hexString(remoteResource.digest)));
        for (RemoteResource remoteResource2 : collection) {
            hashSet.add(Pair.Pair(remoteResource2.resourceId, Utils.hexString(remoteResource2.digest)));
            hashSet2.add(remoteResource2.filename);
        }
        db.knownResourcesForApp(Db.ResourceTable.local, remoteApp).stream().filter(new Predicate(hashSet) {
            /* class oculus.internal.yadi.$$Lambda$InstallServiceHelpers$NtnLRTsy48PoXyLvcXsnxpZjxU */
            private final /* synthetic */ Set f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InstallServiceHelpers.lambda$cleanupOldResources$3(this.f$0, (Pair) obj);
            }
        }).map(new Function() {
            /* class oculus.internal.yadi.$$Lambda$InstallServiceHelpers$mJRDqdTOxVdecoXFBa_dfbN3UCY */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Pair pair;
                return Pair.Pair(pair, Db.this.getPreviousFilename((String) pair.left, (String) ((Pair) obj).right));
            }
        }).forEach(new Consumer(hashSet2, applicationInfo, db) {
            /* class oculus.internal.yadi.$$Lambda$InstallServiceHelpers$cKMqv5s44LtEj_PMJVihA3BicJo */
            private final /* synthetic */ Set f$0;
            private final /* synthetic */ ApplicationInfo f$1;
            private final /* synthetic */ Db f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                InstallServiceHelpers.lambda$cleanupOldResources$6(this.f$0, this.f$1, this.f$2, (Pair) obj);
            }
        });
        db.knownResourcesForApp(Db.ResourceTable.temporary, remoteApp).stream().forEach(new Consumer(file, db) {
            /* class oculus.internal.yadi.$$Lambda$InstallServiceHelpers$6WcVLuuge_mjm7LvRAKfixC_3c */
            private final /* synthetic */ File f$0;
            private final /* synthetic */ Db f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                InstallServiceHelpers.lambda$cleanupOldResources$7(this.f$0, this.f$1, (Pair) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$cleanupOldResources$3(Set set, Pair pair) {
        return !set.contains(pair);
    }

    static /* synthetic */ void lambda$cleanupOldResources$6(Set set, ApplicationInfo applicationInfo, Db db, Pair pair) {
        ((Optional) pair.right).ifPresent(new Consumer(set, applicationInfo) {
            /* class oculus.internal.yadi.$$Lambda$InstallServiceHelpers$5bnYbVJ5tVNB9svOsx330icecnc */
            private final /* synthetic */ Set f$0;
            private final /* synthetic */ ApplicationInfo f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                InstallServiceHelpers.lambda$cleanupOldResources$5(this.f$0, this.f$1, (Uri) obj);
            }
        });
        Object obj = pair.left;
        db.removeResource(Db.ResourceTable.local, (String) ((Pair) obj).left, (String) ((Pair) obj).right);
    }

    static /* synthetic */ void lambda$cleanupOldResources$5(Set set, ApplicationInfo applicationInfo, Uri uri) {
        if (!set.contains(uri)) {
            YadiPath.forAsset(UserHandle.OWNER, applicationInfo, uri).delete();
        }
    }

    static /* synthetic */ void lambda$cleanupOldResources$7(File file, Db db, Pair pair) {
        String str = (String) pair.left;
        String str2 = (String) pair.right;
        new File(file, YadiPath.forDownload(str, str2)).delete();
        db.removeResource(Db.ResourceTable.temporary, str, str2);
    }

    static void purgeInactiveResources(Db db, File file, RemoteApp remoteApp) {
        db.knownResourcesForApp(Db.ResourceTable.local, remoteApp).stream().filter(new Predicate() {
            /* class oculus.internal.yadi.$$Lambda$InstallServiceHelpers$KvY83TJ2gufs87C3L9zFQrTH_Q */

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InstallServiceHelpers.lambda$purgeInactiveResources$8(Db.this, (Pair) obj);
            }
        }).forEach(new Consumer(file, db) {
            /* class oculus.internal.yadi.$$Lambda$InstallServiceHelpers$l2PPyvaZsizPuFz0UleopbulVVc */
            private final /* synthetic */ File f$0;
            private final /* synthetic */ Db f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                InstallServiceHelpers.lambda$purgeInactiveResources$9(this.f$0, this.f$1, (Pair) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$purgeInactiveResources$8(Db db, Pair pair) {
        return !db.isResourceActive((String) pair.left, (String) pair.right);
    }

    static /* synthetic */ void lambda$purgeInactiveResources$9(File file, Db db, Pair pair) {
        new File(file, YadiPath.forDownload((String) pair.left, (String) pair.right)).delete();
        db.removeResource(Db.ResourceTable.local, (String) pair.left, (String) pair.right);
    }

    static void moveAssetFile(Db db, int i, File file, File file2) throws IOException {
        if (file.exists()) {
            file2.delete();
        }
        int i2 = YadiPath.isExternalStorage(file2) ? 1015 : i;
        Os.mkdir_dashp(file2.getParentFile(), 509, i, i2);
        if (file.exists()) {
            Os.rename(file, file2);
        }
        Os.chmod(file2, 432, i, i2);
    }

    /* access modifiers changed from: package-private */
    public static Optional<Pair<RemoteResource, Pair<File, File>>> getRename(Db db, File file, ApplicationInfo applicationInfo, RemoteResource remoteResource) {
        if (!db.isResourceActive(remoteResource)) {
            return Optional.of(Pair.Pair(remoteResource, Pair.Pair(new File(file, YadiPath.forDownload(remoteResource)), YadiPath.forAsset(UserHandle.OWNER, applicationInfo, remoteResource.filename))));
        }
        Uri uri = db.getPreviousFilename(remoteResource).get();
        if (uri.equals(remoteResource.filename)) {
            return Optional.empty();
        }
        return Optional.of(Pair.Pair(remoteResource, Pair.Pair(YadiPath.forAsset(UserHandle.OWNER, applicationInfo, uri), YadiPath.forAsset(UserHandle.OWNER, applicationInfo, remoteResource.filename))));
    }

    static ApplicationInfo installAssets(Db db, File file, ApplicationInfo applicationInfo, Collection<RemoteResource> collection) throws InstallException {
        for (Pair pair : (List) collection.stream().map(new Function(file, applicationInfo) {
            /* class oculus.internal.yadi.$$Lambda$InstallServiceHelpers$x36LdyTGU3ZL1gkM_jviSbVh6k */
            private final /* synthetic */ File f$1;
            private final /* synthetic */ ApplicationInfo f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return InstallServiceHelpers.getRename(Db.this, this.f$1, this.f$2, (RemoteResource) obj);
            }
        }).filter($$Lambda$bjSXRjZ5UYwAzkWXPKwqbJ9BRQ.INSTANCE).map($$Lambda$1EfiUksDazL1BUL23rm4EIUBJI.INSTANCE).collect(Collectors.toList())) {
            try {
                moveAssetFile(db, applicationInfo.uid, (File) ((Pair) pair.right).left, (File) ((Pair) pair.right).right);
                db.deployResource(Db.ResourceTable.local, (RemoteResource) pair.left, null, true);
            } catch (Exception e) {
                throw new InstallException("Error moving asset", (RemoteResource) pair.left, e);
            }
        }
        return applicationInfo;
    }

    /* access modifiers changed from: package-private */
    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0056, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0057, code lost:
        $closeResource(r10, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005a, code lost:
        throw r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.pm.PackageInfo installApp(oculus.internal.functional.Pair<android.content.pm.PackageInfo, android.content.pm.PackageInstaller.Session> r10, android.content.IntentSender r11, java.io.File r12) throws java.io.IOException {
        /*
            boolean r0 = r12.exists()
            if (r0 != 0) goto L_0x000b
            java.lang.Object r10 = r10.left
            android.content.pm.PackageInfo r10 = (android.content.pm.PackageInfo) r10
            return r10
        L_0x000b:
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r12)
            r1 = 0
            r2 = 1048576(0x100000, float:1.469368E-39)
            byte[] r2 = new byte[r2]     // Catch:{ all -> 0x0054 }
            java.lang.Object r3 = r10.right     // Catch:{ all -> 0x0054 }
            r4 = r3
            android.content.pm.PackageInstaller$Session r4 = (android.content.pm.PackageInstaller.Session) r4     // Catch:{ all -> 0x0054 }
            java.lang.Object r3 = r10.left     // Catch:{ all -> 0x0054 }
            android.content.pm.PackageInfo r3 = (android.content.pm.PackageInfo) r3     // Catch:{ all -> 0x0054 }
            java.lang.String r5 = r3.packageName     // Catch:{ all -> 0x0054 }
            r6 = 0
            long r8 = r12.length()     // Catch:{ all -> 0x0054 }
            java.io.OutputStream r12 = r4.openWrite(r5, r6, r8)     // Catch:{ all -> 0x0054 }
        L_0x002a:
            int r3 = r0.read(r2)     // Catch:{ all -> 0x004f }
            r4 = -1
            if (r3 == r4) goto L_0x0036
            r4 = 0
            r12.write(r2, r4, r3)     // Catch:{ all -> 0x004f }
            goto L_0x002a
        L_0x0036:
            java.lang.Object r2 = r10.right     // Catch:{ all -> 0x004f }
            android.content.pm.PackageInstaller$Session r2 = (android.content.pm.PackageInstaller.Session) r2     // Catch:{ all -> 0x004f }
            r2.fsync(r12)     // Catch:{ all -> 0x004f }
            r12.close()
            $closeResource(r1, r0)
            java.lang.Object r12 = r10.right
            android.content.pm.PackageInstaller$Session r12 = (android.content.pm.PackageInstaller.Session) r12
            r12.commit(r11)
            java.lang.Object r10 = r10.left
            android.content.pm.PackageInfo r10 = (android.content.pm.PackageInfo) r10
            return r10
        L_0x004f:
            r10 = move-exception
            r12.close()
            throw r10
        L_0x0054:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x0056 }
        L_0x0056:
            r11 = move-exception
            $closeResource(r10, r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: oculus.internal.yadi.InstallServiceHelpers.installApp(oculus.internal.functional.Pair, android.content.IntentSender, java.io.File):android.content.pm.PackageInfo");
    }

    /* access modifiers changed from: package-private */
    public static void abandonSession(PackageInstaller packageInstaller, int i) {
        try {
            packageInstaller.abandonSession(i);
        } catch (Exception unused) {
        }
    }

    static void cleanupCache(Db db, File file, RemoteResource remoteResource, Collection<RemoteResource> collection) {
        String[] list = file.list(new FilenameFilter(remoteResource, collection) {
            /* class oculus.internal.yadi.$$Lambda$InstallServiceHelpers$4r25LcctHSNjFLDcEk_Sq4Xs2UM */
            private final /* synthetic */ RemoteResource f$0;
            private final /* synthetic */ Collection f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final boolean accept(File file, String str) {
                return InstallServiceHelpers.lambda$cleanupCache$12(this.f$0, this.f$1, file, str);
            }
        });
        for (String str : list) {
            new File(file, str).delete();
            int lastIndexOf = str.lastIndexOf(45);
            db.removeResource(Db.ResourceTable.temporary, str.substring(0, lastIndexOf), str.substring(lastIndexOf + 1, str.length() - 4));
        }
    }

    static /* synthetic */ boolean lambda$cleanupCache$12(RemoteResource remoteResource, Collection collection, File file, String str) {
        return YadiPath.isInvalidTmpFile(str, remoteResource) || collection.stream().anyMatch(new Predicate(str) {
            /* class oculus.internal.yadi.$$Lambda$InstallServiceHelpers$Vh8YLdeXuuDHBTB952Udk8ayggE */
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return YadiPath.isInvalidTmpFile(this.f$0, (RemoteResource) obj);
            }
        });
    }

    static Try<Pair<PackageInfo, PackageInstaller.Session>> getInstallerSession(PackageManager packageManager, RemoteResource remoteResource, PackageInfo packageInfo, File file) {
        PackageInstaller packageInstaller = packageManager.getPackageInstaller();
        return packageInstaller.getMySessions().stream().filter(new Predicate(packageInfo) {
            /* class oculus.internal.yadi.$$Lambda$InstallServiceHelpers$nU0cuRGxomS1pC51buci9dtHzt8 */
            private final /* synthetic */ PackageInfo f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InstallServiceHelpers.lambda$getInstallerSession$13(this.f$0, (PackageInstaller.SessionInfo) obj);
            }
        }).findFirst().map($$Lambda$InstallServiceHelpers$1J8G70hTxal5fVnDL7eqwoBsVM.INSTANCE).flatMap(new Function(packageInstaller, packageInfo) {
            /* class oculus.internal.yadi.$$Lambda$InstallServiceHelpers$h0NlGyE0OfaijV0uxlrXnr1kHY */
            private final /* synthetic */ PackageInstaller f$0;
            private final /* synthetic */ PackageInfo f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return InstallServiceHelpers.lambda$getInstallerSession$18(this.f$0, this.f$1, (Integer) obj);
            }
        }).map($$Lambda$IWtE2EldN7jyUV7sJVt85Sej7i4.INSTANCE).orElseGet(new Supplier(packageInfo, remoteResource, packageInstaller) {
            /* class oculus.internal.yadi.$$Lambda$InstallServiceHelpers$CVvUXEB7Os6b2jeQ2CTQacWVNg */
            private final /* synthetic */ PackageInfo f$0;
            private final /* synthetic */ RemoteResource f$1;
            private final /* synthetic */ PackageInstaller f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                return Try.Try(new Try.F0(this.f$0, this.f$1, this.f$2) {
                    /* class oculus.internal.yadi.$$Lambda$InstallServiceHelpers$T86AjYyGnGBfsREnXg_tfjaY4xY */
                    private final /* synthetic */ PackageInfo f$0;
                    private final /* synthetic */ RemoteResource f$1;
                    private final /* synthetic */ PackageInstaller f$2;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final Object get() {
                        return InstallServiceHelpers.lambda$getInstallerSession$19(this.f$0, this.f$1, this.f$2);
                    }
                });
            }
        });
    }

    static /* synthetic */ boolean lambda$getInstallerSession$13(PackageInfo packageInfo, PackageInstaller.SessionInfo sessionInfo) {
        return !sessionInfo.sealed && packageInfo.packageName.equals(sessionInfo.getAppPackageName());
    }

    static /* synthetic */ Optional lambda$getInstallerSession$18(PackageInstaller packageInstaller, PackageInfo packageInfo, Integer num) {
        return (Optional) Try.Try(new Try.F0(packageInstaller, num) {
            /* class oculus.internal.yadi.$$Lambda$InstallServiceHelpers$MCZSpU05_kCEz0Jxb0nlMlyIfe0 */
            private final /* synthetic */ PackageInstaller f$0;
            private final /* synthetic */ Integer f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final Object get() {
                return this.f$0.openSession(this.f$1.intValue());
            }
        }).map(new Function(packageInfo) {
            /* class oculus.internal.yadi.$$Lambda$InstallServiceHelpers$oOfeG8Sp308JYr2MCHNNv2dB8M */
            private final /* synthetic */ PackageInfo f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Optional.of(Pair.Pair(this.f$0, (PackageInstaller.Session) obj));
            }
        }).onFailure(new Consumer(packageInstaller, num) {
            /* class oculus.internal.yadi.$$Lambda$InstallServiceHelpers$rWY9JvEAFuLSyMTh651wg4p1ZI */
            private final /* synthetic */ PackageInstaller f$0;
            private final /* synthetic */ Integer f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Exception exc = (Exception) obj;
                InstallServiceHelpers.abandonSession(this.f$0, this.f$1.intValue());
            }
        }).orElse(Optional.empty());
    }

    static /* synthetic */ Pair lambda$getInstallerSession$19(PackageInfo packageInfo, RemoteResource remoteResource, PackageInstaller packageInstaller) throws Exception {
        PackageInstaller.SessionParams sessionParams = new PackageInstaller.SessionParams(1);
        sessionParams.setAppPackageName(packageInfo.packageName);
        sessionParams.setOriginatingUri(remoteResource.downloadUri);
        int createSession = packageInstaller.createSession(sessionParams);
        if (createSession >= 0) {
            PackageInstaller.Session openSession = packageInstaller.openSession(createSession);
            if (openSession != null) {
                return Pair.Pair(packageInfo, openSession);
            }
            abandonSession(packageInstaller, createSession);
            throw new InstallException("Unable to open session", remoteResource);
        }
        throw new InstallException("Unable to create session", remoteResource);
    }

    static PendingIntent getInstallIntent(Context context, String str, RemoteApp remoteApp) {
        Intent intent = new Intent(str);
        intent.addCategory(remoteApp.appId);
        return PendingIntent.getBroadcast(context, remoteApp.hashCode(), intent, 134217728);
    }
}
