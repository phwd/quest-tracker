package com.oculus.horizon.remotewipe;

public class RemoteWipeQueries {
    public static final String DEVICE_MANIFEST_WIPE_STATUS = "me() {  all_device_manifests.device_serial(<serial_number>) {    nodes {      device_wipe_status    }  }}";
}
