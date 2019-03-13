import xyz.Skyfalls.RCNB.Exceptions.LengthNotNBException;
import xyz.Skyfalls.RCNB.Exceptions.NotEnoughNBException;
import xyz.Skyfalls.RCNB.Exceptions.RCNBOverflowException;
import xyz.Skyfalls.RCNB.RCNBDecoder;
import xyz.Skyfalls.RCNB.RCNBEncoder;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws RCNBOverflowException, NotEnoughNBException, LengthNotNBException, UnsupportedEncodingException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input a String:\n");
        String s = scanner.nextLine();
        s = RCNBEncoder.encode(s);
        System.out.println("encoded: " + s);
        System.out.println("decoded: " + RCNBDecoder.decode(s));
    }
}
