package common;

import java.sql.*;

/**
 * <p>Copyright: 2005</p>
 * @version 1.0
 */

public class ORABaseDAO {
    //==========================================================================
    public static void releaseConnection(Connection conn, PreparedStatement preStmt) {
        common.ORADBconnect.putConnection(conn);
        try {
            if (preStmt != null) {
                preStmt.close();
            }
        } catch (SQLException e) {}
    }

    public static void releaseConnection(Connection conn, PreparedStatement preStmt, ResultSet rs) {
        releaseConnection(conn, preStmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {}
    }

    public static void releaseConnection(Connection conn, PreparedStatement preStmt, Statement stmt, ResultSet rs) {
        releaseConnection(conn, preStmt, rs);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {}
    }

}
