package xyz.seekyou.bank.util;

public final class CustomCryptUtil {

    /**
     * 加密/解密数据
     */
    public static String cryptData(String src){
        char[] t=src.toCharArray();
        for(int i=0;i<t.length;i++){
            t[i]=(char)~t[i];
        }
        return String.valueOf(t);
    }
}
