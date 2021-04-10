package com.oculus.appsafety;

import android.util.Base64;
import com.android.okhttp.Callback;
import com.android.okhttp.HttpUrl;
import com.android.okhttp.MediaType;
import com.android.okhttp.OkHttpClient;
import com.android.okhttp.Request;
import com.android.okhttp.RequestBody;
import com.android.okhttp.Response;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import com.oculus.appsafety.PackagePartUploader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import oculus.internal.functional.CancellableProcess;
import oculus.internal.gson.HexString;

public class PackagePartUploader {
    private static final String BASE_URL = "https://rupload.facebook.com/oculus_package_parts";
    private final String authToken;
    private final HttpUrl baseUrl;
    private final OkHttpClient client;
    private final Gson gson;
    private final MessageDigest sha256;
    private final String telemetryDeviceIdentifier;

    private static class InterruptiblePartStream extends InputStream {
        private final Future cancellable;
        private final InputStream is;

        InterruptiblePartStream(InputStream is2, Future cancellable2) {
            this.is = is2;
            this.cancellable = cancellable2;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.cancellable.isCancelled()) {
                Thread.currentThread().interrupt();
            }
            return this.is.read();
        }

        @Override // java.io.InputStream
        public int read(byte[] b, int off, int len) throws IOException {
            if (this.cancellable.isCancelled()) {
                Thread.currentThread().interrupt();
            }
            return this.is.read(b, off, len);
        }

        @Override // java.io.InputStream
        public int read(byte[] b) throws IOException {
            return read(b, 0, b.length);
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return this.is.available();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
        public void close() throws IOException {
            this.is.close();
        }

        @Override // java.io.InputStream
        public void mark(int readlimit) {
            this.is.mark(readlimit);
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return this.is.markSupported();
        }

        @Override // java.io.InputStream
        public void reset() throws IOException {
            this.is.reset();
        }

        @Override // java.io.InputStream
        public long skip(long n) throws IOException {
            return this.is.skip(n);
        }
    }

    /* access modifiers changed from: private */
    public static class UploadServiceSessionResponse {
        boolean duplicate;
        long offset;

        private UploadServiceSessionResponse() {
        }
    }

    public static class UploadServiceResponse {
        @SerializedName("server_id")
        final String serverId;

        UploadServiceResponse(String serverId2) {
            this.serverId = serverId2;
        }
    }

    PackagePartUploader(OkHttpClient client2, String authToken2, MessageDigest sha2562, Gson responseParser, String telemetryDeviceIdentifier2) {
        this(client2, authToken2, sha2562, responseParser, telemetryDeviceIdentifier2, HttpUrl.parse(BASE_URL));
    }

    PackagePartUploader(OkHttpClient client2, String authToken2, MessageDigest sha2562, Gson responseParser, String telemetryDeviceIdentifier2, HttpUrl baseUrl2) {
        this.client = client2;
        this.authToken = authToken2;
        this.sha256 = sha2562;
        this.gson = responseParser;
        this.telemetryDeviceIdentifier = telemetryDeviceIdentifier2;
        this.baseUrl = baseUrl2;
    }

