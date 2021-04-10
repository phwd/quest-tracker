package com.squareup.okhttp;

import com.facebook.acra.util.HttpRequest;
import java.io.IOException;
import okio.Buffer;
import okio.BufferedSink;

public final class FormEncodingBuilder {
    public static final MediaType CONTENT_TYPE = MediaType.parse(HttpRequest.POST_CONTENT_TYPE_FORM_URLENCODED);
    public final Buffer content = new Buffer();

    public FormEncodingBuilder add(String str, String str2) {
        Buffer buffer = this.content;
        if (buffer.size > 0) {
            buffer.writeByte(38);
        }
        HttpUrl.canonicalize(this.content, str, 0, str.length(), " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, true, true);
        this.content.writeByte(61);
        HttpUrl.canonicalize(this.content, str2, 0, str2.length(), " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, true, true);
        return this;
    }

    public FormEncodingBuilder addEncoded(String str, String str2) {
        Buffer buffer = this.content;
        if (buffer.size > 0) {
            buffer.writeByte(38);
        }
        HttpUrl.canonicalize(this.content, str, 0, str.length(), " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, true, true);
        this.content.writeByte(61);
        HttpUrl.canonicalize(this.content, str2, 0, str2.length(), " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, true, true);
        return this;
    }

    public RequestBody build() {
        return new RequestBody(CONTENT_TYPE, this.content.snapshot()) {
            /* class com.squareup.okhttp.RequestBody.AnonymousClass1 */

            @Override // com.squareup.okhttp.RequestBody
            public long contentLength() throws IOException {
                return (long) r2.size();
            }

            @Override // com.squareup.okhttp.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                bufferedSink.write(r2);
            }

            @Override // com.squareup.okhttp.RequestBody
            public MediaType contentType() {
                return r1;
            }
        };
    }
}
