package cn.jiyun.service;

import cn.jiyun.mapper.EmpMapper;
import cn.jiyun.pojo.Emp;
import cn.jiyun.pojo.EmpExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class EmpService {

    @Autowired
    EmpMapper empMapper;


    public List<Emp> list() {
        EmpExample empExample = new EmpExample();


        return  empMapper.selectByExample(empExample);
    }

    public void add(Emp emp) {

        empMapper.insert(emp);

    }
}
