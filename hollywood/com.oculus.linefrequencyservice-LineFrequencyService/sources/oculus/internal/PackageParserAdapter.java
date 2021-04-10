package oculus.internal;

import android.content.pm.PackageInfo;
import android.content.pm.PackageParser;
import android.content.pm.PackageUserState;
import java.io.File;
import java.util.Set;

public class PackageParserAdapter {
    public static PackageInfo getPackageInfo(File file) throws PackageParser.PackageParserException {
        PackageParser.Package pkg = new PackageParser().parsePackage(file, 0);
        PackageParser.collectCertificates(pkg, false);
        return PackageParser.generatePackageInfo(pkg, (int[]) null, 794624, 0, 0, (Set) null, new PackageUserState());
    }
}
