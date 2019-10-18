package br.com.alura.agenda.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInicializador {

    private final Retrofit retrofit;

    public RetrofitInicializador(){
        retrofit = new Retrofit.Builder().baseUrl("http://179.96.134.58:8080/api/")
                .addConverterFactory(JacksonConverterFactory.create()).build();
    }
}
