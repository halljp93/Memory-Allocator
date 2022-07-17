package Mallocator;

public class Process {
    int pid;
    int size;
    int start;
    int end;

    public Process(int newPid, int newStart, int newSize) {
        pid = newPid;
        start = newStart;
        size = newSize;
        end = start + size;

    }

    int getStart() {
        return start;
    }
    int getEnd() {
        return start + size;
    }
    int getPid() {
        return pid;
    }
}
