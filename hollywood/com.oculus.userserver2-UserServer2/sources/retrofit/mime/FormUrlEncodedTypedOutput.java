package retrofit.mime;

import com.facebook.acra.LogCatCollector;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

public final class FormUrlEncodedTypedOutput implements TypedOutput {
    public final ByteArrayOutputStream content = new ByteArrayOutputStream();

    @Override // retrofit.mime.TypedOutput
    public String fileName() {
        return null;
    }

    @Override // retrofit.mime.TypedOutput
    public String mimeType() {
        return "application/x-www-form-urlencoded; charset=UTF-8";
    }

    @Override // retrofit.mime.TypedOutput
    public long length() {
        return (long) this.content.size();
    }

    @Override // retrofit.mime.TypedOutput
    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.content.toByteArray());
    }

    public void addField(String str, String str2) {
        addField(str, true, str2, true);
    }

    public void addField(String str, boolean z, String str2, boolean z2) {
        String str3;
        if (str == null) {
            str3 = "name";
        } else if (str2 != null) {
            if (this.content.size() > 0) {
                this.content.write(38);
            }
            if (z) {
                try {
                    str = URLEncoder.encode(str, LogCatCollector.UTF_8_ENCODING);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (z2) {
                str2 = URLEncoder.encode(str2, LogCatCollector.UTF_8_ENCODING);
            }
            this.content.write(str.getBytes(LogCatCollector.UTF_8_ENCODING));
            this.content.write(61);
            this.content.write(str2.getBytes(LogCatCollector.UTF_8_ENCODING));
            return;
        } else {
            str3 = "value";
        }
        throw new NullPointerException(str3);
    }
}
