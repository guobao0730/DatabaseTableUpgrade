package com.casic.databasetableupgrade.bean;

/**
 * @author 郭宝
 * @project： DatabaseTableUpgrade
 * @package： com.casic.databasetableupgrade.bean
 * @date： 2019/6/27 0027 16:55
 * @brief:
 */
public class RecommendBean {

    //标题
    private String title;

    //新闻来源
    private String source;

    //评论个数
    private Integer comments;

    //信息时间
    private String time;

    //是否置顶
    private String istop;


    public String getIstop() {
        return istop;
    }

    public void setIstop(String istop) {
        this.istop = istop;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
