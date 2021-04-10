package com.facebook.common.util.exception;

import android.util.JsonWriter;
import androidx.core.app.NotificationCompat;
import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ExceptionUtil {
    public static String getStackTraceString(@Nullable Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            try {
                PrintWriter printWriter = new PrintWriter((Writer) stringWriter, true);
                try {
                    th.printStackTrace(printWriter);
                    printWriter.close();
                    String stringWriter2 = stringWriter.toString();
                    stringWriter.close();
                    return stringWriter2;
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException unused3) {
            return "";
        }
        throw th;
        throw th;
    }

    @Nullable
    public static <T extends Throwable> T unwrap(@Nullable Throwable th, Class<T> cls) {
        while (th != null) {
            if (cls.isInstance(th)) {
                return (T) th;
            }
            th = (T) th.getCause();
        }
        return null;
    }

    public static <T extends Throwable> T unwrapOrThrow(Throwable th, Class<T> cls) {
        Assertions.assertNotNull(th);
        T t = (T) unwrap(th, cls);
        if (t != null) {
            return t;
        }
        throw new RuntimeException("Expected to find a cause ot type " + cls.getName(), th);
    }

    public static String exceptionToJson(Throwable th) {
        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(stringWriter);
            exceptionToJson(jsonWriter, th);
            jsonWriter.close();
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public static void exceptionToJson(JsonWriter jsonWriter, Throwable th) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("excls").value(th.getClass().getName());
        jsonWriter.name(NotificationCompat.CATEGORY_MESSAGE).value(th.getMessage());
        jsonWriter.name("stack");
        jsonWriter.beginArray();
        StackTraceElement[] stackTrace = th.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            jsonWriter.beginObject();
            jsonWriter.name("cls").value(stackTraceElement.getClassName());
            jsonWriter.name("method").value(stackTraceElement.getMethodName());
            jsonWriter.name("ln").value((long) stackTraceElement.getLineNumber());
            jsonWriter.endObject();
        }
        jsonWriter.endArray();
        Throwable cause = th.getCause();
        if (cause != null) {
            jsonWriter.name("cause");
            exceptionToJson(jsonWriter, cause);
        }
        jsonWriter.endObject();
    }

    @Nullable
    public static String getMessage(@Nullable Throwable th) {
        if (th == null) {
            return null;
        }
        return th.getMessage();
    }
}
