package xyz.hitpy.seproject.model;

public class MyActivityPreview extends ActivityPreview{
    protected int checkNum;
    public MyActivityPreview() {
        aid = 0;
        name = null;
        time = null;
        location = null;
        username = null;
        checkNum = 0;
    }
    public MyActivityPreview(int aid, String name, String time, String location, String username, int checkNum) {
        this.aid = aid;
        this.name = name;
        this.time = time;
        this.username = username;
        this.location = location;
        this.checkNum = checkNum;
    }
    
    public int getCheckNum() { return checkNum; }
    
    public void setCheckNum(int checkNum)
    {
        this.checkNum = checkNum;
    }
}
