package com.hexu.petproject.model.vo.pcdDicVO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Classname DicVO
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-09 16:03
 * @Version 1.0
 **/
@Data
public class DicVO {
    private Integer id;
    private String name;
    private List<PcdList> pcdList;
}
