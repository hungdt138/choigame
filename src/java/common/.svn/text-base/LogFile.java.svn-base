/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author ThanhTam
 */
import common.Config;
import java.io.*;
import java.sql.Date;
import java.util.Vector;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class LogFile {

    public static String folderCDR = "C:\\tigviettel_cdr";//path_cdr
    public static String folderBIL = "C:\\tigviettel_bil";//path_cdr

    public LogFile() {
    }

    public static boolean SaveFile(String SrcMsisdn, String DesMsisdn, String ServiceId, String Category, String Command, String Pcode, String Contents, String SmsType, String ShortCode, String Amount, String Status, String Datetime, String SubCP, String Other) {

        folderCDR = Config.getString("path_cdr");
        //folderCDR = "C:\\cdr";
        Vector vFile = getAllFiles(new File(folderCDR), ".dcm");
        String fileCdr = "";
        java.sql.Timestamp time = createTimestamp();
        boolean flg = false;
        if (vFile == null || vFile.size() == 0) {
            fileCdr = "DATACP" + getYYYYMMDDHHMMSSString(time) + ".dcm";
            flg = true;
        } else {
            for (int i = 0; i < vFile.size(); i++) {
                fileCdr = ((File) (vFile.elementAt(0))).getName();
                if (fileCdr.startsWith("DATACP")) {
                    flg = true;
                    break;
                }
            }
        }
        if (flg == false) {
            fileCdr = "DATACP" + getYYYYMMDDHHMMSSString(time) + ".dcm";
        }
        return saveDataTable(folderCDR + "\\" + fileCdr, SrcMsisdn, DesMsisdn, ServiceId, Category, Command, Pcode, Contents, SmsType, ShortCode, Amount, Status, Datetime, SubCP, Other);
    }

    public static boolean saveDataTable(String desFile, String SrcMsisdn, String DesMsisdn, String ServiceId, String Category, String Command, String Pcode, String Contents, String SmsType, String ShortCode, String Amount, String Status, String Datetime, String SubCP, String Other) {
        //SrcMsisdn= formatUserId(SrcMsisdn,0);
        String data = SrcMsisdn;
        try {
            java.io.PrintWriter fout = new java.io.PrintWriter(new java.io.FileOutputStream(desFile, true));
            if (DesMsisdn == null || DesMsisdn.length() == 0) {
                DesMsisdn = "";
                //DesMsisdn= formatUserId(DesMsisdn,2);
            }
            data = data + "|" + DesMsisdn;
            if (ServiceId == null || ServiceId.length() == 0) {
                ServiceId = "";
            }

            data = data + "|" + ServiceId;
            if (Category == null || Category.length() == 0) {
                Category = "";
            }
            data = data + "|" + Category;

            if (Command == null || Command.length() == 0) {
                Command = "";
            }

            data = data + "|" + Command;

            if (Pcode == null || Pcode.length() == 0) {
                Pcode = "";
            }
            data = data + "|" + Pcode;

            if (Contents == null || Contents.length() == 0) {
                Contents = "";
            }
            data = data + "|" + Contents;

            if (SmsType == null || SmsType.length() == 0) {
                SmsType = "";
            }
            data = data + "|" + SmsType;

            if (ShortCode == null || ShortCode.length() == 0) {
                ShortCode = "";
            }
            data = data + "|" + ShortCode;

            if (Amount == null || Amount.length() == 0) {
                Amount = "";
            }
            data = data + "|" + Amount;


            if (Status == null || Status.length() == 0) {
                Status = "";
            }
            data = data + "|" + Status;

            if (Datetime == null || Datetime.length() == 0) {
                Datetime = "";
            }
            data = data + "|" + Datetime;

            if (SubCP == null || SubCP.length() == 0) {
                SubCP = "";
            }
            data = data + "|" + SubCP;


            if (Other == null || Other.length() == 0) {
                Other = "";
            }
            data = data + "|" + Other;


            fout.println(data);
            fout.close();
            System.out.println(" save data complete !");

        } catch (FileNotFoundException ex) {
            System.out.println("erorr :" + ex.toString());
            return false;
        }
        // append = false
        return true;
    }

    public static boolean SaveCDRFile(String msisdn, String contentType, String command, String datetime, String money, String subCP) {

        folderBIL = Config.getString("path_bil");
        //folderCDR = "C:\\cdr";
        Vector vFile = getAllFiles(new File(folderBIL), ".bil");
        String fileCdr = "";
        java.sql.Timestamp time = createTimestamp();
        boolean flg = false;
        if (vFile == null || vFile.size() == 0) {
            fileCdr = "DATACP" + getYYYYMMDDHHMMSSString(time) + ".bil";
            flg = true;
        } else {
            for (int i = 0; i < vFile.size(); i++) {
                fileCdr = ((File) (vFile.elementAt(0))).getName();
                if (fileCdr.startsWith("DATACP")) {
                    flg = true;
                    break;
                }
            }
        }
        if (flg == false) {
            fileCdr = "DATACP" + getYYYYMMDDHHMMSSString(time) + ".bil";
        }
        return saveDataCDR(folderBIL + "\\" + fileCdr, msisdn, contentType, command, datetime, money, subCP);
    }

    public static boolean saveDataCDR(String desFile, String msisdn, String contentType, String command, String datetime, String money, String subCP) {
        //SrcMsisdn= formatUserId(SrcMsisdn,0);
        String data = msisdn;
        try {
            java.io.PrintWriter fout = new java.io.PrintWriter(new java.io.FileOutputStream(desFile, true));
            if (contentType == null || contentType.length() == 0) {
                contentType = "";
            }
            data = data + "|" + contentType;

            if (command == null || command.length() == 0) {
                command = "";
            }

            if (datetime == null || datetime.length() == 0) {
                datetime = "";
            }
            data = data + "|" + datetime;

            if (money == null || money.length() == 0) {
                money = "";
            }
            data = data + "|" + money;

            if (subCP == null || subCP.length() == 0) {
                subCP = "";
            }
            data = data + "|" + subCP;

            fout.println(data);
            fout.close();
            System.out.println(" save cdr data complete !");

        } catch (FileNotFoundException ex) {
            System.out.println("erorr :" + ex.toString());
            return false;
        }
        // append = false
        return true;
    }

    public static String formatUserId(String userId, int formatType) {
        if (userId == null || "".equals(userId)) {
            return null;
        }
        String temp = userId;
        switch (formatType) {
            case 0:
                if (temp.startsWith("9")) {
                    temp = "84" + temp;
                } else if (temp.startsWith("09")) {
                    temp = "84" + temp.substring(1);
                } // else startsWith("84")
                break;
            case 2:
                if (temp.startsWith("84")) {
                    temp = temp.substring(2);
                } else if (temp.startsWith("09")) {
                    temp = temp.substring(1);
                } // else startsWith("9")
                break;
            case 1:
                if (temp.startsWith("84")) {
                    temp = "0" + temp.substring(2);
                } else if (temp.startsWith("9")) {
                    temp = "0" + temp;
                } // else startsWith("09")
                break;
            default:
                System.out.println("formatUserId: Invalid userId format_type " +
                        formatType);
                return null;
        }
        return temp;
    }

    public static Vector getAllFiles(java.io.File location, String fileExt) {
        Vector v = new Vector();
        java.io.File[] list = location.listFiles();
        if (list != null && list.length > 0) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].toString().endsWith(fileExt)) {
                    v.addElement(list[i]);
                }
            }
        }
        return v;
    }

    public static java.sql.Timestamp createTimestamp() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        return new java.sql.Timestamp((calendar.getTime()).getTime());
    }

    public static String getYYYYMMDDHHMMSSString(java.sql.Timestamp ts) {
        if (ts == null) {
            return "";
        }
        return Timestamp2YYYYMMDD(ts) + Timestamp2HHMMSS(ts);
    }

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

    public static void main(String dfd[]) {

        SaveFile("8498", "098", "tunglt", "tunglt", "tunglt", "tunglt", "tunglt", "1", "tunglt", "tunglt", "0", "tunglt", "tunglt", "tunglt");
    }
}
