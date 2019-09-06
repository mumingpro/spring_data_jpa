package com.jxw.test;

import com.jxw.domain.Customer;
import com.jxw.util.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

    /**
     * 测试jpa的保存
     *      案例：保存一个客户到数据库中
     *  Jpa的操作步骤
     *     1.加载配置文件创建工厂（实体管理器工厂）对象
     *     2.通过实体管理器工厂获取实体管理器
     *     3.获取事务对象，开启事务
     *     4.完成增删改查操作
     *     5.提交事务（回滚事务）
     *     6.释放资源
     */
    @Test
    public void testAdd(){
        //获得EntityManagerFactory
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //根据实体类工厂对象获取管理器对象
        EntityManager entityManager = JpaUtils.getEntityManager();
        //获取事务对象,开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 完成增删改:保存一个客户的数据到数据库
        Customer customer = new Customer();  //创建实体类对象
        customer.setCustName("目茗科技");
        customer.setCustIndustry("软件开发");
        entityManager.persist(customer);//保存操作
        transaction.commit();//提交事务
        entityManager.close();//释放资源
    }

    /**
     * 根据id查询客户
     *  使用find方法查询：
     *      1.查询的对象就是当前客户对象本身
     *      2.在调用find方法的时候，就会发送sql语句查询数据库
     *  立即加载
     */

    @Test
    public void testFind(){
        //通过工具类获取实体对象管理器
        EntityManager entityManager = JpaUtils.getEntityManager();
        //获取事务对象,开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //根据id查询客户
        /**
         * find : 根据id查询数据
         *      class：查询数据的结果需要包装的实体类类型的字节码
         *      id：查询的主键的取值
         */
        Customer customer = entityManager.find(Customer.class, 1L);
        System.out.print(customer);
        //提交事务
        transaction.commit();
        //释放资源
        entityManager.close();
    }

    /**
     * 根据id查询客户
     *      getReference方法
     *          1.获取的对象是一个动态代理对象
     *          2.调用getReference方法不会立即发送sql语句查询数据库
     *              * 当调用查询结果对象的时候，才会发送查询的sql语句：什么时候用，什么时候发送sql语句查询数据库
     *
     * 延迟加载（懒加载）
     *      * 得到的是一个动态代理对象
     *      * 什么时候用，什么使用才会查询
     */
    @Test
    public  void testReference() {
        //获取实体管理器
        EntityManager entityManager = JpaUtils.getEntityManager();
        //获取事务对象开始事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.增删改查 -- 根据id查询客户
        /**
         * getReference : 根据id查询数据
         *      class：查询数据的结果需要包装的实体类类型的字节码
         *      id：查询的主键的取值
         */
        Customer customer = entityManager.getReference(Customer.class, 1L);
        System.out.println(customer);
        //提交事务
        transaction.commit();
        //释放资源
        entityManager.close();
    }

    /**
     * 删除客户的案例
     *
     */
    @Test
    public  void testRemove() {
        //获取实体管理器
        EntityManager entityManager = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //根据id查询客户
        Customer customer = entityManager.find(Customer.class, 2L);
        entityManager.remove(customer);
        //提交事务
        transaction.commit();
        //释放资源
        entityManager.close();
    }

    /**
     * 更新客户的操作
     *      merge(Object)
     */
    @Test
    public  void testUpdate() {
        //获取实体管理器
        EntityManager entityManager = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //根据用户Id查询客户
        Customer customer = entityManager.find(Customer.class, 1L);
        //ii 更新客户
        customer.setCustAddress("湖北宜昌");
        entityManager.merge(customer);//保存修改
        //提交事务
        transaction.commit();
        //释放资源
        entityManager.close();
    }





}
