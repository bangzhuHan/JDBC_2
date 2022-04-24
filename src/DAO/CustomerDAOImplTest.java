package DAO;

import Bean.Customer;
import JDBCUtils.JDBCUtils;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xh
 * @date 2022/4/23
 * @apiNote
 */
class CustomerDAOImplTest {

    private CustomerDAOImpl dao = new CustomerDAOImpl();

    @org.junit.jupiter.api.Test
    void insert() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Customer cust = new Customer(1, "法外狂徒张三","2356475463@qq.com",new Date(3254765474567L));
            dao.insert(conn,cust);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }

    }

    @org.junit.jupiter.api.Test
    void deleteById() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            dao.deleteById(connection,21);
            System.out.println("删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }

    }

    @org.junit.jupiter.api.Test
    void update() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Customer customer = new Customer(22,"正义之师李四","23436543753@qq.com",new Date(12314123L));
            dao.update(connection,customer);
            System.out.println("更新成功!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }

    }

    @org.junit.jupiter.api.Test
    void getCustomerById() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getDruidConnection();
            Customer customer = dao.getCustomerById(connection,2);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }

    }

    @org.junit.jupiter.api.Test
    void getAll() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            List<Customer> list = dao.getAll(connection);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }


    }

    @org.junit.jupiter.api.Test
    void getCount() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            System.out.println(dao.getCount(connection));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }

    }

    @org.junit.jupiter.api.Test
    void getMaxBirth() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            System.out.println(dao.getMaxBirth(connection));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }

    }
}