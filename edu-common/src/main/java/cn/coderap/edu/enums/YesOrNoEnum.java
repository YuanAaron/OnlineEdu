package cn.coderap.edu.enums;

public enum YesOrNoEnum {

    // 上架
    YES(1,"是"),
    // 下架
    NO(0,"否");

    private Integer type;
    private String value;

    YesOrNoEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public Integer getType() {
        return type;
    }
}
