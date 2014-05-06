/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package m_operator;

import java.io.IOException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Do Tien Hung
 */
public class OperatorConfig {
     static Map operstorsTable = new Hashtable();

    public OperatorConfig()
    {
    }

    private static int getIntProperty(String propName, int defaultValue)
    {
        return Integer.parseInt(OpConfig.getString(propName));
    }

    public static void loadProperties() throws IOException
    {
        int i = 1;
        do
        {
            String tempStr = OpConfig.getString("operator_" + i);
            if(tempStr != null)
            {
                System.out.println("operator_" + i + "=" + tempStr);
                int idx = tempStr.indexOf(":");
                if(idx <= 0)
                {
                    System.out.println("Seperator ':' is NOT found.");
                } else
                {
                    String mOp = tempStr.substring(0, idx).trim();
                    String subOps = tempStr.substring(idx + 1).trim();
                    try
                    {
                        Collection cSubOps = StringTool.parseStringEx(subOps);
                        String op;
                        for(Iterator it = cSubOps.iterator(); it.hasNext(); )
                        {
                        	op = (String)it.next();
                        	operstorsTable.put(op, mOp);
                        }
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
                i++;
            } else
            {
                return;
            }
        } while(true);
    }
    
    //Lay Mobile Operator
    /*
     * Return MobileOperator
     * UNKNOWN for not
     * */
    public static String getMobileOperator(String phoneNumber){
    	String mOperator = "UNKNOWN";
    	String stPhone = getInterMobileFormat(phoneNumber);
    	if (stPhone.startsWith("849") && (stPhone.length()>5)){
    		mOperator = (String)operstorsTable.get(stPhone.subSequence(0,4));
    	}else if (stPhone.startsWith("841") && (stPhone.length()>6)){
    		mOperator = (String)operstorsTable.get(stPhone.substring(0,5));
    	}
    	System.out.println("[Mobile Operator]: "+ mOperator);
    	return mOperator;
    }
    
    //Lay dinh dang so dt International, 84xxxxxxxx
    public static String getInterMobileFormat (String phoneNumber){
    	String interMobileFormat = phoneNumber;
    	if(phoneNumber.startsWith("84")){
    		interMobileFormat = phoneNumber;
    	}else if (phoneNumber.startsWith("+84")){
    		interMobileFormat = phoneNumber.substring(1);
    	}else if (phoneNumber.startsWith("0")){
    		interMobileFormat = "84"+phoneNumber.substring(1);
    	}else if (phoneNumber.startsWith("9") || phoneNumber.startsWith("1")){
    		interMobileFormat = "84"+phoneNumber;
    	}
    	
    	return interMobileFormat;
    }

    public static void main(String args1[])
    {
    	try{
    		loadProperties();
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	//System.out.println(getMobileOperator("841285826609"));
    }
}
