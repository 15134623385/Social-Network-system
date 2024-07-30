package com.example.controller;

import com.example.common.Result;
import com.example.entity.InternalReferral;
import com.example.service.InternalReferralService;
import com.github.pagehelper.PageInfo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.Resource;

/**
 * 内推信息前端管理接口
 **/
@RestController
@RequestMapping("/internalReferral")
public class InternalReferralController {

    @Resource
    private InternalReferralService internalReferralService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody InternalReferral ir) {
        internalReferralService.add(ir);
        // 有待商榷
        return Result.success();
    }

    /**
     * 更改
     */
    @PutMapping("/update")
    public Result update(@RequestBody InternalReferral ir) {
        internalReferralService.updateById(ir);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        internalReferralService.delete(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        internalReferralService.deleteBatch(ids);
        return Result.success();
    }

    @GetMapping("/selectTop")
    public Result selectTop() {
        List<InternalReferral> list = internalReferralService.selectTop();
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(InternalReferral ir,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<InternalReferral> page = internalReferralService.selectPage(ir, pageNum, pageSize);
        return Result.success(page);
    }

    @PutMapping("/updateReadCount/{id}")
    public Result updateReadCount(@PathVariable Integer id) {
        internalReferralService.updateReadCount(id);
        return Result.success();
    }

}