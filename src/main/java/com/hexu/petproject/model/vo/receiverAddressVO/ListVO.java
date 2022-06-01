package com.hexu.petproject.model.vo.receiverAddressVO;

/**
 * @Classname ListVO
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-11 10:42
 * @Version 1.0
 **/
public class ListVO {

    private Long id;
    private Long userId;
    private String userName;
    private String phoneNumber;
    private Integer defaultStatus;
    private String province;
    private String city;
    private String region;
    private String detailAddress;
    private String county;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDefaultStatus(Integer defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(int defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }
}
