package com.oculus.vrcast.googlecast;

import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import com.google.common.collect.Lists;
import com.oculus.vrcast.googlecast.net.CastV2Device;
import com.oculus.vrcast.googlecast.net.JSONTransactionSession;
import com.oculus.vrcast.googlecast.net.ReceiverPlatformSession;
import fi.iki.elonen.NanoHTTPD;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import oculus.internal.Gatekeeper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleCastMWSBridge extends NanoHTTPD implements Closeable {
    private static final int ANSWER_TIMEOUT_SEC = 3;
    private static final String CHROMECAST_DONGLE_NAME_PREFIX = "chromecast";
    private static final Gatekeeper ENABLE_CHROMECAST_FORCE_H264_FOR_SMART_TVS = new Gatekeeper(34);
    private static final Gatekeeper ENABLE_CHROMECAST_H264_OVERRIDE = new Gatekeeper(32);
    private static final String H264_CODEC_NAME = "h264";
    private static final int LAUNCH_TIMEOUT_SEC = 6;
    private static final String NATIVE_RECEIVER_ID = "DC4AF9FD";
    private static final String NS_WEBRTC = "urn:x-cast:com.google.cast.webrtc";
    private static final String OVRPLATFORM_KEY_ANSWER_SDP = "answer_sdp";
    private static final String OVRPLATFORM_KEY_OFFER_SDP = "offer_sdp";
    private static final String OVRPLATFORM_KEY_RTC_CONNECTION_ID = "rtc_connection_id";
    private static final String OVRPLATFORM_KEY_RTC_SESSION_ID = "rtc_session_id";
    private static final String OVRPLATFORM_OFFER_URI = "/rtc/connections";
    private static final String TAG = "GoogleCastMWSBridge";
    private static final String VP8_CODEC_NAME = "vp8";
    private final GoogleCastDevice mCastDevice;
    private boolean mClosed = false;
    private final CastV2Device mDevice;
    private final GoogleCastParams mParams;
    private final ReceiverPlatformSession mPlatformSession;
    private final JSONTransactionSession mSession;

    public void onFailure(Throwable th) {
    }

    public void onRemoteClose() {
    }

    public GoogleCastMWSBridge(String str, int i, CastV2Device castV2Device, GoogleCastParams googleCastParams, GoogleCastDevice googleCastDevice) throws IOException, TimeoutException, InterruptedException, SessionSetupException {
        super(str, i);
        this.mDevice = castV2Device;
        this.mParams = googleCastParams;
        this.mCastDevice = googleCastDevice;
        this.mPlatformSession = new ReceiverPlatformSession(castV2Device);
        try {
            try {
                this.mSession = new JSONTransactionSession(castV2Device, this.mPlatformSession.launch(NATIVE_RECEIVER_ID).get(6, TimeUnit.SECONDS).transportId, NS_WEBRTC, "seqNum") {
                    /* class com.oculus.vrcast.googlecast.GoogleCastMWSBridge.AnonymousClass1 */

                    @Override // com.oculus.vrcast.googlecast.net.CastV2Session
                    public void onRemoteClose() {
                        GoogleCastMWSBridge.this.onRemoteClose();
                    }
                };
                try {
                    start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
                } catch (IOException e) {
                    this.mSession.close();
                    this.mPlatformSession.close();
                    throw e;
                }
            } catch (IOException e2) {
                this.mPlatformSession.close();
                throw e2;
            }
        } catch (CancellationException e3) {
            throw new RuntimeException("Future cancelled unexpectedly", e3);
        } catch (ExecutionException e4) {
            Throwable cause = e4.getCause();
            if (JSONException.class.isInstance(cause)) {
                throw new SessionSetupException("Failed to launch Native Receiver", cause);
            }
            throw new RuntimeException("Future threw illegal exception", cause);
        } catch (IOException | TimeoutException e5) {
            this.mPlatformSession.close();
            throw e5;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.mClosed) {
            Log.d(TAG, "Attempted to close closed session");
            return;
        }
        stop();
        this.mSession.close();
        this.mPlatformSession.close();
        this.mDevice.close();
        this.mClosed = true;
    }

    public InetSocketAddress getDeviceAddress() {
        return this.mDevice.getAddress();
    }

    private JSONObject doCastOfferAnswer(JSONObject jSONObject) throws IOException, TimeoutException, InterruptedException {
        try {
            return this.mSession.sendRequest(jSONObject).get(3, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            throw new RuntimeException("Future threw illegal exception", e.getCause());
        } catch (CancellationException e2) {
            throw new RuntimeException("Future cancelled unexpectedly", e2);
        }
    }

    private long getSSRC(String str, String str2) {
        String[] split = str.split("\r\n");
        boolean z = false;
        for (String str3 : split) {
            if (str3.startsWith("m=")) {
                z = str3.startsWith(str2 + " ", 2);
            } else if (z && str3.startsWith("a=ssrc:")) {
                return Long.parseLong(str3.substring(7, str3.indexOf(32, 7)));
            }
        }
        return -1;
    }

    private Pair<String, String> getCrypto(String str, String str2) {
        String[] split = str.split("\r\n");
        boolean z = false;
        for (String str3 : split) {
            if (str3.startsWith("m=")) {
                z = str3.startsWith(str2 + " ", 2);
            } else if (z && str3.startsWith("a=crypto:")) {
                String[] split2 = str3.split(" ");
                if (split2.length >= 3 && split2[1].equals("AES_GOOGLECAST_128") && split2[2].startsWith("inline:")) {
                    byte[] decode = Base64.decode(split2[2].substring(7), 0);
                    return new Pair<>(Util.bytesToHex(decode, 0, 16), Util.bytesToHex(decode, 16, 16));
                }
            }
        }
        return null;
    }

    private JSONObject sdpOfferToJson(String str, DeviceCapabilities deviceCapabilities) throws JSONException {
        Pair<String, String> crypto = getCrypto(str, "video");
        Pair<String, String> crypto2 = getCrypto(str, "audio");
        Log.d(TAG, "sdpOfferToJson: sdp = " + str);
        long ssrc = getSSRC(str, "video");
        ArrayList<OfferStreamParams> newArrayList = Lists.newArrayList();
        ArrayList<String> newArrayList2 = Lists.newArrayList();
        if ((!ENABLE_CHROMECAST_H264_OVERRIDE.isEnabled() || ENABLE_CHROMECAST_FORCE_H264_FOR_SMART_TVS.isEnabled()) && (!ENABLE_CHROMECAST_FORCE_H264_FOR_SMART_TVS.isEnabled() || this.mCastDevice.getModelName().toLowerCase().startsWith(CHROMECAST_DONGLE_NAME_PREFIX))) {
            if (deviceCapabilities.supportsVP8) {
                newArrayList2.add(VP8_CODEC_NAME);
            }
            newArrayList2.add(H264_CODEC_NAME);
        } else {
            newArrayList2.add(H264_CODEC_NAME);
        }
        for (String str2 : newArrayList2) {
            newArrayList.add(OfferStreamParams.builder().setGoogleCastParams(this.mParams).setAesKey((String) crypto.first).setAesIvMask((String) crypto.second).setSsrc(ssrc).setCodecName(str2).build());
        }
        JSONArray put = new JSONArray().put(new JSONObject().put("index", 0).put("codecName", "opus").put("rtpProfile", "cast").put("rtpPayloadType", 127).put("ssrc", getSSRC(str, "audio")).put("targetDelay", this.mParams.targetPlayoutDelay).put("aesKey", crypto2.first).put("aesIvMask", crypto2.second).put("timeBase", "1/48000").put("receiverRtcpEventLog", false).put("type", "audio_source").put("bitRate", 32000).put("sampleRate", 48000).put("channels", 2));
        int i = 1;
        for (OfferStreamParams offerStreamParams : newArrayList) {
            put.put(offerStreamParams.toJSONObject().put("index", i));
            i++;
        }
        return new JSONObject().put("type", "OFFER").put("offer", new JSONObject().put("castMode", "mirroring").put("receiverGetStatus", true).put("supportedStreams", put));
    }

    private String jsonAnswerToSdp(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException, SessionSetupException {
        Log.d(TAG, "Json answer: " + jSONObject.toString());
        if (jsonAnswerResultIsOkay(jSONObject)) {
            int i = jSONObject.getJSONObject("answer").getInt("udpPort");
            String hostAddress = getDeviceAddress().getAddress().getHostAddress();
            StringBuilder sb = new StringBuilder();
            sb.append("v=0\r\n");
            sb.append(String.format("o=- %d 2 IN IP4 127.0.0.1\r\n", Integer.valueOf(ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE))));
            sb.append("s=-\r\n");
            sb.append("t=0 0\r\n");
            sb.append("a=group:BUNDLE video audio\r\n");
            sb.append("m=audio 9 RTP/AVPF 127\r\n");
            sb.append(String.format("c=IN IP4 0.0.0.0\r\n", hostAddress));
            sb.append("a=mid:audio\r\n");
            sb.append("a=recvonly\r\n");
            sb.append("a=rtcp-mux\r\n");
            sb.append("a=crypto:2 AES_GOOGLECAST_128 inline:AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=\r\n");
            sb.append("a=rtpmap:127 opus/48000/2\r\n");
            sb.append("a=fmtp:127 stereo=0\r\n");
            sb.append("a=x-cast-header\r\n");
            JSONArray jSONArray = jSONObject.getJSONObject("answer").getJSONArray("sendIndexes");
            int i2 = -1;
            for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                int i4 = jSONArray.getInt(i3);
                if (i4 != 0) {
                    i2 = i4;
                }
            }
            if (i2 != -1) {
                JSONArray jSONArray2 = jSONObject2.getJSONObject("offer").getJSONArray("supportedStreams");
                if (i2 < jSONArray2.length()) {
                    String string = jSONArray2.getJSONObject(i2).getString("codecName");
                    sb.append(String.format("m=video %d RTP/AVPF 96\r\n", Integer.valueOf(i)));
                    sb.append(String.format("c=IN IP4 %s\r\n", hostAddress));
                    sb.append("a=mid:video\r\n");
                    sb.append("a=recvonly\r\n");
                    sb.append("a=rtcp-mux\r\n");
                    sb.append("a=crypto:2 AES_GOOGLECAST_128 inline:AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=\r\n");
                    sb.append(String.format("a=rtpmap:96 %s/90000\r\n", string.toUpperCase()));
                    sb.append("a=x-cast-header\r\n");
                    sb.append(String.format("a=fmtp:96 x-google-max-bitrate=%d\r\n", Integer.valueOf(this.mParams.bitrate)));
                    Log.d(TAG, "answer sdp: " + sb.toString());
                    return sb.toString();
                }
                throw new SessionSetupException("Google Cast device selected an invalid stream index.");
            }
            throw new SessionSetupException("Google Cast device did not select a video stream. It might not support any of our supported codecs.");
        }
        throw new SessionSetupException("Google Cast device rejected our offer");
    }

    private static NanoHTTPD.Response makeTextResponse(NanoHTTPD.Response.IStatus iStatus, String str) {
        return NanoHTTPD.newFixedLengthResponse(iStatus, NanoHTTPD.MIME_PLAINTEXT, str);
    }

    private NanoHTTPD.Response reportFailure(NanoHTTPD.Response.IStatus iStatus, Throwable th) {
        if (!this.mClosed) {
            onFailure(th);
        }
        return makeTextResponse(iStatus, th.getMessage() + "\n");
    }

    private boolean jsonAnswerResultIsOkay(JSONObject jSONObject) throws JSONException {
        return jSONObject.getString("result").equals("ok");
    }

    private DeviceCapabilities getDeviceCapabilities() {
        DeviceCapabilities deviceCapabilities = new DeviceCapabilities();
        try {
            JSONObject doCastOfferAnswer = doCastOfferAnswer(new JSONObject().put("type", "GET_CAPABILITIES"));
            Log.d(TAG, "get_capabilities response: " + doCastOfferAnswer.toString());
            if (!jsonAnswerResultIsOkay(doCastOfferAnswer)) {
                Log.w(TAG, "GET_CAPABILITIES call failed.");
                return deviceCapabilities;
            }
            JSONArray jSONArray = doCastOfferAnswer.getJSONObject("capabilities").getJSONArray("mediaCaps");
            for (int i = 0; i < jSONArray.length(); i++) {
                if (jSONArray.getString(i).equals(VP8_CODEC_NAME)) {
                    deviceCapabilities.supportsVP8 = true;
                }
                if (jSONArray.getString(i).equals(H264_CODEC_NAME)) {
                    deviceCapabilities.supportsH264 = true;
                }
            }
            return deviceCapabilities;
        } catch (IOException | InterruptedException | TimeoutException | JSONException e) {
            Log.e(TAG, "Exception encountered during getDeviceCapabilities: " + e);
            return deviceCapabilities;
        }
    }

    @Override // fi.iki.elonen.NanoHTTPD
    public NanoHTTPD.Response serve(NanoHTTPD.IHTTPSession iHTTPSession) {
        String uri = iHTTPSession.getUri();
        if (!uri.equals(OVRPLATFORM_OFFER_URI)) {
            Log.i(TAG, "Got unexpected request for " + uri);
            return makeTextResponse(NanoHTTPD.Response.Status.NOT_FOUND, "Bad request path\n");
        }
        try {
            JSONObject sdpOfferToJson = sdpOfferToJson(iHTTPSession.getParms().get(OVRPLATFORM_KEY_OFFER_SDP), getDeviceCapabilities());
            try {
                try {
                    try {
                        return makeTextResponse(NanoHTTPD.Response.Status.OK, new JSONObject().put(OVRPLATFORM_KEY_ANSWER_SDP, jsonAnswerToSdp(doCastOfferAnswer(sdpOfferToJson), sdpOfferToJson)).put(OVRPLATFORM_KEY_RTC_CONNECTION_ID, "").put(OVRPLATFORM_KEY_RTC_SESSION_ID, "").toString());
                    } catch (JSONException unused) {
                        return this.reportFailure(NanoHTTPD.Response.Status.INTERNAL_ERROR, new SessionSetupException("Failed to wrap answer SDP in JSON"));
                    }
                } catch (SessionSetupException e) {
                    return reportFailure(NanoHTTPD.Response.Status.INTERNAL_ERROR, e);
                } catch (JSONException e2) {
                    return reportFailure(NanoHTTPD.Response.Status.INTERNAL_ERROR, new SessionSetupException("Invalid answer JSON from Google Cast device", e2));
                }
            } catch (IOException e3) {
                return reportFailure(NanoHTTPD.Response.Status.INTERNAL_ERROR, new SessionSetupException("Failed to get answer from Google Cast device", e3));
            } catch (TimeoutException unused2) {
                return reportFailure(NanoHTTPD.Response.Status.INTERNAL_ERROR, new SessionSetupException("Timed out waiting for answer from Google Cast device"));
            } catch (InterruptedException e4) {
                return reportFailure(NanoHTTPD.Response.Status.INTERNAL_ERROR, e4);
            }
        } catch (JSONException e5) {
            return reportFailure(NanoHTTPD.Response.Status.BAD_REQUEST, new SessionSetupException("Invalid offer SDP from WebRTC", e5));
        }
    }
}
