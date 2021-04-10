package com.squareup.okhttp;

import com.facebook.acra.util.HttpRequestMultipart;
import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;

public final class MultipartBuilder {
    public static final MediaType ALTERNATIVE = MediaType.parse("multipart/alternative");
    public static final byte[] COLONSPACE = {58, 32};
    public static final byte[] CRLF = {13, 10};
    public static final byte[] DASHDASH = {45, 45};
    public static final MediaType DIGEST = MediaType.parse("multipart/digest");
    public static final MediaType FORM = MediaType.parse("multipart/form-data");
    public static final MediaType MIXED = MediaType.parse("multipart/mixed");
    public static final MediaType PARALLEL = MediaType.parse("multipart/parallel");
    public final ByteString boundary;
    public final List<RequestBody> partBodies;
    public final List<Headers> partHeaders;
    public MediaType type;

    public static final class MultipartRequestBody extends RequestBody {
        public final ByteString boundary;
        public long contentLength = -1;
        public final MediaType contentType;
        public final List<RequestBody> partBodies;
        public final List<Headers> partHeaders;

        @Override // com.squareup.okhttp.RequestBody
        public void writeTo(BufferedSink bufferedSink) throws IOException {
            writeOrCountBytes(bufferedSink, false);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: okio.Buffer */
        /* JADX DEBUG: Multi-variable search result rejected for r4v1, resolved type: okio.Buffer */
        /* JADX DEBUG: Multi-variable search result rejected for r4v2, resolved type: okio.Buffer */
        /* JADX WARN: Multi-variable type inference failed */
        private long writeOrCountBytes(BufferedSink bufferedSink, boolean z) throws IOException {
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
                Headers headers = this.partHeaders.get(i);
                RequestBody requestBody = this.partBodies.get(i);
                bufferedSink.write(MultipartBuilder.DASHDASH);
                bufferedSink.write(this.boundary);
                byte[] bArr = MultipartBuilder.CRLF;
                bufferedSink.write(bArr);
                if (headers != null) {
                    int length = headers.namesAndValues.length >> 1;
                    for (int i2 = 0; i2 < length; i2++) {
                        bufferedSink.writeUtf8(headers.name(i2)).write(MultipartBuilder.COLONSPACE).writeUtf8(headers.value(i2)).write(bArr);
                    }
                }
                MediaType contentType2 = requestBody.contentType();
                if (contentType2 != null) {
                    bufferedSink.writeUtf8("Content-Type: ").writeUtf8(contentType2.toString()).write(bArr);
                }
                long contentLength2 = requestBody.contentLength();
                if (contentLength2 != -1) {
                    bufferedSink.writeUtf8("Content-Length: ").writeDecimalLong(contentLength2).write(MultipartBuilder.CRLF);
                } else if (z) {
                    buffer.clear();
                    return -1;
                }
                byte[] bArr2 = MultipartBuilder.CRLF;
                bufferedSink.write(bArr2);
                if (z) {
                    j += contentLength2;
                } else {
                    this.partBodies.get(i).writeTo(bufferedSink);
                }
                bufferedSink.write(bArr2);
            }
            bufferedSink.write(MultipartBuilder.DASHDASH);
            bufferedSink.write(this.boundary);
            bufferedSink.write(MultipartBuilder.DASHDASH);
            bufferedSink.write(MultipartBuilder.CRLF);
            if (!z) {
                return j;
            }
            long j2 = j + buffer.size;
            buffer.clear();
            return j2;
        }

        @Override // com.squareup.okhttp.RequestBody
        public long contentLength() throws IOException {
            long j = this.contentLength;
            if (j != -1) {
                return j;
            }
            long writeOrCountBytes = writeOrCountBytes(null, true);
            this.contentLength = writeOrCountBytes;
            return writeOrCountBytes;
        }

        public MultipartRequestBody(MediaType mediaType, ByteString byteString, List<Headers> list, List<RequestBody> list2) {
            if (mediaType != null) {
                this.boundary = byteString;
                StringBuilder sb = new StringBuilder();
                sb.append(mediaType);
                sb.append("; boundary=");
                sb.append(byteString.utf8());
                this.contentType = MediaType.parse(sb.toString());
                this.partHeaders = Util.immutableList(list);
                this.partBodies = Util.immutableList(list2);
                return;
            }
            throw new NullPointerException("type == null");
        }

        @Override // com.squareup.okhttp.RequestBody
        public MediaType contentType() {
            return this.contentType;
        }
    }

    public static StringBuilder appendQuotedString(StringBuilder sb, String str) {
        String str2;
        sb.append('\"');
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt != '\n') {
                if (charAt == '\r') {
                    str2 = "%0D";
                } else if (charAt != '\"') {
                    sb.append(charAt);
                } else {
                    str2 = "%22";
                }
                sb.append(str2);
            } else {
                str2 = "%0A";
                sb.append(str2);
            }
        }
        sb.append('\"');
        return sb;
    }

    public RequestBody build() {
        if (!this.partHeaders.isEmpty()) {
            return new MultipartRequestBody(this.type, this.boundary, this.partHeaders, this.partBodies);
        }
        throw new IllegalStateException("Multipart body must have at least one part.");
    }

    public MultipartBuilder type(MediaType mediaType) {
        if (mediaType == null) {
            throw new NullPointerException("type == null");
        } else if (mediaType.type.equals("multipart")) {
            this.type = mediaType;
            return this;
        } else {
            StringBuilder sb = new StringBuilder("multipart != ");
            sb.append(mediaType);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public MultipartBuilder() {
        this(UUID.randomUUID().toString());
    }

    public MultipartBuilder(String str) {
        this.type = MIXED;
        this.partHeaders = new ArrayList();
        this.partBodies = new ArrayList();
        this.boundary = ByteString.encodeUtf8(str);
    }

    public MultipartBuilder addFormDataPart(String str, String str2) {
        addFormDataPart(str, null, RequestBody.create((MediaType) null, str2));
        return this;
    }

    public MultipartBuilder addFormDataPart(String str, String str2, RequestBody requestBody) {
        if (str != null) {
            StringBuilder sb = new StringBuilder(HttpRequestMultipart.CONTENT_DISPOSITION_FORM_DATA);
            appendQuotedString(sb, str);
            if (str2 != null) {
                sb.append("; filename=");
                appendQuotedString(sb, str2);
            }
            addPart(Headers.of(HttpRequestMultipart.CONTENT_DISPOSITION, sb.toString()), requestBody);
            return this;
        }
        throw new NullPointerException("name == null");
    }

    public MultipartBuilder addPart(Headers headers, RequestBody requestBody) {
        if (requestBody != null) {
            if (headers != null) {
                String[] strArr = headers.namesAndValues;
                if (Headers.get(strArr, HttpRequestMultipart.CONTENT_TYPE) != null) {
                    throw new IllegalArgumentException("Unexpected header: Content-Type");
                } else if (Headers.get(strArr, "Content-Length") != null) {
                    throw new IllegalArgumentException("Unexpected header: Content-Length");
                }
            }
            this.partHeaders.add(headers);
            this.partBodies.add(requestBody);
            return this;
        }
        throw new NullPointerException("body == null");
    }

    public MultipartBuilder addPart(RequestBody requestBody) {
        addPart(null, requestBody);
        return this;
    }
}
