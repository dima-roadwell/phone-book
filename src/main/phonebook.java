package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class phonebook {
    public static Map<String, String> PhonebookInit()
    {
        Map<String, String> pb = new HashMap<>();

        pb.put("John", "1111111, 222222222");
        pb.put("Linda", "3333333");
        pb.put("Jack", "5555555, 66666666, 777777777");

        return pb;
    }

    public static void PhonebookAction(Map<String, String> pb, String name, String phone)
    {
        pb.computeIfPresent(name, (key, value) -> value + ", " + phone);
        pb.computeIfAbsent(name, key -> phone);
    }

    public static void DisplayPhonebook(Map<String, String> pb)
    {
        HashMap<String, Integer> phoneCounter = new HashMap<>();
        LinkedHashMap<String, Integer> sortedPhoneCounter = new LinkedHashMap<>();

        ArrayList<Integer> list = new ArrayList<>();

        int counter = 0;

        Pattern pattern = Pattern.compile(",");

        // Честно, очень влом заморачиваться с нормальным подсчетом телефонов, поэтому считаю запятые
        for (Object o : pb.keySet()) {
            Matcher matcher = pattern.matcher(pb.get(o).toString());
            
            while(matcher.find())
            {
                counter++;
            }
            counter++;

            phoneCounter.put(o.toString(), counter);
            list.add(counter);
            counter = 0;
        }

        Collections.sort(list);

        for (int num : list.reversed()) {
            for (Entry<String, Integer> entry : phoneCounter.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedPhoneCounter.put(entry.getKey(), num);
                }
            }
        }

        for(Object o : sortedPhoneCounter.keySet())
        {
            System.out.format("Name: %s\nPhones: %s\n", o, pb.get(o));
        }
    }

    public static void main(String[] args) {
        Map<String, String> phonebookMap = PhonebookInit();

        DisplayPhonebook(phonebookMap);

        PhonebookAction(phonebookMap, "Tony", "8888888888");
        System.out.println();
        DisplayPhonebook(phonebookMap);
    }
}
