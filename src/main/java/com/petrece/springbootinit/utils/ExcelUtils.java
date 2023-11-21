package com.petrece.springbootinit.utils;


import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Excel工具类
 */
@Slf4j
public class ExcelUtils {
    /**
     * excel转csv
     * @param multipartFile
     * @return
     */
    public static  String excelToCsv(MultipartFile multipartFile){
//        File file = null;
//        try {
////            file = ResourceUtils.getFile("classpath:test_excel.xlsx");
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        //读取数据
        List<Map<Integer, String>> list = null;
        try {
            list = EasyExcel.read(multipartFile.getInputStream())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();
        } catch (IOException e) {
            log.error("excel转csv失败",e);
        }

        if(CollUtil.isEmpty(list)){
            return "";
        }
        //转换为csv
        StringBuilder stringBuilder = new StringBuilder();
        //读取表头
        LinkedHashMap<Integer, String> headerMap = (LinkedHashMap)list.get(0);
        List<String> headerList = headerMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());//过滤为空的
        stringBuilder.append(StringUtils.join(headerList, ",")).append("\n");//字符串拼接

        //读取数据
        for(int i=1;i<list.size();i++){
            LinkedHashMap<Integer, String> dataMap = (LinkedHashMap)list.get(i);
            List<String> dataList = dataMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());//过滤为空的
            stringBuilder.append(StringUtils.join(dataList, ",")).append("\n");//字符串拼接
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        excelToCsv(null);
    }
}
