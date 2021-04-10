package com.facebook.mobileconfig.metadata;

import android.content.Context;
import com.facebook.common.iolite.Closeables;
import com.facebook.mobileconfig.ota.MobileConfigOTAUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import javax.annotation.Nullable;
import org.apache.commons.cli.HelpFormatter;

public class ParamsMapFactory {
    private static final String PARAMS_MAP_PATH = "params_map.txt";
    public static final String PARAMS_MAP_V2_PREFIX = "v2,";
    public static final String VIRTUAL_GK_PREFIX = "gk_";

    public static String readParamsMapResource(InputStream inputStream, boolean z) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                StringBuffer stringBuffer = new StringBuffer();
                boolean z2 = true;
                boolean z3 = false;
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        if (z2) {
                            boolean startsWith = readLine.startsWith("v2,");
                            if (startsWith) {
                                z3 = startsWith;
                                z2 = false;
                            } else {
                                throw new RuntimeException("ParamsMap currently only supports version 2");
                            }
                        }
                        if (z && isVirtualGKLine(z3, readLine)) {
                            stringBuffer.append(ParamsMapEntry.END_MARKER);
                            stringBuffer.append('\n');
                            break;
                        }
                        stringBuffer.append(readLine);
                        stringBuffer.append('\n');
                    } else {
                        break;
                    }
                }
                String stringBuffer2 = stringBuffer.toString();
                bufferedReader.close();
                return stringBuffer2;
            } catch (Throwable unused) {
            }
            throw th;
        } catch (IOException | RuntimeException e) {
            if (e instanceof RuntimeException) {
                throw new RuntimeException("ParamsMap currently only supports version 2", e);
            }
            throw new RuntimeException("IOException encountered while reading asset", e);
        }
    }

    public static String readMergedParamsMapResource(Context context, @Nullable MobileConfigOTAUtil mobileConfigOTAUtil, boolean z) {
        if (mobileConfigOTAUtil != null) {
            try {
                String mergedParamsMapPath = mobileConfigOTAUtil.getMergedParamsMapPath();
                if (mergedParamsMapPath != null) {
                    return readParamsMapResource(new FileInputStream(new File(mergedParamsMapPath)), z);
                }
            } catch (IOException unused) {
                return readParamsMapResourceFromClassPath();
            }
        }
        return readParamsMapResource(context.getAssets().open(PARAMS_MAP_PATH), z);
    }

    public static String readParamsMapResource(Context context, boolean z) {
        try {
            return readParamsMapResource(context.getAssets().open(PARAMS_MAP_PATH), z);
        } catch (IOException unused) {
            return readParamsMapResourceFromClassPath();
        }
    }

    private static boolean isVirtualGKLine(boolean z, String str) {
        if (!z) {
            return str.startsWith("gk_");
        }
        return str.startsWith("*gk_") || str.startsWith("+") || str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX);
    }

    public static String readParamsMapResourceFromClassPath() {
        try {
            InputStream resourceAsStream = ParamsMapFactory.class.getResourceAsStream("/assets/params_map.txt");
            Scanner useDelimiter = new Scanner(resourceAsStream).useDelimiter("\\A");
            String next = useDelimiter.hasNext() ? useDelimiter.next() : "";
            Closeables.closeQuietly(resourceAsStream);
            return next;
        } catch (Exception e) {
            throw new RuntimeException("IOException encountered while reading resource", e);
        } catch (Throwable th) {
            Closeables.closeQuietly(null);
            throw th;
        }
    }
}
