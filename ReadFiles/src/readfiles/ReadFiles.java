/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package readfiles;

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
public class ReadFiles {
    private String pathToListFiles;
    private ArrayList<String> listFiles;
    //индекс текущего обрабатываемого файла
    private int currentIndexFile;
    
    ReadFiles(String pathToListFiles) {
        this.listFiles = new ArrayList<>();
        this.pathToListFiles = pathToListFiles;
        this.currentIndexFile = -1;
    }
    
    //запуск подсчета количества цифр
    public void run() throws FileNotFoundException, IOException{
        createListFiles();
        Integer countEven = 0;
        Integer countOdd = 0;
        int currentIndexFile;
        do{
            currentIndexFile = getIndexNextFile();
            if(currentIndexFile != -1){
                calculateNumbersInFile(listFiles.get(currentIndexFile), countEven, countOdd);
                System.out.println("pathToFile = " + listFiles.get(currentIndexFile));
                System.out.println("countEven = " + countEven);
                System.out.println("countOdd = " + countOdd);
            }
        }while(currentIndexFile != -1);
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
    
    public void calculateNumbersInFile(String pathToFile,
                                       Integer countEvenOut, 
                                       Integer countOddOut) throws FileNotFoundException, IOException
    {
        countEvenOut = 0;
        countOddOut = 0;
        
        int readedChar;
        InputStream fIS = new FileInputStream(pathToFile.replace("/", "//"));
        Reader reader = new InputStreamReader(fIS);
        
        while( (readedChar = reader.read()) != -1){
            // 48 - char 0 ; 57 - char 9 
            if(readedChar >= 48 && readedChar <= 57){
                if(readedChar%2 == 0){
                    countEvenOut += 1;
                }else{
                    countOddOut += 1;
                }
            }
            
            //выводим текст файла
            System.out.print((char)readedChar);
        }
        System.out.println("countEven = " + countEvenOut);
        System.out.println("countOdd = " + countOddOut);
        System.out.println();
    }
    
    
}
