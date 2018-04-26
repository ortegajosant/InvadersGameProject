package app.invaders.com.invaderscontrol;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by jorte on 15/4/2018.
 */

public class ControlActivity extends AppCompatActivity implements Runnable {

    private TextView levelText1;
    private Sensor acelerometerSensor;
    private SensorManager sensorManager;
    private SensorEventListener sensorEventListener;
    private byte yCoordAcelerometer;
    private byte shotAction;
    private Button shotButton;
    private Bundle extras;
    private ClientControl client;
    private int port;
    private String ipAdress;
    private String data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //_____Inicialización de los atributos_____
        setContentView(R.layout.activity_control);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        extras = getIntent().getExtras();
        shotButton = findViewById(R.id.shotButton);
        this.ipAdress = extras.getString("ipAdress");
        this.port = extras.getInt("port");
        // Acelerómetro:
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        client = ClientControl.getInstance(ipAdress, port);

        levelText1 = (TextView) findViewById(R.id.levelText);

        new Thread(ControlActivity.this).start();

        shotButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStats();
                shotAction = 1;
            }
        });

    }

    private void changeStats() {
        levelText1.setText(client.readMessage());
    }

    public void getAcelerometerCoords() {
        sensorEventListener = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float y = sensorEvent.values[1];
                if (y > 1.5f && yCoordAcelerometer != 1) {
                    yCoordAcelerometer = 1;
                    client.setMove(1);
                    new Thread(ControlActivity.this).start();
                } else if (y < -1.5f && yCoordAcelerometer != 2) {
                    yCoordAcelerometer = 2;
                    client.setMove(2);
                    new Thread(ControlActivity.this).start();
                } else if (y < 1.5f && y > -1.5f && yCoordAcelerometer != 0) {
                    yCoordAcelerometer = 0;
                    client.setMove(0);
                    new Thread(ControlActivity.this).start();
                } else if (shotAction != 0) {
                    client.setShot(shotAction);
                    shotAction = 0;
                    new Thread(ControlActivity.this).start();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
    }

    private void startSensor() {
        sensorManager.registerListener(sensorEventListener, acelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void stopSensor() {
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPause() {
        stopSensor();
        super.onPause();
    }

    @Override
    protected void onResume() {
        startSensor();
        super.onResume();
    }

    @Override
    public void run() {
        getAcelerometerCoords();

        try {
            new Thread(client).start();

        } catch (Exception e) {
            System.out.println("FALLO MAYOR " + e);
        }
    }
}
