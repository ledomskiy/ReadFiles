/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DigitsCounterParallel;

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
            int maxCountParallelThreads = 2;
            DigitsCounter readFiles = new DigitsCounter(pathToListFiles,maxCountParallelThreads);
            readFiles.run();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
