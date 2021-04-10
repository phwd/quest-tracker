package com.facebook.gk.store;

import com.facebook.common.iolite.Closeables;
import com.facebook.debug.log.BLog;
import com.facebook.gk.store.AtomicFileHelper;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
class NamesFileSerializer implements AtomicFileHelper.FileSerializer<NamesFileContent> {
    private static final int CURRENT_VERSION = 1;
    private static final String GK_NAMES_SIGNATURE = "GK_NAMES";
    private static final String TAG = "NamesFileSerializer";

    NamesFileSerializer() {
    }

    public void write(File file, NamesFileContent namesFileContent) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        boolean z = true;
        try {
            dataOutputStream.writeUTF(GK_NAMES_SIGNATURE);
            dataOutputStream.writeInt(z ? 1 : 0);
            dataOutputStream.writeUTF(namesFileContent.gatekeeperNamesHash);
            int size = namesFileContent.gatekeeperNames.size();
            dataOutputStream.writeInt(size);
            boolean z2 = false;
            for (int i = 0; i < size; i++) {
                dataOutputStream.writeUTF(namesFileContent.gatekeeperNames.get(i));
            }
        } finally {
            Closeables.close(dataOutputStream, z);
        }
    }

    @Override // com.facebook.gk.store.AtomicFileHelper.FileSerializer
    @Nullable
    public NamesFileContent read(File file) throws IOException {
        Throwable th;
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        boolean z = false;
        try {
            String readUTF = dataInputStream.readUTF();
            if (!GK_NAMES_SIGNATURE.equals(readUTF)) {
                BLog.e(TAG, "Cannot read gatekeepers, invalid signature: %s", readUTF);
                Closeables.close(dataInputStream, true);
                return null;
            }
            int readInt = dataInputStream.readInt();
            if (readInt != 1) {
                BLog.wtf(TAG, "Cannot read gatekeepers, invalid version: %s", Integer.valueOf(readInt));
                Closeables.close(dataInputStream, true);
                return null;
            }
            String readUTF2 = dataInputStream.readUTF();
            int readInt2 = dataInputStream.readInt();
            ArrayList arrayList = new ArrayList(readInt2);
            for (int i = 0; i < readInt2; i++) {
                arrayList.add(dataInputStream.readUTF());
            }
            try {
                NamesFileContent namesFileContent = new NamesFileContent(readUTF2, arrayList);
                Closeables.close(dataInputStream, false);
                return namesFileContent;
            } catch (Throwable th2) {
                th = th2;
                z = true;
                Closeables.close(dataInputStream, !z);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            Closeables.close(dataInputStream, !z);
            throw th;
        }
    }
}
