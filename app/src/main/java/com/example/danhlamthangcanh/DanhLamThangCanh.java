package com.example.danhlamthangcanh;

public class DanhLamThangCanh {
    int id;
    String name; //Tên
    String famousname;//Mệnh danh
    int imgflag;//mini img (chưa cần add)
    int imgcontent; //Img trong chi tiết từng dltc (chưa cần add)
    String description;//Đoạn mô tả
    String city;// Thành phố có dlct này
    String content;// Đoạn văn rv dltc
    String regions;// Vùng miền
    String video;// Mã video (chưa cần add)


    public DanhLamThangCanh(int id, String name, int imgflag, int imgcontent, String description, String city, String content, String video, String famousname, String regions) {
        this.id = id;
        this.name = name;
        this.imgflag = imgflag;
        this.imgcontent = imgcontent;
        this.description = description;
        this.city = city;
        this.content = content;
        this.video = video;
        this.regions = regions;
        this.famousname = famousname;
    }

    public DanhLamThangCanh(String name, String description, String city) {
        this.name = name;
        this.description = description;
        this.city = city;
    }

    public DanhLamThangCanh(String name, int imgflag, String city, String regions) {
        this.name = name;
        this.imgflag = imgflag;
        this.city = city;
        this.regions = regions;
    }

    public DanhLamThangCanh(String name, String city) {
        this.name = name;
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

    public int getImgflag() {
        return imgflag;
    }

    public void setImgflag(int imgflag) {
        this.imgflag = imgflag;
    }

    public int getImgcontent() {
        return imgcontent;
    }

    public void setImgcontent(int imgcontent) {
        this.imgcontent = imgcontent;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getFamousname() {
        return famousname;
    }

    public void setFamousname(String famousname) {
        this.famousname = famousname;
    }

    public String getRegions() {
        return regions;
    }

    public void setRegions(String regions) {
        this.regions = regions;
    }
}
