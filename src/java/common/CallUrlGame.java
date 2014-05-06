/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import m_operator.OperatorConfig;
import model.bean.FMC_MO;
import model.bean.FMC_MT;
import model.bean.IC;
import model.bean.IC_MT;
import model.bean.KRA_MO;
import model.bean.KRA_MT;
import model.bean.LT_MO;
import model.bean.LT_MT;
import model.bean.MV_MO;
import model.bean.MV_MT;
import model.bean.VNM_MO;
import model.bean.VNM_MT;
import model.bean.VTC_MO;
import model.bean.VTC_MT;
import model.dao.FMCDAO;
import model.dao.ICDAO;
import model.dao.KRADAO;
import model.dao.LTDAO;
import model.dao.MVDAO;
import model.dao.VNMDAO;
import model.dao.VTCDao;

/**
 *
 * @author noname
 */
public class CallUrlGame {

    /*
     * ServiceId
     * Choigame viettel: 1 - Choigame vms: 2 - Liveinfo: 3 - Mobix viettel: 4 - 
     * Mobix vms: 5 - Mua chung game: 6 - Mdeal: 7 - Mgame: 8 - Upro: 9
     */
    private int ServiceId = 2;

    public String callURL(String url) {
        String strURL = "";
        if (url.length() > 0 && !url.equals("")) {
            try {
                // Create a URL for the desired page

                URL _url = new URL(url);//"http://www5.dantri.com.vn/giaitri/2006/7/127603.vip"
                // Read all the text returned by the server
                BufferedReader in = new BufferedReader(new InputStreamReader(_url.openStream()));
                String str;
                //System.out.println(in.toString());
                while ((str = in.readLine()) != null) {
                    System.out.println(str);
                    strURL = strURL + str + "";
                }
                in.close();
            } catch (MalformedURLException e) {
                //ko tim thay', or sai url
                System.out.println("Khong mo duoc url!");
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
        return strURL;
    }

    private String getXMLTagValue(String xml, String section) throws Exception {
        String xmlString = xml;
        String value = "";
        if (!xml.equals("") && xml.length() > 0) {

            String beginTagToSearch = "<" + section + ">";
            String endTagToSearch = "</" + section + ">";

            // Look for the first occurrence of begin tag
            int bindex = xmlString.indexOf(beginTagToSearch);
            int eindex = xmlString.indexOf(endTagToSearch);
            if ((bindex != -1) && (eindex != -1)) {
                value = xml.substring(bindex + beginTagToSearch.length(), eindex);
            }
        }
        return value;
    }

    public static String MD5Hex(String s) {
        String result = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(s.getBytes());
            result = toHex(digest);
        } catch (NoSuchAlgorithmException e) {
            // this won't happen, we know Java has MD5!
        }
        return result;
    }

    public static String toHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (int i = 0; i < a.length; i++) {
            sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
            sb.append(Character.forDigit(a[i] & 0x0f, 16));
        }
        return sb.toString();
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

    public static String getYYYYMMDDHHMMSSString(java.sql.Timestamp ts) {
        if (ts == null) {
            return "";
        }
        return Timestamp2YYYYMMDD(ts) + Timestamp2HHMMSS(ts);
    }

    ////////////////////////////////////////////////////////////
    ///////////Get FMC URL
    ////////////////////////////////////////////////////////////
    public String getFMCURL(String phone_number, String gameID) {
        String downloadurl = "";
        String fmcURL = common.Config.getString("fmcUrl");
        String xmlStr = "";

        //Get Game download link from FMC, call fmcUrl
        java.sql.Timestamp ts = new Timestamp(System.currentTimeMillis());
        java.lang.String reqTime = getYYYYMMDDHHMMSSString(ts);

        fmcURL = fmcURL.replace("xxx", reqTime).replace("uuu", phone_number).replace("vvv", gameID);
        System.out.println("[FMC URL]: " + fmcURL);

        //Log FMC MO
        FMCDAO fmcDAO = new FMCDAO();
        FMC_MO fmc = new FMC_MO();
        fmc.setTxid(reqTime);
        fmc.setCusid("tekciz");
        fmc.setCuspwd("550541zicket");
        fmc.setMobileno(phone_number);
        fmc.setMsg(gameID);
        fmc.setServiceid(ServiceId);
        fmcDAO.insertFMC(fmc);

        xmlStr = callURL(fmcURL);
        //Goi lai 5 lan, neu xmlStr --> null
        int n = 0;
        while (xmlStr == null && n < 5) {
            xmlStr = callURL(fmcURL);
            n++;
        }
        if (xmlStr != null) {
            //Log FMC_MT----
            try {
                String cusid = getXMLTagValue(xmlStr, "dk_cusid");
                String statuscode = getXMLTagValue(xmlStr, "dk_statuscode");
                String statusmsg = getXMLTagValue(xmlStr, "dk_status");
                String contenturl = getXMLTagValue(xmlStr, "dk_content");
                String dnid = getXMLTagValue(xmlStr, "dk_dnid");
                String fullmsg = xmlStr;

                FMC_MT fmc_mt = new FMC_MT();
                fmc_mt.setCusid(cusid);
                fmc_mt.setStatuscode(statuscode);
                fmc_mt.setStatusmsg(statusmsg);
                fmc_mt.setContenturl(contenturl);
                fmc_mt.setDnid(dnid);
                fmc_mt.setFullmsg(fullmsg);
                fmcDAO.insertFMC_MT(fmc_mt);

                //Log MGAME after
                if (contenturl != null && contenturl.startsWith("http://")) {
                    downloadurl = contenturl;
                } else {
                    downloadurl = "http://" + contenturl;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("[MGAME]: " + downloadurl);
        }

        return downloadurl;
    }

    ////////////////////////////////////////////////////////////
    ///////////Get KRA URL
    ////////////////////////////////////////////////////////////
    public String getKRAURL(String msisdn, String gameid) {
        String downloadurl = "";
        String kraURL = common.Config.getString("kraUrl");
        String xmlStr = "";
        //Get Game download link from KRA
        java.sql.Timestamp ts = new Timestamp(System.currentTimeMillis());
        java.lang.String reqTime = getYYYYMMDDHHMMSSString(ts);

        kraURL = kraURL.replace("xxx", reqTime).replace("uuu", msisdn).replace("vvv", gameid);
        System.out.println("[KRA URL]: " + kraURL);

        //Log KRA MO
        KRADAO kraDAO = new KRADAO();
        KRA_MO kra = new KRA_MO();
        kra.setTxid(reqTime);
        kra.setCusid("TCK");
        kra.setCuspwd("TCK");
        kra.setMobileno(msisdn);
        kra.setMsg(gameid);
//        kraDAO.insertKRA_MO(kra);

        xmlStr = callURL(kraURL);
        //Goi lai 5 lan, neu xmlStr --> null
        int n = 0;
        while (xmlStr == null && n < 5) {
            xmlStr = callURL(kraURL);
            n++;
        }
        if (xmlStr != null) {
            //Log KRA_MT----
            String cusid = "NOT_SET";//getXMLTagValue(xmlStr, "dk_cusid");
            String statuscode = "NOT_SET";//getXMLTagValue(xmlStr, "dk_statuscode");
            String statusmsg = "NOT_SET";//getXMLTagValue(xmlStr, "dk_statusmsg");
            String contenturl = xmlStr.trim();//getXMLTagValue(xmlStr, "dk_contenturl");
            String dnid = "NOT_SET";//getXMLTagValue(xmlStr, "dk_dnid");
            String fullmsg = xmlStr;

            KRA_MT kra_mt = new KRA_MT();
            kra_mt.setCusid(cusid);
            kra_mt.setStatuscode(statuscode);
            kra_mt.setStatusmsg(statusmsg);
            kra_mt.setContenturl(contenturl);
            kra_mt.setDnid(dnid);
            kra_mt.setFullmsg(fullmsg);
            //kraDAO.in(kra_mt);

            //Log MGAME after
            if (contenturl != null) {
                if (contenturl.startsWith("http://")) {
                    downloadurl = contenturl;
                } else {
                    downloadurl = "http://" + contenturl;
                }
            } else {
                downloadurl = "-1";
            }
            System.out.println("[KRA DOWNLOAD URL]: " + downloadurl);
        } else {
            KRA_MT kra_mt = new KRA_MT();
            kra_mt.setCusid("NOT_SET");
            kra_mt.setStatuscode("NOT_SET");
            kra_mt.setStatusmsg("NOT_SET");
            kra_mt.setContenturl("NOT_SET");
            kra_mt.setDnid("NOT_SET");
            kra_mt.setFullmsg("NOT_SET");
//            kraDAO.insertKRA_MT(kra_mt);

            downloadurl = "-1";
        }

        return downloadurl;
    }

    ////////////////////////////////////////////////////////////
    ///////////Get VNM URL
    ////////////////////////////////////////////////////////////
    public String getVNMURL(String msisdn, String gameid) {
        String downloadurl = "";
        //Log KRA MO
        VNMDAO vnmDAO = new VNMDAO();

        String vnmURL = common.Config.getString("vnmUrl");
        String xmlStr = "";
        //Get Game download link from KRA
        java.sql.Timestamp ts = new Timestamp(System.currentTimeMillis());
        java.lang.String reqTime = getYYYYMMDDHHMMSSString(ts);

        int moID = vnmDAO.getVNMMO_ID() + 1;

        vnmURL = vnmURL.replace("xxx", String.valueOf(moID)).replace("uuu", msisdn).replace("vvv", gameid);
        System.out.println("[VNM URL]: " + vnmURL);

        VNM_MO vnm = new VNM_MO();
        vnm.setId(moID);
        vnm.setTxid(reqTime);
        vnm.setCusid("teckciz");
        vnm.setCuspwd("tc.2112$");
        vnm.setMobileno(msisdn);
        vnm.setMsg(gameid);
        vnm.setServiceid(ServiceId);

        vnmDAO.insertVNM_MO(vnm);

        xmlStr = callURL(vnmURL);
        //Goi lai 5 lan, neu xmlStr --> null
        int n = 0;
        while (xmlStr == null && n < 5) {
            xmlStr = callURL(vnmURL);
            n++;
        }
        if (xmlStr != null) {
            try {
                //Log VNM_MT----
                String cusid = "NOT_SET";//getXMLTagValue(xmlStr, "dk_cusid");
                String statuscode = "NOT_SET";//getXMLTagValue(xmlStr, "dk_statuscode");
                String statusmsg = getXMLTagValue(xmlStr, "Status");
                String contenturl = getXMLTagValue(xmlStr, "Link");
                String dnid = "NOT_SET";//getXMLTagValue(xmlStr, "dk_dnid");
                String fullmsg = xmlStr;

                VNM_MT mt = new VNM_MT();
                mt.setCusid(cusid);
                mt.setStatuscode(statuscode);
                mt.setStatusmsg(statusmsg);
                mt.setContenturl(contenturl);
                mt.setDnid(dnid);
                mt.setFullmsg(fullmsg);
                vnmDAO.insertVNM_MT(mt);

                //Log MGAME after
                if (contenturl != null) {
                    if (contenturl.startsWith("http://")) {
                        downloadurl = contenturl;
                    } else {
                        downloadurl = "http://" + contenturl;
                    }

                    downloadurl = downloadurl.replace("%2F", "/").replace("%3F", "?").replace("%3D", "=");

                } else {
                    downloadurl = "-1";
                }
                System.out.println("[VNM DOWNLOAD URL]: " + downloadurl);
            } catch (Exception ex) {
                ex.printStackTrace();
                downloadurl = "-1";
            }
        } else {
            VNM_MT mt = new VNM_MT();
            mt.setCusid("NOT_SET");
            mt.setStatuscode("NOT_SET");
            mt.setStatusmsg("NOT_SET");
            mt.setContenturl("NOT_SET");
            mt.setDnid("NOT_SET");
            mt.setFullmsg("NOT_SET");
            vnmDAO.insertVNM_MT(mt);

            downloadurl = "-1";
        }

        return downloadurl;
    }

    /**
     *
     * @param msisdn
     * @param gameid
     * @return Tra ve link download cua doi tac VTC
     */
   public String getVTCURL(String msisdn, String gameid) throws IOException {
        String downloadurl = "";
        String vtcURL = common.Config.getString("vtcUrl");
        String xmlStr = "";
        model.dao.VTCDao vtcDAO = new VTCDao();
        //Get Game download link from VTC
        java.sql.Timestamp ts = new Timestamp(System.currentTimeMillis());
        java.lang.String reqTime = getYYYYMMDDHHMMSSString(ts);
        vtcURL = vtcURL.replace("rrr", reqTime).replace("uuu", msisdn).replace("vvv", gameid).replace("xxx", vtcDAO.getReqID());
        System.out.println("[VTC URL]: " + vtcURL);
        //Log VTC MO
        VTC_MO vtc = new VTC_MO();
        vtc.setTxid(reqTime);
        vtc.setCusid("tekciz");
        vtc.setCuspwd("tekciz@270420!1");
        vtc.setMobileno(msisdn);
        vtc.setMsg(gameid);
        vtc.setServiceid(ServiceId);
        vtcDAO.insertVTC_MO(vtc);

        xmlStr = callURL(vtcURL);
        //Goi lai 5 lan, neu xmlStr --> null
        int n = 0;
        while (xmlStr == null && n < 5) {
            xmlStr = callURL(vtcURL);
            n++;
        }
        if (xmlStr != null) {
            //Log VTC_MT----
            String cusid = "NOT_SET";//getXMLTagValue(xmlStr, "dk_cusid");
            String statuscode = "NOT_SET";//getXMLTagValue(xmlStr, "dk_statuscode");
            String statusmsg = "NOT_SET";//getXMLTagValue(xmlStr, "dk_statusmsg");
            String contenturl = xmlStr.trim();//getXMLTagValue(xmlStr, "dk_contenturl");
            String dnid = "NOT_SET";//getXMLTagValue(xmlStr, "dk_dnid");
            String fullmsg = xmlStr;

            VTC_MT vtc_mt = new VTC_MT();
            vtc_mt.setCusid(cusid);
            vtc_mt.setStatuscode(statuscode);
            vtc_mt.setStatusmsg(statusmsg);
            vtc_mt.setContenturl(contenturl);
            vtc_mt.setDnid(dnid);
            vtc_mt.setFullmsg(fullmsg);
            vtcDAO.insertVTC_MT(vtc_mt);

            //Log MGAME after
            if (contenturl != null) {
                if (contenturl.startsWith("http://")) {
                    downloadurl = contenturl;
                } else {
                    downloadurl = "http://" + contenturl;
                }
            } else {
                downloadurl = "-1";
            }
            System.out.println("[VTC DOWNLOAD URL]: " + downloadurl);
        } else {
            VTC_MT vtc_mt = new VTC_MT();
            vtc_mt.setCusid("NOT_SET");
            vtc_mt.setStatuscode("NOT_SET");
            vtc_mt.setStatusmsg("NOT_SET");
            vtc_mt.setContenturl("NOT_SET");
            vtc_mt.setDnid("NOT_SET");
            vtc_mt.setFullmsg("NOT_SET");
            vtcDAO.insertVTC_MT(vtc_mt);

            downloadurl = "-1";
        }

        return downloadurl;
    }

    /**
     *
     * @param msisdn
     * @param gameid
     * @return  Tra ve link download cua doi tac MV
     */
    public String getMVURL(String msisdn, String gameid) {

        String downloadurl = "";
        //Log KRA MO
        MVDAO mvDAO = new MVDAO();

        String mvURL = common.Config.getString("mvUrl");
        String xmlStr = "";
        //Get Game download link from KRA
        java.sql.Timestamp ts = new Timestamp(System.currentTimeMillis());
        java.lang.String reqTime = getYYYYMMDDHHMMSSString(ts);

        int moID = mvDAO.getMV_MO_ID() + 1;
        String operator = OperatorConfig.getMobileOperator(msisdn);
        mvURL = mvURL.replace("xxx", String.valueOf(moID)).replace("uuu", msisdn).replace("vvv", gameid).replace("rrr", reqTime).replace("ooo", operator);
        System.out.println("[MV URL]: " + mvURL);

        MV_MO mv = new MV_MO();
        mv.setId(moID);
        mv.setTxid(reqTime);
        mv.setCusid("tekciz");
        mv.setCuspwd("tekciz@270420!1");
        mv.setMobileno(msisdn);
        mv.setMsg(gameid);
        mv.setServiceid(ServiceId);

        mvDAO.insertMV_MO(mv);

        xmlStr = callURL(mvURL);
        //Goi lai 5 lan, neu xmlStr --> null
        int n = 0;
        while (xmlStr == null && n < 5) {
            xmlStr = callURL(mvURL);
            n++;
        }
        if (xmlStr != null) {
            try {
                //Log MV_MT----
                String cusid = "NOT_SET";//getXMLTagValue(xmlStr, "dk_cusid");
                String statuscode = "NOT_SET";//getXMLTagValue(xmlStr, "dk_statuscode");
                String statusmsg = "NOT_SET";
                String contenturl = xmlStr.trim();
                String dnid = "NOT_SET";//getXMLTagValue(xmlStr, "dk_dnid");
                String fullmsg = xmlStr;

                MV_MT mt = new MV_MT();
                mt.setCusid(cusid);
                mt.setStatuscode(statuscode);
                mt.setStatusmsg(statusmsg);
                mt.setContenturl(contenturl);
                mt.setDnid(dnid);
                mt.setFullmsg(fullmsg);
                mvDAO.insertMV_MT(mt);

                //Log MGAME after
                if (contenturl != null) {
                    if (contenturl.equalsIgnoreCase("104") == false && contenturl.equalsIgnoreCase("105") == false && contenturl.equalsIgnoreCase("106") == false) {
                        if (contenturl.startsWith("http://")) {
                            downloadurl = contenturl;
                        } else {
                            downloadurl = "http://" + contenturl;
                        }
                    } else {
                        downloadurl = contenturl;
                    }
                    downloadurl = downloadurl.replace("%2F", "/").replace("%3F", "?").replace("%3D", "=");

                } else {
                    downloadurl = "-1";
                }
                System.out.println("[MV DOWNLOAD URL]: " + downloadurl);
            } catch (Exception ex) {
                ex.printStackTrace();
                downloadurl = "-1";
            }
        } else {
            MV_MT mt = new MV_MT();
            mt.setCusid("NOT_SET");
            mt.setStatuscode("NOT_SET");
            mt.setStatusmsg("NOT_SET");
            mt.setContenturl("NOT_SET");
            mt.setDnid("NOT_SET");
            mt.setFullmsg("NOT_SET");
            mvDAO.insertMV_MT(mt);

            downloadurl = "-1";
        }
        return downloadurl;
    }

    //get IC URL
    public String getICURL(String msisdn, String gameid) throws IOException, SQLException {
        String downloadurl = "";
        //log IC MO
        ICDAO icDAO = new ICDAO();
        String icUrl = common.Config.getString("icUrl");
        String xmlStr = "";
        //get game download from IC

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String reqTime = getYYYYMMDDHHMMSSString(ts);

        int moID = icDAO.getICMO_ID() + 1;

        icUrl = icUrl.replace("mmmm", msisdn).replace("gggg", gameid).replace("qqqq", String.valueOf(moID)).replace("rtime", reqTime);

        System.out.println("[ICHIP URL]:" + icUrl);

        IC ic = new IC();
        ic.setID(moID);
        ic.setCusid("tig");
        ic.setCuspwd("tig123");
        ic.setTxid(reqTime);
        ic.setMobileno(msisdn);
        ic.setMsg(gameid);
        ic.setServiceid(ServiceId);

        icDAO.insertICMO(ic);

        xmlStr = callURL(icUrl);
        //goi lai 5 lan new xmlStr = null
        int n = 0;
        while (xmlStr == null && n < 5) {
            xmlStr = callURL(icUrl);
            n++;
        }

        if (xmlStr != null) {
            try {
                //log IC MT
                String cusid = "NOTSET";
                String statuscode = "NOTSET";
                String statusmsg = "NOTSET";
                String contentUrl = xmlStr.trim();
                String dnid = "NOTSET";
                String fullmsg = xmlStr;

                IC_MT icmt = new IC_MT();
                icmt.setContenturl(contentUrl);
                icmt.setCusid(cusid);
                icmt.setDnid(dnid);
                icmt.setFullmsg(fullmsg);
                icmt.setStatuscode(statuscode);
                icmt.setStatusmsg(statusmsg);

                icDAO.inserIC_MT(icmt);
                System.out.println("IC game full result is: " + xmlStr);

                //log icmo after
                if (contentUrl != null) {
                    if (contentUrl.startsWith("http://")) {
                        downloadurl = contentUrl;
                    } else {
                        downloadurl = "http://" + contentUrl;
                    }

                    downloadurl = downloadurl.replace("%2F", "/").replace("%3F", "?").replace("%3D", "=");
                } else {
                    downloadurl = "-1";
                }

                System.out.println("[ICHIP DOWNLOAD URL]: " + downloadurl);
            } catch (Exception ex) {
                ex.printStackTrace();
                downloadurl = "-1";
            }
        } else {

            IC_MT icmt = new IC_MT();
            icmt.setCusid("NOTSET");
            icmt.setStatuscode("NOT_SET");
            icmt.setStatusmsg("NOT_SET");
            icmt.setContenturl("NOT_SET");
            icmt.setDnid("NOT_SET");
            icmt.setFullmsg("NOT_SET");
            icDAO.inserIC_MT(icmt);

            downloadurl = "-1";
        }

        return downloadurl;
    }

    public String getURLLT(String msisdn, String gameid) throws IOException {
        String downloadurl = "";
        String LTUrl = common.Config.getString("ltUrl");
        String xmlStr = "";
        //Get download link from Lotus

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String regTime = getYYYYMMDDHHMMSSString(ts);
        String partner = "tekciz";
        String pass = "ciztek!45727$";
        String partnerid = "211";
        String sign = MD5Hex(gameid + partner + regTime + pass + partnerid);
        LTUrl = LTUrl.replace("bbb", regTime).replace("ddd", msisdn).replace("aaa", gameid).replace("ccc", sign);
        System.out.println("URL: " + LTUrl);

        //log FMC MO
        LTDAO ltDAO = new LTDAO();
        LT_MO lt = new LT_MO();
        lt.setTxid(regTime);
        lt.setCusid(partner);
        lt.setCuspwd(pass);
        lt.setMobileno(msisdn);
        lt.setMsg(gameid);
        lt.setServiceid(ServiceId);
        ltDAO.insertLT(lt);
        xmlStr = callURL(LTUrl);

        int n = 0;
        while (xmlStr == null && n < 5) {
            xmlStr = callURL(LTUrl);
            n++;
        }

        if (xmlStr != null) {
            //Log FMC_MT----
            try {
                String cusid = "tekciz";
                String statuscode = getXMLTagValue(xmlStr, "dk_statuscode");
                String statusmsg = getXMLTagValue(xmlStr, "dk_status");
                String pr = getXMLTagValue(xmlStr, "Message") + "</Message>";
                String contenturl = getXMLTagValue(pr, "Message");
                System.out.println(contenturl);
                String dnid = "NO VALUE";
                String fullmsg = xmlStr;

                LT_MT mt = new LT_MT();
                mt.setCusid(cusid);
                mt.setStatuscode(statuscode);
                mt.setStatusmsg(statusmsg);
                mt.setContenturl(contenturl);
                mt.setDnid(dnid);
                mt.setFullmsg(fullmsg);
                ltDAO.insertLT_MT(mt);

                //Log MGAME after
                if (contenturl != null && contenturl.startsWith("http://")) {
                    downloadurl = contenturl;
                } else {
                    downloadurl = "http://" + contenturl;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("[MGAME]: " + downloadurl);
        }
        return downloadurl;

    }
}
