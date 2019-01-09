package com.leyou.item.service.impl;

import com.leyou.common.enums.ExceptionInfoEnum;
import com.leyou.common.exceptions.LyException;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author LiuJinmai
 * @Description 商品信息的规格参数的参数名称管理实现类
 * @Date 2019/1/7 18:02
 */
@Service
public class SpecParamServiceImpl implements SpecParamService {

    private final SpecParamMapper paramMapper;

    @Autowired
    public SpecParamServiceImpl(SpecParamMapper paramMapper) {
        this.paramMapper = paramMapper;
    }

    @Override
    public List<SpecParam> queryParamByGid(Long gid, Long cid, Boolean searching) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        //修改：添加2个搜索条件
        specParam.setCid(cid);
        specParam.setSearching(searching);

        List<SpecParam> paramList = paramMapper.select(specParam);
        if (CollectionUtils.isEmpty(paramList)) {
            throw new LyException(ExceptionInfoEnum.SPEC_PARAM_NOT_FOUND);
        }
        return paramList;
    }
}
