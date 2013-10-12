/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DigitsCounterParallel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 *
 * @author lpa
 */
public class DigitsCounter {
    //путь до файла со списком файлов для подсчета количества цифр
    private String pathToListFiles;
    //максимальное количество параллельно работающих потоков
    private int maxCountParallelThreads;
    //список файлов для подсчета количества цифр
    private ArrayList<String> listFiles;
    private int countEvenDigits;
    private int countOddDigits;
    //индекс текущего обрабатываемого файла
    private int currentIndexFile;
    //список исключительных ситуаций, возникших во время выполнения
    private ArrayList<Exception> listExceptions;
    
    public final class DigitsCounterThread extends Thread {
        public DigitsCounterThread(){
            super();
            //запускаем поток на выполнение
            start();
        }
        @Override
        public void run(){
            try{
                System.out.println("!!!!!!!!!!!!");
                String pathToFile;
                pathToFile = getPathToNextFile();
                calculateNumbersInFile(pathToFile);
            }catch(Exception e){
                exceptionThrown(e);
            }
        }
        
        public void calculateNumbersInFile(String pathToFile) throws FileNotFoundException, IOException
        {
            int countEven = 0;
            int countOdd = 0;

            int readedChar;
            InputStream fIS = new FileInputStream(pathToFile.replace("/", "//"));
            Reader reader = new InputStreamReader(fIS);

            while( (readedChar = reader.read()) != -1){
                // 48 - char 0 ; 57 - char 9 
                if(readedChar >= 48 && readedChar <= 57){
                    if(readedChar%2 == 0){
                        countEven += 1;
                    }else{
                        countOdd += 1;
                    }
                }

                //выводим текст файла
                System.out.print((char)readedChar);
            }
            
            System.out.println("countEven = " + countEven);
            System.out.println("countOdd = " + countOdd);
            System.out.println();
            /*
             * добавляем количество четных и нечетных чисел к количеству, 
             * подсчитанному другими потоками
             */
            addCountDigits(countEven, countOdd);
        }
    }
    
    DigitsCounter(String pathToListFiles, int maxCountParallelThreads) {
        this.listFiles = new ArrayList<>();
        this.pathToListFiles = pathToListFiles;
        this.currentIndexFile = -1;
        this.countEvenDigits = 0;
        this.countOddDigits = 0;
        this.listExceptions = new ArrayList<>();
        if(maxCountParallelThreads>0){
            this.maxCountParallelThreads = maxCountParallelThreads;
        }else{
            //throw new exception 
        }
    }
    
    //запуск подсчета количества цифр
    public void run() throws FileNotFoundException, IOException, InterruptedException{
        createListFiles();
        
        for(int i=0; i<listFiles.size(); i++){
            Thread digitCounter = new DigitsCounterThread();
            digitCounter.join();
        }

        System.out.println("countEvenValues = " + countEvenDigits);
        System.out.println("countOddValues = " + countOddDigits);
    }
    
    /*
     * регистрируем выброшенное исключение
     */
    private void exceptionThrown(Exception e){
        listExceptions.add(e);
    }
    
    /*Вычисляет индекс в массиве следующего файла для обработки
     * возвращает индекс файла для обработки
     * -1 если файлы закончились
     */ 
    private int getIndexNextFile(){
        if(currentIndexFile < (listFiles.size()-1)){
            currentIndexFile++;
            return currentIndexFile;
        }else{
            return -1;
        }
    }
    
    private synchronized String getPathToNextFile(){
        int currentIndexFile = getIndexNextFile();
        if(currentIndexFile != -1){
            return listFiles.get(currentIndexFile);
        }else{
            return null;
        }
    }
    
    //Создание массива путей до файлов
    private void createListFiles() throws FileNotFoundException, IOException{
        String readedLine;
        InputStream fIS = new FileInputStream(pathToListFiles);
        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new InputStreamReader(fIS,Charset.forName("UTF-8")));
        
        while((readedLine = bufferedReader.readLine()) != null){
            System.out.println(readedLine);
            listFiles.add(readedLine);            
        }       
    }
    
    /*
     * 
     */
    private synchronized void addCountDigits(int countEven, int countOdd){
        countEvenDigits += countEven;
        countOddDigits += countOdd;
    }
    
    
    
    
    
}
