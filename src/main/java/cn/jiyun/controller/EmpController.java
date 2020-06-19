package cn.jiyun.controller;

import cn.jiyun.pojo.Emp;
import cn.jiyun.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
public class EmpController {

    @Autowired
    EmpService empService;


    @Autowired
    JavaMailSenderImpl mailSender;

    private String code="";

    @RequestMapping("list")
    public String list(Model model){
        List<Emp> list=  empService.list();
        System.out.println(list);
        model.addAttribute("list",list);

        return  "list";

    }


    @RequestMapping("tosend")
    public String LoadsmailSimpleMailMessage(Model model) {

        return  "send";

    }
    @RequestMapping("toadd")
    public String toadd(Model model) {

        return  "add";

    }

    @RequestMapping("send")
    public String contextLoadsmailSimpleMailMessage(Model model,String qq) {
        //一封简单的邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        // JavaMailSenderImpl mailSender = MailConfig.getJavaMailSender();

        //邮件标题
        mailMessage.setSubject("这是邮件验证码，哈哈哈哈");
        //邮件内容 随机生成验证码
        String code = UUID.randomUUID().toString().substring(0, 4);

        mailMessage.setText(code);
        //发送人（必须和配置文件中的username相同）
        mailMessage.setFrom("发信人qq邮箱写你自己邮箱");

        //mailMessage.setTo("收信人qq邮箱");
        mailMessage.setTo(qq);

        mailSender.send(mailMessage);
        model.addAttribute("code",code);
        return  "add";

    }

    @RequestMapping("add")
    public String add(Model model,Emp emp){
        String code = emp.getCode();
        String yanzheng = emp.getYanzheng();
        System.out.println(code);
        System.out.println(yanzheng);

        if(yanzheng.equals(code)){
            empService.add(emp);

            return  "redirect:list";

        }

        else {

            model.addAttribute("error","验证码错误重新注册去验证邮箱");
            return  "add";

        }



    }





}
