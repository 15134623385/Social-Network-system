package com.example.service;

import com.example.common.enums.LikesModuleEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Blog;
import com.example.entity.InternalReferral;
import com.example.mapper.InternalReferralMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.date.DateUtil;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 内推信息业务处理
 **/
@Service
public class InternalReferralService {

    @Resource
    private InternalReferralMapper irMapper;

    /**
     * 新增
     */
    public void add(InternalReferral ir) {
        ir.setReadCount(0l);
        irMapper.insert(ir);
    }

    public void updateById(InternalReferral ir) {
        irMapper.updateById(ir);
    }

    public void delete(Integer id) {
        irMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            irMapper.deleteById(id);
        }
    }

    public PageInfo<InternalReferral> selectPage(InternalReferral ir, Integer pageNum,
            Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<InternalReferral> list = irMapper.selectAll();
        return PageInfo.of(list);
    }

    public InternalReferral selectById(Integer id) {
        return irMapper.selectById(id);
    }

    public List<InternalReferral> selectAll() {
        return irMapper.selectAll();
    }

    public List<InternalReferral> selectTop() {
        List<InternalReferral> irList = this.selectAll();
        irList = irList.stream().sorted((b1, b2) -> b2.getReadCount().compareTo(b1.getReadCount()))
                .limit(20)
                .collect(Collectors.toList());
        return irList;
    }

    public void updateReadCount(Integer id) {
        irMapper.updateReadCount(id);
    }

}