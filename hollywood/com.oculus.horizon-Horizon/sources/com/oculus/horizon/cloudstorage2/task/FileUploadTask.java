package com.oculus.horizon.cloudstorage2.task;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.cloudstorage2.Reporter;
import java.io.File;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_facebook_fbuploader_FbUploaderImpl_ULSEP_BINDING_ID"})
public class FileUploadTask extends AsyncWork<RemoteFile> {
    public AnonymousClass0QC _UL_mInjectionContext;
    public final File mFile;
    @Nullable
    public ProgressListener mProgressListener = null;
    public final String mSha1;

    public interface ProgressListener {
    }

    @Inject
    public FileUploadTask(AbstractC06640p5 r4, @Assisted File file, @Assisted String str, @Assisted @Nullable Reporter reporter) {
        super(reporter);
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r4);
        this.mFile = file;
        this.mSha1 = str;
    }

    public static class RemoteFile {
        public final File file;
        @Nullable
        public final String uploadedHandle;

        public RemoteFile(File file2, String str) {
            this.file = file2;
            this.uploadedHandle = str;
        }
    }
}
