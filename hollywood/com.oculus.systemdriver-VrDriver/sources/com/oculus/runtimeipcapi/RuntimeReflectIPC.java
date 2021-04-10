package com.oculus.runtimeipcapi;

import android.util.Log;

public class RuntimeReflectIPC {
    static int RPC_REFLECTION_HELPER_VERSION = 1;
    private static final String TAG = "RuntimeReflectIPCJava";

    public static int GetRPCHandle(String name) {
        return RuntimeReflect.hashString(name);
    }

    public static <T> T CallServerRPC(int clientId, int rpcHandle, Object request, Class<T> rclass) {
        Log.d(TAG, "CallServerRPC");
        Writer writer = new Writer();
        try {
            writer.writeShort(RPC_REFLECTION_HELPER_VERSION);
            writer.writeInt(rpcHandle);
            writer.writeInt(RuntimeReflect.getTypeInfo(request.getClass()).hash);
            RuntimeReflect.serializeObjectToBytes(request, writer);
            writer.flush();
            byte[] responseBytes = RuntimeIPCApi.ipc_CallServerRPC(clientId, writer.toByteArray());
            if (responseBytes == null) {
                Log.d(TAG, "ipc_CallServerRPC FAILED");
                return null;
            }
            Reader reader = new Reader(responseBytes);
            if (reader.readShort() != RPC_REFLECTION_HELPER_VERSION) {
                return null;
            }
            int hash = reader.readInt();
            if (hash == RuntimeReflect.getTypeInfo(rclass).hash) {
                return (T) RuntimeReflect.serializeObjectFromBytes(rclass, reader);
            }
            Log.d(TAG, "CallServerRPC: Hash doesn't match" + RuntimeReflect.getTypeInfo(rclass).hash + " != " + hash);
            return null;
        } catch (Exception ex) {
            Log.d(TAG, "CallServerRPC ex: " + ex);
            return null;
        }
    }
}
