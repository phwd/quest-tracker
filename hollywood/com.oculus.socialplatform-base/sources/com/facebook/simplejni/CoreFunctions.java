package com.facebook.simplejni;

import com.facebook.proguard.annotations.DoNotStrip;
import java.io.PrintWriter;
import java.io.StringWriter;

@DoNotStrip
public class CoreFunctions {
    @DoNotStrip
    public static String getErrorDescription(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
