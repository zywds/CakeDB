�༭��ʹ�÷���
1. ���ر༭��
���� KindEditor ���°汾������֮��� examples/index.html �Ϳ��Կ�����ʾ��

����ҳ��: http://www.kindsoft.net/down.php

2. ����༭��
��ѹ kindeditor-x.x.x.zip �ļ����������ļ��ϴ���������վ����Ŀ¼����磺http://��������/editor/

Note:

�����Ը�������ɾ������Ŀ¼���ϴ�����������

asp - ASP����
asp.net - ASP.NET����
php - PHP����
jsp - JSP����
examples - ��ʾ�ļ�
3. �޸�HTMLҳ��
1.����Ҫ��ʾ�༭����λ�����textarea�����
<textarea id="editor_id" name="content" style="width:700px;height:300px;">
&lt;strong&gt;HTML����&lt;/strong&gt;
</textarea>
Note:

id�ڵ�ǰҳ�������Ψһ��ֵ��
��textarea������HTML���ݼ���ʵ�ֱ༭����������Ҫע����ǣ�����ӷ������˳���(ASP��PHP��ASP.NET��)ֱ����ʾ���ݣ������ת��HTML�����ַ�(>,<,&,��)��������ο�������Ŀ¼�����demo.xxx����Ŀǰ֧��ASP��ASP.NET��PHP��JSP��
����Щ������ϲ����Ⱥ͸߶ȿ�����ʾ�����⣬���������һ�¿�Ⱥ͸߶ȡ���Ⱥ͸߶ȿ���inline��ʽ���ã�Ҳ���� �༭����ʼ������ ���á�
2.�ڸ�HTMLҳ��������½ű���
<script charset="utf-8" src="/editor/kindeditor.js"></script>
<script charset="utf-8" src="/editor/lang/zh_CN.js"></script>
<script>
        var editor;
        KindEditor.ready(function(K) {
                editor = K.create('#editor_id');
        });
</script>
Note:

��һ��������������CSSѡ������ƥ����textareaʱֻ�ڵ�һ��Ԫ���ϼ��ر༭����
ͨ��K.create�����ĵڶ������������ԶԱ༭���������ã����������ο� �༭����ʼ������ ��
var options = {
        cssPath : '/css/index.css',
        filterMode : true
};
var editor = K.create('textarea[name="content"]', options);
4. ��ȡHTML����
// ȡ��HTML����
html = editor.html();

// ͬ�����ݺ����ֱ��ȡ��textarea��value
editor.sync();
html = document.getElementById('editor_id').value; // ԭ��API
html = K('#editor_id').val(); // KindEditor Node API
html = $('#editor_id').val(); // jQuery

// ����HTML����
editor.html('HTML����');
Note:

KindEditor�Ŀ��ӻ��������´�����iframe��ִ�У�����ģʽ�µ�textarea��Ҳ���´����ģ���������ύǰ��Ҫִ�� sync() ��HTML�������õ�ԭ����textarea��
KindEditor��Ĭ��������Զ�Ѱ��textarea������formԪ�أ��ҵ�form��onsubmit�¼������sync������������form��ʽ�ύ���ݣ�����Ҫ�ֶ�ִ��sync()������


