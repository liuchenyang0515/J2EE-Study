<#list computers as c>
��ţ�${c_index + 1} <#-- ��������_index������ѭ������������0��ʼ -->
SN��${c.sn}
�ͺţ�${c.model}
<#switch c.state>
<#case 1>
״̬��ʹ����
<#break>
<#case 2>
״̬������
<#break>
<#case 3>
״̬��������
<#break>
</#switch>
<#if c.user??>
�û���${c.user}
</#if>
�ɹ����ڣ�${c.dop?string("yyyy-MM-dd")}
�ɹ��۸�${c.price?string("0.00")}
----------------------------------------
</#list>
===================================================
<#list computer_map?keys as k>
${k}-${computer_map[k].model}
${computer_map[k].price?string("0.00")}
</#list>