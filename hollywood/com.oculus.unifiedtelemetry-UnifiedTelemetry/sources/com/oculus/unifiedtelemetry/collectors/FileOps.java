package com.oculus.unifiedtelemetry.collectors;

import X.C00319g;
import X.EnumC00439y;
import X.UM;
import X.UN;
import X.US;
import X.UT;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileOps {
    public static int A00(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            int nextInt = new Scanner(fileInputStream).nextInt();
            fileInputStream.close();
            return nextInt;
        } catch (Throwable unused) {
        }
        throw th;
    }

    public static String A01(File file) throws IOException {
        return new US(new UM(file), StandardCharsets.UTF_8).A01();
    }

    public static void A02(File file, String str) throws IOException {
        UT ut = new UT(new UN(file, new EnumC00439y[0]), StandardCharsets.UTF_8);
        if (str != null) {
            C00319g r2 = new C00319g(C00319g.A03);
            try {
                Writer A00 = ut.A00();
                if (A00 != null) {
                    r2.A02.addFirst(A00);
                }
                A00.append((CharSequence) str);
                A00.flush();
                r2.close();
            } catch (Throwable th) {
                r2.close();
                throw th;
            }
        } else {
            throw null;
        }
    }
}
