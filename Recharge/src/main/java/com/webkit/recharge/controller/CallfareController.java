package com.webkit.recharge.controller;

import com.webkit.recharge.bean.Callfare;
import com.webkit.recharge.bean.message.Message;
import com.webkit.recharge.service.ICallfareService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by HH on 2018/1/22.
 */
@Controller
@RequestMapping("/callfares")
public class CallfareController {

    @Resource
    private ICallfareService callfareService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    Message query() {
        return callfareService.query();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    Message create(@RequestBody Callfare callfare) {
        return callfareService.create(callfare);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    Message delete(@PathVariable("id") Long id){
        return  callfareService.delete(id);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ResponseBody
    Message delete(@RequestParam(value = "items") List<Long> items){
        return  callfareService.delete(items);
    }
}
