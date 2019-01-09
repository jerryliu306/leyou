package com.leyou.common.utils;

import org.joda.time.DateTime;

/**
 * @Author LiuJinmai
 * @Description 处理图片的工具类
 * @Date 2019/1/5 22:19
 */
public class ImageUtil {

    /**
     * 图片重命名
     *
     * @param imageName
     * @return
     */
    public static String renameImageName(String imageName) {
        //获取文件后缀名
        String lastOldFileName = imageName.substring(imageName.lastIndexOf("."));
        //获取并格式化系统时间
        String time = DateTime.now().toString("yyyyMMddHHmmss");
        //生成一个随机数
        String num = NumberUtils.generateCode(5);
        //返回新文件名
        return time + num + lastOldFileName;
    }
}
