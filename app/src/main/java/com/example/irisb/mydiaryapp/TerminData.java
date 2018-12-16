package com.example.irisb.mydiaryapp;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TerminData {
    private static ArrayList<ArrayList<String>> dataFromFile;

    private TerminData(Context c) {
        // String path=  Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"favoriten"+File.separator+"favoriten_data.csv";

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"Termine"+File.separator+"TermineDaten.csv"));
        } catch (FileNotFoundException e) {
            Log.i("Keine Datei Gefunden",e.toString());
        }
        scanner.useDelimiter(";");
        dataFromFile = new ArrayList<ArrayList<String>>();
        while (scanner.hasNext()){
            String dataInRow = scanner.nextLine();
            String[]dataInRowArray = dataInRow.split(";");
            ArrayList<String> rowDataFromFile = new ArrayList<String>(Arrays.asList(dataInRowArray));
            dataFromFile.add(rowDataFromFile);
        }
        scanner.close();
    }
    public static ArrayList<ArrayList<String>> AlleTermine(Context c) {
        if (null == dataFromFile) {
            new TerminData(c);
        }
        return dataFromFile;
    }
}
