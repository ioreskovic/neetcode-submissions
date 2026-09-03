class Solution {
    public int reverseBits(int n) {
        int res = 0;

        for (int i = 0; i < 32; i++) {
            if (((n >> i) & 1) == 1) {
                res |= (1 << (31 - i));
            }
        }

        return res;
    }

    // res:  0000 | 0000 | 0000 | 0010 | 0011
    // mask: 0001 | 0010 | 0100 | 1000 | 
    // n:    1100 | 1100 | 1100 | 1100 | 
    // and:  0000 | 0000 | 0100 | 1000 | 
    // shit: 3    | 1    | 1    | 3    | 
    // as:   0000 | 0000 | 0010 | 0001 | 

    // 0000 | ()

}
