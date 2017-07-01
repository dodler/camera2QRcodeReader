package artyom.lyan.sbt.ru.fr.rest;

import android.util.Log;

import com.qrcodereader.MainActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * getFaceDet request to send to server
 * Created by SBT-Lyan-AI on 21.12.2016.
 */
public class RequestBuilder {

    public static final String DEFAULT_URL = "http://bio.baryshnikov.net/regist";
    private String url = DEFAULT_URL;

    private RequestBuilder(String url) {
        this.url = url;
    }

    private Method method = Method.REGISTER;

    public enum Method{
        REGISTER("regist"), AUTHORISE("result");

        private final String method;

        Method(String method) {
            this.method = method;
        }

        @Override
        public String toString(){
            return method;
        }

        public static Method fromSource(String source){

            if (source == null){
                throw new IllegalArgumentException("Can't get method from null");
            }

            if (source.equals(REGISTER.toString())){
                return REGISTER;
            }
            if (source.equals(AUTHORISE.toString())){
                return AUTHORISE;
            }

            throw new IllegalArgumentException("Given source doesn't " +
                    "associate with any method. ");
        }
    }

    public static class HttpParam{
        String key, value;

        HttpParam(String key, String value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString(){
            return key + "=" + value + "&";
        }
    }

    public static HttpParam param(String key, String value){
        return new HttpParam(key,value);
    }

    private JSONObject object;

    public RequestBuilder addParams(HttpParam ...params){

        object = new JSONObject();

        for (HttpParam param : params) {
            try {
                object.put(param.key, param.value);
            } catch (JSONException e) {
                Log.d(MainActivity.TAG, e.getLocalizedMessage());
            }
        }

        return this;
    }

    public RequestBuilder withMethod(Method method){
        this.method = method;
        return this;
    }

    public static RequestBuilder createConnection(String url) throws IOException {
        return new RequestBuilder(url);
    }

    public HttpResponse buildAndSend() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url + method);
        StringEntity params = new StringEntity(object.toString());
        request.addHeader("Content-Type", "application/json");
        request.setEntity(params);
        return client.execute(request);
    }
}
