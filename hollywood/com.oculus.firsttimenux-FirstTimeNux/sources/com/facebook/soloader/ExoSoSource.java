package com.facebook.soloader;

import android.content.Context;
import com.facebook.soloader.UnpackingSoSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public final class ExoSoSource extends UnpackingSoSource {
    public ExoSoSource(Context context, String name) {
        super(context, name);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.soloader.UnpackingSoSource
    public UnpackingSoSource.Unpacker makeUnpacker() throws IOException {
        return new ExoUnpacker(this);
    }

    private final class ExoUnpacker extends UnpackingSoSource.Unpacker {
        private final FileDso[] mDsos;

        ExoUnpacker(UnpackingSoSource soSource) throws IOException {
            File exoDir = new File("/data/local/tmp/exopackage/" + ExoSoSource.this.mContext.getPackageName() + "/native-libs/");
            ArrayList<FileDso> providedLibraries = new ArrayList<>();
            Set<String> librariesAbiSet = new LinkedHashSet<>();
            String[] supportedAbis = SysUtil.getSupportedAbis();
            int length = supportedAbis.length;
            for (int i = 0; i < length; i++) {
                String abi = supportedAbis[i];
                File abiDir = new File(exoDir, abi);
                if (abiDir.isDirectory()) {
                    librariesAbiSet.add(abi);
                    File metadataFileName = new File(abiDir, "metadata.txt");
                    if (metadataFileName.isFile()) {
                        FileReader fr = new FileReader(metadataFileName);
                        try {
                            BufferedReader br = new BufferedReader(fr);
                            while (true) {
                                try {
                                    String line = br.readLine();
                                    if (line == null) {
                                        br.close();
                                        fr.close();
                                        break;
                                    } else if (line.length() != 0) {
                                        int sep = line.indexOf(32);
                                        if (sep == -1) {
                                            throw new RuntimeException("illegal line in exopackage metadata: [" + line + "]");
                                        }
                                        String soName = line.substring(0, sep) + ".so";
                                        int nrAlreadyProvided = providedLibraries.size();
                                        boolean found = false;
                                        int i2 = 0;
                                        while (true) {
                                            if (i2 >= nrAlreadyProvided) {
                                                break;
                                            } else if (providedLibraries.get(i2).name.equals(soName)) {
                                                found = true;
                                                break;
                                            } else {
                                                i2++;
                                            }
                                        }
                                        if (!found) {
                                            String backingFileBaseName = line.substring(sep + 1);
                                            providedLibraries.add(new FileDso(soName, backingFileBaseName, new File(abiDir, backingFileBaseName)));
                                        }
                                    }
                                } catch (Throwable th) {
                                    th.addSuppressed(th);
                                }
                            }
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        continue;
                    }
                }
            }
            soSource.setSoSourceAbis((String[]) librariesAbiSet.toArray(new String[librariesAbiSet.size()]));
            this.mDsos = (FileDso[]) providedLibraries.toArray(new FileDso[providedLibraries.size()]);
            return;
            throw th;
            throw th;
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.soloader.UnpackingSoSource.Unpacker
        public UnpackingSoSource.DsoManifest getDsoManifest() throws IOException {
            return new UnpackingSoSource.DsoManifest(this.mDsos);
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.soloader.UnpackingSoSource.Unpacker
        public UnpackingSoSource.InputDsoIterator openDsoIterator() throws IOException {
            return new FileBackedInputDsoIterator();
        }

        private final class FileBackedInputDsoIterator extends UnpackingSoSource.InputDsoIterator {
            private int mCurrentDso;

            private FileBackedInputDsoIterator() {
            }

            @Override // com.facebook.soloader.UnpackingSoSource.InputDsoIterator
            public boolean hasNext() {
                return this.mCurrentDso < ExoUnpacker.this.mDsos.length;
            }

            @Override // com.facebook.soloader.UnpackingSoSource.InputDsoIterator
            public UnpackingSoSource.InputDso next() throws IOException {
                FileDso[] fileDsoArr = ExoUnpacker.this.mDsos;
                int i = this.mCurrentDso;
                this.mCurrentDso = i + 1;
                FileDso fileDso = fileDsoArr[i];
                FileInputStream dsoFile = new FileInputStream(fileDso.backingFile);
                try {
                    UnpackingSoSource.InputDso ret = new UnpackingSoSource.InputDso(fileDso, dsoFile);
                    dsoFile = null;
                    return ret;
                } finally {
                    if (dsoFile != null) {
                        dsoFile.close();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class FileDso extends UnpackingSoSource.Dso {
        final File backingFile;

        FileDso(String name, String hash, File backingFile2) {
            super(name, hash);
            this.backingFile = backingFile2;
        }
    }
}
