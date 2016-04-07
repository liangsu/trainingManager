package edu.lsnu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

	/**成功消息*/
	public final static String SUCCESS = "";
	
	public static String isNull(Object obj, String errorMsg){
		return obj == null ? errorMsg : SUCCESS; 
	}
	
	public static String isBlank(String str,String errorMsg){
		if(StringUtil.isBank(str)){
			return errorMsg;
		}
		return SUCCESS;
	}
	
	/**
	 * 校验邮箱
	 * @param email
	 * @param errorMsg
	 * @return
	 */
	public static String isEmail(String email, String errorMsg){
		if(email == null){
			return errorMsg;
		}
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(email);
		if(matcher.matches()){
			return SUCCESS;
		}
		return errorMsg;
	}
	
	/**
	 * 校验移动电话
	 * @param mobile
	 * @param errorMsg
	 * @return
	 */
	public static String isMobile(String mobile, String errorMsg){
		if(mobile == null){
			return errorMsg;
		}
		Pattern pattern = Pattern.compile("^[1][3,4,5,8][0-9]{9}$");
		Matcher matcher = pattern.matcher(mobile);
		if(matcher.matches()){
			return SUCCESS;
		}
		return errorMsg;
	}
	
	//----------------------------校验银行卡号
    /**
     * 校验银行卡卡号
     * 测试帐号：6227007200120897790
     * 原理:
	 *	现行 16 位银联卡现行卡号开头 6 位是 622126～622925 之间的，7 到 15 位是银行自定义的， 
	 *	可能是发卡分行，发卡网点，发卡序号，第 16 位是校验码。 
	 *	16 位卡号校验位采用 Luhm 校验方法计算： 
	 *	1，将未带校验位的 15 位卡号从右依次编号 1 到 15，位于奇数位号上的数字乘以 2
	 *	2，将奇位乘积的个十位全部相加，再加上所有偶数位上的数字 
	 *	3，将加法和加上校验位能被 10 整除。 
     * @param cardId 银行卡号
     * @return
     */
    public static boolean isBankCard(String cardId) {
             char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
             if(bit == 'N'){
                 return false;
             }
             return cardId.charAt(cardId.length() - 1) == bit;
    }
    
    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     * @param nonCheckCodeCardId
     * @return
     */
    private static char getBankCardCheckCode(String nonCheckCodeCardId){
        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;           
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }
    
    /**
	 * 校验银行卡号
	 * @param bankCard
	 * @param errorMsg
	 * @return
	 */
	public static String isBankCard(String bankCard, String errorMsg){
		if(isBankCard(bankCard)){
			return SUCCESS;
		}
		return errorMsg;
	}
	
    public static void main(String[] args) {
        String card = "";
        System.out.println("      card: " + card);
        System.out.println("check code: " + getBankCardCheckCode(card));
        System.out.println("是否为银行卡:"+isBankCard(card));
    }
}
