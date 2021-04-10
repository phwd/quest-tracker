package com.facebook.qe.schema;

import android.content.Context;
import com.facebook.common.internal.ByteStreams;
import com.facebook.common.iolite.Closeables;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public final class SchemaFactory {
    private static Schema sSessionedSchema;
    private static Schema sSessionlessSchema;

    private SchemaFactory() {
    }

    public static synchronized Schema newSessionlessSchemaInstance(Context context) {
        Schema schema;
        synchronized (SchemaFactory.class) {
            if (sSessionlessSchema == null) {
                final byte[] readAssetBytes = readAssetBytes(context, "sessionless_index.bin", 48);
                sSessionlessSchema = new Schema() {
                    /* class com.facebook.qe.schema.SchemaFactory.AnonymousClass1 */

                    @Override // com.facebook.qe.schema.Schema
                    public String getHash() {
                        return "0B1DCB0CAD2A6289D925168864CE904E97FDD4DC";
                    }

                    @Override // com.facebook.qe.schema.Schema
                    public int getNumExperiments() {
                        return 0;
                    }

                    @Override // com.facebook.qe.schema.Schema
                    public int getNumSlots() {
                        return 0;
                    }

                    @Override // com.facebook.qe.schema.Schema
                    public byte[] getIndexBin() {
                        return readAssetBytes;
                    }
                };
            }
            schema = sSessionlessSchema;
        }
        return schema;
    }

    public static synchronized Schema newSessionedSchemaInstance(Context context) {
        Schema schema;
        synchronized (SchemaFactory.class) {
            if (sSessionedSchema == null) {
                final byte[] readAssetBytes = readAssetBytes(context, "sessioned_index.bin", 48);
                sSessionedSchema = new Schema() {
                    /* class com.facebook.qe.schema.SchemaFactory.AnonymousClass2 */

                    @Override // com.facebook.qe.schema.Schema
                    public String getHash() {
                        return "0B1DCB0CAD2A6289D925168864CE904E97FDD4DC";
                    }

                    @Override // com.facebook.qe.schema.Schema
                    public int getNumExperiments() {
                        return 0;
                    }

                    @Override // com.facebook.qe.schema.Schema
                    public int getNumSlots() {
                        return 0;
                    }

                    @Override // com.facebook.qe.schema.Schema
                    public byte[] getIndexBin() {
                        return readAssetBytes;
                    }
                };
            }
            schema = sSessionedSchema;
        }
        return schema;
    }

    private static byte[] readAssetBytes(Context context, String str, int i) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(str);
            byte[] byteArray = ByteStreams.toByteArray(inputStream, i);
            Closeables.closeQuietly(inputStream);
            return byteArray;
        } catch (FileNotFoundException unused) {
            byte[] readClasspathResourcesBytes = readClasspathResourcesBytes("/assets/" + str, i);
            Closeables.closeQuietly(inputStream);
            return readClasspathResourcesBytes;
        } catch (IOException e) {
            throw new RuntimeException("IOException encountered while reading asset", e);
        } catch (Throwable th) {
            Closeables.closeQuietly(inputStream);
            throw th;
        }
    }

    private static byte[] readClasspathResourcesBytes(String str, int i) {
        InputStream inputStream = null;
        try {
            inputStream = SchemaFactory.class.getResourceAsStream(str);
            byte[] byteArray = ByteStreams.toByteArray(inputStream, i);
            Closeables.closeQuietly(inputStream);
            return byteArray;
        } catch (IOException e) {
            throw new RuntimeException("IOException encountered while reading resource", e);
        } catch (Throwable th) {
            Closeables.closeQuietly(inputStream);
            throw th;
        }
    }
}
