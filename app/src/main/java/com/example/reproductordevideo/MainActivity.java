package com.example.reproductordevideo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

    private static final int REQUEST_CODE_PICK_VIDEO = 1;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencias a los componentes de la interfaz
        videoView = findViewById(R.id.video_view);
        Button btnFilePicker = findViewById(R.id.btn_file_picker);

        // Configurar el controlador de medios para el reproductor de video
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // Abrir la galería de medios cuando se haga clic en el botón
        btnFilePicker.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_CODE_PICK_VIDEO);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_VIDEO && resultCode == RESULT_OK) {
            // Obtener la ubicación del archivo de video seleccionado
            Uri videoUri = data.getData();

            // Establecer el archivo de video en el reproductor de video
            videoView.setVideoURI(videoUri);

            // Iniciar la reproducción del video
            videoView.start();
        }
    }
}
