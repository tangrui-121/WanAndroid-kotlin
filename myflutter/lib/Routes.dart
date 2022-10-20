import 'package:flutter/material.dart';

import 'main.dart' as home;
import 'RoundImage.dart' as roundImage;
import 'HttpList.dart';

String homePage = "/";
String imagePage = "/roundImage";
String httplist = "/httpList";

final routes = {
  homePage: (context) => home.MyHomePage(title: 'I am home hot load'),
  imagePage: (context,{arguments}) => roundImage.SampleApp(arguments: arguments),
  httplist: (context) => HttpPracticePage()
};

//下面这段代码是将一个匿名方法赋值给一个变量
//匿名方法做的事情：处理路由传参，生成 MaterialPageRoute 路由对象
var onGenerateRoute = (settings) {
  Function? pageContentBuilder = routes[settings.name];
  if (pageContentBuilder != null) {
    if (settings.arguments != null) {
      var route = MaterialPageRoute(
          builder: (context) =>
              pageContentBuilder(context, arguments: settings.arguments));
      return route;
    } else {
      var route =
          MaterialPageRoute(builder: (context) => pageContentBuilder(context));
      return route;
    }
  }
};
