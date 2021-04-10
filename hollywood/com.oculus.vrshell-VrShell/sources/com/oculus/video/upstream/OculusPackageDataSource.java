package com.oculus.video.upstream;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.text.TextUtils;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import com.oculus.android.exoplayer2.upstream.TransferListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public final class OculusPackageDataSource implements DataSource {
    private static final String ASSETS_PATH_NAME = "assets";
    private final AssetManager assetManager;
    private long bytesRemaining;
    private InputStream inputStream;
    private final TransferListener<? super OculusPackageDataSource> listener;
    private boolean opened;
    private final Map<String, AssetManager> packageAssetManagers = new HashMap();
    private final PackageManager packageManager;
    private Uri uri;

    public static final class PackageDataSourceException extends IOException {
        public PackageDataSourceException(Exception exc) {
            super(exc);
        }
    }

    public OculusPackageDataSource(Context context, TransferListener<? super OculusPackageDataSource> transferListener) {
        this.assetManager = context.getAssets();
        this.packageManager = context.getPackageManager();
        this.listener = transferListener;
    }

    @Override // com.oculus.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws PackageDataSourceException {
        try {
            this.uri = dataSpec.uri;
            String path = this.uri.getPath();
            String host = this.uri.getHost();
            int indexOf = path.indexOf("/assets/");
            if (indexOf >= 0) {
                String substring = path.substring(indexOf + 8);
                AssetManager assetManager2 = this.assetManager;
                if (!TextUtils.isEmpty(host)) {
                    if (!this.packageAssetManagers.containsKey(host)) {
                        this.packageAssetManagers.put(host, this.packageManager.getResourcesForApplication(host).getAssets());
                    }
                    assetManager2 = this.packageAssetManagers.get(host);
                }
                this.inputStream = assetManager2.open(substring, 1);
                if (this.inputStream.skip(dataSpec.position) >= dataSpec.position) {
                    if (dataSpec.length != -1) {
                        this.bytesRemaining = dataSpec.length;
                    } else {
                        this.bytesRemaining = (long) this.inputStream.available();
                        if (this.bytesRemaining == 2147483647L) {
                            this.bytesRemaining = -1;
                        }
                    }
                    this.opened = true;
                    TransferListener<? super OculusPackageDataSource> transferListener = this.listener;
                    if (transferListener != null) {
                        transferListener.onTransferStart(this, dataSpec);
                    }
                    return this.bytesRemaining;
                }
                throw new EOFException();
            }
            throw new PackageDataSourceException(new IOException("Unexpected APK URL"));
        } catch (PackageManager.NameNotFoundException | IOException e) {
            throw new PackageDataSourceException(e);
        }
    }

    @Override // com.oculus.android.exoplayer2.upstream.DataSource
    public int read(byte[] bArr, int i, int i2) throws PackageDataSourceException {
        if (i2 == 0) {
            return 0;
        }
        long j = this.bytesRemaining;
        if (j == 0) {
            return -1;
        }
        if (j != -1) {
            try {
                i2 = (int) Math.min(j, (long) i2);
            } catch (IOException e) {
                throw new PackageDataSourceException(e);
            }
        }
        int read = this.inputStream.read(bArr, i, i2);
        if (read != -1) {
            long j2 = this.bytesRemaining;
            if (j2 != -1) {
                this.bytesRemaining = j2 - ((long) read);
            }
            TransferListener<? super OculusPackageDataSource> transferListener = this.listener;
            if (transferListener != null) {
                transferListener.onBytesTransferred(this, read);
            }
            return read;
        } else if (this.bytesRemaining == -1) {
            return -1;
        } else {
            throw new PackageDataSourceException(new EOFException());
        }
    }

    @Override // com.oculus.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        return this.uri;
    }

    @Override // com.oculus.android.exoplayer2.upstream.DataSource
    public void close() throws PackageDataSourceException {
        this.uri = null;
        try {
            if (this.inputStream != null) {
                this.inputStream.close();
            }
            this.inputStream = null;
            if (this.opened) {
                this.opened = false;
                TransferListener<? super OculusPackageDataSource> transferListener = this.listener;
                if (transferListener != null) {
                    transferListener.onTransferEnd(this);
                }
            }
        } catch (IOException e) {
            throw new PackageDataSourceException(e);
        } catch (Throwable th) {
            this.inputStream = null;
            if (this.opened) {
                this.opened = false;
                TransferListener<? super OculusPackageDataSource> transferListener2 = this.listener;
                if (transferListener2 != null) {
                    transferListener2.onTransferEnd(this);
                }
            }
            throw th;
        }
    }
}
