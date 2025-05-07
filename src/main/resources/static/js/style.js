// 面板风格
style = Ext.extend(Ext.Button, {
	text : "面板风格",
	pressed : false,
	menu : [{
		text : "默认风格",
		icon : 'images/vista.jpg',
		handler : function() {
			changeSkin('ext-all')
		}
	}, {
		text : "银白风格",
		icon : 'images/gray.jpg',
		handler : function() {
			changeSkin('xtheme-gray')
		}
	}, {
		text : "深蓝风格",
		icon : 'images/slate.jpg',
		handler : function() {
			changeSkin('xtheme-slate')
		}
	}, {
		text : "紫色风格",
		icon : 'images/purple.jpg',
		handler : function() {
			changeSkin('xtheme-purple')
		}
	}, {
		text : "绿色风格",
		icon : 'images/green.jpg',
		handler : function() {
			changeSkin('xtheme-olive')
		}
	}, {
		text : "灰色风格",
		icon : 'images/darkgray.jpg',
		handler : function() {
			changeSkin('xtheme-darkgray')
		}
	}, {
		text : "黑色风格",
		icon : 'images/black.jpg',
		handler : function() {
			changeSkin('xtheme-slickness')
		}
	}]
})
// 改变面板风格函数
changeSkin = function(value) {
	var pathName = window.document.location.pathname;
	var projectName = pathName.substring(1, pathName.substr(1).indexOf('/') + 1);
	Ext.util.CSS.swapStyleSheet('window', projectName + '/ext/resources/css/' + value + '.css');
}