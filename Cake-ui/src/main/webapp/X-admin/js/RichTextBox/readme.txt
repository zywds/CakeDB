编辑器使用方法
1. 下载编辑器
下载 KindEditor 最新版本，下载之后打开 examples/index.html 就可以看到演示。

下载页面: http://www.kindsoft.net/down.php

2. 部署编辑器
解压 kindeditor-x.x.x.zip 文件，将所有文件上传到您的网站程序目录里，例如：http://您的域名/editor/

Note:

您可以根据需求删除以下目录后上传到服务器。

asp - ASP程序
asp.net - ASP.NET程序
php - PHP程序
jsp - JSP程序
examples - 演示文件
3. 修改HTML页面
1.在需要显示编辑器的位置添加textarea输入框。
<textarea id="editor_id" name="content" style="width:700px;height:300px;">
&lt;strong&gt;HTML内容&lt;/strong&gt;
</textarea>
Note:

id在当前页面必须是唯一的值。
在textarea里设置HTML内容即可实现编辑，在这里需要注意的是，如果从服务器端程序(ASP、PHP、ASP.NET等)直接显示内容，则必须转换HTML特殊字符(>,<,&,”)。具体请参考各语言目录下面的demo.xxx程序，目前支持ASP、ASP.NET、PHP、JSP。
在有些浏览器上不设宽度和高度可能显示有问题，所以最好设一下宽度和高度。宽度和高度可用inline样式设置，也可用 编辑器初始化参数 设置。
2.在该HTML页面添加以下脚本。
<script charset="utf-8" src="/editor/kindeditor.js"></script>
<script charset="utf-8" src="/editor/lang/zh_CN.js"></script>
<script>
        var editor;
        KindEditor.ready(function(K) {
                editor = K.create('#editor_id');
        });
</script>
Note:

第一个参数可用其它CSS选择器，匹配多个textarea时只在第一个元素上加载编辑器。
通过K.create函数的第二个参数，可以对编辑器进行配置，具体参数请参考 编辑器初始化参数 。
var options = {
        cssPath : '/css/index.css',
        filterMode : true
};
var editor = K.create('textarea[name="content"]', options);
4. 获取HTML数据
// 取得HTML内容
html = editor.html();

// 同步数据后可以直接取得textarea的value
editor.sync();
html = document.getElementById('editor_id').value; // 原生API
html = K('#editor_id').val(); // KindEditor Node API
html = $('#editor_id').val(); // jQuery

// 设置HTML内容
editor.html('HTML内容');
Note:

KindEditor的可视化操作在新创建的iframe上执行，代码模式下的textarea框也是新创建的，所以最后提交前需要执行 sync() 将HTML数据设置到原来的textarea。
KindEditor在默认情况下自动寻找textarea所属的form元素，找到form后onsubmit事件里添加sync函数，所以用form方式提交数据，不需要手动执行sync()函数。


