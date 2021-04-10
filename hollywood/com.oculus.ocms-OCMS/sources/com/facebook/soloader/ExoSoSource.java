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

public final class ExoSoSource extends UnpackingSoSource {
    public ExoSoSource(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.soloader.UnpackingSoSource
    public UnpackingSoSource.Unpacker makeUnpacker() throws IOException {
        return new ExoUnpacker(this);
    }

    private final class ExoUnpacker extends UnpackingSoSource.Unpacker {
        private final FileDso[] mDsos;

        ExoUnpacker(UnpackingSoSource unpackingSoSource) throws IOException {
            boolean z;
            Context context = ExoSoSource.this.mContext;
            File file = new File("/data/local/tmp/exopackage/" + context.getPackageName() + "/native-libs/");
            ArrayList arrayList = new ArrayList();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            String[] supportedAbis = SysUtil.getSupportedAbis();
            int length = supportedAbis.length;
            int i = 0;
            int i2 = 0;
            while (i2 < length) {
                String str = supportedAbis[i2];
                File file2 = new File(file, str);
                if (file2.isDirectory()) {
                    linkedHashSet.add(str);
                    File file3 = new File(file2, "metadata.txt");
                    if (!file3.isFile()) {
                        continue;
                    } else {
                        FileReader fileReader = new FileReader(file3);
                        try {
                            BufferedReader bufferedReader = new BufferedReader(fileReader);
                            while (true) {
                                try {
                                    String readLine = bufferedReader.readLine();
                                    if (readLine == null) {
                                        bufferedReader.close();
                                        fileReader.close();
                                        break;
                                    } else if (readLine.length() != 0) {
                                        int indexOf = readLine.indexOf(32);
                                        if (indexOf != -1) {
                                            String str2 = readLine.substring(i, indexOf) + ".so";
                                            int size = arrayList.size();
                                            int i3 = 0;
                                            while (true) {
                                                if (i3 >= size) {
                                                    z = false;
                                                    break;
                                                } else if (((FileDso) arrayList.get(i3)).name.equals(str2)) {
                                                    z = true;
                                                    break;
                                                } else {
                                                    i3++;
                                                }
                                            }
                                            if (!z) {
                                                String substring = readLine.substring(indexOf + 1);
                                                arrayList.add(new FileDso(str2, substring, new File(file2, substring)));
                                            }
                                            i = 0;
                                        } else {
                                            throw new RuntimeException("illegal line in exopackage metadata: [" + readLine + "]");
                                        }
                                    }
                                } catch (Throwable unused) {
                                }
                            }
                        } catch (Throwable unused2) {
                        }
                    }
                }
                i2++;
                i = 0;
            }
            unpackingSoSource.setSoSourceAbis((String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]));
            this.mDsos = (FileDso[]) arrayList.toArray(new FileDso[arrayList.size()]);
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
                FileInputStream fileInputStream = new FileInputStream(fileDso.backingFile);
                try {
                    return new UnpackingSoSource.InputDso(fileDso, fileInputStream);
                } catch (Throwable th) {
                    fileInputStream.close();
                    throw th;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class FileDso extends UnpackingSoSource.Dso {
        final File backingFile;

        FileDso(String str, String str2, File file) {
            super(str, str2);
            this.backingFile = file;
        }
    }
}
