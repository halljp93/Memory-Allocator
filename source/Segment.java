package Mallocator;

public class Segment {

    int start;
    int end;
    public Segment(int newStart, int newEnd) {
        start= newStart;
        end = newEnd;
    }


    int getSize() {
        return  end - start;
    }

    void setStart(int processSize) {
        start = start + processSize;
    }
    int getStart() {
        return start;
    }
    int getEnd() {
        return end;
    }


}