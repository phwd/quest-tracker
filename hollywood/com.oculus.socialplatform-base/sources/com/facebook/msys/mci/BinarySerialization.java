package com.facebook.msys.mci;

import X.AnonymousClass0MD;
import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class BinarySerialization {
    @DoNotStrip
    public static native void nativeInitialize();

    @DoNotStrip
    @Nullable
    public static HashMap decode(byte[] bArr) {
        try {
            return (HashMap) new ObjectInputStream(new ByteArrayInputStream(bArr)).readObject();
        } catch (IOException | ClassNotFoundException e) {
            AnonymousClass0MD.A07("BinarySerialization", "Decode failed.", e);
            return null;
        }
    }

    @DoNotStrip
    @Nullable
    public static byte[] encode(HashMap hashMap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream.writeObject(hashMap);
                objectOutputStream.flush();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                objectOutputStream.close();
                return byteArray;
            } catch (Throwable unused) {
            }
            throw th;
        } catch (IOException e) {
            AnonymousClass0MD.A07("BinarySerialization", "Encode failed.", e);
            return null;
        }
    }

    static {
        AnonymousClass1Nh.A00();
    }
}
