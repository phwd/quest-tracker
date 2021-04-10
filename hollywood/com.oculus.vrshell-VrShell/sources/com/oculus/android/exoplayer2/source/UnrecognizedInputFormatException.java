package com.oculus.android.exoplayer2.source;

import android.net.Uri;
import com.oculus.android.exoplayer2.ParserException;

public class UnrecognizedInputFormatException extends ParserException {
    public final Uri uri;

    public UnrecognizedInputFormatException(String str, Uri uri2) {
        super(str);
        this.uri = uri2;
    }
}
