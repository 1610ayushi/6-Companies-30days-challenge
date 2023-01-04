/*
A string is called a happy prefix if is a non-empty prefix which is also a suffix (excluding itself).

Given a string s, return the longest happy prefix of s. Return an empty string "" if no such prefix exists.

 

Example 1:

Input: s = "level"
Output: "l"
Explanation: s contains 4 prefix excluding itself ("l", "le", "lev", "leve"), and suffix ("l", "el", "vel", "evel"). The largest prefix which is also suffix is given by "l".
Example 2:

Input: s = "ababab"
Output: "abab"
Explanation: "abab" is the largest prefix which is also suffix. They can overlap in the original string.
 

Constraints:

1 <= s.length <= 105
s contains only lowercase English letters.
*/
public String longestPrefix(String s) {
    long h1 = 0, h2 = 0, mul = 1, len = 0, mod = 1000000007;
    for (int i = 0, j = s.length() - 1; j > 0; ++i, --j) {
        int first = s.charAt(i) - 'a', last = s.charAt(j) - 'a';
        h1 = (h1 * 26 + first) % mod;
        h2 = (h2 + mul * last) % mod;
        mul = mul * 26 % mod;
        if (h1 == h2)
            len = i + 1;
    }
    return s.substring(0, (int)len);        
}
