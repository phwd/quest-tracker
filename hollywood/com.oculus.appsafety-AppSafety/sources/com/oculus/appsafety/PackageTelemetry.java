package com.oculus.appsafety;

import java.util.Arrays;
import java.util.Objects;
import oculus.internal.pkgtelemetry.Package;

/* access modifiers changed from: package-private */
public class PackageTelemetry {
    public final byte[] androidManifestHash;
    public final Package packageInfo;
    public final byte[] packageManifestHash;
    public final long processingTimeMs;

    PackageTelemetry(Package packageInfo2, long processingTimeMs2, byte[] androidManifestHash2, byte[] packageManifestHash2) {
        this.packageInfo = packageInfo2;
        this.processingTimeMs = processingTimeMs2;
        this.androidManifestHash = androidManifestHash2;
        this.packageManifestHash = packageManifestHash2;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof PackageTelemetry)) {
            return false;
        }
        PackageTelemetry o = (PackageTelemetry) other;
        if (!Objects.equals(this.packageInfo, o.packageInfo) || this.processingTimeMs != o.processingTimeMs || !Arrays.equals(this.androidManifestHash, o.androidManifestHash) || !Arrays.equals(this.packageManifestHash, o.packageManifestHash)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.packageInfo);
    }
}
