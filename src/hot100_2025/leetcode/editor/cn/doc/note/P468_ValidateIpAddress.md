# 验证IP地址 - 深入解析

你问的这道题确实需要仔细理解边界条件和各种特殊情况。让我详细解释验证IPv4和IPv6地址的思路和实现。

## 题目理解
验证给定的字符串是有效的IPv4地址、IPv6地址，还是都不是。

## 解题思路

### 总思路
分别验证IPv4和IPv6的格式要求，通过分割字符串和检查每个部分的合法性来判断。

### IPv4验证思路
1. 用"."分割字符串，必须得到4个部分
2. 每个部分必须是纯数字
3. 每个部分不能有前导零（除非就是"0"）
4. 每个部分的数字必须在0-255之间

### IPv6验证思路
1. 用":"分割字符串，必须得到8个部分
2. 每个部分必须是1-4位的十六进制数
3. 允许大小写字母a-f/A-F
4. 允许前导零

## Java代码实现

```java
class Solution {
    public String validIPAddress(String queryIP) {
        // 检查是否是IPv4
        if (queryIP.chars().filter(ch -> ch == '.').count() == 3) {
            return validateIPv4(queryIP) ? "IPv4" : "Neither";
        }
        // 检查是否是IPv6
        else if (queryIP.chars().filter(ch -> ch == ':').count() == 7) {
            return validateIPv6(queryIP) ? "IPv6" : "Neither";
        }
        return "Neither";
    }
    
    /**
     * 验证IPv4地址
     */
    private boolean validateIPv4(String ip) {
        // 用"."分割字符串
        String[] parts = ip.split("\\.", -1); // 使用-1保留空字符串
        
        // 必须正好有4个部分
        if (parts.length != 4) {
            return false;
        }
        
        for (String part : parts) {
            // 1. 每个部分不能为空
            if (part.length() == 0) {
                return false;
            }
            
            // 2. 长度不能超过3
            if (part.length() > 3) {
                return false;
            }
            
            // 3. 不能有前导零（除非就是"0"）
            if (part.length() > 1 && part.charAt(0) == '0') {
                return false;
            }
            
            // 4. 必须全是数字
            for (char c : part.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            
            // 5. 数字必须在0-255之间
            int num = Integer.parseInt(part);
            if (num < 0 || num > 255) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 验证IPv6地址
     */
    private boolean validateIPv6(String ip) {
        // 用":"分割字符串
        String[] parts = ip.split(":", -1); // 使用-1保留空字符串
        
        // 必须正好有8个部分
        if (parts.length != 8) {
            return false;
        }
        
        // 定义合法的十六进制字符
        String hexdigits = "0123456789abcdefABCDEF";
        
        for (String part : parts) {
            // 1. 每个部分长度必须在1-4之间
            if (part.length() == 0 || part.length() > 4) {
                return false;
            }
            
            // 2. 每个字符必须是合法的十六进制字符
            for (char c : part.toCharArray()) {
                if (hexdigits.indexOf(c) == -1) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
```

## 关键点详细解释

### IPv4验证的关键点

```java
String[] parts = ip.split("\\.", -1);
```
- 使用`\\.`是因为`.`在正则表达式中是特殊字符
- 使用`-1`参数确保空字符串也被保留，这样可以检测到连续点的情况

**前导零检查：**
```java
if (part.length() > 1 && part.charAt(0) == '0') {
    return false;
}
```
- `"0"`是允许的（长度=1）
- `"01"`、`"001"`是不允许的（长度>1且以0开头）

**数字范围检查：**
```java
int num = Integer.parseInt(part);
if (num < 0 || num > 255) {
    return false;
}
```
- 虽然前面已经检查了都是数字，但还要确保在有效范围内
- 比如`"256"`、`"-1"`都是无效的

### IPv6验证的关键点

```java
String hexdigits = "0123456789abcdefABCDEF";
```
- 定义所有合法的十六进制字符
- 包括数字0-9和字母a-f/A-F

**长度检查：**
```java
if (part.length() == 0 || part.length() > 4) {
    return false;
}
```
- 每个部分不能为空（防止`"2001::1"`这种情况）
- 每个部分最多4个字符

## 测试用例分析

### IPv4有效用例：
- `"192.168.1.1"` - 常规情况
- `"0.0.0.0"` - 全零，但每个部分都是单个0
- `"255.255.255.255"` - 边界值

### IPv4无效用例：
- `"192.168.1.01"` - 有前导零
- `"192.168.1.00"` - 有前导零
- `"192.168.1.256"` - 超过255
- `"192.168.1"` - 只有3部分
- `"192.168.1.1.1"` - 有5部分
- `"192.168.1."` - 最后部分为空
- `".192.168.1"` - 第一部分为空
- `"192.168..1"` - 中间部分为空

### IPv6有效用例：
- `"2001:0db8:85a3:0000:0000:8a2e:0370:7334"` - 常规
- `"2001:db8:85a3:0:0:8A2E:0370:7334"` - 缩写形式
- `"2001:0db8:85a3::8a2e:0370:7334"` - 注意：这个在我们的实现中会失败，因为我们要求正好8部分

### IPv6无效用例：
- `"2001:0db8:85a3::8a2e:0370:7334"` - 双冒号缩写（我们的实现会拒绝）
- `"2001:0db8:85a3:0:0:8a2e:0370:7334:1234"` - 超过8部分
- `"2001:0db8:85a3:0:0:8a2e:0370"` - 只有7部分
- `"2001:0db8:85a3:0:0:8g2e:0370:7334"` - 包含非法字符g

## 学习建议

### 1. 理解分割参数
- `split("\\.", -1)`中的`-1`很重要，确保能检测到空部分
- 如果不加`-1`，连续的分隔符会被合并

### 2. 边界条件思维
- 始终考虑空字符串、前导零、超出范围等情况
- 考虑字符串开头和结尾的特殊情况

### 3. 字符验证技巧
- IPv4：使用`Character.isDigit()`检查数字
- IPv6：使用预定义字符串检查十六进制字符

### 4. 相关题目练习
1. **字符串转换整数 (atoi)** - 类似的边界检查
2. **验证回文串** - 字符验证技巧
3. **有效的括号** - 分隔符匹配思维

### 5. 调试技巧
- 为每个测试用例画出验证流程图
- 使用System.out.println输出中间结果
- 特别注意空字符串和边界值的情况

这道题的关键在于对各种边界情况的全面考虑，通过这道题可以很好地训练你的细节处理能力和边界条件思维！