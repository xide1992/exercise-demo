package com.exercise.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.exercise.demo.common.utils.HttpRequestUtil;
import com.exercise.demo.common.utils.LogHelper;
import com.exercise.demo.dao.order.generated.UserMapper;
import com.exercise.demo.model.Response;
import com.exercise.demo.model.po.order.User;
import com.exercise.demo.model.po.order.UserExample;
import com.exercise.demo.model.wechat.Jscode2sessionResponse;
import com.exercise.demo.service.inter.IWeChatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FileUtils;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/9/17 17:21
 */
@Service
public class WeChatService implements IWeChatService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private  Environment env;

    private static final String TEMP_FILE_PATH = "D:/";//"./temp/"

    @Override
    public String wxLogin(String code) {
        try {

            String appid = env.getProperty("wechat.appid");
            String secret = env.getProperty("wechat.secret");

            String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                    "appid=" + appid +
                    "&secret=" + secret +
                    "&js_code=" + code + "&grant_type=authorization_code";

            String result = HttpRequestUtil.connect(url).get().html();
            LogHelper.info("wechat", "111", "222", result);

            Jscode2sessionResponse response = JSON.parseObject(result, Jscode2sessionResponse.class);
            if (response != null && StringUtils.isNotBlank(response.getOpenid())) {
                UserExample userExample = new UserExample();
                userExample.createCriteria().andOpenidEqualTo(response.getOpenid());
                List<User> userList = userMapper.selectByExample(userExample);
                if (CollectionUtils.isEmpty(userList)) {
                    User insert = new User();
                    insert.setNickname("");
                    insert.setAvatar("");
                    insert.setPhone("");
                    insert.setOpenid(response.getOpenid());
                    if (StringUtils.isNotBlank(response.getSession_key())) {
                        insert.setSessionKey(response.getSession_key());
                    }
                    if (StringUtils.isNotBlank(response.getUnionid())) {
                        insert.setUnionid(response.getUnionid());
                    }
                    insert.setCreateTime(new Date());
                    insert.setUpdateTime(new Date());
                    userMapper.insertSelective(insert);
                } else {
                    if (StringUtils.isNotBlank(response.getSession_key())) {
                        User update = new User();
                        update.setId(userList.get(0).getId());
                        update.setSessionKey(response.getSession_key());
                        update.setUpdateTime(new Date());
                        userMapper.updateByPrimaryKeySelective(update);
                    }
                }
                return response.getOpenid();
            }
            return "";
        } catch (Exception e) {
            LogHelper.error("wechat", "111", "222", "Exception", e);
            return "";
        }
    }

    @Override
    public void refreshWeChatImage() {

        StringBuilder sbLog = new StringBuilder();
        sbLog.append(">>refreshWeChatImage");
        try {

            String token = getToken();

            String imagePath = "xxxxxxxx";
            sbLog.append(">>imagePath:" + imagePath);
            String fileName = "wechatimage" + (new Date()).getTime() + ".jpg";
            sbLog.append(">>fileName:" + fileName);
            File file = createTemporaryFile(imagePath, fileName);

            String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=" + token + "&type=image";
            sbLog.append(">>url:" + url);
            String imageId = uploadFile(url, file, "image");
            sbLog.append(">>imageId:" + imageId);
            deleteTemporaryFile(fileName);
            if (StringUtils.isNotBlank(imageId)) {

                System.out.println(sbLog.toString());
            }
            return;
        } catch (Exception e) {
            sbLog.append("Exception:" + e);
            System.out.println(sbLog.toString());
            return;
        } finally {
            System.out.println(sbLog.toString());
        }

    }

    private String getToken() {
        try {
            String url = "xxxxxxxxxx";
            String result = HttpRequestUtil.connect(url).get().html();
            Response resultJson = JSON.parseObject(result, Response.class);
            if (resultJson == null) {
                return "";
            }
            return resultJson.getBody().toString();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 创建临时文件
     *
     * @param tempFileName
     * @return
     * @throws IOException
     */
    private File createTemporaryFile(String urlPath, String tempFileName) throws IOException {
        File tempDirectory = new File(TEMP_FILE_PATH);
        if (!tempDirectory.isDirectory()) {
            tempDirectory.mkdir();
        }

        File tempFile = new File(TEMP_FILE_PATH + tempFileName);
        URL url = new URL(urlPath);
        FileUtils.copyURLToFile(url, tempFile);

        return tempFile;
    }

    /**
     * 删除临时文件
     *
     * @param fileName
     */
    private void deleteTemporaryFile(String fileName) {
        File file = new File(TEMP_FILE_PATH + fileName);
        FileUtils.deleteQuietly(file);
    }

    public String uploadFile(String url, File file, String type) throws Exception {

        URL urlObj = new URL(url);

        // 创建Http连接
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);   // 使用post提交需要设置忽略缓存

        //消息请求头信息
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");

        //设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);

        StringBuilder sb = new StringBuilder();
        sb.append("--");    // 必须多两道线
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition:form-data;name=\"media\";filename=\"" + file.getName() + "\";filelength=\"" + file.length() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");

        byte[] head = sb.toString().getBytes("utf-8");

        // 创建输出流
        OutputStream out = new DataOutputStream(conn.getOutputStream());
        // 获得输出流表头
        out.write(head);

        //文件正文部分
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024 * 1024 * 10]; // 10M
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();

        //结尾
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");
        out.write(foot);
        out.flush();
        out.close();


        // 获取响应
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        String result = null;
        try {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }

        JSONObject json = JSONObject.parseObject(result);
        System.out.println(json);
        String mediaId = "";
        if (!type.equals("image")) {
            mediaId = json.getString(type + "_media_id");
        } else {
            mediaId = json.getString("media_id");
        }
        return mediaId;
    }
}
