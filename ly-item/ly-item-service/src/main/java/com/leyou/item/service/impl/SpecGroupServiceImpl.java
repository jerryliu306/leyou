package com.leyou.item.service.impl;

import com.leyou.common.enums.ExceptionInfoEnum;
import com.leyou.common.exceptions.LyException;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.service.SpecGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author LiuJinmai
 * @Description 商品信息的规格参数的规格参数组的实现层
 * @Date 2019/1/7 14:41
 */
@Service
public class SpecGroupServiceImpl implements SpecGroupService {

    private final SpecGroupMapper groupMapper;

    @Autowired
    public SpecGroupServiceImpl(SpecGroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    @Override
    public List<SpecGroup> queryGroupByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        //根据对象已有条件查询
        List<SpecGroup> specGroupList = groupMapper.select(specGroup);
        if (CollectionUtils.isEmpty(specGroupList)) {
            throw new LyException(ExceptionInfoEnum.SPEC_GROUP_NOT_FOUND);
        }
        return specGroupList;
    }
}
