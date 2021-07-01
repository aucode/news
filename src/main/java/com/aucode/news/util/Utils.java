package com.aucode.news.util;

import com.aucode.news.entity.SysNews;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Project news
 * @Description: Utils
 * @Author
 * @Explain
 * @Date 2021-06-19 14:13
 */
public class Utils {
    /**
     * 得到网页中图片的地址
     * @param htmlStr html字符串
     */
    public static Set<String> getImgStr(String htmlStr) {
        Set<String> pics = new HashSet<String>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }
    /**
     * 去除所有的标签，空格
     * @param content
     * @return
     */
    public static List<SysNews> removeHtml(List<SysNews> content) {
        for (int i = 0; i < content.size(); i++) {
            content.get(i).setContent(content.get(i).getContent().replaceAll("</?[^<]+>", ""));
            content.get(i).setContent(content.get(i).getContent().replaceAll("\\s*|\t|\r|\n", ""));

        }
        return content;
    }

}
