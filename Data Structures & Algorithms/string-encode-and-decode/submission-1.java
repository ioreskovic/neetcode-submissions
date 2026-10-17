class Solution {
    private static final char DELIMITER = '#';

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.length()).append(DELIMITER).append(s);
        }
        return sb.toString();
    }

    public List<String> decode(String str) {
        List<String> result = new ArrayList<>();
        int startHeaderIdx = 0;

        while (startHeaderIdx < str.length()) {
            var endHeaderIdx = str.indexOf(DELIMITER, startHeaderIdx);
            if (endHeaderIdx == -1) break;

            var contentLength = Integer.parseInt(str.substring(startHeaderIdx, endHeaderIdx));
            var contentStartIdx = endHeaderIdx + 1;
            var contentEndIdx = contentStartIdx + contentLength;
            result.add(str.substring(contentStartIdx, contentEndIdx));
            startHeaderIdx = contentEndIdx;
        }

        return result;
    }
}
