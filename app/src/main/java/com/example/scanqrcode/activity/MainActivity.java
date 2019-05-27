package com.example.scanqrcode.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scanqrcode.R;
import com.example.scanqrcode.api.APIService;
import com.example.scanqrcode.model.Catraca;
import com.example.scanqrcode.util.ApiUtils;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.camera.CameraSettings;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private Activity activity;

    private APIService mAPIService;

    CameraSettings cameraSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
    }

    public void scanQrCode(View view){
        activity = this;
        IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        cameraSettings = new CameraSettings();
        intentIntegrator.setPrompt("Camera Scan");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setBeepEnabled(true);
        cameraSettings.setAutoFocusEnabled(true);
        cameraSettings.setFocusMode(CameraSettings.FocusMode.MACRO);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null){

            if (result.getContents() != null){
                resultScan(result.getContents());
            } else {
                resultScan("Erro ao ler o QrCode");
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void resultScan(String resultado){
        if (resultado != null){
            textView.setText("Aquarde! Seu processo está em andamento!");
            mAPIService = ApiUtils.getAPIService();
            sendPost(resultado.toString());
        }
    }

    public void sendPost(String codigo) {
        Catraca catraca = new Catraca();
        catraca.setQrCode(codigo);

        Gson gson = new Gson();

        String json = gson.toJson(catraca);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);


        mAPIService.savePost(body).enqueue(new Callback<Catraca>() {

            @Override
            public void onResponse(Call<Catraca> call, Response<Catraca> response) {

                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Toast.makeText(getApplicationContext(), "post submitted to API.", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Catraca> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Unable to submit post to API." , Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showResponse(String response) {
        if (response != null) {
            textView.setText("Liberado!");
        } else{
            textView.setText("Erro no servidor! Tente novamente!");
        }
    }
}
