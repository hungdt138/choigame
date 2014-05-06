/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Do Tien Hung
 */
public class Stringutils extends common.ORABaseDAO {

    public Stringutils() {
    }

    public static String removeSign(String str) {
        String currChar;
        String rvl = "";
        for (int i = 0; i < str.length(); i++) {
            currChar = String.valueOf(str.charAt(i));
            if ("á|à|ạ|ả|ã|Á|À|Ạ|Ả|Ã|ă|ắ|ằ|ặ|ẳ|ẵ|Ă|Ắ|Ằ|Ặ|Ẳ|Ẵ|â|ấ|ầ|ậ|ẩ|ẫ|Â|Ấ|Ầ|Ậ|Ẩ|Ẫ".indexOf(currChar) >= 0) {
                rvl = rvl + "a";
            } else if ("ó|ò|ọ|ỏ|õ|Ó|Ò|Ọ|Ỏ|Õ|ô|ố|ồ|ộ|ổ|ỗ|Ô|Ố|Ồ|Ộ|Ổ|Ỗ|ơ|ớ|ờ|ợ|ở|ỡ|Ơ|Ớ|Ờ|Ợ|Ở|Ỡ".indexOf(currChar) >= 0) {
                rvl = rvl + "o";
            } else if ("é|è|ẹ|ẻ|ẽ|É|È|Ẹ|Ẻ|Ẽ|ê|ế|ề|ệ|ể|ễ|Ê|Ế|Ề|Ệ|Ể|Ễ".indexOf(currChar) >= 0) {
                rvl = rvl + "e";
            } else if ("ú|ù|ụ|ủ|ũ|Ú|Ù|Ụ|Ủ|Ũ|ư|ứ|ừ|ự|ử|ữ|Ư|Ứ|Ừ|Ự|Ử|Ữ".indexOf(currChar) >= 0) {
                rvl = rvl + "u";
            } else if ("í|ì|ị|ỉ|ĩ|Í|Ì|Ị|Ỉ|Ĩ".indexOf(currChar) >= 0) {
                rvl = rvl + "i";
            } else if ("ý|ỳ|ỵ|ỷ|ỹ|Ý|Ỳ|Ỵ|Ỷ|Ỹ".indexOf(currChar) >= 0) {
                rvl = rvl + "y";
            } else if ("đ|Đ".indexOf(currChar) >= 0) {
                rvl = rvl + "d";
            } else {
                rvl = rvl + currChar;
            }
        }
        return rvl;
    }

    public static String removeSign(String str, String seperator) {
        String currChar;
        String rvl = "";
        for (int i = 0; i < str.length(); i++) {
            currChar = String.valueOf(str.charAt(i));
            if (currChar.equals(" ")) {
                rvl = rvl + seperator;
            } else {
                if ("á|à|ạ|ả|ã|Á|À|Ạ|Ả|Ã|ă|ắ|ằ|ặ|ẳ|ẵ|Ă|Ắ|Ằ|Ặ|Ẳ|Ẵ|â|ấ|ầ|ậ|ẩ|ẫ|Â|Ấ|Ầ|Ậ|Ẩ|Ẫ".indexOf(currChar) >= 0) {
                    rvl = rvl + "a";
                } else if ("ó|ò|ọ|ỏ|õ|Ó|Ò|Ọ|Ỏ|Õ|ô|ố|ồ|ộ|ổ|ỗ|Ô|Ố|Ồ|Ộ|Ổ|Ỗ|ơ|ớ|ờ|ợ|ở|ỡ|Ơ|Ớ|Ờ|Ợ|Ở|Ỡ".indexOf(currChar) >= 0) {
                    rvl = rvl + "o";
                } else if ("é|è|ẹ|ẻ|ẽ|É|È|Ẹ|Ẻ|Ẽ|ê|ế|ề|ệ|ể|ễ|Ê|Ế|Ề|Ệ|Ể|Ễ".indexOf(currChar) >= 0) {
                    rvl = rvl + "e";
                } else if ("ú|ù|ụ|ủ|ũ|Ú|Ù|Ụ|Ủ|Ũ|ư|ứ|ừ|ự|ử|ữ|Ư|Ứ|Ừ|Ự|Ử|Ữ".indexOf(currChar) >= 0) {
                    rvl = rvl + "u";
                } else if ("í|ì|ị|ỉ|ĩ|Í|Ì|Ị|Ỉ|Ĩ".indexOf(currChar) >= 0) {
                    rvl = rvl + "i";
                } else if ("ý|ỳ|ỵ|ỷ|ỹ|Ý|Ỳ|Ỵ|Ỷ|Ỹ".indexOf(currChar) >= 0) {
                    rvl = rvl + "y";
                } else if ("đ|Đ".indexOf(currChar) >= 0) {
                    rvl = rvl + "d";
                } else {
                    rvl = rvl + currChar;
                }
            }
        }
        return rvl;
    }

