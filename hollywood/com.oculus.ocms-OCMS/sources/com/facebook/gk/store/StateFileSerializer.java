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
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
class StateFileSerializer implements AtomicFileHelper.FileSerializer<StateFileContent> {
    private static final int CURRENT_VERSION = 1;
    private static final String STATE_SIGNATURE = "GK_STATE";
    private static final String TAG = "StateFileSerializer";

    StateFileSerializer() {
    }

    public void write(File file, StateFileContent stateFileContent) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file), 1024));
        boolean z = true;
        try {
            dataOutputStream.writeUTF(STATE_SIGNATURE);
            dataOutputStream.writeInt(z ? 1 : 0);
            dataOutputStream.writeUTF(stateFileContent.gatekeeperNamesHash);
            dataOutputStream.writeInt(stateFileContent.gatekeeperStates.length);
            dataOutputStream.write(stateFileContent.gatekeeperStates);
            boolean z2 = false;
        } finally {
            Closeables.close(dataOutputStream, z);
        }
    }

    @Override // com.facebook.gk.store.AtomicFileHelper.FileSerializer
    @Nullable
    public StateFileContent read(File file) throws IOException {
        Throwable th;
        DataInputStream dataInputStream;
        boolean z = false;
        try {
            dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
            try {
                String readUTF = dataInputStream.readUTF();
                if (!STATE_SIGNATURE.equals(readUTF)) {
                    BLog.wtf(TAG, "Cannot read gatekeepers state, invalid signature: %s", readUTF);
                    Closeables.close(dataInputStream, true);
                    return null;
                }
                int readInt = dataInputStream.readInt();
                if (readInt != 1) {
                    BLog.wtf(TAG, "Cannot read gatekeepers state, invalid version: %s", Integer.valueOf(readInt));
                    Closeables.close(dataInputStream, true);
                    return null;
                }
                String readUTF2 = dataInputStream.readUTF();
                byte[] bArr = new byte[dataInputStream.readInt()];
                dataInputStream.readFully(bArr);
                BLog.d(TAG, "GK file version: %s; number of gatekeepers: %s; hash: %s", Integer.valueOf(readInt), Integer.valueOf(bArr.length), readUTF2);
                try {
                    StateFileContent stateFileContent = new StateFileContent(readUTF2, bArr);
                    Closeables.close(dataInputStream, false);
                    return stateFileContent;
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
        } catch (Throwable th4) {
            th = th4;
            dataInputStream = null;
            Closeables.close(dataInputStream, !z);
            throw th;
        }
    }
}
