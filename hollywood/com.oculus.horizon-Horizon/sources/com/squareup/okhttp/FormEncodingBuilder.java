package com.squareup.okhttp;

import X.AnonymousClass0B3;
import X.AnonymousClass0Lx;
import com.facebook.acra.util.HttpRequest;
import java.io.IOException;

public final class FormEncodingBuilder {
    public static final MediaType CONTENT_TYPE = MediaType.parse(HttpRequest.POST_CONTENT_TYPE_FORM_URLENCODED);
    public final AnonymousClass0B3 content = new AnonymousClass0B3();

    public FormEncodingBuilder add(String str, String str2) {
        AnonymousClass0B3 r6 = this.content;
        if (r6.A00 > 0) {
            r6.A09(38);
        }
        HttpUrl.canonicalize(this.content, str, 0, str.length(), HttpUrl.FORM_ENCODE_SET, false, true, true);
        this.content.A09(61);
        HttpUrl.canonicalize(this.content, str2, 0, str2.length(), HttpUrl.FORM_ENCODE_SET, false, true, true);
        return this;
    }

    public FormEncodingBuilder addEncoded(String str, String str2) {
        AnonymousClass0B3 r6 = this.content;
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
            public void writeTo(AnonymousClass0Lx r2) throws IOException {
                r2.AA6(r2);
            }

            @Override // com.squareup.okhttp.RequestBody
            public MediaType contentType() {
                return r1;
            }
        };
    }
}
