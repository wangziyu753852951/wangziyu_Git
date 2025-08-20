package com.wzy.common.core.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: PageQueryDTO
 * Description
 *
 * @Author wzy
 * @Create 2025/8/5 18:33
 * @Version 1.0
 */
@Getter
@Setter
public class PageQueryDTO {
    private Integer pageSize = 10;

    private Integer pageNum = 1 ;  //默认从第一页开始传
}
