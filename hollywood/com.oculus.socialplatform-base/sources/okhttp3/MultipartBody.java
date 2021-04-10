package okhttp3;

import com.facebook.acra.util.HttpRequestMultipart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;

public final class MultipartBody extends RequestBody {
    public static final MediaType ALTERNATIVE = MediaType.parse("multipart/alternative");
    public static final byte[] COLONSPACE = {58, 32};
    public static final byte[] CRLF = {13, 10};
    public static final byte[] DASHDASH = {45, 45};
    public static final MediaType DIGEST = MediaType.parse("multipart/digest");
    public static final MediaType FORM = MediaType.parse("multipart/form-data");
    public static final MediaType MIXED = MediaType.parse("multipart/mixed");
    public static final MediaType PARALLEL = MediaType.parse("multipart/parallel");
    public final ByteString boundary;
    public long contentLength = -1;
    public final MediaType contentType;
    public final MediaType originalType;
    public final List<Part> parts;

    public static final class Builder {
        public final ByteString boundary;
        public final List<Part> parts;
        public MediaType type;

        public MultipartBody build() {
            if (!this.parts.isEmpty()) {
                return new MultipartBody(this.boundary, this.type, this.parts);
            }
            throw new IllegalStateException("Multipart body must have at least one part.");
        }

        public Builder setType(MediaType mediaType) {
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

        public Builder() {
            this(UUID.randomUUID().toString());
        }

        public Builder(String str) {
            this.type = MultipartBody.MIXED;
            this.parts = new ArrayList();
            this.boundary = ByteString.encodeUtf8(str);
        }

        public Builder addFormDataPart(String str, String str2) {
            addPart(Part.createFormData(str, str2));
            return this;
        }

        public Builder addFormDataPart(String str, String str2, RequestBody requestBody) {
            addPart(Part.createFormData(str, str2, requestBody));
            return this;
        }

        public Builder addPart(Headers headers, RequestBody requestBody) {
            addPart(Part.create(headers, requestBody));
            return this;
        }

        public Builder addPart(Part part) {
            if (part != null) {
                this.parts.add(part);
                return this;
            }
            throw new NullPointerException("part == null");
        }

        public Builder addPart(RequestBody requestBody) {
            addPart(Part.create(null, requestBody));
            return this;
        }
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        writeOrCountBytes(bufferedSink, false);
    }

    public static final class Part {
        public final RequestBody body;
        public final Headers headers;

        public Part(Headers headers2, RequestBody requestBody) {
            this.headers = headers2;
            this.body = requestBody;
        }

        public RequestBody body() {
            return this.body;
        }

        public Headers headers() {
            return this.headers;
        }

        public static Part create(Headers headers2, RequestBody requestBody) {
            if (requestBody != null) {
                if (headers2 != null) {
                    String[] strArr = headers2.namesAndValues;
                    if (Headers.get(strArr, HttpRequestMultipart.CONTENT_TYPE) != null) {
                        throw new IllegalArgumentException("Unexpected header: Content-Type");
                    } else if (Headers.get(strArr, "Content-Length") != null) {
                        throw new IllegalArgumentException("Unexpected header: Content-Length");
                    }
                }
                return new Part(headers2, requestBody);
            }
            throw new NullPointerException("body == null");
        }

        public static Part create(RequestBody requestBody) {
            return create(null, requestBody);
        }

        public static Part createFormData(String str, String str2) {
            return createFormData(str, null, RequestBody.create((MediaType) null, str2));
        }

        public static Part createFormData(String str, String str2, RequestBody requestBody) {
            if (str != null) {
                StringBuilder sb = new StringBuilder(HttpRequestMultipart.CONTENT_DISPOSITION_FORM_DATA);
                MultipartBody.appendQuotedString(sb, str);
                if (str2 != null) {
                    sb.append("; filename=");
                    MultipartBody.appendQuotedString(sb, str2);
                }
                return create(Headers.of(HttpRequestMultipart.CONTENT_DISPOSITION, sb.toString()), requestBody);
            }
            throw new NullPointerException("name == null");
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
        int size = this.parts.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            Part part = this.parts.get(i);
            Headers headers = part.headers;
            RequestBody requestBody = part.body;
            bufferedSink.write(DASHDASH);
            bufferedSink.write(this.boundary);
            bufferedSink.write(CRLF);
            if (headers != null) {
                int length = headers.namesAndValues.length >> 1;
                for (int i2 = 0; i2 < length; i2++) {
                    bufferedSink.writeUtf8(headers.namesAndValues[i2 << 1]).write(COLONSPACE).writeUtf8(headers.value(i2)).write(CRLF);
                }
            }
            MediaType contentType2 = requestBody.contentType();
            if (contentType2 != null) {
                bufferedSink.writeUtf8("Content-Type: ").writeUtf8(contentType2.toString()).write(CRLF);
            }
            long contentLength2 = requestBody.contentLength();
            if (contentLength2 != -1) {
                bufferedSink.writeUtf8("Content-Length: ").writeDecimalLong(contentLength2).write(CRLF);
            } else if (z) {
                buffer.clear();
                return -1;
            }
            byte[] bArr = CRLF;
            bufferedSink.write(bArr);
            if (z) {
                j += contentLength2;
            } else {
                requestBody.writeTo(bufferedSink);
            }
            bufferedSink.write(bArr);
        }
        bufferedSink.write(DASHDASH);
        bufferedSink.write(this.boundary);
        bufferedSink.write(DASHDASH);
        bufferedSink.write(CRLF);
        if (!z) {
            return j;
        }
        long j2 = j + buffer.size;
        buffer.clear();
        return j2;
    }

    public String boundary() {
        return this.boundary.utf8();
    }

    @Override // okhttp3.RequestBody
    public long contentLength() throws IOException {
        long j = this.contentLength;
        if (j != -1) {
            return j;
        }
        long writeOrCountBytes = writeOrCountBytes(null, true);
        this.contentLength = writeOrCountBytes;
        return writeOrCountBytes;
    }

    public Part part(int i) {
        return this.parts.get(i);
    }

    public int size() {
        return this.parts.size();
    }

    public MultipartBody(ByteString byteString, MediaType mediaType, List<Part> list) {
        this.boundary = byteString;
        this.originalType = mediaType;
        StringBuilder sb = new StringBuilder();
        sb.append(mediaType);
        sb.append("; boundary=");
        sb.append(byteString.utf8());
        this.contentType = MediaType.parse(sb.toString());
        this.parts = Util.immutableList(list);
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return this.contentType;
    }

    public List<Part> parts() {
        return this.parts;
    }

    public MediaType type() {
        return this.originalType;
    }
}
