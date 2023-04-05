package com.example.danhlamthangcanh;

public class DanhLamThangCanh {
    int id;
    String name; //Tên
    String contentname;//Mệnh danh
    int imgflag;//mini img (chưa cần add)
    int imgcontent1; //Img trong chi tiết từng dltc (chưa cần add)
    int imgcontent2;
    String description;//Đoạn mô tả
    String city;// Thành phố có dlct này
    String content1;// Đoạn văn rv dltc
    String content2;
    String regions;// Vùng miền
    String video;// Mã video (chưa cần add)


    public DanhLamThangCanh(int id, String name, String contentname, int imgflag, int imgcontent1, int imgcontent2, String description, String city, String content1, String content2, String regions, String video) {
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
        this.video = video;
    }

    public DanhLamThangCanh(String name, String city, String description) {
        this.name = name;
        this.description = description;
        this.city = city;
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

    public int getImgflag() {
        return imgflag;
    }

    public void setImgflag(int imgflag) {
        this.imgflag = imgflag;
    }

    public int getImgcontent1() {
        return imgcontent1;
    }

    public void setImgcontent1(int imgcontent1) {
        this.imgcontent1 = imgcontent1;
    }

    public int getImgcontent2() {
        return imgcontent2;
    }

    public void setImgcontent2(int imgcontent2) {
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
