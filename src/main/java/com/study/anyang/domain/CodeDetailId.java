package com.study.anyang.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"groupCode", "codeValue"})
@ToString
public class CodeDetailId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String groupCode;
    private String codeValue;

}
