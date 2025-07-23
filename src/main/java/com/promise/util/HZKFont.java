package com.promise.util;

import java.io.File;
import java.io.RandomAccessFile;

public class HZKFont {
    // 获取区位码
    private static String bytes2HexString(byte b) {
        return bytes2HexString(new byte[]{b});
    }

    private static String bytes2HexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

    public static int[] getLocation(String str) throws Exception {
        byte[] bs = str.getBytes("GB2312");
        int[] s = new int[bs.length];
        for (int i = 0; i < bs.length; i++) {
            int a = Integer.parseInt(bytes2HexString(bs[i]), 16);
            s[i] = (a - 0x80 - 0x20);
        }
        return s;
    }

    // 返回字符的点阵数据数组
    public static byte[][] getFontData(String str) throws Exception {
        File file = new File("D:\\game_information\\GameInformationBack\\hzk16");
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        int charCount = str.length();
        byte[][] result = new byte[charCount][32];

        try {
            if (!file.canRead()) {
                System.out.println("无法读取 hzk16 文件");
                return null;
            }

            int[] location = getLocation(str);
            for (int a = 0; a < charCount; a++) {
                int offset = (94 * (location[a*2] - 1) + (location[a*2+1] - 1)) * 32;
                raf.seek(offset);
                raf.readFully(result[a]);
            }
        } finally {
            raf.close();
        }

        return result;
    }

}