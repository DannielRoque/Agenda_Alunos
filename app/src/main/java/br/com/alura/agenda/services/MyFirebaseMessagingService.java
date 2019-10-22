package br.com.alura.agenda.services;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import br.com.alura.agenda.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public MyFirebaseMessagingService() {
    }

    @Override
    public void onNewToken(final String token) {
        super.onNewToken(token);
        Log.i("Log Token", "Refreshed token: " + token);

        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(final String token) {
        Call<Void> call = new RetrofitInicializador().getDispositivoService().enviaToken(token);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("Token enviado", token);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Erro ao enviar token", t.getMessage());
            }
        });

    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Map<String, String> mensagem = remoteMessage.getData();
        Log.e("Mensagem recebida", String.valueOf(mensagem));

    }


}
