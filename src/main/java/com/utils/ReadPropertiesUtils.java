package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ReadPropertiesUtils {
	
	 /**
     *  得到properties文件的map形式
     * @param propertiesFilePath  properties文件的路径
     * @return Map<String,Object> 对应资源文件转换成map
     */
    public static Map<String,String> loadToMap(String propertiesFilePath) {
         
        //1.加载资源文件
        InputStream in;
        Properties pro = new Properties();
        try {
            in = new FileInputStream(new File(propertiesFilePath));
            pro.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
         
        //获取配置文件的所有键值
        Set<String> keys = pro.stringPropertyNames();
 
        //文件的内容为空
        if(keys.size() == 0){
            throw new RuntimeException("资源文件:"+propertiesFilePath+"的内容为空");
        }
         
        //把键值对放入map中
        Map<String,String> map = new HashMap<String,String>();
        for(String key : keys){
            map.put(key, pro.getProperty(key));
        }
        return map;
    }
}
