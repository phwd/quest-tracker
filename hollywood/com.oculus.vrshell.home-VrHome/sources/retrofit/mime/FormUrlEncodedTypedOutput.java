package retrofit.mime;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

public final class FormUrlEncodedTypedOutput implements TypedOutput {
    final ByteArrayOutputStream content = new ByteArrayOutputStream();

    public void addField(String name, String value) {
        addField(name, true, value, true);
    }

    public void addField(String name, boolean encodeName, String value, boolean encodeValue) {
        if (name == null) {
            throw new NullPointerException("name");
        } else if (value == null) {
            throw new NullPointerException("value");
        } else {
            if (this.content.size() > 0) {
                this.content.write(38);
            }
            if (encodeName) {
                try {
                    name = URLEncoder.encode(name, "UTF-8");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (encodeValue) {
                value = URLEncoder.encode(value, "UTF-8");
            }
            this.content.write(name.getBytes("UTF-8"));
            this.content.write(61);
            this.content.write(value.getBytes("UTF-8"));
        }
    }

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
    public void writeTo(OutputStream out) throws IOException {
        out.write(this.content.toByteArray());
    }
}
