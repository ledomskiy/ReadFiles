/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package readfiles;

/**
 *
 * @author lpa
 */
public class Run {
    public static void main(String Args[]){
        String pathToListFiles = "/home/lpa/NetBeansProjects/ReadFiles/listOfFiles.txt";
        ReadFiles readFiles = new ReadFiles(pathToListFiles);
        System.out.println("Hello");
    }
    
}
