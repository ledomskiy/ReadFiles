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
    
    ReadFiles(String pathToListFiles) {
        this.listFiles = new ArrayList<String>();
        this.pathToListFiles = pathToListFiles;
    }
    
    //запуск подсчета количества цифр
    public void run(){
        try{
            createListFiles();
            Integer countEven = 0;
            Integer countOdd = 0;
            for(int i=0; i<listFiles.size(); i++){
                calculateNumbersInFile(listFiles.get(i), countEven, countOdd);
                System.out.println("pathToFile = " + listFiles.get(i));
                System.out.println("countEven = " + countEven);
                System.out.println("countOdd = " + countOdd);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
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
