package com.promise;

import com.promise.entity.People;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class DroolsApplicationTests  {

    @Autowired
    private KieSession session;

    @Autowired
    private KieBase kieBase;


    @Test
    public void people() {
        People people = new People(0, "sun", "update"); // 设置sex为0，并且drlType为"update"
        System.out.println(people.getSex());
        session.insert(people); // 插入
        session.fireAllRules(); // 执行
        System.out.println(people.getSex());


    }
    @Test
    public void FileExists() {
// 请根据实际情况调整文件路径
        File file = new File("D:\\bbbCode\\IndividualProject\\GameInformation\\Java\\src\\main\\resources\\rules\\Update\\UpdateRules.drl");
        if (file.exists()) {
            System.out.println("File exists at: " + file.getAbsolutePath());
        } else {
            System.out.println("File does not exist at the specified path.");
        }

    }
    @AfterEach
    public void runDispose() {
        session.dispose();//释放资源
    }

}
