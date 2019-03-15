package xyz.Skyfalls.RCNB;

import xyz.Skyfalls.RCNB.Exceptions.RCNBOverflowException;

import java.util.ArrayList;
import java.util.List;

public class RCNBEncoder {
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

    private static short _div(int a, int b){
        return (short) Math.floor(a / b);
    }

    public static char[] encodeByte(short i) throws RCNBOverflowException{
        if(i > 0xFF) throw new RCNBOverflowException();
        if(i > 0x7F){
            short value = (short) (i & 0x7F);
            char result[] = {cn.charAt(_div(value, sb)), cb.charAt(value % sb)};
            return result;
        }
        char result[] = {cr.charAt(_div(i, sc)), cc.charAt(i % sc)};
        return result;
    }

    public static char[] encodeShort(int value) throws RCNBOverflowException{
        if(value > 0xFFFF) throw new RCNBOverflowException();
        boolean reverse = false;
        int i = value;
        if(i > 0x7FFF){
            reverse = true;
            i = i & 0x7FFF;
        }
        char chars[] = {cr.charAt(_div(i, scnb)), cc.charAt(_div(i % scnb, snb)), cn.charAt(_div(i % snb, sb)), cb.charAt((i % sb))};
        if(reverse){
            char results[] = {chars[2], chars[3], chars[0], chars[1]};
            return results;
        }
        return chars;
    }

    public static String encode(Short[] bytes) throws RCNBOverflowException{
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < (bytes.length >> 1); i++) {
            sb.append(encodeShort(((bytes[i * 2] << 8)) | bytes[i * 2 + 1]));
        }
        if((bytes.length & 1) == 1) sb.append(encodeByte(bytes[bytes.length - 1]));
        return sb.toString();
    }

    public static String encode(String s) throws RCNBOverflowException{
        byte _bytes[] = s.getBytes();
        List<Short> bytes = new ArrayList<Short>(_bytes.length);
        for (int i = 0; i < _bytes.length; i++) {
            bytes.add((short) (_bytes[i] & 0xFF));
        }
        return encode(bytes.toArray(new Short[bytes.size() - 1]));
    }
}
