package app.invaders.com.invaderscontrol;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button connectButton;
    private EditText ipText;
    private EditText portText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // Evita que se muestre la barra de tareas
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        connectButton = (Button) findViewById(R.id.connectButton);
        ipText = (EditText) findViewById(R.id.ipText);
        portText = (EditText) findViewById(R.id.portText);

        connectButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(MainActivity.this, ControlActivity.class);
                    int port = Integer.parseInt((portText.getText().toString()));
                    i.putExtra("ipAdress", ipText.getText().toString());
                    i.putExtra("port", port);
                    startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Connection Failed", Toast.LENGTH_LONG);
                }
            }
        });

        getMessage();


    }

    private void getMessage() {

    }
}
