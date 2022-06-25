package cn.jvtc.utils;

/**
 * The type R.
 */
public class R {
    private Boolean flag;
    private Object data;
    /**
     * 用于封装消息
     */
    private String msg;

    /**
     * Instantiates a new R.
     */
    public R() {
    }

    /**
     * Instantiates a new R.
     *
     * @param flag the flag
     * @param o    the o
     */
    public R(boolean flag, Object o) {
        this.flag = flag;
        this.data = o;
    }

    /**
     * Instantiates a new R.
     *
     * @param flag the flag
     * @param msg  the msg
     */
    public R(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public R(Boolean flag, Object data, String msg) {
        this.flag = flag;
        this.data = data;
        this.msg = msg;
    }

    public R(Object data) {
        this.data = data;
    }

    /**
     * Gets flag.
     *
     * @return the flag
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * Sets flag.
     *
     * @param flag the flag
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets msg.
     *
     * @param msg the msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
