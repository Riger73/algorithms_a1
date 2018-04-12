import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GenerateCommand {
    ArrayList<String[]> data=new ArrayList<String[]>();
    
    public static void main(String[] args) {
        GenerateCommand dg = new GenerateCommand();
        String inputFilename = null;
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter file name");
        inputFilename = sc.nextLine();
        System.out.println("Enter operation");
        String operation = sc.nextLine();
        System.out.println("Enter number");
        int number = sc.nextInt();
        switch(operation.toUpperCase()){
            case "AV":
                dg.generateTestCaseAV(number);
                break;
            case "RV":
                dg.generateTestCaserRV(number);
                break;
            case "RE":
                dg.generateTestCaserRE(number);
                break;
            
        }
    }
    
    private void generateTestCaseAV(int number)
    {
        Random r = new Random();
        while(number>0)
        {   
            int a = r.nextInt(10000)+4039;
            System.out.println("AV "+a);
            number--;
        }
    }
    
    public void generateTestCaserRV(int number)
    {
        Random r = new Random();
        while(number>0)
        {   
            int a = r.nextInt(4039);
            System.out.println("AV "+a);
            number--;
        }
    }
    public void generateTestCaserRE(int number)
    {
        Random r = new Random();
        while(number>0)
        {   
            int a = r.nextInt(10000)+4039;
            System.out.println("AV "+a);
            number--;
        }
    }
}
