<#list computers as c>
序号：${c_index + 1} <#-- 迭代变量_index保存了循环的索引，从0开始 -->
SN：${c.sn}
型号：${c.model}
<#switch c.state>
<#case 1>
状态：使用中
<#break>
<#case 2>
状态：闲置
<#break>
<#case 3>
状态：已作废
<#break>
</#switch>
<#if c.user??>
用户：${c.user}
</#if>
采购日期：${c.dop?string("yyyy-MM-dd")}
采购价格：${c.price?string("0.00")}
----------------------------------------
</#list>
===================================================
<#list computer_map?keys as k>
${k}-${computer_map[k].model}
${computer_map[k].price?string("0.00")}
</#list>