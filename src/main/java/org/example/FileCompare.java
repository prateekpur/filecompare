package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class FileCompare    {


    public static void compareFiles (String file1, String file2) {
        Map<String, String> map1 = readFile(file1);
        Map<String, String> map2 = readFile(file2);
        Set<String> onlyMap1 = new HashSet<>(), onlyMap2 = new HashSet<>();
        Set<String> extraMap1 = new HashSet<>(), extraMap2 = new HashSet<>();

        //find records from map1 missing in map2
        for (String key : map1.keySet())    {
            if (!map2.containsKey(key)) {
                onlyMap1.add(key);
            }   else    {
                String chVal1[] = map1.get(key).split(",");
                String chVal2[] = map2.get(key).split(",");
                if (chVal1.length > chVal2.length)  {
                    extraMap1.add(key);
                }   else if (chVal2.length > chVal1.length) {
                    extraMap2.add(key);
                }   else    {
                    // rows in both files have a seperate order
                    // row appears same number of times in both files , so this row is not an extra
                }
            }
        }
        for (String key : map2.keySet())    {
            if (!map1.containsKey(key)) {
                onlyMap2.add(key);
            }
        }
        if (onlyMap1.isEmpty() && onlyMap2.isEmpty() && extraMap1.isEmpty() && extraMap2.isEmpty()) {
            System.out.println("Files are same");
        }   else{
            if (!onlyMap1.isEmpty())   {
                System.out.println("Lines only in file1 : " + onlyMap1.toString());
            }
            if (!onlyMap2.isEmpty())   {
                System.out.println("Lines only in file2 : " + onlyMap2.toString());
            }
            if (!extraMap1.isEmpty())   {
                System.out.println("Lines appearing more times in file1 : " + extraMap1.toString());
            }
            if (!extraMap2.isEmpty())  {
                System.out.println("Lines appearing more times in file2 : " + extraMap2.toString());
            }
        }
    }

    /**
     *
     * @param filePath
     * @return Map with key = line in file and value = line numbers where each line is present in file
     */
    public static Map<String, String> readFile(String filePath)   {
        Map<String, String> map = new HashMap<>();
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                ++i;
                String data = myReader.nextLine();
                if (data.isEmpty())  {
                    continue;
                }
                if (map.containsKey(data))  {
                    map.put(data, map.get(data) + "," + i);
                }   else    {
                    map.put(data, Integer.toString(i));
                }
            }
            myReader.close();
        } catch (FileNotFoundException e)   {
            e.printStackTrace();
        }
        return map;
    }
}
