package DAO;

import Bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @Description:此接口用于规范对于customers表的常用操作
 * @author xh
 * @date 2022/4/23
 * @apiNote
 */

public interface CustomerDAO {
    //将cust对象添加到数据库中
    void insert(Connection conn, Customer cust);

    //删除指定id的一条记录
    void deleteById(Connection conn,int id);

    //修改cust对象的记录
    void update(Connection conn,Customer cust);

    //针对指定id查询cust对象
    Customer getCustomerById(Connection conn, int id);

    //查询表中所有数据
    List<Customer> getAll(Connection conn);

    //返回表中条目数
    Long getCount(Connection conn);

    //返回表中最大的生日
    Date getMaxBirth(Connection conn);
}
