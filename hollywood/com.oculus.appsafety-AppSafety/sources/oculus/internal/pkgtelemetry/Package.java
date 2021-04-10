package oculus.internal.pkgtelemetry;

import android.os.Bundle;
import android.os.PersistableBundle;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.oculus.os.PackageMetadata;
import dalvik.system.VMDebug;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Objects;
import oculus.internal.gson.Base64EncodedData;
import oculus.internal.pkgtelemetry.PackageMetadataCollector;

public final class Package {
    public static final String ERRORS = "errors";
    public static final String FIRST_INSTALL_TIME = "first_install_time";
    public static final String INSTALLER_PACKAGE = "installer_package";
    public static final String INSTALL_FLAGS = "install_flags";
    public static final String LAST_UPDATE_TIME = "last_update_time";
    public static final String PACKAGE_ANDROID_MANIFEST = "package_android_manifest";
    public static final String PACKAGE_ANDROID_MANIFEST_HASH = "package_android_manifest_hash";
    public static final String PACKAGE_HASH = "package_hash";
    public static final String PACKAGE_ID = "package_id";
    public static final String PACKAGE_MANIFEST = "package_manifest";
    public static final String PACKAGE_MANIFEST_HASH = "package_manifest_hash";
    public static final String PACKAGE_MANIFEST_LENGTH = "package_manifest_length";
    public static final String PACKAGE_SIGNATURES = "package_signatures";
    public static final String SOURCE_STAMP_DISPOSITION = "source_stamp_disposition";
    private static final int TELEMETRY_EXTRA_MAX_LENGTH = 100000;
    private static final String TELEMETRY_EXTRA_TOO_LONG_OVERRIDE = "TOO_LONG";
    public final List<PackageMetadataCollector.CollectionError> errors;
    public final int installFlags;
    public final PackageMetadata packageMetadata;

    public static final class Builder {
        private byte[] androidManifest = null;
        private List<PackageMetadataCollector.CollectionError> errors = null;
        private long firstInstallTime = 0;
        private int installFlags = 0;
        private String installerPackage = null;
        private long lastUpdateTime = 0;
        private byte[] packageHash = null;
        private String packageIdentifier = null;
        private byte[] packageManifest = null;
        private PackageMetadata.Signature[] signatures = null;
        private PackageMetadata.SourceStampDisposition sourceStampDisposition = null;

        public Builder setPackageIdentifier(String packageIdentifier2) {
            this.packageIdentifier = packageIdentifier2;
            return this;
        }

        public Builder setPackageHash(byte[] packageHash2) {
            this.packageHash = packageHash2;
            return this;
        }

        public Builder setSignatures(PackageMetadata.Signature[] signatures2) {
            this.signatures = signatures2;
            return this;
        }

        public Builder setAndroidManifest(byte[] androidManifest2) {
            this.androidManifest = androidManifest2;
            return this;
        }

        public Builder setPackageManifest(byte[] packageManifest2) {
            this.packageManifest = packageManifest2;
            return this;
        }

        public Builder setInstallerPackage(String installerPackage2) {
            this.installerPackage = installerPackage2;
            return this;
        }

        public Builder setFirstInstallTime(long firstInstallTime2) {
            this.firstInstallTime = firstInstallTime2;
            return this;
        }

        public Builder setLastUpdateTime(long lastUpdateTime2) {
            this.lastUpdateTime = lastUpdateTime2;
            return this;
        }

        public Builder setInstallFlags(int installFlags2) {
            this.installFlags = installFlags2;
            return this;
        }

        public Builder setSourceStampDisposition(PackageMetadata.SourceStampDisposition disposition) {
            this.sourceStampDisposition = disposition;
            return this;
        }

        public Builder setErrors(List<PackageMetadataCollector.CollectionError> errors2) {
            this.errors = errors2;
            return this;
        }

        public Package build() {
            return new Package(new PackageMetadata.Builder().setPackageIdentifier(this.packageIdentifier).setPackageHash(this.packageHash).setSignatures(this.signatures).setAndroidManifest(this.androidManifest).setPackageManifest(this.packageManifest).setInstallerPackage(this.installerPackage).setFirstInstallTime(this.firstInstallTime).setLastUpdateTime(this.lastUpdateTime).setSourceStampDisposition(this.sourceStampDisposition).build(), this.installFlags, this.errors);
        }
    }

