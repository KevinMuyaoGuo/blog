package com.gmy.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kevin Guo
 * @package com.gmy.vo
 * @date 2020/8/21 14:47
 * @description
 */
@NoArgsConstructor
@Data
public class BlogQuery {

    private String title;

    private Long typeId;

    private boolean recommend;//是否推荐

}
