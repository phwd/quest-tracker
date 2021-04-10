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

public final class Utils {
    public static final int BUFFER_SIZE = 4096;

    public static Response replaceResponseBody(Response response, TypedInput typedInput) {
        return new Response(response.url, response.status, response.reason, response.headers, typedInput);
    }

    public static byte[] streamToBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (inputStream != null) {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static class SynchronousExecutor implements Executor {
        public void execute(Runnable runnable) {
            runnable.run();
        }
    }

    public static <T> void validateServiceClass(Class<T> cls) {
        if (!cls.isInterface()) {
            throw new IllegalArgumentException("Only interface endpoint definitions are supported.");
        } else if (cls.getInterfaces().length > 0) {
            throw new IllegalArgumentException("Interface definitions must not extend other interfaces.");
        }
    }

    public static Request readBodyToBytesIfNecessary(Request request) throws IOException {
        TypedOutput typedOutput = request.body;
        if (typedOutput == null || (typedOutput instanceof TypedByteArray)) {
            return request;
        }
        String mimeType = typedOutput.mimeType();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        typedOutput.writeTo(byteArrayOutputStream);
        return new Request(request.method, request.url, request.headers, new TypedByteArray(mimeType, byteArrayOutputStream.toByteArray()));
    }

    public static Response readBodyToBytesIfNecessary(Response response) throws IOException {
        TypedInput typedInput = response.body;
        if (typedInput == null || (typedInput instanceof TypedByteArray)) {
            return response;
        }
        String mimeType = typedInput.mimeType();
        InputStream in = typedInput.in();
        try {
            return replaceResponseBody(response, new TypedByteArray(mimeType, streamToBytes(in)));
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException unused) {
                }
            }
        }
    }
}
