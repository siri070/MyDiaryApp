package com.example.irisb.mydiaryapp;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Collections;

public class TerminData {
    private static ArrayList<ArrayList<String>> dataFromFile= new ArrayList<ArrayList<String>>();
    private ArrayList<File> files = new ArrayList<File>();
    private File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"Termine");
    public TerminData(Context c) {/*
        // String path=  Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"favoriten"+File.separator+"favoriten_data.csv";

        Scanner scanner= null;
        try {
            scanner = new Scanner(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"Termine"+File.separator+"TerminDaten1.txt"));
        } catch (FileNotFoundException e) {
            Log.i("Keine Datei Gefunden",e.toString());
        }
       scanner.useDelimiter("\n");
        dataFromFile = new ArrayList<ArrayList<String>>();
        while (scanner.hasNextLine()){
            String dataInRow = scanner.nextLine();
            String[]dataInRowArray = dataInRow.split(";");

            ArrayList<String> rowDataFromFile = new ArrayList<String>(Arrays.asList(dataInRowArray));
            dataFromFile.add(rowDataFromFile);
        }
        scanner.close();*/
    }
    public static ArrayList<ArrayList<String>> AlleTermine(Context c) {
        if (null == dataFromFile) {
            new TerminData(c);
        }
        return dataFromFile;
    }
    public  ArrayList<ArrayList<String>> data(){
        if(directory.listFiles().length>0){
           files.addAll(Arrays.asList(directory.listFiles()));
           Collections.sort(files);

           int counter=0;
           while(counter<files.size()){
               dataFromFile.add(getDataFromFile(files.get(counter)));
               counter++;
           }
        }
        return dataFromFile;
    }

    public static ArrayList<String> getDataFromFile(File file) {
        ArrayList<String>fileData= new ArrayList<String >();
        try {
            BufferedReader bufferedReader= new BufferedReader(new FileReader(file));
            String zeile;
            while ((zeile= bufferedReader.readLine())!=null){
                String[]dataInRowArray = zeile.split(";");

                ArrayList<String> rowDataFromFile = new ArrayList<String>(Arrays.asList(dataInRowArray));
                fileData.addAll(rowDataFromFile);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileData ;
    }


}
