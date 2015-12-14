import com.blueshit.apk.bean.ApkInfo;
import com.blueshit.apk.utils.ApkParseUtils;
import com.blueshit.apk.utils.FileUtils;
import com.blueshit.apk.utils.Z_7Utils;

/**
 * 调用APK解析程序测试类
 * author  9527
 * version 1.0
 * email   zhaohuaan0925@163.com
 * created  2015/12/14 16:58
 * description
 */
public class ApkParseDemo {

    public static void main(String[] args) throws Exception{
        String apptPath = "D:/Bin/aapt.exe";
        String apkPath = "E:/a/AdDemo.apk";

        ApkInfo apkInfo = new ApkParseUtils(apptPath).getApkInfo(apkPath);

        String z7Path = "D:/Bin/7-Zip/7z.exe";
        String destPath = "E:/a/AdDemo";
        Z_7Utils.tar7Z(z7Path, apkPath, destPath);

        FileUtils.copyFile(destPath+"/"+ apkInfo.getApplicationIcon(),"E:/a/");
        FileUtils.delAllFile(destPath);
    }
}