    /**
     * have all 'allpage' but show 'pageNumber' page allow 'currentpage'
     * @param allpage
     * @param currentpage
     * @param pageNumber
     * @return
     */
    public static int[] paging(int allpage, int currentpage, int pageNumber) {
        // if page number to much so many, show 'pageNumber' page arround current page
        // all page is all of page number, begin from 0 then endless page is allpage - 1
        int pageToShow[] = new int[pageNumber];
        int padding = Math.round(pageNumber / 2);
        int startpage = currentpage - padding > 1 ? currentpage - padding : 1;
        int endpage = allpage > (startpage + pageNumber - 1) ? startpage + pageNumber - 1 : allpage;
        for (int i = 0; i <= endpage - startpage; i++) {
            pageToShow[i] = startpage + i;
        }
        return pageToShow;
    }

//    public static int[] paging(int allpage, int currentpage, int pageNumber) {
//        // if page number to much so many, show 'pageNumber' page arround current page
//        // all page is all of page number, begin from 0 then endless page is allpage - 1
//
//
//
//        int pageToShow[] = new int[pageNumber];
//        int pageCenter = Math.round(pageNumber / 2);
//        if (allpage > pageNumber) {
//            if (currentpage > pageCenter) {
//                for (int i = currentpage - pageCenter; i < currentpage + pageCenter && i < allpage; i++) {
//                    pageToShow[i + pageCenter - currentpage] = i + 1;
//                }
//            } else {
//                for (int i = 0; i < pageNumber; i++) {
//                    pageToShow[i] = i + 1;
//                }
//            }
//        } else {
//            for (int i = 0; i < allpage - 1; i++) {
//                pageToShow[i] = i + 1;
//            }
//        }
//        return pageToShow;
//    }
    public static int[] pagingEx(int allpage, int currentpage, int pageNumber) {
        // if page number to much so many, show 'pageNumber' page arround current page
        // all page is all of page number, begin from 0 then endless page is allpage - 1

        int[] pageToShow = null;
        // 1 2 3 4 5 6
// 2 - 2 =0
// 3 + 3
        // 5 1 5
//        System.out.println(allpage + "=" + currentpage + "-" + pageNumber);
        int countAdd = allpage % currentpage;
        if (countAdd > 0) {
            countAdd = 1;
        }
        int countPage = allpage / currentpage + countAdd;
        if (allpage <= currentpage) {

            pageToShow = new int[1];
            pageToShow[0] = 1;
            return pageToShow;
        }
// 23 4 6

        if (pageNumber <= 1) {
            if (countPage > 4) {
                countPage = 4;
            }
            pageToShow = new int[countPage];
            for (int i = 1; i <= countPage; i++) {
                pageToShow[i - 1] = i;
            }

            return pageToShow;
        }
        int pre = pageNumber - 2;
        if (pre < 1) {
            pre = 1;
        }
        int next = pageNumber + 2;
        if (next > countPage) {
            next = countPage;
        }
        pageToShow = new int[next - pre + 1];
        int j = 0;
        for (int i = pre; i <= next; i++) {
            pageToShow[j] = i;
            j++;
        }

        return pageToShow;
    }

    public static int pagingNext(int allpage, int currentpage, int[] pageNumber) {
        int next = 0;
        next = pageNumber[pageNumber.length - 1];
        int countAdd = allpage % currentpage;
        if (countAdd > 0) {
            countAdd = 1;
        }
        int countPage = allpage / currentpage + countAdd;
        if (next < countPage) {
            next++;
        } else {
            next = 0;
        }
        return next;
    }

