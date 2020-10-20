package com.automation.qa.util;

import org.json.simple.*;
import org.json.simple.parser.*;
import org.python.util.PythonInterpreter;

import java.io.File;

public class ReportGenerate {


    public static void generateHtmlReport(String desinationPath) {
//       public static void main(String arg[]){
//           File source = new File("target/allure-results/");
//           String reportPath =source.getAbsolutePath();
//           String desinationPath ="C:/Workspace/LATEST/CUCUMBER_project/target/HTML_REPORTS/report.html";

            String[] arguments = {"PythonMain.py", desinationPath};
            PythonInterpreter.initialize(System.getProperties(), System.getProperties(), arguments);
            PythonInterpreter interpreter = new PythonInterpreter();
            try {
                interpreter.execfile("src/test/resources/PythonMain.py");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//
//    public static void generateHtmlReport() {
//        PythonInterpreter interpreter = new PythonInterpreter();
//        try {
//            interpreter.execfile("src/test/resources/PythonMain.py");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }















}
