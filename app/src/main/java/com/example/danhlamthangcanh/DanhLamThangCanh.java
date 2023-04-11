package com.example.danhlamthangcanh;

public class DanhLamThangCanh {
    int id;
    String name; //Tên
    String contentname;//Mệnh danh
    String imgflag;//mini img (chưa cần add)
    String imgcontent1; //Img trong chi tiết từng dltc (chưa cần add)
    String imgcontent2;
    String description;//Đoạn mô tả
    String city;// Thành phố có dlct này
    String content1;// Đoạn văn rv dltc
    String content2;
    String regions;// Vùng miền
    String video;// Mã video (chưa cần add)


    public DanhLamThangCanh(int id, String name, String contentname, String imgcontent1, String imgcontent2, String description, String city, String content1, String content2, String regions) {
        this.id = id;
        this.name = name;
        this.contentname = contentname;
        this.imgcontent1 = imgcontent1;
        this.imgcontent2 = imgcontent2;
        this.description = description;
        this.city = city;
        this.content1 = content1;
        this.content2 = content2;
        this.regions = regions;
    }

    public DanhLamThangCanh(int id, String name, String contentname, String imgflag, String imgcontent1, String imgcontent2, String description, String city, String content1, String content2, String regions) {
        this.id = id;
        this.name = name;
        this.contentname = contentname;
        this.imgflag = imgflag;
        this.imgcontent1 = imgcontent1;
        this.imgcontent2 = imgcontent2;
        this.description = description;
        this.city = city;
        this.content1 = content1;
        this.content2 = content2;
        this.regions = regions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentname() {
        return contentname;
    }

    public void setContentname(String contentname) {
        this.contentname = contentname;
    }

    public String getImgflag() {
        return imgflag;
    }

    public void setImgflag(String imgflag) {
        this.imgflag = imgflag;
    }

    public String getImgcontent1() {
        return imgcontent1;
    }

    public void setImgcontent1(String imgcontent1) {
        this.imgcontent1 = imgcontent1;
    }

    public String getImgcontent2() {
        return imgcontent2;
    }

    public void setImgcontent2(String imgcontent2) {
        this.imgcontent2 = imgcontent2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getRegions() {
        return regions;
    }

    public void setRegions(String regions) {
        this.regions = regions;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