    /**
     * use in model package's class, when view content on list pages
     * @param type
     * @return
     * type = 0 --> return newest
     * type = 1 --> return the most downloaded
     * type = 2 --> return is hot
     * type = 3 --> return is hot and home --> show in cat
     */
    public static String selectFolType(int type) {
        String wherePara = "";
        switch (type) {
            case 1:
                wherePara = "is_publish = 1 order by downloaded desc";
                break;
            case 2:
                wherePara = "is_publish = 1 and is_hot = 1";
                break;
            case 3:
                wherePara = "is_publish = 1 and is_home = 1 and is_hot = 1";
                break;
            default:
                wherePara = "is_publish = 1 order by updatedate desc";
        }
        return wherePara;
    }

    /**
     * kiem tra 1 ky tu tu 0 --> 9
     * @param c
     * @return
     */
    public static boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    /**
     * check is phone number, 10 || 11 digit
     * @param phonenum
     * @return
     */
    public static boolean isPhoneNumber(String phonenum) {

        boolean isPhone = true;
        if (phonenum.length() >= 9 && phonenum.length() <= 12) {
            char[] phone = phonenum.toCharArray();
            for (int i = 0; i < phone.length; i++) {
                if (!isDigit(phone[i])) {
                    isPhone = false;
                }
            }
        } else {
            isPhone = false;
        }
        return isPhone;
    }

    public static String getSubStringForJobContent(String str, int num) {
        String rs = "";
        if (str.length() > num) {
            char s = str.charAt(num);
            while (s != ' ') {
                num--;
                s = str.charAt(num);
            }
            rs = str.substring(0, num) + "...";
        } else {
            rs = str;
        }
        return rs;
    }

//    //ReqID
//    public static boolean checkReqID(String req) {
//        boolean rvl = false;
//        ORADB6X56Task db = new ORADB6X56Task();
//        //Logger log = new Logger(req)
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        String sql = "select * from tig_6x56_8x98_cp_mo Where Req_ID = '" + req +"'";
//        try {
//            conn = db.makeDBConnect();
//            pstmt = conn.prepareStatement(sql);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                System.out.println("[checkReqID] Check Req_ID!");
//                rvl = true;
//            }
//        } catch (SQLException e) {
//            System.out.println("select error " + sql + " " + e.toString());
//        } catch (Exception e) {
//            System.out.println("exception error " + e.toString());
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (pstmt != null) {
//                    pstmt.close();
//                }
//
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException sqlex) {
//                sqlex.printStackTrace();
//            }
//        }
//        return rvl;
//    }
    /**
     * add new user time line record when who push accept button
     * @param phonenumber
     * @param table
     * @param catid
     * @param addvalue
     */
    public static void addUserInfoLog(String phonenumber, String table, int packageid, int addvalue) {
        java.sql.Connection conn = null;
        PreparedStatement preStmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = ORADBconnect.getConnection();
            // conn.setAutoCommit(false);
            sql =
                    "INSERT INTO TIG_VT_USER_TIMELINE (ID, phonenumber, table_name, packageid, "
                    + "start_time, end_time, ext_info, datetime) "
                    + "VALUES (TIG_VT_USER_TIMELINE_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?)";
            preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, phonenumber);
            preStmt.setString(2, table);
            preStmt.setInt(3, packageid);
            // start time and datetime is current time
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());

            String start_date = sdf.format(calendar.getTime());

            calendar.add(Calendar.DATE, addvalue);
            String end_date = sdf.format(calendar.getTime());
            System.out.println("current is" + start_date + " end date " + end_date);

            preStmt.setDate(4, new java.sql.Date(sdf.parse(start_date).getTime()));
            preStmt.setDate(5, new java.sql.Date(sdf.parse(end_date).getTime()));
            preStmt.setString(6, "");
            preStmt.setDate(7, new java.sql.Date(sdf.parse(start_date).getTime()));

