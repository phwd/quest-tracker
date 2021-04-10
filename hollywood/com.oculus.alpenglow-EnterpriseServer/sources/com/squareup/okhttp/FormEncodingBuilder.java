package com.squareup.okhttp;

import X.AnonymousClass0HR;
import X.AnonymousClass0Oe;
import java.io.IOException;

public final class FormEncodingBuilder {
    public static final MediaType CONTENT_TYPE = MediaType.parse("application/x-www-form-urlencoded");
    public final AnonymousClass0HR content = new AnonymousClass0HR();

    public FormEncodingBuilder add(String str, String str2) {
        AnonymousClass0HR r6 = this.content;
        if (r6.A00 > 0) {
            r6.A09(38);
        }
        HttpUrl.canonicalize(this.content, str, 0, str.length(), HttpUrl.FORM_ENCODE_SET, false, true, true);
        this.content.A09(61);
        HttpUrl.canonicalize(this.content, str2, 0, str2.length(), HttpUrl.FORM_ENCODE_SET, false, true, true);
        return this;
    }

    public FormEncodingBuilder addEncoded(String str, String str2) {
        AnonymousClass0HR r6 = this.content;
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
            public MediaType contentType() {
                return r1;
            }

            @Override // com.squareup.okhttp.RequestBody
            public void writeTo(AnonymousClass0Oe r2) throws IOException {
                r2.A8v(r2);
            }
        };
    }
}
