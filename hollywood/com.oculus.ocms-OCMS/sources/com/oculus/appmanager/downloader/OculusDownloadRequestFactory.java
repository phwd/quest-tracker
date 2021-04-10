package com.oculus.appmanager.downloader;

import android.app.DownloadManager;
import android.net.Uri;
import com.oculus.downloader.util.OculusDownloadManagerUtils;

public class OculusDownloadRequestFactory {
    private final OculusDownloadManagerUtils mDownloadManagerUtils;

    public OculusDownloadRequestFactory(OculusDownloadManagerUtils oculusDownloadManagerUtils) {
        this.mDownloadManagerUtils = oculusDownloadManagerUtils;
    }

    public DownloadManager.Request createAuthenticatedRequestWithDefaultConfig(String str) {
        DownloadManager.Request createAuthenticatedRequest = createAuthenticatedRequest(str);
        applyDefaultNetworkingConfig(createAuthenticatedRequest);
        applyDefaultVisibilityConfig(createAuthenticatedRequest);
        applyDefaultDestinationConfig(createAuthenticatedRequest);
        return createAuthenticatedRequest;
    }

    private void applyDefaultDestinationConfig(DownloadManager.Request request) {
        request.setDestinationUri(null);
    }

    private void applyDefaultNetworkingConfig(DownloadManager.Request request) {
        request.setAllowedNetworkTypes(2);
        request.setAllowedOverRoaming(false);
        this.mDownloadManagerUtils.setAllowedOverMetered(request, false);
    }

    private void applyDefaultVisibilityConfig(DownloadManager.Request request) {
        request.setVisibleInDownloadsUi(false);
    }

    public DownloadManager.Request createAuthenticatedRequest(String str) {
        return new DownloadManager.Request(Uri.parse(str));
    }
}
