package fuckermonkey.phots.model;

import java.util.List;

/**
 * Created by xuxiaowu on 2016/12/26.
 *
 * 数据模型
 */

public class ImageListResult {
    public String tag1;
    public String tag2;
    public int totalNum;
    public List<Data> data;

    public static class Data {
        public long id;
        public String abs;
        public String desc;
        public List<String> tags;
        public String tag;
        public String date;
        public String image_url;
        public int image_width;
        public int image_height;
    }
}
