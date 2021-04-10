package com.facebook.proxygen;

import X.EX;

public class HttpNetworkException extends EX {
    public final HTTPRequestError mError;

    public HttpNetworkException(HTTPRequestError hTTPRequestError) {
        super(hTTPRequestError.mErrMsg);
        this.mError = hTTPRequestError;
    }

    public HTTPRequestError getRequestError() {
        return this.mError;
    }
}
