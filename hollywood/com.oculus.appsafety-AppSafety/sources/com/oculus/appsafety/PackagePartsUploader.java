package com.oculus.appsafety;

import com.oculus.appsafety.PackagePartUploader;
import com.oculus.appsafety.PackagePartsUploader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

public class PackagePartsUploader {
    private final PackagePartUploader uploader;

    public static class Callbacks {
        public void onSuccess(PackagePart part, PackagePartUploader.UploadServiceResponse response) {
        }

        public void onFailure(PackagePart part, Throwable cause) {
        }

        public void onComplete(boolean stopped) {
        }
    }

    public class PackagePartsUploadTask {
        private final Callbacks callbacks;
        private volatile CompletableFuture<PackagePartUploader.UploadServiceResponse> currentUpload;
        private final Queue<PackagePart> queue;
        private final AtomicBoolean stopped;

        private PackagePartsUploadTask(Queue<PackagePart> queue2, Callbacks callbacks2) {
            callbacks2 = callbacks2 == null ? new Callbacks() : callbacks2;
            this.queue = new LinkedList(queue2);
            this.stopped = new AtomicBoolean();
            this.callbacks = callbacks2;
        }

        /* access modifiers changed from: package-private */
        public void stop() {
            this.stopped.set(true);
            Future localCurrentUpload = this.currentUpload;
            if (localCurrentUpload != null) {
                localCurrentUpload.cancel(true);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private CompletableFuture<Void> uploadRemainingParts() {
            if (this.queue.isEmpty() || this.stopped.get()) {
                this.callbacks.onComplete(this.stopped.get());
                return CompletableFuture.completedFuture(null);
            }
            PackagePart item = this.queue.poll();
            this.currentUpload = PackagePartsUploader.this.uploader.upload(item);
            return this.currentUpload.thenApply((Function<? super PackagePartUploader.UploadServiceResponse, ? extends U>) new Function(item) {
                /* class com.oculus.appsafety.$$Lambda$PackagePartsUploader$PackagePartsUploadTask$qbcTAuhcpvibFmDHbiUlqlGjY6Q */
                private final /* synthetic */ PackagePart f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return PackagePartsUploader.PackagePartsUploadTask.this.lambda$uploadRemainingParts$0$PackagePartsUploader$PackagePartsUploadTask(this.f$1, (PackagePartUploader.UploadServiceResponse) obj);
                }
            }).exceptionally((Function<Throwable, ? extends U>) new Function(item) {
                /* class com.oculus.appsafety.$$Lambda$PackagePartsUploader$PackagePartsUploadTask$LPFEgrH6o3kM6b12v5vFspfTJd4 */
                private final /* synthetic */ PackagePart f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return PackagePartsUploader.PackagePartsUploadTask.this.lambda$uploadRemainingParts$1$PackagePartsUploader$PackagePartsUploadTask(this.f$1, (Throwable) obj);
                }
            }).thenCompose((Function<? super U, ? extends CompletionStage<U>>) new Function() {
                /* class com.oculus.appsafety.$$Lambda$PackagePartsUploader$PackagePartsUploadTask$MPJAvRDibovS6xEP2PeZbmpshyE */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return PackagePartsUploader.PackagePartsUploadTask.this.lambda$uploadRemainingParts$2$PackagePartsUploader$PackagePartsUploadTask((PackagePartUploader.UploadServiceResponse) obj);
                }
            });
        }

        public /* synthetic */ PackagePartUploader.UploadServiceResponse lambda$uploadRemainingParts$0$PackagePartsUploader$PackagePartsUploadTask(PackagePart item, PackagePartUploader.UploadServiceResponse response) {
            this.callbacks.onSuccess(item, response);
            return response;
        }

        public /* synthetic */ PackagePartUploader.UploadServiceResponse lambda$uploadRemainingParts$1$PackagePartsUploader$PackagePartsUploadTask(PackagePart item, Throwable e) {
            this.callbacks.onFailure(item, e);
            return null;
        }

        public /* synthetic */ CompletionStage lambda$uploadRemainingParts$2$PackagePartsUploader$PackagePartsUploadTask(PackagePartUploader.UploadServiceResponse response) {
            return uploadRemainingParts();
        }
    }

    PackagePartsUploader(PackagePartUploader uploader2) {
        this.uploader = uploader2;
    }

    public PackagePartsUploadTask upload(Queue<PackagePart> parts, Callbacks callbacks) {
        PackagePartsUploadTask task = new PackagePartsUploadTask(parts, callbacks);
        task.uploadRemainingParts();
        return task;
    }
}
