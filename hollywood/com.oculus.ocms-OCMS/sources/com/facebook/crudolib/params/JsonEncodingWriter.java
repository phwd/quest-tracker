package com.facebook.crudolib.params;

import com.facebook.infer.annotation.Nullsafe;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class JsonEncodingWriter extends FilterWriter {
    protected JsonEncodingWriter(Writer writer) {
        super(writer);
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(String str, int i, int i2) throws IOException {
        for (int i3 = i; i3 < i + i2; i3++) {
            write(str.charAt(i3));
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(char[] cArr, int i, int i2) throws IOException {
        for (int i3 = i; i3 < i + i2; i3++) {
            write(cArr[i3]);
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(int i) throws IOException {
        writeUnencodedChar(this.out, (char) i);
    }

    public static void writeUnencodedChar(Writer writer, char c) throws IOException {
        if (c == '\f') {
            writer.write(92);
            writer.write(102);
        } else if (c == '\r') {
            writer.write(92);
            writer.write(114);
        } else if (c == '\"' || c == '\\') {
            writer.write(92);
            writer.write(c);
        } else {
            switch (c) {
                case '\b':
                    writer.write(92);
                    writer.write(98);
                    return;
                case '\t':
                    writer.write(92);
                    writer.write(116);
                    return;
                case '\n':
                    writer.write(92);
                    writer.write("n");
                    return;
                default:
                    if (c <= 31 || c == 8232 || c == 8233) {
                        writer.write(String.format("\\u%04x", Integer.valueOf(c)));
                        return;
                    }
                    writer.write(c);
                    return;
            }
        }
    }
}
