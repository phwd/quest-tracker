package com.oculus.mobileconfig.init;

import android.content.Context;
import com.facebook.mobileconfig.metadata.ParamsMapFactory;
import com.google.common.collect.ImmutableMap;
import com.oculus.provider.OculusContent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

public class MobileConfigManagerUtil {
    private static final String PARAMS_MAP_PATH = "params_map.txt";

    static String readParamsMapResource(Context context) {
        InputStream inputStream = null;
        try {
            InputStream open = context.getAssets().open(PARAMS_MAP_PATH);
            Scanner useDelimiter = new Scanner(open).useDelimiter("\\A");
            String next = useDelimiter.hasNext() ? useDelimiter.next() : "";
            if (open == null) {
                try {
                    open.close();
                } catch (IOException unused) {
                }
            }
            return next;
        } catch (FileNotFoundException unused2) {
            String readParamsMapResourceFromClassPath = readParamsMapResourceFromClassPath();
            if (0 == 0) {
                try {
                    inputStream.close();
                } catch (IOException unused3) {
                }
            }
            return readParamsMapResourceFromClassPath;
        } catch (Exception e) {
            throw new RuntimeException("IOException encountered while reading asset", e);
        } catch (Throwable th) {
            if (0 == 0) {
                try {
                    inputStream.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
    }

    private static String readParamsMapResourceFromClassPath() {
        InputStream inputStream = null;
        try {
            InputStream resourceAsStream = ParamsMapFactory.class.getResourceAsStream("/assets/params_map.txt");
            Scanner useDelimiter = new Scanner(resourceAsStream).useDelimiter("\\A");
            String next = useDelimiter.hasNext() ? useDelimiter.next() : "";
            if (resourceAsStream == null) {
                try {
                    resourceAsStream.close();
                } catch (IOException unused) {
                }
            }
            return next;
        } catch (Exception e) {
            throw new RuntimeException("IOException encountered while reading resource", e);
        } catch (Throwable th) {
            if (0 == 0) {
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    static ImmutableMap<String, String> createExtraURLParams(Locale locale) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        builder.put(OculusContent.Profile.LOCALE, locale.toString());
        return builder.build();
    }
}
