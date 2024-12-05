package com.example.appfinal;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttHelper {
    private static final String TAG = "MqttHelper";
    private static final String MQTT_BROKER = "tcp://broker.hivemq.com:1883";
    private static final String MQTT_TOPIC = "informacion/superencino";

    private MqttAndroidClient mqttAndroidClient;

    public MqttHelper(Context context) {
        try {
            String clientId = MqttClient.generateClientId();
            mqttAndroidClient = new MqttAndroidClient(context, MQTT_BROKER, clientId);

            mqttAndroidClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Log.d(TAG, "Conexión perdida con el broker MQTT");
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    Log.d(TAG, "Mensaje recibido: " + new String(message.getPayload()));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    Log.d(TAG, "Mensaje enviado correctamente");
                }
            });

            connect();
        } catch (Exception e) {
            Log.e(TAG, "Error al inicializar el cliente MQTT: " + e.getMessage());
        }
    }

    private void connect() {
        try {
            IMqttToken token = mqttAndroidClient.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "Conectado al broker MQTT");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.e(TAG, "Fallo al conectar al broker MQTT", exception);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error al inicializar MQTT: " + e.getMessage());
        }

    }

    public void publish(String message) {
        try {
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(1);
            mqttAndroidClient.publish(MQTT_TOPIC, mqttMessage);
            Log.d(TAG, "Mensaje publicado: " + message);
        } catch (Exception e) {
            Log.e(TAG, "Error al publicar el mensaje", e);
        }
    }
}
