import cn.Skyfalls.RCNB.Exceptions.RCNBOverflowException;
import cn.Skyfalls.RCNB.RCNBEncoder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws RCNBOverflowException{
        Scanner scanner=new Scanner(System.in);
        //System.out.println("Please input a String:\n");
        String s="rcnb";
        String s1="ɌcńƁȓČņÞ";
        s=RCNBEncoder.encode(s);
        System.out.println(s);
        System.out.println(s.equals(s1));
    }
}
