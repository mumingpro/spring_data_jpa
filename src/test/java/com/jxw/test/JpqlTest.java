package com.jxw.test;
import com.jxw.util.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * 测试jqpl查询
 */
public class JpqlTest {

    /**
     * 查询全部
     *      jqpl：from cn.itcast.domain.Customer
     *      sql：SELECT * FROM cst_customer
     */

    @Test
    public void testFindAll() {
        //获取实体管理器
        EntityManager entityManager = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //查询全部
        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);//创建Query查询对象，query对象才是执行jqpl的对象
        //发送查询，并封装结果集
        List resultList = query.getResultList();
        for (Object obj : resultList) {
            System.out.println(obj);
        }
        //提交事务
        transaction.commit();
        //释放资源
        entityManager.close();
    }

    /**
     * 排序查询： 倒序查询全部客户（根据id倒序）
     *      sql：SELECT * FROM cst_customer ORDER BY cust_id DESC
     *      jpql：from Customer order by custId desc
     *
     * 进行jpql查询
     *      1.创建query查询对象
     *      2.对参数进行赋值
     *      3.查询，并得到返回结果
     */
    @Test
    public void testOrders() {
        
    }
}
