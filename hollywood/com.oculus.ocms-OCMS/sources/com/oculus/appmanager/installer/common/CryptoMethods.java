package com.oculus.appmanager.installer.common;

import android.util.Base64;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.secure.trustedapp.HashHelper;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.UL;
import com.google.common.io.Closeables;
import com.oculus.appmanager.installer.common.CommonModule;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.inject.Provider;

@Dependencies({})
public class CryptoMethods {
    private static final Map<String, Integer> HASH_SIZE_MAP = new HashMap<String, Integer>() {
        /* class com.oculus.appmanager.installer.common.CryptoMethods.AnonymousClass1 */

        {
            put("MD5", 32);
            put(HashHelper.SHA1, 40);
            put("SHA-256", 64);
        }
    };

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_appmanager_installer_common_CryptoMethods_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_CryptoMethods_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_appmanager_installer_common_CryptoMethods_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_CryptoMethods_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final CryptoMethods _UL__ULSEP_com_oculus_appmanager_installer_common_CryptoMethods_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (CryptoMethods) UL.factorymap.get(CommonModule.UL_id._UL__ULSEP_com_oculus_appmanager_installer_common_CryptoMethods_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final CryptoMethods _UL__ULSEP_com_oculus_appmanager_installer_common_CryptoMethods_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new CryptoMethods();
    }

    public byte[] generateDigestFromFile(File file, String str) throws IOException, NoSuchAlgorithmException {
        Throwable th;
        FileInputStream fileInputStream;
        DigestInputStream digestInputStream;
        MessageDigest instance = MessageDigest.getInstance(str);
        DigestInputStream digestInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                digestInputStream = new DigestInputStream(fileInputStream, instance);
            } catch (Throwable th2) {
                th = th2;
                Closeables.close(fileInputStream, false);
                Closeables.close(digestInputStream2, false);
                throw th;
            }
            try {
                while (digestInputStream.read(new byte[8192]) != -1) {
                }
                byte[] digest = instance.digest();
                Closeables.close(fileInputStream, false);
                Closeables.close(digestInputStream, false);
                return digest;
            } catch (Throwable th3) {
                th = th3;
                digestInputStream2 = digestInputStream;
                Closeables.close(fileInputStream, false);
                Closeables.close(digestInputStream2, false);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
            Closeables.close(fileInputStream, false);
            Closeables.close(digestInputStream2, false);
            throw th;
        }
    }

    public String getFileHash(File file, String str) throws IOException, NoSuchAlgorithmException {
        if (HASH_SIZE_MAP.containsKey(str)) {
            String bigInteger = new BigInteger(1, generateDigestFromFile(file, str)).toString(16);
            String format = String.format(Locale.US, "%%%ds", HASH_SIZE_MAP.get(str));
            return String.format(Locale.US, format, bigInteger).replace(' ', '0');
        }
        throw new NoSuchAlgorithmException("not supported");
    }

    public String makeSHA1HashBase64(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance(HashHelper.SHA1);
            instance.update(bArr, 0, bArr.length);
            return convertToBase64(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertToBase64(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }
}
