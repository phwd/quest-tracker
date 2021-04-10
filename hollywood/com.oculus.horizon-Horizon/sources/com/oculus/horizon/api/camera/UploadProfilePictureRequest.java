package com.oculus.horizon.api.camera;

import com.oculus.http.core.base.ApiRequest;
import java.io.File;

public class UploadProfilePictureRequest extends ApiRequest<Void> {
    public final File profilePictureFile;

    public UploadProfilePictureRequest(File file) {
        this.profilePictureFile = file;
    }
}
