package com.esys.registrationscanner.di.module;

import com.esys.registrationscanner.data.network.ApiConstants;
import com.esys.registrationscanner.data.network.ApiHelper;
import com.esys.registrationscanner.data.network.ApiInterface;
import com.esys.registrationscanner.data.network.AppApiHelper;
import com.esys.registrationscanner.data.network.HostSelectionInterceptor;
import com.esys.registrationscanner.di.scope.ApplicationScope;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    private static HostSelectionInterceptor mHostSelectionInterceptor;

    public RetrofitModule(HostSelectionInterceptor hostSelectionInterceptor) {
        mHostSelectionInterceptor = hostSelectionInterceptor;
    }

    @Provides
    @ApplicationScope
    static ApiHelper provideApiHelper(ApiInterface apiInterface) {
        return new AppApiHelper(apiInterface);
    }

    @Provides
    @ApplicationScope
    static ApiInterface provideApiInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @ApplicationScope
    static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

     @Provides
     @ApplicationScope
     static HostSelectionInterceptor provideHostSelectionInterceptor() {
         return mHostSelectionInterceptor;
     }


    @Provides
    @ApplicationScope
    static OkHttpClient provideOkHttpClient(HostSelectionInterceptor hostSelectionInterceptor, HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(chain -> {
            Request request = chain.request();

            Request.Builder builder1 = request.newBuilder()
                    .addHeader("Content-Type", ApiConstants.CONTENT_TYPE);
            return chain.proceed(builder1.build());
        })
                .addInterceptor(hostSelectionInterceptor)
                .addInterceptor(httpLoggingInterceptor);

        return builder.build();
    }

    @Provides
    @ApplicationScope
    static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
