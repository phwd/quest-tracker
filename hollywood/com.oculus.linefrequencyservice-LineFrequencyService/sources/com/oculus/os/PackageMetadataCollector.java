package com.oculus.os;

import android.content.pm.PackageInfo;
import com.oculus.os.PackageMetadata;
import java.io.File;
import oculus.internal.SourceStampVerifier;

public final class PackageMetadataCollector {
    public static PackageMetadata.SourceStampDisposition sourceStampDisposition(PackageInfo packageInfo) {
        return PackageMetadata.SourceStampDisposition.fromValue(SourceStampVerifier.verify(new File(packageInfo.applicationInfo.sourceDir)).value);
    }
}
