package com.lw.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据字典表
 * @author wuchaoqun
 * @date 2018年12月28日
 * @description
 */
@Getter
@Setter
public class Dictionary {
	
	/**字典id（一个id对应多个code）**/
    private String itemId;

    /**字典key**/
    private String itemCode;

    /**字典值**/
    private String itemName;

    /**父节点key**/
    private String parentItemCode;

    /**字典描述信息**/
    private String description;
    
    /**是否可用  0不可用 1可用**/
    private Boolean valid;
    
    /**序号**/
    private Integer index;
    
}