package com.oculus.library.sync;

public class LibrarySyncQuery {
    public static final String SYNC_APP_LIBRARY_OSS_MUTATION = "mutation SyncDeviceManifestData($input: SyncDeviceManifestData!){sync_device_manifest(data: $input) {client_mutation_id,}}";
}
