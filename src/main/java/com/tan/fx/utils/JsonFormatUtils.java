package com.tan.fx.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class JsonFormatUtils {
    private final static String space = "   ";
    private final static String space2 = "    ";
    private static boolean existLeft = false;
    public static void main(String[] args) throws Exception {

        JsonFormatUtils tool = new JsonFormatUtils();
        String text = "{\"info\":[{\"code\":\"C\",\"key\":\"028\",\"nearest\":\"NO\",\"value\":\"好冷\"},{\"code\":\"N\",\"key\":\"0771\",\"nearest\":\"NO,\",\"value\":\"好{冷}\"},{\"code\":\"L\",\"key\":\"07[72\",\"nearest\":\"N]O\",\"value\":\"好冷\"},{\"code\":\"G\",\"key\":\"0773\",\"nearest\":\"NO\",\"value\":\"好冷\"}],\"resultCode\":\"0\",\"resultInfo\":\"SUCCESS\"}";
        String json = tool.formatJson(text);
        System.out.println(json);
    }

    //写入文件
    private void writeFile(String filePath, String text) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        writer.write(text);
        writer.close();
    }


    //格式化json
    public static String formatJson(String json) {
        StringBuffer result = new StringBuffer();
        int length = json.length();
        int number = 0;
        char key = 0;
        for (int i = 0; i < length; i++) {
            key = json.charAt(i);
            if (isEffectSpecChr(i, key, json)) {
                if ((key == '[') || (key == '{')) {
                    result.append(key);
                    result.append('\n');
                    number++;
                    result.append(indent(number));
                    continue;
                }

                if ((key == ']') || (key == '}')) {
                    result.append('\n');
                    number--;
                    result.append(indent(number));
                    result.append(key);
                    continue;
                }

                if ((key == ',')) {
                    result.append(key);
                    result.append('\n');
                    result.append(indent(number));
                    continue;
                }
            }
            result.append(key);
        }
        return result.toString();
    }

    //过滤有效的特殊字符
    private static boolean isEffectSpecChr(int index, char key, String json) {
        boolean flag = false;

        if (isDouMark(index, String.valueOf(key), json)) {
            if (existLeft) {
                existLeft = false;
            } else {
                existLeft = true;
            }
        } else {
            if ((key == '[')
                    || (key == '{')
                    || (key == ']')
                    || (key == '}')
                    || (key == ',')) {
                if (!existLeft) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    //判断当前双引号是否为特殊字符
    private static boolean isDouMark(int index, String key, String json) {
        if (key.equals("\"") && index >= 0) {
            if (index == 0) {
                return true;
            }

            char c = json.charAt(index - 1);
            if (c != '\\') {
                return true;
            }
        }
        return false;
    }

    //补充tab空格
    private static String indent(int number) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < number; i++) {
            result.append(space);
        }
        return result.toString();
    }
    //补充tab空格
    private static String indent2(int number) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < number; i++) {
            result.append(space2);
        }
        return result.toString();
    }
}


