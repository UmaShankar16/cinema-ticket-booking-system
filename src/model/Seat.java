package model;

public class Seat {
    private int seatId;
    private String type;
    private String row;
    private int seatNo;

    public Seat(String type,String row,int seatNo)
    {
        this.type=type;
        this.row=row;
        this.seatNo=seatNo;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getSeatId() {
        return seatId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }
}
