package oculus.internal;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.util.Log;

public class AssetManager {
    private static final int[][] ASSET_DATA_IDS = {new int[]{17825801, 17825802}, new int[]{17825805, 17825806}, new int[]{17825803, 17825804}, new int[]{17825797, 17825798}};
    private static final int[][] ASSET_FORMAT_IDS = {new int[]{17040036, 17040037}, new int[]{17040040, 17040041}, new int[]{17040038, 17040039}, new int[]{17040034, 17040035}};
    public static final long ID_DEFAULT = 3;
    public static final long ID_GO = 0;
    public static final long ID_JEDI = 2;
    public static final long ID_LCON = 1;
    public static final long ID_LEFT_CONTROLLER = 0;
    public static final long ID_RIGHT_CONTROLLER = 1;
    private static final String TAG = "AssetManager";

    public static class NativeAssetFd {
        AssetFileDescriptor afd;
        int fd = -1;
        long length;
        long offset;
    }

    public static NativeAssetFd loadAssetData(long deviceId, long assetId) {
        NativeAssetFd nfd = new NativeAssetFd();
        try {
            AssetFileDescriptor afd = Resources.getSystem().openRawResourceFd(ASSET_DATA_IDS[(int) deviceId][(int) assetId]);
            nfd.fd = afd.getParcelFileDescriptor().getFd();
            nfd.offset = afd.getStartOffset();
            nfd.length = afd.getLength();
            nfd.afd = afd;
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Failed to load asset " + assetId, e);
        } catch (ArrayIndexOutOfBoundsException e2) {
            Log.e(TAG, String.format("Failed to load asset, cannot find deviceId: %s assetId: %s", Long.valueOf(deviceId), Long.valueOf(assetId)));
        }
        return nfd;
    }

    public static void closeAsset(NativeAssetFd nfd) {
        if (nfd == null) {
            Log.e(TAG, "Failed to close asset, nfd object is null");
        }
        if (nfd.afd == null) {
            Log.e(TAG, "Failed to close asset, afd object is null");
        }
        try {
            nfd.afd.close();
        } catch (Exception e) {
            Log.e(TAG, "Failed to close asset file " + e);
        }
    }

    public static String getAssetFormat(long deviceId, long assetId) {
        return Resources.getSystem().getString(ASSET_FORMAT_IDS[(int) deviceId][(int) assetId]);
    }
}
