package com.ppg.proj.easyexcel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.ppg.proj.easyexcel.listener.LargeExcelDataListener;
import com.ppg.proj.easyexcel.model.LargeExcelData;
import com.ppg.proj.easyexcel.service.ReadExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class ReadExcelServiceImpl implements ReadExcelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadExcelServiceImpl.class);

    @Override
    public void largeExcelRead(MultipartFile file) {
        long start = System.currentTimeMillis();
        try {
            EasyExcel.read(file.getInputStream(), LargeExcelData.class, new LargeExcelDataListener())
                    .headRowNumber(2).sheet().doRead();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("Large data total time spent: {}", (System.currentTimeMillis() - start) / 1000 + "秒");
    }
}
