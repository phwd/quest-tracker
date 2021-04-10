package com.facebook;

public class FacebookGraphResponseException extends FacebookException {
    public final GraphResponse graphResponse;

    @Override // com.facebook.FacebookException
    public final String toString() {
        FacebookRequestError facebookRequestError;
        GraphResponse graphResponse2 = this.graphResponse;
        if (graphResponse2 != null) {
            facebookRequestError = graphResponse2.error;
        } else {
            facebookRequestError = null;
        }
        StringBuilder sb = new StringBuilder("{FacebookGraphResponseException: ");
        String message = getMessage();
        if (message != null) {
            sb.append(message);
            sb.append(" ");
        }
        if (facebookRequestError != null) {
            sb.append("httpResponseCode: ");
            sb.append(facebookRequestError.requestStatusCode);
            sb.append(", facebookErrorCode: ");
            sb.append(facebookRequestError.errorCode);
            sb.append(", facebookErrorType: ");
            sb.append(facebookRequestError.errorType);
            sb.append(", message: ");
            sb.append(facebookRequestError.getErrorMessage());
            sb.append("}");
        }
        return sb.toString();
    }

    public FacebookGraphResponseException(GraphResponse graphResponse2, String str) {
        super(str);
        this.graphResponse = graphResponse2;
    }

    public final GraphResponse getGraphResponse() {
        return this.graphResponse;
    }
}
