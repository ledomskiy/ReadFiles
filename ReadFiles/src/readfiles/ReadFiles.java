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
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lpa
 */
public class ReadFiles {
    private String pathToListFiles;
    private ArrayList<String> listFiles;
    
    ReadFiles(String pathToListFiles) {
        this.pathToListFiles = pathToListFiles;
    }
    
    //запуск подсчета количества цифр
    public void run() throws FileNotFoundException, IOException{
        //try{
            listFiles = createListFiles();
            Integer countEven = 0;
            Integer countOdd = 0;
            for(int i=0; i<listFiles.length; i++){
                //calculateNumbersInFile(listFiles[i], countEven, countOdd);
                System.out.println("pathToFile = " + listFiles[i]);
                System.out.println("countEven = " + countEven);
                System.out.println("countOdd = " + countOdd);
            }
        /*}catch(Exception e){
            System.out.println(e.getMessage());
        }*/
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
                                       Integer countOddOut)
    {
        countEvenOut = 0;
        countOddOut = 0;
        System.out.println("CountEven = " + countEvenOut);
        System.out.println("CountOdd = " + countOddOut);
    }
    
    
}
