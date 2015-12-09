package com.example.dulzh.lizhijoke.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dulzh on 12/8/15.
 */
public class JokeImgBean implements Parcelable {


    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"allNum":18281,"allPages":915,"contentlist":[{"ct":"2015-12-08 16:10:25.734","img":"http://img6.hao123.com/data/3_7d30f2151be225a8eeb94e5412f8dd85_0","title":"我中了再来一瓶的可乐！","type":2},{"ct":"2015-12-08 16:10:25.729","img":"http://img2.hao123.com/data/3_c17dc8c90b1487fb99bf739d2895c0a4_430","title":"看到这个还能吃得下去吗？","type":2},{"ct":"2015-12-08 15:10:27.223","img":"http://sc2.hao123img.com/data/83b2c2a6d60c537775fa61b67cf66e7a_430","title":"刚交的女友开放的有点担心呢","type":2},{"ct":"2015-12-08 15:10:27.219","img":"http://sc0.hao123img.com/data/aea7bc6d606c4b50236284b5a2f59330_0","title":"这妹子也太有心计了吧","type":2},{"ct":"2015-12-08 14:10:29.271","img":"http://img6.hao123.com/data/3_f05b8cbb7c750353eb49f1783ec4606a_0","title":"一个化学实验室制作的圣诞树","type":2},{"ct":"2015-12-08 14:10:29.251","img":"http://sc2.hao123img.com/data/12ea1aa6f8638840ccadff3b25bcb195_0","title":"我只是想吃点豆腐","type":2},{"ct":"2015-12-08 14:10:29.251","img":"http://sc4.hao123img.com/data/99d9fa329d171f771b743e63673220d5_0","title":"这一幕我也是看的醉了","type":2},{"ct":"2015-12-08 14:10:29.250","img":"http://sc2.hao123img.com/data/cfa04d4afac76f900e35adc55359dd7a_0","title":"现在的学生也太激进了吧","type":2},{"ct":"2015-12-08 13:10:27.576","img":"http://sc1.hao123img.com/data/40f89fae2f683904213445383bff4ad5_430","title":"这皇家装备太逆天了","type":2},{"ct":"2015-12-08 13:10:27.574","img":"http://sc4.hao123img.com/data/eac6a4f4a6c079cac597ef1b5db5cab8_430","title":"这驾校我打死也不敢去啊","type":2},{"ct":"2015-12-08 11:10:17.856","img":"http://img4.hao123.com/data/3_f2a6532e1cee929b9f70dfcb1ebaf1d8_0","title":"警察叔叔，这有人超载！","type":2},{"ct":"2015-12-08 11:10:17.855","img":"http://img3.hao123.com/data/3_83dad94291f5def46aa084182735b476_0","title":"空手不行还有武器","type":2},{"ct":"2015-12-08 11:10:17.855","img":"http://img6.hao123.com/data/3_ca9e1aa1333a370fbd56bc2722911fba_0","title":"反正在地铁上闲着也是闲着","type":2},{"ct":"2015-12-08 11:10:17.855","img":"http://img6.hao123.com/data/3_0855107abcab6688caaa9b83fa220715_430","title":"那诡异一笑！","type":2},{"ct":"2015-12-08 11:10:17.854","img":"http://img2.hao123.com/data/3_426bb722c8122aac1885ac0d2bf53628_430","title":"这得是多大的雪呀！","type":2},{"ct":"2015-12-08 10:10:25.644","img":"http://img0.hao123.com/data/3_3b9325c87a5a0f347ac8402415d99e0a_0","title":"想买那又黄又邪恶的蛋糕，我说的是海绵宝宝","type":2},{"ct":"2015-12-08 10:10:25.644","img":"http://img5.hao123.com/data/3_fa655d57bed0275ab14aa32bc5c59fd4_430","title":"后面成功抢镜！","type":2},{"ct":"2015-12-08 10:10:25.643","img":"http://img5.hao123.com/data/3_680230bf2c7d05d86f5e8b826e4410f0_0","title":"逗比主人给的装扮","type":2},{"ct":"2015-12-08 10:10:25.642","img":"http://img0.hao123.com/data/3_43a0b7597cee4d016187581b0f959a32_0","title":"没有尺子用扫把也行！","type":2},{"ct":"2015-12-08 10:10:25.641","img":"http://img6.hao123.com/data/3_4ebc51d1b66f4235451affb66e1dd7bd_430","title":"妹纸，约不？","type":2}],"currentPage":1,"maxResult":20,"ret_code":0}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    /**
     * allNum : 18281
     * allPages : 915
     * contentlist : [{"ct":"2015-12-08 16:10:25.734","img":"http://img6.hao123.com/data/3_7d30f2151be225a8eeb94e5412f8dd85_0","title":"我中了再来一瓶的可乐！","type":2},{"ct":"2015-12-08 16:10:25.729","img":"http://img2.hao123.com/data/3_c17dc8c90b1487fb99bf739d2895c0a4_430","title":"看到这个还能吃得下去吗？","type":2},{"ct":"2015-12-08 15:10:27.223","img":"http://sc2.hao123img.com/data/83b2c2a6d60c537775fa61b67cf66e7a_430","title":"刚交的女友开放的有点担心呢","type":2},{"ct":"2015-12-08 15:10:27.219","img":"http://sc0.hao123img.com/data/aea7bc6d606c4b50236284b5a2f59330_0","title":"这妹子也太有心计了吧","type":2},{"ct":"2015-12-08 14:10:29.271","img":"http://img6.hao123.com/data/3_f05b8cbb7c750353eb49f1783ec4606a_0","title":"一个化学实验室制作的圣诞树","type":2},{"ct":"2015-12-08 14:10:29.251","img":"http://sc2.hao123img.com/data/12ea1aa6f8638840ccadff3b25bcb195_0","title":"我只是想吃点豆腐","type":2},{"ct":"2015-12-08 14:10:29.251","img":"http://sc4.hao123img.com/data/99d9fa329d171f771b743e63673220d5_0","title":"这一幕我也是看的醉了","type":2},{"ct":"2015-12-08 14:10:29.250","img":"http://sc2.hao123img.com/data/cfa04d4afac76f900e35adc55359dd7a_0","title":"现在的学生也太激进了吧","type":2},{"ct":"2015-12-08 13:10:27.576","img":"http://sc1.hao123img.com/data/40f89fae2f683904213445383bff4ad5_430","title":"这皇家装备太逆天了","type":2},{"ct":"2015-12-08 13:10:27.574","img":"http://sc4.hao123img.com/data/eac6a4f4a6c079cac597ef1b5db5cab8_430","title":"这驾校我打死也不敢去啊","type":2},{"ct":"2015-12-08 11:10:17.856","img":"http://img4.hao123.com/data/3_f2a6532e1cee929b9f70dfcb1ebaf1d8_0","title":"警察叔叔，这有人超载！","type":2},{"ct":"2015-12-08 11:10:17.855","img":"http://img3.hao123.com/data/3_83dad94291f5def46aa084182735b476_0","title":"空手不行还有武器","type":2},{"ct":"2015-12-08 11:10:17.855","img":"http://img6.hao123.com/data/3_ca9e1aa1333a370fbd56bc2722911fba_0","title":"反正在地铁上闲着也是闲着","type":2},{"ct":"2015-12-08 11:10:17.855","img":"http://img6.hao123.com/data/3_0855107abcab6688caaa9b83fa220715_430","title":"那诡异一笑！","type":2},{"ct":"2015-12-08 11:10:17.854","img":"http://img2.hao123.com/data/3_426bb722c8122aac1885ac0d2bf53628_430","title":"这得是多大的雪呀！","type":2},{"ct":"2015-12-08 10:10:25.644","img":"http://img0.hao123.com/data/3_3b9325c87a5a0f347ac8402415d99e0a_0","title":"想买那又黄又邪恶的蛋糕，我说的是海绵宝宝","type":2},{"ct":"2015-12-08 10:10:25.644","img":"http://img5.hao123.com/data/3_fa655d57bed0275ab14aa32bc5c59fd4_430","title":"后面成功抢镜！","type":2},{"ct":"2015-12-08 10:10:25.643","img":"http://img5.hao123.com/data/3_680230bf2c7d05d86f5e8b826e4410f0_0","title":"逗比主人给的装扮","type":2},{"ct":"2015-12-08 10:10:25.642","img":"http://img0.hao123.com/data/3_43a0b7597cee4d016187581b0f959a32_0","title":"没有尺子用扫把也行！","type":2},{"ct":"2015-12-08 10:10:25.641","img":"http://img6.hao123.com/data/3_4ebc51d1b66f4235451affb66e1dd7bd_430","title":"妹纸，约不？","type":2}]
     * currentPage : 1
     * maxResult : 20
     * ret_code : 0
     */

    private ShowapiResBodyEntity showapi_res_body;

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public void setShowapi_res_body(ShowapiResBodyEntity showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public ShowapiResBodyEntity getShowapi_res_body() {
        return showapi_res_body;
    }

    public static class ShowapiResBodyEntity implements Parcelable {
        private int allNum;
        private int allPages;
        private int currentPage;
        private int maxResult;
        private int ret_code;
        /**
         * ct : 2015-12-08 16:10:25.734
         * img : http://img6.hao123.com/data/3_7d30f2151be225a8eeb94e5412f8dd85_0
         * title : 我中了再来一瓶的可乐！
         * type : 2
         */

        private List<ContentlistEntity> contentlist;

        public void setAllNum(int allNum) {
            this.allNum = allNum;
        }

        public void setAllPages(int allPages) {
            this.allPages = allPages;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public void setMaxResult(int maxResult) {
            this.maxResult = maxResult;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public void setContentlist(List<ContentlistEntity> contentlist) {
            this.contentlist = contentlist;
        }

        public int getAllNum() {
            return allNum;
        }

        public int getAllPages() {
            return allPages;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public int getMaxResult() {
            return maxResult;
        }

        public int getRet_code() {
            return ret_code;
        }

        public List<ContentlistEntity> getContentlist() {
            return contentlist;
        }

        public static class ContentlistEntity implements Parcelable {
            private String ct;
            private String img;
            private String title;
            private int type;

            public void setCt(String ct) {
                this.ct = ct;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getCt() {
                return ct;
            }

            public String getImg() {
                return img;
            }

            public String getTitle() {
                return title;
            }

            public int getType() {
                return type;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.ct);
                dest.writeString(this.img);
                dest.writeString(this.title);
                dest.writeInt(this.type);
            }

            public ContentlistEntity() {
            }

            protected ContentlistEntity(Parcel in) {
                this.ct = in.readString();
                this.img = in.readString();
                this.title = in.readString();
                this.type = in.readInt();
            }

            public static final Creator<ContentlistEntity> CREATOR = new Creator<ContentlistEntity>() {
                public ContentlistEntity createFromParcel(Parcel source) {
                    return new ContentlistEntity(source);
                }

                public ContentlistEntity[] newArray(int size) {
                    return new ContentlistEntity[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.allNum);
            dest.writeInt(this.allPages);
            dest.writeInt(this.currentPage);
            dest.writeInt(this.maxResult);
            dest.writeInt(this.ret_code);
            dest.writeList(this.contentlist);
        }

        public ShowapiResBodyEntity() {
        }

        protected ShowapiResBodyEntity(Parcel in) {
            this.allNum = in.readInt();
            this.allPages = in.readInt();
            this.currentPage = in.readInt();
            this.maxResult = in.readInt();
            this.ret_code = in.readInt();
            this.contentlist = new ArrayList<ContentlistEntity>();
            in.readList(this.contentlist, List.class.getClassLoader());
        }

        public static final Creator<ShowapiResBodyEntity> CREATOR = new Creator<ShowapiResBodyEntity>() {
            public ShowapiResBodyEntity createFromParcel(Parcel source) {
                return new ShowapiResBodyEntity(source);
            }

            public ShowapiResBodyEntity[] newArray(int size) {
                return new ShowapiResBodyEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.showapi_res_code);
        dest.writeString(this.showapi_res_error);
        dest.writeParcelable(this.showapi_res_body, flags);
    }

    public JokeImgBean() {
    }

    protected JokeImgBean(Parcel in) {
        this.showapi_res_code = in.readInt();
        this.showapi_res_error = in.readString();
        this.showapi_res_body = in.readParcelable(ShowapiResBodyEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<JokeImgBean> CREATOR = new Parcelable.Creator<JokeImgBean>() {
        public JokeImgBean createFromParcel(Parcel source) {
            return new JokeImgBean(source);
        }

        public JokeImgBean[] newArray(int size) {
            return new JokeImgBean[size];
        }
    };
}
