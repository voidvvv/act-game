//package com.mygdx.game.desktop;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class Test {
//    public List<String> findHighAccessEmployees(List<List<String>> access_times) {
//        Map<String,List<String>> mapping = new HashMap<>();
//        Map<String,List<Integer>> countMapping = new HashMap<>();
//        List<String> result = new ArrayList<>();
//        for(int x=0; x<access_times.size(); x++){
//            List<String> em = access_times.get(x);
//            String name = em.get(0);
//            List<String> timeList = mapping.getOrDefault(name,new ArrayList<String>());
//            List<Integer> countList = countMapping.getOrDefault(name,new ArrayList<Integer>());
//            String time = em.get(1);
//            int c=1;
//            for(int i=0; i<timeList.size(); i++){
//                if(countList.get(i)>=3){
//                    break;
//                }
//                if(compareTime(time,timeList.get(i))){
//                    c++;
//                    countList.set(x,countList.get(x)+1);
//                    if(countList.get(i)>=3){
//                        result.add(name);
//                        break;
//                    }
//                }
//                timeList.add(time);
//                countList.add(c);
//            }
//        }
//        return result;
//    }
//
//    boolean compareTime(String t1, String t2){
//        int t1Hour = Integer.valueOf(t1.substring(2));
//        int t2Hour = Integer.valueOf(t1.substring(2));
//        if(t1Hour == t2Hour){
//            return true;
//        }
//        if(Math.abs(t1Hour - t2Hour) == 1){
//            int t1Minate = Integer.valueOf(t1.substring(2,4));
//            int t2Minate = Integer.valueOf(t1.substring(2,4));
//            if(t1Hour<t2Hour){
//                return t1Minate>t2Minate;
//            }else{
//                return t1Minate<t2Minate;
//            }
//        }
//        return false;
//    }
//}
