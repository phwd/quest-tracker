package com.oculus.localmedia.metadata;

import X.AnonymousClass006;
import android.util.Xml;
import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import com.coremedia.iso.boxes.MovieBox;
import com.coremedia.iso.boxes.TrackBox;
import com.coremedia.iso.boxes.UnknownBox;
import com.coremedia.iso.boxes.UserBox;
import com.googlecode.mp4parser.AbstractContainerBox;
import com.oculus.localmedia.metadata.SphericalVideoMetadata;
import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SphericalVideoMetadataParser {
    public static final boolean ENABLED = true;
    public static final byte[] SPHERICAL_BOX_UUID = {-1, -52, -126, 99, -8, 85, 74, -109, -120, 20, 88, 122, 2, 82, 31, -35};
    public static final String SPHERICAL_VIDEO_BOX_NAME = "sv3d";
    public static final boolean SPHERICAL_VIDEO_HEADER_V1_VR180_PROJECTION_SUPPORTED = false;
    public static final String SPHERICAL_VIDEO_STEREO_BOX_NAME = "st3d";

    public static SphericalVideoMetadata parseSphericalVideoV2(String str) {
        SphericalVideoMetadata sphericalVideoMetadata;
        try {
            System.currentTimeMillis();
            MovieBox movieBox = new IsoFile(str).getMovieBox();
            String parseStereoMode = parseStereoMode(movieBox);
            String parseProjectionType = parseProjectionType(movieBox);
            if (parseProjectionType != null) {
                SphericalVideoMetadata.Builder builder = new SphericalVideoMetadata.Builder();
                builder.mFormat = parseStereoMode;
                builder.mProjectionType = parseProjectionType;
                sphericalVideoMetadata = builder.build();
            } else {
                sphericalVideoMetadata = null;
            }
            System.currentTimeMillis();
            return sphericalVideoMetadata;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String parseProjectionType(AbstractContainerBox abstractContainerBox) {
        UnknownBox firstBox = getFirstBox(abstractContainerBox, SPHERICAL_VIDEO_BOX_NAME);
        if (firstBox == null) {
            return null;
        }
        ByteBuffer data = firstBox.getData();
        data.rewind();
        byte[] bArr = new byte[4];
        while (data.hasRemaining()) {
            data.get(bArr);
            byte b = bArr[0];
            if (b == 99 && bArr[1] == 98 && bArr[2] == 109 && bArr[3] == 112) {
                return "CUBEMAP";
            }
            if (b == 101) {
                if (bArr[1] == 113 && bArr[2] == 117 && bArr[3] == 105) {
                    return "EQUIRECTANGULAR";
                }
            } else if (b == 109 && bArr[1] == 115 && bArr[2] == 104 && bArr[3] == 112) {
                return "VR180";
            }
        }
        return null;
    }

    public static String parseStereoMode(AbstractContainerBox abstractContainerBox) {
        UnknownBox firstBox = getFirstBox(abstractContainerBox, SPHERICAL_VIDEO_STEREO_BOX_NAME);
        if (firstBox == null) {
            return "2D";
        }
        ByteBuffer data = firstBox.getData();
        data.rewind();
        byte b = data.get(4);
        if (b == 1) {
            return "3DTB";
        }
        if (b == 2) {
            return "3DLR";
        }
        return "2D";
    }

    public static void dump(AbstractContainerBox abstractContainerBox, String str) {
        for (AbstractContainerBox abstractContainerBox2 : abstractContainerBox.getBoxes()) {
            abstractContainerBox2.getType();
            if (abstractContainerBox2 instanceof AbstractContainerBox) {
                dump(abstractContainerBox2, AnonymousClass006.A07(str, "    "));
            }
        }
    }

    public static Box getFirstBox(Container container, String str) {
        for (Box box : container.getBoxes()) {
            if (box.getType().equalsIgnoreCase(str) || ((box instanceof Container) && (box = getFirstBox((Container) box, str)) != null)) {
                return box;
            }
        }
        return null;
    }

    public static SphericalVideoMetadata parse(String str) {
        if (!str.toLowerCase().endsWith(".mp4")) {
            return null;
        }
        SphericalVideoMetadata parseSphericalVideoV2 = parseSphericalVideoV2(str);
        if (parseSphericalVideoV2 == null) {
            return parseSphericalVideoV1(str);
        }
        return parseSphericalVideoV2;
    }

    public static SphericalVideoMetadata parseSphericalVideoV1(String str) {
        try {
            System.currentTimeMillis();
            for (TrackBox trackBox : new IsoFile(str).getMovieBox().getBoxes(TrackBox.class)) {
                Iterator it = trackBox.getBoxes(UserBox.class).iterator();
                while (true) {
                    if (it.hasNext()) {
                        UserBox userBox = (UserBox) it.next();
                        if (Arrays.equals(userBox.getUserType(), SPHERICAL_BOX_UUID)) {
                            String str2 = new String(userBox.getData(), "UTF-8");
                            XmlPullParser newPullParser = Xml.newPullParser();
                            StringReader stringReader = new StringReader(str2);
                            newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
                            newPullParser.setInput(stringReader);
                            SphericalVideoMetadata.Builder builder = new SphericalVideoMetadata.Builder();
                            while (newPullParser.next() != 3) {
                                if (newPullParser.getEventType() == 2) {
                                    String name = newPullParser.getName();
                                    if (!name.equals("rdf:SphericalVideo")) {
                                        if (name.equals("GSpherical:Spherical")) {
                                            if (Boolean.parseBoolean(readText(newPullParser))) {
                                                builder.mProjectionType = "EQUIRECTANGULAR";
                                            }
                                        } else if (name.equals("GSpherical:StereoMode")) {
                                            String readText = readText(newPullParser);
                                            if (readText.equalsIgnoreCase("mono")) {
                                                builder.mFormat = "2D";
                                            } else if (readText.equalsIgnoreCase("left-right")) {
                                                builder.mFormat = "3DLR";
                                            } else if (readText.equalsIgnoreCase("top-bottom")) {
                                                builder.mFormat = "3DTB";
                                            }
                                        } else if (name.equals("GSpherical:CroppedAreaImageWidthPixels")) {
                                            Integer.parseInt(readText(newPullParser));
                                        } else if (name.equals("GSpherical:FullPanoWidthPixels")) {
                                            Integer.parseInt(readText(newPullParser));
                                        } else {
                                            skip(newPullParser);
                                        }
                                    }
                                }
                            }
                            SphericalVideoMetadata build = builder.build();
                            System.currentTimeMillis();
                            return build;
                        }
                    }
                }
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String readText(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        if (xmlPullParser.next() != 4) {
            return "";
        }
        String text = xmlPullParser.getText();
        xmlPullParser.nextTag();
        return text;
    }

    public static void skip(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.getEventType() == 2) {
            int i = 1;
            while (true) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    i++;
                } else if (next == 3) {
                    i--;
                } else {
                    continue;
                }
                if (i == 0) {
                    return;
                }
            }
        } else {
            throw new IllegalStateException();
        }
    }
}
