package com.oculus.http.core.base;

public interface ValidatableApiResponse {
    void validate() throws RuntimeException;
}
