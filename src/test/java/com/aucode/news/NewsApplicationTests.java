package com.aucode.news;

import com.aucode.news.entity.SysNewsColumnClass;
import com.aucode.news.entity.SysNewscolumn;
import com.aucode.news.service.SysNewscolumnService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class NewsApplicationTests {

    @Autowired
    private SysNewscolumnService newscolumnService;

    @Test
    void contextLoads() {
        List<SysNewsColumnClass> type = newscolumnService.getType();
        System.out.println(type);
    }

}
