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
    private String[] listFiles;
    
    ReadFiles(String pathToListFiles) {
        this.pathToListFiles = pathToListFiles;
    }
    
    //запуск подсчета количества цифр
    private void run(){
        try{
            listFiles = createListFiles();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    //Создание массива путей до файлов
    private String[] createListFiles() throws FileNotFoundException, IOException{
        ArrayList<String> arraylistFiles = new ArrayList<>();
        String readedLine;
        InputStream fIS = new FileInputStream(pathToListFiles);
        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new InputStreamReader(fIS,Charset.forName("UTF-8")));
        
        while((readedLine = bufferedReader.readLine()) != null){
            System.out.println(readedLine);
            arraylistFiles.add(readedLine);
        }
        
        return (String[])arraylistFiles.toArray();        
    }
    
    public void calculateNumbersInFile(String pathToFile,
                                       Integer countEvenOut, 
                                       Integer countOddOut)
    {
        System.out.println("CountEven = " + countEvenOut);
        System.out.println("CountOdd = " + countOddOut);
    }
    
    
}
