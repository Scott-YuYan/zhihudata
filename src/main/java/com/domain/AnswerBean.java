package com.domain;

public class AnswerBean {
    private int seq;
    private String userName;
    private int voteNumber;
    private String articleContext;

    public AnswerBean(int seq, String userName, int voteNumber, String articleContext) {
        this.seq = seq;
        this.userName = userName;
        this.voteNumber = voteNumber;
        this.articleContext = articleContext;
    }

    public AnswerBean() {
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getVoteNumber() {
        return voteNumber;
    }

    public void setVoteNumber(int voteNumber) {
        this.voteNumber = voteNumber;
    }

    public String getArticleContext() {
        return articleContext;
    }

    public void setArticleContext(String articleContext) {
        this.articleContext = articleContext;
    }

    @Override
    public String toString() {
        return "AnswerBean{" +
                "seq=" + seq +
                ", userName='" + userName + '\'' +
                ", voteNumber=" + voteNumber +
                ", articleContext='" + articleContext + '\'' +
                '}';
    }
}
