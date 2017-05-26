package fuckermonkey.phots.app;

/**
 * AppContext
 * <p>
 * Created by xuxiaowu on 2016/11/4.
 */
public class AppContext extends IApplication {

    private static AppContext sAppContext;

//    public static DbManager.DaoConfig sDaoConfig;

    public static final boolean DEBUG = true;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;

    }

    public static AppContext instance() {
        return sAppContext;
    }



    /**
     * 初始化数据库
     */
    private void initDbManager() {
//        sDaoConfig = new DbManager.DaoConfig()
//                .setDbName(MConstants.DATABASE_NAME)
////                .setDbDir(new File("/sdcard")) //不设置dbDir时, 默认存储在app的私有目录.
//                .setDbVersion(1)
//                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
//                    @Override
//                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
//                    }
//                });
//        DbManager dbManager = x.getDb(sDaoConfig);
//        dbManager.getDatabase().enableWriteAheadLogging(); // 开启WAL, 对写入加速提升巨大
    }



}
