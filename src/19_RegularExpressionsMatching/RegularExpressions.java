  package Chap3;

  public class ReMatch {
      public boolean match(char[] str, char[] pattern)
      {
          if (str == null || pattern == null) {
              return false;
          }

          return matchRecur(str, pattern, 0, 0);
      }

      private boolean matchRecur(char[] str, char[] pattern, int s, int p) {
          if (s == str.length && p == pattern.length) {
              return true;
          }
          // 模式串比文本串先到末尾，肯定没有匹配成功
          if (p == pattern.length && s < str.length) {
              return false;
          }
  		// 两种情况，1、模式和文本都没有到结尾
          // 2、或者文本到了结尾而文本还没有到结尾，此时肯定会调用else分支
          // 第二个字符是*
          if (p < pattern.length-1 && pattern[p + 1] == '*') {
              if ((s < str.length && str[s] == pattern[p]) || (pattern[p]== '.' && s < str.length))
                  return matchRecur(str, pattern, s, p + 2) ||
                          matchRecur(str, pattern, s + 1, p+2) ||
                          matchRecur(str,pattern,s + 1, p);
              else
                  return matchRecur(str, pattern, s, p + 2);
          }
  		// 第二个字符不是*
          if ((s < str.length && str[s] == pattern[p]) || (pattern[p]== '.' && s < str.length)) {
              return matchRecur(str, pattern, s + 1, p + 1);
          }
          return false;
      }
}
