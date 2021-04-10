package com.oculus.os;

import android.os.ParcelFileDescriptor;

public class MapManager {
    public MapManager() {
        throw new RuntimeException("Stub!");
    }

    public long readMap(ParcelFileDescriptor pipeWriteEnd, String mapId) {
        throw new RuntimeException("Stub!");
    }

    public boolean writeMap(ParcelFileDescriptor pipeReadEnd, String mapId, long fileSize) {
        throw new RuntimeException("Stub!");
    }

    public String[] listMaps() {
        throw new RuntimeException("Stub!");
    }

    public String getCurrentMapUUID() {
        throw new RuntimeException("Stub!");
    }
}
