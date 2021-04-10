package com.oculus.mediadownloader;

import android.text.TextUtils;
import android.util.Log;
import com.oculus.mediadownloader.Schema;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DashManifest {
    final String rawXml;
    private final Node xmlDom;

    public DashManifest(String xml) {
        this.rawXml = xml;
        this.xmlDom = stringToDom(xml);
    }

    public static class LocalManifest {
        private static final Pattern XS_DURATION_PATTERN = Pattern.compile("^(-)?P(([0-9]*)Y)?(([0-9]*)M)?(([0-9]*)D)?(T(([0-9]*)H)?(([0-9]*)M)?(([0-9.]*)S)?)?$");
        public long audioBytesFromDash;
        public String audioUrl;
        public Node dom;
        public long durationMs;
        public long videoBytesFromDash;
        public String videoUrl;

        public String toString() {
            return DashManifest.domToString(this.dom);
        }

        private static long parseXsDuration(String value) {
            Matcher matcher = XS_DURATION_PATTERN.matcher(value);
            if (!matcher.matches()) {
                return (long) (Double.parseDouble(value) * 3600.0d * 1000.0d);
            }
            boolean negated = !TextUtils.isEmpty(matcher.group(1));
            String years = matcher.group(3);
            double durationSeconds = years != null ? Double.parseDouble(years) * 3.1556908E7d : 0.0d;
            String months = matcher.group(5);
            double durationSeconds2 = durationSeconds + (months != null ? Double.parseDouble(months) * 2629739.0d : 0.0d);
            String days = matcher.group(7);
            double durationSeconds3 = durationSeconds2 + (days != null ? Double.parseDouble(days) * 86400.0d : 0.0d);
            String hours = matcher.group(10);
            double durationSeconds4 = durationSeconds3 + (hours != null ? Double.parseDouble(hours) * 3600.0d : 0.0d);
            String minutes = matcher.group(12);
            double durationSeconds5 = durationSeconds4 + (minutes != null ? Double.parseDouble(minutes) * 60.0d : 0.0d);
            String seconds = matcher.group(14);
            long durationMillis = (long) (1000.0d * (durationSeconds5 + (seconds != null ? Double.parseDouble(seconds) : 0.0d)));
            if (negated) {
                return -durationMillis;
            }
            return durationMillis;
        }

        public void setDuration(String duration) {
            this.durationMs = parseXsDuration(duration);
        }
    }

    public LocalManifest createLocalManifest(String uriPrefix) {
        if (this.xmlDom == null) {
            return null;
        }
        try {
            LocalManifest localManifest = new LocalManifest();
            localManifest.dom = this.xmlDom.cloneNode(true);
            NodeList adaptationSets = localManifest.dom.getFirstChild().getChildNodes();
            if (adaptationSets.getLength() != 2) {
                Log.e("Omd.DashManifest", "Expected 2 <AdaptationSet> nodes");
                return null;
            }
            localManifest.setDuration(localManifest.dom.getAttributes().getNamedItem("mediaPresentationDuration").getNodeValue());
            Node videoAdaptationSet = adaptationSets.item(0);
            Node audioAdaptationSet = adaptationSets.item(1);
            if ("audio".equals(videoAdaptationSet.getAttributes().getNamedItem("contentType"))) {
                videoAdaptationSet = audioAdaptationSet;
                audioAdaptationSet = videoAdaptationSet;
            }
            NodeList videoRepresentations = videoAdaptationSet.getChildNodes();
            int maxVideoBandwidth = 0;
            Node maxVideoNode = null;
            for (int i = 0; i < videoRepresentations.getLength(); i++) {
                Node representation = videoRepresentations.item(i);
                int bandwidth = Integer.parseInt(representation.getAttributes().getNamedItem("bandwidth").getNodeValue());
                if (bandwidth > maxVideoBandwidth) {
                    maxVideoNode = representation;
                    maxVideoBandwidth = bandwidth;
                }
            }
            localManifest.videoBytesFromDash = (localManifest.durationMs * ((long) maxVideoBandwidth)) / 8000;
            NodeList children = maxVideoNode.getChildNodes();
            for (int j = 0; j < children.getLength(); j++) {
                Node child = children.item(j);
                if (child.getNodeName().equals("BaseURL")) {
                    localManifest.videoUrl = child.getTextContent();
                    child.setTextContent(uriPrefix + Schema.FileElement.MEDIA0.toString());
                }
            }
            while (videoAdaptationSet.hasChildNodes()) {
                videoAdaptationSet.removeChild(videoAdaptationSet.getFirstChild());
            }
            videoAdaptationSet.appendChild(maxVideoNode);
            if (audioAdaptationSet.getChildNodes().getLength() != 1) {
                Log.w("Omd.DashManifest", "Manifest has more than 1 audio element.");
            }
            Node audioRepresentation = audioAdaptationSet.getFirstChild();
            int audioBandwidth = Integer.parseInt(audioRepresentation.getAttributes().getNamedItem("bandwidth").getNodeValue());
            NodeList children2 = audioRepresentation.getChildNodes();
            for (int i2 = 0; i2 < children2.getLength(); i2++) {
                Node child2 = children2.item(i2);
                if (child2.getNodeName().equals("BaseURL")) {
                    localManifest.audioUrl = child2.getTextContent();
                    child2.setTextContent(uriPrefix + Schema.FileElement.MEDIA1.toString());
                    localManifest.audioBytesFromDash = (localManifest.durationMs * ((long) audioBandwidth)) / 8000;
                    return localManifest;
                }
            }
            return localManifest;
        } catch (DOMException e) {
            Log.e("Omd.DashManifest", "Unable to create local manifest:", e);
            return null;
        }
    }

    public static Node stringToDom(String xml) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setIgnoringElementContentWhitespace(true);
            return dbFactory.newDocumentBuilder().parse(new InputSource(new StringReader(xml))).getDocumentElement();
        } catch (IOException | ParserConfigurationException | SAXException e) {
            Log.e("Omd.DashManifest", "Unable to parse dash.", e);
            return null;
        }
    }

    public static String domToString(Node dom) {
        try {
            DOMSource domSource = new DOMSource(dom);
            StringWriter writer = new StringWriter();
            TransformerFactory.newInstance().newTransformer().transform(domSource, new StreamResult(writer));
            return writer.toString();
        } catch (TransformerException e) {
            Log.e("Omd.DashManifest", "Unable to stringify XML");
            return "";
        }
    }
}
