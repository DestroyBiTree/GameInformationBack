package com.promise.service.Impl;

import com.promise.service.FontService;
import com.promise.util.HZKFont;
import org.springframework.stereotype.Service;

@Service
public class FontServiceImpl implements FontService {

    @Override
    public byte[] getFontData(String infor) throws Exception {
        if (infor == null || infor.isEmpty()) {
            throw new IllegalArgumentException("输入信息不能为空");
        }
        try {
            byte[][] FONT_DATA = HZKFont.getFontData(infor);
            byte[] result = new byte[FONT_DATA.length * FONT_DATA[0].length];
            int index = 0;
            for (byte[] row : FONT_DATA) {
                for (byte b : row) {
                    result[index++] = b;
                }
            }
            return result;
        } catch (Exception e) {
            System.err.println("获取字体数据失败: " + e.getMessage());
            e.printStackTrace();
            throw new Exception("获取字体数据失败: " + e.getMessage(), e);
        }
    }
}