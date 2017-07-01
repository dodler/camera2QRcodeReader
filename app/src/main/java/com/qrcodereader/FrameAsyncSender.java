package com.qrcodereader;

import android.graphics.Bitmap;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import artyom.lyan.sbt.ru.fr.rest.RequestBuilder;
import artyom.lyan.sbt.ru.fr.transformer.Base64BitmapTransformer;

import static artyom.lyan.sbt.ru.fr.rest.RequestBuilder.createConnection;
import static artyom.lyan.sbt.ru.fr.rest.RequestBuilder.param;

/**
 * Created by lyan on 7/1/2017.
 */

class FrameAsyncSender implements Runnable {

    private static final String FITNESS_SERVER_URL = "http://home.namezis.com:5005";
    private Base64BitmapTransformer transformer = new Base64BitmapTransformer();

    public static class RequestResult {
        String errorDesc, status;

        public RequestResult(String errorDesc, String status) {
            this.errorDesc = errorDesc;
            this.status = status;
        }

        static RequestResult fromRequest(String errorDesc, String status) {
            return new RequestResult(errorDesc, status);
        }
    }

    private volatile boolean running;

    public RequestResult sendRequest(Bitmap image) throws IOException, JSONException {
        HttpResponse response = createConnection(FITNESS_SERVER_URL)
                .withMethod(RequestBuilder.Method.AUTHORISE)
                .addParams(param("imageStr", transformer.tranform(image)))
                .buildAndSend();

        String tmp = null;
        try {
            tmp = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonResponse = new JSONObject(tmp);

        String status = jsonResponse.get("status").toString(),
                errorDesc = jsonResponse.get("errorDescription").toString();
        return RequestResult.fromRequest(errorDesc, status);
    }

    private final BlockingQueue<Bitmap> queue = new LinkedBlockingDeque<>();

    @Override
    public void run() {
        this.running = true;
        while (this.running) {
            try {
                RequestResult result =
                        sendRequest(queue.poll(1, TimeUnit.SECONDS));

                Log.d(MainActivity.TAG, result.status);

            } catch (InterruptedException e) {
                this.running = false;
                e.printStackTrace();
            } catch (JSONException e) {
                Log.d(MainActivity.TAG, e.getLocalizedMessage());
            } catch (IOException e) {
                Log.d(MainActivity.TAG, e.getLocalizedMessage());
            }
        }
    }

    public FrameAsyncSender() {

    }

    public void terminate() {
        this.running = false;
    }
}
