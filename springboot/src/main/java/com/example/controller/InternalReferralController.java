package com.example.controller;

import com.example.common.Result;
import com.example.entity.InternalReferral;
import com.example.service.InternalReferralService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 内推管理Controller
 **/
@RestController
@RequestMapping("/Internalreferral")
public class InternalReferralController {

    @Resource
    private InternalReferralService internalReferralService;

    /**
     * 查询
     */
    @PostMapping("/add")
    public Result add(@RequestBody InternalReferral internalReferral) {
        return Result.success();
    }

}