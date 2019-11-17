package com.exercise.demo.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/9/9 10:38
 */
public class IpUtil {

    private final static String OS_WINDOWS = "windows";

    /**
     * 获取本机ip地址
     *
     * @return
     */
    public static String getLocalHostAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "";
        }
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotBlank(ip) && !"Unknown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(ip) && !"Unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 获取真实IP
     *
     * @param request 请求体
     * @return 真实IP
     */
    public static String getRealIp(HttpServletRequest request) {
        // 这个一般是Nginx反向代理设置的参数
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 处理多IP的情况（只取第一个IP）
        if (ip != null && ip.contains(",")) {
            String[] ipArray = ip.split(",");
            ip = ipArray[0];
        }
        return ip;
    }

    /**
     * 判断当前操作是否Windows.
     *
     * @return true---是Windows操作系统
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains(OS_WINDOWS)) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 获取本机IP地址，并自动区分Windows还是Linux操作系统
     *
     * @return String
     */
    public static String getLocalIP() {
        String sIP = "";
        InetAddress ip = null;
        try {
            // 如果是Windows操作系统
            if (isWindowsOS()) {
                ip = InetAddress.getLocalHost();
            }
            // 如果是Linux操作系统
            else {
                boolean bFindIP = false;
                Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
                while (netInterfaces.hasMoreElements()) {
                    if (bFindIP) {
                        break;
                    }
                    NetworkInterface ni = netInterfaces.nextElement();
                    // ----------特定情况，可以考虑用ni.getName判断
                    Enumeration<InetAddress> ips = ni.getInetAddresses();
                    while (ips.hasMoreElements()) {
                        ip = ips.nextElement();
                        // 127.开头的都是 look back 地址
                        if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {
                            bFindIP = true;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null != ip) {
            sIP = ip.getHostAddress();
        }
        return sIP;
    }

    /**
     * 获取本机名称
     *
     * @return 本机主机名称
     */
    public static String getLocalHostName() {
        String hostName = null;
        try {
            InetAddress ia = InetAddress.getLocalHost();
            hostName = ia.getHostName();
        } catch (UnknownHostException e) {
            String host = e.getMessage();
            if (host != null) {
                int colon = host.indexOf(':');
                if (colon > 0) {
                    hostName = host.substring(0, colon);
                    return hostName;
                }
            }
        }
        return hostName;
    }
}
