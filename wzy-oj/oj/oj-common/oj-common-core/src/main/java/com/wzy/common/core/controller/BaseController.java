package com.wzy.common.core.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageInfo;
import com.wzy.common.core.domain.R;
import com.wzy.common.core.domain.TableDataInfo;

import java.util.List;

/**
 * ClassName: BaseController
 * Description
 *
 * @Author wzy
 * @Create 2025/8/3 11:25
 * @Version 1.0
 */
public class BaseController {
    public R<Void> toResult(int rows){
        return rows > 0 ? R.ok(): R.fail();
    }

    public R<Void> toResult(boolean result){
        return result ? R.ok(): R.fail();
    }

    public TableDataInfo getTableDataInfo(List<?> list) {
        if(CollectionUtil.isEmpty(list)){
            return TableDataInfo.empty();
        }

        return TableDataInfo.success(list, new PageInfo<>(list).getTotal());
    }
    }

