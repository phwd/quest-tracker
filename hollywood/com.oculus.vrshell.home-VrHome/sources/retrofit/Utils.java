package retrofit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/* access modifiers changed from: package-private */
public final class Utils {
    private static final int BUFFER_SIZE = 4096;

    static byte[] streamToBytes(InputStream stream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (stream != null) {
            byte[] buf = new byte[4096];
            while (true) {
                int r = stream.read(buf);
                if (r == -1) {
                    break;
                }
                baos.write(buf, 0, r);
            }
        }
        return baos.toByteArray();
    }

    static Request readBodyToBytesIfNecessary(Request request) throws IOException {
        TypedOutput body = request.getBody();
        if (body == null || (body instanceof TypedByteArray)) {
            return request;
        }
        String bodyMime = body.mimeType();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        body.writeTo(baos);
        return new Request(request.getMethod(), request.getUrl(), request.getHeaders(), new TypedByteArray(bodyMime, baos.toByteArray()));
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002a A[SYNTHETIC, Splitter:B:16:0x002a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static retrofit.client.Response readBodyToBytesIfNecessary(retrofit.client.Response r7) throws java.io.IOException {
        /*
            retrofit.mime.TypedInput r0 = r7.getBody()
            if (r0 == 0) goto L_0x000a
            boolean r5 = r0 instanceof retrofit.mime.TypedByteArray
            if (r5 == 0) goto L_0x000b
        L_0x000a:
            return r7
        L_0x000b:
            java.lang.String r3 = r0.mimeType()
            java.io.InputStream r4 = r0.in()
            byte[] r2 = streamToBytes(r4)     // Catch:{ all -> 0x0027 }
            retrofit.mime.TypedByteArray r1 = new retrofit.mime.TypedByteArray     // Catch:{ all -> 0x0027 }
            r1.<init>(r3, r2)     // Catch:{ all -> 0x0027 }
            retrofit.client.Response r7 = replaceResponseBody(r7, r1)     // Catch:{ all -> 0x0032 }
            if (r4 == 0) goto L_0x0025
            r4.close()     // Catch:{ IOException -> 0x002e }
        L_0x0025:
            r0 = r1
            goto L_0x000a
        L_0x0027:
            r5 = move-exception
        L_0x0028:
            if (r4 == 0) goto L_0x002d
            r4.close()     // Catch:{ IOException -> 0x0030 }
        L_0x002d:
            throw r5
        L_0x002e:
            r5 = move-exception
            goto L_0x0025
        L_0x0030:
            r6 = move-exception
            goto L_0x002d
        L_0x0032:
            r5 = move-exception
            r0 = r1
            goto L_0x0028
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit.Utils.readBodyToBytesIfNecessary(retrofit.client.Response):retrofit.client.Response");
    }

    static Response replaceResponseBody(Response response, TypedInput body) {
        return new Response(response.getUrl(), response.getStatus(), response.getReason(), response.getHeaders(), body);
    }

    static <T> void validateServiceClass(Class<T> service) {
        if (!service.isInterface()) {
            throw new IllegalArgumentException("Only interface endpoint definitions are supported.");
        } else if (service.getInterfaces().length > 0) {
            throw new IllegalArgumentException("Interface definitions must not extend other interfaces.");
        }
    }

    static class SynchronousExecutor implements Executor {
        SynchronousExecutor() {
        }

        public void execute(Runnable runnable) {
            runnable.run();
        }
    }

    private Utils() {
    }
}
