import xyz.Skyfalls.RCNB.Exceptions.RCNBOverflowException;
import xyz.Skyfalls.RCNB.RCNBEncoder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws RCNBOverflowException{
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please input a String:\n");
        String s=scanner.nextLine();
        s=RCNBEncoder.encode(s);
        System.out.println(s);
    }
}
