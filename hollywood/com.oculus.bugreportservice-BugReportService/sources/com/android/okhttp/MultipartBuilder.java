package com.android.okhttp;

import com.android.okhttp.internal.Util;
import com.android.okhttp.okio.Buffer;
import com.android.okhttp.okio.BufferedSink;
import com.android.okhttp.okio.ByteString;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class MultipartBuilder {
    public static final MediaType ALTERNATIVE = MediaType.parse("multipart/alternative");
    private static final byte[] COLONSPACE = {58, 32};
    private static final byte[] CRLF = {13, 10};
    private static final byte[] DASHDASH = {45, 45};
    public static final MediaType DIGEST = MediaType.parse("multipart/digest");
    public static final MediaType FORM = MediaType.parse("multipart/form-data");
    public static final MediaType MIXED = MediaType.parse("multipart/mixed");
    public static final MediaType PARALLEL = MediaType.parse("multipart/parallel");
    private final ByteString boundary;
    private final List partBodies;
    private final List partHeaders;
    private MediaType type;

    public MultipartBuilder() {
        this(UUID.randomUUID().toString());
    }

    public MultipartBuilder(String str) {
        this.type = MIXED;
        this.partHeaders = new ArrayList();
        this.partBodies = new ArrayList();
        this.boundary = ByteString.encodeUtf8(str);
    }

    public MultipartBuilder type(MediaType mediaType) {
        if (mediaType == null) {
            throw new NullPointerException("type == null");
        } else if (mediaType.type().equals("multipart")) {
            this.type = mediaType;
            return this;
        } else {
            throw new IllegalArgumentException("multipart != " + mediaType);
        }
    }

    public MultipartBuilder addPart(Headers headers, RequestBody requestBody) {
        if (requestBody == null) {
            throw new NullPointerException("body == null");
        } else if (headers != null && headers.get("Content-Type") != null) {
            throw new IllegalArgumentException("Unexpected header: Content-Type");
        } else if (headers == null || headers.get("Content-Length") == null) {
            this.partHeaders.add(headers);
            this.partBodies.add(requestBody);
            return this;
        } else {
            throw new IllegalArgumentException("Unexpected header: Content-Length");
        }
    }

    private static StringBuilder appendQuotedString(StringBuilder sb, String str) {
        sb.append('\"');
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '\n') {
                sb.append("%0A");
            } else if (charAt == '\r') {
                sb.append("%0D");
            } else if (charAt != '\"') {
                sb.append(charAt);
            } else {
                sb.append("%22");
            }
        }
        sb.append('\"');
        return sb;
    }

    public MultipartBuilder addFormDataPart(String str, String str2) {
        addFormDataPart(str, null, RequestBody.create((MediaType) null, str2));
        return this;
    }

    public MultipartBuilder addFormDataPart(String str, String str2, RequestBody requestBody) {
        if (str != null) {
            StringBuilder sb = new StringBuilder("form-data; name=");
            appendQuotedString(sb, str);
            if (str2 != null) {
                sb.append("; filename=");
                appendQuotedString(sb, str2);
            }
            addPart(Headers.of("Content-Disposition", sb.toString()), requestBody);
            return this;
        }
        throw new NullPointerException("name == null");
    }

    public RequestBody build() {
        if (!this.partHeaders.isEmpty()) {
            return new MultipartRequestBody(this.type, this.boundary, this.partHeaders, this.partBodies);
        }
        throw new IllegalStateException("Multipart body must have at least one part.");
    }

    private static final class MultipartRequestBody extends RequestBody {
        private final ByteString boundary;
        private long contentLength = -1;
        private final MediaType contentType;
        private final List partBodies;
        private final List partHeaders;

        public MultipartRequestBody(MediaType mediaType, ByteString byteString, List list, List list2) {
            if (mediaType != null) {
                this.boundary = byteString;
                this.contentType = MediaType.parse(mediaType + "; boundary=" + byteString.utf8());
                this.partHeaders = Util.immutableList(list);
                this.partBodies = Util.immutableList(list2);
                return;
            }
            throw new NullPointerException("type == null");
        }

        @Override // com.android.okhttp.RequestBody
        public MediaType contentType() {
            return this.contentType;
        }

        @Override // com.android.okhttp.RequestBody
        public long contentLength() {
            long j = this.contentLength;
            if (j != -1) {
                return j;
            }
            long writeOrCountBytes = writeOrCountBytes(null, true);
            this.contentLength = writeOrCountBytes;
            return writeOrCountBytes;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.android.okhttp.okio.Buffer */
        /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: com.android.okhttp.okio.Buffer */
        /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: com.android.okhttp.okio.Buffer */
        /* JADX WARN: Multi-variable type inference failed */
        private long writeOrCountBytes(BufferedSink bufferedSink, boolean z) {
            Buffer buffer;
            if (z) {
                bufferedSink = new Buffer();
                buffer = bufferedSink;
            } else {
                buffer = 0;
            }
            int size = this.partHeaders.size();
            long j = 0;
            for (int i = 0; i < size; i++) {
                Headers headers = (Headers) this.partHeaders.get(i);
                RequestBody requestBody = (RequestBody) this.partBodies.get(i);
                bufferedSink.write(MultipartBuilder.DASHDASH);
                bufferedSink.write(this.boundary);
                bufferedSink.write(MultipartBuilder.CRLF);
                if (headers != null) {
                    int size2 = headers.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        bufferedSink.writeUtf8(headers.name(i2)).write(MultipartBuilder.COLONSPACE).writeUtf8(headers.value(i2)).write(MultipartBuilder.CRLF);
                    }
                }
                MediaType contentType2 = requestBody.contentType();
                if (contentType2 != null) {
                    bufferedSink.writeUtf8("Content-Type: ").writeUtf8(contentType2.toString()).write(MultipartBuilder.CRLF);
                }
                long contentLength2 = requestBody.contentLength();
                if (contentLength2 != -1) {
                    bufferedSink.writeUtf8("Content-Length: ").writeDecimalLong(contentLength2).write(MultipartBuilder.CRLF);
                } else if (z) {
                    buffer.clear();
                    return -1;
                }
                bufferedSink.write(MultipartBuilder.CRLF);
                if (z) {
                    j += contentLength2;
                } else {
                    ((RequestBody) this.partBodies.get(i)).writeTo(bufferedSink);
                }
                bufferedSink.write(MultipartBuilder.CRLF);
            }
            bufferedSink.write(MultipartBuilder.DASHDASH);
            bufferedSink.write(this.boundary);
            bufferedSink.write(MultipartBuilder.DASHDASH);
            bufferedSink.write(MultipartBuilder.CRLF);
            if (!z) {
                return j;
            }
            long size3 = j + buffer.size();
            buffer.clear();
            return size3;
        }

        @Override // com.android.okhttp.RequestBody
        public void writeTo(BufferedSink bufferedSink) {
            writeOrCountBytes(bufferedSink, false);
        }
    }
}
