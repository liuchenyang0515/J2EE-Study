<#-- Freemarkerȡֵ -->
${site}
${url}
<#-- Ĭ��ֵ -->
${author!"author�ǲ����ڵ�����"}
<#-- string��ʽ����� -->
${date?string("yyyy��MM��dd�� HH:mm:ss SSS")}
${number?string("0,00.00")} <#-- С�����ֱ�����λ��ÿ��2λһ������-->

SN: ${computer.sn}
�ͺ�: ${computer.model}
״̬: ${computer.state}
�û�: ${computer.user}
�ɹ�ʱ��: ${computer.dop?string("yyyy��MM��dd�� HH:mm:ss SSS")}
�ɹ��۸�: ${computer.price?string("0.00")}
������Ϣ:
-------------------
CPU: ${computer.info["cpu"]}
�ڴ�: ${computer.info["memory"]!"���ڴ���Ϣ"}