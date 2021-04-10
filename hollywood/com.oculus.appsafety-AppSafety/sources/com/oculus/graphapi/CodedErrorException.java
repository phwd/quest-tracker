package com.oculus.graphapi;

public class CodedErrorException extends Exception {
    public final CodedError error;

    public CodedErrorException(CodedError error2) {
        super(error2.message);
        this.error = error2;
    }

    public CodedErrorException(CodedError error2, Throwable t) {
        super(error2.message, t);
        this.error = error2;
    }
}
