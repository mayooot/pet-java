package com.hexu.petproject.model.vo.pcdDicVO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Classname pcdList
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-09 16:03
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PcdList {
    private Integer id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PcdList> pcdList;
}
