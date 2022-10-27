package com.example.udprcu;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Data {
    private String MagicString;
    private int RcuKeyOrNumber;
    private int RcuKey;
    private int TvRadio;
    private int ChannelNumber;
    private int Control;
    private int HotStandby;
    private int Decoding;
    private String ChannelName;

    public Data(String magicString, int rcuKeyOrNumber, int rcuKey, int tvRadio, int channelNumber, int control, int hotStandby, int decoding, String channelName) {
        MagicString = magicString;
        RcuKeyOrNumber = rcuKeyOrNumber;
        RcuKey = rcuKey;
        TvRadio = tvRadio;
        ChannelNumber = channelNumber;
        Control = control;
        HotStandby = hotStandby;
        Decoding = decoding;
        ChannelName = channelName;
    }

    public String getMagicString() {
        return MagicString;
    }

    public void setMagicString(String magicString) {
        MagicString = magicString;
    }

    public int getRcuKeyOrNumber() {
        return RcuKeyOrNumber;
    }

    public void setRcuKeyOrNumber(int rcuKeyOrNumber) {
        RcuKeyOrNumber = rcuKeyOrNumber;
    }

    public int getRcuKey() {
        return RcuKey;
    }

    public void setRcuKey(int rcuKey) {
        RcuKey = rcuKey;
    }

    public int getTvRadio() {
        return TvRadio;
    }

    public void setTvRadio(int tvRadio) {
        TvRadio = tvRadio;
    }

    public int getChannelNumber() {
        return ChannelNumber;
    }

    public void setChannelNumber(int channelNumber) {
        ChannelNumber = channelNumber;
    }

    public int getControl() {
        return Control;
    }

    public void setControl(int control) {
        Control = control;
    }

    public int getHotStandby() {
        return HotStandby;
    }

    public void setHotStandby(int hotStandby) {
        HotStandby = hotStandby;
    }

    public int getDecoding() {
        return Decoding;
    }

    public void setDecoding(int decoding) {
        Decoding = decoding;
    }

    public String getChannelName() {
        return ChannelName;
    }

    public void setChannelName(String channelName) {
        ChannelName = channelName;
    }

    public static byte[] validateData(Data data)  {

        ByteBuffer byteBuffer;

        byte [] magicStringArr = data.getMagicString().getBytes();
        byteBuffer = ByteBuffer.allocate(16);
        byteBuffer.put(magicStringArr);
        magicStringArr = byteBuffer.array();

        //RcuKeyOrNum
        byte [] rcuKeyOrNumArr = getArrayByLittleEndian(data.getRcuKeyOrNumber());
        //RCUKey
        byte [] rcuKeyArr = getArrayByLittleEndian(data.getRcuKey());
        //TvRadio
        byte [] tvRadioArr = getArrayByLittleEndian(data.getTvRadio());
        //chanelNumber
        byte [] chanelNumberArr = getArrayByLittleEndian(data.getChannelNumber());
        //control
        byte [] controlArr = getArrayByLittleEndian(data.getControl());
        //hostStandby
        byte [] hostStandbyArr = getArrayByLittleEndian(data.getHotStandby());
        //decoding
        byte [] decodingArr = getArrayByLittleEndian(data.getDecoding());

        //chanelName
        byte [] chaneNameArr = data.getChannelName().getBytes();
        byteBuffer = ByteBuffer.allocate(32);
        byteBuffer.put(chaneNameArr);
        chaneNameArr = byteBuffer.array();

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            bao.write(magicStringArr);
            bao.write(rcuKeyOrNumArr);
            bao.write(rcuKeyArr);
            bao.write(tvRadioArr);
            bao.write(chanelNumberArr);
            bao.write(controlArr);
            bao.write(hostStandbyArr);
            bao.write(decodingArr);
            bao.write(chaneNameArr);
            bao.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] dataArr = bao.toByteArray();
    return dataArr;
    }
    private static byte[] getArrayByLittleEndian(int num){
        ByteBuffer byteBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putInt(num);
        byte [] arrayLittleEndian = byteBuffer.array();
        return arrayLittleEndian;
    }
}
