package com.oculus.horizon.abuse_prevention;

import com.facebook.common.stringformat.StringFormatUtil;

public class VideoUploaderException extends Exception {
    public VideoUploaderException(String str) {
        super(str);
    }

    public VideoUploaderException(String str, Object... objArr) {
        super(StringFormatUtil.formatStrLocaleSafe(str, objArr));
    }

    public VideoUploaderException(Throwable th) {
        super(th);
    }

    public VideoUploaderException(Throwable th, String str, Object... objArr) {
        super(StringFormatUtil.formatStrLocaleSafe(str, objArr), th);
    }
}
