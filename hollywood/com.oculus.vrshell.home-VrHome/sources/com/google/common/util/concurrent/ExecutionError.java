package com.google.common.util.concurrent;

public class ExecutionError extends Error {
    protected ExecutionError() {
    }

    public ExecutionError(Error cause) {
        super(cause);
    }
}
