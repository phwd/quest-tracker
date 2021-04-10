package com.oculus.mediaupload.model;

import com.facebook.common.stringformat.StringFormatUtil;
import com.oculus.mediaupload.model.MediaUploaderResult;

public class MediaUploaderException extends Exception {
    public MediaUploaderException(MediaUploaderResult.MediaUploaderRequestError mediaUploaderRequestError) {
        super(StringFormatUtil.formatStrLocaleSafe(mediaUploaderRequestError.name()));
    }

    public MediaUploaderException() {
        super("unknown request share type");
    }

    public MediaUploaderException(Object... objArr) {
        super(StringFormatUtil.formatStrLocaleSafe("unknown request type: %s", objArr));
    }
}
