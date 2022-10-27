package com.example.udprcu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
static DatagramSocket udpSocket;
private String IP ="";
private int PORT = 33180;
private static InetAddress serverAddr;
RcuButton setting;
Context context;
RcuButton btns[];
Pair<Integer, Integer> ViewIDs[] = new Pair[] {
        new Pair(R.id.btn_0, KeyEnum.REM_KEY_0.getValue()),
        new Pair(R.id.btn_1, KeyEnum.REM_KEY_1.getValue()),
        new Pair(R.id.btn_2, KeyEnum.REM_KEY_2.getValue()),
        new Pair(R.id.btn_3, KeyEnum.REM_KEY_3.getValue()),
        new Pair(R.id.btn_4, KeyEnum.REM_KEY_4.getValue()),
        new Pair(R.id.btn_5, KeyEnum.REM_KEY_5.getValue()),
        new Pair(R.id.btn_6, KeyEnum.REM_KEY_6.getValue()),
        new Pair(R.id.btn_7, KeyEnum.REM_KEY_7.getValue()),
        new Pair(R.id.btn_8, KeyEnum.REM_KEY_8.getValue()),
        new Pair(R.id.btn_9, KeyEnum.REM_KEY_9.getValue()),
        new Pair(R.id.btn_mute, KeyEnum.REM_KEY_MUTE.getValue()),
        new Pair(R.id.btn_tvr, KeyEnum.REM_KEY_TVRAD.getValue()),
        new Pair(R.id.btn_info, KeyEnum.REM_KEY_INFO.getValue()),
        new Pair(R.id.btn_fav, KeyEnum.REM_KEY_FAV.getValue()),
        new Pair(R.id.btn_rewind, KeyEnum.REM_KEY_REW.getValue()),
        new Pair(R.id.btn_forward, KeyEnum.REM_KEY_FF.getValue()),
        new Pair(R.id.btn_audio, KeyEnum.REM_KEY_AUDIO.getValue()),
        new Pair(R.id.btn_media, KeyEnum.REM_KEY_MEDIA.getValue()),
        new Pair(R.id.btn_red, KeyEnum.REM_KEY_RED.getValue()),
        new Pair(R.id.btn_green, KeyEnum.REM_KEY_GREEN.getValue()),
        new Pair(R.id.btn_yellow, KeyEnum.REM_KEY_YELLOW.getValue()),
        new Pair(R.id.btn_blue, KeyEnum.REM_KEY_BLUE.getValue()),
        new Pair(R.id.btn_Direction, KeyEnum.REM_KEY_DIRECT.getValue()),
        new Pair(R.id.btn_menu, KeyEnum.REM_KEY_MENU.getValue()),
        new Pair(R.id.btn_volumeUp, KeyEnum.REM_KEY_VOUP.getValue()),
        new Pair(R.id.btn_volumeDown, KeyEnum.REM_KEY_VODN.getValue()),
        new Pair(R.id.btn_record, KeyEnum.REM_KEY_REC.getValue()),
        new Pair(R.id.btn_power, KeyEnum.REM_KEY_POWR.getValue()),
        new Pair(R.id.btn_chanelUp, KeyEnum.REM_KEY_CHUP.getValue()),
        new Pair(R.id.btn_chanelDown, KeyEnum.REM_KEY_CHDN.getValue()),
        new Pair(R.id.btn_play_pause, KeyEnum.REM_KEY_PLAY.getValue()),
        new Pair(R.id.btn_exit, KeyEnum.REM_KEY_EXIT.getValue()),
        new Pair(R.id.btn_last, KeyEnum.REM_KEY_LAST.getValue()),
        new Pair(R.id.btn_help, KeyEnum.REM_KEY_HELP.getValue()),
        new Pair(R.id.btn_epg, KeyEnum.REM_KEY_EPG.getValue()),
        new Pair(R.id.btn_txt, KeyEnum.REM_KEY_TEXT.getValue()),
        new Pair(R.id.btn_back, KeyEnum.REM_KEY_BACK.getValue()),
        new Pair(R.id.btn_portal, KeyEnum.REM_KEY_PORTAL.getValue()),
        new Pair(R.id.btn_pageUp, KeyEnum.REM_KEY_PGUP.getValue()),
        new Pair(R.id.btn_pageDown, KeyEnum.REM_KEY_PGDN.getValue()),
        new Pair(R.id.btn_stop, KeyEnum.REM_KEY_STOP.getValue()),
        new Pair(R.id.btn_option, KeyEnum.REM_KEY_OPTION.getValue())
};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        displayAlertDialog();
    }

    private void initView() {
        btns = new RcuButton[ViewIDs.length];
        for ( int i = 0; i < ViewIDs.length; i++ )
        {
            btns[i] = findViewById(ViewIDs[i].first);
            btns[i].setOnTouchListener(ViewIDs[i].second);
        }
    }
    public void displayAlertDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.custom_dialog_input_ip, null);
        final EditText inputIP = (EditText) alertLayout.findViewById(R.id.et_Username);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Input Ip Address!");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            IP = inputIP.getText().toString();
            Log.d("ip",IP);
            connectServer();
                Log.d("ip",IP);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }


    private void connectServer(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        udpSocket = new DatagramSocket(PORT);
                        serverAddr = InetAddress.getByName(IP);
                        Log.d("Status", "connected");
                    } catch (UnknownHostException e) {
                        Log.e("Udp:", "Socket Error:", e);
                    } catch (IOException e) {
                        Log.e("Udp Send:", "IO Error:", e);
                    }
                }
            }).start();
            Toast.makeText(MainActivity.this, "IP inputed!",Toast.LENGTH_SHORT).show();
    }

    public  void sendText(byte[] byteArr) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d("array", Arrays.toString(byteArr));
                    Log.d("IP", IP);
                    DatagramPacket packet = new DatagramPacket(byteArr, byteArr.length, serverAddr, PORT);
                    try {
                        udpSocket.send(packet);
                        Log.d("status", "sent");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

}