kindeditor API ，kindeditor使用手册，kindeditor函数，kindeditor使用，超级大收集 
变量
1. KE
唯一的全局变量，也是程序的命名空间。
数据类型：Object
2. KE.version
编辑器的版本信息。
数据类型：String
3. KE.lang
编辑器的中文信息。
数据类型：Object
4. KE.scriptPath
kindeditor.js的路径。
数据类型：String
5. KE.htmlPath
编辑器的HTML页面路径。
数据类型：String
注：3.4版本已废弃。
6. KE.browser
浏览器类型和版本，分别为KE.browser.VERSION、KE.browser.IE、KE.browser.WEBKIT、 KE.browser.GECKO、KE.browser.OPERA。
数据类型：Object
注：3.4以前版本直接返回字符串，分别为"IE"、"WEBKIT"、"GECKO"、"OPERA"。
7. KE.setting
编辑器的初始化属性和其它配置。
数据类型：Object
8. KE.g
一个编辑器的变量集，包含所有编辑器属性，此外还包含以下变量，经常用KE.g[id]来表示。
例如：KE.g["content_1"].iframeDoc表示id为"content_1"的编辑器的iframe document对象。
数据类型：Object
主要变量：
container: 编辑器的外部element对象。
iframe: 编辑区域的iframe对象。
iframeWin: 编辑区域的iframe window对象。
iframeDoc: 编辑区域的iframe document对象。
keSel: 当前选中信息的KE.selection对象。
keRange: 当前选中信息的KE.range对象。
sel: 当前选中信息的浏览器原生selection对象。
range: 当前选中信息的浏览器原生range对象。
layoutDiv: 编辑器弹出层的div对象。3.4版本已废弃。
hideDiv: 编辑器弹出层的parent div对象。
dialog: 弹出窗口的iframe对象。3.4版本已废弃。
yesButton: 弹出窗口的确定按钮input对象。
noButton: 弹出窗口的取消按钮input对象。
previewButton: 弹出窗口的预览按钮input对象。
maskDiv: 弹出窗口时灰色遮罩层的div对象。
undoStack: undo/redo的undo记录。
redoStack: undo/redo的redo记录。
9. KE.plugin
定义编辑器的插件。
数据类型：Object
函数
1. KE.$(id, doc)
取得element对象，doc.getElementById的别名。
参数：
id：String，element的id
doc：Object，element所在document对象，是可选参数，默认值为document。
返回值：
Object，element对象
2. KE.$$(name, doc)
创建element对象，doc.createElement的别名。
参数：
name：String，element的tag name
doc：Object，element所在document对象，是可选参数，默认值为document。
返回值：
Object，element对象
3. KE.event.add(el, event, listener)
添加一个事件。
参数：
el：Object，要添加事件的element对象
event：String，事件名称，可设置"click"，"change"，"mousedown"等。
listener：Function，事件处理回调函数。
返回值：无
4. KE.event.remove(el, event, listener)
删除已添加的一个事件。
参数：
el：Object，要添加事件的element对象
event：String，事件名称，可设置"click"，"change"，"mousedown"等。
listener：Function，事件处理回调函数。
返回值：无
5. KE.event.input(el, func)
添加一个编辑器输入事件。
参数：
el：Object，要添加事件的element对象
func：Function，编辑器输入内容时调用这个函数。
返回值：无
6. KE.event.ctrl(el, key, func)
添加一个Ctrl+[]事件。
参数：
el：Object，要添加事件的element对象
key：String，Ctrl组合键的字母，支持A到Z。
func：Function，按下Ctrl+[]时调用这个函数。
返回值：无
7. KE.event.ready(func)
添加一个document的DOMContentLoaded事件。
参数：
func：Function，DOM加载完成后调用这个函数。
返回值：无
8. KE.each(obj, func)
遍历一个object。
参数：
obj：Object，要遍历的object
func：Function，循环时调用这个函数，参数为object的key和value。
返回值：无
9. KE.eachNode(node, func)
遍历一个node。
参数：
node：Object，要遍历的parent node
func：Function，循环时调用这个函数，参数为node。
返回值：无
10. KE.format.getHtml(html, htmlTags)
把HTML转换成XHTML，当指定htmlTags参数时，按照htmlTags规则过滤HTML标签。
参数：
html：String，HTML文本
htmlTags：Object，过滤规则，可选参数。
返回值：
String，XHTML文本
11. KE.util.getDocumentElement()
取得document element对象。
参数：无
返回值：
Object，element对象
12. KE.util.getDocumentWidth()
取得当前页面的宽度。
参数：无
返回值：
Int，document宽度
13. KE.util.getDocumentHeight()
取得当前页面的高度。
参数：无
返回值：
Int，document高度
14. KE.util.loadStyle(path)
在当前页面加载一个CSS文件。
参数：
path：String，CSS文件的URL路径
返回值：无
15. KE.util.inArray(str, arr)
判断一个字符串是否在一个数组里。
参数：
str：String
arr：Array
返回值：
Boolean，返回true表示在数组里，返回false表示不在数组里。
16. KE.util.trim(str)
删除字符串两边的空格字符。
参数：
str：String
返回值：String
17. KE.util.getJsKey(key)
把HTML style里的CSS名转换成JavaScript属性名。例如：KE.util.getJsKey("font-size")会返回"fontSize"。
参数：
key：String
返回值：String
18. KE.util.escape(html)
转换HTML里的特殊字符。
参数：
html：String，HTML文本
返回值：String
19. KE.util.getElementPos(el)
取得指定element的坐标。
参数：
el：Object，element对象
返回值：Object
20. KE.util.getCoords(ev)
取得鼠标坐标。
参数：
ev：Object，event对象
返回值：Object
21. KE.util.setOpacity(el, opacity)
设置element的透明度。
参数：
el：Object，element对象
opacity：Int，透明度，可设置0到100的数字。
返回值：无
22. KE.util.getIframeDoc(iframe)
取得iframe document对象。
参数：
iframe：Object，iframe对象
返回值：Object
23. KE.util.rgbToHex(str)
把RGB格式的颜色转换成16进制的颜色。
参数：
str：String，RGB颜色标记
返回值：String
24. KE.util.createRange(doc)
创建指定document的range。
参数：
doc：Object，document对象
返回值：Object，range对象
25. KE.util.getFullHtml(id, tagLineMode)
取得编辑器iframe的初始化HTML文本。
参数：
id：String，编辑器的ID
tagLineMode：Boolean，true时显示模块标签的轮廓。
返回值：String
26. KE.util.getData(id)
取得编辑器的HTML内容。
参数：
id：String，编辑器的ID
返回值：String
27. KE.util.getSrcData(id)
取得编辑器的原生HTML内容，也就是innerHTML直接返回的HTML。
参数：
id：String，编辑器的ID
返回值：String
28. KE.util.getPureData(id)
取得编辑器的纯文本内容，不包含HTML标签。3.4版本开始包含img和embed标签。
参数：
id：String，编辑器的ID
返回值：String
29. KE.util.setData(id)
把编辑器的内容设置到原TEXTAREA控件里。
参数：
id：String，编辑器的ID
返回值：无
30. KE.util.focus(id)
把焦点移到编辑器里。
参数：
id：String，编辑器的ID
返回值：无
31. KE.util.selection(id)
把当前选中信息设置到KE.g[id].sel，KE.g[id].range，KE.g[id].keSel，KE.g[id].keRange里。
参数：
id：String，编辑器的ID
返回值：无
32. KE.util.select(id)
重新选中range，仅在IE有效。
参数：
id：String，编辑器的ID
返回值：无
33. KE.util.pToBr(id)
按下回车键时生成BR标签，仅在IE有效。
参数：
id：String，编辑器的ID
返回值：无
注：3.4版本已废弃。
34. KE.util.execCommand(id, cmd, value)
执行浏览器自带的命令，详细请参考浏览器API里的document.execCommand。
参数：
id：String，编辑器的ID
cmd：String，浏览器execCommand里的cmd参数
value：String，浏览器execCommand里的value参数
返回值：无
35. KE.util.insertHtml(id, html)
把HTML内容插入到编辑区域里的光标处。
参数：
id：String，编辑器的ID
html：String，HTML内容
返回值：无
注：执行本函数之前必须先执行过 KE.util.selection(id)，因为要先设置KE.g[id].sel和KE.g[id].range。
36. KE.create(id, mode)
创建编辑器。
参数：
id：String，编辑器的ID
mode：Int，可选参数，指定1时在body下面创建编辑器，0或未指定时在TEXTAREA前面创建编辑器。
返回值：无
37. KE.remove(id, mode)
移除编辑器。
参数：
id：String，编辑器的ID
mode：Int，可选参数，指定1时移除在body下面的编辑器，0或未指定时移除在TEXTAREA前面的编辑器。
返回值：无
38. KE.init(config)
设置编辑器的初始化参数。
参数：
config：Object，编辑器属性的哈希数组，具体请参考编辑器属性
返回值：无
39. KE.show(config)
初始化并创建编辑器。执行本函数时先调用KE.init设置初始化参数，然后在DOM加载完成后执行KE.create。
参数：
config：Object，编辑器属性的哈希数组，具体请参考编辑器属性
返回值：无
类
1. KE.selection(win, doc)
KindEditor的selection类，取得或设置选中部分的range。
参数：
win：Object，window对象
oc：Object，document对象
成员变量：
sel：Object，浏览器原生selection对象
range：Object，当前selection的浏览器原生range对象
keRange：Object，当前selection的KindEditor range对象，请参考KE.range。
方法：
addRange(keRange)：设置当前selection。
focus()：重新选中、仅在IE有效。
2. KE.range(doc)
KindEditor的range类，为各浏览器提供统一的range接口。
参数：
doc：Object，document对象
成员变量：
startNode：Object，开始节点
startPos：Int，开始节点的位置
endNode：Object，结束节点
endPos：Int，结束节点的位置
方法：
getParentElement()：返回包含range的parent element。
getNodeList()：返回range里的node list。
comparePoints(how, range)：比较2个keRange的位置，how可以设置"START_TO_START", "START_TO_END", "END_TO_START","END_TO_END"。
setStart(node, pos)：设置range的开始节点和位置。
setEnd(node, pos)：设置range的结束节点和位置。
selectNode(node)：把node设置到range，开始节点和结束节点都是node。
extractContents()：提取range的内容。
cloneContents()：复制range的内容。
getText()：取得range的纯文本内容。
3. KE.cmd(id)
KindEditor的命令类，类似execCommand。
参数：
id：String，编辑器的ID
成员变量：
doc：Object，编辑器的iframe document对象
keSel：Int，KindEditor selection对象
keRange：Object，当前selection的KindEditor range对象
方法：
wrap(tagName, attributes)：用指定标签包当前选中文本，目前只支持inline tag。tagName为标签名，attributes为该标签属性数组。
remove(tags)：在当前选中文本中，清除指定的标签和属性。tags为你要删除的标签和属性。


ntext
nvarchar(8000)  