    Package(PackageMetadata packageMetadata2, int installFlags2, List<PackageMetadataCollector.CollectionError> errors2) {
        this.packageMetadata = packageMetadata2;
        this.installFlags = installFlags2;
        this.errors = errors2;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Package)) {
            return false;
        }
        Package o = (Package) other;
        if (!Objects.equals(this.packageMetadata, o.packageMetadata) || this.installFlags != o.installFlags || !Objects.equals(this.errors, o.errors)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.packageMetadata.packageIdentifier);
    }

    public PackageMetadata getPackageMetadata() {
        return this.packageMetadata;
    }

    public String getPackageIdentifier() {
        return this.packageMetadata.packageIdentifier;
    }

    public byte[] getPackageHash() {
        return this.packageMetadata.packageHash;
    }

    public PackageMetadata.Signature[] getSignatures() {
        return this.packageMetadata.signatures;
    }

    public byte[] getAndroidManifest() {
        return this.packageMetadata.androidManifest;
    }

    public byte[] getPackageManifest() {
        return this.packageMetadata.packageManifest;
    }

    public String getInstallerPackage() {
        return this.packageMetadata.installerPackage;
    }

    public long getFirstInstallTime() {
        return this.packageMetadata.firstInstallTime;
    }

    public long getLastUpdateTime() {
        return this.packageMetadata.lastUpdateTime;
    }

    public int getInstallFlags() {
        return this.installFlags;
    }

    public List<PackageMetadataCollector.CollectionError> getErrors() {
        return this.errors;
    }

    public void toTelemetryExtras(PersistableBundle extras) {
        toTelemetryExtras(extras, null, null);
    }

    public void toTelemetryExtras(PersistableBundle extras, byte[] androidManifestHash, byte[] packageManifestHash) {
        String packageManifestExtra;
        Gson gson = buildGson();
        extras.putString(PACKAGE_ID, getPackageIdentifier());
        extras.putString(PACKAGE_HASH, gson.toJson(getPackageHash()));
        if (androidManifestHash != null) {
            extras.putString(PACKAGE_ANDROID_MANIFEST_HASH, Base64EncodedData.getEncodedString(androidManifestHash));
        } else {
            extras.putString(PACKAGE_ANDROID_MANIFEST, gson.toJson(getAndroidManifest()));
        }
        if (packageManifestHash != null) {
            extras.putString(PACKAGE_MANIFEST_HASH, Base64EncodedData.getEncodedString(packageManifestHash));
        } else {
            String packageManifest = gson.toJson(getPackageManifest());
            int packageManifestLength = packageManifest.length();
            if (packageManifestLength <= TELEMETRY_EXTRA_MAX_LENGTH) {
                packageManifestExtra = packageManifest;
            } else {
                packageManifestExtra = TELEMETRY_EXTRA_TOO_LONG_OVERRIDE;
            }
            extras.putString(PACKAGE_MANIFEST, packageManifestExtra);
            extras.putInt(PACKAGE_MANIFEST_LENGTH, packageManifestLength);
        }
        extras.putString(INSTALLER_PACKAGE, getInstallerPackage());
        extras.putLong(FIRST_INSTALL_TIME, getFirstInstallTime() / 1000);
        extras.putLong(LAST_UPDATE_TIME, getLastUpdateTime() / 1000);
        extras.putString(ERRORS, gson.toJson(getErrors()));
        extras.putString(PACKAGE_SIGNATURES, gson.toJson(getSignatures()));
        boolean z = true;
        extras.putBoolean("replace_existing", (this.installFlags & 2) != 0);
        extras.putBoolean("allow_test", (this.installFlags & 4) != 0);
        extras.putBoolean("install_internal", (this.installFlags & 16) != 0);
        extras.putBoolean("install_from_adb", (this.installFlags & 32) != 0);
        extras.putBoolean("install_all_user", (this.installFlags & 64) != 0);
        extras.putBoolean("allow_downgrade", (this.installFlags & VMDebug.KIND_THREAD_GC_INVOCATIONS) != 0);
        extras.putBoolean("grant_runtime_permissions", (this.installFlags & 256) != 0);
        extras.putBoolean("force_volume_uuid", (this.installFlags & 512) != 0);
        extras.putBoolean("force_permission_prompt", (this.installFlags & 1024) != 0);
        if ((this.installFlags & 4096) == 0) {
            z = false;
        }
        extras.putBoolean("install_dont_kill_app", z);
        extras.putInt(SOURCE_STAMP_DISPOSITION, this.packageMetadata.sourceStampDisposition.value);
    }

    public Bundle toBundle() {
        Bundle extras = new Bundle();
        Gson gson = buildGson();
        extras.putString(PACKAGE_ID, getPackageIdentifier());
        extras.putString(PACKAGE_HASH, gson.toJson(getPackageHash()));
        extras.putString(PACKAGE_ANDROID_MANIFEST, gson.toJson(getAndroidManifest()));
        extras.putString(PACKAGE_MANIFEST, gson.toJson(getPackageManifest()));
        extras.putString(PACKAGE_SIGNATURES, gson.toJson(getSignatures()));
        extras.putString(INSTALLER_PACKAGE, getInstallerPackage());
        extras.putLong(FIRST_INSTALL_TIME, getFirstInstallTime());
        extras.putLong(LAST_UPDATE_TIME, getLastUpdateTime());
        extras.putInt(INSTALL_FLAGS, getInstallFlags());
        extras.putInt(SOURCE_STAMP_DISPOSITION, this.packageMetadata.sourceStampDisposition.value);
        extras.putString(ERRORS, gson.toJson(getErrors()));
        return extras;
    }

    static Gson buildGson() {
        return GsonBuilder.builder().setExclusionStrategies(new ExclusionStrategy() {
            /* class oculus.internal.pkgtelemetry.Package.AnonymousClass1 */

            @Override // com.google.gson.ExclusionStrategy
            public boolean shouldSkipClass(Class<?> clazz) {
                return X509Certificate.class.equals(clazz);
            }

            @Override // com.google.gson.ExclusionStrategy
            public boolean shouldSkipField(FieldAttributes f) {
                return X509Certificate.class.equals(f.getDeclaredClass());
            }
        }).create();
    }
}
