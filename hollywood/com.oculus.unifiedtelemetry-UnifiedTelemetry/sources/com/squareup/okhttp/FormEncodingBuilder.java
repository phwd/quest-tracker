package com.squareup.okhttp;

import X.AnonymousClass98;
import X.KJ;
import com.facebook.acra.util.HttpRequest;
import java.io.IOException;

public final class FormEncodingBuilder {
    public static final MediaType CONTENT_TYPE = MediaType.parse(HttpRequest.POST_CONTENT_TYPE_FORM_URLENCODED);
    public final AnonymousClass98 content = new AnonymousClass98();

    public FormEncodingBuilder add(String str, String str2) {
        AnonymousClass98 r6 = this.content;
        if (r6.A00 > 0) {
            r6.A09(38);
        }
        HttpUrl.canonicalize(this.content, str, 0, str.length(), HttpUrl.FORM_ENCODE_SET, false, true, true);
        this.content.A09(61);
        HttpUrl.canonicalize(this.content, str2, 0, str2.length(), HttpUrl.FORM_ENCODE_SET, false, true, true);
        return this;
    }

    public FormEncodingBuilder addEncoded(String str, String str2) {
        AnonymousClass98 r6 = this.content;
        if (r6.A00 > 0) {
            r6.A09(38);
        }
        HttpUrl.canonicalize(this.content, str, 0, str.length(), HttpUrl.FORM_ENCODE_SET, true, true, true);
        this.content.A09(61);
        HttpUrl.canonicalize(this.content, str2, 0, str2.length(), HttpUrl.FORM_ENCODE_SET, true, true, true);
        return this;
    }

    public RequestBody build() {
        return new RequestBody(CONTENT_TYPE, this.content.A06()) {
            /* class com.squareup.okhttp.RequestBody.AnonymousClass1 */

            @Override // com.squareup.okhttp.RequestBody
            public long contentLength() throws IOException {
                return (long) r2.A07();
            }

            @Override // com.squareup.okhttp.RequestBody
            public void writeTo(KJ kj) throws IOException {
                kj.A5m(r2);
            }

            @Override // com.squareup.okhttp.RequestBody
            public MediaType contentType() {
                return r1;
            }
        };
    }
}
