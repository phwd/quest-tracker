package com.squareup.okhttp;

import X.AnonymousClass0B3;
import X.AnonymousClass0Lx;
import X.C07700vD;
import com.facebook.acra.util.HttpRequestMultipart;
import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class MultipartBuilder {
    public static final MediaType ALTERNATIVE = MediaType.parse("multipart/alternative");
    public static final byte[] COLONSPACE = {58, 32};
    public static final byte[] CRLF = {13, 10};
    public static final byte[] DASHDASH = {45, 45};
    public static final MediaType DIGEST = MediaType.parse("multipart/digest");
    public static final MediaType FORM = MediaType.parse("multipart/form-data");
    public static final MediaType MIXED = MediaType.parse("multipart/mixed");
    public static final MediaType PARALLEL = MediaType.parse("multipart/parallel");
    public final C07700vD boundary;
    public final List<RequestBody> partBodies;
    public final List<Headers> partHeaders;
    public MediaType type;

    public static final class MultipartRequestBody extends RequestBody {
        public final C07700vD boundary;
        public long contentLength = -1;
        public final MediaType contentType;
        public final List<RequestBody> partBodies;
        public final List<Headers> partHeaders;

        @Override // com.squareup.okhttp.RequestBody
        public void writeTo(AnonymousClass0Lx r2) throws IOException {
            writeOrCountBytes(r2, false);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: X.0B3 */
        /* JADX DEBUG: Multi-variable search result rejected for r4v1, resolved type: X.0B3 */
        /* JADX DEBUG: Multi-variable search result rejected for r4v2, resolved type: X.0B3 */
        /* JADX WARN: Multi-variable type inference failed */
        private long writeOrCountBytes(AnonymousClass0Lx r12, boolean z) throws IOException {
            AnonymousClass0B3 r4;
            if (z) {
                r12 = new AnonymousClass0B3();
                r4 = r12;
            } else {
                r4 = 0;
            }
            int size = this.partHeaders.size();
            long j = 0;
            for (int i = 0; i < size; i++) {
                Headers headers = this.partHeaders.get(i);
                RequestBody requestBody = this.partBodies.get(i);
                r12.AA7(MultipartBuilder.DASHDASH);
                r12.AA6(this.boundary);
                byte[] bArr = MultipartBuilder.CRLF;
                r12.AA7(bArr);
                if (headers != null) {
                    int length = headers.namesAndValues.length >> 1;
                    for (int i2 = 0; i2 < length; i2++) {
                        r12.AAI(headers.name(i2));
                        r12.AA7(MultipartBuilder.COLONSPACE);
                        r12.AAI(headers.value(i2));
                        r12.AA7(bArr);
                    }
                }
                MediaType contentType2 = requestBody.contentType();
                if (contentType2 != null) {
                    r12.AAI("Content-Type: ");
                    r12.AAI(contentType2.toString());
                    r12.AA7(bArr);
                }
                long contentLength2 = requestBody.contentLength();
                if (contentLength2 != -1) {
                    r12.AAI("Content-Length: ");
                    r12.AAD(contentLength2);
                    r12.AA7(MultipartBuilder.CRLF);
                } else if (z) {
                    r4.A08();
                    return -1;
                }
                byte[] bArr2 = MultipartBuilder.CRLF;
                r12.AA7(bArr2);
                if (z) {
                    j += contentLength2;
                } else {
                    this.partBodies.get(i).writeTo(r12);
                }
                r12.AA7(bArr2);
            }
            r12.AA7(MultipartBuilder.DASHDASH);
            r12.AA6(this.boundary);
            r12.AA7(MultipartBuilder.DASHDASH);
            r12.AA7(MultipartBuilder.CRLF);
            if (!z) {
                return j;
            }
            long j2 = j + r4.A00;
            r4.A08();
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

        public MultipartRequestBody(MediaType mediaType, C07700vD r4, List<Headers> list, List<RequestBody> list2) {
            if (mediaType != null) {
                this.boundary = r4;
                StringBuilder sb = new StringBuilder();
                sb.append(mediaType);
                sb.append("; boundary=");
                sb.append(r4.A0A());
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
        this.boundary = C07700vD.A04(str);
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
        String str;
        if (requestBody != null) {
            if (headers != null) {
                String[] strArr = headers.namesAndValues;
                if (Headers.get(strArr, "Content-Type") != null) {
                    str = "Unexpected header: Content-Type";
                } else if (Headers.get(strArr, "Content-Length") != null) {
                    str = "Unexpected header: Content-Length";
                }
                throw new IllegalArgumentException(str);
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
