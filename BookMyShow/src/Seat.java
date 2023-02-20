import Enums.SeatCategory;

public class Seat {

    private int id;
    private int row;
    private SeatCategory seatCategory;

    public int getSeatId() {
        return this.id;
    }

    public void setSeatId(int id) {
        this.id = id;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public SeatCategory getSeatCategory() {
        return seatCategory;
    }

    public void setSeatCategory(SeatCategory seatCategory) {
        this.seatCategory = seatCategory;
    }
}
