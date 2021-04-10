package retrofit.mime;

import com.adobe.xmp.options.PropertyOptions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public final class MultipartTypedOutput implements TypedOutput {
    public static final String DEFAULT_TRANSFER_ENCODING = "binary";
    private final String boundary;
    private final byte[] footer;
    private long length;
    private final List<MimePart> mimeParts;

    /* access modifiers changed from: private */
    public static final class MimePart {
        private final TypedOutput body;
        private final String boundary;
        private boolean isBuilt;
        private final boolean isFirst;
        private final String name;
        private byte[] partBoundary;
        private byte[] partHeader;
        private final String transferEncoding;

        public MimePart(String name2, String transferEncoding2, TypedOutput body2, String boundary2, boolean isFirst2) {
            this.name = name2;
            this.transferEncoding = transferEncoding2;
            this.body = body2;
            this.isFirst = isFirst2;
            this.boundary = boundary2;
        }

        public void writeTo(OutputStream out) throws IOException {
            build();
            out.write(this.partBoundary);
            out.write(this.partHeader);
            this.body.writeTo(out);
        }

        public long size() {
            build();
            if (this.body.length() > -1) {
                return this.body.length() + ((long) this.partBoundary.length) + ((long) this.partHeader.length);
            }
            return -1;
        }

        private void build() {
            if (!this.isBuilt) {
                this.partBoundary = MultipartTypedOutput.buildBoundary(this.boundary, this.isFirst, false);
                this.partHeader = MultipartTypedOutput.buildHeader(this.name, this.transferEncoding, this.body);
                this.isBuilt = true;
            }
        }
    }

    public MultipartTypedOutput() {
        this(UUID.randomUUID().toString());
    }

    MultipartTypedOutput(String boundary2) {
        this.mimeParts = new LinkedList();
        this.boundary = boundary2;
        this.footer = buildBoundary(boundary2, false, true);
        this.length = (long) this.footer.length;
    }

    /* access modifiers changed from: package-private */
    public List<byte[]> getParts() throws IOException {
        List<byte[]> parts = new ArrayList<>(this.mimeParts.size());
        for (MimePart part : this.mimeParts) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            part.writeTo(bos);
            parts.add(bos.toByteArray());
        }
        return parts;
    }

    public void addPart(String name, TypedOutput body) {
        addPart(name, DEFAULT_TRANSFER_ENCODING, body);
    }

    public void addPart(String name, String transferEncoding, TypedOutput body) {
        if (name == null) {
            throw new NullPointerException("Part name must not be null.");
        } else if (transferEncoding == null) {
            throw new NullPointerException("Transfer encoding must not be null.");
        } else if (body == null) {
            throw new NullPointerException("Part body must not be null.");
        } else {
            MimePart part = new MimePart(name, transferEncoding, body, this.boundary, this.mimeParts.isEmpty());
            this.mimeParts.add(part);
            long size = part.size();
            if (size == -1) {
                this.length = -1;
            } else if (this.length != -1) {
                this.length += size;
            }
        }
    }

    public int getPartCount() {
        return this.mimeParts.size();
    }

    @Override // retrofit.mime.TypedOutput
    public String fileName() {
        return null;
    }

    @Override // retrofit.mime.TypedOutput
    public String mimeType() {
        return "multipart/form-data; boundary=" + this.boundary;
    }

    @Override // retrofit.mime.TypedOutput
    public long length() {
        return this.length;
    }

    @Override // retrofit.mime.TypedOutput
    public void writeTo(OutputStream out) throws IOException {
        for (MimePart part : this.mimeParts) {
            part.writeTo(out);
        }
        out.write(this.footer);
    }

    /* access modifiers changed from: private */
    public static byte[] buildBoundary(String boundary2, boolean first, boolean last) {
        try {
            StringBuilder sb = new StringBuilder(boundary2.length() + 8);
            if (!first) {
                sb.append("\r\n");
            }
            sb.append("--");
            sb.append(boundary2);
            if (last) {
                sb.append("--");
            }
            sb.append("\r\n");
            return sb.toString().getBytes("UTF-8");
        } catch (IOException ex) {
            throw new RuntimeException("Unable to write multipart boundary", ex);
        }
    }

    /* access modifiers changed from: private */
    public static byte[] buildHeader(String name, String transferEncoding, TypedOutput value) {
        try {
            StringBuilder headers = new StringBuilder((int) PropertyOptions.HAS_TYPE);
            headers.append("Content-Disposition: form-data; name=\"");
            headers.append(name);
            String fileName = value.fileName();
            if (fileName != null) {
                headers.append("\"; filename=\"");
                headers.append(fileName);
            }
            headers.append("\"\r\nContent-Type: ");
            headers.append(value.mimeType());
            long length2 = value.length();
            if (length2 != -1) {
                headers.append("\r\nContent-Length: ").append(length2);
            }
            headers.append("\r\nContent-Transfer-Encoding: ");
            headers.append(transferEncoding);
            headers.append("\r\n\r\n");
            return headers.toString().getBytes("UTF-8");
        } catch (IOException ex) {
            throw new RuntimeException("Unable to write multipart header", ex);
        }
    }
}