    public CompletableFuture<UploadServiceResponse> upload(PackagePart part) {
        CancellableProcess<UploadServiceResponse> result = new CancellableProcess<>();
        CompletableFuture<byte[]> digestFuture = CompletableFuture.supplyAsync(new Supplier(part, result) {
            /* class com.oculus.appsafety.$$Lambda$PackagePartUploader$RYEpe22VZUUOqunb7NSMJP0Ey6w */
            private final /* synthetic */ PackagePart f$1;
            private final /* synthetic */ CancellableProcess f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // java.util.function.Supplier
            public final Object get() {
                return PackagePartUploader.this.lambda$upload$0$PackagePartUploader(this.f$1, this.f$2);
            }
        });
        result.setUpstream(digestFuture);
        digestFuture.thenCombine((CompletionStage) digestFuture.thenCompose((Function<? super byte[], ? extends CompletionStage<U>>) new Function(part) {
            /* class com.oculus.appsafety.$$Lambda$PackagePartUploader$eZyAHKf_DrYhSSpCfKjTpyk0II */
            private final /* synthetic */ PackagePart f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PackagePartUploader.this.lambda$upload$1$PackagePartUploader(this.f$1, (byte[]) obj);
            }
        }), (BiFunction<? super byte[], ? super U, ? extends V>) new BiFunction(part, result) {
            /* class com.oculus.appsafety.$$Lambda$PackagePartUploader$wTbrSnNcuDYgvzl2Ukmf4mgW29w */
            private final /* synthetic */ PackagePart f$1;
            private final /* synthetic */ CancellableProcess f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return PackagePartUploader.this.lambda$upload$2$PackagePartUploader(this.f$1, this.f$2, (byte[]) obj, (PackagePartUploader.UploadServiceSessionResponse) obj2);
            }
        }).thenCompose((Function) $$Lambda$PackagePartUploader$UpNz9l0iS6xAwe3vyKU6BpriDI.INSTANCE).thenAccept((Consumer) new Consumer(result) {
            /* class com.oculus.appsafety.$$Lambda$PackagePartUploader$2P2rGtKHoatGR2UeGo3BXh_TYJ4 */
            private final /* synthetic */ CancellableProcess f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.complete((PackagePartUploader.UploadServiceResponse) obj);
            }
        }).exceptionally((Function<Throwable, ? extends Void>) new Function(result) {
            /* class com.oculus.appsafety.$$Lambda$PackagePartUploader$GUonhpf0R36jLqMmqGhQVocokY */
            private final /* synthetic */ CancellableProcess f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.completeExceptionally((Throwable) obj);
            }
        });
        return result;
    }

    public /* synthetic */ byte[] lambda$upload$0$PackagePartUploader(PackagePart part, CancellableProcess result) {
        try {
            return getDigest(part, result);
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }

    public /* synthetic */ CompletionStage lambda$upload$1$PackagePartUploader(PackagePart part, byte[] digest) {
        String digestHeader = "sha256 " + Base64.encodeToString(digest, 2);
        Request.Builder requestBuilder = new Request.Builder().url(this.baseUrl.newBuilder().addPathSegment(HexString.encode(digest)).build()).get().header("Authorization", "OAuth " + this.authToken).header("X-Entity-Name", part.getPackageIdentifier() + "/" + part.getName()).header("X-Entity-Type", part.getMimeType()).header("X-Entity-Digest", digestHeader).header("X-Entity-Length", String.format("%d", Long.valueOf(part.getLength()))).header("X-Oculus-Part-Digest", digestHeader);
        String str = this.telemetryDeviceIdentifier;
        if (str != null) {
            requestBuilder.header("X-Telemetry-Device-Identifier", str);
        }
        Request request = requestBuilder.build();
        final CompletableFuture<UploadServiceSessionResponse> future = new CompletableFuture<>();
        this.client.newCall(request).enqueue(new Callback() {
            /* class com.oculus.appsafety.PackagePartUploader.AnonymousClass1 */

            @Override // com.android.okhttp.Callback
            public void onFailure(Request r, IOException e) {
                future.completeExceptionally(e);
            }

            @Override // com.android.okhttp.Callback
            public void onResponse(Response response) throws IOException {
                InputStreamReader reader = new InputStreamReader(response.body().byteStream(), StandardCharsets.UTF_8);
                if (!response.isSuccessful()) {
                    CompletableFuture completableFuture = future;
                    completableFuture.completeExceptionally(new RuntimeException("Failed to upload part: " + PackagePartUploader.this.parseErrorMessage(reader)));
                    return;
                }
                future.complete((UploadServiceSessionResponse) PackagePartUploader.this.gson.fromJson((Reader) reader, UploadServiceSessionResponse.class));
            }
        });
        return future;
    }

    public /* synthetic */ CompletableFuture lambda$upload$2$PackagePartUploader(final PackagePart part, final CancellableProcess result, byte[] digest, final UploadServiceSessionResponse session) {
        String digestHeader = "sha256 " + Base64.encodeToString(digest, 2);
        Request.Builder builder = new Request.Builder().url(this.baseUrl.newBuilder().addPathSegment(HexString.encode(digest)).build()).header("Authorization", "OAuth " + this.authToken).header("X-Entity-Name", part.getPackageIdentifier() + "/" + part.getName()).header("X-Entity-Type", part.getMimeType()).header("X-Entity-Length", String.format("%d", Long.valueOf(part.getLength()))).header("X-Oculus-Part-Digest", digestHeader).header("X-Telemetry-Device-Identifier", this.telemetryDeviceIdentifier);
        if (session.duplicate) {
            builder.header("Offset", String.format("%d", Long.valueOf(part.getLength()))).header("X-Entity-Digest", digestHeader).post(RequestBody.create(MediaType.parse("application/octet-stream"), new byte[0]));
        } else {
            builder.header("Offset", String.format("%d", Long.valueOf(session.offset))).post(new RequestBody() {
                /* class com.oculus.appsafety.PackagePartUploader.AnonymousClass2 */

                @Override // com.android.okhttp.RequestBody
                public MediaType contentType() {
                    return MediaType.parse(part.getMimeType());
                }

                @Override // com.android.okhttp.RequestBody
                public long contentLength() {
                    return part.getLength() - session.offset;
                }

                /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
                    r2.close();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
                    r5 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
                    r3.addSuppressed(r5);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:14:0x002f, code lost:
                    throw r4;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:8:0x0024, code lost:
                    r4 = move-exception;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:9:0x0025, code lost:
                    if (r2 != null) goto L_0x0027;
                 */
                @Override // com.android.okhttp.RequestBody
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void writeTo(com.android.okhttp.okio.BufferedSink r7) throws java.io.IOException {
                    /*
                        r6 = this;
                        com.oculus.appsafety.PackagePart r0 = r12
                        java.io.InputStream r0 = r0.getStream()
                        com.oculus.appsafety.PackagePartUploader$UploadServiceSessionResponse r1 = r15
                        long r1 = r1.offset
                        r0.skip(r1)
                        com.oculus.appsafety.PackagePartUploader$InterruptiblePartStream r1 = new com.oculus.appsafety.PackagePartUploader$InterruptiblePartStream
                        oculus.internal.functional.CancellableProcess r2 = r13
                        r1.<init>(r0, r2)
                        com.android.okhttp.okio.Source r2 = com.android.okhttp.okio.Okio.source(r1)
                        r7.writeAll(r2)     // Catch:{ all -> 0x0022 }
                        r2.close()     // Catch:{ all -> 0x0022 }
                        r2.close()
                        return
                    L_0x0022:
                        r3 = move-exception
                        throw r3     // Catch:{ all -> 0x0024 }
                    L_0x0024:
                        r4 = move-exception
                        if (r2 == 0) goto L_0x002f
                        r2.close()     // Catch:{ all -> 0x002b }
                        goto L_0x002f
                    L_0x002b:
                        r5 = move-exception
                        r3.addSuppressed(r5)
                    L_0x002f:
                        throw r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.oculus.appsafety.PackagePartUploader.AnonymousClass2.writeTo(com.android.okhttp.okio.BufferedSink):void");
                }
            });
        }
        Request request = builder.build();
        final CompletableFuture<UploadServiceResponse> future = new CompletableFuture<>();
        this.client.newCall(request).enqueue(new Callback() {
            /* class com.oculus.appsafety.PackagePartUploader.AnonymousClass3 */

            @Override // com.android.okhttp.Callback
            public void onFailure(Request r, IOException e) {
                future.completeExceptionally(e);
            }

            @Override // com.android.okhttp.Callback
            public void onResponse(Response response) throws IOException {
                InputStreamReader reader = new InputStreamReader(response.body().byteStream(), StandardCharsets.UTF_8);
                if (!response.isSuccessful()) {
                    CompletableFuture completableFuture = future;
                    completableFuture.completeExceptionally(new RuntimeException("Failed to upload part: " + PackagePartUploader.this.parseErrorMessage(reader)));
                    return;
                }
                future.complete((UploadServiceResponse) PackagePartUploader.this.gson.fromJson((Reader) reader, UploadServiceResponse.class));
            }
        });
        return future;
    }

    static /* synthetic */ CompletionStage lambda$upload$3(CompletableFuture future) {
        return future;
    }

    private byte[] getDigest(PackagePart part, Future cancellable) throws IOException, CloneNotSupportedException {
        byte[] digest = part.getSHA256();
        if (digest != null) {
            return digest;
        }
        MessageDigest md = (MessageDigest) this.sha256.clone();
        byte[] buf = new byte[1024];
        InputStream is = part.getStream();
        do {
            int l = is.read(buf);
            if (l < 0) {
                break;
            }
            md.update(buf, 0, l);
        } while (!cancellable.isCancelled());
        if (cancellable.isCancelled()) {
            return null;
        }
        return md.digest();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String parseErrorMessage(Reader reader) {
        try {
            return new JsonParser().parse(reader).getAsJsonObject().getAsJsonObject("debug_info").getAsJsonPrimitive("message").getAsString();
        } catch (Exception e) {
            return null;
        }
    }
}
