package Mallocator;

import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;



public class Mallocator {

    public static void main(String[] args) {
       ff();
       bf();
       wf();
    }
    public static void ff() {
        try {
            //read input file
            File input = new File("Minput.data");
            Scanner scr = new Scanner(input);
            int segmentTotal = Integer.valueOf(scr.next());

            //create free memory and nonallocate list
            ArrayList<Segment> segmentList = new ArrayList<Segment>();
            ArrayList<Integer> nonAllocate = new ArrayList<Integer>();
            ArrayList<Process> allocated = new ArrayList<Process>();

            for (int i = 0; i < segmentTotal; i++) {
                int segmentStart = Integer.valueOf(scr.next());
                int segmentEnd = Integer.valueOf(scr.next());
                segmentList.add(new Segment(segmentStart, segmentEnd));
                System.out.println(segmentStart + " " + segmentEnd);
            }

            //iterate over segments
            int processTotal = Integer.valueOf(scr.next());

            for (int i = 0; i < processTotal; i++) {
                boolean foundSpace = false;
                int pid = Integer.valueOf(scr.next());
                int pSize = Integer.valueOf(scr.next());
                System.out.println(pid + " " + pSize);

                for (int j = 0; j < segmentTotal; j++) {
                    if (segmentList.get(j).getSize() >= pSize) {
                        allocated.add(new Process(pid, segmentList.get(j).getStart(), pSize));

                        System.out.println("allocated " + pid + " at " + segmentList.get(j).getStart());
                        segmentList.get(j).setStart(pSize);
                        foundSpace = true;
                        break;
                    }

                }
                if (!foundSpace) {
                    System.out.println("nonallocated: " + pid);
                    nonAllocate.add(pid);
                }

            }

            //test
            String outText = new String();
            for (int i = 0; i < allocated.size(); i++) {
                System.out.println(allocated.get(i).getPid() + " " + allocated.get(i).getStart() + " " + allocated.get(i).getEnd());
                outText = outText.concat(Integer.toString(allocated.get(i).getPid()) + " ");
                outText = outText.concat(Integer.toString(allocated.get(i).getStart())+ " ");
                outText = outText.concat(Integer.toString(allocated.get(i).getEnd())+ " ");
                outText = outText.concat(System.lineSeparator());
            }
            outText = outText.concat("-");
            for (int j = 0; j < nonAllocate.size(); j++) {
                System.out.println("nonallocated: " + nonAllocate.get(j));
                outText = outText.concat(Integer.toString(nonAllocate.get(j)) + ", ");
            }

            scr.close();

            //write to file
            try {
                BufferedWriter outWriter = new BufferedWriter(new FileWriter("FFoutput.data"));
                outWriter.write(outText);
                outWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void bf() {
        try {
            //read input file
            File input = new File("Minput.data");
            Scanner scr = new Scanner(input);
            int segmentTotal = Integer.valueOf(scr.next());

            //create free memory and nonallocate list
            ArrayList<Segment> segmentList = new ArrayList<Segment>();
            ArrayList<Integer> nonAllocate = new ArrayList<Integer>();
            ArrayList<Process> allocated = new ArrayList<Process>();

            for (int i = 0; i < segmentTotal; i++) {
                int segmentStart = Integer.valueOf(scr.next());
                int segmentEnd = Integer.valueOf(scr.next());
                segmentList.add(new Segment(segmentStart, segmentEnd));
                System.out.println(segmentStart + " " + segmentEnd);
            }

            //iterate over segments
            int processTotal = Integer.valueOf(scr.next());

            for (int i = 0; i < processTotal; i++) {
                boolean foundSpace = false;
                int pid = Integer.valueOf(scr.next());
                int pSize = Integer.valueOf(scr.next());
                int min = Integer.MAX_VALUE;
                int potential = -1;
                System.out.println(pid + " " + pSize);

                for (int j = 0; j < segmentTotal; j++) {
                    if (segmentList.get(j).getSize() >= pSize && segmentList.get(j).getSize() < min) {
                        min = segmentList.get(j).getSize();
                        potential = j;
                        foundSpace = true;
                       // allocated.add(new Process(pid, segmentList.get(j).getStart(), pSize));

                       // System.out.println("allocated " + pid + " at " + segmentList.get(j).getStart());
                       // segmentList.get(j).setStart(pSize);
                    }

                }
                if(foundSpace) {
                    allocated.add(new Process(pid, segmentList.get(potential).getStart(), pSize));
                    System.out.println("allocated " + pid + " at " + segmentList.get(potential).getStart());
                    segmentList.get(potential).setStart(pSize);
                }
                else {
                    System.out.println("nonallocated: " + pid);
                    nonAllocate.add(pid);
                }
            }

            //test
            String outText = new String();
            for (int i = 0; i < allocated.size(); i++) {
                System.out.println(allocated.get(i).getPid() + " " + allocated.get(i).getStart() + " " + allocated.get(i).getEnd());
                outText = outText.concat(Integer.toString(allocated.get(i).getPid()) + " ");
                outText = outText.concat(Integer.toString(allocated.get(i).getStart())+ " ");
                outText = outText.concat(Integer.toString(allocated.get(i).getEnd())+ " ");
                outText = outText.concat(System.lineSeparator());
            }
            outText = outText.concat("-");
            for (int j = 0; j < nonAllocate.size(); j++) {
                System.out.println("nonallocated: " + nonAllocate.get(j));
                outText = outText.concat(Integer.toString(nonAllocate.get(j)) + ", ");
            }

            scr.close();

            //write to file
            try {
                BufferedWriter outWriter = new BufferedWriter(new FileWriter("BFoutput.data"));
                outWriter.write(outText);
                outWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void wf() {
        try {
            //read input file
            File input = new File("Minput.data");
            Scanner scr = new Scanner(input);
            int segmentTotal = Integer.valueOf(scr.next());

            //create free memory and nonallocate list
            ArrayList<Segment> segmentList = new ArrayList<Segment>();
            ArrayList<Integer> nonAllocate = new ArrayList<Integer>();
            ArrayList<Process> allocated = new ArrayList<Process>();

            for (int i = 0; i < segmentTotal; i++) {
                int segmentStart = Integer.valueOf(scr.next());
                int segmentEnd = Integer.valueOf(scr.next());
                segmentList.add(new Segment(segmentStart, segmentEnd));
                System.out.println(segmentStart + " " + segmentEnd);
            }

            //iterate over segments
            int processTotal = Integer.valueOf(scr.next());

            for (int i = 0; i < processTotal; i++) {
                boolean foundSpace = false;
                int pid = Integer.valueOf(scr.next());
                int pSize = Integer.valueOf(scr.next());
                int max = Integer.MIN_VALUE;
                int potential = -1;
                System.out.println(pid + " " + pSize);

                for (int j = 0; j < segmentTotal; j++) {
                    if (segmentList.get(j).getSize() >= pSize && segmentList.get(j).getSize() > max) {
                        max = segmentList.get(j).getSize();
                        potential = j;
                        foundSpace = true;
                        // allocated.add(new Process(pid, segmentList.get(j).getStart(), pSize));

                        // System.out.println("allocated " + pid + " at " + segmentList.get(j).getStart());
                        // segmentList.get(j).setStart(pSize);
                    }

                }
                if(foundSpace) {
                    allocated.add(new Process(pid, segmentList.get(potential).getStart(), pSize));
                    System.out.println("allocated " + pid + " at " + segmentList.get(potential).getStart());
                    segmentList.get(potential).setStart(pSize);
                }
                else {
                    System.out.println("nonallocated: " + pid);
                    nonAllocate.add(pid);
                }
            }

            //test
            String outText = new String();
            for (int i = 0; i < allocated.size(); i++) {
                System.out.println(allocated.get(i).getPid() + " " + allocated.get(i).getStart() + " " + allocated.get(i).getEnd());
                outText = outText.concat(Integer.toString(allocated.get(i).getPid()) + " ");
                outText = outText.concat(Integer.toString(allocated.get(i).getStart())+ " ");
                outText = outText.concat(Integer.toString(allocated.get(i).getEnd())+ " ");
                outText = outText.concat(System.lineSeparator());
            }
            outText = outText.concat("-");
            for (int j = 0; j < nonAllocate.size(); j++) {
                System.out.println("nonallocated: " + nonAllocate.get(j));
                outText = outText.concat(Integer.toString(nonAllocate.get(j)) + ", ");
            }

            scr.close();

            //write to file
            try {
                BufferedWriter outWriter = new BufferedWriter(new FileWriter("WFoutput.data"));
                outWriter.write(outText);
                outWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}


