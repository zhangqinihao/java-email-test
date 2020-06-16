package cn.jiyun.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private Integer eid;

    private String ename;
    @Transient
    private String code;
    @Transient
    private String yanzheng;
}