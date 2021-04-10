package com.oculus.localmedia.metadata;

import android.util.Log;
import android.util.Xml;
import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import com.coremedia.iso.boxes.MovieBox;
import com.coremedia.iso.boxes.TrackBox;
import com.coremedia.iso.boxes.UnknownBox;
import com.coremedia.iso.boxes.UserBox;
import com.googlecode.mp4parser.AbstractContainerBox;
import com.oculus.localmedia.LocalMediaManager;
import com.oculus.localmedia.metadata.SphericalVideoMetadata;
import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SphericalVideoMetadataParser {
    private static final byte[] SPHERICAL_BOX_UUID = {-1, -52, -126, 99, -8, 85, 74, -109, -120, 20, 88, 122, 2, 82, 31, -35};

    public static SphericalVideoMetadata parse(String filePath) {
        if (!filePath.toLowerCase().endsWith(".mp4")) {
            return null;
        }
        SphericalVideoMetadata metadata = parseSphericalVideoV2(filePath);
        if (metadata == null) {
            return parseSphericalVideoV1(filePath);
        }
        return metadata;
    }

    public static SphericalVideoMetadata parseSphericalVideoV1(String filePath) {
        try {
            long startTime = System.currentTimeMillis();
            for (TrackBox trackBox : new IsoFile(filePath).getMovieBox().getBoxes(TrackBox.class)) {
                Iterator it = trackBox.getBoxes(UserBox.class).iterator();
                while (true) {
                    if (it.hasNext()) {
                        UserBox userBox = (UserBox) it.next();
                        if (Arrays.equals(userBox.getUserType(), SPHERICAL_BOX_UUID)) {
                            String str = new String(userBox.getData(), "UTF-8");
                            XmlPullParser parser = Xml.newPullParser();
                            StringReader reader = new StringReader(str);
                            parser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
                            parser.setInput(reader);
                            SphericalVideoMetadata.Builder builder = SphericalVideoMetadata.builder();
                            while (parser.next() != 3) {
                                if (parser.getEventType() == 2) {
                                    String name = parser.getName();
                                    if (!name.equals("rdf:SphericalVideo")) {
                                        if (name.equals("GSpherical:Spherical")) {
                                            if (Boolean.parseBoolean(readText(parser))) {
                                                builder.setProjectionType("EQUIRECTANGULAR");
                                            }
                                        } else if (name.equals("GSpherical:StereoMode")) {
                                            String value = readText(parser);
                                            if (value.equalsIgnoreCase("mono")) {
                                                builder.setFormat("2D");
                                            } else if (value.equalsIgnoreCase("left-right")) {
                                                builder.setFormat("3DLR");
                                            } else if (value.equalsIgnoreCase("top-bottom")) {
                                                builder.setFormat("3DTB");
                                            }
                                        } else if (name.equals("GSpherical:CroppedAreaImageWidthPixels")) {
                                            Integer.parseInt(readText(parser));
                                        } else if (name.equals("GSpherical:FullPanoWidthPixels")) {
                                            Integer.parseInt(readText(parser));
                                        } else {
                                            skip(parser);
                                        }
                                    }
                                }
                            }
                            SphericalVideoMetadata metadata = builder.build();
                            Log.d(LocalMediaManager.TAG, "Video media mp4 V1 header scan took " + (System.currentTimeMillis() - startTime) + " msec, found: " + metadata);
                            return metadata;
                        }
                    }
                }
            }
        } catch (Throwable throwable) {
            Log.d(LocalMediaManager.TAG, "Unexpected error while scanning mp4 header V1 : " + throwable);
        }
        return null;
    }

    public static SphericalVideoMetadata parseSphericalVideoV2(String filePath) {
        try {
            long startTime = System.currentTimeMillis();
            SphericalVideoMetadata metadata = null;
            MovieBox moov = new IsoFile(filePath).getMovieBox();
            String stereoMode = parseStereoMode(moov);
            String projectionType = parseProjectionType(moov);
            if (projectionType != null) {
                metadata = SphericalVideoMetadata.builder().setFormat(stereoMode).setProjectionType(projectionType).build();
            }
            Log.d(LocalMediaManager.TAG, "Video media mp4 header V2 scan took " + (System.currentTimeMillis() - startTime) + " msec, found: " + metadata);
            return metadata;
        } catch (Throwable throwable) {
            Log.d(LocalMediaManager.TAG, "Unexpected error while scanning mp4 header V2 : " + throwable);
            return null;
        }
    }

    private static String parseStereoMode(AbstractContainerBox parent) {
        UnknownBox box = getFirstBox(parent, "st3d");
        if (box != null) {
            ByteBuffer buffer = box.getData();
            buffer.rewind();
            switch (buffer.get(4)) {
                case 1:
                    return "3DTB";
                case 2:
                    return "3DLR";
            }
        }
        return "2D";
    }

    private static String parseProjectionType(AbstractContainerBox parent) {
        UnknownBox box = getFirstBox(parent, "sv3d");
        Log.d(LocalMediaManager.TAG, "parseProjectionType box : " + box);
        if (box != null) {
            ByteBuffer buffer = box.getData();
            buffer.rewind();
            byte[] values = new byte[4];
            while (buffer.hasRemaining()) {
                buffer.get(values);
                if (values[0] == 99 && values[1] == 98 && values[2] == 109 && values[3] == 112) {
                    return "CUBEMAP";
                }
                if (values[0] == 101 && values[1] == 113 && values[2] == 117 && values[3] == 105) {
                    return "EQUIRECTANGULAR";
                }
                if (values[0] == 109 && values[1] == 115 && values[2] == 104 && values[3] == 112) {
                    return "VR180";
                }
            }
        }
        Log.d(LocalMediaManager.TAG, "parseProjectionType return null");
        return null;
    }

    private static Box getFirstBox(Container parent, String type) {
        Box ret;
        for (Box box : parent.getBoxes()) {
            if (box.getType().equalsIgnoreCase(type)) {
                return box;
            }
            if ((box instanceof Container) && (ret = getFirstBox((Container) box, type)) != null) {
                return ret;
            }
        }
        return null;
    }

    private static String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        if (parser.next() != 4) {
            return "";
        }
        String result = parser.getText();
        parser.nextTag();
        return result;
    }

    private static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != 2) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case 2:
                    depth++;
                    break;
                case 3:
                    depth--;
                    break;
            }
        }
    }
}
