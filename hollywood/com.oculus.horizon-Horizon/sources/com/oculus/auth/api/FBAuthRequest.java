package com.oculus.auth.api;

import javax.annotation.Nullable;

public class FBAuthRequest {
    public static final String CREDENTIAL_TYPE_PASSWORD = "password";
    public static final String CREDENTIAL_TYPE_TRANSIENT_TOKEN = "transient_token";
    public static final String CREDENTIAL_TYPE_TWO_FACTOR = "two_factor";
    @Nullable
    public String credentialsType;
    @Nullable
    public String email;
    @Nullable
    public String firstFactor;
    @Nullable
    public String machineId;
    @Nullable
    public String password;
    @Nullable
    public String uid;

    @Nullable
    public String getCredentialsType() {
        return this.credentialsType;
    }

    @Nullable
    public String getEmail() {
        return this.email;
    }

    @Nullable
    public String getFirstFactor() {
        return this.firstFactor;
    }

    @Nullable
    public String getMachineId() {
        return this.machineId;
    }

    @Nullable
    public String getPassword() {
        return this.password;
    }

    @Nullable
    public String getUid() {
        return this.uid;
    }

    public void setCredentialsType(@Nullable String str) {
        this.credentialsType = str;
    }

    public void setEmail(@Nullable String str) {
        this.email = str;
    }

    public void setFirstFactor(@Nullable String str) {
        this.firstFactor = str;
    }

    public void setMachineId(@Nullable String str) {
        this.machineId = str;
    }

    public void setPassword(@Nullable String str) {
        this.password = str;
    }

    public void setUid(@Nullable String str) {
        this.uid = str;
    }
}
