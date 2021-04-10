package com.oculus.mediaupload.api;

import com.facebook.common.stringformat.StringFormatUtil;

public class UploaderException extends Exception {
    public UploaderException() {
        super("Ran out of retries");
    }

    public UploaderException(Throwable th, Object... objArr) {
        super(StringFormatUtil.formatStrLocaleSafe("Ran out of retries", objArr), th);
    }
}
