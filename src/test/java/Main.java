import xyz.Skyfalls.RCNB.Exceptions.LengthNotNBException;
import xyz.Skyfalls.RCNB.Exceptions.NotEnoughNBException;
import xyz.Skyfalls.RCNB.Exceptions.RCNBOverflowException;
import xyz.Skyfalls.RCNB.RCNBDecoder;
import xyz.Skyfalls.RCNB.RCNBEncoder;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws RCNBOverflowException, NotEnoughNBException, LengthNotNBException, UnsupportedEncodingException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("encode test 1: " + RCNBEncoder.encode(new Short[]{114, 99, 110, 98}).equals("ɌcńƁȓČņÞ"));
        System.out.println("encode test 2: " + RCNBEncoder.encode(new Short[]{222, 233, 111, 122, 222}).equals("ȵßȑƈȓƇńÞƞƃ"));
        System.out.println("encode test 3: " + RCNBEncoder.encode("Who NB?").equals("ȐȼŃƅȓčƞÞƦȻƝƃŖć"));

        System.out.println("decode test 1: " + Arrays.equals(RCNBDecoder.decodeAsArray("ɌcńƁȓČņÞ"), (new Character[]{114, 99, 110, 98})));
        System.out.println("decode test 2: " + Arrays.equals(RCNBDecoder.decodeAsArray("ȵßȑƈȓƇńÞƞƃ"), (new Character[]{222, 233, 111, 122, 222})));
        System.out.println("decode test 3: " + RCNBDecoder.decodeAsString("ȐĉņþƦȻƝƃŔć").equals("RCNB!"));

        System.out.println("Please input a String:\n");
        String s = scanner.nextLine();
        s = RCNBEncoder.encode(s);
        System.out.println("encoded: " + s);
        System.out.println("decoded: " + RCNBDecoder.decodeAsString(s));
        System.out.print("decoded as array:");
        for (char c : RCNBDecoder.decodeAsArray(s)) {
            System.out.print(' ');
            System.out.print((int) c);
        }
    }
}
