package utils;

import android.util.Log;

import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;

/**
 * Created by 李浩 on 2016/9/21.
 */
public class XUtilsDB {
    public static  DbManager.DaoConfig getDBconfig(){
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                //设置数据库名，默认xutils.db
                .setDbName("personal.db")
                //设置表创建的监听
                .setTableCreateListener(new DbManager.TableCreateListener() {
                    @Override
                    public void onTableCreated(DbManager db, TableEntity<?>  table) {
                        Log.i("JAVA", "onTableCreated：" + table.getName());
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion,
                                          int newVersion) {
                    }
                })
                //设置数据库打开的监听
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        //开启数据库支持多线程操作，提升性能
                        db.getDatabase().enableWriteAheadLogging();
                    }
                });
        return daoConfig;
    }
}
