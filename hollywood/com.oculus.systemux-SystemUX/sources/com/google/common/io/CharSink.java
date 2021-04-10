package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.stream.Stream;

@GwtIncompatible
public abstract class CharSink {
    public abstract Writer openStream() throws IOException;

    protected CharSink() {
    }

    public Writer openBufferedStream() throws IOException {
        Writer openStream = openStream();
        return openStream instanceof BufferedWriter ? (BufferedWriter) openStream : new BufferedWriter(openStream);
    }

    public void write(CharSequence charSequence) throws IOException {
        Preconditions.checkNotNull(charSequence);
        Closer create = Closer.create();
        try {
            Writer writer = (Writer) create.register(openStream());
            writer.append(charSequence);
            writer.flush();
            create.close();
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    public void writeLines(Iterable<? extends CharSequence> iterable) throws IOException {
        writeLines(iterable, System.getProperty("line.separator"));
    }

    public void writeLines(Iterable<? extends CharSequence> iterable, String str) throws IOException {
        writeLines(iterable.iterator(), str);
    }

    @Beta
    public void writeLines(Stream<? extends CharSequence> stream) throws IOException {
        writeLines(stream, System.getProperty("line.separator"));
    }

    @Beta
    public void writeLines(Stream<? extends CharSequence> stream, String str) throws IOException {
        writeLines(stream.iterator(), str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
        if (r0 != null) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
        if (r4 != null) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        r4.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0022, code lost:
        r4 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeLines(java.util.Iterator<? extends java.lang.CharSequence> r4, java.lang.String r5) throws java.io.IOException {
        /*
            r3 = this;
            com.google.common.base.Preconditions.checkNotNull(r5)
            java.io.Writer r0 = r3.openBufferedStream()
        L_0x0007:
            r1 = 0
            boolean r2 = r4.hasNext()     // Catch:{ Throwable -> 0x0024 }
            if (r2 == 0) goto L_0x001c
            java.lang.Object r2 = r4.next()     // Catch:{ Throwable -> 0x0024 }
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2     // Catch:{ Throwable -> 0x0024 }
            java.io.Writer r2 = r0.append(r2)     // Catch:{ Throwable -> 0x0024 }
            r2.append(r5)     // Catch:{ Throwable -> 0x0024 }
            goto L_0x0007
        L_0x001c:
            if (r0 == 0) goto L_0x0021
            r0.close()
        L_0x0021:
            return
        L_0x0022:
            r4 = move-exception
            goto L_0x0027
        L_0x0024:
            r4 = move-exception
            r1 = r4
            throw r1     // Catch:{ all -> 0x0022 }
        L_0x0027:
            if (r0 == 0) goto L_0x0037
            if (r1 == 0) goto L_0x0034
            r0.close()     // Catch:{ Throwable -> 0x002f }
            goto L_0x0037
        L_0x002f:
            r5 = move-exception
            r1.addSuppressed(r5)
            goto L_0x0037
        L_0x0034:
            r0.close()
        L_0x0037:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.CharSink.writeLines(java.util.Iterator, java.lang.String):void");
    }

    @CanIgnoreReturnValue
    public long writeFrom(Readable readable) throws IOException {
        Preconditions.checkNotNull(readable);
        Closer create = Closer.create();
        try {
            Writer writer = (Writer) create.register(openStream());
            long copy = CharStreams.copy(readable, writer);
            writer.flush();
            create.close();
            return copy;
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }
}
