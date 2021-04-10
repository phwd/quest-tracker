package com.squareup.okhttp;

import X.AnonymousClass006;
import com.facebook.acra.util.HttpRequestMultipart;
import com.facebook.tigon.iface.TigonRequest;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.DiskLruCache;
import com.squareup.okhttp.internal.InternalCache;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.CacheRequest;
import com.squareup.okhttp.internal.http.CacheStrategy;
import com.squareup.okhttp.internal.http.HttpMethod;
import com.squareup.okhttp.internal.http.OkHeaders;
import com.squareup.okhttp.internal.http.StatusLine;
import com.squareup.okhttp.internal.io.FileSystem;
import java.io.File;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.RealBufferedSink;
import okio.Sink;
import okio.Source;

public final class Cache {
    public static final int ENTRY_BODY = 1;
    public static final int ENTRY_COUNT = 2;
    public static final int ENTRY_METADATA = 0;
    public static final int VERSION = 201105;
    public final DiskLruCache cache;
    public int hitCount;
    public final InternalCache internalCache;
    public int networkCount;
    public int requestCount;
    public int writeAbortCount;
    public int writeSuccessCount;

    public final class CacheRequestImpl implements CacheRequest {
        public Sink body;
        public Sink cacheOut;
        public boolean done;
        public final DiskLruCache.Editor editor;

        public CacheRequestImpl(final DiskLruCache.Editor editor2) throws IOException {
            this.editor = editor2;
            Sink newSink = editor2.newSink(1);
            this.cacheOut = newSink;
            this.body = new ForwardingSink(newSink, Cache.this) {
                /* class com.squareup.okhttp.Cache.CacheRequestImpl.AnonymousClass1 */

                @Override // okio.ForwardingSink, java.io.Closeable, java.lang.AutoCloseable, okio.Sink
                public void close() throws IOException {
                    synchronized (Cache.this) {
                        CacheRequestImpl cacheRequestImpl = CacheRequestImpl.this;
                        if (!cacheRequestImpl.done) {
                            cacheRequestImpl.done = true;
                            Cache.this.writeSuccessCount++;
                            super.close();
                            editor2.commit();
                        }
                    }
                }
            };
        }

        @Override // com.squareup.okhttp.internal.http.CacheRequest
        public void abort() {
            synchronized (Cache.this) {
                if (!this.done) {
                    this.done = true;
                    Cache.this.writeAbortCount++;
                    Util.closeQuietly(this.cacheOut);
                    try {
                        this.editor.abort();
                    } catch (IOException unused) {
                    }
                }
            }
        }

        @Override // com.squareup.okhttp.internal.http.CacheRequest
        public Sink body() {
            return this.body;
        }
    }

    public static class CacheResponseBody extends ResponseBody {
        public final BufferedSource bodySource;
        public final String contentLength;
        public final String contentType;
        public final DiskLruCache.Snapshot snapshot;

        @Override // com.squareup.okhttp.ResponseBody
        public long contentLength() {
            try {
                String str = this.contentLength;
                if (str != null) {
                    return Long.parseLong(str);
                }
                return -1;
            } catch (NumberFormatException unused) {
                return -1;
            }
        }

        @Override // com.squareup.okhttp.ResponseBody
        public MediaType contentType() {
            String str = this.contentType;
            if (str != null) {
                return MediaType.parse(str);
            }
            return null;
        }

        public CacheResponseBody(final DiskLruCache.Snapshot snapshot2, String str, String str2) {
            this.snapshot = snapshot2;
            this.contentType = str;
            this.contentLength = str2;
            this.bodySource = Okio.buffer(new ForwardingSource(snapshot2.sources[1]) {
                /* class com.squareup.okhttp.Cache.CacheResponseBody.AnonymousClass1 */

                @Override // okio.Source, okio.ForwardingSource, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    snapshot2.close();
                    super.close();
                }
            });
        }

