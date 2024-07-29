package com.example.mapper;

import java.util.List;
import com.example.entity.InternalReferral;

/**
 * 内推管理Mapper接口
 */
public interface InternalReferralMapper {
    /**
     * 查询内推管理
     * 
     * @param id 内推管理主键
     * @return 内推管理
     */
    public InternalReferral selectInternalReferralById(Integer id);

    /**
     * 查询内推管理列表
     * 
     * @param internalReferral 内推管理
     * @return 内推管理集合
     */
    public List<InternalReferral> selectInternalReferralList(InternalReferral internalReferral);

    /**
     * 新增内推管理
     * 
     * @param internalReferral 内推管理
     * @return 结果
     */
    public int insertInternalReferral(String internalReferral);

    /**
     * 修改内推管理
     * 
     * @param internalReferral 内推管理
     * @return 结果
     */
    public int updateInternalReferral(String internalReferral);

    /**
     * 删除内推管理
     * 
     * @param id 内推管理主键
     * @return 结果
     */
    public int deleteInternalReferralById(Integer id);

    /**
     * 批量删除内推管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInternalReferralByIds(Integer[] ids);
}
