package com.facebook;

public class FacebookServiceException extends FacebookException {
    public static final long serialVersionUID = 1;
    public final FacebookRequestError error;

    @Override // com.facebook.FacebookException
    public final String toString() {
        StringBuilder sb = new StringBuilder("{FacebookServiceException: ");
        sb.append("httpResponseCode: ");
        FacebookRequestError facebookRequestError = this.error;
        sb.append(facebookRequestError.requestStatusCode);
        sb.append(", facebookErrorCode: ");
        sb.append(facebookRequestError.errorCode);
        sb.append(", facebookErrorType: ");
        sb.append(facebookRequestError.errorType);
        sb.append(", message: ");
        sb.append(facebookRequestError.getErrorMessage());
        sb.append("}");
        return sb.toString();
    }

    public FacebookServiceException(FacebookRequestError facebookRequestError, String str) {
        super(str);
        this.error = facebookRequestError;
    }

    public final FacebookRequestError getRequestError() {
        return this.error;
    }
}
