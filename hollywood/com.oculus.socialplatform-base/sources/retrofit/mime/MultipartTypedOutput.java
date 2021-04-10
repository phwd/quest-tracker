package retrofit.mime;

import X.AnonymousClass006;
import com.facebook.acra.util.HttpRequestMultipart;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.apache.commons.cli.HelpFormatter;

public final class MultipartTypedOutput implements TypedOutput {
    public static final String DEFAULT_TRANSFER_ENCODING = "binary";
    public final String boundary;
    public final byte[] footer;
    public long length;
    public final List<MimePart> mimeParts;

    public static final class MimePart {
        public final TypedOutput body;
        public final String boundary;
        public boolean isBuilt;
        public final boolean isFirst;
        public final String name;
        public byte[] partBoundary;
        public byte[] partHeader;
        public final String transferEncoding;

        private void build() {
            if (!this.isBuilt) {
                this.partBoundary = MultipartTypedOutput.buildBoundary(this.boundary, this.isFirst, false);
                this.partHeader = MultipartTypedOutput.buildHeader(this.name, this.transferEncoding, this.body);
                this.isBuilt = true;
            }
        }

        public MimePart(String str, String str2, TypedOutput typedOutput, String str3, boolean z) {
            this.name = str;
            this.transferEncoding = str2;
            this.body = typedOutput;
            this.isFirst = z;
            this.boundary = str3;
        }

        public long size() {
            build();
            if (this.body.length() > -1) {
                return this.body.length() + ((long) this.partBoundary.length) + ((long) this.partHeader.length);
            }
            return -1;
        }

        public void writeTo(OutputStream outputStream) throws IOException {
            build();
            outputStream.write(this.partBoundary);
            outputStream.write(this.partHeader);
            this.body.writeTo(outputStream);
        }
    }

    @Override // retrofit.mime.TypedOutput
    public String fileName() {
        return null;
    }

    public static byte[] buildBoundary(String str, boolean z, boolean z2) {
        try {
            StringBuilder sb = new StringBuilder(str.length() + 8);
            if (!z) {
                sb.append(HttpRequestMultipart.LINE_FEED);
            }
            sb.append(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
            sb.append(str);
            if (z2) {
                sb.append(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
            }
            sb.append(HttpRequestMultipart.LINE_FEED);
            return sb.toString().getBytes("UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("Unable to write multipart boundary", e);
        }
    }

    public static byte[] buildHeader(String str, String str2, TypedOutput typedOutput) {
        try {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Content-Disposition: form-data; name=\"");
            sb.append(str);
            String fileName = typedOutput.fileName();
            if (fileName != null) {
                sb.append("\"; filename=\"");
                sb.append(fileName);
            }
            sb.append("\"\r\nContent-Type: ");
            sb.append(typedOutput.mimeType());
            long length2 = typedOutput.length();
            if (length2 != -1) {
                sb.append("\r\nContent-Length: ");
                sb.append(length2);
            }
            sb.append("\r\nContent-Transfer-Encoding: ");
            sb.append(str2);
            sb.append("\r\n\r\n");
            return sb.toString().getBytes("UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("Unable to write multipart header", e);
        }
    }

    public int getPartCount() {
        return this.mimeParts.size();
    }

    public List<byte[]> getParts() throws IOException {
        ArrayList arrayList = new ArrayList(this.mimeParts.size());
        for (MimePart mimePart : this.mimeParts) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            mimePart.writeTo(byteArrayOutputStream);
            arrayList.add(byteArrayOutputStream.toByteArray());
        }
        return arrayList;
    }

    @Override // retrofit.mime.TypedOutput
    public String mimeType() {
        return AnonymousClass006.A07("multipart/form-data; boundary=", this.boundary);
    }

    @Override // retrofit.mime.TypedOutput
    public void writeTo(OutputStream outputStream) throws IOException {
        for (MimePart mimePart : this.mimeParts) {
            mimePart.writeTo(outputStream);
        }
        outputStream.write(this.footer);
    }

    @Override // retrofit.mime.TypedOutput
    public long length() {
        return this.length;
    }

    public MultipartTypedOutput() {
        this(UUID.randomUUID().toString());
    }

    public MultipartTypedOutput(String str) {
        this.mimeParts = new LinkedList();
        this.boundary = str;
        byte[] buildBoundary = buildBoundary(str, false, true);
        this.footer = buildBoundary;
        this.length = (long) buildBoundary.length;
    }

    public void addPart(String str, String str2, TypedOutput typedOutput) {
        if (str == null) {
            throw new NullPointerException("Part name must not be null.");
        } else if (str2 == null) {
            throw new NullPointerException("Transfer encoding must not be null.");
        } else if (typedOutput != null) {
            MimePart mimePart = new MimePart(str, str2, typedOutput, this.boundary, this.mimeParts.isEmpty());
            this.mimeParts.add(mimePart);
            long size = mimePart.size();
            if (size == -1) {
                this.length = -1;
                return;
            }
            long j = this.length;
            if (j != -1) {
                this.length = j + size;
            }
        } else {
            throw new NullPointerException("Part body must not be null.");
        }
    }

    public void addPart(String str, TypedOutput typedOutput) {
        addPart(str, "binary", typedOutput);
    }
}
