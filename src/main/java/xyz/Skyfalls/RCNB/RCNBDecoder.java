package xyz.Skyfalls.RCNB;

import xyz.Skyfalls.RCNB.Exceptions.LengthNotNBException;
import xyz.Skyfalls.RCNB.Exceptions.NotEnoughNBException;
import xyz.Skyfalls.RCNB.Exceptions.RCNBOverflowException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class RCNBDecoder {
    private final static String cr = "rRŔŕŖŗŘřƦȐȑȒȓɌɍ";
    private final static String cc = "cCĆćĈĉĊċČčƇƈÇȻȼ";
    private final static String cn = "nNŃńŅņŇňƝƞÑǸǹȠȵ";
    private final static String cb = "bBƀƁƃƄƅßÞþ";

    private final static short sr = (short) cr.length();
    private final static short sc = (short) cc.length();
    private final static short sn = (short) cn.length();
    private final static short sb = (short) cb.length();
    private final static short src = (short) (sr * sc);
    private final static short snb = (short) (sn * sb);
    private final static short scnb = (short) (sc * snb);

    public static char decodeByte(String c) throws NotEnoughNBException{
        boolean nb = false;
        int idx[] = {cr.indexOf(c.charAt(0)), cc.indexOf(c.charAt(1))};
        if(idx[0] < 0 || idx[1] < 0){
            idx = new int[]{cn.indexOf(c.charAt(0)), cb.indexOf(c.charAt(1))};
            nb = true;
        }
        if(idx[0] < 0 || idx[1] < 0) throw new NotEnoughNBException();
        return (char) (nb ? (idx[0] * sb + idx[1]) | 0x80 : idx[0] * sc + idx[1]);
    }

    public static int decodeShort(String c) throws NotEnoughNBException, RCNBOverflowException{
        int idx[];
        boolean reverse = cr.indexOf(c.charAt(0)) < 0;
        if(!reverse){
            idx = new int[]{cr.indexOf(c.charAt(0)), cc.indexOf(c.charAt(1)), cn.indexOf(c.charAt(2)), cb.indexOf(c.charAt(3))};
        } else {
            idx = new int[]{cr.indexOf(c.charAt(2)), cc.indexOf(c.charAt(3)), cn.indexOf(c.charAt(0)), cb.indexOf(c.charAt(1))};
        }
        if(idx[0] < 0 || idx[1] < 0 || idx[2] < 0 || idx[3] < 0) throw new NotEnoughNBException();
        int result = idx[0] * scnb + idx[1] * snb + idx[2] * sb + idx[3];
        if(result > 0x7FFF) throw new RCNBOverflowException();
        return (idx[0] * scnb + idx[1] * snb + idx[2] * sb + idx[3]) | (reverse ? 0x8000 : 0);
    }

    public static Character[] decodeAsArray(String s) throws LengthNotNBException, NotEnoughNBException, RCNBOverflowException{
        List<Character> chars = new ArrayList<Character>(s.length() / 2 + 1);
        if((s.length() & 1) == 1){
            throw new LengthNotNBException();
        }

        // decode every 2 bytes (1 rcnb = 2 bytes)
        for (int i = 0; i < (s.length() >> 2); i++) {
            int value = decodeShort(s.substring(i * 4, i * 4 + 4));
            chars.add((char) (value >> 8));
            chars.add((char) (value & 0xFF));
        }
        // decode tailing byte (1 rc / 1 nb = 1 byte)
        if((s.length() & 2) == 2) chars.add(decodeByte(s.substring(s.length() - 2)));
        return chars.toArray(new Character[chars.size() - 1]);
    }

    public static String decodeAsString(String s) throws LengthNotNBException, NotEnoughNBException, RCNBOverflowException, UnsupportedEncodingException{
        StringBuilder sb = new StringBuilder();
        for (char c : decodeAsArray(s)) {
            sb.append(c);
        }
        return new String(sb.toString().getBytes("iso8859-1"), "UTF-8");
    }
}

