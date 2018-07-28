package tan.java.test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;

/**
 * @Desc
 * @Created by tzq 2018/7/12 14:28
 **/
public class Test {
    public static void main(String[] args) {
      int x=1121234;
      int y=12345678;
        byte[] bytes3 = intToByteArray(y);
        System.out.println(bytes3.length);

        for (byte b : bytes3) {
            System.out.format("0x%x ", b);
        }

        int i = byteArrayToInt(bytes3);
        System.out.println(i);

//        byte[] bytes = ByteBuffer.allocate(4).putInt(x).array();
//        System.out.println(bytes.length);
//
//        for (byte b : bytes) {
//            System.out.format("0x%x ", b);
//        }
//
//        String str = new String(bytes);
//        System.out.println(str.length());
//        System.out.println(str);
//
//
//        System.out.println("--------1---------");
//
//        byte[] bytes1 = intToByteArray2(x);
//        System.out.println(bytes1.length);
//
//        for (byte b : bytes1) {
//            System.out.format("0x%x ", b);
//        }
//
//
//        System.out.println("---------2----------");
//        byte[] bytes2 = BigInteger.valueOf(x).toByteArray();
//
//        System.out.println(bytes2.length);//3
//
//        for (byte b : bytes2) {
//            System.out.format("0x%x ", b);
//        }
    }



    public static final byte[] intToByteArray(int value) {
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
    }


    //byte 数组与 int 的相互转换
    public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    public static byte[] intToByteArray2(int a) {
        return new byte[] {
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }
    /**
     * 将byte数组转化成String,为了支持中文，转化时用GBK编码方式
     */
    public String ByteArraytoString(byte[] valArr,int maxLen) {
        String result=null;
        int index = 0;
        while(index < valArr.length && index < maxLen) {
            if(valArr[index] == 0) {
                break;
            }
            index++;
        }
        byte[] temp = new byte[index];
        System.arraycopy(valArr, 0, temp, 0, index);
        try {
            result= new String(temp,"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
