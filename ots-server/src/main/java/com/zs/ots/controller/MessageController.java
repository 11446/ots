package com.zs.ots.controller;

import com.github.pagehelper.PageHelper;
import com.zs.ots.entity.Message;
import com.zs.ots.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 张帅
 * @date 2020/2/24 18:09
 * @description 留言信息功能
 */
@RestController
@RequestMapping
@Api(tags = "留言信息功能")
public class MessageController {

    @Autowired
    MessageService messageService;

    @ApiOperation(value = "插入留言信息")
    @PostMapping(value = "/setMessageInfo")
    public boolean setMessageInfo(@RequestBody Message message) {
        return messageService.setMessageInfo(message);
    }


    @ApiOperation(value = "分页获取留言信息")
    @GetMapping(value = "/getPageMesInfo")
    public List<Message> getPageMesInfo(@RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return messageService.getPageMesInfo();
    }


    @ApiOperation(value = "获取所有留言信息")
    @GetMapping(value = "/getAllMesInfo")
    public List<Message> getAllMesInfo() {
        return messageService.getAllMesInfo();
    }

    @ApiOperation(value = "修改留言信息")
    @PostMapping(value = "/updateMessage")
    public boolean updateMessage(@RequestBody Message message) {
        return messageService.updateMessage(message);
    }

    @ApiOperation(value = "删除留言")
    @GetMapping(value = "/deleteMessage")
    public boolean deleteMessage(@RequestParam("id") Integer id) {
        return messageService.deleteMessage(id);
    }
}
