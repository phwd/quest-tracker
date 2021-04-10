package com.oculus.horizon.service;

import X.AnonymousClass006;
import X.AnonymousClass0NO;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Process;
import android.os.RemoteException;
import com.facebook.FacebookSdk;
import com.oculus.appmanager.signature.VRPackageVerifier;
import com.oculus.appmanager.vrsign.VRSignVerifier;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.annotation.Nullable;

public class SignatureChecker {
    public static final Class<?> TAG = SignatureChecker.class;
    public static final ConcurrentHashMap<Integer, SignatureLevel> sSignatureChecks = new ConcurrentHashMap<>();

    public enum SignatureLevel {
        UNSIGNED,
        DEVELOPER,
        VR
    }

    public static void clear() {
        sSignatureChecks.clear();
    }

    @Nullable
    public static String getCallingPackage(Context context) {
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(Binder.getCallingUid());
        if (packagesForUid.length > 0) {
            return packagesForUid[0];
        }
        return null;
    }

    public static SignatureLevel getCallingSignatureLevel(Context context) {
        int callingUid = Binder.getCallingUid();
        ConcurrentHashMap<Integer, SignatureLevel> concurrentHashMap = sSignatureChecks;
        Integer valueOf = Integer.valueOf(callingUid);
        if (concurrentHashMap.containsKey(valueOf)) {
            return sSignatureChecks.get(valueOf);
        }
        String callingPackage = getCallingPackage(context);
        SignatureLevel signatureLevel = SignatureLevel.UNSIGNED;
        if (callingPackage != null) {
            VRPackageVerifier vRPackageVerifier = new VRPackageVerifier();
            try {
                PackageManager packageManager = context.getPackageManager();
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(callingPackage, FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE);
                try {
                    VRSignVerifier vRSignVerifier = vRPackageVerifier.mVerifier;
                    JarFile jarFile = new JarFile(applicationInfo.publicSourceDir);
                    List<JarEntry> A00 = VRSignVerifier.A00(jarFile, ".SF");
                    if (A00.size() == 1) {
                        InputStream inputStream = jarFile.getInputStream(A00.get(0));
                        Iterator<JarEntry> it = VRSignVerifier.A00(jarFile, ".OSIG").iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            if (vRSignVerifier.mVerifier.A00(inputStream, jarFile.getInputStream(it.next()))) {
                                signatureLevel = SignatureLevel.VR;
                                break;
                            }
                        }
                        if (vRPackageVerifier.A00(packageManager, callingPackage)) {
                            signatureLevel = SignatureLevel.DEVELOPER;
                        }
                    } else {
                        throw new IOException(AnonymousClass006.A01("expected one .SF file, got ", A00.size()));
                    }
                } catch (IOException unused) {
                }
            } catch (PackageManager.NameNotFoundException e) {
                AnonymousClass0NO.A07(SignatureChecker.class, e, "Error checking signature, package not found: %s", callingPackage);
            }
        }
        sSignatureChecks.put(valueOf, signatureLevel);
        return signatureLevel;
    }

    public static boolean isCallerHorizon() {
        if (Binder.getCallingUid() == Process.myUid() && Binder.getCallingPid() == Process.myPid()) {
            return true;
        }
        return false;
    }

    public static boolean isCallerUnsigned(Context context) {
        if (isCallerHorizon() || getCallingSignatureLevel(context) != SignatureLevel.UNSIGNED) {
            return false;
        }
        return true;
    }

    public static void throwIfCallerNotSigned(Context context) throws RemoteException {
        if (isCallerUnsigned(context)) {
            throw new RemoteException("Calling package does not have a valid VR signature.");
        }
    }
}
