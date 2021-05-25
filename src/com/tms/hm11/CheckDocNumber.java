package com.tms.hm11;
/**
 * Программа проверяет на валидность номера документов, которые считывает из файла. Валидные записывает в один файл, невалидные -
 * во второй и указывает причину невалидности.
 */

import java.io.*;
import java.util.Scanner;

public class CheckDocNumber {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Укажите путь к файлу с номерами документов");
        String way = in.next();

        try(BufferedReader docNumber = new BufferedReader(new FileReader(way));
            BufferedWriter docNumberCorrect = new BufferedWriter(new FileWriter("docNumberCorrect.txt"));
            BufferedWriter docNumberNoCorrect = new BufferedWriter(new FileWriter("docNumberNoCorrect.txt"));){
            
            String line;
            while ((line = docNumber.readLine()) != null){
                if (isSizeCorrect(line)){
                    if (isStartCorrect(line)){
                        if (isOnlyLettersAndNum(line)){
                            docNumberCorrect.write(line + "\n");
                        } else {
                            docNumberNoCorrect.write(line  + " - номер документа содержит не только буквы и цифры.\n");
                        }
                    } else {
                        docNumberNoCorrect.write(line + " - документ начинается неправильно.\n");
                    }
                } else if (isSizeMore(line)){
                    docNumberNoCorrect.write(line + " - размер больше 15 символов.\n");
                } else {
                    docNumberNoCorrect.write(line + "- размер меньше 15 символов.\n");
                }
            }
        }catch (IOException e){
            e.getMessage();
        }

        in.close();
    }

    private static boolean isSizeCorrect(String docNumber){
        if (docNumber.length() == 15){
            return true;
        } else {
            return false;
        }
    }

    private static boolean isSizeMore(String docNumber){
        if (docNumber.length() > 15){
            return true;
        } else {
            return false;
        }
    }
    private static boolean isStartCorrect(String docNumber){
        if (docNumber.substring(0,6).equals("docnum") || docNumber.substring(0,8).equals("kontract")){
            return true;
        } else {
            return false;
        }
    }

    private static boolean isOnlyLettersAndNum(String docNumber){
        if (docNumber.matches("[A-Za-z_0-9]+")){
            return true;
        } else {
            return false;
        }
    }

}
