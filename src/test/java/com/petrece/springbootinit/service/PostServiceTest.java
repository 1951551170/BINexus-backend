package com.petrece.springbootinit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petrece.springbootinit.model.dto.chart.ChartQueryRequest;
import com.petrece.springbootinit.model.entity.Post;

import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 帖子服务测试
 *
 * 
 */
@SpringBootTest
class PostServiceTest {

    @Resource
    private PostService postService;

    @Test
    void searchFromEs() {
        ChartQueryRequest postQueryRequest = new ChartQueryRequest();
        postQueryRequest.setUserId(1L);
        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
        Assertions.assertNotNull(postPage);
    }

}