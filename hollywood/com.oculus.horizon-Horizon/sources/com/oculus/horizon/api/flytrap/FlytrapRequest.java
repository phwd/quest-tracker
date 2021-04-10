package com.oculus.horizon.api.flytrap;

import X.AnonymousClass006;
import com.oculus.http.core.base.ApiRequest;
import javax.annotation.concurrent.Immutable;
import retrofit.mime.TypedFile;

@Immutable
public class FlytrapRequest extends ApiRequest<FlytrapResponse> {
    public final String app_id;
    public final String app_secret;
    public final String client_time;
    public final String config_id;
    public final String locale;
    public TypedFile logcat;
    public final String metadata;
    public TypedFile screenshot;
    public TypedFile stackDump;
    public final String user_identifier;

    public String getAccessToken() {
        return AnonymousClass006.A07(this.app_id, "|", this.app_secret);
    }

    public FlytrapRequest(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.app_id = str;
        this.app_secret = str2;
        this.user_identifier = str3;
        this.client_time = str4;
        this.config_id = str5;
        this.locale = str6;
        this.metadata = str7;
    }
}
