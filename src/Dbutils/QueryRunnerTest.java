package Dbutils;

import Bean.Customer;
import JDBCUtils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.testng.annotations.Test;

import javax.swing.*;
import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * @author xh
 * @date 2022/4/24
 * @apiNote
 */
/*
 * commons-dbutils 是 Apache 组织提供的一个开源 JDBC工具类库,封装了针对于数据库的增删改查操作
 *
 */
public class QueryRunnerTest {

    //测试插入
    @Test
    public void testInsert(){
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection();
            String sql = "insert into customers(name,email,birth)values(?,?,?)";
            int insertCount = runner.update(conn,sql,"小明", "2354362@1225.com", "1999-9-9");
            System.out.println("添加了" + insertCount + "条记录");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }

    }

    //测试删除
    @Test
    public void testDelete(){
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtils.getDruidConnection();
            String sql = "delete from customers where id = ?";
            int deleteCount = queryRunner.update(connection,sql,28);
            System.out.println("删除了" + deleteCount + "条记录");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection,null);
        }

    }

    //测试查询
    /*
     * BeanHander:是ResultSetHandler接口的实现类，用于封装表中的一条记录。
     */
    @Test
    public void testQuery(){
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtils.getDruidConnection();
            String sql = "select * from customers where id = ?";
            BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);
            Customer customer = queryRunner.query(connection,sql,handler,2);
            System.out.println(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        JDBCUtils.closeResource(connection,null);
    }

    /*
     * BeanListHandler:是ResultSetHandler接口的实现类，用于封装表中的多条记录构成的集合。
     */
    @Test
    public void testQuery2(){
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtils.getDruidConnection();
            String sql = "select * from customers ";
            BeanListHandler<Customer> handler = new BeanListHandler<>(Customer.class);
            List<Customer> list = queryRunner.query(connection,sql,handler);
            list.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        JDBCUtils.closeResource(connection,null);
    }

    /*
     * MapHander:是ResultSetHandler接口的实现类，对应表中的一条记录。
     * 将字段及相应字段的值作为map中的key和value
     */
    @Test
    public void testQuery3(){
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection();
            String sql = "select * from customers where id = ?";
            MapHandler mapHandler = new MapHandler();
            Map<String,Object> map = runner.query(conn,sql,mapHandler,26);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }

    }

    /*
     * MapListHander:是ResultSetHandler接口的实现类，对应表中的多条记录。
     * 将字段及相应字段的值作为map中的key和value。将这些map添加到List中
     */
    @Test
    public void testQuery4(){
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection();
            String sql = "select * from customers ";
            MapListHandler mapHandler = new MapListHandler();
            List<Map<String,Object>> map = runner.query(conn,sql,mapHandler);
           map.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }

    }


    /*
     * ScalarHandler:用于查询特殊值
     */
    @Test
    public void testQuery5(){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select min(birth) from customers";
            ScalarHandler scalarHandler = new ScalarHandler();
            Date birth = (Date) queryRunner.query(conn,sql,scalarHandler);
            System.out.println(birth);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }


    /*
     * 自定义ResultSetHandler的实现类
     */
    @Test
    public void testQuery6(){
        Connection conn = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            conn = JDBCUtils.getConnection();
            String sql = "select id,name,email,birth from customers where id = ?";
            ResultSetHandler<Customer> handler = new ResultSetHandler<Customer>() {
                @Override
                public Customer handle(ResultSet resultSet) throws SQLException {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String email = resultSet.getString("email");
                        Date date = resultSet.getDate("birth");
                        return new Customer(id, name, email, date);
                    }
                    return null;
                }

            };
            Customer customer = queryRunner.query(conn,sql,handler,4);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }

    }
}
