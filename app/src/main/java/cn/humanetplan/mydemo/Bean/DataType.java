package cn.humanetplan.mydemo.Bean;

/**
 * author   : 肖波
 * e-mail   : xiaoboabc168@163.com
 * date     :  2019/6/6.
 */

public class DataType {
    private String typeName;
    private String typeValue;

    public DataType() {

    }

    public DataType(String typeName, String typeValue) {
        this.typeName = typeName;
        this.typeValue = typeValue;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }
}
