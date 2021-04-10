package com.facebook.assistant.oacr;

import X.C0157Eh;
import X.C0374Uk;
import X.C0984q2;
import X.C0992qC;
import X.Ed;
import X.Ei;
import X.Ep;
import X.L9;
import android.content.Context;
import android.os.SystemClock;
import android.util.Base64;
import com.facebook.acra.util.HttpRequestMultipart;
import com.facebook.assistant.oacr.config.OacrTrimSpec;
import com.facebook.hyperthrift.HyperThriftBase;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OacrUtil {
    public static ByteBuffer getResourcesDownloadList(String str, Context context, OacrTrimSpec oacrTrimSpec) {
        byte[] bArr;
        if (!((Boolean) oacrTrimSpec.A00(6)).booleanValue()) {
            return ByteBuffer.allocate(0);
        }
        try {
            InputStream open = context.getResources().getAssets().open(str);
            if (open != null) {
                ArrayDeque arrayDeque = new ArrayDeque(20);
                int i = 0;
                int i2 = HttpRequestMultipart.STREAM_BLOCK_SIZE;
                while (true) {
                    if (i < 2147483639) {
                        byte[] bArr2 = new byte[Math.min(i2, 2147483639 - i)];
                        arrayDeque.add(bArr2);
                        int i3 = 0;
                        while (i3 < bArr2.length) {
                            int read = open.read(bArr2, i3, bArr2.length - i3);
                            if (read == -1) {
                                bArr = new byte[i];
                                int i4 = i;
                                while (i4 > 0) {
                                    byte[] bArr3 = (byte[]) arrayDeque.removeFirst();
                                    int min = Math.min(i4, bArr3.length);
                                    System.arraycopy(bArr3, 0, bArr, i - i4, min);
                                    i4 -= min;
                                }
                            } else {
                                i3 += read;
                                i += read;
                            }
                        }
                        i2 = C0374Uk.A00(((long) i2) * ((long) 2));
                    } else if (open.read() == -1) {
                        bArr = new byte[2147483639];
                        int i5 = 2147483639;
                        while (i5 > 0) {
                            byte[] bArr4 = (byte[]) arrayDeque.removeFirst();
                            int min2 = Math.min(i5, bArr4.length);
                            System.arraycopy(bArr4, 0, bArr, 2147483639 - i5, min2);
                            i5 -= min2;
                        }
                    } else {
                        throw new OutOfMemoryError("input is too large to fit in a byte array");
                    }
                }
                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(bArr.length);
                allocateDirect.put(bArr);
                return (ByteBuffer) allocateDirect.flip();
            }
            throw null;
        } catch (IOException unused) {
            return ByteBuffer.allocate(0);
        }
    }

    public static byte[] base64Decode(String str) {
        int i = 0;
        if (str.startsWith("_")) {
            str = str.substring(1);
            i = 8;
        }
        return Base64.decode(str, i);
    }

    public static long computeAudioDuration(int i, int i2) {
        return (long) (((i * 1000) >> 1) / i2);
    }

    public static List getInitParamsCustomHeaders(Map map) {
        if (map == null) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(map.size() << 1);
        for (Map.Entry entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList.add(entry.getValue());
        }
        return arrayList;
    }

    public static ByteBuffer serialize(String str, HyperThriftBase hyperThriftBase) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Ei.A00(new Ei(Ep.A00().A00, new C0984q2().A2l(new C0992qC(byteArrayOutputStream))), str, hyperThriftBase);
            byteArrayOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(byteArray.length);
            allocateDirect.put(byteArray);
            allocateDirect.flip();
            return allocateDirect;
        } catch (L9 e) {
            throw new RuntimeException(e);
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static long now() {
        return SystemClock.elapsedRealtime();
    }

    public static HyperThriftBase deserialize(String str, ByteBuffer byteBuffer, Ed ed) {
        C0984q2 q2Var = new C0984q2();
        try {
            byte[] bArr = new byte[byteBuffer.limit()];
            byteBuffer.position(0);
            byteBuffer.get(bArr);
            return C0157Eh.A00(new C0157Eh(ed.A00, q2Var.A2l(new C0992qC(new ByteArrayInputStream(bArr))), ed.A01), str);
        } catch (L9 e) {
            throw new RuntimeException("Failed to deserialize payload", e);
        }
    }

    public static HyperThriftBase deserialize(String str, byte[] bArr, Ed ed) {
        try {
            return C0157Eh.A00(new C0157Eh(ed.A00, new C0984q2().A2l(new C0992qC(new ByteArrayInputStream(bArr))), ed.A01), str);
        } catch (L9 e) {
            throw new RuntimeException("Failed to deserialize payload", e);
        }
    }
}