kindeditor API ��kindeditorʹ���ֲᣬkindeditor������kindeditorʹ�ã��������ռ� 
����
1. KE
Ψһ��ȫ�ֱ�����Ҳ�ǳ���������ռ䡣
�������ͣ�Object
2. KE.version
�༭���İ汾��Ϣ��
�������ͣ�String
3. KE.lang
�༭����������Ϣ��
�������ͣ�Object
4. KE.scriptPath
kindeditor.js��·����
�������ͣ�String
5. KE.htmlPath
�༭����HTMLҳ��·����
�������ͣ�String
ע��3.4�汾�ѷ�����
6. KE.browser
��������ͺͰ汾���ֱ�ΪKE.browser.VERSION��KE.browser.IE��KE.browser.WEBKIT�� KE.browser.GECKO��KE.browser.OPERA��
�������ͣ�Object
ע��3.4��ǰ�汾ֱ�ӷ����ַ������ֱ�Ϊ"IE"��"WEBKIT"��"GECKO"��"OPERA"��
7. KE.setting
�༭���ĳ�ʼ�����Ժ��������á�
�������ͣ�Object
8. KE.g
һ���༭���ı��������������б༭�����ԣ����⻹�������±�����������KE.g[id]����ʾ��
���磺KE.g["content_1"].iframeDoc��ʾidΪ"content_1"�ı༭����iframe document����
�������ͣ�Object
��Ҫ������
container: �༭�����ⲿelement����
iframe: �༭�����iframe����
iframeWin: �༭�����iframe window����
iframeDoc: �༭�����iframe document����
keSel: ��ǰѡ����Ϣ��KE.selection����
keRange: ��ǰѡ����Ϣ��KE.range����
sel: ��ǰѡ����Ϣ�������ԭ��selection����
range: ��ǰѡ����Ϣ�������ԭ��range����
layoutDiv: �༭���������div����3.4�汾�ѷ�����
hideDiv: �༭���������parent div����
dialog: �������ڵ�iframe����3.4�汾�ѷ�����
yesButton: �������ڵ�ȷ����ťinput����
noButton: �������ڵ�ȡ����ťinput����
previewButton: �������ڵ�Ԥ����ťinput����
maskDiv: ��������ʱ��ɫ���ֲ��div����
undoStack: undo/redo��undo��¼��
redoStack: undo/redo��redo��¼��
9. KE.plugin
����༭���Ĳ����
�������ͣ�Object
����
1. KE.$(id, doc)
ȡ��element����doc.getElementById�ı�����
������
id��String��element��id
doc��Object��element����document�����ǿ�ѡ������Ĭ��ֵΪdocument��
����ֵ��
Object��element����
2. KE.$$(name, doc)
����element����doc.createElement�ı�����
������
name��String��element��tag name
doc��Object��element����document�����ǿ�ѡ������Ĭ��ֵΪdocument��
����ֵ��
Object��element����
3. KE.event.add(el, event, listener)
���һ���¼���
������
el��Object��Ҫ����¼���element����
event��String���¼����ƣ�������"click"��"change"��"mousedown"�ȡ�
listener��Function���¼�����ص�������
����ֵ����
4. KE.event.remove(el, event, listener)
ɾ������ӵ�һ���¼���
������
el��Object��Ҫ����¼���element����
event��String���¼����ƣ�������"click"��"change"��"mousedown"�ȡ�
listener��Function���¼�����ص�������
����ֵ����
5. KE.event.input(el, func)
���һ���༭�������¼���
������
el��Object��Ҫ����¼���element����
func��Function���༭����������ʱ�������������
����ֵ����
6. KE.event.ctrl(el, key, func)
���һ��Ctrl+[]�¼���
������
el��Object��Ҫ����¼���element����
key��String��Ctrl��ϼ�����ĸ��֧��A��Z��
func��Function������Ctrl+[]ʱ�������������
����ֵ����
7. KE.event.ready(func)
���һ��document��DOMContentLoaded�¼���
������
func��Function��DOM������ɺ�������������
����ֵ����
8. KE.each(obj, func)
����һ��object��
������
obj��Object��Ҫ������object
func��Function��ѭ��ʱ�����������������Ϊobject��key��value��
����ֵ����
9. KE.eachNode(node, func)
����һ��node��
������
node��Object��Ҫ������parent node
func��Function��ѭ��ʱ�����������������Ϊnode��
����ֵ����
10. KE.format.getHtml(html, htmlTags)
��HTMLת����XHTML����ָ��htmlTags����ʱ������htmlTags�������HTML��ǩ��
������
html��String��HTML�ı�
htmlTags��Object�����˹��򣬿�ѡ������
����ֵ��
String��XHTML�ı�
11. KE.util.getDocumentElement()
ȡ��document element����
��������
����ֵ��
Object��element����
12. KE.util.getDocumentWidth()
ȡ�õ�ǰҳ��Ŀ�ȡ�
��������
����ֵ��
Int��document���
13. KE.util.getDocumentHeight()
ȡ�õ�ǰҳ��ĸ߶ȡ�
��������
����ֵ��
Int��document�߶�
14. KE.util.loadStyle(path)
�ڵ�ǰҳ�����һ��CSS�ļ���
������
path��String��CSS�ļ���URL·��
����ֵ����
15. KE.util.inArray(str, arr)
�ж�һ���ַ����Ƿ���һ�������
������
str��String
arr��Array
����ֵ��
Boolean������true��ʾ�����������false��ʾ���������
16. KE.util.trim(str)
ɾ���ַ������ߵĿո��ַ���
������
str��String
����ֵ��String
17. KE.util.getJsKey(key)
��HTML style���CSS��ת����JavaScript�����������磺KE.util.getJsKey("font-size")�᷵��"fontSize"��
������
key��String
����ֵ��String
18. KE.util.escape(html)
ת��HTML��������ַ���
������
html��String��HTML�ı�
����ֵ��String
19. KE.util.getElementPos(el)
ȡ��ָ��element�����ꡣ
������
el��Object��element����
����ֵ��Object
20. KE.util.getCoords(ev)
ȡ��������ꡣ
������
ev��Object��event����
����ֵ��Object
21. KE.util.setOpacity(el, opacity)
����element��͸���ȡ�
������
el��Object��element����
opacity��Int��͸���ȣ�������0��100�����֡�
����ֵ����
22. KE.util.getIframeDoc(iframe)
ȡ��iframe document����
������
iframe��Object��iframe����
����ֵ��Object
23. KE.util.rgbToHex(str)
��RGB��ʽ����ɫת����16���Ƶ���ɫ��
������
str��String��RGB��ɫ���
����ֵ��String
24. KE.util.createRange(doc)
����ָ��document��range��
������
doc��Object��document����
����ֵ��Object��range����
25. KE.util.getFullHtml(id, tagLineMode)
ȡ�ñ༭��iframe�ĳ�ʼ��HTML�ı���
������
id��String���༭����ID
tagLineMode��Boolean��trueʱ��ʾģ���ǩ��������
����ֵ��String
26. KE.util.getData(id)
ȡ�ñ༭����HTML���ݡ�
������
id��String���༭����ID
����ֵ��String
27. KE.util.getSrcData(id)
ȡ�ñ༭����ԭ��HTML���ݣ�Ҳ����innerHTMLֱ�ӷ��ص�HTML��
������
id��String���༭����ID
����ֵ��String
28. KE.util.getPureData(id)
ȡ�ñ༭���Ĵ��ı����ݣ�������HTML��ǩ��3.4�汾��ʼ����img��embed��ǩ��
������
id��String���༭����ID
����ֵ��String
29. KE.util.setData(id)
�ѱ༭�����������õ�ԭTEXTAREA�ؼ��
������
id��String���༭����ID
����ֵ����
30. KE.util.focus(id)
�ѽ����Ƶ��༭���
������
id��String���༭����ID
����ֵ����
31. KE.util.selection(id)
�ѵ�ǰѡ����Ϣ���õ�KE.g[id].sel��KE.g[id].range��KE.g[id].keSel��KE.g[id].keRange�
������
id��String���༭����ID
����ֵ����
32. KE.util.select(id)
����ѡ��range������IE��Ч��
������
id��String���༭����ID
����ֵ����
33. KE.util.pToBr(id)
���»س���ʱ����BR��ǩ������IE��Ч��
������
id��String���༭����ID
����ֵ����
ע��3.4�汾�ѷ�����
34. KE.util.execCommand(id, cmd, value)
ִ��������Դ��������ϸ��ο������API���document.execCommand��
������
id��String���༭����ID
cmd��String�������execCommand���cmd����
value��String�������execCommand���value����
����ֵ����
35. KE.util.insertHtml(id, html)
��HTML���ݲ��뵽�༭������Ĺ�괦��
������
id��String���༭����ID
html��String��HTML����
����ֵ����
ע��ִ�б�����֮ǰ������ִ�й� KE.util.selection(id)����ΪҪ������KE.g[id].sel��KE.g[id].range��
36. KE.create(id, mode)
�����༭����
������
id��String���༭����ID
mode��Int����ѡ������ָ��1ʱ��body���洴���༭����0��δָ��ʱ��TEXTAREAǰ�洴���༭����
����ֵ����
37. KE.remove(id, mode)
�Ƴ��༭����
������
id��String���༭����ID
mode��Int����ѡ������ָ��1ʱ�Ƴ���body����ı༭����0��δָ��ʱ�Ƴ���TEXTAREAǰ��ı༭����
����ֵ����
38. KE.init(config)
���ñ༭���ĳ�ʼ��������
������
config��Object���༭�����ԵĹ�ϣ���飬������ο��༭������
����ֵ����
39. KE.show(config)
��ʼ���������༭����ִ�б�����ʱ�ȵ���KE.init���ó�ʼ��������Ȼ����DOM������ɺ�ִ��KE.create��
������
config��Object���༭�����ԵĹ�ϣ���飬������ο��༭������
����ֵ����
��
1. KE.selection(win, doc)
KindEditor��selection�࣬ȡ�û�����ѡ�в��ֵ�range��
������
win��Object��window����
oc��Object��document����
��Ա������
sel��Object�������ԭ��selection����
range��Object����ǰselection�������ԭ��range����
keRange��Object����ǰselection��KindEditor range������ο�KE.range��
������
addRange(keRange)�����õ�ǰselection��
focus()������ѡ�С�����IE��Ч��
2. KE.range(doc)
KindEditor��range�࣬Ϊ��������ṩͳһ��range�ӿڡ�
������
doc��Object��document����
��Ա������
startNode��Object����ʼ�ڵ�
startPos��Int����ʼ�ڵ��λ��
endNode��Object�������ڵ�
endPos��Int�������ڵ��λ��
������
getParentElement()�����ذ���range��parent element��
getNodeList()������range���node list��
comparePoints(how, range)���Ƚ�2��keRange��λ�ã�how��������"START_TO_START", "START_TO_END", "END_TO_START","END_TO_END"��
setStart(node, pos)������range�Ŀ�ʼ�ڵ��λ�á�
setEnd(node, pos)������range�Ľ����ڵ��λ�á�
selectNode(node)����node���õ�range����ʼ�ڵ�ͽ����ڵ㶼��node��
extractContents()����ȡrange�����ݡ�
cloneContents()������range�����ݡ�
getText()��ȡ��range�Ĵ��ı����ݡ�
3. KE.cmd(id)
KindEditor�������࣬����execCommand��
������
id��String���༭����ID
��Ա������
doc��Object���༭����iframe document����
keSel��Int��KindEditor selection����
keRange��Object����ǰselection��KindEditor range����
������
wrap(tagName, attributes)����ָ����ǩ����ǰѡ���ı���Ŀǰֻ֧��inline tag��tagNameΪ��ǩ����attributesΪ�ñ�ǩ�������顣
remove(tags)���ڵ�ǰѡ���ı��У����ָ���ı�ǩ�����ԡ�tagsΪ��Ҫɾ���ı�ǩ�����ԡ�


ntext
nvarchar(8000)  













