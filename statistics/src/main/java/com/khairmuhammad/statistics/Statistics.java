package com.khairmuhammad.statistics;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.khairmuhammad.dataservices.DataServices;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Handling statistic related operations, generate report, export to excel, generate graph
 * for evaluation, include all necessary statistic algorithms
 *
 * Module Contribution: Oh Shan Ge
 */

public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static void generateExcel(){
        String Fnamexls="testfile"  + ".xls";
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File (sdCard.getAbsolutePath() + "/empcord");
        System.out.println(sdCard.getAbsolutePath()+"--HELLO------------------------------------------");
        directory.mkdirs();

        File file = new File(directory, Fnamexls);

        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook;
        try {
            int a = 1;
            workbook = Workbook.createWorkbook(file, wbSettings);
            WritableSheet sheet = workbook.createSheet("First Sheet", 0);
            Label label = new Label(0, 2, "SECOND");
            Label label1 = new Label(0,1,"first");
            Label label0 = new Label(0,0,"HEADING");
            Label label3 = new Label(1,0,"Heading2");
            Label label4 = new Label(1,1,String.valueOf(a));

            try {
                sheet.addCell(label);
                sheet.addCell(label1);
                sheet.addCell(label0);
                sheet.addCell(label4);
                sheet.addCell(label3);
            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
            workbook.write();

            try {
                workbook.close();
            } catch (WriteException e) {

                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
