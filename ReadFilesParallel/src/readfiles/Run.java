/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package readfiles;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author lpa
 */
public class Run {
    public static void main(String Args[]){
        try{
            String pathToListFiles = "/home/lpa/NetBeansProjects/ReadFiles/listOfFiles.txt";
            ReadFiles readFiles = new ReadFiles(pathToListFiles);
            readFiles.run();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
