package com.esys.registrationscanner.data.network;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

public class HostSelectionInterceptor implements Interceptor {
    private volatile String host;

    public void setHost(String host) {
        this.host = HttpUrl.parse(host).host();
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String reqUrl = request.url().host();

        String host = this.host;
        if (host != null) {
            HttpUrl newUrl = request.url().newBuilder()
                    .host(host)
                    .build();
            request = request.newBuilder()
                    .url(newUrl)
                    .build();
        }
        return chain.proceed(request);
    }
}
