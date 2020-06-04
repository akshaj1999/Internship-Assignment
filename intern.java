import java.io.*;
import java.util.StringTokenizer;
import java.util.*;
import java.math.*; 
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;



// public
class Solution{

    public static Map<LocalDate, Long> solution(Map<LocalDate, Long> hm)
    {
        Iterator hmIterator = hm.entrySet().iterator(); 
        Map.Entry mapElement1=null,mapElement2=null;
            Map.Entry previous = null;
            boolean flag = true;
        Map<LocalDate, Long> back = new TreeMap<LocalDate, Long>();
        for(Map.Entry e: hm.entrySet())
        {
            back.put((LocalDate)e.getKey(),(Long)e.getValue());
        }
        while (hmIterator.hasNext()) { 
            if(flag)
            {
             mapElement1 =(Map.Entry) hmIterator.next();
             mapElement2 =(Map.Entry) hmIterator.next();
            }
            else
            {
              mapElement2 = (Map.Entry)hmIterator.next();
            }
            LocalDate d1;
            long end = 0,start = 0;
            if(!flag)
            {
                d1 =(LocalDate) previous.getKey();
                start = (long)previous.getValue();
            }
            else
            {
            d1 =(LocalDate) mapElement1.getKey();
            start = (long)  mapElement1.getValue();
            }
           LocalDate d2 =(LocalDate) mapElement2.getKey();
           end =(long) mapElement2.getValue();
           long intervalDays = ChronoUnit.DAYS.between(d1, d2);
           long gap = (end-start)/intervalDays;
           d2 = d2.minusDays(1);
           for (LocalDate date = d1.plusDays(1); date.isBefore(d2); date = date.plusDays(1)) {
                      start +=gap;
                      back.put(date,start);
                }
                // System.out.println(end);
                // System.out.println(start);
            back.put(d2,((start+end)/2));
           previous = (Map.Entry)mapElement2;
           flag = false;        
        }
         return back;

         // '2019-01-01':100,'2019-01-05':140,'2019-01-30':1090,'2018-10-20':830,'2016-05-22':109823

    }
  
      public static void main(String[] args) throws Exception {
        FastScanner cin = new FastScanner(System.in);

        String str = cin.nextLine();
        
        String strs[] = str.trim().split(",");
        
        Map<LocalDate, Long> m = new TreeMap<LocalDate, Long>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for(int i=0;i<strs.length;i++)
        {
            String temp[] = strs[i].split(":");
            String key = temp[0].substring(1,temp[0].length()-1);
            LocalDate date = LocalDate.parse(key, formatter);
            long value = Long.parseLong(temp[1]);
            m.put(date,value);
        }
        Map<LocalDate, Long> ans = solution(m);

        for(Map.Entry e: ans.entrySet())
        {
            System.out.println("Date: "+e.getKey()+" Value: "+e.getValue());
        }

        
    }
        
    static class FastScanner {
        private BufferedReader reader = null;
        private StringTokenizer tokenizer = null;
 
        public FastScanner(InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }
 
        public String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
 
        public String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
 
            return tokenizer.nextToken("\n");
        }
 
        public long nextLong() {
            return Long.parseLong(next());
        }
         
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
    }
  }
  