            rs = preStmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("select error " + sql + " " + e.toString());
        } catch (Exception e) {
            System.out.println("exception error " + e.toString());
            //System.out.println("sql:" + sql);
        } finally {
            try {
                releaseConnection(conn, preStmt, rs);
            } catch (Exception sqlex) {
                sqlex.printStackTrace();
            }
        }
    }

    /**
     * check user time line exist or not
     * @param phonenumber
     * @param table
     * @param catid
     * @return
     */
    public static boolean checkAllowInLog(String phonenumber, String table, int packageid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        // neu catid == 0 --> ko check catid,
        if (packageid != 0) {
            sql = "Select * from TIG_VT_USER_TIMELINE where phonenumber = '" + phonenumber
                    + "' and table_name = '" + table + "' and packageid = " + packageid + " and"
                    + " TO_CHAR(Start_time, 'MM/DD/YY HH24:MI:SS') <= to_char(sysdate,'MM/DD/YY HH24:MI:SS') and"
                    + " TO_CHAR(end_time, 'MM/DD/YY HH24:MI:SS') >= to_char(sysdate,'MM/DD/YY HH24:MI:SS')";
        } else {
            sql = "Select * from TIG_VT_USER_TIMELINE where phonenumber = '" + phonenumber
                    + "' and table_name = '" + table + "' and"
                    + " TO_CHAR(Start_time, 'MM/DD/YY HH24:MI:SS') <= to_char(sysdate,'MM/DD/YY HH24:MI:SS') and"
                    + " TO_CHAR(end_time, 'MM/DD/YY HH24:MI:SS') >= to_char(sysdate,'MM/DD/YY HH24:MI:SS')";
        }

        //System.out.println("SQL:" + sql);
        try {
            conn = Connector.getConnection();
            // conn = ORADBconnect.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("select error " + sql + " " + e.toString());
        } catch (Exception e) {
            System.out.println("exception error " + e.toString());
            //System.out.println("sql:" + sql);
        } finally {
            try {
                Connector.closeConnections(conn, pstmt, rs);
            } catch (Exception sqlex) {
                sqlex.printStackTrace();
            }
        }
        return false;
    }

    public static String Timestamp2HHMMSS(java.sql.Timestamp ts) {
        if (ts == null) {
            return "";
        }

        String sHour = "";
        String sMinunute = "";
        String sSecond = "";
        String strTemp = "";

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        // HH
        if (calendar.get(calendar.HOUR_OF_DAY) < 10) {
            sHour = "0" + Integer.toString(calendar.get(calendar.HOUR_OF_DAY));
        } else {
            sHour = "" + Integer.toString(calendar.get(calendar.HOUR_OF_DAY));
        }
        //MM
        if (calendar.get(calendar.MINUTE) < 10) {
            sMinunute = "0" + calendar.get(calendar.MINUTE);
        } else {
            sMinunute = "" + calendar.get(calendar.MINUTE);
        }
        //SS
        if (calendar.get(calendar.SECOND) < 10) {
            sSecond = "0" + calendar.get(calendar.SECOND);
        } else {
            sSecond = "" + calendar.get(calendar.SECOND);
        }

        return (sHour + sMinunute + sSecond);
    }

    /**
     * Return date time used for 24 hour clock: YYYYMMDDHHMMSS
     */
    public static String getYYYYMMDDHHMMSSString(java.sql.Timestamp ts) {
        if (ts == null) {
            return "";
        }
        return Timestamp2YYYYMMDD(ts) + Timestamp2HHMMSS(ts);
    }
    /* Format date : yyyymmdd
     */

    public static String Timestamp2YYYYMMDD(java.sql.Timestamp ts) {
        if (ts == null) {
            return "";
        }
        String sYear = "";
        String sMonth = "";
        String sDay = "";
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date(ts.getTime()));
        // DD
        sDay = "" + Integer.toString(calendar.get(calendar.DAY_OF_MONTH));
        if (calendar.get(calendar.DAY_OF_MONTH) < 10) {
            sDay = "0" + sDay;
        }
        // MM
        if (calendar.get(calendar.MONTH) + 1 < 10) {
            sMonth = "0" + (calendar.get(calendar.MONTH) + 1);
        } else {
            sMonth = "" + (calendar.get(calendar.MONTH) + 1);
        }
        // YYYY
        sYear = "" + calendar.get(calendar.YEAR);

        return sYear + sMonth + sDay;
    }

    public static void main(String args[]) {
//        System.out.println("check req: " + checkReqID("941282549032236"));
    }
}
