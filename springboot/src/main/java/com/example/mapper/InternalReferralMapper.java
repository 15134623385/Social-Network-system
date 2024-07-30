package com.example.mapper;

import com.example.entity.InternalReferral;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 操作blog相关数据接口
 */
public interface InternalReferralMapper {

    /**
     * 新增
     */
    void insert(InternalReferral ir);

    /**
     * 删除
     */
    void deleteById(Integer id);

    /**
     * 修改
     */
    void updateById(InternalReferral ir);

    /**
     * 根据ID查询
     */
    InternalReferral selectById(Integer id);

    /**
     * 查询所有
     */
    @Select("select * from internal_referral")
    List<InternalReferral> selectAll();

    @Update("update internal_referral set read_count = read_count + 1 where id = #{interId}")
    void updateReadCount(Integer interId);
}