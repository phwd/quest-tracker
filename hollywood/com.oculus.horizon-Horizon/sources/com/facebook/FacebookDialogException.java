package com.facebook;

public class FacebookDialogException extends FacebookException {
    public static final long serialVersionUID = 1;
    public int errorCode;
    public String failingUrl;

    @Override // com.facebook.FacebookException
    public final String toString() {
        StringBuilder sb = new StringBuilder("{FacebookDialogException: ");
        sb.append("errorCode: ");
        sb.append(this.errorCode);
        sb.append(", message: ");
        sb.append(getMessage());
        sb.append(", url: ");
        sb.append(this.failingUrl);
        sb.append("}");
        return sb.toString();
    }

    public FacebookDialogException(String str, int i, String str2) {
        super(str);
        this.errorCode = i;
        this.failingUrl = str2;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getFailingUrl() {
        return this.failingUrl;
    }
}
