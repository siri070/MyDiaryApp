package com.example.irisb.mydiaryapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;

import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
public class Terminspeichern {
    private String infos;
    private Context context;
    private String datum;
    private static String TAG = "Termin";
    public Terminspeichern(String i, Context c, String d){
        infos= i ;
        context=c;
        datum= d;
    }
    public boolean save(){


        File fileDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"Termine");
        //èberprüfung ob es den Ordner schon gibt, falls nicht wird er erstellt
        if(!fileDir.exists()){
            try {
                fileDir.mkdir();

            }
            catch (Exception e ){
                Log.v(TAG, e.toString());
            }
        }
        File file = new File(fileDir+File.separator+"TerminDaten"+datum+"-"+System.currentTimeMillis()+".txt");
        try{
            OutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(infos.getBytes());
            fileOutputStream.close();
        }
        catch (FileNotFoundException e){

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Überprüfung ob das File schon existiert, falls nicht wird es erstellt
        if(!file.exists()){
            try{
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);


                BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
                //Daten in das File schreiben

                bufferedWriter.write(infos);
                bufferedWriter.newLine();
                bufferedWriter.close();
                return true;
            }
            catch (Exception e){
                Log.v(TAG, e.toString());
                return false;
            }
        }
        //adf
        if(file.exists()){
            try{

                FileWriter fileWriter = new FileWriter(file);

                     BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
                   //Daten in das File schreiben

                   bufferedWriter.write(infos);
                   bufferedWriter.newLine();
                   bufferedWriter.close();
                /*   PrintWriter writer = new PrintWriter(fileWriter);
                writer.println(infos);

                writer.close();*/
                return true;
            }
            catch (Exception e){
                Log.v(TAG, e.toString());
               return false;
            }

        }
        return false;

    }

    private void error(String fehlermeldung){

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(context);
        helpBuilder.setTitle("Meldung");
        helpBuilder.setMessage(fehlermeldung);
        helpBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });






        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();


    }

}
