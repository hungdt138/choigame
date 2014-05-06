/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.viettel.xsd.ResultResponse;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.*;
import model.dao.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import call_ws_viettel.CallWS_TIG_8x98;
import call_ws_viettel.Main;
import com.tekciz.service.MTSender;
import common.CallUrlGame;

/**
 *
 * @author Do Tien Hung
 */
public class ChargeController implements Controller {

    private GrouponDAO gDAO;
    private CallUrlGame cUrl;

    public ChargeController() {
        gDAO = new GrouponDAO();
        cUrl = new CallUrlGame();
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map myModel = new HashMap();
        String ip = "";
        String user_agent = request.getHeader("user-agent");
        if (user_agent.contains("Opera")) {
            ip = request.getHeader("X-FORWARDED-FOR");
        }
        if ("".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        System.out.println("[Choigame][IP of mobile]: " + ip);
        myModel.put("ip", ip);

        String x_wap_profile = request.getHeader("x-wap-profile");
        System.out.println("[CHOIGAME CHARGING]................");
        String msisdn = request.getHeader("msisdn");
        //String msisdn = "0983171490";
        System.out.println("[Choigame]Header msisdn: " + msisdn);

        Main ws_vt = new Main();
        String username = "tekciz";
        String password = "tekcis20may";
        if (msisdn == null || msisdn.length() == 0) {
            msisdn = request.getHeader("X-WAP-MSISDN");
            System.out.println("[Choigame]X-WAP-MSISDN: " + msisdn);
        }
        if (msisdn == null || msisdn.length() == 0) {
            ResultResponse rp = ws_vt.getMSISDN("datacp", "datacp%$#12", ip);
            myModel.put("code", rp.getCode());
            myModel.put("desc", rp.getDesc());
            if (rp.getCode() == 0) {
                msisdn = rp.getDesc();
            } else {
                //fix so
                msisdn = "";
            }

        }

        myModel.put("msisdn", msisdn);
        System.out.println("[ChoiGame]user_angent: " + user_agent);
        System.out.println("[Choigame]x_wap_profile: " + x_wap_profile);
        System.out.println("[ChoiGame]msisdn: " + msisdn);

        //url
        String home_page = common.Config.getString("home_page");
        if (home_page == null || "".equals(home_page)) {
            System.out.println("Homepage:  null");
            home_page = "http://v.mobix.vn/choigame";
        }

        String send_ok = common.Config.getString("send_ok");
        if (send_ok == null || "".equals(send_ok)) {
            System.out.println("[ChoiGame][send_ok]: null");
            send_ok = "http://v.mobix.vn/choigame/sendresult.html?status=1&pn=";
        }
        String send_false = common.Config.getString("send_false");
        if (send_false == null || "".equals(send_false)) {
            System.out.println("[ChoiGame][send_false]: null");
            send_false = "http://v.mobix.vn/choigame/sendresult.html?status=0&pn=";
        }
        String url = request.getParameter("url");


        if (url != null && url.length() > 0) {
            url = url.trim().replace(" ", "%20").replace("zzz", "&");
        }
        System.out.println("[VIETTEL Data link]: " + url);
        String pr = request.getParameter("pr");
        //String cat = request.getParameter("cat");
        String friendphone = request.getParameter("pn");
        String phone_number = "";
        String friend = "";
        String free_phone_list = common.Config.getString("free_phone_list");

        String contents = "";
        String id = request.getParameter("id");
        if (id == null) {
            id = "";
        }
        String pid = request.getParameter("pId");
        String pcode = "";
        String cat = request.getParameter("catid");

        if ("".equals(id)) {
            pcode = cat + ":" + pid;
        } else {
            pcode = cat + ":" + id;
        }
        int packid = 0;
        if (pid != null) {
            packid = Integer.parseInt(pid);
        }
        String code = "";
        //Process Groupon Game
        String type = request.getParameter("type");
        if ("g".equalsIgnoreCase(type)) {
            System.out.println("-----------[CHOIGAME - GROUPON] --------------");
            code = gDAO.GenerateCode(Integer.parseInt(id));
            url = "grouponreturn.html?id=" + id + "&msisdn=" + msisdn + "";
            System.out.println("Customer: " + msisdn);
            System.out.println("Code " + msisdn + " is: " + code);
        }

        if (isMobileNumber(friendphone)) { // neu la gui tang ban be
            try {
                if (url != null && pr != null && !"".equals(url) && !"".equals(pr) && cat != null) {
                    if (ip != null) {
                        if (isMobileNumber(msisdn)) {
                            //luu vao log 
                            ChargeLogDAO log = new ChargeLogDAO();
                            phone_number = msisdn;
                            if (msisdn.startsWith("84")) {
                                phone_number = msisdn.substring(2);
                            } else if (msisdn.startsWith("0")) {
                                phone_number = msisdn.substring(1);
                            } else if (msisdn.startsWith("+84")) {
                                phone_number = msisdn.substring(3);
                            }
                            friend = friendphone;
                            if (friendphone.startsWith("0")) {
                                friend = friendphone.substring(1);
                            } else if (friendphone.startsWith("84")) {
                                friend = friendphone.substring(2);
                            } else if (friendphone.startsWith("+84")) {
                                friend = friendphone.substring(3);
                            }

                            String p = pr.trim();
                            String charging = pr.trim();
                            System.out.println("$$$$$Price: " + charging);

                            try {
                                int ipr = Integer.parseInt(p);
                                if (ipr > 30000) {
                                    charging = "30000";
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                charging = pr.trim();
                            }


                            username = "tekciz";
                            password = "tekcis20may";

                            Timestamp ts = new Timestamp(System.currentTimeMillis());
                            String reqTime = getYYYYMMDDHHMMSSString(ts);

                            String providerid = "DATACP";
                            String serviceId = "TEKCIZ";
                            String cmd = "DOWNLOAD";



                            Integer requestId = Integer.valueOf(1);
                            String[] ct = getContents(cat);
                            ChargePDU chPDU = new ChargePDU();
                            //Log Pre
                            chPDU.setMsisdn(msisdn);
                            chPDU.setCharging(charging);
                            chPDU.setUsername(username);
                            chPDU.setPassword(password);

                            chPDU.setRegTime(ts);

                            chPDU.setProviderid(providerid);
                            chPDU.setServiceid(serviceId);
                            chPDU.setCmd(cmd);
                            chPDU.setContents(ct[2]);
                            chPDU.setRequesid(requestId);
                            chPDU.setReturn_values("PRE");
                            chPDU.setPcode(pcode);
                            chPDU.setContentid(Integer.parseInt(id));

                            log.insertRow_Pre(chPDU);

                            //Charge
                            String result = "";
                            // String result = "0";
                            int check = log.checkMSISDNByTime(msisdn, pcode);

                            if (check == 0) {
                                result = ws_vt.processCharging(phone_number, charging, username, password, reqTime, providerid,
                                        serviceId, cmd, ct[2], requestId);

                                System.out.println("$$$$$Charge " + phone_number + " result: " + result);

                                //Log DB//////////////////////////////////////////////
                                chPDU.setReturn_values(result);

                                log.insertRow(chPDU);

                                if (result != null) {
                                    //Log file ////////////////////////////////
                                    Calendar calendar = Calendar.getInstance();
                                    Date now = calendar.getTime();
                                    Timestamp timeStamp = new Timestamp(now.getTime());
                                    String currentTime = common.LogFile.getYYYYMMDDHHMMSSString(timeStamp);
                                    if (currentTime == null || "".equals(currentTime)) {
                                        currentTime = "YYYYMMDDHHMMSSS";
                                    }

//                                  
                                    //Log file .bil
                                    if (!phone_number.equals("") && phone_number != null) {
                                        //common.LogFile.SaveCDRFile(phone_number, contents, "DOWNLOAD", currentTime, charging, "TEKCIZ");
                                        common.LogFile.SaveCDRFile(phone_number, ct[0], "DOWNLOAD", currentTime, charging.trim(), "TEKCIZ");
                                    }

                                    //Log file .dcm //////////////////////////////////////////

                                    if (!phone_number.equals("") && !friend.equals("") && phone_number != null && friend != null) {
                                        contents = "Duoc tang " + ct[1] + ";" + log.getContents(cat, id);
                                        System.out.println("[Contents]: " + contents);
                                        common.LogFile.SaveFile(phone_number, friend, "TEKCIZ", ct[0], "DOWNLOAD", cat + id, contents, "", "8098", charging.trim(), result, currentTime, "TEKCIZ", "");
                                    }
                                    if (result.equals("0")) {//Neu charge thanh cong
                                        //Gui link thong bao cho Nguoi duoc tang
                                        GameDAO gdao = new GameDAO();
                                        String downloaded = gdao.insertDownloaded(Integer.parseInt(id));
                                        System.out.println(downloaded);

                                        //Send link download
                                        if (url.startsWith("FMC") && url.indexOf(":") != -1) {
                                            String dl = cUrl.getFMCURL(phone_number, url.substring(url.indexOf(":") + 1));
                                            if (dl != null && dl.length() > 0) {
                                                String dllink = "[Thue bao " + msisdn + " tang ban " + contents + "]:" + dl;

                                                System.out.println(dllink);
                                                CallWS_TIG_8x98.sendMT_8x98(friendphone, dllink,
                                                        "1000", "0", "8");
                                                response.sendRedirect(send_ok + friendphone);
                                            } else {
                                                response.sendRedirect(send_false);
                                            }
                                        } else if (url.startsWith("VNM") && url.indexOf(":") != -1) {
                                            String urlStr = url.substring(url.indexOf(":") + 1);//GameID: KRAxxx

                                            String dl = cUrl.getVNMURL(phone_number, urlStr.substring(3));
                                            if (dl != null && dl.length() > 0) {
                                                String dllink = "[Thue bao " + msisdn + " tang ban " + contents + "]:" + dl;
                                                System.out.println(dllink);
                                                CallWS_TIG_8x98.sendMT_8x98(friendphone, dllink,
                                                        "1000", "0", "8");
                                                response.sendRedirect(send_ok + friendphone);
                                            } else {
                                                response.sendRedirect(send_false);
                                            }
                                        } else if (url.startsWith("VTC") && url.indexOf(":") != -1) {
                                            String urlStr = url.substring(url.indexOf(":") + 1);//GameID: VTCxxx

                                            String dl = cUrl.getVTCURL(msisdn, urlStr.substring(3));
                                            if (dl != null && dl.length() > 0) {
                                                String dllink = "[Thue bao " + msisdn + " tang ban " + contents + "]:" + dl;
                                                System.out.println(dllink);
                                                CallWS_TIG_8x98.sendMT_8x98(friendphone, dllink,
                                                        "1000", "0", "8");
                                                response.sendRedirect(send_ok + friendphone);
                                            } else {
                                                response.sendRedirect(send_false);
                                            }
                                        } else if (url.startsWith("MV") && url.indexOf(":") != -1) {
                                            String urlStr = url.substring(url.indexOf(":") + 1);//GameID: MVxxx

                                            String dl = cUrl.getMVURL(phone_number, urlStr.substring(2));
                                            if (dl != null && dl.length() > 0) {
                                                String dllink = "[Thue bao " + msisdn + " tang ban " + contents + "]:" + dl;
                                                System.out.println(dllink);
                                                CallWS_TIG_8x98.sendMT_8x98(friendphone, dllink,
                                                        "1000", "0", "8");
                                                response.sendRedirect(send_ok + friendphone);
                                            } else {
                                                response.sendRedirect(send_false);
                                            }
                                        } else if (url.startsWith("IC") && url.indexOf(":") != -1) {
                                            String urlStr = url.substring(url.indexOf(":") + 1);//GameID: MVxxx

                                            String dl = cUrl.getICURL(phone_number, urlStr.substring(2));
                                            if (dl != null && dl.length() > 0) {
                                                String dllink = "[Thue bao " + msisdn + " tang ban " + contents + "]:" + dl;
                                                System.out.println(dllink);
                                                CallWS_TIG_8x98.sendMT_8x98(friendphone, dllink,
                                                        "1000", "0", "8");
                                                response.sendRedirect(send_ok + friendphone);
                                            } else {
                                                response.sendRedirect(send_false);
                                            }
                                        } else if (url.startsWith("LT") && url.indexOf(":") != -1) {
                                            String urlStr = url.substring(url.indexOf(":") + 1);//GameID: MVxxx

                                            String dl = cUrl.getURLLT(phone_number, urlStr.substring(2));
                                            if (dl != null && dl.length() > 0) {
                                                String dllink = "[Thue bao " + msisdn + " tang ban " + contents + "]:" + dl;
                                                System.out.println(dllink);
                                                CallWS_TIG_8x98.sendMT_8x98(friendphone, dllink,
                                                        "1000", "0", "8");
                                                response.sendRedirect(send_ok + friendphone);
                                            } else {
                                                response.sendRedirect(send_false);
                                            }
                                        } else if (url.startsWith("RS")) {
                                            String dl = new RSDao().getRSURL(msisdn, url);
                                            if (dl != null && dl.length() > 0) {
                                                String dllink = "[Thue bao " + msisdn + " tang ban " + contents + "]:" + dl;
                                                System.out.println(dllink);
                                                CallWS_TIG_8x98.sendMT_8x98(friendphone, dllink,
                                                        "1000", "0", "8");
                                                response.sendRedirect(dl);
                                            } else {
                                                response.sendRedirect(send_false);
                                            }
                                        } else {
                                            String link = "[Thue bao " + msisdn + " tang ban " + ct[1] + "]:" + url;
                                            System.out.println(link);

                                            CallWS_TIG_8x98.sendMT_8x98(friendphone, link,
                                                    "1000", "0", "8");

                                            response.sendRedirect(send_ok + friendphone);
                                        }
                                    } else {
                                        response.sendRedirect(send_false + friendphone);
                                    }
                                } else {
                                    response.sendRedirect(send_false + friendphone);
                                }
                            } else {//Thuoc dang vua download xong, muon download lai
                                System.out.println(">>>>> Check Charge: [" + msisdn + "] Duplicated!!!");
                                response.sendRedirect(send_ok + friendphone);
                            }
                        } else {
                            //response.sendRedirect("./error.htm");
                            response.sendRedirect(send_false + friendphone);
                        }
                    } else {
                        System.out.println("IP is null!!!");
                        response.sendRedirect(send_false + friendphone);
                    }
                } else {
                    response.sendRedirect(send_false + friendphone);
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(send_false + friendphone);
            }
        } else {//Khong phai tang ban be
            try {
                if (url != null && pr != null && cat != null && !"".equals(cat)
                        && !"".equals(url) && !"".equals(pr)) {
                    if (ip != null) {
                        if (isMobileNumber(msisdn)) {

                            ChargeLogDAO log = new ChargeLogDAO();
                            //processCharging
                            //processCharging(“976123456”,”200”,”tekciz”,”tekciz!@#$%”,”200909260909009”,”DATACP”,”TEKCIZ”,”DOWNLOAD”,”NHACCHUONGHOT|DOWNLOAD”,1)
                            phone_number = msisdn;
                            if (msisdn.startsWith("84")) {
                                phone_number = msisdn.substring(2);
                            } else if (msisdn.startsWith("0")) {
                                phone_number = msisdn.substring(1);
                            } else if (msisdn.startsWith("+84")) {
                                phone_number = msisdn.substring(3);
                            }
                            String p = pr.trim();
                            String charging = pr.trim();
                            System.out.println("$$$$$Price: " + charging);
                            try {
                                int ipr = Integer.parseInt(p);
                                if (ipr > 30000) {
                                    charging = "30000";
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                charging = pr.trim();
                            }
                            username = "tekciz";
                            password = "tekcis20may";
                            Timestamp ts = new Timestamp(System.currentTimeMillis());
                            String reqTime = getYYYYMMDDHHMMSSString(ts);
                            String providerid = "DATACP";
                            String serviceId = "TEKCIZ";
                            String cmd = "DOWNLOAD";
                            String[] ct = getContents(cat);
                            Integer requestId = Integer.valueOf(1);
                            ChargePDU chPDU = new ChargePDU();
                            //Log Pre
                            chPDU.setMsisdn(msisdn);
                            chPDU.setCharging(charging);
                            chPDU.setUsername(username);
                            chPDU.setPassword(password);
                            chPDU.setRegTime(ts);
                            chPDU.setProviderid(providerid);
                            chPDU.setServiceid(serviceId);
                            chPDU.setCmd(cmd);
                            chPDU.setContents(ct[2]);
                            chPDU.setRequesid(requestId);
                            chPDU.setReturn_values("PRE");
                            chPDU.setPcode(pcode);
                            chPDU.setContentid(Integer.parseInt(id));
                            log.insertRow_Pre(chPDU);

                            if (free_phone_list.indexOf(msisdn) != -1) {
                                GameDAO gdao = new GameDAO();
                                String downloaded = gdao.insertDownloaded(Integer.parseInt(id));
                                System.out.println(downloaded);
                                //Tra link download.
                                //response.sendRedirect(url);

                                //Tra link download.
                                if (url.startsWith("FMC") && url.indexOf(":") != -1) {
                                    String dl = cUrl.getFMCURL(msisdn, url.substring(url.indexOf(":") + 1));
                                    if (dl != null && dl.length() > 0) {
                                        response.sendRedirect(dl);
                                        return null;
                                    } else {
                                        response.sendRedirect(send_false);
                                    }
                                } else if (url.startsWith("VTC") && url.indexOf(":") != -1) {
                                    String urlStr = url.substring(url.indexOf(":") + 1);//GameID: VTCxxx

                                    String dl = cUrl.getVTCURL(msisdn, urlStr.substring(3));
                                    if (dl != null && dl.length() > 0) {
                                        response.sendRedirect(dl);
                                        return null;
                                    } else {
                                        response.sendRedirect(send_false);
                                    }
                                } else if (url.startsWith("VNM") && url.indexOf(":") != -1) {
                                    String urlStr = url.substring(url.indexOf(":") + 1);//GameID: KRAxxx

                                    String dl = cUrl.getVNMURL(msisdn, urlStr.substring(3));
                                    if (dl != null && dl.length() > 0) {
                                        response.sendRedirect(dl);
                                        return null;
                                    } else {
                                        response.sendRedirect(send_false);
                                    }
                                } else if (url.startsWith("MV") && url.indexOf(":") != -1) {
                                    String urlStr = url.substring(url.indexOf(":") + 1);//GameID: MVxxx

                                    String dl = cUrl.getMVURL(msisdn, urlStr.substring(2));
                                    if (dl != null && dl.length() > 0) {
                                        response.sendRedirect(dl);
                                        return null;
                                    } else {
                                        response.sendRedirect(send_false);
                                    }
                                } else if (url.startsWith("IC") && url.indexOf(":") != -1) {
                                    String urlStringStr = url.substring(url.indexOf(":") + 1);
                                    String dl = cUrl.getICURL(msisdn, urlStringStr.substring(2));
                                    if (dl != null && dl.length() > 0) {
                                        response.sendRedirect(dl);
                                        return null;
                                    } else {
                                        response.sendRedirect(send_false);
                                    }
                                } else if (url.startsWith("LT") && url.indexOf(":") != -1) {
                                    String urlStringStr = url.substring(url.indexOf(":") + 1);
                                    String dl = cUrl.getURLLT(msisdn, urlStringStr.substring(2));
                                    if (dl != null && dl.length() > 0) {
                                        response.sendRedirect(dl);
                                    } else {
                                        response.sendRedirect(send_false);
                                    }

                                } else if (url.startsWith("RS")) {
                                    String dl = new RSDao().getRSURL(msisdn, url);
                                    if (dl != null && dl.length() > 0) {
                                        response.sendRedirect(dl);
                                    } else {
                                        response.sendRedirect(send_false);
                                    }
                                } else {
                                    System.out.println("[Choigame] Freephone return game OK!" + url);
                                    response.sendRedirect(url);
                                }
                            } else {
                                //Charge
                                int check = 0;
                                if (!"8".equalsIgnoreCase(cat)) {
                                    check = log.checkMSISDNByTime(msisdn, pcode);
                                }
                                //int check = 0;
                                if (check == 0) {
                                    //String result = "0";
                                    //tru tien o doan nay
                                    String result = "";
                                    if ("8".equalsIgnoreCase(cat)) {
                                        int pricetotal = gDAO.GetPriceByMsisdn(msisdn);
                                        if (pricetotal == 0 || pricetotal < Integer.parseInt(pr)) {
                                            result = ws_vt.processCharging(phone_number, charging.trim(), username, password, reqTime, providerid,
                                                    serviceId, cmd, ct[2], requestId);
                                        } else if (pricetotal > Integer.parseInt(pr)) {
                                            result = "0";
                                            GrouponTransfers gtf = new GrouponTransfers();
                                            gtf.setIdmember(msisdn);
                                            gtf.setPricetransfers(Integer.parseInt(pr));
                                            gtf.setPrice(pricetotal - Integer.parseInt(pr));
                                            gDAO.InserHistoryTransfers(gtf);

                                        }
                                    } else {
                                        result = ws_vt.processCharging(phone_number, charging.trim(), username, password, reqTime, providerid,
                                                serviceId, cmd, ct[2], requestId);
                                    }

                                    System.out.println("$$$$$Charge " + phone_number + " result: " + result);
                                    //Log//////////////////////////////////////////////
                                    chPDU.setReturn_values(result);
                                    log.insertRow(chPDU);
                                    if (result != null) {

                                        //Log file///////////////////////////////////////////////
                                        Calendar calendar = Calendar.getInstance();
                                        Date now = calendar.getTime();
                                        Timestamp timeStamp = new Timestamp(now.getTime());
                                        String currentTime = common.LogFile.getYYYYMMDDHHMMSSString(timeStamp);
                                        if (currentTime == null || "".equals(currentTime)) {
                                            currentTime = "YYYYMMDDHHMMSSS";
                                        }
                                        //Log file .bil
                                        if (!phone_number.equals("") && phone_number != null) {
                                            //common.LogFile.SaveCDRFile(phone_number, contents, "DOWNLOAD", currentTime, charging, "TEKCIZ");
                                            common.LogFile.SaveCDRFile(phone_number, ct[0], "DOWNLOAD", currentTime, charging.trim(), "TEKCIZ");
                                        }

                                        //Log file .dcm

                                        if (!phone_number.equals("") && phone_number != null) {
                                            //common.LogFile.SaveFile(phone_number, "", "TEKCIZ", contents, "DOWNLOAD", cat + id, "Download " + contents1, "", "8098", pr, result, currentTime, "TEKCIZ", "");
                                            contents = "Download " + ct[1] + ";" + log.getContents(cat, id);
                                            System.out.println("[Contents]: " + contents);

                                            common.LogFile.SaveFile(phone_number, "", "TEKCIZ", ct[0], "DOWNLOAD", cat + id, contents, "", "8098", charging.trim(), result, currentTime, "TEKCIZ", "");
                                        }

                                        if (result.equals("0")) {//Neu charge thanh cong
                                            GameDAO gdao = new GameDAO();
                                            String downloaded = gdao.insertDownloaded(Integer.parseInt(id));
                                            System.out.println(downloaded);
                                            //Tra link download.
                                            if (url.startsWith("FMC") && url.indexOf(":") != -1) {
                                                String dl = cUrl.getFMCURL(msisdn, url.substring(url.indexOf(":") + 1));
                                                if (dl != null && dl.length() > 0) {
                                                    response.sendRedirect(dl);
                                                    return null;
                                                } else {
                                                    response.sendRedirect(send_false);
                                                }
                                            } else if (url.startsWith("VNM") && url.indexOf(":") != -1) {
                                                String urlStr = url.substring(url.indexOf(":") + 1);

                                                String dl = cUrl.getVNMURL(msisdn, urlStr.substring(3));
                                                if (dl != null && dl.length() > 0) {
                                                    response.sendRedirect(dl);
                                                    return null;
                                                } else {
                                                    response.sendRedirect(send_false);
                                                }
                                            } else if (url.startsWith("VTC") && url.indexOf(":") != -1) {
                                                String urlStr = url.substring(url.indexOf(":") + 1);//GameID: VTCxxx

                                                String dl = cUrl.getVTCURL(msisdn, urlStr.substring(3));
                                                if (dl != null && dl.length() > 0) {
                                                    response.sendRedirect(dl);
                                                    return null;
                                                } else {
                                                    response.sendRedirect(send_false);
                                                }
                                            } else if (url.startsWith("MV") && url.indexOf(":") != -1) {
                                                String urlStr = url.substring(url.indexOf(":") + 1);

                                                String dl = cUrl.getMVURL(msisdn, urlStr.substring(2));
                                                if (dl != null && dl.length() > 0) {
                                                    response.sendRedirect(dl);
                                                    return null;
                                                } else {
                                                    response.sendRedirect(send_false);
                                                }
                                            } else if (url.startsWith("IC") && url.indexOf(":") != -1) {
                                                String urlStringStr = url.substring(url.indexOf(":") + 1);
                                                String dl = cUrl.getICURL(msisdn, urlStringStr.substring(2));
                                                if (dl != null && dl.length() > 0) {
                                                    response.sendRedirect(dl);
                                                    return null;
                                                } else {
                                                    response.sendRedirect(send_false);
                                                }
                                            } else if (url.startsWith("LT") && url.indexOf(":") != -1) {
                                                String urlStringStr = url.substring(url.indexOf(":") + 1);
                                                String dl = cUrl.getURLLT(msisdn, urlStringStr.substring(2));
                                                if (dl != null && dl.length() > 0) {
                                                    response.sendRedirect(dl);
                                                } else {
                                                    response.sendRedirect(send_false);
                                                }

                                            } else {
                                                if (!"g".equalsIgnoreCase(type)) {
                                                    System.out.println("[Choigame] return link game OK" + url);
                                                    response.sendRedirect(url);
                                                    return null;
                                                } else if ("g".equalsIgnoreCase(type)) {
                                                    System.out.println("Return link groupon");
                                                    GrouponLog glog = new GrouponLog();
                                                    glog.setCharging(Integer.parseInt(pr));
                                                    glog.setContent("GROUPON");
                                                    glog.setCode(code);
                                                    glog.setMsisdn(msisdn);
                                                    glog.setIdgroupon(Integer.parseInt(id));
                                                    glog.setStatuscharging(Integer.parseInt(result));
                                                    glog.setTelcos(1);
                                                    int rs = gDAO.InsertLogGroupon(glog);
                                                    System.out.println("INSERT LOG GROUPON OK!" + msisdn);
                                                    String ct1 = "Ban da dang ki thanh cong chuong trinh Mua chung gia re. Ma so mua hang "
                                                            + "cua ban la " + code + ". Chi tiet: http://v.mobix.vn/choigame";
                                                    if (rs == 1) {
                                                        String a = gDAO.UpdateNumberBuy(Integer.parseInt(id));
                                                        System.out.println(a);
                                                        boolean sms = MTSender.sendMT(msisdn, ct1);
                                                        if (sms) {
                                                            System.out.println("Send SMS OK!" + msisdn + ct1);
                                                        } else {
                                                            System.out.println("Send SMS False!" + msisdn + ct1);
                                                        }
                                                        url += "&act=1";
                                                        response.sendRedirect(url);
                                                        return null;
                                                    } else {
                                                        System.out.println("Error save log groupon" + msisdn + id);
                                                    }
                                                }
                                            }
                                        } else {

                                            if (!"g".equalsIgnoreCase(type)) {
                                                response.sendRedirect(send_false + msisdn);
                                            } else if ("g".equalsIgnoreCase(type)) {
                                                url += "&act=0";
                                                response.sendRedirect(url);
                                                return null;
                                            }
                                        }
                                    } else {
                                        if (!"g".equalsIgnoreCase(type)) {
                                            response.sendRedirect(send_false + msisdn);
                                        } else if ("g".equalsIgnoreCase(type)) {
                                            url += "&act=0";
                                            response.sendRedirect(url);
                                            return null;
                                        }
                                    }
                                } else {
                                    //Thuoc dang vua download cung 1 noi dung trong vong 60p

                                    System.out.println(">>>>> Check Charge: [" + msisdn + "] Duplicated!!!");
                                    System.out.println(">>>>> Cat info: [" + cat + "]");
                                    GameDAO gdao = new GameDAO();
                                    String downloaded = gdao.insertDownloaded(Integer.parseInt(id));
                                    System.out.println(downloaded);
                                    //Tra link download.
                                    if (url.startsWith("FMC") && url.indexOf(":") != -1) {
                                        String dl = cUrl.getFMCURL(msisdn, url.substring(url.indexOf(":") + 1));
                                        if (dl != null && dl.length() > 0) {
                                            response.sendRedirect(dl);
                                            return null;
                                        } else {
                                            response.sendRedirect(send_false);
                                        }
                                    } else if (url.startsWith("VNM") && url.indexOf(":") != -1) {
                                        String urlStr = url.substring(url.indexOf(":") + 1);

                                        String dl = cUrl.getVNMURL(msisdn, urlStr.substring(3));
                                        if (dl != null && dl.length() > 0) {
                                            response.sendRedirect(dl);
                                            return null;
                                        } else {
                                            response.sendRedirect(send_false);
                                        }
                                    } else if (url.startsWith("VTC") && url.indexOf(":") != -1) {
                                        String urlStr = url.substring(url.indexOf(":") + 1);//GameID: VTCxxx

                                        String dl = cUrl.getVTCURL(msisdn, urlStr.substring(3));
                                        if (dl != null && dl.length() > 0) {
                                            response.sendRedirect(dl);
                                            return null;
                                        } else {
                                            response.sendRedirect(send_false);
                                        }
                                    } else if (url.startsWith("MV") && url.indexOf(":") != -1) {
                                        String urlStr = url.substring(url.indexOf(":") + 1);

                                        String dl = cUrl.getMVURL(msisdn, urlStr.substring(2));
                                        if (dl != null && dl.length() > 0) {
                                            response.sendRedirect(dl);
                                            return null;
                                        } else {
                                            response.sendRedirect(send_false);
                                        }
                                    } else if (url.startsWith("IC") && url.indexOf(":") != -1) {
                                        String urlStringStr = url.substring(url.indexOf(":") + 1);
                                        String dl = cUrl.getICURL(msisdn, urlStringStr.substring(2));
                                        if (dl != null && dl.length() > 0) {
                                            response.sendRedirect(dl);
                                        } else {
                                            response.sendRedirect(send_false);
                                        }
                                    } else if (url.startsWith("LT") && url.indexOf(":") != -1) {
                                        String urlStringStr = url.substring(url.indexOf(":") + 1);
                                        String dl = cUrl.getURLLT(msisdn, urlStringStr.substring(2));
                                        if (dl != null && dl.length() > 0) {
                                            response.sendRedirect(dl);
                                        } else {
                                            response.sendRedirect(send_false);
                                        }

                                    } else {
                                        System.out.println("[Choigame] return link game OK" + url);
                                        response.sendRedirect(url);
                                        return null;
                                    }
                                }
                            }

                        } else {
                            // response.sendRedirect("./error.htm");
                            response.sendRedirect(send_false + msisdn);
                        }
                    } else {
                        System.out.println("IP is null!!!");
                        response.sendRedirect(send_false + msisdn);
                    }

                } else {
                    response.sendRedirect(send_false + msisdn);
                }


            } catch (Exception ex) {
                ex.printStackTrace();
                response.sendRedirect(send_false + msisdn);
            }
        }

        ModelAndView mv = new ModelAndView("ch");
        mv.addObject("mdch", myModel);
        return mv;
    }

    public boolean isMobileNumber(String mobileNumber) {
        if (mobileNumber == null || "".equals(mobileNumber)) {
            return false;
        }
        if ((mobileNumber.startsWith("1") && mobileNumber.length() == 10)
                || (mobileNumber.startsWith("9") && mobileNumber.length() == 9)
                || (mobileNumber.startsWith("09") && mobileNumber.length() == 10)
                || (mobileNumber.startsWith("01") && mobileNumber.length() == 11)
                || (mobileNumber.startsWith("841") && mobileNumber.length() == 12)
                || (mobileNumber.startsWith("849") && mobileNumber.length() == 11)) {

            System.out.println("True");
            return true;
        } else {
            System.out.println("False!!!");
            return false;

        }
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

    

    public String[] getContents(String catid) {
        String[] rvl = new String[3];
        int icat = Integer.parseInt(catid);
        switch (icat) {
            case 1:
                rvl[0] = "GAME";
                rvl[1] = "Game";
                rvl[2] = "GAME|DOWNLOAD";
                break;
            case 2:
                rvl[0] = "GAME";
                rvl[1] = "PACKAGE";
                rvl[2] = "PACKAGE|DOWNLOAD";
                break;
            default:
                rvl[0] = "THONGTIN";
                rvl[1] = "thong tin";
                rvl[2] = "THONGTIN|DOWNLOAD";
        }

        return rvl;
    }

   
}
