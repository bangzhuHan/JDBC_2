package Connection;

import JDBCUtils.JDBCUtils;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.testng.annotations.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author xh
 * @date 2022/4/23
 * @apiNote
 */
public class DruidTest {
    @Test
    public void getConnection() throws Exception {
        Connection connection = JDBCUtils.getDruidConnection();
        System.out.println(connection);
    }
}
