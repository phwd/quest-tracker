package com.oculus.appsafety;

import android.content.Context;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.oculus.appsafety.MemoryBufferPackagePart;
import java.io.File;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.concurrent.ConcurrentLinkedQueue;
import oculus.internal.gson.Base64EncodedData;

public class PackagePartsQueueManager {
    private static final String TAG = PackagePartsQueueManager.class.getSimpleName();
    private final Context context;
    private final Gson gson;
    private final Runnable jobScheduler;
    private final int maxPersistedSize;
    private final PackagePartFactory partFactory;
    ConcurrentLinkedQueue<PackagePart> queue;
    private final File storage;

    @FunctionalInterface
    public interface PackagePartFactory {
        PackagePart create(PackagePartConfig packagePartConfig);
    }

    /* access modifiers changed from: private */
    public static class CountingOutputStream extends FilterOutputStream {
        private long count = 0;

        public CountingOutputStream(OutputStream out) {
            super(out);
        }

        public long getCount() {
            return this.count;
        }

        @Override // java.io.OutputStream, java.io.FilterOutputStream
        public void write(byte[] b, int off, int len) throws IOException {
            this.out.write(b, off, len);
            this.count += (long) len;
        }

        public void write(byte b) throws IOException {
            super.write((int) b);
            this.count++;
        }
    }

    public static GsonBuilder configureGson(GsonBuilder builder) {
        return builder.disableHtmlEscaping().setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(PackagePartConfig.class, "type").registerSubtype(MemoryBufferPackagePart.Config.class, "raw")).registerTypeAdapter(byte[].class, new Base64EncodedData.GsonTypeAdapter());
    }

    public PackagePartsQueueManager(Context context2, File storage2, Gson gson2, PackagePartFactory partFactory2, Runnable jobScheduler2, int maxPersistedSize2) {
        this.context = context2;
        this.storage = storage2;
        this.gson = gson2;
        this.partFactory = partFactory2;
        this.jobScheduler = jobScheduler2;
        this.maxPersistedSize = maxPersistedSize2;
        loadQueue();
    }

    public void add(PackagePart part) {
        synchronized (this) {
            this.queue.add(part);
            saveQueue();
        }
        this.jobScheduler.run();
    }

    public void remove(PackagePart part) {
        this.queue.remove(part);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0072, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0073, code lost:
        if (r4 != null) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0075, code lost:
        $closeResource(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0078, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0092, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0093, code lost:
        $closeResource(r3, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0096, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0099, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x009a, code lost:
        $closeResource(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x009d, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00a0, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00a1, code lost:
        $closeResource(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00a4, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void saveQueue() {
        /*
        // Method dump skipped, instructions count: 186
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.appsafety.PackagePartsQueueManager.saveQueue():void");
    }

    public /* synthetic */ void lambda$saveQueue$1$PackagePartsQueueManager(CountingOutputStream counter, Type packagePartType, OutputStreamWriter writer, PackagePartConfig config) {
        if (counter.getCount() < ((long) this.maxPersistedSize)) {
            this.gson.toJson(config, packagePartType, writer);
            try {
                writer.flush();
            } catch (IOException e) {
            }
        }
    }

    private static /* synthetic */ void $closeResource(Throwable x0, AutoCloseable x1) {
        if (x0 != null) {
            try {
                x1.close();
            } catch (Throwable th) {
                x0.addSuppressed(th);
            }
        } else {
            x1.close();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0041, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        $closeResource(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0045, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadQueue() {
        /*
            r8 = this;
            java.io.FileReader r0 = new java.io.FileReader     // Catch:{ Exception -> 0x0046 }
            java.io.File r1 = r8.storage     // Catch:{ Exception -> 0x0046 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0046 }
            r1 = 0
            com.google.gson.JsonStreamParser r2 = new com.google.gson.JsonStreamParser     // Catch:{ all -> 0x003f }
            r2.<init>(r0)     // Catch:{ all -> 0x003f }
            java.util.LinkedList r3 = new java.util.LinkedList     // Catch:{ all -> 0x003f }
            r3.<init>()     // Catch:{ all -> 0x003f }
        L_0x0012:
            boolean r4 = r2.hasNext()     // Catch:{ all -> 0x003f }
            if (r4 == 0) goto L_0x0031
            com.oculus.appsafety.PackagePartsQueueManager$PackagePartFactory r4 = r8.partFactory     // Catch:{ JsonParseException -> 0x0030 }
            com.google.gson.Gson r5 = r8.gson     // Catch:{ JsonParseException -> 0x0030 }
            com.google.gson.JsonElement r6 = r2.next()     // Catch:{ JsonParseException -> 0x0030 }
            java.lang.Class<com.oculus.appsafety.PackagePartConfig> r7 = com.oculus.appsafety.PackagePartConfig.class
            java.lang.Object r5 = r5.fromJson(r6, r7)     // Catch:{ JsonParseException -> 0x0030 }
            com.oculus.appsafety.PackagePartConfig r5 = (com.oculus.appsafety.PackagePartConfig) r5     // Catch:{ JsonParseException -> 0x0030 }
            com.oculus.appsafety.PackagePart r4 = r4.create(r5)     // Catch:{ JsonParseException -> 0x0030 }
            r3.add(r4)     // Catch:{ JsonParseException -> 0x0030 }
            goto L_0x0012
        L_0x0030:
            r4 = move-exception
        L_0x0031:
            java.util.Collections.reverse(r3)
            java.util.concurrent.ConcurrentLinkedQueue r4 = new java.util.concurrent.ConcurrentLinkedQueue
            r4.<init>(r3)
            r8.queue = r4
            $closeResource(r1, r0)
            goto L_0x0059
        L_0x003f:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0041 }
        L_0x0041:
            r2 = move-exception
            $closeResource(r1, r0)
            throw r2
        L_0x0046:
            r0 = move-exception
            boolean r1 = r0 instanceof java.io.FileNotFoundException
            if (r1 != 0) goto L_0x0052
            java.lang.String r1 = com.oculus.appsafety.PackagePartsQueueManager.TAG
            java.lang.String r2 = "Failed to load queue"
            android.util.Log.e(r1, r2, r0)
        L_0x0052:
            java.util.concurrent.ConcurrentLinkedQueue r1 = new java.util.concurrent.ConcurrentLinkedQueue
            r1.<init>()
            r8.queue = r1
        L_0x0059:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.appsafety.PackagePartsQueueManager.loadQueue():void");
    }
}
