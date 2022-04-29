import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedDNASeq_187 {

    /**
     * 187. Repeated DNA Sequences
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> collection = new HashSet<>();
        if(s.length() < 10) return new ArrayList<>();

        Set<String> set = new HashSet<>();
        int start = 0;
        for(int i = 9; i<s.length(); i++){
            if(i - start + 1 == 10){
                String sub = s.substring(start, i+1);
                if(!set.add(sub)) collection.add(sub);
                start += 1;
            }
        }
        List<String> ret = new ArrayList<>();
        ret.addAll(collection);
        return ret;
    }


    // convert string to integer by encoding ACTG
    public List<String> findRepeatedDnaSequences2(String s) {
        Set<String> collection = new HashSet<>();
        if(s.length() < 10) return new ArrayList<>();

        Set<Integer> set = new HashSet<>();
        char[] encodeMap = new char[26];
        encodeMap['A' - 'A'] = 0;
        encodeMap['C' - 'A'] = 1;
        encodeMap['G' - 'A'] = 2;
        encodeMap['T' - 'A'] = 3;
        // A - 00, C - 01, G - 10, T - 11

        for(int i = 0; i<s.length()-9; i++){
            int code = 0;
            for(int j = i ; j<i+10; j++){
                code <<= 2;
                code |= encodeMap[s.charAt(j) - 'A'];
            }
            if(!set.add(code)) collection.add(s.substring(i, i+10));
        }
        List<String> ret = new ArrayList<>();
        ret.addAll(collection);
        return ret;
    }
}
