package com.squareup.okhttp;

import X.AbstractC04550h0;
import X.AnonymousClass006;
import X.AnonymousClass0HO;
import X.AnonymousClass0HP;
import X.AnonymousClass0HR;
import X.AnonymousClass0OY;
import X.AnonymousClass0Oa;
import X.AnonymousClass0Od;
import X.AnonymousClass0Oe;
import X.AnonymousClass0h1;
import X.C04610h7;
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
        public AnonymousClass0h1 body;
        public AnonymousClass0h1 cacheOut;
        public boolean done;
        public final DiskLruCache.Editor editor;

        public CacheRequestImpl(final DiskLruCache.Editor editor2) throws IOException {
            this.editor = editor2;
            AnonymousClass0h1 newSink = editor2.newSink(1);
            this.cacheOut = newSink;
            this.body = new AnonymousClass0Oa(newSink, Cache.this) {
                /* class com.squareup.okhttp.Cache.CacheRequestImpl.AnonymousClass1 */

                @Override // X.AnonymousClass0Oa, java.io.Closeable, java.lang.AutoCloseable, X.AnonymousClass0h1
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
        public AnonymousClass0h1 body() {
            return this.body;
        }
    }

    public static class CacheResponseBody extends ResponseBody {
        public final AnonymousClass0Od bodySource;
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

        @Override // com.squareup.okhttp.ResponseBody
        public AnonymousClass0Od source() {
            return this.bodySource;
        }

        public CacheResponseBody(final DiskLruCache.Snapshot snapshot2, String str, String str2) {
            this.snapshot = snapshot2;
            this.contentType = str;
            this.contentLength = str2;
            this.bodySource = new AnonymousClass0HO(new AnonymousClass0OY(snapshot2.sources[1]) {
                /* class com.squareup.okhttp.Cache.CacheResponseBody.AnonymousClass1 */

                @Override // java.io.Closeable, X.AnonymousClass0OY, java.lang.AutoCloseable, X.AbstractC04550h0
                public void close() throws IOException {
                    snapshot2.close();
                    super.close();
                }
            });
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
            AnonymousClass0HP r2 = new AnonymousClass0HP(editor.newSink(0));
            r2.A9F(this.url);
            r2.A91(10);
            r2.A9F(this.requestMethod);
            r2.A91(10);
            r2.A92((long) (this.varyHeaders.namesAndValues.length >> 1));
            r2.A91(10);
            int length = this.varyHeaders.namesAndValues.length >> 1;
            for (int i = 0; i < length; i++) {
                r2.A9F(this.varyHeaders.name(i));
                r2.A9F(": ");
                r2.A9F(this.varyHeaders.value(i));
                r2.A91(10);
            }
            r2.A9F(new StatusLine(this.protocol, this.code, this.message).toString());
            r2.A91(10);
            r2.A92((long) (this.responseHeaders.namesAndValues.length >> 1));
            r2.A91(10);
            int length2 = this.responseHeaders.namesAndValues.length >> 1;
            for (int i2 = 0; i2 < length2; i2++) {
                r2.A9F(this.responseHeaders.name(i2));
                r2.A9F(": ");
                r2.A9F(this.responseHeaders.value(i2));
                r2.A91(10);
            }
            if (isHttps()) {
                r2.A91(10);
                r2.A9F(this.handshake.cipherSuite);
                r2.A91(10);
                writeCertList(r2, this.handshake.peerCertificates);
                writeCertList(r2, this.handshake.localCertificates);
            }
            r2.close();
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
            String str = Headers.get(strArr, "Content-Type");
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

        private List<Certificate> readCertificateList(AnonymousClass0Od r7) throws IOException {
            int readInt = Cache.readInt(r7);
            if (readInt == -1) {
                return Collections.emptyList();
            }
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509");
                ArrayList arrayList = new ArrayList(readInt);
                for (int i = 0; i < readInt; i++) {
                    String A7I = r7.A7I();
                    AnonymousClass0HR r1 = new AnonymousClass0HR();
                    C04610h7 A02 = C04610h7.A02(A7I);
                    if (A02 != null) {
                        A02.A0F(r1);
                        arrayList.add(instance.generateCertificate(r1.A5H()));
                    } else {
                        throw new IllegalArgumentException("byteString == null");
                    }
                }
                return arrayList;
            } catch (CertificateException e) {
                throw new IOException(e.getMessage());
            }
        }

        private void writeCertList(AnonymousClass0Oe r5, List<Certificate> list) throws IOException {
            try {
                r5.A92((long) list.size());
                r5.A91(10);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    r5.A9F(C04610h7.A05(list.get(i).getEncoded()).A08());
                    r5.A91(10);
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

        public Entry(AbstractC04550h0 r7) throws IOException {
            Handshake handshake2;
            try {
                AnonymousClass0HO r3 = new AnonymousClass0HO(r7);
                this.url = r3.A7I();
                this.requestMethod = r3.A7I();
                Headers.Builder builder = new Headers.Builder();
                int readInt = Cache.readInt(r3);
                for (int i = 0; i < readInt; i++) {
                    builder.addLenient(r3.A7I());
                }
                this.varyHeaders = new Headers(builder);
                StatusLine parse = StatusLine.parse(r3.A7I());
                this.protocol = parse.protocol;
                this.code = parse.code;
                this.message = parse.message;
                Headers.Builder builder2 = new Headers.Builder();
                int readInt2 = Cache.readInt(r3);
                for (int i2 = 0; i2 < readInt2; i2++) {
                    builder2.addLenient(r3.A7I());
                }
                this.responseHeaders = new Headers(builder2);
                if (isHttps()) {
                    String A7I = r3.A7I();
                    if (A7I.length() <= 0) {
                        handshake2 = Handshake.get(r3.A7I(), readCertificateList(r3), readCertificateList(r3));
                    } else {
                        throw new IOException(AnonymousClass006.A07("expected \"\" but was \"", A7I, "\""));
                    }
                } else {
                    handshake2 = null;
                }
                this.handshake = handshake2;
            } finally {
                r7.close();
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
                        this.nextUrl = new AnonymousClass0HO(next.sources[0]).A7I();
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

    public static int readInt(AnonymousClass0Od r6) throws IOException {
        try {
            long A7C = r6.A7C();
            String A7I = r6.A7I();
            if (A7C >= 0 && A7C <= 2147483647L && A7I.isEmpty()) {
                return (int) A7C;
            }
            throw new IOException("expected an int but was \"" + A7C + A7I + "\"");
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
        this.cache = DiskLruCache.create(fileSystem, file, VERSION, 2, j);
    }
}