        @Override // com.squareup.okhttp.ResponseBody
        public BufferedSource source() {
            return this.bodySource;
        }
    }

    public static final class Entry {
        public final int code;
        public final Handshake handshake;
        public final String message;
        public final Protocol protocol;
        public final String requestMethod;
        public final Headers responseHeaders;
        public final String url;
        public final Headers varyHeaders;

        public void writeTo(DiskLruCache.Editor editor) throws IOException {
            RealBufferedSink realBufferedSink = new RealBufferedSink(editor.newSink(0));
            realBufferedSink.writeUtf8(this.url);
            realBufferedSink.writeByte(10);
            realBufferedSink.writeUtf8(this.requestMethod);
            realBufferedSink.writeByte(10);
            realBufferedSink.writeDecimalLong((long) (this.varyHeaders.namesAndValues.length >> 1));
            realBufferedSink.writeByte(10);
            int length = this.varyHeaders.namesAndValues.length >> 1;
            for (int i = 0; i < length; i++) {
                realBufferedSink.writeUtf8(this.varyHeaders.name(i));
                realBufferedSink.writeUtf8(": ");
                realBufferedSink.writeUtf8(this.varyHeaders.value(i));
                realBufferedSink.writeByte(10);
            }
            realBufferedSink.writeUtf8(new StatusLine(this.protocol, this.code, this.message).toString());
            realBufferedSink.writeByte(10);
            realBufferedSink.writeDecimalLong((long) (this.responseHeaders.namesAndValues.length >> 1));
            realBufferedSink.writeByte(10);
            int length2 = this.responseHeaders.namesAndValues.length >> 1;
            for (int i2 = 0; i2 < length2; i2++) {
                realBufferedSink.writeUtf8(this.responseHeaders.name(i2));
                realBufferedSink.writeUtf8(": ");
                realBufferedSink.writeUtf8(this.responseHeaders.value(i2));
                realBufferedSink.writeByte(10);
            }
            if (isHttps()) {
                realBufferedSink.writeByte(10);
                realBufferedSink.writeUtf8(this.handshake.cipherSuite);
                realBufferedSink.writeByte(10);
                writeCertList(realBufferedSink, this.handshake.peerCertificates);
                writeCertList(realBufferedSink, this.handshake.localCertificates);
            }
            realBufferedSink.close();
        }

        private boolean isHttps() {
            return this.url.startsWith("https://");
        }

        public boolean matches(Request request, Response response) {
            if (!this.url.equals(request.url.toString()) || !this.requestMethod.equals(request.method) || !OkHeaders.varyMatches(response, this.varyHeaders, request)) {
                return false;
            }
            return true;
        }

        public Response response(Request request, DiskLruCache.Snapshot snapshot) {
            String[] strArr = this.responseHeaders.namesAndValues;
            String str = Headers.get(strArr, HttpRequestMultipart.CONTENT_TYPE);
            String str2 = Headers.get(strArr, "Content-Length");
            Request.Builder builder = new Request.Builder();
            builder.url(this.url);
            builder.method(this.requestMethod, null);
            builder.headers = this.varyHeaders.newBuilder();
            Request build = builder.build();
            Response.Builder builder2 = new Response.Builder();
            builder2.request = build;
            builder2.protocol = this.protocol;
            builder2.code = this.code;
            builder2.message = this.message;
            builder2.headers = this.responseHeaders.newBuilder();
            builder2.body = new CacheResponseBody(snapshot, str, str2);
            builder2.handshake = this.handshake;
            return builder2.build();
        }

        private List<Certificate> readCertificateList(BufferedSource bufferedSource) throws IOException {
            int readInt = Cache.readInt(bufferedSource);
            if (readInt == -1) {
                return Collections.emptyList();
            }
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509");
                ArrayList arrayList = new ArrayList(readInt);
                for (int i = 0; i < readInt; i++) {
                    String readUtf8LineStrict = bufferedSource.readUtf8LineStrict();
                    Buffer buffer = new Buffer();
                    buffer.write(ByteString.decodeBase64(readUtf8LineStrict));
                    arrayList.add(instance.generateCertificate(buffer.inputStream()));
                }
                return arrayList;
            } catch (CertificateException e) {
                throw new IOException(e.getMessage());
            }
        }

        private void writeCertList(BufferedSink bufferedSink, List<Certificate> list) throws IOException {
            try {
                bufferedSink.writeDecimalLong((long) list.size());
                bufferedSink.writeByte(10);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    bufferedSink.writeUtf8(ByteString.of(list.get(i).getEncoded()).base64());
                    bufferedSink.writeByte(10);
                }
            } catch (CertificateEncodingException e) {
                throw new IOException(e.getMessage());
            }
        }

        public Entry(Response response) {
            this.url = response.request.url.toString();
            this.varyHeaders = OkHeaders.varyHeaders(response);
            this.requestMethod = response.request.method;
            this.protocol = response.protocol;
            this.code = response.code;
            this.message = response.message;
            this.responseHeaders = response.headers;
            this.handshake = response.handshake;
        }

        public Entry(Source source) throws IOException {
            Handshake handshake2;
            try {
                BufferedSource buffer = Okio.buffer(source);
                this.url = buffer.readUtf8LineStrict();
                this.requestMethod = buffer.readUtf8LineStrict();
                Headers.Builder builder = new Headers.Builder();
                int readInt = Cache.readInt(buffer);
                for (int i = 0; i < readInt; i++) {
                    builder.addLenient(buffer.readUtf8LineStrict());
                }
                this.varyHeaders = new Headers(builder);
                StatusLine parse = StatusLine.parse(buffer.readUtf8LineStrict());
                this.protocol = parse.protocol;
                this.code = parse.code;
                this.message = parse.message;
                Headers.Builder builder2 = new Headers.Builder();
                int readInt2 = Cache.readInt(buffer);
                for (int i2 = 0; i2 < readInt2; i2++) {
                    builder2.addLenient(buffer.readUtf8LineStrict());
                }
                this.responseHeaders = new Headers(builder2);
                if (isHttps()) {
                    String readUtf8LineStrict = buffer.readUtf8LineStrict();
                    if (readUtf8LineStrict.length() <= 0) {
                        handshake2 = Handshake.get(buffer.readUtf8LineStrict(), readCertificateList(buffer), readCertificateList(buffer));
                    } else {
                        throw new IOException(AnonymousClass006.A09("expected \"\" but was \"", readUtf8LineStrict, "\""));
                    }
                } else {
                    handshake2 = null;
                }
                this.handshake = handshake2;
            } finally {
                source.close();
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void trackConditionalCacheHit() {
        this.hitCount++;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void trackResponse(CacheStrategy cacheStrategy) {
        this.requestCount++;
        if (cacheStrategy.networkRequest != null) {
            this.networkCount++;
        } else if (cacheStrategy.cacheResponse != null) {
            this.hitCount++;
        }
    }

    public synchronized int getHitCount() {
        return this.hitCount;
    }

    public synchronized int getNetworkCount() {
        return this.networkCount;
    }

    public synchronized int getRequestCount() {
        return this.requestCount;
    }

    public synchronized int getWriteAbortCount() {
        return this.writeAbortCount;
    }

    public synchronized int getWriteSuccessCount() {
        return this.writeSuccessCount;
    }

    private void abortQuietly(DiskLruCache.Editor editor) {
        if (editor != null) {
            try {
                editor.abort();
            } catch (IOException unused) {
            }
        }
    }

    public static /* synthetic */ int access$808(Cache cache2) {
        int i = cache2.writeSuccessCount;
        cache2.writeSuccessCount = i + 1;
        return i;
    }

    public static /* synthetic */ int access$908(Cache cache2) {
        int i = cache2.writeAbortCount;
        cache2.writeAbortCount = i + 1;
        return i;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private CacheRequest put(Response response) throws IOException {
        DiskLruCache.Editor editor;
        Request request = response.request;
        String str = request.method;
        if (HttpMethod.invalidatesCache(str)) {
            try {
                remove(request);
            } catch (IOException unused) {
            }
            return null;
        }
        if (str.equals(TigonRequest.GET) && !OkHeaders.hasVaryAll(response.headers)) {
            Entry entry = new Entry(response);
            try {
                editor = this.cache.edit(urlToKey(response.request));
                if (editor != null) {
                    try {
                        entry.writeTo(editor);
                        return new CacheRequestImpl(editor);
                    } catch (IOException unused2) {
                        abortQuietly(editor);
                        return null;
                    }
                }
            } catch (IOException unused3) {
                editor = null;
                abortQuietly(editor);
                return null;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void remove(Request request) throws IOException {
        this.cache.remove(urlToKey(request));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void update(Response response, Response response2) {
        DiskLruCache.Editor editor;
        Entry entry = new Entry(response2);
        try {
            editor = ((CacheResponseBody) response.body).snapshot.edit();
            if (editor != null) {
                try {
                    entry.writeTo(editor);
                    editor.commit();
                } catch (IOException unused) {
                    abortQuietly(editor);
                }
            }
        } catch (IOException unused2) {
            editor = null;
            abortQuietly(editor);
        }
    }

    public static String urlToKey(Request request) {
        return Util.md5Hex(request.url.toString());
    }

    public void close() throws IOException {
        this.cache.close();
    }

    public void delete() throws IOException {
        this.cache.delete();
    }

    public void evictAll() throws IOException {
        this.cache.evictAll();
    }

    public void flush() throws IOException {
        this.cache.flush();
    }

    public File getDirectory() {
        return this.cache.directory;
    }

    public long getMaxSize() {
        return this.cache.getMaxSize();
    }

    public long getSize() throws IOException {
        return this.cache.size();
    }

    public void initialize() throws IOException {
        this.cache.initialize();
    }

    public boolean isClosed() {
        return this.cache.isClosed();
    }

    public Iterator<String> urls() throws IOException {
        return new Iterator<String>() {
            /* class com.squareup.okhttp.Cache.AnonymousClass2 */
            public boolean canRemove;
            public final Iterator<DiskLruCache.Snapshot> delegate;
            public String nextUrl;

            {
                this.delegate = Cache.this.cache.snapshots();
            }

            public boolean hasNext() {
                if (this.nextUrl != null) {
                    return true;
                }
                this.canRemove = false;
                while (this.delegate.hasNext()) {
                    DiskLruCache.Snapshot next = this.delegate.next();
                    try {
                        this.nextUrl = Okio.buffer(next.sources[0]).readUtf8LineStrict();
                        return true;
                    } catch (IOException unused) {
                    } finally {
                        next.close();
                    }
                }
                return false;
            }

            public void remove() {
                if (this.canRemove) {
                    this.delegate.remove();
                    return;
                }
                throw new IllegalStateException("remove() before next()");
            }

            @Override // java.util.Iterator
            public String next() {
                if (hasNext()) {
                    String str = this.nextUrl;
                    this.nextUrl = null;
                    this.canRemove = true;
                    return str;
                }
                throw new NoSuchElementException();
            }
        };
    }

    public static int readInt(BufferedSource bufferedSource) throws IOException {
        try {
            long readDecimalLong = bufferedSource.readDecimalLong();
            String readUtf8LineStrict = bufferedSource.readUtf8LineStrict();
            if (readDecimalLong >= 0 && readDecimalLong <= 2147483647L && readUtf8LineStrict.isEmpty()) {
                return (int) readDecimalLong;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("expected an int but was \"");
            sb.append(readDecimalLong);
            sb.append(readUtf8LineStrict);
            sb.append("\"");
            throw new IOException(sb.toString());
        } catch (NumberFormatException e) {
            throw new IOException(e.getMessage());
        }
    }

    public Response get(Request request) {
        try {
            DiskLruCache.Snapshot snapshot = this.cache.get(urlToKey(request));
            if (snapshot != null) {
                try {
                    Entry entry = new Entry(snapshot.sources[0]);
                    Response response = entry.response(request, snapshot);
                    if (entry.matches(request, response)) {
                        return response;
                    }
                    Util.closeQuietly(response.body);
                } catch (IOException unused) {
                    Util.closeQuietly(snapshot);
                    return null;
                }
            }
            return null;
        } catch (IOException unused2) {
            return null;
        }
    }

    public Cache(File file, long j) {
        this(file, j, FileSystem.SYSTEM);
    }

    public Cache(File file, long j, FileSystem fileSystem) {
        this.internalCache = new InternalCache() {
            /* class com.squareup.okhttp.Cache.AnonymousClass1 */

            @Override // com.squareup.okhttp.internal.InternalCache
            public Response get(Request request) throws IOException {
                return Cache.this.get(request);
            }

            @Override // com.squareup.okhttp.internal.InternalCache
            public CacheRequest put(Response response) throws IOException {
                return Cache.this.put(response);
            }

            @Override // com.squareup.okhttp.internal.InternalCache
            public void remove(Request request) throws IOException {
                Cache.this.remove(request);
            }

            @Override // com.squareup.okhttp.internal.InternalCache
            public void trackConditionalCacheHit() {
                Cache.this.trackConditionalCacheHit();
            }

            @Override // com.squareup.okhttp.internal.InternalCache
            public void trackResponse(CacheStrategy cacheStrategy) {
                Cache.this.trackResponse(cacheStrategy);
            }

            @Override // com.squareup.okhttp.internal.InternalCache
            public void update(Response response, Response response2) throws IOException {
                Cache.this.update(response, response2);
            }
        };
        this.cache = DiskLruCache.create(fileSystem, file, 201105, 2, j);
    }
}
