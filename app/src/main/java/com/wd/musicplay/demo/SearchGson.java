package com.wd.musicplay.demo;

import java.util.List;

/**
 * Created by Administrator on 2016/5/18.
 * <p/>
 * 主体参数:
 * 当前页数: current_page
 * 搜索字符: keyword
 * 资源总数: total_rows
 * 资源总页: total_page
 * 本页条数: page_size
 * 内容参数:
 * 文件名称: filename
 * 文件类型: extname
 * M4A大小:  m4afilesize
 * 文件大小: filesize
 * 比特率: bitrate
 * 是否新歌: is_new
 * 持续时间: duration
 * 专辑名称: album_name
 * 歌手名称: singername
 * 歌曲HASH: hash
 */
public class SearchGson {
    private int code;
    private String status;
    private String msg;
    private Page data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Page getData() {
        return data;
    }

    public void setData(Page data) {
        this.data = data;
    }

    private class Page {
        private int current_page;
        private String keyword;
        private int total_rows;
        private int total_page;
        private int page_size;
        private List<Data> data;
        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public int getTotal_rows() {
            return total_rows;
        }

        public void setTotal_rows(int total_rows) {
            this.total_rows = total_rows;
        }

        public int getTotal_page() {
            return total_page;
        }

        public void setTotal_page(int total_page) {
            this.total_page = total_page;
        }

        public int getPage_size() {
            return page_size;
        }

        public void setPage_size(int page_size) {
            this.page_size = page_size;
        }

        public List<Data> getData() {
            return data;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }

        private class Data {
            private String filename;
            private String extname;
            private int m4afilesize;
            private int filesize;
            private int bitrate;
            private int isnew;

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public String getExtname() {
                return extname;
            }

            public void setExtname(String extname) {
                this.extname = extname;
            }

            public int getM4afilesize() {
                return m4afilesize;
            }

            public void setM4afilesize(int m4afilesize) {
                this.m4afilesize = m4afilesize;
            }

            public int getFilesize() {
                return filesize;
            }

            public void setFilesize(int filesize) {
                this.filesize = filesize;
            }

            public int getBitrate() {
                return bitrate;
            }

            public void setBitrate(int bitrate) {
                this.bitrate = bitrate;
            }

            public int getIsnew() {
                return isnew;
            }

            public void setIsnew(int isnew) {
                this.isnew = isnew;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getAlbum_name() {
                return album_name;
            }

            public void setAlbum_name(String album_name) {
                this.album_name = album_name;
            }

            public String getSingername() {
                return singername;
            }

            public void setSingername(String singername) {
                this.singername = singername;
            }

            public String getHash() {
                return hash;
            }

            public void setHash(String hash) {
                this.hash = hash;
            }

            private int duration;
            private String album_name;
            private String singername;
            private String hash;
        }

    }
}
