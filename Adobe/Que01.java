/*
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

If multiple answers are possible, return any of them.

It is guaranteed that the length of the answer string is less than 104 for all the given inputs.

 

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 4, denominator = 333
Output: "0.(012)"
 

Constraints:

-231 <= numerator, denominator <= 231 - 1
denominator != 0
*/
class Solution {
    StringBuilder sb;
    public String fractionToDecimal(int numerator, int denominator) {

        if(numerator == 0){
            return "0";
        }   
        sb = new StringBuilder();
        
        addSign(numerator,denominator);
        divideNumbers(numerator,denominator);

        return sb.toString();
    }
    private void addSign(int a , int b){
        
        if((a > 0 && b > 0) || (a < 0 && b < 0)){
            return;
        }    
        sb.append("-");
    }
    private void divideNumbers(int a,int b){
        
        long num = Math.abs((long)a);
        long den = Math.abs((long)b);

        sb.append(num/den);
        num %= den;
        if(num == 0){
            return;
        } 
        appendDecimalPart(num,den);
    }
    private void appendDecimalPart(long num, long den){

        HashMap<Long,Integer> map = new HashMap<>();
        sb.append(".");
        while(num !=0){

            num *= 10;
            sb.append(num/den);
            num %= den;

            if(map.containsKey(num)){
                sb.insert(map.get(num),"(");
                sb.append(")");
                return;
            }
            else{
                map.put(num,sb.length());
            }
        }
    }
}
