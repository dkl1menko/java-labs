package com.company.klimenko.nsu;

import java.io.File;

public final class ConstantDump {
    private ConstantDump(){
    }

    public static void main(String[] args)  {
        try{
            JavaByteParser.ShowConstant(new File(args[0]));
        }
        catch (Exception e){
            e.printStackTrace();
            System.err.println("Access to class file not allowed or stream error");
        }

    }
}
