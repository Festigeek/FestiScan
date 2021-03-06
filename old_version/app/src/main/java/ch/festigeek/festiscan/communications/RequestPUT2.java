package ch.festigeek.festiscan.communications;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import ch.festigeek.festiscan.interfaces.ICallback;
import ch.festigeek.festiscan.interfaces.IConstant;

public class RequestPUT2 extends Communication<String> implements IConstant{

    private String mToken;
    private String mRequest = "";
    private String mKey;
    private int mValue;

    public RequestPUT2(ICallback<String> callback, String token, String request, String key, int value) {
        setCallback(callback);
        mToken = token;
        mRequest = request;
        mKey = key;
        mValue = value;
    }

    @Override
    protected String communication() {
        StringBuilder body = new StringBuilder();
        try {
            URL url = new URL(mRequest);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "bearer " + mToken);
            connection.setUseCaches(false);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            bw.write("{\"" + mKey + "\": " + mValue + "}");
            bw.flush();
            bw.close();

            int status = connection.getResponseCode();
            InputStream is;
            if (status == HttpURLConnection.HTTP_OK) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
            String line;
            while ((line = br.readLine()) != null) {
                body.append(line + "\n");
            }
            br.close();
            if (status != HttpURLConnection.HTTP_OK) {
                setException(new Exception(body.toString()));
            }
        } catch (IOException e) {
            setException(e);
        }
        return body.toString();
    }
}