package com.oculus.devicecertservice;

public interface DeviceCertInterface {

    public enum ProvisionType {
        FACTORY,
        PROTOTYPE,
        UNPROVISIONED
    }

    public enum SecureState {
        CURRENT,
        SECURE,
        INSECURE
    }

    public static class ProvisionState {
        public final String provisionSerial;
        public final ProvisionType provisionType;

        public ProvisionState(ProvisionType provisionType2, String str) {
            this.provisionType = provisionType2;
            this.provisionSerial = str;
        }
    }
}
