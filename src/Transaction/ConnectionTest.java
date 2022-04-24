package Transaction;

import JDBCUtils.JDBCUtils;
import org.testng.annotations.Test;

import java.sql.Connection;

/**
 * @author xh
 * @date 2022/4/21
 * @apiNote
 */
public class ConnectionTest {
    @Test
    public void testConnection() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
    }
}
