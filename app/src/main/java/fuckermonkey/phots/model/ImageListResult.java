package fuckermonkey.phots.model;

import android.os.Parcel;
import android.os.Parcelable;

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

    public static class Data implements Parcelable{
        public long id;
        public String abs;
        public String desc;
        public List<String> tags;
        public String tag;
        public String date;
        public String image_url;
        public int image_width;
        public int image_height;

        public Data() {

        }

        public Data(Parcel in)
        {
            id = in.readLong();
            abs = in.readString();
            desc = in.readString();
            image_url = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(id);
            parcel.writeString(abs);
            parcel.writeString(desc);
            parcel.writeString(image_url);
        }

        public static final Parcelable.Creator<Data> CREATOR = new Creator<Data>()
        {
            @Override
            public Data[] newArray(int size)
            {
                return new Data[size];
            }

            @Override
            public Data createFromParcel(Parcel in)
            {
                return new Data(in);
            }
        };
    }
}
